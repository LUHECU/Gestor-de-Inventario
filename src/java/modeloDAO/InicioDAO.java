
package modeloDAO;

import config.Conexion;
import interfaces.IInicioDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelos.Producto;
import modelos.Usuario;


public class InicioDAO implements IInicioDAO{
    @Override
    public Usuario InicioSesion(String cedula, String contrasena){
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
    public Producto calcularCantidadProductosPorProveedor(int idProveedor) {
        
    Conexion cn = new Conexion();
    
    //Crear una instancia de los modelos
    Producto voProducto = new Producto();
    
    try (Connection con = cn.getConnection()) {
        try (CallableStatement cstmt = con.prepareCall("{ ? = call calcular_cantidad_productos_proveedor(?) }")) {
            // Establecer los valores de los parámetros
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setInt(2, idProveedor);

            // Ejecutar la función
            cstmt.execute();

            // Obtener el resultado de la función
            voProducto.setCalcularCantidadProductosPorProveedor(cstmt.getInt(1));
            
        } catch (SQLException e) {
            System.out.println("Error al llamar a la función calcular_cantidad_productos_proveedor: " + e.getMessage());
        }
    } catch (SQLException e) {
        System.out.println("Error de conexión a la base de datos: " + e.getMessage());
    }
        return voProducto;
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
    public List vista1() {
        Conexion cn= new Conexion();
        ArrayList<Producto> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call primera_vista() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    Producto voProducto = new Producto();
                    voProducto.setIdProducto(rs.getInt("idproducto"));
                    voProducto.setNombre(rs.getString("nombreproducto"));
                    voProducto.setCosto(rs.getFloat("costoproducto"));
                    voProducto.setPrecioVenta(rs.getFloat("precioventa"));
                    voProducto.setNombreProveedor(rs.getString("nombreproveedor")); 
                    voProducto.setUbicacion(rs.getString("ubicacionproveedor"));
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
    public List vista2() {
        Conexion cn= new Conexion();
        ArrayList<Producto> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call segunda_vista() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    Producto voProducto = new Producto();
                    voProducto.setIdProducto(rs.getInt("idProducto"));
                    voProducto.setIdProveedor(rs.getInt("idProveedor"));
                    voProducto.setNombre(rs.getString("Producto"));
                    voProducto.setPrecioVenta(rs.getFloat("PrecioMinimo"));
                    voProducto.setCosto(rs.getFloat("Costo"));
                    voProducto.setNombreProveedor(rs.getString("Proveedor"));
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
    public List vista3() {
        Conexion cn= new Conexion();
        ArrayList<Producto> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call tercera_vista() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    Producto voProducto = new Producto();
                    voProducto.setIdProducto(rs.getInt("idProducto"));
                    voProducto.setIdProveedor(rs.getInt("idProveedor"));
                    voProducto.setNombre(rs.getString("Producto"));
                    voProducto.setPrecioVenta(rs.getFloat("PrecioMaximo"));
                    voProducto.setCosto(rs.getFloat("Costo"));
                    voProducto.setNombreProveedor(rs.getString("Proveedor"));
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
    
}
