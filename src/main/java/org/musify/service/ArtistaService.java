package org.musify.service;

import org.musify.model.Artista;
import org.musify.model.ArtistaDTO;

public interface ArtistaService {
    public Artista getArtista(String nombreArtistico);
    public Artista crearArtista(ArtistaDTO artista);
}
