package org.musify.service;

import org.musify.model.artista.Artista;
import org.musify.model.artista.ArtistaDTO;

public interface ArtistaService {
    public Artista getArtista(String nombreArtistico);
    public Artista crearArtista(ArtistaDTO artista);
}
