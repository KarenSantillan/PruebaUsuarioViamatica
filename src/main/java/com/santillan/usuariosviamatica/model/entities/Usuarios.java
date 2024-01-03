package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuarios {
    private Integer idUsuario;
    private String userName;
    private String password1;
    private String mail;
    //private String sessionActivate;
    private Integer idPersona2;
    private String status1;
    private Integer intentos;
    private Collection<RolUsuarios> rolUsuariosByIdUsuario;
    //private Collection<Sessions> sessionsByIdUsuario;
    private Sessions sessionsByIdUsuario;
    private Persona personaByIdPersona2;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario", nullable = false)
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Basic
    @Column(name = "userName", nullable = true, length = 50, unique = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password1", nullable = true, length = 50)
    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    @Basic
    @Column(name = "mail", nullable = true, length = 120)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
/*
    @Basic
    @Column(name = "sessionActivate", nullable = true, length = 1)
    public String getSessionActivate() {
        return sessionActivate;
    }

    public void setSessionActivate(String sessionActivate) {
        this.sessionActivate = sessionActivate;
    }
*/
    @Basic
    @Column(name = "idPersona2", nullable = true)
    public Integer getIdPersona2() {
        return idPersona2;
    }

    public void setIdPersona2(Integer idPersona2) {
        this.idPersona2 = idPersona2;
    }

    @Basic
    @Column(name = "status1", nullable = true, length = 20)
    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return Objects.equals(idUsuario, usuarios.idUsuario) && Objects.equals(userName, usuarios.userName) && Objects.equals(password1, usuarios.password1) && Objects.equals(mail, usuarios.mail) /*&& Objects.equals(sessionActivate, usuarios.sessionActivate) */&& Objects.equals(idPersona2, usuarios.idPersona2) && Objects.equals(status1, usuarios.status1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, userName, password1, mail, /*sessionActivate,*/ idPersona2, status1);
    }

    @OneToMany(mappedBy = "usuariosByIdUsuario")
    public Collection<RolUsuarios> getRolUsuariosByIdUsuario() {
        return rolUsuariosByIdUsuario;
    }

    public void setRolUsuariosByIdUsuario(Collection<RolUsuarios> rolUsuariosByIdUsuario) {
        this.rolUsuariosByIdUsuario = rolUsuariosByIdUsuario;
    }

    @OneToOne(mappedBy = "usuariosByIdUsuario")
    public Sessions getSessionsByIdUsuario() {
        return sessionsByIdUsuario;
    }

    public void setSessionsByIdUsuario(Sessions sessionsByIdUsuario) {
        this.sessionsByIdUsuario = sessionsByIdUsuario;
    }
    /*
    public Collection<Sessions> getSessionsByIdUsuario() {
        return sessionsByIdUsuario;
    }

    public void setSessionsByIdUsuario(Collection<Sessions> sessionsByIdUsuario) {
        this.sessionsByIdUsuario = sessionsByIdUsuario;
    }*/


    @ManyToOne
    @JoinColumn(name = "idPersona2", referencedColumnName = "idPersona")
    public Persona getPersonaByIdPersona2() {
        return personaByIdPersona2;
    }

    public void setPersonaByIdPersona2(Persona personaByIdPersona2) {
        this.personaByIdPersona2 = personaByIdPersona2;
    }


    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }
}
