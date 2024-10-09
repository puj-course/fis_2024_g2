package org.musify.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.sql.Date;


@Entity
@DiscriminatorValue("premium")
public class UsuarioPremium extends Usuario {

    public UsuarioPremium() {
    }

    public UsuarioPremium(String idUsuario, String nombres, String apellidos, String nickname, String contraseña,
                          String email, Date fechaNacimiento, Date fechaRegistro, String estado,
                          String paisIdPais, String idiomaIdIdioma, String fotoPerfilUrl) {

        super(idUsuario, nombres, apellidos, nickname, contraseña, email, fechaNacimiento, fechaRegistro, estado, paisIdPais, idiomaIdIdioma, fotoPerfilUrl);
    }


    @Override
    public void mostrarTipoUsuario() {
        System.out.println("Tipo de usuario: Premium");
    }
}