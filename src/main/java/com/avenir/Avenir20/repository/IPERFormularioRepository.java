package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.IPERFormulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IPERFormularioRepository extends JpaRepository<IPERFormulario, Integer> {
    List<IPERFormulario> findByEmpresa(String empresa);
    List<IPERFormulario> findByIdresponsable(Integer idresponsable);
    List<IPERFormulario> findByEstadoNot(String estado);
}