
<%@page import="modelos.Proveedor"%>
<%@page import="modeloDAO.ProveedorDAO"%>
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
        
         
        <%
            ProveedorDAO dao = new ProveedorDAO();
            String temp = request.getAttribute("idProveedor").toString();
            int idProveedor = Integer.parseInt(temp);
            Proveedor prov = dao.ListarProveedorUnico(idProveedor);           
        %>  
        
        <div class="tituloContainer espaciadorTop espaciadorBottom">
            <h3>Ingreso de Proveedor</h3>
        </div>
        
        <div align="center">
            <form  action="ProveedorControlador">
                <input type="hidden" name="txtIdProveedor" value="<%= prov.getIdProvedor()%>">
                <div class="formulario">
                    <div>Nombre:</div>
                    <input type="text" name="txtNombre" value="<%= prov.getNombre()%>">
                </div>
                <div class="formulario">
                    <div> Ubicación:</div>
                    <input type="text" name="txtUbicacion" value="<%= prov.getUbicacion()%>"> 
                </div>
                <div class="formulario">
                    <div>Teléfono:</div>
                    <input type="text" name="txtTelefono" value="<%= prov.getTelefono()%>">
                </div>
                <div class="formulario">
                    <div>Email:</div>
                    <input type="text" name="txtEmail" value="<%= prov.getEmail()%>">
                </div>
                <div class="formulario">
                    <div>Asesor:</div>
                    <input type="text" name="txtAsesor" value="<%= prov.getAsesor()%>">
                </div>
                <div id="BotonesFunc">
                    <span><input type="submit" name="accion" value="Actualizar" class="botones" ></span>
                </div>

            </form>
        </div>
        <%@include file="../PieDePagina.jsp" %>
        
    </body>
</html>
