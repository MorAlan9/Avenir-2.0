package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.TipoRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoRiesgoRepository extends JpaRepository<TipoRiesgo, Long> {
    Optional<TipoRiesgo> findByNombre(String nombre);
}
