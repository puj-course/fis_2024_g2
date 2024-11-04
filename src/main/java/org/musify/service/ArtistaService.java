package org.musify.service;

import org.musify.model.artista.Artista;
import org.musify.model.artista.ArtistaDTO;

import java.util.List;

public interface ArtistaService {
    public Artista getArtista(String nombreArtistico);
    public Artista crearArtista(ArtistaDTO artista);
    public List<ArtistaDTO> getArtistas();
}
