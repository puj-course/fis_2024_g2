package org.musify.service;


import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;

import java.util.List;

public interface AudioService {
    public Cancion crearCancion(CancionDTO cancionDTO);

    public List<CancionDTO> getCanciones();
}
