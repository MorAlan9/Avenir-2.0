package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.TipoPersona;
import com.avenir.Avenir20.service.TipoPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class TipoPersonaController {

    @Autowired
    private TipoPersonaService service;

    // GET: Trae la lista de roles
    @GetMapping
    public List<TipoPersona> listar() {
        return service.listarTodos();
    }

    // POST: Crea un rol nuevo (UH-1)
    @PostMapping
    public TipoPersona crear(@RequestBody TipoPersona tipoPersona) {
        return service.guardar(tipoPersona);
    }
}