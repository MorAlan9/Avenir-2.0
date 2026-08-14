package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.Estado;
import com.avenir.Avenir20.repository.EstadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> listarTodos() {
        return repository.findAll();
    }

    public List<Estado> listarActivos() {
        return repository.findByActivoTrue();
    }

    public Optional<Estado> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public Estado guardar(Estado estado) {
        estado.setActivo(true);
        return repository.save(estado);
    }

    public Estado actualizar(Integer id, Estado datos) {
        return repository.findById(id).map(est -> {
            est.setNombre(datos.getNombre());
            est.setActivo(datos.getActivo());
            return repository.save(est);
        }).orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + id));
    }

    public void darDeBaja(Integer id) {
        repository.findById(id).ifPresent(est -> {
            est.setActivo(false);
            repository.save(est);
        });
    }
}