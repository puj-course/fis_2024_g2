import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.musify.model.album.Album;
import org.musify.model.album.AlbumDTO;
import org.musify.repository.AlbumRepository;
import org.musify.service.AlbumServiceImpl;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlbumServiceImplTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearAlbum() {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setNombre("Test Album");
        albumDTO.setFechaLanzamiento(Date.valueOf("2023-01-01"));
        albumDTO.setImagenUrl("http://example.com/image.jpg");

        Album album = new Album();
        album.setIdAlbum(UUID.randomUUID().toString().replace("-", ""));
        album.setNombre(albumDTO.getNombre());
        album.setFechaLanzamiento(albumDTO.getFechaLanzamiento());
        album.setImagenUrl(albumDTO.getImagenUrl());

        when(albumRepository.save(any(Album.class))).thenReturn(album);

        Album result = albumService.crearAlbum(albumDTO);

        assertNotNull(result);
        assertEquals(albumDTO.getNombre(), result.getNombre());
        assertEquals(albumDTO.getFechaLanzamiento(), result.getFechaLanzamiento());
        assertEquals(albumDTO.getImagenUrl(), result.getImagenUrl());
        verify(albumRepository, times(1)).save(any(Album.class));
    }

    @Test
    void testGetAlbumes() {
        Album album1 = new Album();
        album1.setIdAlbum(UUID.randomUUID().toString().replace("-", ""));
        album1.setNombre("Album 1");
        album1.setFechaLanzamiento(Date.valueOf("2023-01-01"));
        album1.setImagenUrl("http://example.com/image1.jpg");

        Album album2 = new Album();
        album2.setIdAlbum(UUID.randomUUID().toString().replace("-", ""));
        album2.setNombre("Album 2");
        album2.setFechaLanzamiento(Date.valueOf("2023-02-01"));
        album2.setImagenUrl("http://example.com/image2.jpg");

        when(albumRepository.findAll()).thenReturn(Arrays.asList(album1, album2));

        List<AlbumDTO> result = albumService.getAlbumes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Album 1", result.get(0).getNombre());
        assertEquals("Album 2", result.get(1).getNombre());
        verify(albumRepository, times(1)).findAll();
    }
}