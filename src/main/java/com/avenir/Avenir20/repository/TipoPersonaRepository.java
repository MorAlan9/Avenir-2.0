package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.TipoPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPersonaRepository extends JpaRepository<TipoPersona, Long> {
}