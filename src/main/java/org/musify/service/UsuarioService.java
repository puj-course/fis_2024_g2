package org.musify.service;

import org.musify.model.usuario.Usuario;
import org.musify.model.usuario.UsuarioMostrarDatosDTO;
import org.musify.model.usuario.UsuarioRegistroDTO;

import java.util.List;

public interface UsuarioService {
    public Usuario getUsuarioByNickname(String id);
    public Usuario getUsuarioById(String id);
    public Usuario crearUsuario(UsuarioRegistroDTO usuarioDTO, Usuario usuario);
    public List<UsuarioMostrarDatosDTO> getUsuarios();
}
