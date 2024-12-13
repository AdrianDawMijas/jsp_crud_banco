-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS `clientesBanco` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `clientesBanco`;

-- Crear la tabla cliente
CREATE TABLE IF NOT EXISTS `cliente` (
                                         `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         `nombre` VARCHAR(100) COLLATE utf8_spanish2_ci NOT NULL,
                                         `direccion` VARCHAR(255) COLLATE utf8_spanish2_ci NOT NULL,
                                         `telefono` VARCHAR(15) COLLATE utf8_spanish2_ci NOT NULL,
                                         `fecha_nacimiento` DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Insertar 10 registros de ejemplo en la tabla cliente
INSERT INTO `cliente` (`nombre`, `direccion`, `telefono`, `fecha_nacimiento`) VALUES
                                                                                  ('Juan Pérez', 'Calle Mayor 1', '123456789', '1985-03-25'),
                                                                                  ('Ana López', 'Avenida Libertad 23', '987654321', '1990-07-14'),
                                                                                  ('Carlos Gómez', 'Plaza España 10', '654987321', '1980-12-05'),
                                                                                  ('María Sánchez', 'Calle del Sol 45', '345678901', '1975-09-21'),
                                                                                  ('Pedro Martínez', 'Avenida Central 8', '456789012', '1983-01-11'),
                                                                                  ('Lucía Fernández', 'Calle Luna 17', '789012345', '1995-06-30'),
                                                                                  ('Jorge Ramírez', 'Paseo de la Paz 3', '890123456', '1987-11-19'),
                                                                                  ('Laura Torres', 'Callejón del Río 12', '901234567', '1992-04-10'),
                                                                                  ('Sofía Vega', 'Calle Primavera 7', '567890123', '1988-02-28'),
                                                                                  ('Manuel Castro', 'Calle Otoño 20', '678901234', '1999-08-15');


/*
Por si necesitas hacer un join calculando numero de empleados, el left join es por si es 0

d.id_departamento,
    d.nombre_departamento,
    COUNT(e.id_empleado) AS numero_empleados
FROM
    departamentos d
LEFT JOIN
    empleados e
ON
    d.id_departamento = e.id_departamento
GROUP BY
    d.id_departamento, d.nombre_departamento;


Por si necesitas hacer un beetween entre dos valores.

SELECT
    id_departamento,
    nombre_departamento,
    (presupuesto - gastos) AS presupuesto_actual_disponible
FROM
    departamentos
WHERE
    (presupuesto - gastos) BETWEEN ? AND ?;

PARA LOS EMPLEADOS QUE TIENEN UN DEPARTAMENTO ASIGNADO.

SELECT
    e.id_empleado,
    e.nombre_empleado,
    d.nombre_departamento
FROM
    empleados e
INNER JOIN
    departamentos d
ON
    e.id_departamento = d.id_departamento;


NOMBRE DE EMPLEADO QUE CONTIENE UNA LETRA

SELECT
    id_empleado,
    nombre_empleado
FROM
    empleados
WHERE
    nombre_empleado LIKE '%A%';


SUMAR PRESUPUESTOS

SELECT
    d.id_departamento,
    d.nombre_departamento,
    SUM(e.gastos) AS total_gastos
FROM
    empleados e
INNER JOIN
    departamentos d
ON
    e.id_departamento = d.id_departamento
GROUP BY
    d.id_departamento, d.nombre_departamento;
*/

