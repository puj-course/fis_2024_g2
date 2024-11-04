package org.musify.service;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.usuario.*;
import org.musify.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario getUsuarioById(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @Override
    public Usuario crearUsuario(org.musify.model.usuario.UsuarioRegistroDTO usuarioDTO, Usuario usuario) {

        //Pasar los datos del DTO al usuario nuevo
        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setNickname(usuarioDTO.getNickname());
        usuario.setContra(usuarioDTO.getContra());
        usuario.setNumeroTelefonico(usuarioDTO.getNumeroTelefonico());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setPaisIdPais(usuarioDTO.getPaisIdPais());
        usuario.setIdiomaIdIdioma(usuarioDTO.getIdiomaIdIdioma());


        //Agregar datos adicionales
        String idAleatorio = UUID.randomUUID().toString();  // Genera un UUID de 32 caracteres
        usuario.setId_usuario(idAleatorio.replace("-", ""));
        usuario.setFechaRegistro(Date.valueOf(LocalDate.now()));
        usuario.setEstado("activo");
        return usuarioRepository.save(usuario);
    }
    @Override
    public Usuario getUsuarioByNickname(String nickname) {
        return usuarioRepository.findByNickname(nickname)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    //Este método busca un usuario en la DB y lo convierte en
    //un User de SpringSecurity
//    @Override
//    public UserDetails loadUserByNickname(String username) throws UsernameNotFoundException {
//        // Buscar el usuario según su username
//        Usuario usuario = usuarioRepository.findByNickname(username)
//                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));
//
//        // Determinar el rol según el tipo de usuario concreto
//        String role;
//        if (usuario instanceof UsuarioGratuito) {
//            role = "ROLE_USER";
//        } else if (usuario instanceof UsuarioPremium) {
//            role = "ROLE_PREMIUM";
//        } else if (usuario instanceof Administrador) {
//            role = "ROLE_ADMIN";
//        } else {
//            throw new IllegalStateException("Tipo de usuario no reconocido");
//        }
//
//        // Crear los authorities basados en el tipo de usuario determinado
//        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
//
//        // Retornar un objeto User de Spring Security con los valores true por defecto para las otras propiedades
//        return new User(
//                usuario.getNickname(),
//                usuario.getContra(),
//                true, // enabled ->Indica si cuenta de usuario está habilitada
//                true, // accountNonExpired -> No expirada
//                true, // credentialsNonExpired ->Credenciales no expiradas
//                true, // accountNonLocked ->Cuenta no bloqueada
//                authorities
//        );
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario según su username
        Usuario usuario = usuarioRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));

        // Determinar el rol según el tipo de usuario concreto
        String role;
        if (usuario instanceof org.musify.model.usuario.UsuarioGratuito) {
            role = "ROLE_USER";
        } else if (usuario instanceof org.musify.model.usuario.UsuarioPremium) {
            role = "ROLE_PREMIUM";
        } else if (usuario instanceof Administrador) {
            role = "ROLE_ADMIN";
        } else {
            throw new IllegalStateException("Tipo de usuario no reconocido");
        }

        // Crear los authorities basados en el tipo de usuario determinado
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        // Retornar un objeto User de Spring Security con los valores true por defecto para las otras propiedades
        return new User(
                usuario.getNickname(),
                usuario.getContra(),
                true, // enabled ->Indica si cuenta de usuario está habilitada
                true, // accountNonExpired -> No expirada
                true, // credentialsNonExpired ->Credenciales no expiradas
                true, // accountNonLocked ->Cuenta no bloqueada
                authorities
        );
    }
}
