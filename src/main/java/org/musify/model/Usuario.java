package org.musify.model;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol")
public abstract class Usuario {

    @Id
    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "contra")
    private String contraseña; // Puedes usar 'contraseña' o 'contra' como prefieras, pero asegúrate de que coincida con el nombre en la base de datos.

    @Column(name = "email")
    private String email;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    @Column(name = "rol", insertable = false, updatable = false)

    //@Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais_id_pais")
    private String paisIdPais; // Puedes usar el nombre que prefieras, pero asegúrate de que coincida.

    @Column(name = "idioma_id_idioma")
    private String idiomaIdIdioma; // Puedes usar el nombre que prefieras, pero asegúrate de que coincida.

    @Column(name = "foto_perfil_url")
    private String fotoPerfilUrl;

    public Usuario() {
    }

    // Constructor con parámetros sin 'rol'
    public Usuario(String idUsuario, String nombres, String apellidos, String nickname, String contraseña,
                   String email, Date fechaNacimiento, Date fechaRegistro, String estado,
                   String paisIdPais, String idiomaIdIdioma, String fotoPerfilUrl) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nickname = nickname;
        this.contraseña = contraseña;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.paisIdPais = paisIdPais;
        this.idiomaIdIdioma = idiomaIdIdioma;
        this.fotoPerfilUrl = fotoPerfilUrl;
    }




    public abstract void mostrarTipoUsuario();

    public String getId_usuario() {
        return idUsuario;
    }

    public void setId_usuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContra() {
        return contraseña;
    }

    public void setContra(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    public void setFotoPerfilUrl(String fotoPerfilUrl) {
        this.fotoPerfilUrl = fotoPerfilUrl;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPaisIdPais() {
        return paisIdPais;
    }

    public void setPaisIdPais(String paisIdPais) {
        this.paisIdPais = paisIdPais;
    }

    public String getIdiomaIdIdioma() {
        return idiomaIdIdioma;
    }

    public void setIdiomaIdIdioma(String idiomaIdIdioma) {
        this.idiomaIdIdioma = idiomaIdIdioma;
    }
}