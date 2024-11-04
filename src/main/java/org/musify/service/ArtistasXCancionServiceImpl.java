package org.musify.service;

import org.musify.model.artista.Artista;
import org.musify.model.artistasXCancion.ArtistasXCancion;
import org.musify.model.artistasXCancion.ArtistasXCancionDTO;
import org.musify.model.artistasXCancion.ArtistasXCancionId;
import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;
import org.musify.repository.ArtistaRepository;
import org.musify.repository.ArtistasXCancionRepository;
import org.musify.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistasXCancionServiceImpl {

    @Autowired
    ArtistaRepository artistaRepository;
    @Autowired
    CancionRepository cancionRepository;
    @Autowired
    ArtistasXCancionRepository artistasXCancionRepository;

    public ArtistasXCancion relacionarArtistaYcancion(ArtistasXCancionDTO artistasXCancionDTO) {
        Artista artista = artistaRepository.findById(artistasXCancionDTO.getIdArtista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        Cancion cancion = cancionRepository.findById(artistasXCancionDTO.getIdCancion())
                .orElseThrow(() -> new RuntimeException("Cancion no encontrada"));
        ArtistasXCancion artistasXCancion = new ArtistasXCancion();
        artistasXCancion.setCancion(cancion);
        artistasXCancion.setArtista(artista);

        //Asigna el id
        ArtistasXCancionId id = new ArtistasXCancionId(artistasXCancionDTO.getIdCancion(), artistasXCancionDTO.getIdArtista());
        artistasXCancion.setId(id);

        return artistasXCancionRepository.save(artistasXCancion);
    }

    public List<CancionDTO> getCancionesByNombreArtista(String nombreArtista) {
        List<Cancion> canciones = artistasXCancionRepository.getCancionesByNombreArtista(nombreArtista);
        // Convertir cada Cancion a CancionDTO
        return canciones.stream()
                .map(CancionDTO::new)
                .collect(Collectors.toList());
    }
}
