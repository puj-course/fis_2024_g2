package org.musify.repository;

import org.musify.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    Optional<Usuario> findByNickname(String nickname);
}
