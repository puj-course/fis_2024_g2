package org.musify.service;

import org.musify.model.Usuario;
import org.musify.model.UsuarioRegistroDTO;

public interface UsuarioService {
    public Usuario getUsuarioByNickname(String id);
    public Usuario getUsuarioById(String id);
    public Usuario crearUsuario(UsuarioRegistroDTO usuarioDTO, Usuario usuario);
}
