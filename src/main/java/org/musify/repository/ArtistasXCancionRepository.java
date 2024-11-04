package org.musify.repository;

import org.musify.model.artistasXCancion.ArtistasXCancion;
import org.musify.model.cancion.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistasXCancionRepository extends JpaRepository<ArtistasXCancion, String> {
    @Query("SELECT ac.cancion " +
            "FROM ArtistasXCancion ac " +
            "JOIN ac.artista a " +
            "WHERE a.nombreArtistico = :nombreArtista")
    List<Cancion> getCancionesByNombreArtista(@Param("nombreArtista") String nombreArtista);




}
