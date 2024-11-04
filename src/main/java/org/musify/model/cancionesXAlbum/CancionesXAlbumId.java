package org.musify.model.cancionesXAlbum;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CancionesXAlbumId implements Serializable {

    @Column(name = "id_cancion", length = 32)
    private String cancionId;

    @Column(name = "id_album", length = 32)
    private String albumId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancionesXAlbumId that = (CancionesXAlbumId) o;
        return Objects.equals(cancionId, that.cancionId) && Objects.equals(albumId, that.albumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancionId, albumId);
    }
}
