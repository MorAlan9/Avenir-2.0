package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Guardar un nuevo usuario en la base de datos (UH-3)
    public Usuario guardar(Usuario usuario) {
        // Al crear un usuario nuevo, nos aseguramos que esté activo por defecto
        usuario.setActivo(true);
        return repository.save(usuario);
    }

    // Listar todos los usuarios
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
}