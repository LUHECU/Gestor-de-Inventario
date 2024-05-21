
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Usuario"%>
<%@page import="modeloDAO.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Tablapagi.css">
        <link rel="stylesheet" type="text/css" href="vistas/CSS/Estilos.css"/>
        <title>Usuarios</title>
    </head>
    <body>
        
        <%@include file="../Encabezado.jsp" %>
        
        <div class="tituloContainer espaciadorTop">
                    <h2>Usuarios</h2>
        </div>

        <div></div>

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
                            <th hidden="hidden"></th>
                            <th hidden="hidden"></th>
                            <th hidden="hidden">IdUsuario</th>
                            <th>Cédula</th>
                            <th>Nombre</th>
                            <th>Rol</th>
                            <th>Correo</th>
                            <th>Contraseña</th>
                            <th>Funciones</th>
                        </tr>
                </thead>

                <tbody>
                    
                    <%
                            UsuarioDAO daoUs = new UsuarioDAO();
                            List<Usuario> listaUs = daoUs.Listar();
                            Iterator<Usuario> iterUS = listaUs.iterator();
                            Usuario us = null;
                            while(iterUS.hasNext()) {
                                us = iterUS.next();
                        %>
                        
                            <tr>
                                <td hidden="hidden"> <%= us.getIdUsuario()%> </td>
                                <td> <%= us.getCedula()%> </td>
                                <td> <%= us.getNombre()%> </td>
                                <td> <%= us.getRol()%> </td>
                                <td> <%= us.getEmail() %> </td>
                                <td> <%= us.getContrasena()%> </td>
                                <td>
                                    <a id="botones" href="UsuarioControlador?accion=editar&idUsuario=<%= us.getIdUsuario()%>">Editar</a>
                                    <a id="botones" href="UsuarioControlador?accion=eliminar&idUsuario=<%= us.getIdUsuario()%>">Eliminar</a>                      
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
                      <a id="botones" class="btnA" href="UsuarioControlador?accion=ingreso">Agregar</a>
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

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script  src="vistas/JavaS/Tablapaginada.js"></script>
        <script src="vistas/JavaS/roles.js"></script>

        <%@include file="../PieDePagina.jsp" %>
            
    </body>
</html>
