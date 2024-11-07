import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.musify.model.artista.Artista;
import org.musify.model.artistasXCancion.ArtistasXCancion;
import org.musify.model.artistasXCancion.ArtistasXCancionDTO;
import org.musify.model.artistasXCancion.ArtistasXCancionId;
import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;
import org.musify.repository.ArtistaRepository;
import org.musify.repository.ArtistasXCancionRepository;
import org.musify.repository.CancionRepository;
import org.musify.service.ArtistasXCancionServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArtistasXCancionServiceImplTest {

    @Mock
    private ArtistaRepository artistaRepository;

    @Mock
    private CancionRepository cancionRepository;

    @Mock
    private ArtistasXCancionRepository artistasXCancionRepository;

    @InjectMocks
    private ArtistasXCancionServiceImpl artistasXCancionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRelacionarArtistaYcancion() {
        // Datos de prueba
        String artistaId = "artista123";
        String cancionId = "cancion123";

        ArtistasXCancionDTO artistasXCancionDTO = new ArtistasXCancionDTO();
        artistasXCancionDTO.setIdArtista(artistaId);
        artistasXCancionDTO.setIdCancion(cancionId);

        Artista artista = new Artista();
        artista.setIdArtista(artistaId);

        Cancion cancion = new Cancion();
        cancion.setIdCancion(cancionId);

        ArtistasXCancion artistasXCancion = new ArtistasXCancion();
        artistasXCancion.setArtista(artista);
        artistasXCancion.setCancion(cancion);
        artistasXCancion.setId(new ArtistasXCancionId(cancionId, artistaId));

        // Configuración del comportamiento simulado
        when(artistaRepository.findById(artistaId)).thenReturn(Optional.of(artista));
        when(cancionRepository.findById(cancionId)).thenReturn(Optional.of(cancion));
        when(artistasXCancionRepository.save(any(ArtistasXCancion.class))).thenReturn(artistasXCancion);

        // Llamada al método de prueba
        ArtistasXCancion result = artistasXCancionService.relacionarArtistaYcancion(artistasXCancionDTO);

        // Verificación de resultados
        assertNotNull(result);
        assertEquals(artistaId, result.getId().getArtistaId());
        assertEquals(cancionId, result.getId().getCancionId());
        verify(artistaRepository, times(1)).findById(artistaId);
        verify(cancionRepository, times(1)).findById(cancionId);
        verify(artistasXCancionRepository, times(1)).save(any(ArtistasXCancion.class));
    }

    @Test
    void testRelacionarArtistaYcancion_ArtistaNotFound() {
        // Datos de prueba
        String artistaId = "artista123";
        String cancionId = "cancion123";

        ArtistasXCancionDTO artistasXCancionDTO = new ArtistasXCancionDTO();
        artistasXCancionDTO.setIdArtista(artistaId);
        artistasXCancionDTO.setIdCancion(cancionId);

        // Configuración del comportamiento simulado
        when(artistaRepository.findById(artistaId)).thenReturn(Optional.empty());

        // Verificación de excepción cuando el artista no es encontrado
        assertThrows(RuntimeException.class, () -> artistasXCancionService.relacionarArtistaYcancion(artistasXCancionDTO));
        verify(artistaRepository, times(1)).findById(artistaId);
        verify(cancionRepository, never()).findById(anyString());
        verify(artistasXCancionRepository, never()).save(any(ArtistasXCancion.class));
    }

    @Test
    void testGetCancionesByNombreArtista() {
        // Datos de prueba
        String nombreArtista = "Artista Ejemplo";

        Cancion cancion1 = new Cancion();
        cancion1.setNombre("Cancion 1");
        cancion1.setIdCancion("cancion1");

        Cancion cancion2 = new Cancion();
        cancion2.setNombre("Cancion 2");
        cancion2.setIdCancion("cancion2");

        List<Cancion> canciones = Arrays.asList(cancion1, cancion2);

        // Configuración del comportamiento simulado
        when(artistasXCancionRepository.getCancionesByNombreArtista(nombreArtista)).thenReturn(canciones);

        // Llamada al método de prueba
        List<CancionDTO> result = artistasXCancionService.getCancionesByNombreArtista(nombreArtista);

        // Verificación de resultados
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Cancion 1", result.get(0).getNombre());
        assertEquals("Cancion 2", result.get(1).getNombre());
        verify(artistasXCancionRepository, times(1)).getCancionesByNombreArtista(nombreArtista);
    }
}
