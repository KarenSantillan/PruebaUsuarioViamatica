package com.santillan.usuariosviamatica.service;

import com.santillan.usuariosviamatica.model.entities.Sessions;
import com.santillan.usuariosviamatica.model.entities.Usuarios;
import com.santillan.usuariosviamatica.repository.SessionsRepository;
import com.santillan.usuariosviamatica.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuariosServices {
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private SessionsRepository sessionsRepository;
    //CREAR USUARIO
    public Usuarios createUsuario(Usuarios usuario){
        validateUser(usuario);
        generateCorreo(usuario);
        return usuariosRepository.save(usuario);
    }

    //LOGIN USUARIO
    public void loginUsuario(Usuarios usuario){
        if (usuariosRepository.findByUsername(usuario.getUserName()).isPresent() || usuariosRepository.findByMail(usuario.getMail()).isPresent()){
            validateIntPass(usuario);
            //String sesiones = usuariosRepository.findBySessionsByIdUsuario(usuario.getIdUsuario());
            Sessions sesiones = usuario.getSessionsByIdUsuario();
            if (sesiones != null){
                throw new IllegalArgumentException("El usuario posee  una sesión activa");
            }else {
                Sessions sesion = new Sessions();
                //CONVERSION DE LOCALDATETIME TO DATE SQL
                LocalDateTime time = LocalDateTime.now();
                java.sql.Date date = java.sql.Date.valueOf(time.toLocalDate());
                sesion.setFechaIngreso(date);
                sesion.setUsuariosByIdUsuario(usuario);
                sessionsRepository.save(sesion);

                //Asignar sesion nueva
                usuario.setSessionsByIdUsuario(sesion);
            }
        }

    }
    public void logoutUsuario(Usuarios usuario){
        if (usuariosRepository.findByUsername(usuario.getUserName()).isPresent() || usuariosRepository.findByMail(usuario.getMail()).isPresent()){
            //String sesiones = usuariosRepository.findBySessionsByIdUsuario(usuario.getIdUsuario());
            Sessions sesiones = usuario.getSessionsByIdUsuario();
            if (sesiones != null){
                LocalDateTime time = LocalDateTime.now();
                java.sql.Date date = java.sql.Date.valueOf(time.toLocalDate());
                sesiones.setFechaCierre(date);

                //Desactivo la sesión
                usuario.setSessionsByIdUsuario(null);
                usuariosRepository.save(usuario);

            }
        }
    }

    public void validateIntPass(Usuarios usuario){
        if (!usuariosRepository.findPassByUsername(usuario.getUserName()).equals(usuario.getPassword1())){
            int intentos = usuario.getIntentos()+1;
            usuario.setIntentos(intentos);
            usuariosRepository.save(usuario);

            if (intentos >= 3){
                usuario.setStatus1("BLOQUEADO");
                usuariosRepository.save(usuario);
                throw new IllegalArgumentException("El usuario ha sido bloqueado debido a múltiples intentos fallidos");
            }
            throw new IllegalArgumentException("Contraseña incorrecta. Intento " + intentos + " de 3");

        }

    }

    public void generateCorreo(Usuarios usuario){
        String nombre = usuario.getPersonaByIdPersona2().getNombres();
        String apellido = usuario.getPersonaByIdPersona2().getApellidos();
        String identificacion = usuario.getPersonaByIdPersona2().getIdentificacion();

        String correoIni = (nombre.substring(0,1) + apellido.split(" ")[0]).toLowerCase();
        String correoF = correoIni + "@" + "mail.com";

        int cont = 1;

        while (usuariosRepository.findByMail(correoF).isPresent()){
            correoF = correoIni + cont + "@" + "mail.com";
            cont++;

        }
        usuario.setMail(correoF);

    }
    public void validateUser(Usuarios usuario){
        validateUserName(usuario.getUserName());
        validatePassw(usuario.getPassword1());
        validateIdentif(usuario.getPersonaByIdPersona2().getIdentificacion());
    }

    public void validateUserName(String username){
        //NO SIGNOS
        if (!username.matches("^[a-zA-Z0-9]*$")){
            throw new IllegalArgumentException("La longitud del nombre de usuario no debe contener signos");
        }

        //NO DUPLICADOS
        if (usuariosRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("El nombre de usuario ya se encuentra utilizado");
        }

        //DEBE CONTENER AL MENOS UN NUMERO
        if (!username.matches(".*\\.*")){
            throw new IllegalArgumentException("El nombre de usuario debe contener al menos un número");
        }

        //DEBE CONTENER AL MENOS UNA LETRA MAYUSCULA
        if (!username.matches(".*[A-Z].*]")){
            throw new IllegalArgumentException("El nombre de usuario debe contener al menos una letra en mayúscula");
        }

        //LONGITUD ENTRE 8 Y 20 CARACTERES
        if (username.length() < 8 || username.length() > 20){
            throw new IllegalArgumentException("La longitud del nombre de usuario debe tener entre 8 y 20 caracteres");
        }

    }

    public void validatePassw(String pass){
        //LONGITUD NO MENOR A 8
        if (pass.length() < 8){
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 dígitos");
        }

        //AL MENOS UNA MAYUSCULA
        if (!pass.matches(".*[A-Z].*]")){
            throw new IllegalArgumentException("La contraseña debe contener al menos una letra en mayúscula");
        }

        //NO ESPACIOS
        if (pass.contains(" ")){
            throw new IllegalArgumentException("La contraseña no debe contener espacios");
        }

        //DEBE CONTENER AL MENOS 1 SIGNO
        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*")){
            throw new IllegalArgumentException("La contraseña debe contener al menos un signo");
        }

    }

    public void validateIdentif(String identificacion){
        //10 DIGITOS
        if (identificacion.length() != 10){
            throw new IllegalArgumentException("La longitud de la identificacion debe ser de 10 digitos");
        }

        //SOLO NUMEROS
        if (identificacion.matches("\\d+")){
            throw new IllegalArgumentException("La identificacion debe solo contener números");
        }

        //NO 4 VECES SEGUIDAS UN NUMERO
        if(identificacion.matches(".*\\d{4,}.*")){
            throw new IllegalArgumentException("La identificacion no debe poseer 4 digitos iguales seguidos ");

        }

    }

}
