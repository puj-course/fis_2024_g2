package org.musify.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresadiscografica")
public class EmpresaDiscografica {

    @Id
    @Column(name = "id_empresa_discografica")
    private String idEmpresaDiscografica;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

    @OneToMany(mappedBy = "empresaDiscografica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cancion> canciones = new ArrayList<>();

    // Constructor vacío
    public EmpresaDiscografica() {
    }

    // Constructor con parámetros
    public EmpresaDiscografica(String idEmpresaDiscografica, String nombre, Date fechaRegistro) {
        this.idEmpresaDiscografica = idEmpresaDiscografica;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public String getIdEmpresaDiscografica() {
        return idEmpresaDiscografica;
    }

    public void setIdEmpresaDiscografica(String idEmpresaDiscografica) {
        this.idEmpresaDiscografica = idEmpresaDiscografica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    // Métodos helper para manejar la relación bidireccional
    public void addCancion(Cancion cancion) {
        canciones.add(cancion);
        cancion.setEmpresaDiscografica(this);
    }

    public void removeCancion(Cancion cancion) {
        canciones.remove(cancion);
        cancion.setEmpresaDiscografica(null);
    }
}