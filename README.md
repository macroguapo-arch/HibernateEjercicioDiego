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

Este código me funciona en clase, en caso de que el mapeo no funcione, cambiar la línea 35 de persistence.xml por:
<property name="hibernate.hbm2ddl.auto" value="create"/>

El código puede funcionar sin datos de ejemplo.

Existe un package deprecated en el que hacía uso de cuatro clases para la creación de un ManyToMany

Package dao contiene el CRUD de la entidad alumno

Package model contiene las clases con las entidades de la BD

META-INF contiene el logback.xml y el pesistence.xml

