public class Main {
    public static void main(String[] args) {
        // Crear un usuario gratuito
        Usuario usuarioGratuito = UsuarioFactory.crearUsuario(TipoUsuarioEnum.GRATUITO, "1", "Sami", "Campos", "samuelescYT", "sami@example.com", "password123", "fotoUrl", null);
        usuarioGratuito.mostrarTipoUsuario();

        // Crear un usuario premium
        Usuario usuarioPremium = UsuarioFactory.crearUsuario(TipoUsuarioEnum.PREMIUM, "2", "Sebas", "Sanchez", "Nori", "N0ri@example.com", "password456", "fotoUrl", null);
        usuarioPremium.mostrarTipoUsuario();

        // Crear un artista
        Usuario artista = UsuarioFactory.crearUsuario(TipoUsuarioEnum.ARTISTA, "3", "Gabo", "Camacho", "Bobis", "bobis@jijijijajaja.com", "password789", "fotoUrl", "Cantante legendario");
        artista.mostrarTipoUsuario();
    }
}
