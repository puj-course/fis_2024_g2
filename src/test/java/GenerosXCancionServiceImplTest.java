package org.musify.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.musify.model.GeneroMusical;
import org.musify.model.generosXCancion.GenerosXCancion;
import org.musify.model.generosXCancion.GenerosXCancionDTO;
import org.musify.model.generosXCancion.GenerosXCancionId;
import org.musify.model.cancion.Cancion;
import org.musify.repository.CancionRepository;
import org.musify.repository.GeneroRepository;
import org.musify.repository.GenerosXCancionRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenerosXCancionServiceImplTest {

    @Mock
    private GeneroRepository generoRepository;

    @Mock
    private CancionRepository cancionRepository;

    @Mock
    private GenerosXCancionRepository generosXCancionRepository;

    @InjectMocks
    private GenerosXCancionServiceImpl generosXCancionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRelacionarGeneroYCancionSuccess() {
        // Datos de prueba
        String idGenero = "1";
        String idCancion = "2";
        GenerosXCancionDTO generosXCancionDTO = new GenerosXCancionDTO(idGenero, idCancion);

        // Simulación de entidades
        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setIdGenero(idGenero);

        Cancion cancion = new Cancion();
        cancion.setIdCancion(idCancion);

        // Simulación de repositorios
        when(generoRepository.findById(idGenero)).thenReturn(Optional.of(generoMusical));
        when(cancionRepository.findById(idCancion)).thenReturn(Optional.of(cancion));

        GenerosXCancion generosXCancion = new GenerosXCancion();
        GenerosXCancionId generosXCancionId = new GenerosXCancionId(idCancion, idGenero);
        generosXCancion.setId(generosXCancionId);
        generosXCancion.setGeneroMusical(generoMusical);
        generosXCancion.setCancion(cancion);

        when(generosXCancionRepository.save(any(GenerosXCancion.class))).thenReturn(generosXCancion);

        // Ejecución del método
        GenerosXCancion result = generosXCancionService.relacionarGeneroYCancion(generosXCancionDTO);

        // Verificaciones
        assertNotNull(result);
        assertEquals(idGenero, result.getGeneroMusical().getIdGenero());
        assertEquals(idCancion, result.getCancion().getIdCancion());
        assertEquals(generosXCancionId, result.getId());

        verify(generoRepository, times(1)).findById(idGenero);
        verify(cancionRepository, times(1)).findById(idCancion);
        verify(generosXCancionRepository, times(1)).save(any(GenerosXCancion.class));
    }

    @Test
    void testRelacionarGeneroYCancionGeneroNotFound() {
        String idGenero = "1";
        String idCancion = "2";
        GenerosXCancionDTO generosXCancionDTO = new GenerosXCancionDTO(idGenero, idCancion);

        when(generoRepository.findById(idGenero)).thenReturn(Optional.empty());

        // Verificación de la excepción lanzada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            generosXCancionService.relacionarGeneroYCancion(generosXCancionDTO);
        });

        assertEquals("Genero no encontrado", exception.getMessage());
        verify(generoRepository, times(1)).findById(idGenero);
        verify(cancionRepository, never()).findById(anyString());
        verify(generosXCancionRepository, never()).save(any(GenerosXCancion.class));
    }

    @Test
    void testRelacionarGeneroYCancionCancionNotFound() {
        String idGenero = "1";
        String idCancion = "2";
        GenerosXCancionDTO generosXCancionDTO = new GenerosXCancionDTO(idGenero, idCancion);

        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setIdGenero(idGenero);

        when(generoRepository.findById(idGenero)).thenReturn(Optional.of(generoMusical));
        when(cancionRepository.findById(idCancion)).thenReturn(Optional.empty());

        // Verificación de la excepción lanzada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            generosXCancionService.relacionarGeneroYCancion(generosXCancionDTO);
        });

        assertEquals("Cancion no encontrada", exception.getMessage());
        verify(generoRepository, times(1)).findById(idGenero);
        verify(cancionRepository, times(1)).findById(idCancion);
        verify(generosXCancionRepository, never()).save(any(GenerosXCancion.class));
    }
}
