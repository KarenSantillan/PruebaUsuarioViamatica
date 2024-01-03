package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rol_rolopciones", schema = "usuariosv", catalog = "")
public class RolRolopciones {
    private Integer idRol;
    private Integer idOpcion;
    private Rol rolByIdRol;
    private Rolopciones rolopcionesByIdOpcion;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolRolopciones that = (RolRolopciones) o;
        return Objects.equals(idRol, that.idRol) && Objects.equals(idOpcion, that.idOpcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idOpcion);
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
