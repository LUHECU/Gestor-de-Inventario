

<%@page import="modelos.CuentaProveedor"%>
<%@page import="modeloDAO.CuentaProveedorDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelos.Producto"%>
<%@page import="java.util.List"%>
<%@page import="modeloDAO.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'><link rel="stylesheet" href="../CSS/Tablapagi.css">
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilo_MenuDesplegable.css"/>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
        
        <title>Actualización de Facturas</title>
    </head>
    <body>
        
        <%@include file="../Encabezado.jsp" %>
        
        
        <%
            CuentaProveedorDAO daoCuenta = new CuentaProveedorDAO();
            String temp = request.getAttribute("idFacProvedor").toString();
            int idFacProvedor = Integer.parseInt(temp);
            CuentaProveedor cuenta = daoCuenta.ListarCuentaProveedorUnica(idFacProvedor);           
        %>  
        
        <div class="tituloContainer espaciadorTop espaciadorBottom">
                <h2>Actualización de productos</h2>
        </div>

        <div align="center">
            <form action="CuentaProveedorControlador" style="margin-bottom: 150px;">
		
                <input type="hidden" name="txtIdCuenta" value="<%= cuenta.getIdFacProvedor()%>">
                
                <div class="formulario">
                        <div>Método de pago:</div>
                        <div class="select" >
                            <select id="standard-select" name="cbxMetodoPago" >
                                <option value="" disabled="true">Elija una opción...</option>
                                <option value="Efectivo">Efectivo</option>
                                <option value="Transferencia">Transferencia</option>
                                <option value="Cheque">Cheque</option>
                                <option value="Tarjeta de crédito">Tarjeta de crédito</option>
                            </select>
                        </div>
                </div>
                <div class="formulario">
                        <div>Monto:</div>
                        <input type="text" name="txtMonto" value="<%= cuenta.getMonto()%>">
                </div>
                
                <div id="formulario">
                        <span><input class="botones " type="submit" id="btnGuardar" name="accion" value="Actualizar"></span>
                </div>
			
            </form>
        </div>
        
        <%@include file="../PieDePagina.jsp" %>
        
    </body>
</html>
