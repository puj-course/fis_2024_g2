package org.musify.model;

public class Administrador extends Usuario{

    public Administrador(String idUsuario, String nombres, String apellidos, String nickname, String email, String contraseña, String fotoPerfilUrl) {
        super(idUsuario, nombres, apellidos, nickname, email, contraseña, fotoPerfilUrl);
    }

    @Override
    public void mostrarTipoUsuario() {
        System.out.println("Tipo de usuario: Administrador");
    }
}
