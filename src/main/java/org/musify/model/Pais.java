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
@Table(name ="pais")
public class Pais {

    @Id
    @Column(name = "id_pais", length = 32)
    private String idPais;

    @Column(nullable = false)
    private String nombre;

}
