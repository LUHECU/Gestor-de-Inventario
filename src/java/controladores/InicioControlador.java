
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
import modeloDAO.InicioDAO;
import modeloDAO.ProveedorDAO;
import modeloDAO.UsuarioDAO;
import modelos.Producto;
import modelos.Proveedor;
import modelos.Usuario;


@WebServlet(name = "InicioControlador", urlPatterns = {"/InicioControlador"})
public class InicioControlador extends HttpServlet {

    String inicio = "vistas/Inicio.jsp";
    String login = "vistas/InicioSesion.jsp";
    Usuario voUsuario = new Usuario();
    UsuarioDAO voUsuarioDAO = new UsuarioDAO();
    InicioDAO voInicioDAO = new InicioDAO();
    ProveedorDAO voProveedorDAO = new ProveedorDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InicioControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InicioControlador at " + request.getContextPath() + "</h1>");
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
                        acceso=inicio;
                       } else {
                          request.setAttribute("error", "Cédula o Contraseña Incorrecta");
                          acceso=login;
                       }  
                    } else {
                      acceso=login;
                    }                                                            
                } else {  
                    
                     switch(action){
                        case "inicio":
                            acceso = inicio;
                            break;
                            
                        case "cerrarsesion":
                            sesion.setAttribute("cedula", "");
                            sesion.setAttribute("nombre", "");
                            acceso=login;
                            break;
                        
                        case "calcular cantidad":
                            calcularCantProd(request);
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
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso = "";
        String action = request.getParameter("accion");
        action = action.toLowerCase();

        
        try{
            HttpSession sesion = request.getSession();
            String cedula = (String)sesion.getAttribute("cedula");
            request.setAttribute("error", "");
                if(action.equals("ingresar")){
                   if(validarUsuario(request)){
                    acceso=inicio;
                   } else {
                      request.setAttribute("error", "Cédula o Contraseña Incorrecta");
                      acceso=login;
                   }  
                } else {
                  acceso=login;
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
    
    public String obtCedula(HttpServletRequest request){
        HttpSession sesion = request.getSession();
        String cedula = (String)sesion.getAttribute("cedula");
        return cedula;
    }
    
    private void calcularCantProd(HttpServletRequest request){
        String idProveedor = request.getParameter("cbxProveedor");
        
        if (idProveedor == null || idProveedor.equals("")) {
            request.setAttribute("msgCant", "");
        }
        else{
            Proveedor voProveedor = voProveedorDAO.ListarProveedorUnico(Integer.parseInt(idProveedor));
            Producto voProdu = voInicioDAO.calcularCantidadProductosPorProveedor(Integer.parseInt(idProveedor));
            request.setAttribute("msgCant", "La cantidad de productos del provedor " + voProveedor.getNombre() + " es de: " + voProdu.getCalcularCantidadProductosPorProveedor());
        }
        
        
    }
    
   
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
