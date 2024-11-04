package org.musify.model.usuario;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import org.musify.model.usuario.UsuarioGratuito;
import org.musify.model.usuario.UsuarioPremium;
import org.musify.model.usuario.Administrador;

import java.sql.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol")  // Se define la columna, sin tenerla como atributo

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "rol"  // Aquí se definirá el tipo de usuario en el JSON usando "rol"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UsuarioGratuito.class, name = "gratuito"),
        @JsonSubTypes.Type(value = UsuarioPremium.class, name = "premium"),
        @JsonSubTypes.Type(value = Administrador.class, name = "admin")
})
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
    private String contraseña;


    @Column(name = "numero_telefonico")
    //@JsonProperty("numero_telefonico") //Si en el JSON se pone distinto nombre al del atributo en la clase
    private String numeroTelefonico;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais_id_pais")
    private Integer paisIdPais;

    @Column(name = "idioma_id_idioma")
    private Integer idiomaIdIdioma;

    @Column(name = "foto_perfil_url")
    private String fotoPerfilUrl;

    public Usuario() {
    }

    // Constructor con parámetros sin 'rol'
    public Usuario(String idUsuario, String nombres, String apellidos, String nickname, String contraseña,
                   String numeroTelefonico, Date fechaNacimiento, Date fechaRegistro, String estado,
                   Integer paisIdPais, Integer idiomaIdIdioma, String fotoPerfilUrl) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nickname = nickname;
        this.contraseña = contraseña;
        this.numeroTelefonico = numeroTelefonico;
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

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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