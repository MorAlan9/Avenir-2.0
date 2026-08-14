package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.IPERFormulario;
import com.avenir.Avenir20.service.IPERFormularioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/iper")
@CrossOrigin(origins = "*")
public class IPERFormularioController {

    private final IPERFormularioService service;

    public IPERFormularioController(IPERFormularioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<IPERFormulario>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<IPERFormulario>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IPERFormulario> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empresa/{empresa}")
    public ResponseEntity<List<IPERFormulario>> listarPorEmpresa(@PathVariable String empresa) {
        return ResponseEntity.ok(service.listarPorEmpresa(empresa));
    }

    @PostMapping
    public ResponseEntity<IPERFormulario> crear(@RequestBody IPERFormulario iper) {
        return ResponseEntity.ok(service.guardar(iper));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IPERFormulario> actualizar(@PathVariable Integer id, @RequestBody IPERFormulario iper) {
        return ResponseEntity.ok(service.actualizar(id, iper));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darDeBaja(id);
        return ResponseEntity.noContent().build();
    }
}