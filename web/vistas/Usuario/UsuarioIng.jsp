
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilo_MenuDesplegable.css"/>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
        
        <title>Ingreso Usuarios</title>
    </head>
    <body>
        
        <%@include file="../Encabezado.jsp" %>
        
        <div class="tituloContainer espaciadorTop espaciadorBottom">
            <h3>Ingreso de usuarios</h3>
        </div>

        <form class="centrador" action="UsuarioControlador" style="margin-bottom: 150px;">
                
                
                <div class="formulario">
                    <div>Cedula:</div>
                    <input type="text" name="txtCedulaUs">
                </div>
                <div class="formulario">
                    <div> Nombre:</div>
                    <input type="text" name="txtNombre">
                </div>
                <div class="formulario"  align="center">
                    <div>Rol:</div>
                    <div class="select" >
                        <select id="standard-select" name="cbxRol" >
                            <option value="" disabled="true">Elija una opción...</option>
                            <option value="Administrador">Administrador</option>
                            <option value="Privilegiado">Privilegiado</option>
                            <option value="Visualizador">Visualizador</option>
                        </select>
                    </div>
                </div>
                <div class="formulario">
                    <div>Email:</div>
                    <input type="text" name="txtEmail">
                </div>
                <div class="formulario">
                    <div>Contraseña:</div>
                    <input type="password" name="txtContrasena">
                </div>
                <div id="BotonesFunc">
                    <span><input type="submit" name="accion" value="Guardar" class="botones" ></span>
                </div>
            </div>
            
        </form>
        
        <%@include file="../PieDePagina.jsp" %>
        
    </body>
</html>
