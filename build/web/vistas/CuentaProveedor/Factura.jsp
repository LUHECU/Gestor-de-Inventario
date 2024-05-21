
<%@page import="java.util.Iterator"%>
<%@page import="modelos.CuentaProveedor"%>
<%@page import="java.util.List"%>
<%@page import="modeloDAO.CuentaProveedorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="vistas/CSS/Tablapagi.css">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
        
        <title>Facturas</title>
    </head>
    <body>
        
        <%@include file="../Encabezado.jsp" %>
        
        <div class="tituloContainer espaciadorTop">
			<h2>Facturas</h2>
		</div>

		
		<!--Inicio de tabla paginada-->
		<div class="container">
			<div class="header_wrap">
			    <div class="num_rows">
					
                    <div class="form-group">  <!--        Muestra número de filas         -->
                        <select class  ="form-control" name="state" id="maxRows">
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                            <option value="5000">Mostrar todas las filas</option>
                        </select>		 
				    </div>
			    </div>

                <div class="tb_search">
                    <input type="text" id="search_input_all" onkeyup="FilterkeyWord_all_table()" placeholder="Buscar..." class="form-control">
                </div>

            </div>

			<table class="table table-striped table-class" id= "table-id">   
			  
                <thead>
                    <tr>
                    <th hidden="hidden">IdFacProveedor</th>
                    <th hidden="hidden">IdProveedor</th>
                    <th>Proveedor</th>
                    <th>Fecha de Emisión</th>
                    <th>Monto</th>
                    <th>Método de Pago</th>
                    <th>Funciones</th>
                    </tr>
                </thead>
	  
                <tbody>
                    
                     <%
                        CuentaProveedorDAO daoCuenta = new CuentaProveedorDAO();
                        List<CuentaProveedor> listaCuenta = daoCuenta.ListarCuentas();
                        Iterator<CuentaProveedor> iterUS = listaCuenta.iterator();
                        CuentaProveedor cuenta = null;
                        while(iterUS.hasNext()) {
                            cuenta = iterUS.next();
                    %>
                        
                        <tr>
                            <td hidden="hidden"> <%= cuenta.getIdFacProvedor()%> </td>
                            <td hidden="hidden"> <%= cuenta.getIdProvedor()%> </td>
                            <td> <%= cuenta.getProveedor()%> </td>
                            <td> <%= cuenta.getFechaEmision()%> </td>
                            <td> <%= cuenta.getMonto()%> </td>
                            <td> <%= cuenta.getMetodoPago()%> </td>
                            <td>
                                <a id="botones" href="CuentaProveedorControlador?accion=editar&idFacProvedor=<%= cuenta.getIdFacProvedor()%>">Editar</a>
                                <a id="botones" href="CuentaProveedorControlador?accion=eliminar&idFacProvedor=<%= cuenta.getIdFacProvedor()%>">Eliminar</a>                      
                            </td>

                        </tr>
                    <%}%>
                
                <tbody>
            </table>
			
			<!--      Inicio de paginación -->
			<div class='pagination-container'>
				<nav>
                    <ul class="pagination">
                        <!--   Aquí la funcion JS agregará filas -->
                    </ul>
				</nav>
			</div>
			
        </div> <!--       Fin de contenedor -->
        
        
        <%
            String msg = (String)request.getAttribute("msg");                            
            if(msg != null && !msg.equals("")) {
        %>
            <div  id="Advertencia">
                <div style="color: red; text-align: center;">
                   <%= msg %>
                </div>
            </div>
        <% } %>
	  
        <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
        <script src='//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>
        <script src="vistas/JavaS/Tablapaginada.js"></script>
        <script src="vistas/JavaS/roles.js"></script>
        
        
        <%@include file="../PieDePagina.jsp" %>
        
    </body>
</html>
