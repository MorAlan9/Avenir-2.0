package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.IperFormulario;
import com.avenir.Avenir20.service.IperFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iper-formularios")
@CrossOrigin(origins = "*")
public class IperFormularioController {

    @Autowired
    private IperFormularioService service;

    @GetMapping
    @PreAuthorize("hasAuthority('VER_IPER_FORMULARIOS')")
    public List<IperFormulario> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_IPER_FORMULARIOS')")
    public ResponseEntity<IperFormulario> buscarPorId(@PathVariable Long id) {
        Optional<IperFormulario> formulario = service.buscarPorId(id);
        return formulario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_IPER_FORMULARIOS')")
    public ResponseEntity<?> crear(@RequestBody IperFormulario formulario) {
        try {
            IperFormulario nuevoFormulario = service.guardar(formulario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoFormulario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
