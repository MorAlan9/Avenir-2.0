package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.TipoPersona;
import com.avenir.Avenir20.service.TipoPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles") // Le ponemos una URL amigable para el front
@CrossOrigin(origins = "http://localhost:5173")
public class TipoPersonaController {

    @Autowired
    private TipoPersonaService service;

    // GET: Traer todos los roles
    @GetMapping
    public List<TipoPersona> listar() {
        return service.listarTodos();
    }

    // POST: Crear un nuevo rol
    @PostMapping
    public ResponseEntity<TipoPersona> crear(@RequestBody TipoPersona tipoPersona) {
        TipoPersona nuevoRol = service.guardar(tipoPersona);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }

    // PUT: Editar un rol existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TipoPersona tipoPersona) {
        try {
            TipoPersona actualizado = service.actualizar(id, tipoPersona);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE: Borrar un rol
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok().body("{\"mensaje\": \"Rol eliminado exitosamente.\"}");
        } catch (Exception e) {
            // Atrapamos cualquier error (ej. si intentan borrar un rol que ya tiene usuarios asignados)
            return ResponseEntity.badRequest().body("No se puede eliminar este rol porque está en uso.");
        }
    }
}