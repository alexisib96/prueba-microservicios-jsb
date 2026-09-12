
CREATE DATABASE CatalogDB;


USE CatalogoDB;

CREATE TABLE dbo.Items (
    Id           INT IDENTITY(1,1) PRIMARY KEY,
    Titulo        NVARCHAR(200)  NOT NULL,
    Descripcion  NVARCHAR(1000) NULL,
    ImagenUrl     NVARCHAR(500)  NULL,
    Precio        DECIMAL(12,2)  NULL,   -- NULL permitido: caso límite del score
    Rating       DECIMAL(3,2)   NULL,   -- NULL permitido: caso límite del score
    CreatedAt    DATETIME       NOT NULL DEFAULT GETDATE()
);

CREATE INDEX IX_Items_Rating ON dbo.Items(Rating);
CREATE INDEX IX_Items_Price  ON dbo.Items(Precio);
