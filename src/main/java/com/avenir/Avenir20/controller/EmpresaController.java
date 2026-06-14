package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Empresa;
import com.avenir.Avenir20.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    // GET: Listar todas las empresas
    @GetMapping
    public List<Empresa> listar() {
        return service.listarTodas();
    }

    // GET: Buscar una empresa específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = service.buscarPorId(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Registrar una nueva empresa con validación
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Empresa empresa) {
        try {
            Empresa nuevaEmpresa = service.guardar(empresa);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEmpresa);
        } catch (IllegalArgumentException e) {
            // Ataja el error si el CUIT está repetido
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT: Modificar datos de una empresa
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Empresa empresaDetalles) {
        Optional<Empresa> empresaOpt = service.buscarPorId(id);
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            empresa.setNombre(empresaDetalles.getNombre());
            empresa.setCuit(empresaDetalles.getCuit());
            empresa.setDireccion(empresaDetalles.getDireccion());

            try {
                // Intentamos guardar los cambios
                return ResponseEntity.ok(service.guardar(empresa));
            } catch (IllegalArgumentException e) {
                // Si al editar le pusimos un CUIT que ya tiene otra empresa, atajamos el error
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH: Dar de baja una empresa (Borrado lógico)
    @PatchMapping("/{id}/baja")
    public ResponseEntity<Empresa> darDeBaja(@PathVariable Long id) {
        Empresa empresa = service.darDeBaja(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.notFound().build();
    }
}