import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.musify.model.cancion.Cancion;
import org.musify.model.cancion.CancionDTO;
import org.musify.repository.CancionRepository;
import org.musify.service.AlbumServiceImpl;
import org.musify.service.CancionServiceImpl;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class CancionServiceImplTest {

    @Mock
    private CancionRepository cancionRepository;

    @InjectMocks
    private CancionServiceImpl cancionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearCancion() {
        CancionDTO cancionDTO = new CancionDTO();
        cancionDTO.setEmpresaDiscograficaId("1");
        cancionDTO.setNombre("yhlqmdlgna");
        cancionDTO.setAudioUrl("http://example.com/audio.mp3");
        cancionDTO.setDuracion(6);
        cancionDTO.setLetra("Letra de ejemplo de la canción");
        cancionDTO.setImagenUrl("http://example.com/image.jpg");
        cancionDTO.setFechaLanzamiento(Date.valueOf("2023-01-01"));

        Cancion cancion = new Cancion();
        cancion.setIdCancion(UUID.randomUUID().toString().replace("-", ""));
        cancion.setEmpresaDiscograficaId(cancionDTO.getEmpresaDiscograficaId());
        cancion.setNombre(cancionDTO.getNombre());
        cancion.setAudioUrl(cancionDTO.getAudioUrl());
        cancion.setDuracion(cancionDTO.getDuracion());
        cancion.setLetra(cancionDTO.getLetra());
        cancion.setImagenUrl(cancionDTO.getImagenUrl());
        cancion.setFechaLanzamiento(cancionDTO.getFechaLanzamiento());

        when(cancionRepository.save(any(Cancion.class))).thenReturn(cancion);

        Cancion result = cancionService.crearCancion(cancionDTO);
        assertNotNull(result);
        assertEquals(cancionDTO.getNombre(), result.getNombre());
        assertEquals(cancionDTO.getFechaLanzamiento(), result.getFechaLanzamiento());
        assertEquals(cancionDTO.getImagenUrl(), result.getImagenUrl());
        verify(cancionRepository, times(1)).save(any(Cancion.class));
    }

    @Test
    void TestGetCancions(){
        Cancion cancion1 = new Cancion();
        cancion1.setEmpresaDiscograficaId("1");
        cancion1.setNombre("cancion1");
        cancion1.setAudioUrl("http://example.com/audio.mp3");
        cancion1.setDuracion(6);
        cancion1.setLetra("Letra de ejemplo de la canción");
        cancion1.setImagenUrl("http://example.com/image.jpg");
        cancion1.setFechaLanzamiento(Date.valueOf("2023-01-01"));

        Cancion cancion2 = new Cancion();
        cancion2.setEmpresaDiscograficaId("1");
        cancion2.setNombre("cancion2");
        cancion2.setAudioUrl("http://example2.com/audio.mp3");
        cancion2.setDuracion(4);
        cancion2.setLetra("Letra de ejemplo de la canción 2");
        cancion2.setImagenUrl("http://example2.com/image.jpg");
        cancion2.setFechaLanzamiento(Date.valueOf("2022-05-12"));

        when(cancionRepository.findAll()).thenReturn(Arrays.asList(cancion1,cancion2));

        List<CancionDTO> result = cancionService.getCanciones();
        assertNotNull(result);
        assertEquals(2,result.size());
        assertEquals("cancion1",result.get(0).getNombre());
        assertEquals("cancion2",result.get(1).getNombre());
        verify(cancionRepository,times(1)).findAll();
    }

}
