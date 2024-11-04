package org.musify.controller;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.artista.Artista;
import org.musify.model.artista.ArtistaDTO;
import org.musify.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/{nombreArtistico}")
    public ResponseEntity<?> getArtista(@PathVariable String nombreArtistico) {
        try{
            Artista artista = artistaService.getArtista(nombreArtistico);
            if (artista == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
            }
            return ResponseEntity.ok().body(artista);

        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }

    }

    @PostMapping("/crearArtista")
    public ResponseEntity<?> postArtista(@RequestBody ArtistaDTO a) {
        try {
            // Llama al método en service
            Artista nuevoArtista =  artistaService.crearArtista(a);

            // Crear la URI para retornar la ubicación del nuevo artista
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{nombreArtistico}")
                    .buildAndExpand(nuevoArtista.getNombreArtistico())
                    .toUri();

            return ResponseEntity.created(location).body(nuevoArtista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el artista: " + e.getMessage());
        }
    }


}
