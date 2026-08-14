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

    // --- MÉTODO PARA EL REGISTRO (VALIDACIONES) ---
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

    // --- NUEVO: Listar filtrando por Activo/Inactivo ---
    public List<Usuario> listarPorEstado(boolean activo) {
        return repository.findByActivo(activo);
    }

    // --- NUEVO: Modificar Usuario ---
    public Usuario actualizar(Long id, Usuario datosNuevos) {
        Optional<Usuario> usuarioExistente = repository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();

            // Actualizamos solo los datos permitidos (evitamos que modifiquen email y contraseña por acá)
            usuario.setNombre(datosNuevos.getNombre());
            usuario.setApellido(datosNuevos.getApellido());
            usuario.setTipoPersona(datosNuevos.getTipoPersona());
            // Si también quieren poder reactivar a alguien, actualizamos el estado:
            usuario.setActivo(datosNuevos.isActivo());

            return repository.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado en la base de datos.");
        }
    }

    // --- NUEVO: Dar de baja (Soft Delete) ---
    public void darDeBaja(Long id) {
        Optional<Usuario> usuarioExistente = repository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setActivo(false); // Lo marcamos como inactivo
            repository.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    }
}