package org.musify.controller;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.cancion.CancionDTO;
import org.musify.model.cancionesXAlbum.CancionesXAlbum;
import org.musify.model.cancionesXAlbum.CancionesXAlbumDTO;
import org.musify.service.CancionesXAlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/canciones_album")
public class CancionesXAlbumController {
    @Autowired
    private CancionesXAlbumServiceImpl cancionAlbumService;

    @PostMapping("/relacionar")
    public ResponseEntity<?> relacionarCancionYAlbum(@RequestBody CancionesXAlbumDTO cancionesXAlbumDTO) {
        try {
            // Llama al método en el servicio para crear la relación
            CancionesXAlbum nuevaRelacion = cancionAlbumService.relacionarCancionYAlbum(cancionesXAlbumDTO);

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
    public ResponseEntity<?> getCancionesByAlbum(@RequestParam String nombreAlbum) {
        try {
            List<CancionDTO> canciones = cancionAlbumService.getCancionesByNombreAlbum(nombreAlbum);

            if (canciones == null || canciones.isEmpty()) {
                return ResponseEntity.notFound().build(); // Se retorna 404 si no hay canciones
            }

            return ResponseEntity.ok(canciones);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Álbum no encontrado: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

}
