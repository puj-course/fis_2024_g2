import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.musify.model.album.Album;
import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;
import org.musify.model.cancionesXAlbum.CancionesXAlbum;
import org.musify.model.cancionesXAlbum.CancionesXAlbumDTO;
import org.musify.model.cancionesXAlbum.CancionesXAlbumId;
import org.musify.repository.AlbumRepository;
import org.musify.repository.CancionRepository;
import org.musify.repository.CancionesXAlbumRepository;
import org.musify.service.CancionesXAlbumServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class CancionesXAlbumServiceImplTest {

    @Mock
    private CancionRepository cancionRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private CancionesXAlbumRepository cancionesXAlbumRepository;

    @InjectMocks
    private CancionesXAlbumServiceImpl cancionesXAlbumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRelacionarCancionYAlbum() {
        // Datos de ejemplo
        String cancionId = (UUID.randomUUID().toString().replace("-", ""));
        String albumId = (UUID.randomUUID().toString().replace("-", ""));

        Cancion cancion = new Cancion();
        cancion.setIdCancion(cancionId);

        Album album = new Album();
        album.setIdAlbum(albumId);

        CancionesXAlbumDTO cancionesXAlbumDTO = new CancionesXAlbumDTO();
        cancionesXAlbumDTO.setIdCancion(cancionId);
        cancionesXAlbumDTO.setIdAlbum(albumId);

        // Configuramos el comportamiento de los repositorios simulados
        when(cancionRepository.findById(cancionId)).thenReturn(Optional.of(cancion));
        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));
        when(cancionesXAlbumRepository.save(any(CancionesXAlbum.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutamos el método
        CancionesXAlbum result = cancionesXAlbumService.relacionarCancionYAlbum(cancionesXAlbumDTO);

        // Verificaciones
        assertNotNull(result);
        assertEquals(cancionId, result.getCancion().getIdCancion());
        assertEquals(albumId, result.getAlbum().getIdAlbum());

        // Verifica que se haya guardado correctamente
        verify(cancionesXAlbumRepository, times(1)).save(any(CancionesXAlbum.class));
    }

    @Test
    void testGetCancionesByNombreAlbum() {
        // Datos de ejemplo
        String nombreAlbum = "Album de Ejemplo";
        Cancion cancion = new Cancion();
        cancion.setIdCancion(UUID.randomUUID().toString().replace("-", ""));
        cancion.setNombre("Cancion de Ejemplo");

        // Configuramos el comportamiento del repositorio de CancionesXAlbum
        when(cancionesXAlbumRepository.getCancionesByNombreAlbum(nombreAlbum))
                .thenReturn(Collections.singletonList(cancion));

        // Ejecutamos el método
        List<CancionDTO> cancionesDTO = cancionesXAlbumService.getCancionesByNombreAlbum(nombreAlbum);

        // Verificaciones
        assertNotNull(cancionesDTO);
        assertEquals(1, cancionesDTO.size());
        assertEquals("Cancion de Ejemplo", cancionesDTO.get(0).getNombre());

        // Verifica que se haya llamado al repositorio
        verify(cancionesXAlbumRepository, times(1)).getCancionesByNombreAlbum(nombreAlbum);
    }
}

