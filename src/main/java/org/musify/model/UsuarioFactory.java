package org.musify.model;

public class UsuarioFactory {

    public static Usuario crearUsuario(TipoUsuario tipo, String idUsuario, String nombres, String apellidos, String nickname, String email, String contraseña, String fotoPerfilUrl, String biografia) {
        switch (tipo) {
            case GRATUITO:
                return new UsuarioGratuito(idUsuario, nombres, apellidos, nickname, email, contraseña, fotoPerfilUrl);
            case PREMIUM:
                return new UsuarioPremium(idUsuario, nombres, apellidos, nickname, email, contraseña, fotoPerfilUrl);
            case ARTISTA:
                return new Artista(idUsuario, nombres, apellidos, nickname, email, contraseña, fotoPerfilUrl, biografia);
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido");
        }
    }
}
