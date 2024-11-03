package org.musify.repository;

import org.musify.model.CancionesXAlbum;
import org.musify.model.CancionesXAlbumId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionesXAlbumRepository extends JpaRepository<CancionesXAlbum, CancionesXAlbumId> {
}
