
USE CatalogoDB;
SET IDENTITY_INSERT dbo.Items ON;

INSERT INTO dbo.Items (Id, Titulo, Descripcion, ImagenUrl, Precio, Rating) VALUES
(1,  'Laptop Pro 15"',          'Laptop de alto rendimiento con procesador de última generación.',      'https://picsum.photos/seed/laptop/400/300',   1500.00, 4.80),
(2,  'Mouse Inalámbrico',       'Mouse ergonómico con conexión Bluetooth y batería recargable.',        'https://picsum.photos/seed/mouse/400/300',      25.00, 4.20),
(3,  'Teclado Mecánico RGB',    'Teclado mecánico con switches táctiles y retroiluminación RGB.',       'https://picsum.photos/seed/keyboard/400/300',   89.99, 4.60),
(4,  'Monitor 27" 4K',          'Monitor UHD 4K con panel IPS y 144Hz de refresco.',                    'https://picsum.photos/seed/monitor/400/300',   450.00, 4.50),
(5,  'Auriculares Bluetooth',   'Auriculares con cancelación activa de ruido y 30h de batería.',        'https://picsum.photos/seed/headphones/400/300', 79.50, 3.80),
(8,  'SSD Externo 1TB',         'Unidad SSD portátil USB 3.2 con velocidades de hasta 1050 MB/s.',      'https://picsum.photos/seed/ssd/400/300',       120.00, 4.70),
(11, 'Lámpara LED Escritorio',  'Lámpara LED ajustable con 3 temperaturas de color y puerto USB.',      'https://picsum.photos/seed/lamp/400/300',       30.00, 4.10),
(12, 'Mochila para Laptop',     'Mochila impermeable con compartimento acolchado para laptop 15.6".',   'https://picsum.photos/seed/backpack/400/300',   55.00, 3.50),
(13, 'Cable HDMI 4K',           NULL,                                                                   NULL,                                             15.00, 4.00),

(6,  'Webcam HD',               'Webcam 1080p con micrófono integrado y enfoque automático.',           'https://picsum.photos/seed/webcam/400/300',     45.00, NULL),

(7,  'Hub USB-C 7 en 1',        'Hub con HDMI, USB 3.0, lector SD y carga PD de 100W.',                 'https://picsum.photos/seed/hub/400/300',       NULL,   4.00),

(9,  'Silla Ergonómica',        'Silla con soporte lumbar ajustable y reposabrazos 4D.',                'https://picsum.photos/seed/chair/400/300',     350.00, 0.00),

(10, 'Escritorio Ajustable',    'Escritorio de altura ajustable eléctricamente, 120x60 cm.',            'https://picsum.photos/seed/desk/400/300',        0.00, 4.30),

(14, 'Item Fantasma',           NULL,                                                                   NULL,                                            100.00, 4.00),

(15, '   ',                     'Item con título vacío para probar normalización.',                     NULL,                                             50.00, 4.00);
SET IDENTITY_INSERT dbo.Items OFF;

