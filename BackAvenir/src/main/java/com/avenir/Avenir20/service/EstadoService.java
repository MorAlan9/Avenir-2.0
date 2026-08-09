package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.Estado;
import com.avenir.Avenir20.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository repository;

    // US: Registrar estado (y modificarlo)
    public Estado guardar(Estado estado) {
        // Validación: Buscar si el nombre ya existe en la base de datos
        Optional<Estado> existente = repository.findByNombre(estado.getNombre());

        // Si el nombre existe Y NO es el mismo estado que estamos intentando editar
        if (existente.isPresent() && !existente.get().getId().equals(estado.getId())) {
            throw new IllegalArgumentException("Ya existe un estado con este nombre.");
        }

        if (estado.getId() == null) {
            estado.setActivo(true); // Si es nuevo, arranca activo por defecto
        }
        return repository.save(estado);
    }

    // US: Leer todos los estados
    public List<Estado> listarTodos() {
        return repository.findAll();
    }

    // US: Buscar estado por ID
    public Optional<Estado> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // US: Dar de baja estado (borrado lógico)
    public Estado darDeBaja(Long id) {
        Optional<Estado> estadoOpt = repository.findById(id);
        if (estadoOpt.isPresent()) {
            Estado estado = estadoOpt.get();
            estado.setActivo(false);
            return repository.save(estado);
        }
        return null;
    }
}
