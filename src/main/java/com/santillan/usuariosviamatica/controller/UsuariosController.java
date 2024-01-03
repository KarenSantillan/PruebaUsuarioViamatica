package com.santillan.usuariosviamatica.controller;

import com.santillan.usuariosviamatica.model.entities.Usuarios;
import com.santillan.usuariosviamatica.service.UsuariosServices;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

@RestController
@RequestMapping
public class UsuariosController {
    @Autowired
    UsuariosServices usuariosServices;

    // Cargar el esquema JSON para /ingresoUsuario
    InputStream ingresoSchemaStream = getClass().getResourceAsStream("/estructura_json/ingreso.json");
    String ingresoSchemaJson = null;
    {
        try {
            ingresoSchemaJson = IOUtils.toString(ingresoSchemaStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción
        }
    }

    private Schema ingresoRequestSchema = SchemaLoader.load(new JSONObject(ingresoSchemaJson));

    // Cargar el esquema JSON para /login
    InputStream loginSchemaStream = getClass().getResourceAsStream("/estructura_json/login.json");
    String loginSchemaJson = null;
    {
        try {
            loginSchemaJson = IOUtils.toString(loginSchemaStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción
        }
    }

    private Schema loginRequestSchema = SchemaLoader.load(new JSONObject(loginSchemaJson));


    // Cargar el esquema JSON para /logout
    InputStream logoutSchemaStream = getClass().getResourceAsStream("/estructura_json/login.json");
    String logoutSchemaJson = null;
    {
        try {
            logoutSchemaJson = IOUtils.toString(logoutSchemaStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción
        }
    }

    private Schema logoutRequestSchema = SchemaLoader.load(new JSONObject(logoutSchemaJson));

    @PostMapping("/ingresoUsuario")
    public ResponseEntity<?> ingresarUsuario(@RequestBody Usuarios usuario){
        try {
            JSONObject ingresoJson = new JSONObject();
            ingresoJson.put("username", usuario.getUserName());
            ingresoJson.put("password", usuario.getPassword1());

            JSONObject personJson = new JSONObject();
            personJson.put("nombre", usuario.getPersonaByIdPersona2().getNombres());
            personJson.put("apellido", usuario.getPersonaByIdPersona2().getApellidos());
            personJson.put("identificacion", usuario.getPersonaByIdPersona2().getIdentificacion());

            ingresoJson.put("person", personJson);

            ingresoRequestSchema.validate(ingresoJson);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("La solicitud para /ingresoUsuario no cumple con el esquema JSON: " + e.getMessage());
        }


        usuariosServices.createUsuario(usuario);
        return ResponseEntity.ok(usuario);

    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Usuarios usuario){
        try {
            ingresoRequestSchema.validate(new JSONObject("{\"username\":\"" + usuario.getUserName() + "\" ,\"password\":" + usuario.getPassword1() + "}"));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("La solicitud para /ingresoUsuario no cumple con el esquema JSON: " + e.getMessage());
        }

        usuariosServices.loginUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody Usuarios usuario){
        try {
            ingresoRequestSchema.validate(new JSONObject("{\"username\":\"" + usuario.getUserName() + "\" ,\"password\":" + usuario.getPassword1() + "}"));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("La solicitud para /ingresoUsuario no cumple con el esquema JSON: " + e.getMessage());
        }

        usuariosServices.logoutUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

}
