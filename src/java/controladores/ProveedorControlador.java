package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeloDAO.ProveedorDAO;
import modeloDAO.UsuarioDAO;
import modelos.Proveedor;
import modelos.Usuario;

@WebServlet(name = "ProveedorControlador", urlPatterns = {"/ProveedorControlador"})
public class ProveedorControlador extends HttpServlet {

    String proveedor = "vistas/Proveedor/Proveedores.jsp";
    String proveedorIng = "vistas/Proveedor/ProveedorIng.jsp";
    String proveedorAct = "vistas/Proveedor/ProveedorAct.jsp";
    String login = "vistas/InicioSesion.jsp";
    String inicio = "index.jsp";
    ProveedorDAO voProveedorDAO = new ProveedorDAO();
    Proveedor voProveedor = new Proveedor();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProveedorControlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProveedorControlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acceso = "";
        String action = request.getParameter("accion");
        action = action.toLowerCase();

        
        try{
            HttpSession sesion = request.getSession();
                String cedula = (String)sesion.getAttribute("cedula");
                String rol = (String)sesion.getAttribute("rol");
                request.setAttribute("error", "");
                if(cedula == null || cedula.equals("")){
                    if(action.equals("ingresar")){
                       if(validarUsuario(request)){
                        acceso=proveedor;
                       } else {
                          request.setAttribute("error", "Cédula o Contraseña Incorrecta");
                          acceso=login;
                       }  
                    } else {
                      acceso=login;
                    }                                                            
                } else {  
                    switch(action){
                        case "proveedor":
                            acceso = proveedor;
                            break;
                            
                        case "cerrarsesion":
                            sesion.setAttribute("cedula", "");
                            sesion.setAttribute("nombre", "");
                            acceso=inicio;
                            break;
                            
                        case "ingreso":
                            if (rol.equals("Administrador")) {
                                acceso=proveedorIng;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = proveedor;
                            }
                            
                            break;
                            
                        case "editar":
                            if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                request.setAttribute("idProveedor", request.getParameter("idProveedor"));
                            acceso=proveedorAct;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = proveedor;
                            }
                            
                            break;
                        case "guardar":
                            if (rol.equals("Administrador") ) {
                                guardarProveedor(request);
                                acceso = proveedor;  
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = proveedor;
                            }
                            
                            break;
                        
                        case "eliminar":
                            if (rol.equals("Administrador")) {
                               eliminarProveedor(request);
                                acceso = proveedor; 
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = proveedor;
                            }
                            
                            break;
                        
                        case "actualizar":
                            if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                actualizarProveedor(request);
                                acceso = proveedor;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = proveedor;
                            }
                            
                            break;
                    }
            }
            RequestDispatcher vista=request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

     //Procedimiento encargado de validad 
    private Boolean validarUsuario(HttpServletRequest request){
        String cedula = request.getParameter("cedula");
        String contrasena = request.getParameter("contrasena");
        UsuarioDAO daoUs = new UsuarioDAO();
        Usuario us = daoUs.InicioSesion(cedula, contrasena);
        if(us != null && us.getCedula()!= null ){
            HttpSession sesion = request.getSession();
            sesion.setAttribute("cedula", us.getCedula());
            sesion.setAttribute("nombre", us.getNombre());
            sesion.setAttribute("rol", us.getRol());
            return true;
        }        
        return false;
    }
    
    //Proceso que obtiene los datos del Proveedor desde la vista y los almacena en el constructor, para posteriormente mandarlos a la consulta sql
    private void guardarProveedor(HttpServletRequest request) throws ParseException{
        voProveedor.setNombre(request.getParameter("txtNombre"));
        voProveedor.setUbicacion(request.getParameter("txtUbicacion"));
        voProveedor.setTelefono(request.getParameter("txtTelefono"));
        voProveedor.setEmail(request.getParameter("txtEmail"));
        voProveedor.setAsesor(request.getParameter("txtAsesor"));
        
        voProveedorDAO.AgregarProveedor(voProveedor);
    }
    
    //Procedimiento encargado de obtener los datos desde la visa para la actualizacion del usuario
    private void actualizarProveedor(HttpServletRequest request) throws ParseException{
        voProveedor.setIdProvedor(Integer.parseInt(request.getParameter("txtIdProveedor")));
       voProveedor.setNombre(request.getParameter("txtNombre"));
        voProveedor.setUbicacion(request.getParameter("txtUbicacion"));
        voProveedor.setTelefono(request.getParameter("txtTelefono"));
        voProveedor.setEmail(request.getParameter("txtEmail"));
        voProveedor.setAsesor(request.getParameter("txtAsesor"));
        
        voProveedorDAO.ActualizarProveedor(voProveedor);
    }
    
    //Procedimiento encargado de obtener el id del usuario que se desea eliminar
    private void eliminarProveedor(HttpServletRequest request){
        int idProveedor;
        idProveedor =Integer.parseInt(request.getParameter("idProveedor"));
        voProveedorDAO.EliminarProveedor(idProveedor);
    }
    
    private void limpiarCampos(HttpServletRequest request) throws ParseException{
        request.setAttribute(" ", "txtNombre");
        request.setAttribute(" ", "txtUbicacion");
        request.setAttribute(" ", "txtTelefono");
        request.setAttribute(" ", "txtEmail");
        request.setAttribute(" ", "txtAsesor");
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
