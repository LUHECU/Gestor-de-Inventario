
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import modeloDAO.UsuarioDAO;
import modelos.Usuario;

@WebServlet(name = "UsuarioControlador", urlPatterns = {"/UsuarioControlador"})
public class UsuarioControlador extends HttpServlet {

    String us = "vistas/Usuario/Usuario.jsp";
    String usuarioAct = "vistas/Usuario/UsuarioAct.jsp";
    String usuarioIng = "vistas/Usuario/UsuarioIng.jsp";
    String login = "vistas/InicioSesion.jsp";
    String inicio="index.jsp";
    Usuario voUsuario = new Usuario();
    UsuarioDAO voUsuarioDAO = new UsuarioDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioControlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    //Proceso principal para la obtencion de los datos y el desplazamiento entre las diversas secciones
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
                        acceso=us;
                       } else {
                          request.setAttribute("error", "Cédula o Contraseña Incorrecta");
                          acceso=login;
                       }  
                    } else {
                      acceso=login;
                    }                                                            
                } else {  
                    switch(action){
                        case "us":
                            acceso = us;
                            break;
                            
                        case "cerrarsesion":
                            sesion.setAttribute("cedula", "");
                            sesion.setAttribute("nombre", "");
                            acceso=inicio;
                            break;
                            
                        case "ingreso":
                            if (rol.equals("Administrador")) {
                                acceso=usuarioIng;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = us;
                            }
                            
                            break;
                            
                        case "editar":
                            if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                request.setAttribute("idUsuario", request.getParameter("idUsuario"));
                                acceso=usuarioAct;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = us;
                            }
                            
                            break;
                            
                        case "guardar":
                            if (rol.equals("Administrador")) {
                                guardarUsuario(request);
                                acceso = us;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = us;
                            }
                            
                            break;
                        
                        case "eliminar":
                            if (rol.equals("Administrador")) {
                                eliminarUsuario(request);
                                acceso = us;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = us;
                            }
                            
                            break;
                        
                        case "actualizar":
                            if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                actualizarUsuario(request);
                                acceso = us;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = us;
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
    
    //Proceso que obtiene los datos del usuarios desde la vista y los almacena en el constructor, para posteriormente mandarlos a la consulta sql
    private void guardarUsuario(HttpServletRequest request) throws ParseException{
        voUsuario.setCedula(request.getParameter("txtCedulaUs"));
        voUsuario.setNombre(request.getParameter("txtNombre"));
        voUsuario.setRol(request.getParameter("cbxRol"));
        voUsuario.setEmail(request.getParameter("txtEmail"));
        voUsuario.setContrasena(request.getParameter("txtContrasena"));
        
        voUsuarioDAO.AgregarUsuario(voUsuario);
    }
    
    //Procedimiento encargado de obtener los datos desde la visa para la actualizacion del usuario
    private void actualizarUsuario(HttpServletRequest request) throws ParseException{
        voUsuario.setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        voUsuario.setCedula(request.getParameter("txtCedulaUs"));
        voUsuario.setNombre(request.getParameter("txtNombre"));
        voUsuario.setRol(request.getParameter("cbxRol"));
        voUsuario.setEmail(request.getParameter("txtEmail"));
        voUsuario.setContrasena(request.getParameter("txtContrasena"));
        
        voUsuarioDAO.ActualizarUsuario(voUsuario);
    }
    
    //Procedimiento encargado de obtener el id del usuario que se desea eliminar
    private void eliminarUsuario(HttpServletRequest request){
        int idUsuario;
        idUsuario =Integer.parseInt(request.getParameter("idUsuario"));
        voUsuarioDAO.EliminarUsuario(idUsuario);
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
