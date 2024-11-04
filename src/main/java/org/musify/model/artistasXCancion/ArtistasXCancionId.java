package org.musify.model.artistasXCancion;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.musify.model.generosXCancion.GenerosXCancionId;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ArtistasXCancionId implements Serializable {
    @Column(name = "cancion_id", length = 32)
    private String cancionId;

    @Column(name = "artista_id", length = 32)
    private String artistaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistasXCancionId that = (ArtistasXCancionId) o;
        return Objects.equals(cancionId, that.cancionId) && Objects.equals(artistaId, that.artistaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancionId, artistaId);
    }
}
