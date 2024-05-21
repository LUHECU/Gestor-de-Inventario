
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeloDAO.UsuarioDAO;
import modelos.Usuario;

@WebServlet(name = "MovimientoInventarioControlador", urlPatterns = {"/MovimientoInventarioControlador"})
public class MovimientoInventarioControlador extends HttpServlet {

    String movimiento =  "vistas/MovimientoInventario/MovimientosInventarios.jsp";
    String login = "vistas/InicioSesion.jsp";
    String inicio = "index.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MoevimientoInventarioControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MoevimientoInventarioControlador at " + request.getContextPath() + "</h1>");
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
                request.setAttribute("error", "");
                if(cedula == null || cedula.equals("")){
                    if(action.equals("ingresar")){
                       if(validarUsuario(request)){
                        acceso=movimiento;
                       } else {
                          request.setAttribute("error", "Cédula o Contraseña Incorrecta");
                          acceso=login;
                       }  
                    } else {
                      acceso=login;
                    }                                                            
                } else {  
                    switch(action){
                        case "movimiento":
                            acceso = movimiento;
                            break;
                            
                        case "cerrarsesion":
                            sesion.setAttribute("cedula", "");
                            sesion.setAttribute("nombre", "");
                            acceso=inicio;
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
        
        
            switch(action){
                case "movimiento":
                    acceso=movimiento;
                    break;
            }
            
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
        
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
            return true;
        }        
        return false;
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
