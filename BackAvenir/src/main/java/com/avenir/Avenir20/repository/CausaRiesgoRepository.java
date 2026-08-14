package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.CausaRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CausaRiesgoRepository extends JpaRepository<CausaRiesgo, Long> {
    Optional<CausaRiesgo> findByNombre(String nombre);
}
