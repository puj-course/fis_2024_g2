package org.musify.model;

public class Artista extends Usuario {
    private String biografia;

    public Artista(String idUsuario, String nombres, String apellidos, String nickname, String email, String contraseña, String fotoPerfilUrl, String biografia) {
        super(idUsuario, nombres, apellidos, nickname, email, contraseña, fotoPerfilUrl);
        this.biografia = biografia;
    }

    @Override
    public void mostrarTipoUsuario() {
        System.out.println("Tipo de usuario: Artista");
    }

    // Getters y setters
    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
