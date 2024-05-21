
<%@page import="modelos.Usuario"%>
<%@page import="modeloDAO.UsuarioDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilo_MenuDesplegable.css"/>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
        
        <title>Actualización de Usuarios</title>
    </head>
    <body>
        
        <%@include file="../Encabezado.jsp" %>
        
        <%
            UsuarioDAO dao = new UsuarioDAO();
            String temp = request.getAttribute("idUsuario").toString();
            int idUsuario = Integer.parseInt(temp);
            Usuario us = dao.ListarUsuarioUnico(idUsuario);           
        %>  
        
        <div class="tituloContainer espaciadorTop espaciadorBottom">
            <h2>Actualización de Usuarios</h2>
        </div>
        
        <div align="center">
            <form action="UsuarioControlador" style="margin-bottom: 150px;">

                    <input type="hidden" name="txtIdUsuario" value="<%= us.getIdUsuario()%>">

                    <div class="formulario">
                        <div>Cedula:</div>
                        <input type="text" name="txtCedulaUs" value="<%= us.getCedula()%>">
                    </div>
                    <div class="formulario">
                        <div> Nombre:</div>
                        <input type="text" name="txtNombre" value="<%= us.getNombre()%>">
                    </div>
                    <div class="formulario">
                        <div>Rol:</div>
                        <div class="select">
                            <select id="standard-select" name="cbxRol">
                                    <option value="">Elija una opción...</option>
                                    <option value="Administrador">Administrador</option>
                                    <option value="Privilegiado">Privilegiado</option>
                                    <option value="Visualizador">Visualizador</option>
                            </select>
                        </div>
                    </div>
                    <div class="formulario">
                        <div>Email:</div>
                        <input type="text" name="txtEmail" value="<%= us.getEmail() %>">
                    </div>
                    <div class="formulario">
                        <div>Contraseña:</div>
                        <input type="password" name="txtContrasena" value="<%= us.getContrasena()%>">
                    </div>
                    <div id="BotonesFunc">
                        <span><input type="submit" name="accion" value="Actualizar" class="botones" ></span>
                    </div>
            </div>

            </form>
        </div>
        <%@include file="../PieDePagina.jsp" %>
        
    </body>
</html>
