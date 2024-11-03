package org.musify.service;


import org.musify.model.Cancion;
import org.musify.model.CancionDTO;

public interface AudioService {
    public Cancion crearCancion(CancionDTO cancionDTO);
}
