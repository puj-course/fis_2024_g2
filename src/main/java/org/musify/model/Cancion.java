package org.musify.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cancion")
public class Cancion {

    @Id
    @Column(name = "id_cancion")
    private String idCancion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "fecha_lanzamiento", nullable = false)
    private Date fechaLanzamiento;

    @Column(name = "letra", columnDefinition = "")
    private String letra;

    @Column(name = "audio_url", nullable = false)
    private String audioUrl;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresadiscografica_id_empresa_discografica", nullable = false)
    private EmpresaDiscografica empresaDiscografica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_album")
    private Album album;

    @Column(name = "numero_pista")
    private Integer numeroPista;

    // Constructor vacío
    public Cancion() {
    }

    // Constructor
    public Cancion(String idCancion, String nombre, Integer duracion, Date fechaLanzamiento,
                   String letra, String audioUrl, String imagenUrl,
                   EmpresaDiscografica empresaDiscografica, Album album, Integer numeroPista) {
        this.idCancion = idCancion;
        this.nombre = nombre;
        this.duracion = duracion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.letra = letra;
        this.audioUrl = audioUrl;
        this.imagenUrl = imagenUrl;
        this.empresaDiscografica = empresaDiscografica;
        this.album = album;
        this.numeroPista = numeroPista;
    }

    // Getters y Setters
    public String getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(String idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public EmpresaDiscografica getEmpresaDiscografica() {
        return empresaDiscografica;
    }

    public void setEmpresaDiscografica(EmpresaDiscografica empresaDiscografica) {
        this.empresaDiscografica = empresaDiscografica;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getNumeroPista() {
        return numeroPista;
    }

    public void setNumeroPista(Integer numeroPista) {
        this.numeroPista = numeroPista;
    }
}