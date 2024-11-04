package org.musify.model.generosXCancion;

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
public class GenerosXCancionId implements Serializable {

    @Column(name = "cancion_id", length = 32)
    private String cancionId;

    @Column(name = "genero_id", length = 32)
    private String generoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerosXCancionId that = (GenerosXCancionId) o;
        return Objects.equals(cancionId, that.cancionId) && Objects.equals(generoId, that.generoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancionId, generoId);
    }
}
