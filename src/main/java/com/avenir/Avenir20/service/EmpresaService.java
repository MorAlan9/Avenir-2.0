package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.Empresa;
import com.avenir.Avenir20.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    // US: Registrar empresa (y modificarla)
    public Empresa guardar(Empresa empresa) {
        if (empresa.getIdEmpresa() == null) {
            empresa.setActivo(true); // Si es nueva, arranca activa por defecto
        }
        return repository.save(empresa);
    }

    // US: Leer datos de empresas (todas)
    public List<Empresa> listarTodas() {
        return repository.findAll();
    }

    // US: Leer datos de empresas (por ID)
    public Optional<Empresa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // US: Dar de baja empresas (Borrado lógico)
    public Empresa darDeBaja(Long id) {
        Optional<Empresa> empresaOpt = repository.findById(id);
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            empresa.setActivo(false); // Cambiamos el estado, no la borramos
            return repository.save(empresa);
        }
        return null;
    }
}