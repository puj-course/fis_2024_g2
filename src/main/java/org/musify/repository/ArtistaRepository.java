package org.musify.repository;

import org.musify.model.artista.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, String> {
    Optional<Artista> findByNombreArtistico(String nombreArtistico);
}
