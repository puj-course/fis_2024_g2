package org.musify.service;

import org.musify.model.Usuario;
import org.musify.model.UsuarioRegistroDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioService {
    public Usuario getUsuarioByNickname(String id);
    public Usuario getUsuarioById(String id);
    public Usuario crearUsuario(UsuarioRegistroDTO usuarioDTO, Usuario usuario);
//    UserDetails loadUserByNickname(String username) throws UsernameNotFoundException;
}
