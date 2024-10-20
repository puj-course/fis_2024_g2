package org.musify.service;

import org.musify.model.Usuario;

public interface UsuarioService {
    public Usuario getUsuarioByNickname(String id);
    public Usuario getUsuarioById(String id);
    public Usuario crearUsuario(Usuario usuario);
}
