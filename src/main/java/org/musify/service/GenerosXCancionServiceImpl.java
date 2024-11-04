package org.musify.service;

import org.musify.model.GeneroMusical;
import org.musify.model.generosXCancion.GenerosXCancion;
import org.musify.model.generosXCancion.GenerosXCancionDTO;
import org.musify.model.generosXCancion.GenerosXCancionId;
import org.musify.model.cancion.Cancion;
import org.musify.repository.CancionRepository;
import org.musify.repository.GeneroRepository;
import org.musify.repository.GenerosXCancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerosXCancionServiceImpl {
    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private CancionRepository cancionRepository;
    @Autowired
    private GenerosXCancionRepository generosXCancionRepository;

    public GenerosXCancion relacionarGeneroYCancion(GenerosXCancionDTO generosXCancionDTO) {
        GeneroMusical generoMusical = generoRepository.findById(generosXCancionDTO.getIdGenero())
                .orElseThrow(() -> new RuntimeException("Genero no encontrado"));

        Cancion cancion = cancionRepository.findById(generosXCancionDTO.getIdCancion())
                .orElseThrow(() -> new RuntimeException("Cancion no encontrada"));
        GenerosXCancion generosXCancion = new GenerosXCancion();
        generosXCancion.setCancion(cancion);
        generosXCancion.setGeneroMusical(generoMusical);

        //Asigna el id
        GenerosXCancionId id = new GenerosXCancionId(generosXCancionDTO.getIdCancion(), generosXCancionDTO.getIdGenero());
        generosXCancion.setId(id);

        return generosXCancionRepository.save(generosXCancion);
    }
}
