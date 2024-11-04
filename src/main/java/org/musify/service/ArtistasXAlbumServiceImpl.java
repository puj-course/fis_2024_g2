package org.musify.service;

import org.musify.model.album.Album;
import org.musify.model.artista.Artista;
import org.musify.model.artistasXAlbum.ArtistasXAlbum;
import org.musify.model.artistasXAlbum.ArtistasXAlbumDTO;
import org.musify.model.artistasXAlbum.ArtistasXAlbumId;
import org.musify.repository.AlbumRepository;
import org.musify.repository.ArtistaRepository;
import org.musify.repository.ArtistasXAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistasXAlbumServiceImpl {
    @Autowired
    private ArtistasXAlbumRepository artistasXAlbumRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistaRepository artistaRepository;

    public ArtistasXAlbum relacionarArtistaYAlbum(ArtistasXAlbumDTO artistasXAlbumDTO) {
        Artista artista = artistaRepository.findById(artistasXAlbumDTO.getIdArtista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        Album album = albumRepository.findById(artistasXAlbumDTO.getIdAlbum())
                .orElseThrow(() -> new RuntimeException("Album no encontrado"));
        ArtistasXAlbum artistasXAlbum = new ArtistasXAlbum();
        artistasXAlbum.setAlbum(album);
        artistasXAlbum.setArtista(artista);

        //Asigna el id
        ArtistasXAlbumId id = new ArtistasXAlbumId(artistasXAlbumDTO.getIdAlbum(), artistasXAlbumDTO.getIdArtista());
        artistasXAlbum.setId(id);

        return artistasXAlbumRepository.save(artistasXAlbum);
    }
}
