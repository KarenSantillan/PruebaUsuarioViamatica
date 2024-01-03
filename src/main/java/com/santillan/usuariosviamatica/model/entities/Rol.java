package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Rol {
    private Integer idRol;
    private String rolName;
    private Collection<RolRolopciones> rolRolopcionesByIdRol;
    private Collection<RolUsuarios> rolUsuariosByIdRol;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRol", nullable = false)
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Basic
    @Column(name = "rolName", nullable = true, length = 50)
    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return Objects.equals(idRol, rol.idRol) && Objects.equals(rolName, rol.rolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, rolName);
    }

    @OneToMany(mappedBy = "rolByIdRol")
    public Collection<RolRolopciones> getRolRolopcionesByIdRol() {
        return rolRolopcionesByIdRol;
    }

    public void setRolRolopcionesByIdRol(Collection<RolRolopciones> rolRolopcionesByIdRol) {
        this.rolRolopcionesByIdRol = rolRolopcionesByIdRol;
    }

    @OneToMany(mappedBy = "rolByIdRol")
    public Collection<RolUsuarios> getRolUsuariosByIdRol() {
        return rolUsuariosByIdRol;
    }

    public void setRolUsuariosByIdRol(Collection<RolUsuarios> rolUsuariosByIdRol) {
        this.rolUsuariosByIdRol = rolUsuariosByIdRol;
    }
}
