package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.TipoRiesgo;
import com.avenir.Avenir20.repository.TipoRiesgoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoRiesgoService {
    private TipoRiesgoRepository repository;

    // US: Registrar tipo de riesgo (y modificarlo)
    public TipoRiesgo guardar(TipoRiesgo tipo) {
        // Validación: Buscar si el nombre ya existe en la base de datos
        Optional<TipoRiesgo> existente = repository.findByNombre(tipo.getNombre());

        // Si el nombre existe Y NO es el mismo registro que estamos intentando editar
        if (existente.isPresent() && !existente.get().getId().equals(tipo.getId())) {
            throw new IllegalArgumentException("Ya existe un tipo de riesgo con este nombre.");
        }

        if (tipo.getId() == null) {
            tipo.setActivo(true); // Si es nuevo, arranca activo por defecto
        }
        return repository.save(tipo);
    }

    // US: Leer todos los tipos de riesgo
    public List<TipoRiesgo> listarTodos() {
        return repository.findAll();
    }

    // US: Buscar tipo de riesgo por ID
    public Optional<TipoRiesgo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // US: Dar de baja tipo de riesgo (borrado lógico)
    public TipoRiesgo darDeBaja(Long id) {
        Optional<TipoRiesgo> tipoOpt = repository.findById(id);
        if (tipoOpt.isPresent()) {
            TipoRiesgo tipo = tipoOpt.get();
            tipo.setActivo(false);
            return repository.save(tipo);
        }
        return null;
    }
}
