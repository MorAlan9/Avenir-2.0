package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.TipoPersona;
import com.avenir.Avenir20.repository.TipoPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPersonaService {

    @Autowired
    private TipoPersonaRepository repository;

    // Guardar un nuevo rol en la base de datos (UH-1)
    public TipoPersona guardar(TipoPersona tipoPersona) {
        return repository.save(tipoPersona);
    }

    // Listar todos los roles
    public List<TipoPersona> listarTodos() {
        return repository.findAll();
    }
}