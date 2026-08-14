package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.CategoriaRiesgo;
import com.avenir.Avenir20.service.CategoriaRiesgoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categoria-riesgo")
@CrossOrigin(origins = "*")
public class CategoriaRiesgoController {

    private final CategoriaRiesgoService service;

    public CategoriaRiesgoController(CategoriaRiesgoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaRiesgo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<CategoriaRiesgo>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRiesgo> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaRiesgo> crear(@RequestBody CategoriaRiesgo categoria) {
        return ResponseEntity.ok(service.guardar(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaRiesgo> actualizar(@PathVariable Integer id, @RequestBody CategoriaRiesgo categoria) {
        return ResponseEntity.ok(service.actualizar(id, categoria));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darDeBaja(id);
        return ResponseEntity.noContent().build();
    }
}