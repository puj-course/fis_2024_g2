package org.musify.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @Column(name = "id_album")
    private String idAlbum;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_lanzamiento", nullable = false)
    private Date fechaLanzamiento;

    @Column(name = "imagenurl")
    private String imagenUrl;

    // Opción 1: Usando List
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cancion> canciones = new ArrayList<>();

    // Opción 2: Usando Map con número de pista como key
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapKey(name = "numeroPista")  // Asumiendo que agregarás este campo a la clase Cancion
    private Map<Integer, Cancion> cancionesPorPista = new HashMap<>();

    // Constructor vacío
    public Album() {
    }

    // Constructor con parámetros básicos
    public Album(String idAlbum, String nombre, Date fechaLanzamiento, String imagenUrl) {
        this.idAlbum = idAlbum;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagenUrl = imagenUrl;
    }

    // Getters y Setters
    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    // Métodos para List
    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void addCancion(Cancion cancion) {
        canciones.add(cancion);
        cancion.setAlbum(this);
    }

    public void removeCancion(Cancion cancion) {
        canciones.remove(cancion);
        cancion.setAlbum(null);
    }

    // Métodos para Map
    public Map<Integer, Cancion> getCancionesPorPista() {
        return cancionesPorPista;
    }

    public void setCancionesPorPista(Map<Integer, Cancion> cancionesPorPista) {
        this.cancionesPorPista = cancionesPorPista;
    }

    public void addCancionConPista(Integer numeroPista, Cancion cancion) {
        cancionesPorPista.put(numeroPista, cancion);
        cancion.setAlbum(this);
    }

    public void removeCancionPorPista(Integer numeroPista) {
        Cancion cancion = cancionesPorPista.remove(numeroPista);
        if (cancion != null) {
            cancion.setAlbum(null);
        }
    }
}