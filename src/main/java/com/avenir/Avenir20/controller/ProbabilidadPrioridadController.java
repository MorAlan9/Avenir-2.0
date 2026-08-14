package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.ProbabilidadPrioridad;
import com.avenir.Avenir20.service.ProbabilidadPrioridadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/probabilidad-prioridad")
@CrossOrigin(origins = "*")
public class ProbabilidadPrioridadController {

    private final ProbabilidadPrioridadService service;

    public ProbabilidadPrioridadController(ProbabilidadPrioridadService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProbabilidadPrioridad>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ProbabilidadPrioridad>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProbabilidadPrioridad> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProbabilidadPrioridad> crear(@RequestBody ProbabilidadPrioridad item) {
        return ResponseEntity.ok(service.guardar(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProbabilidadPrioridad> actualizar(@PathVariable Integer id, @RequestBody ProbabilidadPrioridad item) {
        return ResponseEntity.ok(service.actualizar(id, item));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darDeBaja(id);
        return ResponseEntity.noContent().build();
    }
}