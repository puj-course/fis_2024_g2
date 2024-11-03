package org.musify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cancionesXAlbum")
public class CancionesXAlbum {
    @EmbeddedId
    private CancionesXAlbumId id;  // Clave primaria compuesta

    @ManyToOne
    @MapsId("cancionId")  // Mapea el campo cancionId en CancionAlbumId
    @JoinColumn(name = "cancion_id_cancion")  // Nombre de columna en la tabla
    private Cancion cancion;

    @ManyToOne
    @MapsId("albumId")  // Mapea el campo albumId en CancionAlbumId
    @JoinColumn(name = "album_id_album")  // Nombre de columna en la tabla
    private Album album;
}
