package org.musify.controller;


import org.musify.model.album.Album;
import org.musify.model.album.AlbumDTO;
import org.musify.service.AlbumServiceImpl;
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
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumServiceImpl albumService;

    @PostMapping("/crearAlbum")
    public ResponseEntity<?> postAlbum(@RequestBody AlbumDTO albumDTO) {
        try {
            // Llama al método en service
            Album nuevoAlbum =  albumService.crearAlbum(albumDTO);

            // Crear la URI para retornar la ubicación del nuevo album
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevoAlbum.getIdAlbum())
                    .toUri();

            return ResponseEntity.created(location).body(nuevoAlbum);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el album: " + e.getMessage());
        }
    }

}
