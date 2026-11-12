package org.cipfpcheste.dam2.deprecated;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoAsignaturaIdOLD implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "alumno_id")
    private Long alumno_id;

    @Column(name = "asignatura_id")
    private Long asignatura_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoAsignaturaIdOLD that = (AlumnoAsignaturaIdOLD) o;
        return Objects.equals(alumno_id, that.alumno_id) && Objects.equals(asignatura_id, that.asignatura_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno_id, asignatura_id);
    }
}
