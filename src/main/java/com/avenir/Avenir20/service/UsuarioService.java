package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.Login;
import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.repository.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // --- TU MÉTODO PARA EL REGISTRO (VALIDACIONES) ---
    public Usuario guardar(Usuario usuario) {
        // Validar formato del Email
        if (usuario.getEmail() == null || !usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El formato del email no es válido.");
        }

        // Validar que el Email NO exista en la Base de Datos
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ese email ya está registrado en el sistema.");
        }

        // Guardar el usuario activo por defecto
        usuario.setActivo(true);
        return repository.save(usuario);
    }

    // --- EL MÉTODO DE MATÍAS PARA EL LOGIN (ARGON2) ---
    public Usuario obtenerUsuarioPorCredenciales(Login login) {
        Optional<Usuario> usuarioOpt = repository.findByEmail(login.getEmail());
        if (usuarioOpt.isEmpty()) {
            return null; // El email no existe
        } else {
            String contrasenaHashed = usuarioOpt.get().getContrasena();
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            // Compara la contraseña que puso el usuario con la encriptada de la BD
            if (argon2.verify(contrasenaHashed, login.getContrasena())) {
                return usuarioOpt.get(); // Todo OK
            }
            return null; // Contraseña incorrecta
        }
    }

    // Listar todos los usuarios
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
}