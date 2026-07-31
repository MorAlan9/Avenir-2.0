package com.avenir.Avenir20.service;

import com.avenir.Avenir20.dto.TipoPersonaDTO;
import com.avenir.Avenir20.model.Permiso;
import com.avenir.Avenir20.model.TipoPersona;
import com.avenir.Avenir20.repository.PermisoRepository;
import com.avenir.Avenir20.repository.TipoPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class TipoPersonaService {

    @Autowired
    private TipoPersonaRepository repository;

    @Autowired
    private PermisoRepository permisoRepository;

    public List<TipoPersona> listarTodos() {
        return repository.findAll();
    }

    public TipoPersona guardarConPermisos(TipoPersonaDTO dto) {
        TipoPersona nuevoRol = new TipoPersona();
        nuevoRol.setNombre(dto.getNombre());

        if (dto.getPermisosIds() != null && !dto.getPermisosIds().isEmpty()) {
            List<Permiso> permisosEncontrados = permisoRepository.findAllById(dto.getPermisosIds());
            nuevoRol.setPermisos(new HashSet<>(permisosEncontrados));
        } else {
            nuevoRol.setPermisos(new HashSet<>());
        }

        return repository.save(nuevoRol);
    }

    public TipoPersona actualizarConPermisos(Long id, TipoPersonaDTO dto) {
        TipoPersona existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El rol no existe en la base de datos."));

        existente.setNombre(dto.getNombre());

        if (dto.getPermisosIds() != null) {
            List<Permiso> permisosEncontrados = permisoRepository.findAllById(dto.getPermisosIds());
            existente.setPermisos(new HashSet<>(permisosEncontrados));
        } else {
            existente.setPermisos(new HashSet<>());
        }

        return repository.save(existente);
    }

    // 👇 MÉTODO ELIMINAR BLINDADO
    public void eliminar(Long id) {
        TipoPersona rol = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El rol no existe."));

        // 1. Vaciamos la lista de permisos para que Hibernate borre la relación de la tabla intermedia
        rol.getPermisos().clear();
        repository.save(rol);

        // 2. Ahora sí, borramos el rol tranquilamente
        repository.delete(rol);
    }
}