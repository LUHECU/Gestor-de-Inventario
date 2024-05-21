
<%@page import="java.util.Iterator"%>
<%@page import="modelos.MovimientoInventario"%>
<%@page import="java.util.List"%>
<%@page import="modeloDAO.MovimientoInventarioDAO"%>
<%@page import="modeloDAO.MovimientoInventarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link rel="stylesheet" href="vistas/CSS/Tablapagi.css">
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
        
        <title>Movimientos de Inventario</title>
    </head>
    <body>
            
        <%@include file="../Encabezado.jsp" %>
        
            <div class="tituloContainer espaciadorTop">
                    <h2>Movimientos de Inventario</h2>
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
                                        <th hidden="hidden">IdMovimiento</th>
                                        <th hidden="hidden">IdProducto</th>
                                        <th hidden="hidden">Cantidad</th>
                                        <th hidden="hidden">Costo</th>
                                        <th hidden="hidden">PrecioVenta</th>
                                        <th>Usuario</th>
                                        <th>Producto</th>
                                        <th>Fecha</th>
                                        <th>Tipo Movimiento</th>
                                    </tr>
                            </thead>

                            <tbody>
                                    
                                <%
                                MovimientoInventarioDAO daoMov = new MovimientoInventarioDAO();
                                List<MovimientoInventario> listaMov = daoMov.ListarMovimientos();
                                Iterator<MovimientoInventario> iterUS = listaMov.iterator();
                                MovimientoInventario mov = null;
                                while(iterUS.hasNext()) {
                                    mov = iterUS.next();
                                %>
                        
                                <tr>
                                    <td hidden="hidden"> <%= mov.getIdMovimiento()%> </td>
                                    <td hidden="hidden"> <%= mov.getIdProducto()%> </td>
                                    <td hidden="hidden"> <%= mov.getCantidad() %> </td>
                                    <td hidden="hidden"> <%= mov.getCosto() %> </td>
                                    <td hidden="hidden"> <%= mov.getPrecioVenta() %> </td>
                                    <td> <%= mov.getNombreUsuario() %> </td>
                                    <td> <%= mov.getNombre() %> </td>
                                    <td> <%= mov.getFecha()%> </td>
                                    <td> <%= mov.getTipoMovimiento() %> </td>
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

            <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
            <script src='//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>
            <script src="vistas/JavaS/Tablapaginada.js" type="text/javascript"></script>

            <%@include file="../PieDePagina.jsp" %>
    </body>
</html>
