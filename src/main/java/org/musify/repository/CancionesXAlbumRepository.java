package org.musify.repository;

import org.musify.model.cancion.Cancion;
import org.musify.model.cancionesXAlbum.CancionesXAlbum;
import org.musify.model.cancionesXAlbum.CancionesXAlbumId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CancionesXAlbumRepository extends JpaRepository<CancionesXAlbum, CancionesXAlbumId> {
    @Query("SELECT ca.cancion " +
            "FROM CancionesXAlbum ca " +
            "JOIN ca.album a " +
            "WHERE a.nombre = :nombreAlbum")
    List<Cancion> getCancionesByNombreAlbum(@Param("nombreAlbum") String nombreAlbum);

}
