import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.musify.model.artista.Artista;
import org.musify.model.artista.ArtistaDTO;
import org.musify.repository.ArtistaRepository;
import org.musify.repository.UsuarioRepository;
import org.musify.service.ArtistaServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArtistaServiceImplTest {

    @Mock
    private ArtistaRepository artistaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ArtistaServiceImpl artistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArtista() {
        // Datos de prueba
        String nombreArtistico = "Artista Ejemplo";
        Artista artista = new Artista();
        artista.setNombreArtistico(nombreArtistico);

        // Configuración del comportamiento simulado
        when(artistaRepository.findByNombreArtistico(nombreArtistico)).thenReturn(Optional.of(artista));

        // Llamada al método de prueba
        Artista result = artistaService.getArtista(nombreArtistico);

        // Verificación de resultados
        assertNotNull(result);
        assertEquals(nombreArtistico, result.getNombreArtistico());
        verify(artistaRepository, times(1)).findByNombreArtistico(nombreArtistico);
    }

    @Test
    void testGetArtista_NotFound() {
        // Datos de prueba
        String nombreArtistico = "Artista No Existente";

        // Configuración del comportamiento simulado
        when(artistaRepository.findByNombreArtistico(nombreArtistico)).thenReturn(Optional.empty());

        // Verificación de excepción cuando el artista no es encontrado
        assertThrows(EntityNotFoundException.class, () -> artistaService.getArtista(nombreArtistico));
        verify(artistaRepository, times(1)).findByNombreArtistico(nombreArtistico);
    }

    @Test
    void testCrearArtista() {
        // Datos de prueba para ArtistaDTO
        ArtistaDTO artistaDTO = new ArtistaDTO();
        artistaDTO.setNombreArtistico("Nuevo Artista");
        artistaDTO.setBiografia("Biografía del nuevo artista");

        Artista nuevoArtista = new Artista();
        nuevoArtista.setIdArtista(UUID.randomUUID().toString());
        nuevoArtista.setNombreArtistico(artistaDTO.getNombreArtistico());
        nuevoArtista.setBiografia(artistaDTO.getBiografia());
        nuevoArtista.setFechaRegistroArtista(Date.valueOf(LocalDate.now()));

        // Configuración del comportamiento simulado
        when(artistaRepository.save(any(Artista.class))).thenReturn(nuevoArtista);

        // Llamada al método de prueba
        Artista result = artistaService.crearArtista(artistaDTO);

        // Verificación de resultados
        assertNotNull(result);
        assertEquals(artistaDTO.getNombreArtistico(), result.getNombreArtistico());
        assertEquals(artistaDTO.getBiografia(), result.getBiografia());
        assertNotNull(result.getIdArtista());
        assertNotNull(result.getFechaRegistroArtista());

        // Verificar que el repositorio de artista fue llamado una vez para guardar
        verify(artistaRepository, times(1)).save(any(Artista.class));
    }

}
