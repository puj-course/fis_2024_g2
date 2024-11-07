package org.musify.repository;

import org.musify.model.cancion.Cancion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion, String> {
    @EntityGraph(attributePaths = {"artistas"})
    List<Cancion> findAll(); //Asegura que se haga en una sola consulta

    Optional<Cancion> findByNombre(String nombre);
}
