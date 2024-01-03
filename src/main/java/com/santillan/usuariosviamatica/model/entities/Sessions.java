package com.santillan.usuariosviamatica.model.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Sessions {
    private Date fechaIngreso;
    private Date fechaCierre;
    private Integer idUsuario;
    private Usuarios usuariosByIdUsuario;
    private Integer idSessions;

    @Basic
    @Column(name = "fechaIngreso", nullable = true)
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Basic
    @Column(name = "fechaCierre", nullable = true)
    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
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
        Sessions sessions = (Sessions) o;
        return Objects.equals(fechaIngreso, sessions.fechaIngreso) && Objects.equals(fechaCierre, sessions.fechaCierre) && Objects.equals(idUsuario, sessions.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaIngreso, fechaCierre, idUsuario);
    }

    @OneToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    public Usuarios getUsuariosByIdUsuario() {
        return usuariosByIdUsuario;
    }

    public void setUsuariosByIdUsuario(Usuarios usuariosByIdUsuario) {
        this.usuariosByIdUsuario = usuariosByIdUsuario;
    }

    public void setIdSessions(Integer idSessions) {
        this.idSessions = idSessions;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Integer getIdSessions() {
        return idSessions;
    }
}
