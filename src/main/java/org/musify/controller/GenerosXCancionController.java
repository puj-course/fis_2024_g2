package org.musify.controller;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.GenerosXCancion;
import org.musify.model.GenerosXCancionDTO;
import org.musify.service.GenerosXCancionServiceImpl;
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
@RequestMapping("generos_cancion")
public class GenerosXCancionController {

    @Autowired
    GenerosXCancionServiceImpl generosXCancionService;
    @PostMapping("/relacionar")
    public ResponseEntity<?> relacionarGeneroYAlbum(@RequestBody GenerosXCancionDTO generosXCancionDTO) {
        try {
            // Llama al método en el servicio para crear la relación
            GenerosXCancion nuevaRelacion = generosXCancionService.relacionarGeneroYCancion(generosXCancionDTO);

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
