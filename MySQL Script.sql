-- Crear la base de datos
CREATE DATABASE Ferreteria;
USE Ferreteria;

-- Crear tabla Categorias
CREATE TABLE Categorias (
    CategoriaID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL
);

-- Crear tabla DetalleVentas
CREATE TABLE DetalleVentas (
    DetalleID INT AUTO_INCREMENT PRIMARY KEY,
    VentaID INT,
    ProductoID INT,
    Cantidad INT NOT NULL,
    PrecioUnitario DECIMAL(10, 2) NOT NULL
);

-- Crear tabla Productos
CREATE TABLE Productos (
    ProductoID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion TEXT,
    Precio DECIMAL(10, 2) NOT NULL,
    Stock INT NOT NULL,
    CategoriaID INT
);

-- Crear tabla Ventas
CREATE TABLE Ventas (
    VentaID INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATETIME NOT NULL,
    Total DECIMAL(10, 2) NOT NULL
);

-- Insertar datos en la tabla Categorias
INSERT INTO Categorias (Nombre) VALUES 
('Herramientas manuales'),
('Material electrico'),
('Iluminacion'),
('Tuberias'),
('Pinturas'),
('De acero'),
('Abrasivos'),
('Tornillos'),
('Tuercas');

-- Insertar datos en la tabla Productos
INSERT INTO Productos (Nombre, Descripcion, Precio, Stock, CategoriaID) VALUES 
('Disco Corte', '1 mm de corte', 27.00, 6, 7),
('Abanico Lijador', '12000 RPM, 30 x 5 mm, grano 60', 86.00, 6, 7),
('Lima cuadrada', '200 mm / 8 " acero 60-65 HRC', 140.00, 6, 7),
('Cepillo metalico', 'De taaz ondulado, grano 120, 4,500 RPM', 160.00, 6, 7),
('Apagador voltech', 'Vertical nylon, 15A, 120V', 40.00, 6, 2),
('Interruptor fotoelectrico', '127V, plastico 12.5 x 4.5 x 3, 1.5W', 83.00, 6, 2),
('Socket', 'Blanco ceramica 660W, 127V, rosca E26', 25.00, 6, 2),
('Terminal anillo', '6 pz, 6.4 mm, calibre 22-16 Rojo', 23.00, 6, 2),
('Foco Oakland', 'Ahorrador 45W luz blanca', 120.00, 6, 3),
('Foco mata mosquito', '12W, blanco frio 5500 K, tension 110-120 V', 180.00, 6, 3),
('Linterna recargable', 'Usb, 1.38 x 3.94 x 8.16 mm', 350.00, 6, 3),
('Linterna minero', 'Bateria litio, LED 3 W, 12 hrs duracion', 280.00, 6, 3),
('Abrazadera fastlock', 'Acero galvanizado 25mm, 1 in', 35.00, 6, 4),
('Adaptador PVC', 'Cedula 40 macho, Temp max 60, 13 mm, 1/2 in', 6.00, 6, 4),
('Flotador de plastico', 'Modelo 4, varilla 1/4 in, 65 gm, 11 x 8 x 8', 30.00, 6, 4);

-- Nota: Eliminación de configuraciones específicas de SQL Server no soportadas en MySQL
