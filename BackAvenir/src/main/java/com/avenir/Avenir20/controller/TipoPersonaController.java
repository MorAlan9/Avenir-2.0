package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.dto.TipoPersonaDTO;
import com.avenir.Avenir20.model.Permiso;
import com.avenir.Avenir20.model.TipoPersona;
import com.avenir.Avenir20.repository.PermisoRepository;
import com.avenir.Avenir20.service.TipoPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class TipoPersonaController {

    @Autowired
    private TipoPersonaService service;

    @Autowired
    private PermisoRepository permisoRepository;

    @GetMapping("/permisos")
    @PreAuthorize("hasAuthority('VER_ROLES') or hasAuthority('VER_USUARIOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public List<Permiso> listarPermisosDisponibles() {
        return permisoRepository.findAll();
    }

    @PostMapping("/permisos")
    @PreAuthorize("hasAuthority('CREAR_ROLES') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Permiso> crearPermiso(@RequestBody Permiso nuevoPermiso) {
        String nombreFormateado = nuevoPermiso.getNombre().trim().toUpperCase().replace(" ", "_");
        nuevoPermiso.setNombre(nombreFormateado);
        nuevoPermiso.setActivo(true);

        Permiso guardado = permisoRepository.save(nuevoPermiso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_ROLES') or hasAuthority('ROLE_ADMINISTRADOR')")
    public List<TipoPersona> listar() {
        return service.listarTodos();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_ROLES') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<TipoPersona> crear(@RequestBody TipoPersonaDTO dto) {
        TipoPersona nuevoRol = service.guardarConPermisos(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_ROLES') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TipoPersonaDTO dto) {
        try {
            TipoPersona actualizado = service.actualizarConPermisos(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DAR_DE_BAJA_ROLES') or hasAuthority('ELIMINAR_ROLES') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok().body("{\"mensaje\": \"Rol eliminado exitosamente.\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se puede eliminar este rol porque está en uso por un usuario.");
        }
    }
}