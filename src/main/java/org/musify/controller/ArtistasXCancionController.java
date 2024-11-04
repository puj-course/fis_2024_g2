package org.musify.controller;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.artistasXCancion.ArtistasXCancion;
import org.musify.model.artistasXCancion.ArtistasXCancionDTO;
import org.musify.service.ArtistasXCancionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/artistas_cancion")
public class ArtistasXCancionController {

    @Autowired
    ArtistasXCancionServiceImpl artistasXCancionService;

    @PostMapping("/relacionar")
    public ResponseEntity<?> relacionarArtistaYAlbum(@RequestBody ArtistasXCancionDTO artistasXCancionDTO) {
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
}
