
package modeloDAO;

import config.Conexion;
import interfaces.IMovimientoInventarioDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelos.MovimientoInventario;
import modelos.Usuario;

public class MovimientoInventarioDAO implements IMovimientoInventarioDAO{
    
    @Override
    public List ListarMovimientos(){
        Conexion cn= new Conexion();
        ArrayList<MovimientoInventario> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_movimientos_inventario() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    MovimientoInventario mov = new MovimientoInventario();
                    mov.setIdMovimiento(rs.getInt("IdMovimiento"));
                    mov.setFecha(rs.getString("Fecha"));
                    mov.setIdProducto(rs.getInt("IdProducto"));
                    mov.setNombreUsuario(rs.getString("NombreUsuario"));
                    mov.setNombre(rs.getString("Nombre"));
                    mov.setCantidad(rs.getInt("Cantidad"));
                    mov.setCosto(rs.getDouble("Costo"));
                    mov.setPrecioVenta(rs.getDouble("precioVenta"));
                    mov.setTipoMovimiento(rs.getString("TipoMovimiento"));
                    lista.add(mov);
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
    
}
