package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.Ats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtsRepository extends JpaRepository<Ats, Long> {
    List<Ats> findByEmpresaId(Long empresaId);
}