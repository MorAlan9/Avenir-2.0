package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.Severidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeveridadRepository extends JpaRepository<Severidad, Long> {
    Optional<Severidad> findByNombre(String nombre);
}
