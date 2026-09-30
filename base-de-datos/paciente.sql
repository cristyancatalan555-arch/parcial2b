-- =====================================================================
-- UNIVERSIDAD MARIANO GALVEZ DE GUATEMALA - FACULTAD DE INGENIERIA
-- Programacion II - EXAMEN PARCIAL II
-- Script de base de datos (ENTREGADO POR EL CATEDRATICO - NO MODIFICAR)
-- =====================================================================

DROP DATABASE IF EXISTS parcial2b;
CREATE DATABASE parcial2b CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE parcial2b;

CREATE TABLE paciente (
    ID_PACIENTE INT          NOT NULL AUTO_INCREMENT,
    ESTADO      BOOLEAN      NOT NULL DEFAULT TRUE,
    NOMBRE      VARCHAR(65)  NOT NULL,
    DPI         VARCHAR(13)  NULL,
    TELEFONO    VARCHAR(15)  NULL,
    DIRECCION   VARCHAR(100) NULL,
    PRIMARY KEY (ID_PACIENTE)
);

-- Datos de prueba
INSERT INTO paciente (ESTADO, NOMBRE, DPI, TELEFONO, DIRECCION) VALUES
(TRUE,  'Ana Lucia Ramirez Lopez',  '2451889760101', '55412200', '5a calle 3-20 zona 1, Huehuetenango'),
(TRUE,  'Carlos Estuardo Mendez',   '1998342570902', '55738410', '2a avenida 10-15 zona 3, Huehuetenango'),
(TRUE,  'Maria Jose Herrera Gomez', '3102775480103', '42096655', 'Calzada Kaibil Balam 7-40, Huehuetenango'),
(TRUE,  'Luis Fernando Aguilar',    '2760334910905', '51230098', 'Aldea Chiantla Viejo, Huehuetenango'),
(FALSE, 'Paciente Anulado Prueba',  '1111111110101', '00000000', '1a calle 1-01 zona 2, Huehuetenango');

SELECT * FROM paciente;
