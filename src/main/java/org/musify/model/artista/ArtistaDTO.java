package org.musify.model.artista;

public class ArtistaDTO {
    private String nombreArtistico;
    private String biografia;
    private String usuarioId;

    public ArtistaDTO() {}
    public ArtistaDTO(String nombreArtistico, String biografia, String usuarioId) {
        this.nombreArtistico = nombreArtistico;
        this.biografia = biografia;
        this.usuarioId = usuarioId;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
