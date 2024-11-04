package org.musify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name ="genero")
public class GeneroMusical {
    @Id
    @Column(name = "id_genero", length = 32)
    private String idGenero;

    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;
}
