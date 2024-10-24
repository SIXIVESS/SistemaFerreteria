USE [master]
GO
/****** Object:  Database [Ferreteria]    Script Date: 22/10/2024 06:24:09 a. m. ******/
CREATE DATABASE [Ferreteria]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Ferreteria', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\Ferreteria.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Ferreteria_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\Ferreteria_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Ferreteria] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Ferreteria].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Ferreteria] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Ferreteria] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Ferreteria] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Ferreteria] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Ferreteria] SET ARITHABORT OFF 
GO
ALTER DATABASE [Ferreteria] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Ferreteria] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Ferreteria] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Ferreteria] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Ferreteria] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Ferreteria] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Ferreteria] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Ferreteria] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Ferreteria] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Ferreteria] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Ferreteria] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Ferreteria] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Ferreteria] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Ferreteria] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Ferreteria] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Ferreteria] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Ferreteria] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Ferreteria] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Ferreteria] SET  MULTI_USER 
GO
ALTER DATABASE [Ferreteria] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Ferreteria] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Ferreteria] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Ferreteria] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Ferreteria] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Ferreteria] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Ferreteria] SET QUERY_STORE = ON
GO
ALTER DATABASE [Ferreteria] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Ferreteria]
GO
/****** Object:  Table [dbo].[Categorias]    Script Date: 22/10/2024 06:24:09 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categorias](
	[CategoriaID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoriaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleVentas]    Script Date: 22/10/2024 06:24:09 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleVentas](
	[DetalleID] [int] IDENTITY(1,1) NOT NULL,
	[VentaID] [int] NULL,
	[ProductoID] [int] NULL,
	[Cantidad] [int] NOT NULL,
	[PrecioUnitario] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DetalleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Productos]    Script Date: 22/10/2024 06:24:09 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Productos](
	[ProductoID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[Descripcion] [text] NULL,
	[Precio] [decimal](10, 2) NOT NULL,
	[Stock] [int] NOT NULL,
	[CategoriaID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ProductoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ventas]    Script Date: 22/10/2024 06:24:09 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ventas](
	[VentaID] [int] IDENTITY(1,1) NOT NULL,
	[Fecha] [datetime] NOT NULL,
	[Total] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[VentaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Categorias] ON 

INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (1, N'Herramientas manuales')
INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (2, N'Material electrico')
INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (3, N'Iluminacion')
INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (4, N'Tuberias')
INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (5, N'Pinturas')
INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (6, N'De acero')
INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (7, N'Abrasivos')
INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (8, N'Tornillos')
INSERT [dbo].[Categorias] ([CategoriaID], [Nombre]) VALUES (9, N'Tuercas')
SET IDENTITY_INSERT [dbo].[Categorias] OFF
GO
SET IDENTITY_INSERT [dbo].[Productos] ON 

INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (1, N'Disco Corte', N'1 mm de corte', CAST(27.00 AS Decimal(10, 2)), 6, 7)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (2, N'Abanico Lijador', N'12000 RPM, 30 x 5 mm, grano 60', CAST(86.00 AS Decimal(10, 2)), 6, 7)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (3, N'Lima cuadrada', N'200 mm / 8 " acero 60-65 HRC', CAST(140.00 AS Decimal(10, 2)), 6, 7)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (4, N'Cepillo metalico', N'De taaz ondulado, grano 120, 4,500 RPM', CAST(160.00 AS Decimal(10, 2)), 6, 7)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (5, N'Apagador voltech', N'Vertical nylon, 15A, 120V', CAST(40.00 AS Decimal(10, 2)), 6, 2)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (6, N'Interruptor fotoelectrico', N'127V, plastico 12.5 x 4.5 x 3,1.5W', CAST(83.00 AS Decimal(10, 2)), 6, 2)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (7, N'Socket', N'Blanco ceramica 660W, 127V, rosca E26', CAST(25.00 AS Decimal(10, 2)), 6, 2)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (8, N'Terminal anillo', N'6 pz, 6.4 mm, calibre 22-16 Rojo', CAST(23.00 AS Decimal(10, 2)), 6, 2)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (9, N'Foco Oakland', N'Ahorrador 45W luz blanca', CAST(120.00 AS Decimal(10, 2)), 6, 3)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (10, N'Foco mata mosquito', N'12Wm blanco frio 5500 K, tension 110-120 V', CAST(180.00 AS Decimal(10, 2)), 6, 3)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (11, N'Linterna recargable', N'Usb, 1.38 x 3.94 x 8.16 mm', CAST(350.00 AS Decimal(10, 2)), 6, 3)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (12, N'Linterna minero', N'Bateria litio, LED 3 W, 12 hrs druacion', CAST(280.00 AS Decimal(10, 2)), 6, 3)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (13, N'Abrazadera fastlock', N'Acero galvanizado 25mm, 1 in', CAST(35.00 AS Decimal(10, 2)), 6, 4)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (14, N'Adaptador PVC', N'Cedula 40 macho, Temp max 60, 13 mm, 1/2 in', CAST(6.00 AS Decimal(10, 2)), 6, 4)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (16, N'Flotador de plastico', N'Modelo 4, varilla 1/4 in,65 gm,11 x 8 x 8', CAST(30.00 AS Decimal(10, 2)), 6, 4)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (17, N'Manguera para gas', N'Presion 17.5 kg/cm2/350 psi, temp max 60, 35 cm', CAST(120.00 AS Decimal(10, 2)), 6, 4)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (18, N'Coladera inoxidable', N'Acero inoxidable, 101 mm, calibre 18', CAST(80.00 AS Decimal(10, 2)), 6, 4)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (19, N'Adhesivo Pegaset', N'Blanco, 1.60-170 g/ml, presion 460 g, temp 15-40 C', CAST(100.00 AS Decimal(10, 2)), 6, 5)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (20, N'Acrilica Berel', N'Blanco 1 Lt', CAST(290.00 AS Decimal(10, 2)), 6, 5)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (21, N'Impermeabilizante', N'19 litros', CAST(1250.00 AS Decimal(10, 2)), 6, 5)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (22, N'Aerosol Amarillo', N'Amarillo 400 ml, anticorrosivo', CAST(60.00 AS Decimal(10, 2)), 6, 5)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (23, N'Anticorrosivo', N'946 ml, no inflamable, no toxico', CAST(560.00 AS Decimal(10, 2)), 6, 5)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (24, N'Rollo Malla', N'Hexagonal clibre 20, 1.50 x 45 mts', CAST(1150.00 AS Decimal(10, 2)), 6, 6)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (25, N'Malla mosquitera', N'30 x 1.05 mts', CAST(450.00 AS Decimal(10, 2)), 6, 6)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (26, N'Cepillo carpintero', N'Base lisa #4', CAST(560.00 AS Decimal(10, 2)), 6, 1)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (27, N'Cinta larga', N'Fibra de vidrio 50 M ancho 1/2 in', CAST(180.00 AS Decimal(10, 2)), 6, 1)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (28, N'Cortaperno', N'14 in, corte suave 8 mm, duro 5 mm, metal cromado', CAST(470.00 AS Decimal(10, 2)), 6, 1)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (29, N'Cutter', N'De trinquete suso pesado H1', CAST(120.00 AS Decimal(10, 2)), 6, 1)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (30, N'Tornillo maquina', N'7/16, alrgo 25 mm, 60 xbolsa', CAST(109.00 AS Decimal(10, 2)), 6, 8)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (31, N'Tornillo coche', N'1/4 in, largo 13 mm, 150 x bolsa', CAST(109.00 AS Decimal(10, 2)), 6, 8)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (32, N'Tornillo de acero', N'grado 5 1/2 in, largo 25 mm, 50 x bolsa', CAST(109.00 AS Decimal(10, 2)), 6, 8)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (33, N'Tornillo WC', N'2 x bolsa, 5/16 in, 70 mm', CAST(29.00 AS Decimal(10, 2)), 6, 8)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (34, N'Tuerca hexagonal', N'Galvanizada std 1/2', CAST(2.00 AS Decimal(10, 2)), 100, 9)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (35, N'Tuerca cople', N'comple 1/2 in', CAST(2.00 AS Decimal(10, 2)), 100, 9)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (36, N'Tuercas paquete', N'Galvanizadas 12 tuercas de 3/8 in hex acero', CAST(109.00 AS Decimal(10, 2)), 6, 9)
INSERT [dbo].[Productos] ([ProductoID], [Nombre], [Descripcion], [Precio], [Stock], [CategoriaID]) VALUES (37, N'Tuerca Sakamura', N'1/8 in 3mm, 50 x bolsa', CAST(149.00 AS Decimal(10, 2)), 6, 9)
SET IDENTITY_INSERT [dbo].[Productos] OFF
GO
ALTER TABLE [dbo].[Ventas] ADD  DEFAULT (getdate()) FOR [Fecha]
GO
ALTER TABLE [dbo].[DetalleVentas]  WITH CHECK ADD FOREIGN KEY([ProductoID])
REFERENCES [dbo].[Productos] ([ProductoID])
GO
ALTER TABLE [dbo].[DetalleVentas]  WITH CHECK ADD FOREIGN KEY([VentaID])
REFERENCES [dbo].[Ventas] ([VentaID])
GO
ALTER TABLE [dbo].[Productos]  WITH CHECK ADD FOREIGN KEY([CategoriaID])
REFERENCES [dbo].[Categorias] ([CategoriaID])
GO
USE [master]
GO
ALTER DATABASE [Ferreteria] SET  READ_WRITE 
GO
