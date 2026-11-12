# Proyecto de Acceso a Datos - Hibernate

## Entidades Mapeadas
* Alumno
* Asignatura
* Curso
* Despacho
* Profesor

## Relaciones de la Base de Datos
* **1:1** Profesor - Despacho
* **1:N** Alumno - Curso
* **N:M** Alumno - Asignaturas

## Operaciones CRUD (AlumnoDAO)
El paquete `dao` implementa el mantenimiento completo para la entidad Alumno:
* **Create (añadir):** Permite registrar un nuevo alumno. Si se ejecuta con el parámetro `-r`, muestra la lista de cursos disponibles durante el proceso.
* **Read (mostrar):** Muestra los registros. Si se añade `-r`, muestra también la lista de asignaturas asociadas.
* **Update (actualizar):** Modifica los datos existentes de un alumno.
* **Delete (eliminar):** Da de baja un registro de alumno.

## Instrucciones de Uso y Comandos

### Tarea 1: Visualización
* Ejecutando el comando de muestra (sin necesidad de especificar la entidad, ya que está implementado para Alumno):
  Muestra todos los alumnos de la base de datos. Al añadir el modificador `-r`, se mostrará también la lista de sus asignaturas correspondientes.

### Tarea 2: Inserción Interactiva
* Ejecutando `añadir Alumno`: Muestra un asistente por consola para registrar los datos básicos dejando el curso en `null`.
* Ejecutando `añadir -r`: Despliega una lista con los cursos existentes. El usuario introduce el nombre del curso deseado; si el curso no existe en la base de datos, el sistema lo crea automáticamente y lo asigna.

## Notas de Configuración y Ejecución
* El programa funciona correctamente con los datos de prueba provistos.
* En caso de incidencias con el mapeo o la persistencia al iniciar la aplicación, asegúrate de configurar la siguiente propiedad en la línea 35 del archivo `persistence.xml`:

```xml
<property name="hibernate.hbm2ddl.auto" value="create"/>
