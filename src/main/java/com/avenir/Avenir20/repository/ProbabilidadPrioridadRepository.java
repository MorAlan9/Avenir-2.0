package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.ProbabilidadPrioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProbabilidadPrioridadRepository extends JpaRepository<ProbabilidadPrioridad, Integer> {
    List<ProbabilidadPrioridad> findByActivoTrue();
}