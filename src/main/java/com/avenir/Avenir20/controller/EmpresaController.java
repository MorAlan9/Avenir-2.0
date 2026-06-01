package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Empresa;
import com.avenir.Avenir20.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // POST: Registrar una nueva empresa
    @PostMapping
    public Empresa crear(@RequestBody Empresa empresa) {
        return service.guardar(empresa);
    }

    // PUT: Modificar datos de una empresa
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizar(@PathVariable Long id, @RequestBody Empresa empresaDetalles) {
        Optional<Empresa> empresaExistente = service.buscarPorId(id);
        if (empresaExistente.isPresent()) {
            Empresa empresa = empresaExistente.get();
            empresa.setNombre(empresaDetalles.getNombre());
            empresa.setCuit(empresaDetalles.getCuit());
            empresa.setDireccion(empresaDetalles.getDireccion());
            // No tocamos el campo "activo" acá para no revivirla por error
            return ResponseEntity.ok(service.guardar(empresa));
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