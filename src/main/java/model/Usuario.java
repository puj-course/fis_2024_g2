package model;

public abstract class Usuario {
    protected String idUsuario;
    protected String nombres;
    protected String apellidos;
    protected String nickname;
    protected String email;
    protected String contraseña;
    protected String fotoPerfilUrl;

    public Usuario(String idUsuario, String nombres, String apellidos, String nickname, String email, String contraseña, String fotoPerfilUrl) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nickname = nickname;
        this.email = email;
        this.contraseña = contraseña;
        this.fotoPerfilUrl = fotoPerfilUrl;
    }

    public abstract void mostrarTipoUsuario();

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    public void setFotoPerfilUrl(String fotoPerfilUrl) {
        this.fotoPerfilUrl = fotoPerfilUrl;
    }
}