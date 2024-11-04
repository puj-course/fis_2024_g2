package org.musify.repository;

import org.musify.model.cancion.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion, String> {
    Optional<Cancion> findByNombre(String nombre);
}
