package org.musify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CancionDTO {
    private String nombre;
    private Integer duracion;
    private Date fechaLanzamiento;
    private String letra;
    private String audioUrl;
    private String imagenUrl;
    private String empresaDiscograficaId;
}
