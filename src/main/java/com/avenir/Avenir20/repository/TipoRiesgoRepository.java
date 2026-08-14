package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.TipoRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipoRiesgoRepository extends JpaRepository<TipoRiesgo, Integer> {
    List<TipoRiesgo> findByActivoTrue();
}