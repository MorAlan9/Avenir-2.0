package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.Login;
import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.model.UsuarioRequest;
import com.avenir.Avenir20.repository.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Guardar un nuevo usuario en la base de datos (UH-3)
    /*El metodo guardar usa una variable llamada "claveAcceso" para permitir al usuario crear una cuenta solo si tiene dicha clave.
    El valor de la clave, por el momento, está fijo en el código pero más adelante vamos a hacer que probenga de la base de datos y el admin la pueda modificar*/
    public ResponseEntity<?> guardar(UsuarioRequest usuarioRequest) {
        String llave = "000010001";
        if(usuarioRequest.getClaveAcceso().equals(llave)){
            usuarioRequest.getUsuario().setActivo(true);
            Usuario guardado = repository.save(usuarioRequest.getUsuario());
            return ResponseEntity.ok(guardado);
        } else{
            return new ResponseEntity<>("La clave de acceso no es correcta", HttpStatus.BAD_REQUEST);
        }
    }

    // Listar todos los usuarios
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
    // Verifica credenciales
    public Usuario obtenerUsuarioPorCredenciales(Login login){
        Optional<Usuario> usuarioOpt = repository.findByEmail(login.getEmail());
        if(usuarioOpt.isEmpty()){
            return null;
        }else{

            String contrasenaHashed = usuarioOpt.get().getContrasena();
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if(argon2.verify(contrasenaHashed, login.getContrasena())){
                return usuarioOpt.get();
            }
            return null;
        }
    }
}