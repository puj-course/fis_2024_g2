package org.musify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        // Lógica para crear usuarios (descomentada al ejecutar)
        /*
        Usuario usuarioGratuito = UsuarioFactory.crearUsuario(TipoUsuario.GRATUITO, "1", "Sami", "Campos", "samuelescYT", "sami@example.com", "password123", "fotoUrl", null);
        usuarioGratuito.mostrarTipoUsuario();

        Usuario usuarioPremium = UsuarioFactory.crearUsuario(TipoUsuario.PREMIUM, "2", "Sebas", "Sanchez", "Nori", "N0ri@example.com", "password456", "fotoUrl", null);
        usuarioPremium.mostrarTipoUsuario();

        Usuario artista = UsuarioFactory.crearUsuario(TipoUsuario.ARTISTA, "3", "Gabo", "Camacho", "Bobis", "bobis@jijijijajaja.com", "password789", "fotoUrl", "Cantante legendario");
        artista.mostrarTipoUsuario();
        */
    }
}
