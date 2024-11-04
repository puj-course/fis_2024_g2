package org.musify.model.album;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "album")
public class Album {

    @Id
    @Column(name = "id_album", length = 32)
    private String idAlbum;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(name = "fecha_lanzamiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;

    @Column(name = "imagenurl", length = 255)
    private String imagenUrl;
}