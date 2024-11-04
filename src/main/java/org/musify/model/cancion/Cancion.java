package org.musify.model.cancion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cancion")
public class Cancion {

    @Id
    @Column(name = "id_cancion", length = 32)
    private String idCancion;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false)
    private Integer duracion;

    @Column(name = "fecha_lanzamiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;

    @Column(columnDefinition = "TEXT")
    private String letra;

    @Column(name = "audio_url", nullable = false, length = 255)
    private String audioUrl;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @Column(name = "empresadiscografica_id_empresa_discografica", nullable = false)
    private String empresaDiscograficaId;


}
