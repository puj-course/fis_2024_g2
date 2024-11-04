package org.musify.repository;

import org.musify.model.cancionesXAlbum.CancionesXAlbum;
import org.musify.model.cancionesXAlbum.CancionesXAlbumId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionesXAlbumRepository extends JpaRepository<CancionesXAlbum, CancionesXAlbumId> {
}
