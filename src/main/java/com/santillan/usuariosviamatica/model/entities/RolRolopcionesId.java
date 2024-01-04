package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.io.Serializable;

public class RolRolopcionesId implements Serializable {
    private Integer idRol;
    private Integer idOpcion;

    @Basic
    @Column(name = "idRol", nullable = true)
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Basic
    @Column(name = "idOpcion", nullable = true)
    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

}
