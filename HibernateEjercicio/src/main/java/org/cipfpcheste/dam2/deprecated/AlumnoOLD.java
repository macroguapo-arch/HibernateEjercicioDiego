package org.cipfpcheste.dam2.deprecated;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cipfpcheste.dam2.model.Curso;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "alumno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoOLD implements Serializable {

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
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlumnoAsignaturaOLD> listaAsignaturas;

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombreAlumno='" + nombreAlumno + '\'' +
                ", edadAlumno=" + edadAlumno +
                ", curso=" + curso +
                '}';
    }
}
