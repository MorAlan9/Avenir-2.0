package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.CausaRiesgo;
import com.avenir.Avenir20.repository.CausaRiesgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CausaRiesgoService {
    @Autowired
    private CausaRiesgoRepository repository;

    // US: Registrar causa de riesgo (y modificarla)
    public CausaRiesgo guardar(CausaRiesgo causa) {
        // Validación: Buscar si el nombre ya existe en la base de datos
        Optional<CausaRiesgo> existente = repository.findByNombre(causa.getNombre());

        // Si el nombre existe Y NO es la misma causa que estamos intentando editar
        if (existente.isPresent() && !existente.get().getId().equals(causa.getId())) {
            throw new IllegalArgumentException("Ya existe una causa de riesgo con este nombre.");
        }

        if (causa.getId() == null) {
            causa.setActivo(true); // Si es nueva, arranca activa por defecto
        }
        return repository.save(causa);
    }

    // US: Leer todas las causas
    public List<CausaRiesgo> listarTodas() {
        return repository.findAll();
    }

    // US: Buscar causa por ID
    public Optional<CausaRiesgo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // US: Dar de baja causa (borrado lógico)
    public CausaRiesgo darDeBaja(Long id) {
        Optional<CausaRiesgo> causaOpt = repository.findById(id);
        if (causaOpt.isPresent()) {
            CausaRiesgo causa = causaOpt.get();
            causa.setActivo(false);
            return repository.save(causa);
        }
        return null;
    }
}
