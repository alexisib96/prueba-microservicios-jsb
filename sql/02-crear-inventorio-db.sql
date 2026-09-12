
CREATE DATABASE InventorioDB;

USE InventorioDB;

CREATE TABLE dbo.Inventorio (
    Id         INT IDENTITY(1,1) PRIMARY KEY,
    ItemId     INT           NOT NULL UNIQUE,  -- Relación lógica con CatalogDB.Items.Id
    Stock      INT           NOT NULL DEFAULT 0,
    Status     NVARCHAR(30)  NOT NULL DEFAULT 'AVAILABLE',
    UpdatedAt  DATETIME      NOT NULL DEFAULT GETDATE(),

    CONSTRAINT CK_Inventorio_Stock  CHECK (Stock >= 0),
    CONSTRAINT CK_Inventorio_Status CHECK (Status IN ('AVAILABLE', 'LOW_STOCK', 'OUT_OF_STOCK', 'DISCONTINUED'))
);


CREATE INDEX IX_Inventorio_ItemId ON dbo.Inventorio(ItemId);
CREATE INDEX IX_Inventorio_Status ON dbo.Inventorio(Status);
