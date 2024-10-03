package model;
public class UsuarioGratuito extends Usuario {
    public UsuarioGratuito(String idUsuario, String nombres, String apellidos, String nickname, String email, String contraseña, String fotoPerfilUrl) {
        super(idUsuario, nombres, apellidos, nickname, email, contraseña, fotoPerfilUrl);
    }

    @Override
    public void mostrarTipoUsuario() {
        System.out.println("Tipo de usuario: Gratuito");
    }
}
