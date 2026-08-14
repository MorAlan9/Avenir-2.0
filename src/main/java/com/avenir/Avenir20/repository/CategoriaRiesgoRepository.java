package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.CategoriaRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRiesgoRepository extends JpaRepository<CategoriaRiesgo, Integer> {
    List<CategoriaRiesgo> findByActivoTrue();
}