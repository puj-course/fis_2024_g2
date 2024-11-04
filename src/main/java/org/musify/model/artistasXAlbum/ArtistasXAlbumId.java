package org.musify.model.artistasXAlbum;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.musify.model.artistasXCancion.ArtistasXCancionId;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ArtistasXAlbumId {
    @Column(name = "album_id", length = 32)
    private String albumId;

    @Column(name = "artista_id", length = 32)
    private String artistaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistasXAlbumId that = (ArtistasXAlbumId) o;
        return Objects.equals(albumId, that.albumId) && Objects.equals(artistaId, that.artistaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, artistaId);
    }
}
