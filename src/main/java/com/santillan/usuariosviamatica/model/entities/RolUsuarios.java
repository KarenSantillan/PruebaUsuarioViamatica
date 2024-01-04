package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rol_usuarios", schema = "usuariosv", catalog = "")
public class RolUsuarios {
    @EmbeddedId
    private RolUsuariosId idR;
    private Rol rolByIdRol;
    private Usuarios usuariosByIdUsuario;
    private Integer idRU;

    public RolUsuariosId getIdR() {
        return idR;
    }

    public void setId(RolUsuariosId id) {
        this.idR = id;
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

    public void setIdRU(Integer idRU) {
        this.idRU = idRU;
    }

    @Id
    public Integer getIdRU() {
        return idRU;
    }
}
