package org.musify.model.usuario;

import java.sql.Date;

public class UsuarioRegistroDTO {
    private String rol;
    private String nombres;
    private String apellidos;
    private String nickname;
    private String contra;
    private String numeroTelefonico;
    private Date fechaNacimiento;
    private Integer paisIdPais;
    private Integer idiomaIdIdioma;

    public UsuarioRegistroDTO(String rol, String nombres, String apellidos, String nickname, String contra, String numeroTelefonico, Date fechaNacimiento, Integer paisIdPais, Integer idiomaIdIdioma) {
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nickname = nickname;
        this.contra = contra;
        this.numeroTelefonico = numeroTelefonico;
        this.fechaNacimiento = fechaNacimiento;
        this.paisIdPais = paisIdPais;
        this.idiomaIdIdioma = idiomaIdIdioma;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getPaisIdPais() {
        return paisIdPais;
    }

    public void setPaisIdPais(Integer paisIdPais) {
        this.paisIdPais = paisIdPais;
    }

    public Integer getIdiomaIdIdioma() {
        return idiomaIdIdioma;
    }

    public void setIdiomaIdIdioma(Integer idiomaIdIdioma) {
        this.idiomaIdIdioma = idiomaIdIdioma;
    }
}
