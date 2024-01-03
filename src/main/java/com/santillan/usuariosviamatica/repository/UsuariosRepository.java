package com.santillan.usuariosviamatica.repository;

import com.santillan.usuariosviamatica.model.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByMail(String mail);
    Optional<Usuarios> findByUsername(String username);
    String findBySessionsByIdUsuario(Integer idUsuario);
    @Query(value = "SELECT a.password1 FROM usuarios a where a.userName = ?1 ", nativeQuery = true)
    String findPassByUsername(String username);

}
