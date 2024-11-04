package org.musify.controller;

import org.musify.model.album.AlbumDTO;
import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;
import org.musify.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cancion")
public class CancionController {
    @Autowired
    private AudioService audioService;

    @PostMapping("/crearCancion")
    public ResponseEntity<?> postCancion(@RequestBody CancionDTO cancionDTO) {
        try {
            // Llama al método en service
            Cancion nuevaCancion =  audioService.crearCancion(cancionDTO);

            // Crear la URI para retornar la ubicación del nuevo cancion
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevaCancion.getIdCancion())
                    .toUri();

            return ResponseEntity.created(location).body(nuevaCancion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la cancion: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CancionDTO>> getCanciones() {
        List<CancionDTO> canciones = audioService.getCanciones();
        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(canciones);
    }
}
