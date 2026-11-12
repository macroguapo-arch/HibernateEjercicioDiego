package org.cipfpcheste.dam2.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alumno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombreAlumno;

    @Column(name = "edad", nullable = false)
    private Integer edadAlumno;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "curso_id")
    @ToString.Exclude
    private Curso curso;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,})
    @JoinTable(name="alumno_asignatura",
        joinColumns = @JoinColumn(name = "alumno_id"),
        inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private List<Asignatura> asignaturas = new ArrayList<>();


    @Override
    public String toString() {

        String ToString = "\nID alumno: " + id +
                "\nNombre alumno: " + nombreAlumno +
                "\nEdad alumno: " + edadAlumno;
//He puesto esta if porque en caso de que curso sea null al intentar hacer el toString fallaba y paraba la transacción
        if (curso != null) {
            ToString += "\nCurso: "+curso;
        }

        return ToString;

    }

}
