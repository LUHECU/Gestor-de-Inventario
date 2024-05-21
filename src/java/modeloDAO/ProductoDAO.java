
package modeloDAO;

import config.Conexion;
import interfaces.IProductoDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelos.Producto;
import modelos.Usuario;



public class ProductoDAO implements IProductoDAO{

    @Override
    public List ListarProductos() {
        Conexion cn= new Conexion();
        ArrayList<Producto> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_Productos() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    Producto voProducto = new Producto();
                    voProducto.setIdProducto(rs.getInt("IdProducto"));
                    voProducto.setNombre(rs.getString("Nombre"));
                    voProducto.setCantidad(rs.getInt("Cantidad"));
                    voProducto.setCosto(rs.getFloat("Costo"));
                    voProducto.setPrecioVenta(rs.getFloat("precioVenta"));
                    voProducto.setIdProveedor(rs.getInt("ID_Proveedor"));
                    voProducto.setNombreProveedor(rs.getString("nombreProveedor")); 
                    lista.add(voProducto);
                }
                
                System.out.println("Funcion ejecutada correctamente.");
                cstmt.close();
            } 
            catch (SQLException e) {
                System.out.println("Error al llamar al procedimiento almacenado: " + e.getMessage());
            }
            con.close();
        } 
        catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
    
        return lista;
    }
    
    @Override
    public List ListarNomProveedores() {
        Conexion cn= new Conexion();
        ArrayList<Producto> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_Proveedores() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    Producto voProducto = new Producto();
                    voProducto.setNombreProveedor(rs.getString("nombre"));
                    voProducto.setIdProveedor(rs.getInt("IdProveedor"));
                    lista.add(voProducto);
                }
                
                System.out.println("Funcion ejecutada correctamente.");
                cstmt.close();
            } 
            catch (SQLException e) {
                System.out.println("Error al llamar al procedimiento almacenado: " + e.getMessage());
            }
            con.close();
        } 
        catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
    
        return lista;
    }
    
    
    @Override
    public Producto ListarProductoUnico(int idProducto){
       Producto voProducto = new Producto();
       Conexion cn= new Conexion();
       try (Connection con= cn.getConnection()) {
           
           ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_ProducUni(?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                cstmt.setInt(2, idProducto);
                
                
                // Ejecutar la funcion
                cstmt.execute();
                
                //Obtenemos el cursor que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
               
                
                while (rs.next()){
                    
                    voProducto.setIdProducto(rs.getInt("IdProducto"));
                    voProducto.setNombre(rs.getString("Nombre"));
                    voProducto.setCantidad(rs.getInt("Cantidad"));
                    voProducto.setCosto(rs.getFloat("Costo"));
                    voProducto.setPrecioVenta(rs.getFloat("precioVenta"));
                    voProducto.setIdProveedor(rs.getInt("ID_Proveedor"));
                    voProducto.setNombreProveedor(rs.getString("nombreProveedor"));
                }

                System.out.println("Funcion ejecutada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al llamar a la funcion: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
        return voProducto;
    }
    

    @Override
    public Usuario InicioSesion(String cedula, String contrasena) {
        Usuario us = new Usuario();
       Conexion cn= new Conexion();
       try (Connection con= cn.getConnection()) {
           
           ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call InicioSesion(?, ?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                cstmt.setString(2, cedula);
                cstmt.setString(3, contrasena);
                
                
                // Ejecutar la funcion
                cstmt.execute();
                
                //Obtenemos el cursor que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                while (rs.next()){
                    us.setIdUsuario(rs.getInt("idUsuario"));
                    us.setCedula(rs.getString("cedula"));
                    us.setNombre(rs.getString("nombre"));
                    us.setRol(rs.getString("rol"));
                    us.setEmail(rs.getString("email"));
                    us.setContrasena( rs.getString("contrasena"));
                }
                
                System.out.println(us.getCedula() + "  " + us.getContrasena());

                System.out.println("Funcion ejecutada correctamente.");
                cstmt.close();
            } catch (SQLException e) {
                System.out.println("Error al llamar a la funcion: " + e.getMessage());
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
        return us;
    }

    @Override
    public boolean AgregarProducto(Producto voProducto) {
         Conexion cn= new Conexion();
       
       try (Connection con = cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call INSERTAR_PRODUCTOS(?, ?, ?, ?, ?, ? )}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, voProducto.getCantidad());
                cstmt.setInt(2, voProducto.getIdProveedor());
                cstmt.setString(3, voProducto.getNombre());
                cstmt.setFloat(4, voProducto.getCosto());
                cstmt.setFloat(5, voProducto.getPrecioVenta());
                cstmt.setString(6, voProducto.getNombreUsuario());
                
                
                // Ejecutar la proceso
                cstmt.execute();
                cstmt.close();
                System.out.println("Proceso de insercion ejecutado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al llamar el proceso: " + e.getMessage());
                return false;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean EliminarProducto(int idProducto, String nombreUsuario) {
         Conexion cn= new Conexion();
        
       try (Connection con= cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call ELIMINAR_producto(?, ?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, idProducto);
                cstmt.setString(2, nombreUsuario);
                
                
                // Ejecutar la funcion
                cstmt.execute();

                System.out.println("Proceso de eliminación ejecutado correctamente.");
                cstmt.close();
            } catch (SQLException e) {
                System.out.println("Error al llamar el proceso de eliminacion: " + e.getMessage());
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean ActualizarProducto(Producto voProducto) {
       
         Conexion cn = new Conexion();
       
       try (Connection con = cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call modificar_producto(?, ?, ?, ?, ?, ?, ? )}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, voProducto.getIdProducto());
                cstmt.setInt(2, voProducto.getCantidad());
                cstmt.setInt(3, voProducto.getIdProveedor());
                cstmt.setString(4, voProducto.getNombre());
                cstmt.setFloat(5, voProducto.getCosto());
                cstmt.setFloat(6, voProducto.getPrecioVenta());
                cstmt.setString(7, voProducto.getNombreUsuario());

                
                
                // Ejecutar la funcion
                cstmt.execute();
                cstmt.close();
                System.out.println("Proceso de actualzacion se ha ejecutado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al llamar el proceso: " + e.getMessage());
                return false;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            return false;
        }
        return true;
        
        
        
    }
    
    @Override
    public boolean AumentoProducto() {
       
        Conexion cn = new Conexion();
       
        try (Connection con = cn.getConnection()) {
            // Llamada del cursor
            try (CallableStatement cstmt = con.prepareCall("{ call Aumento_PrecioVenta()}")) {
               
                // Ejecutar el cursor
                cstmt.execute();
                cstmt.close();
                System.out.println("Proceso de actualzacion se ha ejecutado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al llamar el proceso: " + e.getMessage());
                return false;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            return false;
        }
        return true;
        
    }
    
    @Override
    public boolean RebajaProducto() {
       
        Conexion cn = new Conexion();
       
        try (Connection con = cn.getConnection()) {
            // Llamada del cursor
            try (CallableStatement cstmt = con.prepareCall("{ call Rebaja_PrecioVenta()}")) {
               
                // Ejecutar el cursor
                cstmt.execute();
                cstmt.close();
                System.out.println("Proceso de actualzacion se ha ejecutado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al llamar el proceso: " + e.getMessage());
                return false;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            return false;
        }
        return true;
        
    }
    
    
}
