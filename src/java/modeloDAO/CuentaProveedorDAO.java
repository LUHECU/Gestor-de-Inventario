
package modeloDAO;

import config.Conexion;
import interfaces.ICuentaProveedorDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelos.CuentaProveedor;
import modelos.Usuario;

public class CuentaProveedorDAO  implements ICuentaProveedorDAO{
    
   @Override
    public List ListarCuentas(){
        Conexion cn= new Conexion();
        ArrayList<CuentaProveedor> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_cuentas_proveedores() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    CuentaProveedor cuenta = new CuentaProveedor();
                    cuenta.setIdFacProvedor(rs.getInt("IDFACPROVEEDOR"));
                    cuenta.setIdProvedor(rs.getInt("ID_PROVEEDOR"));
                    cuenta.setProveedor(rs.getString("PROVEEDOR"));
                    cuenta.setMonto(rs.getInt("MONTO"));
                    cuenta.setFechaEmision(rs.getString("FECHA_EMISION"));
                    cuenta.setMetodoPago(rs.getString("METODOPAGO")); 
                    lista.add(cuenta);
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
    
    
    @Override
    public CuentaProveedor ListarCuentaProveedorUnica(int idCuentaProveedor){
       CuentaProveedor cuenta = new CuentaProveedor();
       Conexion cn= new Conexion();
       try (Connection con= cn.getConnection()) {
           
           ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_cuentas_proveedores_uni(?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                cstmt.setInt(2, idCuentaProveedor);
                
                
                // Ejecutar la funcion
                cstmt.execute();
                
                //Obtenemos el cursor que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                while (rs.next()){
                    cuenta.setIdFacProvedor(rs.getInt("IDFACPROVEEDOR"));
                    cuenta.setIdProvedor(rs.getInt("ID_PROVEEDOR"));
                    cuenta.setMonto(rs.getInt("MONTO"));
                    cuenta.setFechaEmision(rs.getString("FECHA_EMISION"));
                    cuenta.setMetodoPago(rs.getString("METODOPAGO")); 
                }

                System.out.println("Funcion ejecutada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al llamar a la funcion: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
        return cuenta;
    }
    
    
    public boolean EliminarCuenta(int idCuentaProveedor){
       
       Conexion cn= new Conexion();
        
       try (Connection con= cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call eliminar_cuenta_proveedor(?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, idCuentaProveedor);
                
                
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
    public boolean ActualizarCuentaProveedor(CuentaProveedor voCuentaProveedor){
        
       Conexion cn = new Conexion();
       
       try (Connection con = cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call modificar_cuenta_proveedor(?, ?, ? )}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, voCuentaProveedor.getIdFacProvedor());
                cstmt.setFloat(2, voCuentaProveedor.getMonto());
                cstmt.setString(3, voCuentaProveedor.getMetodoPago());
                
                
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
    
    
    
}
