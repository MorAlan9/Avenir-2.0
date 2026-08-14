package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.IPERFormulario;
import com.avenir.Avenir20.repository.IPERFormularioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IPERFormularioService {

    private final IPERFormularioRepository repository;

    public IPERFormularioService(IPERFormularioRepository repository) {
        this.repository = repository;
    }

    public List<IPERFormulario> listarTodos() {
        return repository.findAll();
    }

    public List<IPERFormulario> listarActivos() {
        return repository.findByEstadoNot("INACTIVO");
    }

    public Optional<IPERFormulario> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public List<IPERFormulario> listarPorEmpresa(String empresa) {
        return repository.findByEmpresa(empresa);
    }

    public IPERFormulario guardar(IPERFormulario iper) {
        if (iper.getFecha() == null) {
            iper.setFecha(LocalDate.now());
        }
        if (iper.getEstado() == null || iper.getEstado().isEmpty()) {
            iper.setEstado("PENDIENTE");
        }
        return repository.save(iper);
    }

    public IPERFormulario actualizar(Integer id, IPERFormulario datos) {
        return repository.findById(id).map(existing -> {
            existing.setIdresponsable(datos.getIdresponsable());
            existing.setFecha(datos.getFecha());
            existing.setTurno(datos.getTurno());
            existing.setEmpresa(datos.getEmpresa());
            existing.setEstado(datos.getEstado());
            existing.setTipoRiesgo(datos.getTipoRiesgo());
            existing.setDescripcionRiesgo(datos.getDescripcionRiesgo());
            existing.setCausaRiesgo(datos.getCausaRiesgo());
            existing.setSector(datos.getSector());
            existing.setCategoriaRiesgo(datos.getCategoriaRiesgo());
            existing.setNivelRiesgo(datos.getNivelRiesgo());
            existing.setExistenMedidas(datos.getExistenMedidas());
            existing.setDescripcionMedidas(datos.getDescripcionMedidas());
            existing.setImpactoPotencialRiesgo(datos.getImpactoPotencialRiesgo());
            existing.setProbabilidadOcurrencia(datos.getProbabilidadOcurrencia());
            existing.setPrioridadRiesgo(datos.getPrioridadRiesgo());
            existing.setAccionesSugeridas(datos.getAccionesSugeridas());
            existing.setResponsableDeAcciones(datos.getResponsableDeAcciones());
            existing.setFechaAlternativaImplementacion(datos.getFechaAlternativaImplementacion());
            existing.setRiesgoEliminado(datos.getRiesgoEliminado());
            existing.setImpactoResidual(datos.getImpactoResidual());
            existing.setComentario(datos.getComentario());
            existing.setFechaCierre(datos.getFechaCierre());

            if (datos.getNombreArchivo() != null) {
                existing.setNombreArchivo(datos.getNombreArchivo());
                existing.setContenidoArchivo(datos.getContenidoArchivo());
            }

            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Formulario IPER no encontrado con ID: " + id));
    }

    public void darDeBaja(Integer id) {
        repository.findById(id).ifPresent(iper -> {
            iper.setEstado("INACTIVO");
            repository.save(iper);
        });
    }
}