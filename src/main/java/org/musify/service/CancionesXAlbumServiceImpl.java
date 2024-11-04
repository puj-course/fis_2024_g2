package org.musify.service;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.album.Album;
import org.musify.model.artista.Artista;
import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;
import org.musify.model.cancionesXAlbum.CancionesXAlbum;
import org.musify.model.cancionesXAlbum.CancionesXAlbumDTO;
import org.musify.model.cancionesXAlbum.CancionesXAlbumId;
import org.musify.repository.AlbumRepository;
import org.musify.repository.CancionRepository;
import org.musify.repository.CancionesXAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CancionesXAlbumServiceImpl {
    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CancionesXAlbumRepository cancionesXAlbumRepository;

    public CancionesXAlbum relacionarCancionYAlbum(CancionesXAlbumDTO cancionesXAlbumDTO) {
        // Crear los objetos Cancion y Album (asegurarse de que existen en la base de datos)
        Cancion cancion = cancionRepository.findById(cancionesXAlbumDTO.getIdCancion())
                .orElseThrow(() -> new RuntimeException("Cancion no encontrada"));

        Album album = albumRepository.findById(cancionesXAlbumDTO.getIdAlbum())
                .orElseThrow(() -> new RuntimeException("Album no encontrado"));
        CancionesXAlbum cancionesXAlbum = new CancionesXAlbum();
        cancionesXAlbum.setCancion(cancion);
        cancionesXAlbum.setAlbum(album);

        //Asigna el id segun la cancion y el album
        CancionesXAlbumId id = new CancionesXAlbumId(cancionesXAlbumDTO.getIdCancion(), cancionesXAlbumDTO.getIdAlbum());
        cancionesXAlbum.setId(id);

        return cancionesXAlbumRepository.save(cancionesXAlbum);
    }

    public List<CancionDTO> getCancionesByNombreAlbum(String nombreAlbum) {
        List<Cancion> canciones = cancionesXAlbumRepository.getCancionesByNombreAlbum(nombreAlbum);
        // Convertir cada Cancion a CancionDTO
        return canciones.stream()
                .map(CancionDTO::new)
                .collect(Collectors.toList());
    }


}
