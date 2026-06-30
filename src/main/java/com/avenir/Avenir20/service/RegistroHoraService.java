package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.Empresa;
import com.avenir.Avenir20.model.RegistroHora;
import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.repository.EmpresaRepository;
import com.avenir.Avenir20.repository.RegistroHoraRepository;
import com.avenir.Avenir20.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroHoraService {

    @Autowired
    private RegistroHoraRepository registroHoraRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // US: Registrar horas trabajadas
    public RegistroHora guardarRegistro(Long idEmpresa, String emailUsuario, LocalDate fecha, Double horas, String tareas) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(idEmpresa);
        // Buscamos al usuario por el EMAIL
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(emailUsuario);

        if (empresaOpt.isEmpty()) {
            throw new IllegalArgumentException("Empresa no encontrada.");
        }
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con ese email.");
        }

        RegistroHora registro = new RegistroHora();
        registro.setEmpresa(empresaOpt.get());
        registro.setUsuario(usuarioOpt.get());
        registro.setFecha(fecha);
        registro.setHorasDedicadas(horas);
        registro.setTareasRealizadas(tareas);

        return registroHoraRepository.save(registro);
    }

    // US: Calendario - Ver registros de un día específico
    public List<RegistroHora> buscarPorFecha(LocalDate fecha) {
        return registroHoraRepository.findByFecha(fecha);
    }
}