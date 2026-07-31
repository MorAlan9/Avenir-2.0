package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar un usuario por su email
    Optional<Usuario> findByEmail(String email);

    // NUEVO: Filtro para traer solo activos o solo inactivos
    List<Usuario> findByActivo(boolean activo);
}