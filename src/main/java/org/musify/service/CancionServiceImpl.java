package org.musify.service;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.*;
import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;
import org.musify.repository.CancionRepository;
import org.musify.repository.EmpresaDiscograficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CancionServiceImpl implements  AudioService{
    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private EmpresaDiscograficaRepository empresaDiscograficaRepository;


    @Override
    public Cancion crearCancion(CancionDTO cancionDTO) {
        EmpresaDiscografica empresaDiscografica = empresaDiscograficaRepository.findById(cancionDTO.getEmpresaDiscograficaId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa discográfica no encontrada"));
        Cancion nuevaCancion = new Cancion();
        nuevaCancion.setEmpresaDiscograficaId(cancionDTO.getEmpresaDiscograficaId());
        nuevaCancion.setNombre(cancionDTO.getNombre());
        nuevaCancion.setAudioUrl(cancionDTO.getAudioUrl());
        nuevaCancion.setDuracion(cancionDTO.getDuracion());
        nuevaCancion.setLetra(cancionDTO.getLetra());
        nuevaCancion.setImagenUrl(cancionDTO.getImagenUrl());
        nuevaCancion.setFechaLanzamiento(cancionDTO.getFechaLanzamiento());

        //Datos faltantes
        //Se genera Id aleatorio para la cancion
        String idAleatorio = UUID.randomUUID().toString();
        nuevaCancion.setIdCancion(idAleatorio.replace("-", ""));

        return cancionRepository.save(nuevaCancion);
    }
}
