package org.musify.model.usuario;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMostrarDatosDTO {
    private String nombres;
    private String apellidos;
    private String nickname;
    private Date fechaNacimiento;
    private Date fechaRegistro;
    private Integer paisIdPais;
    private String fotoPerfilUrl;

    public UsuarioMostrarDatosDTO(Usuario usuario) {
        this.nombres = usuario.getNombres();
        this.apellidos = usuario.getApellidos();
        this.nickname = usuario.getNickname();
        this.fechaNacimiento = usuario.getFechaNacimiento();
        this.fechaRegistro = usuario.getFechaRegistro();
        this.paisIdPais = usuario.getPaisIdPais();  // Asegúrate de que este método exista
        this.fotoPerfilUrl = usuario.getFotoPerfilUrl();  // Asegúrate de que este método exista
    }
}
