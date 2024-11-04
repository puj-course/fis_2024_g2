package org.musify.repository;

import org.musify.model.artistasXAlbum.ArtistasXAlbum;
import org.musify.model.artistasXAlbum.ArtistasXAlbumId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistasXAlbumRepository extends JpaRepository<ArtistasXAlbum, ArtistasXAlbumId> {
}
