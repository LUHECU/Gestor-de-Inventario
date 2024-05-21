
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilo_MenuDesplegable.css"/>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
        
        <title>Ingreso Provedores</title>
    </head>
    <body>
        
        <%@include file="../Encabezado.jsp" %>
        
        <div class="tituloContainer espaciadorTop espaciadorBottom">
            <h3>Ingreso de Proveedor</h3>
        </div>
        
        <div align="center">
            <form action="ProveedorControlador">

                <div class="formulario">
                    <div>Nombre:</div>
                    <input type="text" id="txtNombre" name="txtNombre">
                </div>
                <div class="formulario">
                    <div> Ubicación:</div>
                    <input type="text" id="txtUbicacion" name="txtUbicacion">
                </div>
                <div class="formulario">
                    <div>Teléfono:</div>
                    <input type="text" id="txtTelefono" name="txtTelefono">
                </div>
                <div class="formulario">
                    <div>Email:</div>
                    <input type="text" id="txtEmail" name="txtEmail">
                </div>
                <div class="formulario">
                    <div>Asesor:</div>
                    <input type="text" id="txtAsesor" name="txtAsesor">
                </div>
                <div id="BotonesFunc">
                    <span><input  class="botones" type="submit" id="btnGuardar" name="accion" value="Guardar"></span>
                </div>
            
            </form>
        </div>
        
        <%@include file="../PieDePagina.jsp" %>
        
    </body>
</html>
