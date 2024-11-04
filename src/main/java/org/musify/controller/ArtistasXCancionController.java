package org.musify.controller;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.artistasXCancion.ArtistasXCancion;
import org.musify.model.artistasXCancion.ArtistasXCancionDTO;
import org.musify.model.cancion.CancionDTO;
import org.musify.service.ArtistasXCancionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artistas_cancion")
public class ArtistasXCancionController {

    @Autowired
    ArtistasXCancionServiceImpl artistasXCancionService;

    @PostMapping("/relacionar")
    public ResponseEntity<?> relacionarArtistaYCancion(@RequestBody ArtistasXCancionDTO artistasXCancionDTO) {
        try {
            // Llama al método en el servicio para crear la relación
            ArtistasXCancion nuevaRelacion = artistasXCancionService.relacionarArtistaYcancion(artistasXCancionDTO);

            //Crear la URI para retornar la ubicación de la nueva relación
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevaRelacion.getId())
                    .toUri();
            return ResponseEntity.created(location).body(nuevaRelacion);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la relación: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getCancionesByArtista(@RequestParam String nombreArtista) {
        try {
            List<CancionDTO> canciones = artistasXCancionService.getCancionesByNombreArtista(nombreArtista);

            if (canciones == null || canciones.isEmpty()) {
                return ResponseEntity.notFound().build(); // Se retorna 404 si no hay canciones
            }

            return ResponseEntity.ok(canciones);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }
}
