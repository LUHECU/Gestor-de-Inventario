
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
import javax.swing.JOptionPane;
import modeloDAO.CuentaProveedorDAO;
import modeloDAO.UsuarioDAO;
import modelos.CuentaProveedor;
import modelos.Usuario;

@WebServlet(name = "CuentaProveedorControlador", urlPatterns = {"/CuentaProveedorControlador"})
public class CuentaProveedorControlador extends HttpServlet {

   
    String factura = "vistas/CuentaProveedor/Factura.jsp";
    String facturaAct = "vistas/CuentaProveedor/FacturaAct.jsp";
    String login = "vistas/InicioSesion.jsp";
    String inicio="index.jsp";
    Usuario voUsuario = new Usuario();
    UsuarioDAO voUsuarioDAO = new UsuarioDAO();
    CuentaProveedor voCuentaProveedor = new CuentaProveedor();
    CuentaProveedorDAO voCuentaProveedorDAO = new CuentaProveedorDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CuentaProveedorControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CuentaProveedorControlador at " + request.getContextPath() + "</h1>");
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
                request.setAttribute("msg", "");
                if(cedula == null || cedula.equals("")){
                    if(action.equals("ingresar")){
                       if(validarUsuario(request)){
                        acceso=factura;
                       } else {
                          request.setAttribute("error", "Cédula o Contraseña Incorrecta");
                          acceso=login;
                       }  
                    } else {
                      acceso=login;
                    }                                                            
                } else {  
                    switch(action){
                        case "factura":
                            acceso = factura;
                            break;
                            
                        case "cerrarsesion":
                            sesion.setAttribute("cedula", "");
                            sesion.setAttribute("nombre", "");
                            acceso=inicio;
                            break;
                            
                        case "editar":
                           if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                request.setAttribute("idFacProvedor", request.getParameter("idFacProvedor"));
                                acceso = facturaAct; 
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = factura; 
                            }
                            break;
                        
                        case "eliminar":
                            if (rol.equals("Administrador")) {
                                eliminarCuentaProveedor(request);
                                acceso = factura; 
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = factura; 
                            }
                                   
                            
                            break;
                        
                        case "actualizar":
                           if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                actualizarCuentaProveedor(request);
                                acceso = factura; 
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = factura; 
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
    
    
    //Procedimiento encargado de obtener los datos desde la visa para la actualizacion del usuario
    private void actualizarCuentaProveedor(HttpServletRequest request) throws ParseException{
        voCuentaProveedor.setIdFacProvedor(Integer.parseInt(request.getParameter("txtIdCuenta")));
        voCuentaProveedor.setMonto(Float.parseFloat(request.getParameter("txtMonto")));
        voCuentaProveedor.setMetodoPago(request.getParameter("cbxMetodoPago"));
        
        voCuentaProveedorDAO.ActualizarCuentaProveedor(voCuentaProveedor);
    }
    
    //Procedimiento encargado de obtener el id del usuario que se desea eliminar
    private void eliminarCuentaProveedor(HttpServletRequest request){
        int idCuenta;
        idCuenta = Integer.parseInt(request.getParameter("idFacProvedor"));
        voCuentaProveedorDAO.EliminarCuenta(idCuenta);
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
