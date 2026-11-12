package org.cipfpcheste.dam2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "despacho")
public class Despacho implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero", length = 20,  nullable = false)
    private String numeroDespacho;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profesor_id",
                nullable = false,
                unique = true)
                //es opcional pero lo pongo igual para hacer pruebas
            //foreignKey = @ForeignKey(name = "fk_despacho_profesor"))
    private Profesor profesor;

}

