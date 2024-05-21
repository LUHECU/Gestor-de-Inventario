
<header>
        <div class="tituloContainer">
            <img width="80px" height="80px" style="margin-left: 20px; margin-right: 20px" src="vistas/Imagenes/inicio.png" alt=""/>
            <a href="InicioControlador?accion=inicio" style="text-decoration: none"><h1>Gestión de inventario</h1></a>
            
        </div>
</header>

<nav>
        <div align="center" class="menu">
            <a href="ProductoControlador?accion=producto">Productos</a> <span id="separador">|</span>
            <a href="ProveedorControlador?accion=proveedor">Proveedores</a> <span id="separador">|</span>
            <a href="UsuarioControlador?accion=us">Usuarios</a> <span id="separador">|</span>
            <a href="MovimientoInventarioControlador?accion=movimiento">Movimientos de inventario</a> <span id="separador">|</span>
            <a href="CuentaProveedorControlador?accion=factura">Facturas</a> 
            <%
            HttpSession sesion = request.getSession();
            String cedula = (String)sesion.getAttribute("cedula");
            String nombreUsuario = (String)sesion.getAttribute("nombre");
            String rol = (String)sesion.getAttribute("rol");
            if(cedula != null && cedula != ""){
            %>
            
            <span id="separador">|</span>
            <a  href="InicioControlador?accion=cerrarsesion" >Cerrar Sesion (<%= nombreUsuario%> - <%= rol%>)</a>    
              
            

            <% } %>

        </div>
        
    
</nav>




