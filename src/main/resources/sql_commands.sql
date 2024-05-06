--- TABLA PRODUCTOS ---

CREATE TABLE productos (
    idProducto NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    fec_registro DATE
);

--- STORE PROCEDURE ---

CREATE OR REPLACE PROCEDURE sp_insertAndListProducts (
    nombre IN VARCHAR2,
    fec_registro IN DATE,
    p_cursor OUT SYS_REFCURSOR,
    codigoRespuesta OUT NUMBER,
    mensajeRespuesta OUT VARCHAR2
) AS
BEGIN
    INSERT INTO productos (nombre, fec_registro)
    VALUES (nombre, fec_registro);

    OPEN p_cursor FOR
    SELECT * FROM productos;

    codigoRespuesta := 0;
    mensajeRespuesta := 'Ejecución con éxito';

EXCEPTION
    WHEN OTHERS THEN
        codigoRespuesta := 1;
        mensajeRespuesta := SQLERRM;
END sp_insertAndListProducts;
/

--- CALL SP ---

variable rc refcursor;
variable v_codigoRespuesta NUMBER;
variable v_mensajeRespuesta VARCHAR2;
exec sp_insertAndListProducts('Producto03',SYSDATE,:rc, :v_codigoRespuesta,:v_mensajeRespuesta);
print rc;
print v_codigoRespuesta;
print v_mensajeRespuesta;
