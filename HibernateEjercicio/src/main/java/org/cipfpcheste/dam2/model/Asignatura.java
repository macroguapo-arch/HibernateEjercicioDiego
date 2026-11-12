package org.cipfpcheste.dam2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cipfpcheste.dam2.deprecated.AlumnoAsignaturaOLD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asignatura")
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",length = 100, nullable = false)
    private String nombreAsignatura;

    @ManyToMany(mappedBy = "asignaturas")
    private List<Alumno> alumnos = new ArrayList<>();

    @Override
    public String toString() {
        return "\nID asignatura: " + id +
                "\nNombre asignatura: " + nombreAsignatura;

    }
}
