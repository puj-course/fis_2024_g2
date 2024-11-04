package org.musify.model.generosXCancion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.musify.model.GeneroMusical;
import org.musify.model.cancion.Cancion;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "generosxcancion")
public class GenerosXCancion {

    @EmbeddedId
    private GenerosXCancionId id;  // Clave primaria compuesta

    @ManyToOne
    @MapsId("cancionId")  // Mapea el campo cancionId en GenerosXCancionId
    @JoinColumn(name = "cancion_id_cancion")
    private Cancion cancion;

    @ManyToOne
    @MapsId("generoId")  // Mapea el campo generoId en GenerosXCancionId
    @JoinColumn(name = "genero_id")
    private GeneroMusical generoMusical;
}
