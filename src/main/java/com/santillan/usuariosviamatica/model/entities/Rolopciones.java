package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Rolopciones {
    private String nombreOpcion;
    private Integer idOpcion;
    private Collection<RolRolopciones> rolRolopcionesByIdOpcion;

    @Basic
    @Column(name = "nombreOpcion", nullable = true, length = 50)
    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idOpcion", nullable = false)
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
        Rolopciones that = (Rolopciones) o;
        return Objects.equals(nombreOpcion, that.nombreOpcion) && Objects.equals(idOpcion, that.idOpcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreOpcion, idOpcion);
    }

    @OneToMany(mappedBy = "rolopcionesByIdOpcion")
    public Collection<RolRolopciones> getRolRolopcionesByIdOpcion() {
        return rolRolopcionesByIdOpcion;
    }

    public void setRolRolopcionesByIdOpcion(Collection<RolRolopciones> rolRolopcionesByIdOpcion) {
        this.rolRolopcionesByIdOpcion = rolRolopcionesByIdOpcion;
    }
}
