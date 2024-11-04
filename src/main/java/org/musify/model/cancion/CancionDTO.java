package org.musify.model.cancion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CancionDTO {
    private String nombre;
    private Integer duracion;
    private Date fechaLanzamiento;
    private String letra;
    private String audioUrl;
    private String imagenUrl;
    private String empresaDiscograficaId;

    public CancionDTO(Cancion cancion) {
        this.nombre = cancion.getNombre();
        this.duracion = cancion.getDuracion();
        this.fechaLanzamiento = cancion.getFechaLanzamiento();
        this.letra = cancion.getLetra();
        this.audioUrl = cancion.getAudioUrl();
        this.imagenUrl = cancion.getImagenUrl();
        this.empresaDiscograficaId = cancion.getEmpresaDiscograficaId();
    }
}
