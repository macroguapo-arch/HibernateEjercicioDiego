DROP SCHEMA IF EXISTS colegio;

CREATE SCHEMA IF NOT EXISTS colegio;

USE colegio;

-- ==========================================
-- CREACIÓN DE TABLAS
-- ==========================================

-- Entidad: curso (Parte de la relación 1-M con alumno)
CREATE TABLE curso (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- Entidad: alumno (Relación 1-M con curso, y N:M con asignatura)
CREATE TABLE alumno (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    curso_id BIGINT,
    CONSTRAINT fk_alumno_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
) ENGINE=InnoDB;

-- Entidad: profesor (Parte de la relación 1-1 con despacho)
CREATE TABLE profesor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- Entidad: despacho (Relación 1-1 con profesor mediante UNIQUE)
CREATE TABLE despacho (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(20) NOT NULL,
    profesor_id BIGINT UNIQUE,
    CONSTRAINT fk_despacho_profesor FOREIGN KEY (profesor_id) REFERENCES profesor(id)
) ENGINE=InnoDB;

-- Entidad: asignatura (Parte de la relación N:M con alumno)
CREATE TABLE asignatura (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- Tabla intermedia para la relación N:M entre alumno y asignatura
CREATE TABLE alumno_asignatura (
    alumno_id BIGINT,
    asignatura_id BIGINT,
    PRIMARY KEY (alumno_id, asignatura_id),
    CONSTRAINT fk_aa_alumno FOREIGN KEY (alumno_id) REFERENCES alumno(id),
    CONSTRAINT fk_aa_asignatura FOREIGN KEY (asignatura_id) REFERENCES asignatura(id)
) ENGINE=InnoDB;


-- ==========================================
-- INSERCIÓN DE DATOS DE EJEMPLO
-- ==========================================

-- 1. Insertar Cursos
INSERT INTO curso (id, nombre) VALUES
(1, '2º DAM'),
(2, '1º DAM'),
(3, '2º ASIR');

-- 2. Insertar Asignaturas
INSERT INTO asignatura (id, nombre) VALUES
(1, 'Acceso a Datos'),
(2, 'Programación de Servicios y Procesos'),
(3, 'Sistemas de Gestión Empresarial'),
(4, 'Bases de Datos');

-- 3. Insertar Profesores y Despachos (Relación 1-1)
INSERT INTO profesor (id, nombre) VALUES
(1, 'Carlos G. M.'),
(2, 'Ana Belén R.'),
(3, 'José Manuel P.');

INSERT INTO despacho (id, numero, profesor_id) VALUES
(1, 'Despacho 101', 1),
(2, 'Despacho 102', 2),
(3, 'Despacho 103', 3);

-- 4. Insertar Alumnos (Relación 1-M con curso)
INSERT INTO alumno (id, nombre, edad, curso_id) VALUES
(1, 'Diego Zafra', 30, 1),
(2, 'Carol Pérez', 28, 1),
(3, 'Izan Gómez', 22, 2),
(4, 'María López', 25, 3);

-- 5. Insertar Relaciones N:M (Alumno - Asignatura)
INSERT INTO alumno_asignatura (alumno_id, asignatura_id) VALUES
(1, 1), -- Diego en Acceso a Datos
(1, 2), -- Diego en PSP
(1, 3), -- Diego en SGE
(2, 1), -- Carol en Acceso a Datos
(2, 4), -- Carol en Bases de Datos
(3, 4), -- Izan en Bases de Datos
(4, 2); -- María en PSP