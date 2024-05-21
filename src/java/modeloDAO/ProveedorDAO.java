
package modeloDAO;

import config.Conexion;
import interfaces.IProveedorDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelos.Proveedor;
import modelos.Usuario;

public class ProveedorDAO implements IProveedorDAO{
    
    
    @Override
    public List Listar(){
        Conexion cn= new Conexion();
        ArrayList<Proveedor> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_proveedores() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    Proveedor prov = new Proveedor();
                    prov.setIdProvedor(rs.getInt("IDPROVEEDOR"));
                    prov.setNombre(rs.getString("NOMBRE"));
                    prov.setAsesor(rs.getString("ASESOR"));
                    prov.setEmail(rs.getString("EMAIL"));
                    prov.setTelefono(rs.getString("TELEFONO"));
                    prov.setUbicacion(rs.getString("UBICACION")); 
                    lista.add(prov);
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
    public Proveedor ListarProveedorUnico(int idProveedor){
       Proveedor prov = new Proveedor();
       Conexion cn= new Conexion();
       try (Connection con= cn.getConnection()) {
           
           ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_ProveedoreUni(?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                cstmt.setInt(2, idProveedor);
                
                
                // Ejecutar la funcion
                cstmt.execute();
                
                //Obtenemos el cursor que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                while (rs.next()){
                    prov.setIdProvedor(rs.getInt("IDPROVEEDOR"));
                    prov.setNombre(rs.getString("NOMBRE"));
                    prov.setAsesor(rs.getString("ASESOR"));
                    prov.setEmail(rs.getString("EMAIL"));
                    prov.setTelefono(rs.getString("TELEFONO"));
                    prov.setUbicacion(rs.getString("UBICACION")); 
                }

                System.out.println("Funcion ejecutada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al llamar a la funcion: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
        return prov;
    }
    
    @Override
    public boolean AgregarProveedor(Proveedor voProveedor){
        
       Conexion cn= new Conexion();
       
       try (Connection con = cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call INSERTAR_PROVEEDORES(?, ?, ?, ?, ? )}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setString(1, voProveedor.getNombre());
                cstmt.setString(2, voProveedor.getAsesor());
                cstmt.setString(3, voProveedor.getUbicacion());
                cstmt.setString(4, voProveedor.getEmail());
                cstmt.setString(5, voProveedor.getTelefono());
                
                
                // Ejecutar la funcion
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
    public boolean EliminarProveedor(int idProveedor){
       
       Conexion cn= new Conexion();
        
       try (Connection con= cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call ELIMINAR_PROVEEDOR(?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, idProveedor);
                
                
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
    public boolean ActualizarProveedor(Proveedor voProveedor){
        
       Conexion cn = new Conexion();
       
       try (Connection con = cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call modificar_proveedor(?, ?, ?, ?, ?, ? )}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, voProveedor.getIdProvedor());
                cstmt.setString(2, voProveedor.getNombre());
                cstmt.setString(3, voProveedor.getAsesor());
                cstmt.setString(4, voProveedor.getUbicacion());
                cstmt.setString(5, voProveedor.getEmail());
                cstmt.setString(6, voProveedor.getTelefono());
                
                
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
