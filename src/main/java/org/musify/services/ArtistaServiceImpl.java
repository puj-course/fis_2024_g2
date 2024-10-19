package org.musify.service;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.Artista;
import org.musify.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaServiceImpl implements ArtistaService{
    @Autowired
    private ArtistaRepository artistaRepository;

    @Override
    public Artista getArtista(String nombreArtistico) {
        return artistaRepository.findByNombreArtistico(nombreArtistico)
                .orElseThrow(() ->new EntityNotFoundException("Artista no encontrado"));
    }
}
