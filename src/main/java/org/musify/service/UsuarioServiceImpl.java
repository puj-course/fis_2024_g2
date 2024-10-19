package org.musify.service;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.Usuario;
import org.musify.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;



    public Usuario getUsuario(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setId_usuario("4");
        usuario.setFechaRegistro(Date.valueOf(LocalDate.now()));
        usuario.setEstado("activo");
        return usuarioRepository.save(usuario);
    }

}
