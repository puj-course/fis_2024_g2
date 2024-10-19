package org.musify.service;

import org.musify.model.Usuario;

public interface UsuarioService {
    public Usuario getUsuario(String id);
    public Usuario crearUsuario(Usuario usuario);
}
