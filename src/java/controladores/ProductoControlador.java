
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
import modeloDAO.ProductoDAO;
import modeloDAO.UsuarioDAO;
import modelos.Producto;
import modelos.Usuario;

@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {

   
    String producto = "vistas/Producto/Producto.jsp";
    String productoIng = "vistas/Producto/ProductoIng.jsp";
    String productoAct = "vistas/Producto/ProductoAct.jsp";
    String login = "vistas/InicioSesion.jsp";
    String inicio = "index.jsp";
    Producto voProducto = new Producto();
    ProductoDAO voProductoDAO = new ProductoDAO();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoControlador at " + request.getContextPath() + "</h1>");
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
                        acceso=producto;
                       } else {
                          request.setAttribute("error", "Cédula o Contraseña Incorrecta");
                          acceso=login;
                       }  
                    } else {
                      acceso=login;
                    }                                                            
                } else {  
                    switch(action){
                        case "producto":
                            acceso=producto;
                            break;
                            
                        case "cerrarsesion":
                            sesion.setAttribute("cedula", "");
                            sesion.setAttribute("nombre", "");
                            acceso=inicio;
                            break;
                            
                        case "ingreso":
                            if (rol.equals("Administrador")) {
                                acceso=productoIng;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = producto;
                            }
                             
                             break;
                            
                        case "editar":
                            if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                               request.setAttribute("idProducto", request.getParameter("idProducto"));
                                acceso=productoAct;
                             
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = producto;
                            }
                            
                            break;
                            
                        case "guardar":
                            if (rol.equals("Administrador")) {
                                guardarProducto(request);
                                acceso = producto;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = producto;
                            }
                            
                            break;
                        
                        case "eliminar":
                            if (rol.equals("Administrador")) {
                                eliminarProducto(request);
                                acceso = producto;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = producto;
                            }
                            
                            break;
                        
                        case "actualizar":
                            if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                actualizarProducto(request);
                                acceso = producto;
                            }
                            else{
                                request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                acceso = producto;
                            }
                            
                            break;
                            
                            case "aumento":
                                if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                    aumentoProducto(request);
                                    acceso = producto;
                                }
                                else{
                                    request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                    acceso = producto;
                                }
                                
                                acceso=producto;
                            break;
                            
                            case "rebaja":
                                
                                if (rol.equals("Privilegiado") || rol.equals("Administrador")) {
                                    rebajaProducto(request);
                                    acceso = producto;
                                }
                                else{
                                    request.setAttribute("msg", "ES NECESARIO SER USUARIO PRIVILEGIADO O ADMINISTRADOR PARA REALIZAR ESTA ACCIÓN");
                                    acceso = producto;
                                }
                                acceso=producto;
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
           sesion.setAttribute("rol", us.getRol());
           return true;
       }        
       return false;
    }
     
    //Proceso que obtiene los datos del usuarios desde la vista y los almacena en el constructor, para posteriormente mandarlos a la consulta sql
    private void guardarProducto(HttpServletRequest request) throws ParseException{
        HttpSession sesion = request.getSession();
        voProducto.setIdProveedor(Integer.parseInt(request.getParameter("cbxProveedor")));
        voProducto.setNombre(request.getParameter("txtNombre"));
        voProducto.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
        voProducto.setCosto(Float.parseFloat(request.getParameter("txtCostos")));
        voProducto.setPrecioVenta(Float.parseFloat(request.getParameter("txtPrecioventa")));
        voProducto.setNombreUsuario((String)sesion.getAttribute("nombre"));
        
        voProductoDAO.AgregarProducto(voProducto);
    }
    
    //Procedimiento encargado de obtener los datos desde la visa para la actualizacion del usuario
    private void actualizarProducto(HttpServletRequest request) throws ParseException{
        HttpSession sesion = request.getSession();
        voProducto.setIdProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
        voProducto.setIdProveedor(Integer.parseInt(request.getParameter("cbxProveedor")));
        voProducto.setNombre(request.getParameter("txtNombre"));
        voProducto.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
        voProducto.setCosto(Float.parseFloat(request.getParameter("txtCostos")));
        voProducto.setPrecioVenta(Float.parseFloat(request.getParameter("txtPrecioventa")));
        voProducto.setNombreUsuario((String)sesion.getAttribute("nombre"));
        
        voProductoDAO.ActualizarProducto(voProducto);
    }
    
    //Procedimiento encargado de obtener el id del usuario que se desea eliminar
    private void eliminarProducto(HttpServletRequest request){
        HttpSession sesion = request.getSession();
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String nombreUsuario = (String)sesion.getAttribute("nombre");
        voProductoDAO.EliminarProducto(idProducto, nombreUsuario);
    }
    
    private void rebajaProducto(HttpServletRequest request){
        voProductoDAO.RebajaProducto();
    }

    
    private void aumentoProducto(HttpServletRequest request){
        voProductoDAO.AumentoProducto();
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
