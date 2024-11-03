package org.musify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empresadiscografica")
public class EmpresaDiscografica {

    @Id
    @Column(name = "id_empresa_discografica", length = 32)
    private String idEmpresaDiscografica;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(name = "fecha_registro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
}
