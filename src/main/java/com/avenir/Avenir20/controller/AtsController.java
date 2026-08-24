package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.dto.AtsDTO;
import com.avenir.Avenir20.model.Ats;
import com.avenir.Avenir20.service.AtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ats")
@CrossOrigin(origins = "*")
public class AtsController {

    @Autowired
    private AtsService atsService;

    @PostMapping
    public ResponseEntity<?> crearAts(@RequestBody AtsDTO dto) {
        try {
            Ats nuevoAts = atsService.guardarAts(dto);
            return ResponseEntity.ok(nuevoAts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el ATS: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Ats>> listarAts() {
        return ResponseEntity.ok(atsService.obtenerTodos());
    }
}