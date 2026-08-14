package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.CategoriaRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRiesgoRepository extends JpaRepository<CategoriaRiesgo, Long> {
    Optional<CategoriaRiesgo> findByNombre(String nombre);
}
