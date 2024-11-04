package org.musify.service;


import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;

public interface AudioService {
    public Cancion crearCancion(CancionDTO cancionDTO);
}
