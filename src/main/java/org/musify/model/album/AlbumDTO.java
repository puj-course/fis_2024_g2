package org.musify.model.album;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {
    private String nombre;
    private Date fechaLanzamiento;
    private String imagenUrl;

    public AlbumDTO(Album album) {
        this.nombre = album.getNombre();
        this.fechaLanzamiento=album.getFechaLanzamiento();
        this.imagenUrl = album.getImagenUrl();
    }
}
