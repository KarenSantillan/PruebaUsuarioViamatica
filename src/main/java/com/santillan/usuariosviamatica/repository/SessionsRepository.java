package com.santillan.usuariosviamatica.repository;

import com.santillan.usuariosviamatica.model.entities.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionsRepository extends JpaRepository<Sessions, Integer> {
}
