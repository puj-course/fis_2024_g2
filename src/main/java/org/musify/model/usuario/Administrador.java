package org.musify.model.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.musify.model.Pais;


import java.sql.Date;

@Entity
@DiscriminatorValue("admin")
public class Administrador extends Usuario {

    public Administrador() {
    }

    public Administrador(String idUsuario, String nombres, String apellidos, String nickname, String contraseña,
                         String email, Date fechaNacimiento, Date fechaRegistro, String estado,
                         Pais pais, Integer idiomaIdIdioma, String fotoPerfilUrl) {

        super(idUsuario, nombres, apellidos, nickname, contraseña, email, fechaNacimiento, fechaRegistro, estado, pais, idiomaIdIdioma, fotoPerfilUrl);
    }

    @Override
    public void mostrarTipoUsuario() {
        System.out.println("Tipo de usuario: Administrador");
    }
}
