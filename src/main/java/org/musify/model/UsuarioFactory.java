package org.musify.model;

import java.sql.Date;

public class UsuarioFactory {

    public static Usuario crearUsuarioPorRol(String rol) {
        switch (rol.toLowerCase()) {
            case "premium":
                return new UsuarioPremium();
            case "gratuito":
                return new UsuarioGratuito();
            case "admin":
                return new Administrador();
            default:
                throw new IllegalArgumentException("Rol no válido: " + rol);
        }
    }


    public static Usuario crearUsuario(TipoUsuario tipo, String idUsuario, String nombres, String apellidos,
                                       String nickname, String email, String contraseña,
                                       String fotoPerfilUrl, Date fechaNacimiento,
                                       Date fechaRegistro, String estado,
                                       Integer paisIdPais, Integer idiomaIdIdioma, String biografia) {
        switch (tipo) {
            case GRATUITO:
                return new UsuarioGratuito(idUsuario, nombres, apellidos, nickname, contraseña, email,
                        fechaNacimiento, fechaRegistro, estado,
                        paisIdPais, idiomaIdIdioma, fotoPerfilUrl);
            case PREMIUM:
                return new UsuarioPremium(idUsuario, nombres, apellidos, nickname, contraseña, email,
                        fechaNacimiento, fechaRegistro, estado,
                        paisIdPais, idiomaIdIdioma, fotoPerfilUrl);

            case ADMIN:
                return new Administrador(idUsuario, nombres, apellidos, nickname, contraseña, email,
                        fechaNacimiento, fechaRegistro, estado,
                        paisIdPais, idiomaIdIdioma, fotoPerfilUrl);

            default:
                throw new IllegalArgumentException("Tipo de usuario no válido");
        }
    }
}
