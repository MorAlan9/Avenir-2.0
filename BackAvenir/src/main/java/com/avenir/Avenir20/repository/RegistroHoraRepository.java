package com.avenir.Avenir20.repository;

import com.avenir.Avenir20.model.RegistroHora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroHoraRepository extends JpaRepository<RegistroHora, Long> {
    // Para ver qué usuarios registraron horas en un día específico (El calendario)
    List<RegistroHora> findByFecha(LocalDate fecha);
}