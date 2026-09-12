
USE InventorioDB;
SET IDENTITY_INSERT dbo.Inventorio ON;

INSERT INTO dbo.Inventorio (Id, ItemId, Stock, Status) VALUES
(1,  1,  50,  'AVAILABLE'),   -- Laptop Pro
(2,  2,  200, 'AVAILABLE'),   -- Mouse
(7,  7,  100, 'AVAILABLE'),   -- Hub USB-C
(8,  8,  25,  'AVAILABLE'),   -- SSD Externo
(10, 10, 12,  'AVAILABLE'),   -- Escritorio
(12, 12, 75,  'AVAILABLE'),   -- Mochila
(13, 13, 40,  'AVAILABLE'),   -- Cable HDMI
(15, 15, 20,  'AVAILABLE'),   -- Item con título vacío

(3,  3,  8,   'LOW_STOCK'),   -- Teclado
(9,  9,  5,   'LOW_STOCK'),   -- Silla

(4,  4,  15,  'AVAILABLE'),   -- Monitor
(6,  6,  30,  'AVAILABLE'),   -- Webcam

(5,  5,  0,   'OUT_OF_STOCK'),   -- Auriculares

(11, 11, 0,   'DISCONTINUED'),   -- Lámpara LED

(16, 999, 10, 'AVAILABLE');      -- ItemId fantasma para probar fallback
SET IDENTITY_INSERT dbo.Inventorio OFF;
