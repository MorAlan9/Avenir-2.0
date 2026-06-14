package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.TipoPersona;
import com.avenir.Avenir20.repository.TipoPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPersonaService {

    @Autowired
    private TipoPersonaRepository repository;

    // Guardar un nuevo rol en la base de datos
    public TipoPersona guardar(TipoPersona tipoPersona) {
        return repository.save(tipoPersona);
    }

    // Listar todos los roles
    public List<TipoPersona> listarTodos() {
        return repository.findAll();
    }

    // NUEVO: Modificar un rol
    public TipoPersona actualizar(Long id, TipoPersona datosNuevos) {
        Optional<TipoPersona> existente = repository.findById(id);
        if (existente.isPresent()) {
            TipoPersona rol = existente.get();
            rol.setNombre(datosNuevos.getNombre());
            return repository.save(rol);
        } else {
            throw new IllegalArgumentException("Rol no encontrado en la base de datos.");
        }
    }

    // NUEVO: Eliminar un rol
    public void eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Rol no encontrado.");
        }
    }
}