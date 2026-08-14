package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.IperFormulario;
import com.avenir.Avenir20.repository.IperFormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IperFormularioService {

    @Autowired
    private IperFormularioRepository repository;

    // US: Guardar un formulario enviado
    public IperFormulario guardar(IperFormulario formulario) {
        // No hay validación de unicidad, simplemente se guarda el envío
        return repository.save(formulario);
    }

    // US: Listar todos los formularios enviados (para revisión futura)
    public List<IperFormulario> listarTodos() {
        return repository.findAll();
    }

    // US: Buscar un formulario por ID
    public Optional<IperFormulario> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
