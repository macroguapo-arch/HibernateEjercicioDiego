package org.cipfpcheste.dam2;

//import org.cipfpcheste.dam2.dao.AlumnoDAO;
import org.cipfpcheste.dam2.dao.AlumnoDAO;
import org.cipfpcheste.dam2.model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /* ENTIDADES MAPEADAS */
// Alumno, Asignatura, Curso, Despacho, Profesor

        /* RELACIONES */
// 1-1 Profesor-Despacho 1-N Alumno-Curso N:M Alumno-Asignaturas

        /* CRUD */
// AlumnoDAO implementa create(añadir)[-r hace que en la creacion se muestre la lista de cursos],
// update(actualizar), delete(eliminar), read(mostrar)[-r muestra listaAsignaturas]

        /* Tarea 1 */
// muestra (sin escribir Alumno ya que solo está implementado para esa entidad)
// Muestra todos los alumnos de la base de datos, añadimos -r mostrará también su lista de asignaturas

        /* Tarea 2 */
// añadir Alumno muestra un asistente para añadir, deja el curso en Null
// si añadimos un -r, nos aparecerá una lista con los cursos existentes
// escribiremos el curso al que queramos que pertenezca, si lo que escribimos
// NO EXISTE dentro de la BD, lo crea (en vez de poner Curso0 lo he creado así)

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("colegioPU");
        EntityManager em = emf.createEntityManager();
        AlumnoDAO alumnoDAO = new AlumnoDAO(em);

            switch (args[0]) {
                case "añadir":
                    System.out.println("=".repeat(50));
                    alumnoDAO.crearAlumno(args);
                    break;
                case "eliminar":
                    System.out.println("=".repeat(50));
                    alumnoDAO.eliminarAlumno();
                    break;
                case "actualizar":
                    System.out.println("=".repeat(50));
                    alumnoDAO.actualizarAlumno(args);
                    break;
                case "mostrar":
                    System.out.println("=".repeat(50));
                    alumnoDAO.mostrarAlumnos(args);
                    break;
                case "salir":
                    System.out.println("=== Saliendo del programa ===");
                    break;
            }
        }
    }
