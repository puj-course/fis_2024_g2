package org.musify.model.artista;

public class ArtistaDTO {
    private String nombreArtistico;
    private String biografia;
    private String usuarioId;
    private String fotoPerfilUrl;

    public ArtistaDTO() {}
    public ArtistaDTO(String nombreArtistico, String biografia, String usuarioId, String fotoPerfilUrl) {
        this.nombreArtistico = nombreArtistico;
        this.biografia = biografia;
        this.usuarioId = usuarioId;
        this.fotoPerfilUrl = fotoPerfilUrl;
    }
    public ArtistaDTO(Artista a) {
        this.nombreArtistico = a.getNombreArtistico();
        this.biografia = a.getBiografia();
        this.usuarioId = a.getIdUsuario();
        this.fotoPerfilUrl = a.getFotoPerfilUrl();
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

    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    public void setFotoPerfilUrl(String fotoPerfilUrl) {
        this.fotoPerfilUrl = fotoPerfilUrl;
    }
}
