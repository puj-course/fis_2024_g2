package org.musify.model.artista;

import jakarta.persistence.*;
import org.musify.model.usuario.Usuario;

import java.sql.Date;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @Column(name = "id_artista", length = 32, nullable = false)
    private String idArtista;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre_artistico", length = 255, nullable = false)
    private String nombreArtistico;

    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistroArtista;

    @Column(name = "biografia", length = 100)
    private String biografia;

    // Constructor por defecto
    public Artista() {
    }

    // Constructor con parámetros
    public Artista(String idArtista, Usuario usuario, String nombreArtistico, Date fechaRegistroArtista, String biografia) {
        this.idArtista = idArtista;
        this.usuario = usuario;
        this.nombreArtistico = nombreArtistico;
        this.fechaRegistroArtista = fechaRegistroArtista;
        this.biografia = biografia;
    }

    // Getters y Setters
    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public Date getFechaRegistroArtista() {
        return fechaRegistroArtista;
    }

    public void setFechaRegistroArtista(Date fechaRegistroArtista) {
        this.fechaRegistroArtista = fechaRegistroArtista;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}