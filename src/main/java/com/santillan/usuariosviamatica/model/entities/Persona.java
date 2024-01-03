package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Persona {
    private Integer idPersona;
    private String nombres;
    private String apellidos;
    private String identificacion;
    private Date fechaNacimiento;
    private Collection<Usuarios> usuariosByIdPersona;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPersona", nullable = false)
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    @Basic
    @Column(name = "nombres", nullable = true, length = 60)
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic
    @Column(name = "apellidos", nullable = true, length = 60)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "identificacion", nullable = true, length = 10)
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Basic
    @Column(name = "fechaNacimiento", nullable = true)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(idPersona, persona.idPersona) && Objects.equals(nombres, persona.nombres) && Objects.equals(apellidos, persona.apellidos) && Objects.equals(identificacion, persona.identificacion) && Objects.equals(fechaNacimiento, persona.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersona, nombres, apellidos, identificacion, fechaNacimiento);
    }

    @OneToMany(mappedBy = "personaByIdPersona2")
    public Collection<Usuarios> getUsuariosByIdPersona() {
        return usuariosByIdPersona;
    }

    public void setUsuariosByIdPersona(Collection<Usuarios> usuariosByIdPersona) {
        this.usuariosByIdPersona = usuariosByIdPersona;
    }
}
