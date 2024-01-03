package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rol_usuarios", schema = "usuariosv", catalog = "")
public class RolUsuarios {
    private Integer idRol;
    private Integer idUsuario;
    private Rol rolByIdRol;
    private Usuarios usuariosByIdUsuario;

    @Basic
    @Column(name = "idRol", nullable = true)
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Basic
    @Column(name = "idUsuario", nullable = true)
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolUsuarios that = (RolUsuarios) o;
        return Objects.equals(idRol, that.idRol) && Objects.equals(idUsuario, that.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idUsuario);
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
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    public Usuarios getUsuariosByIdUsuario() {
        return usuariosByIdUsuario;
    }

    public void setUsuariosByIdUsuario(Usuarios usuariosByIdUsuario) {
        this.usuariosByIdUsuario = usuariosByIdUsuario;
    }
}
