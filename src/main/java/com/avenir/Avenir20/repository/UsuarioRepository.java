package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Spring Boot implementa esto automáticamente por el nombre del método:
    // Buscar un usuario por su email (lo vamos a usar para el Login - UH-5)
    Optional<Usuario> findByEmail(String email);


}