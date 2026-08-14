package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.CategoriaRiesgo;
import com.avenir.Avenir20.repository.CategoriaRiesgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaRiesgoService {
    @Autowired
    private CategoriaRiesgoRepository repository;

    // US: Registrar categoría de riesgo (y modificarla)
    public CategoriaRiesgo guardar(CategoriaRiesgo categoria) {
        // Validación: Buscar si el nombre ya existe en la base de datos
        Optional<CategoriaRiesgo> existente = repository.findByNombre(categoria.getNombre());

        // Si el nombre existe Y NO es la misma categoría que estamos intentando editar
        if (existente.isPresent() && existente.get().getId() != categoria.getId()) {
            throw new IllegalArgumentException("Ya existe una categoría de riesgo con este nombre.");
        }

        if (categoria.getId() == 0) {
            categoria.setActivo(true); // Si es nueva, arranca activa por defecto
        }
        return repository.save(categoria);
    }

    // US: Leer todas las categorías
    public List<CategoriaRiesgo> listarTodas() {
        return repository.findAll();
    }

    // US: Buscar categoría por ID
    public Optional<CategoriaRiesgo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // US: Dar de baja categoría (borrado lógico)
    public CategoriaRiesgo darDeBaja(Long id) {
        Optional<CategoriaRiesgo> categoriaOpt = repository.findById(id);
        if (categoriaOpt.isPresent()) {
            CategoriaRiesgo categoria = categoriaOpt.get();
            categoria.setActivo(false);
            return repository.save(categoria);
        }
        return null;
    }
}

