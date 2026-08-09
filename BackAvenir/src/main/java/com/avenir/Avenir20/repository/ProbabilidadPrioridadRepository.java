package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.ProbabilidadPrioridad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProbabilidadPrioridadRepository extends JpaRepository<ProbabilidadPrioridad, Long> {
    Optional<ProbabilidadPrioridad> findByNombre(String nombre);
}
