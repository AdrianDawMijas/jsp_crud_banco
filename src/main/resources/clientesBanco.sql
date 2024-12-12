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
