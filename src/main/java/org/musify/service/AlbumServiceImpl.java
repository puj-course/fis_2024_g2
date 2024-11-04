package org.musify.service;

import org.musify.model.album.Album;
import org.musify.model.album.AlbumDTO;
import org.musify.model.artista.ArtistaDTO;
import org.musify.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl {


    @Autowired
    private AlbumRepository albumRepository;

    public Album crearAlbum(AlbumDTO albumDTO) {
        Album nuevoAlbum = new Album();
        nuevoAlbum.setNombre(albumDTO.getNombre());
        nuevoAlbum.setFechaLanzamiento(albumDTO.getFechaLanzamiento());
        nuevoAlbum.setImagenUrl(albumDTO.getImagenUrl());
        //Datos faltantes
        //Se genera Id aleatorio para el album
        String idAleatorio = UUID.randomUUID().toString();
        nuevoAlbum.setIdAlbum(idAleatorio.replace("-", ""));

        return albumRepository.save(nuevoAlbum);
    }

    public List<AlbumDTO> getAlbumes() {
        return albumRepository.findAll()
                .stream()
                .map(AlbumDTO::new)
                .collect(Collectors.toList());
    }
}
