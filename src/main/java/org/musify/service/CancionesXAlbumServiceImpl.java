package org.musify.service;

import org.musify.model.album.Album;
import org.musify.model.cancion.Cancion;
import org.musify.model.cancionesXAlbum.CancionesXAlbum;
import org.musify.model.cancionesXAlbum.CancionesXAlbumDTO;
import org.musify.model.cancionesXAlbum.CancionesXAlbumId;
import org.musify.repository.AlbumRepository;
import org.musify.repository.CancionRepository;
import org.musify.repository.CancionesXAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
