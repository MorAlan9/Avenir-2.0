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
@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> crear(@RequestBody Empresa empresa) {
        try {
            // Aseguramos que si no envían estado, nazca activa por defecto
            if (empresa.getActivo() == null) {
                empresa.setActivo(true);
            }
            Empresa nuevaEmpresa = service.guardar(empresa);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEmpresa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT: Modificar datos de una empresa (incluyendo su estado)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Empresa empresaDetalles) {
        Optional<Empresa> empresaOpt = service.buscarPorId(id);
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            empresa.setNombre(empresaDetalles.getNombre());
            empresa.setCuit(empresaDetalles.getCuit());
            empresa.setDireccion(empresaDetalles.getDireccion());

            // 🔹 Código limpio: Si viene el campo 'activo', lo actualizamos
            if (empresaDetalles.getActivo() != null) {
                empresa.setActivo(empresaDetalles.getActivo());
            }

            try {
                return ResponseEntity.ok(service.guardar(empresa));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH: Dar de baja una empresa
    @PatchMapping("/{id}/baja")
    public ResponseEntity<Empresa> darDeBaja(@PathVariable Long id) {
        Empresa empresa = service.darDeBaja(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH: Dar de alta una empresa (NUEVO ENDPOINT)
    @PatchMapping("/{id}/alta")
    public ResponseEntity<Empresa> darDeAlta(@PathVariable Long id) {
        Optional<Empresa> empresaOpt = service.buscarPorId(id);
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            empresa.setActivo(true);
            return ResponseEntity.ok(service.guardar(empresa));
        }
        return ResponseEntity.notFound().build();
    }
}