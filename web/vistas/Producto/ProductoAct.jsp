

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
        
        <title>Actualización de Productos</title>
    </head>
    <body>
        
        <%@include file="../Encabezado.jsp" %>
        
        <%
            ProductoDAO dao = new ProductoDAO();
            String temp = request.getAttribute("idProducto").toString();
            int idProducto = Integer.parseInt(temp);
            Producto prod = dao.ListarProductoUnico(idProducto);           
        %>  
        
        <div class="tituloContainer espaciadorTop espaciadorBottom">
                <h2>Actualización de productos</h2>
        </div>

        <div align="center">
            <form action="ProductoControlador" style="margin-bottom: 150px;">
		
                <input type="hidden" name="txtIdProducto" value="<%= prod.getIdProducto()%>">
                
                <div class="formulario">
                        <div>Nombre:</div>
                        <input type="text" id="txtNombre" name="txtNombre" value="<%= prod.getNombre()%>">
                </div>
                <div class="formulario ">
                        <div>Provedor:</div>
                        <div class="select" >
                        <select id="standard-select" name="cbxProveedor">
                            
                            <option disabled="true">Elija una opción...</option>
                            <option value="<%= prod.getIdProveedor()%>"><%= prod.getNombreProveedor()%></option>
                            <%
                                ProductoDAO daoProduc = new ProductoDAO();
                                List<Producto> listaNomProv = daoProduc.ListarNomProveedores();
                                Iterator<Producto> iterProduc = listaNomProv.iterator();
                                Producto produc = null;
                                while(iterProduc.hasNext()) {
                                    produc = iterProduc.next();

                                    if (produc.getIdProveedor()!= prod.getIdProveedor()) {
                            %>
                                        <option value="<%= produc.getIdProveedor()%>"><%= produc.getNombreProveedor()%></option>
                            <%  
                                    }
                                }
                            %>       
                        </select>
                        </div>
                </div>
                <div class="formulario">
                        <div>Cantidad:</div>
                        <input type="text" id="txtCantidad" name="txtCantidad" value="<%= prod.getCantidad()%>">
                </div>
                <div class="formulario">
                        <div>Costo:</div>
                        <input type="text" id="txtCostos" name="txtCostos" value="<%= prod.getCosto()%>">
                </div>
                <div class="formulario">
                        <div>Precio de venta:</div>
                        <input type="text" id="txtPrecioventa" name="txtPrecioventa" value="<%= prod.getPrecioVenta()%>">
                </div>
                <div id="formulario">
                        <span><input class="botones " type="submit" id="btnGuardar" name="accion" value="Actualizar"></span>
                </div>
			
            </form>
        </div>
        
        <%@include file="../PieDePagina.jsp" %>
        
    </body>
</html>
