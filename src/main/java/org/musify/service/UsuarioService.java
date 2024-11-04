package org.musify.service;

import org.musify.model.usuario.Usuario;
import org.musify.model.usuario.UsuarioRegistroDTO;

public interface UsuarioService {
    public Usuario getUsuarioByNickname(String id);
    public Usuario getUsuarioById(String id);
    public Usuario crearUsuario(UsuarioRegistroDTO usuarioDTO, Usuario usuario);
//    UserDetails loadUserByNickname(String username) throws UsernameNotFoundException;
}
