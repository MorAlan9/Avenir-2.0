package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Estado;
import com.avenir.Avenir20.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estados")
@CrossOrigin(origins = "*")
public class EstadoController {

    @Autowired
    private EstadoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('VER_ESTADOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public List<Estado> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_ESTADOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
        Optional<Estado> estado = service.buscarPorId(id);
        return estado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_ESTADOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody Estado estado) {
        try {
            if (estado.getId() == null) estado.setActivo(true);
            Estado nuevoEstado = service.guardar(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_ESTADOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Estado detalles) {
        Optional<Estado> estadoOpt = service.buscarPorId(id);
        if (estadoOpt.isPresent()) {
            Estado estado = estadoOpt.get();
            estado.setNombre(detalles.getNombre());
            estado.setActivo(detalles.isActivo());
            try {
                return ResponseEntity.ok(service.guardar(estado));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/baja")
    @PreAuthorize("hasAuthority('DAR_DE_BAJA_ESTADOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Estado> darDeBaja(@PathVariable Long id) {
        Estado estado = service.darDeBaja(id);
        if (estado != null) return ResponseEntity.ok(estado);
        return ResponseEntity.notFound().build();
    }
}
