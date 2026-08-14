package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.CategoriaRiesgo;
import com.avenir.Avenir20.service.CategoriaRiesgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias-riesgo")
@CrossOrigin(origins = "*")
public class CategoriaRiesgoController {
    @Autowired
    private CategoriaRiesgoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('VER_CATEGORIAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public List<CategoriaRiesgo> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_CATEGORIAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<CategoriaRiesgo> buscarPorId(@PathVariable Long id) {
        Optional<CategoriaRiesgo> categoria = service.buscarPorId(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_CATEGORIAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody CategoriaRiesgo categoria) {
        try {
            if (!categoria.isActivo()) categoria.setActivo(true);
            CategoriaRiesgo nuevaCategoria = service.guardar(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_CATEGORIAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody CategoriaRiesgo detalles) {
        Optional<CategoriaRiesgo> categoriaOpt = service.buscarPorId(id);
        if (categoriaOpt.isPresent()) {
            CategoriaRiesgo categoria = categoriaOpt.get();
            categoria.setNombre(detalles.getNombre());
            categoria.setActivo(detalles.isActivo());
            try {
                return ResponseEntity.ok(service.guardar(categoria));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/baja")
    @PreAuthorize("hasAuthority('DAR_DE_BAJA_CATEGORIAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<CategoriaRiesgo> darDeBaja(@PathVariable Long id) {
        CategoriaRiesgo categoria = service.darDeBaja(id);
        if (categoria != null) return ResponseEntity.ok(categoria);
        return ResponseEntity.notFound().build();
    }
}
