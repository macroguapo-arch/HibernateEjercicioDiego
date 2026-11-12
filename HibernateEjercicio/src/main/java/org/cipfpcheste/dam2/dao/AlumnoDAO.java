package org.cipfpcheste.dam2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.cipfpcheste.dam2.model.Alumno;
import org.cipfpcheste.dam2.model.Asignatura;
import org.cipfpcheste.dam2.model.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AlumnoDAO {

    private final EntityManager em;

    public AlumnoDAO(EntityManager em) {
        this.em = em;
    }

    public void crearAlumno(String[] comando) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Alumno alumno = new Alumno();
            rellenarDatos(alumno,comando);
            em.persist(alumno);


            tx.commit();
            System.out.println("Alumno añadido correctamente. . .");

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error:" + e.getMessage());
        }
    }

    public void mostrarAlumnos(String[] comando) {
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
            if (!alumnos.isEmpty()) {
                System.out.println("Mostrando lista de alumnos...");
                for (Alumno a : alumnos) {
                    System.out.println(a.toString());
                    if (comando.length > 1 && (comando[1].equals("-r") || (comando.length > 2 && comando[2].equals("-r")))) {
                        System.out.println("   Asignaturas: " + a.getAsignaturas().toString());
                    }
                }
            } else {
                System.out.println("No existen usuarios actualmente.");
            }

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void rellenarDatos(Alumno alumno, String[] comando) {
        Scanner sc = new Scanner(System.in);
        // Inicializamos un alumno en vacío para ir añadiéndole datos
        System.out.println("Introduce el nombre del Alumno: ");
        alumno.setNombreAlumno(sc.nextLine());

        System.out.println("Introduce la edad del Alumno: ");
        alumno.setEdadAlumno(sc.nextInt());
        sc.nextLine();

        // Obtenemos una lista de la BD con los nombres de las asignaturas ya existentes para poder saber las que ya existen y en caso de no existir la creamos
        List<Curso> nombreCurso = em.createQuery("SELECT  a FROM Curso a", Curso.class).getResultList();

        if (comando.length > 1 && (comando[1].equals("-r") || (comando.length > 2 && comando[2].equals("-r")))) {
            // Meter en un If para que solo salga si el usuario pasa por java -jar un añadir -r
            System.out.println("Introduce el curso del Alumno: ");

            // Hacemos un stream con la lista anterior para obtener los nombres de las asignaturas con un foreach sout
            nombreCurso.stream()
                    .map(Curso::getNombreCurso)
                    .distinct()
                    .forEach(nombre -> System.out.print(" - " + nombre));
            System.out.println();

            String cursoInput = sc.nextLine();


            // Generamos un objeto curso para buscar si el input del usuario aparece en la lista anterior, en caso de que no, lo añadiremos
            Curso cursoSeleccionado = nombreCurso.stream()
                    .filter(c -> c.getNombreCurso().equalsIgnoreCase(cursoInput))
                    .findFirst()
                    .orElseGet(() -> {
                        Curso nuevoCurso = new Curso();
                        nuevoCurso.setNombreCurso(cursoInput);
                        em.persist(nuevoCurso);
                        return nuevoCurso;
                    });

            alumno.setCurso(cursoSeleccionado);
        }
        //Mismo proceso que antes pero un alumno puede tener muchas asignaturas y una asignatura muchos alumnos
        System.out.println("Introduce las asignaturas del Alumno " + "(pulsa Enter en vacío para dejar de añadir)");

        //Generamos dos listas, una para las asignaturas que añadiremos al alumno y otra para obtener los nombres de las asignaturas de la BD
        List<Asignatura> asignaturasAnyadir = new ArrayList<>();
        List<Asignatura> nombreAsignaturas = em.createQuery("SELECT a FROM Asignatura a", Asignatura.class).getResultList();

        //Creamos un stream para obtener los nombres con un foreachsout
        nombreAsignaturas.stream()
                .map(Asignatura::getNombreAsignatura)
                .distinct()
                .forEach(nombre -> System.out.print(" - " + nombre));
        System.out.println();
        //Variable string para poder ir añadiéndole los valores que el usuario le pase y comprobar si están el la BD o no
        String asignaturaInput;

        while (true) {
            asignaturaInput = sc.nextLine().trim();
            //En caso de estar en vacío quiere decir que el usuario ya ha introducido todas las asignaturas que quería y salimos del bucle
            if (asignaturaInput.isEmpty()) {
                break;
            }


            String finalAsignatura = asignaturaInput;

            //Generamos un objeto asignatura para obtener su si nombre se encuentra dentro de la BD, en caso de que no lo crearemos
            Asignatura asignaturaSeleccionada = nombreAsignaturas.stream()
                    .filter(a -> a.getNombreAsignatura().equalsIgnoreCase(finalAsignatura))
                    .findFirst()
                    .orElseGet(() -> {
                        Asignatura nuevaAsignatura = new Asignatura();
                        nuevaAsignatura.setNombreAsignatura(finalAsignatura);
                        em.persist(nuevaAsignatura);
                        return nuevaAsignatura;
                    });
            //Una vez terminada la comprobación, lo añadirmos a la lista de asignaturas que añadir al usuario y a la propia lista del nuevo objeto asignaturaSeleccionada, ya que
            //ambas tienen una lista con alumnos que pertecenen a y asignaturas que pertenecen a
            asignaturasAnyadir.add(asignaturaSeleccionada);
            asignaturaSeleccionada.getAlumnos().add(alumno);
        }
        //Una vez salido del bucle, le añadimos la lista de asignaturas y persistimos el alumno para añadirlo
        alumno.setAsignaturas(asignaturasAnyadir);
    }

    public void actualizarAlumno(String[] comando){
        EntityTransaction tx = em.getTransaction();
        Scanner sc = new Scanner(System.in);

        try {

            tx.begin();


            List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();

            if (!alumnos.isEmpty()) {
                System.out.println("Mostrando lista de alumnos...");
                for (Alumno a : alumnos) {
                    System.out.println("- " + a.getNombreAlumno());
                }

                String alumnoSeleccionado = sc.nextLine();

                Alumno buscarAlumno = alumnos.stream()
                        .filter(a -> a.getNombreAlumno().equalsIgnoreCase(alumnoSeleccionado))
                        .findFirst()
                        .orElse(null);


                if (buscarAlumno == null) {
                    System.out.println("Alumno no encontrado");
                    tx.rollback();
                    return;
                }


                rellenarDatos(buscarAlumno, comando);

            tx.commit();
            System.out.println("Alumno actualizado correctamente");
            } else {
                System.out.println("No existen alumnos actualmente.");
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error:"+e.getMessage() );
        }

    }

    public void eliminarAlumno(){
        EntityTransaction tx = em.getTransaction();
        try {

            Scanner sc = new Scanner(System.in);
            tx.begin();


            List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();

            if (!alumnos.isEmpty()) {
                System.out.println("Mostrando lista de alumnos...");
                for (Alumno a : alumnos) {
                    System.out.println("- " + a.getNombreAlumno());
                }

                String alumnoSeleccionado = sc.nextLine();

                Alumno buscarAlumno = alumnos.stream()
                        .filter(a -> a.getNombreAlumno().equalsIgnoreCase(alumnoSeleccionado))
                        .findFirst()
                        .orElse(null);

                if (buscarAlumno == null) {
                    System.out.println("Alumno no encontrado. ");
                    return;
                }

                em.remove(buscarAlumno);
                tx.commit();
                System.out.println("Alumno: " + buscarAlumno.getNombreAlumno() + " con id: " + buscarAlumno.getId() + " eliminado correctamente.");
            } else {
                System.out.println("No existen alumnos actualmente.");
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error:"+e.getMessage() );
        }

    }
    }
