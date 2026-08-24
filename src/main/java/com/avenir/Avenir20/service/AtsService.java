package com.avenir.Avenir20.service;

import com.avenir.Avenir20.dto.AtsDTO;
import com.avenir.Avenir20.model.Ats;
import com.avenir.Avenir20.model.AtsPaso;
import com.avenir.Avenir20.repository.AtsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AtsService {

    @Autowired
    private AtsRepository atsRepository;

    @Transactional
    public Ats guardarAts(AtsDTO dto) {
        Ats ats = new Ats();
        ats.setEmpresaId(dto.getEmpresaId());
        ats.setUsuarioAuditorEmail(dto.getUsuarioAuditorEmail());
        ats.setFechaRealizacion(dto.getFechaRealizacion() != null ? dto.getFechaRealizacion() : LocalDate.now());
        ats.setUbicacionSector(dto.getUbicacionSector());
        ats.setTareaARealizar(dto.getTareaARealizar());
        ats.setTipoRiesgoId(dto.getTipoRiesgoId());
        ats.setCategoriaRiesgoId(dto.getCategoriaRiesgoId());
        ats.setCausaRiesgoId(dto.getCausaRiesgoId());
        ats.setProbabilidadId(dto.getProbabilidadId());
        ats.setEstado("PENDIENTE");

        if (dto.getPasosTarea() != null) {
            for (AtsDTO.AtsPasoDTO pasoDto : dto.getPasosTarea()) {
                AtsPaso paso = new AtsPaso();
                paso.setPaso(pasoDto.getPaso());
                paso.setDescripcion(pasoDto.getDescripcion());
                paso.setPeligro(pasoDto.getPeligro());
                paso.setRiesgo(pasoDto.getRiesgo());
                paso.setMedidaControl(pasoDto.getMedidaControl());
                ats.addPaso(paso);
            }
        }

        return atsRepository.save(ats);
    }

    public List<Ats> obtenerTodos() {
        return atsRepository.findAll();
    }
}