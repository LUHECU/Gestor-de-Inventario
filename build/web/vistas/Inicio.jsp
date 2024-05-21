
<%@page import="modeloDAO.InicioDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelos.Producto"%>
<%@page import="java.util.List"%>
<%@page import="modeloDAO.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Tablapagi.css">
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilo_MenuDesplegable.css"/>
        <title>Inicio</title>
    </head>
    <body>
        
        <%@include file="Encabezado.jsp" %>
        
        <div align="center">
            
            <div class="container tituloContainer">
               <h2>Página de inicio</h2> 
            </div>
            
            <p style="color: black; font-size: 4ch; font-family: Gabriola , Aria; margin-top: 70px;">Cantidad de Productos por Proveedor</p> 
            <p style="color: black; font-size: 3ch; font-family: Gabriola , Arial;">Seleccione un proveedor para ver cuantos productos se tienen de este:</p>
            
            <form action="InicioControlador">
               
                <div class="select" >
                    <select id="standard-select" id="cbxProveedor" name="cbxProveedor" style="font-size: 1.7rem">

                        <option value="" >Elija una opción...</option>
                        <option value=" " disabled="true"></option>
                        <%
                        ProductoDAO daoProduc = new ProductoDAO();
                        List<Producto> listaNomProv = daoProduc.ListarNomProveedores();
                        Iterator<Producto> iterProduc = listaNomProv.iterator();
                        Producto produc = null;
                        while(iterProduc.hasNext()) {
                            produc = iterProduc.next();
                        %>
                            <option value="<%= produc.getIdProveedor()%>"><%= produc.getNombreProveedor()%></option>
                        <%}%>       
                    </select>
                </div>
                    
                  

                <div id="formulario">
                    <input class="botones " type="submit" id="btnGuardar" name="accion" value="Calcular Cantidad" style="font-size: 1.7rem; width: 20ch">

                </div>
            </form>
                    
            <%  
                String msgCant = (String)request.getAttribute("msgCant");                            
                if(msgCant != null && !msgCant.equals("")) {
                %>
                    <div  id="MSG">
                        <div style=" text-align: center;">
                           <%= msgCant %>
                        </div>
                    </div>
            <% } %>
                    
                    
        </div>
            
            

        <div class="container " style="margin-top: 50px">  
            <p style="color: black; text-align: center; font-size: 4ch; font-family: Gabriola , Aria; margin-top: 70px;">Productos con Precio Mayor al Promedio</p> 

            <table class="table table-striped table-class" id= "table-vista1">   

                <thead>
                    <tr>
                        <th hidden="hidden">IdProducto</th>
                        <th>Nombre</th>
                        <th>Proveedor</th>
                        <th>Costo</th>
                        <th>Precio de Venta</th>
                        <th>Ubicacion</th>

                    </tr>
                </thead>

                <tbody>
                    <%
                        InicioDAO daoInicio = new InicioDAO();
                        List<Producto> listaVista1 = daoInicio.vista1();
                        Iterator<Producto> iterVista1 = listaVista1.iterator();
                        produc = null;
                        while(iterVista1.hasNext()) {
                            produc = iterVista1.next();
                    %>

                    <tr>
                        <td hidden="hidden"> <%= produc.getIdProducto()%> </td>
                        <td> <%= produc.getNombre()%> </td>
                        <td> <%= produc.getNombreProveedor()%> </td>
                        <td> <%= produc.getCosto()%> </td>
                        <td> <%= produc.getPrecioVenta()%> </td>
                        <td> <%= produc.getUbicacion()%> </td>
                    </tr>

                    <%}%>
                <tbody>
            </table>
        </div>
                
        
        <!--Inicio de tabla paginada-->
        <div class="container " style="margin-top: 70px">  
            <p style="color: black; text-align: center; font-size: 4ch; font-family: Gabriola , Aria; margin-top: 70px;">Productos con Precio Menor por Proveedor al Promedio de los Productos con Menor Precio </p> 

            <table class="table table-striped table-class" id= "table-vista1">   

                <thead>
                    <tr>
                        <th hidden="hidden">IdProducto</th>
                        <th>Nombre</th>
                        <th>Proveedor</th>
                        <th>Costo</th>
                        <th>Precio de Venta Máximo</th>

                    </tr>
                </thead>

                <tbody>
                    <%
                        List<Producto> listaVista2 = daoInicio.vista2();
                        Iterator<Producto> iterVista2 = listaVista2.iterator();
                        produc = null;
                        while(iterVista2.hasNext()) {
                            produc = iterVista2.next();
                    %>

                    <tr>
                        <td hidden="hidden"> <%= produc.getIdProducto()%> </td>
                        <td> <%= produc.getNombre()%> </td>
                        <td> <%= produc.getNombreProveedor()%> </td>
                        <td> <%= produc.getCosto()%> </td>
                        <td> <%= produc.getPrecioVenta()%> </td>
                    </tr>

                    <%}%>
                <tbody>
            </table>
        </div>
                
        <!--Inicio de tabla paginada-->
        <div class="container " style="margin-top: 70px; margin-bottom: 200px;">  
            <p style="color: black; text-align: center; font-size: 4ch; font-family: Gabriola , Aria; margin-top: 70px;">Productos con Precio Mayor por Proveedor al Promedio de los Productos con Mayor Precio </p> 

            <table class="table table-striped table-class" id= "table-vista1">   

                <thead>
                    <tr>
                        <th hidden="hidden">IdProducto</th>
                        <th>Nombre</th>
                        <th>Proveedor</th>
                        <th>Costo</th>
                        <th>Precio de Venta Máximo</th>

                    </tr>
                </thead>

                <tbody>
                    <%
                        List<Producto> listaVista3 = daoInicio.vista3();
                        Iterator<Producto> iterVista3 = listaVista3.iterator();
                        produc = null;
                        while(iterVista3.hasNext()) {
                            produc = iterVista3.next();
                    %>

                    <tr>
                        <td hidden="hidden"> <%= produc.getIdProducto()%> </td>
                        <td> <%= produc.getNombre()%> </td>
                        <td> <%= produc.getNombreProveedor()%> </td>
                        <td> <%= produc.getCosto()%> </td>
                        <td> <%= produc.getPrecioVenta()%> </td>
                    </tr>

                    <%}%>
                <tbody>
            </table>
        </div>
        
                
                
            
            
            
        <script  src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
        <script  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>   
        <script src="vistas/JavaS/msgInicio.js"></script>
        
            
        <%@include file="PieDePagina.jsp" %>
        
        
        
    </body>
</html>
