USE master;


IF NOT EXISTS (SELECT 1 FROM sys.server_principals WHERE name = 'app_user')
BEGIN
    PRINT 'ERROR: El login app_user no existe. Créalo primero.';
    RETURN;
END
ELSE
BEGIN
    PRINT 'Login app_user encontrado.';
END


ALTER LOGIN app_user ENABLE;


USE CatalogoDB;


IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'app_user')
BEGIN
    CREATE USER app_user FOR LOGIN app_user;
    PRINT 'Usuario app_user creado en CatalogDB.';
END
ELSE
BEGIN
    PRINT 'Usuario app_user ya existía en CatalogDB.';
END


ALTER USER app_user WITH DEFAULT_SCHEMA = dbo;


ALTER ROLE db_owner ADD MEMBER app_user;


USE InventorioDB;


IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'app_user')
BEGIN
    CREATE USER app_user FOR LOGIN app_user;
    PRINT 'Usuario app_user creado en InventoryDB.';
END
ELSE
BEGIN
    PRINT 'Usuario app_user ya existía en InventoryDB.';
END


ALTER USER app_user WITH DEFAULT_SCHEMA = dbo;

ALTER ROLE db_owner ADD MEMBER app_user;

