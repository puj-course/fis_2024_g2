package org.musify.model.artistasXCancion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.musify.model.GeneroMusical;
import org.musify.model.artista.Artista;
import org.musify.model.cancion.Cancion;
import org.musify.model.generosXCancion.GenerosXCancionId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artistasxcancion")
public class ArtistasXCancion {

    @EmbeddedId
    private ArtistasXCancionId id;  // Clave primaria compuesta

    @ManyToOne
    @MapsId("cancionId")
    @JoinColumn(name = "cancion_id_cancion")
    private Cancion cancion;

    @ManyToOne
    @MapsId("artistaId")
    @JoinColumn(name = "artista_id_artista")
    private Artista artista;
}
