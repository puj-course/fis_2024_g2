package org.musify.model.cancionesXAlbum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.musify.model.album.Album;
import org.musify.model.cancion.Cancion;

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
    @JoinColumn(name = "cancion_id_cancion")
    private Cancion cancion;

    @ManyToOne
    @MapsId("albumId")  // Mapea el campo albumId en CancionAlbumId
    @JoinColumn(name = "album_id_album")
    private Album album;
}
