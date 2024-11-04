package org.musify.controller;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.artistasXAlbum.ArtistasXAlbum;
import org.musify.model.artistasXAlbum.ArtistasXAlbumDTO;
import org.musify.model.artistasXCancion.ArtistasXCancion;
import org.musify.model.artistasXCancion.ArtistasXCancionDTO;
import org.musify.service.ArtistasXAlbumServiceImpl;
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
@RequestMapping("/artistas_album")
public class ArtistasXAlbumController {

    @Autowired
    private ArtistasXAlbumServiceImpl artistasXAlbumService;

    @PostMapping("/relacionar")
    public ResponseEntity<?> relacionarArtistaYAlbum(@RequestBody ArtistasXAlbumDTO artistasXAlbumDTO) {
        try {
            // Llama al método en el servicio para crear la relación
            ArtistasXAlbum nuevaRelacion = artistasXAlbumService.relacionarArtistaYAlbum(artistasXAlbumDTO);

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
