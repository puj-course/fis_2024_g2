package org.musify.controller;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.Artista;
import org.musify.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
