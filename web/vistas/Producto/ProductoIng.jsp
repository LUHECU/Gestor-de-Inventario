

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
        
        <title>Ingreso Productos</title>
    </head>
    <body>
        
        <%@include file="../Encabezado.jsp" %>
        
        <div class="tituloContainer espaciadorTop espaciadorBottom">
			<h3>Ingreso de productos</h3>
		</div>

        <div align="center">
            <form action="ProductoControlador" style="margin-bottom: 150px;">
				
                <div class="formulario">
                        <div>Nombre:</div>
                        <input type="text" id="txtNombre" name="txtNombre">
                </div>
                <div class="formulario ">
                        <div>Provedor:</div>
                        <div class="select" >
                        <select id="standard-select" name="cbxProveedor">
                            
                            <option disabled="true">Elija una opción...</option>
                            <option value=" "></option>
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
                </div>
                <div class="formulario">
                        <div>Cantidad:</div>
                        <input type="text" id="txtCantidad" name="txtCantidad">
                </div>
                <div class="formulario">
                        <div>Costo:</div>
                        <input type="text" id="txtCostos" name="txtCostos">
                </div>
                <div class="formulario">
                        <div>Precio de venta:</div>
                        <input type="text" id="txtPrecioventa" name="txtPrecioventa">
                </div>
                <div id="formulario">
                    <span><input class="botones " type="submit" id="btnGuardar" name="accion" value="Guardar"></span>
                </div>
			
            </form>
        </div>
        
        <%@include file="../PieDePagina.jsp" %>
        
    </body>
</html>
