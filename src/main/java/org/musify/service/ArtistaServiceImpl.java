package org.musify.service;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.artista.Artista;
import org.musify.model.artista.ArtistaDTO;
import org.musify.model.usuario.Usuario;
import org.musify.repository.ArtistaRepository;
import org.musify.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ArtistaServiceImpl implements ArtistaService{
    @Autowired
    private ArtistaRepository artistaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Artista getArtista(String nombreArtistico) {
        return artistaRepository.findByNombreArtistico(nombreArtistico)
                .orElseThrow(() ->new EntityNotFoundException("Artista no encontrado"));
    }

    @Override
    public Artista crearArtista(ArtistaDTO artista) {
        //Se busca al usuario mediante el id y se asigna al nuevo artista
        Usuario usuario = usuarioRepository.findById(artista.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Artista nuevoArtista = new Artista();
        nuevoArtista.setUsuario(usuario);

        //Se genera Id aleatorio para el artista
        String idAleatorio = UUID.randomUUID().toString();
        nuevoArtista.setIdArtista(idAleatorio.replace("-", ""));
        //Se asigna el resto de atributos
        nuevoArtista.setFechaRegistroArtista(Date.valueOf(LocalDate.now()));
        nuevoArtista.setNombreArtistico(artista.getNombreArtistico());
        nuevoArtista.setBiografia(artista.getBiografia());
        return artistaRepository.save(nuevoArtista);
    }

}
