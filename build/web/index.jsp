
<%@page import="controladores.InicioControlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%
            InicioControlador ic = new InicioControlador();
            
            if (ic.obtCedula(request) == null || ic.obtCedula(request).equals("")) {
        %>
                <%@include file="vistas/InicioSesion.jsp" %>
        <%
            }
            else{
        %>
                <%@include file="vistas/Inicio.jsp" %>
        <% 
            }
        %>
        
    </head>
    <body>
        
    </body>
</html>
