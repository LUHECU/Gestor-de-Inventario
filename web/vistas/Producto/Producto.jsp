
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Producto"%>
<%@page import="modeloDAO.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Tablapagi.css">
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>

        <title>Producto</title>
    </head>
    
    <body >
        
        <%@include file="../Encabezado.jsp" %>
        
        <div>
        
            <div class="tituloContainer espaciadorTop">
                    <h2>Productos</h2>
            </div>

            <div></div>

                    <!--Inicio de tabla paginada-->
            <div class="container ">
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
                        <th hidden="hidden">IdProducto</th>
                        <th hidden="hidden">IdProveedor</th>
                        <th>Nombre</th>
                        <th>Proveedor</th>
                        <th>Cantidad</th>
                        <th>Costo</th>
                        <th>Precio de Venta</th>
                        <th>Funciones</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        ProductoDAO daoProduc = new ProductoDAO();
                        List<Producto> listaProduc = daoProduc.ListarProductos();
                        Iterator<Producto> iterProduc = listaProduc.iterator();
                        Producto produc = null;
                        while(iterProduc.hasNext()) {
                            produc = iterProduc.next();
                    %>

                    <tr>
                        <td hidden="hidden"> <%= produc.getIdProducto()%> </td>
                        <td hidden="hidden"> <%= produc.getIdProveedor()%> </td>
                        <td> <%= produc.getNombre()%> </td>
                        <td> <%= produc.getNombreProveedor()%> </td>
                        <td> <%= produc.getCantidad()%> </td>
                        <td> <%= produc.getCosto()%> </td>
                        <td> <%= produc.getPrecioVenta()%> </td>

                        <td>
                            <a id="botones" href="ProductoControlador?accion=editar&idProducto=<%= produc.getIdProducto()%>">Editar</a>
                            <a id="botones" href="ProductoControlador?accion=eliminar&idProducto=<%= produc.getIdProducto()%>">Eliminar</a>                      
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
                      
                      <a id="botones" class="btnA" href="ProductoControlador?accion=ingreso">Agregar</a>
                      <a id="botones" class="btnA" style="text-align: center" href="ProductoControlador?accion=aumento">Aumentar Precio</a>
                      <a id="botones" class="btnA" style="text-align: center" href="ProductoControlador?accion=rebaja">Disminuir Precio</a>
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
            

            <script  src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
            <script  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
            <script  src="vistas/JavaS/Tablapaginada.js" type="text/javascript"></script>
            <script src="vistas/JavaS/roles.js"></script>

            
        </div>
            
            <%@include file="../PieDePagina.jsp" %>
            
    </body>
</html>
