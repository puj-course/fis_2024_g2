package org.musify.model.artistasXAlbum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.musify.model.album.Album;
import org.musify.model.artista.Artista;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artistasxalbum")
public class ArtistasXAlbum {
    @EmbeddedId
    private ArtistasXAlbumId id;  // Clave primaria compuesta

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id_album")
    private Album album;

    @ManyToOne
    @MapsId("artistaId")
    @JoinColumn(name = "artista_id_artista")
    private Artista artista;
}
