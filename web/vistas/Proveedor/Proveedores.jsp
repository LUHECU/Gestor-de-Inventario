
<%@page import="java.util.Iterator"%>
<%@page import="modelos.Proveedor"%>
<%@page import="java.util.List"%>
<%@page import="modeloDAO.ProveedorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" contentType="text/html; charset="utf-8">
		
		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
                <link rel="stylesheet" href="vistas/CSS/Tablapagi.css">
                <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
                
		<title>Proveedores</title>
	</head>
	<body>

            <%@include file="../Encabezado.jsp" %>

		<div class="tituloContainer espaciadorTop">
			<h2>Proveedores</h2>
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
                                            <th hidden="hidden">IdProveedor</th>
                                            <th>Nombre</th>
                                            <th>Ubicación</th>
                                            <th>Teléfono</th>
                                            <th>Correo</th>
                                            <th>Asesor</th>
                                            <th>Funciones</th>
					</tr>
				</thead>
		
				<tbody>
                                    
                                     <%
                            ProveedorDAO daoProv = new ProveedorDAO();
                            List<Proveedor> listaProv = daoProv.Listar();
                            Iterator<Proveedor> iterProv = listaProv.iterator();
                            Proveedor prov = null;
                            while(iterProv.hasNext()) {
                                prov = iterProv.next();
                        %>
                                    
					<tr>
                                                <td  hidden="hidden"><%= prov.getIdProvedor()%></td>
						<td><%= prov.getNombre()%></td>
						<td><%= prov.getUbicacion()%></td>
                                                <td><%= prov.getTelefono()%></td>
						<td><%= prov.getEmail()%></td>
						<td><%= prov.getAsesor()%></td>
						<td>
							<a id="botones" href="ProveedorControlador?accion=editar&idProveedor=<%= prov.getIdProvedor()%>">Editar</a>
                                                        <a id="botones" href="ProveedorControlador?accion=eliminar&idProveedor=<%= prov.getIdProvedor()%>">Eliminar</a>
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
				  	<a id="botones" class="btnA" class="botones btnAgregar" href="ProveedorControlador?accion=ingreso">Agregar</a>
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
		<script  src="vistas/JavaS/Tablapaginada.js"></script>
                 <script src="vistas/JavaS/roles.js"></script>
		
                <%@include file="../PieDePagina.jsp" %>
				
	</body>
</html>
