package org.cipfpcheste.dam2.deprecated;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cipfpcheste.dam2.model.Asignatura;

import java.io.Serializable;

@Entity
@Table(name = "alumno_asignatura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoAsignaturaOLD implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AlumnoAsignaturaIdOLD id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("alumno_id")
    @JoinColumn(name = "alumno_id")
    private AlumnoOLD alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("asignatura_id")
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;
}

