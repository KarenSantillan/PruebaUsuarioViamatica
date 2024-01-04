package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rol_rolopciones", schema = "usuariosv", catalog = "")
public class RolRolopciones {

    @EmbeddedId
    private RolRolopcionesId idRolRol;

    private Rol rolByIdRol;
    private Rolopciones rolopcionesByIdOpcion;

    public RolRolopcionesId getIdRolRol() {
        return idRolRol;
    }

    public void setIdRolRol(RolRolopcionesId idRolRol) {
        this.idRolRol = idRolRol;
    }

    @ManyToOne
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    public Rol getRolByIdRol() {
        return rolByIdRol;
    }

    public void setRolByIdRol(Rol rolByIdRol) {
        this.rolByIdRol = rolByIdRol;
    }

    @ManyToOne
    @JoinColumn(name = "idOpcion", referencedColumnName = "idOpcion")
    public Rolopciones getRolopcionesByIdOpcion() {
        return rolopcionesByIdOpcion;
    }

    public void setRolopcionesByIdOpcion(Rolopciones rolopcionesByIdOpcion) {
        this.rolopcionesByIdOpcion = rolopcionesByIdOpcion;
    }
}
