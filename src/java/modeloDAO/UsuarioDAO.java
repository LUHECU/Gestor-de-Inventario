
package modeloDAO;

import config.Conexion;
import interfaces.IUsuarioDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelos.Usuario;

public class UsuarioDAO implements IUsuarioDAO{  
    
    @Override
    public List Listar(){
        Conexion cn= new Conexion();
        ArrayList<Usuario> lista = new ArrayList<>();

        try (Connection con = cn.getConnection()) {
            ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call listar_usuarios() }")) {
                // Establecer los valores de los parámetros
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                
                // Ejecutar el procedimiento almacenado
                cstmt.execute();
                
                //Obtenemos el cursos que nos devuelve la funcion
                rs = (ResultSet)cstmt.getObject(1);
                
                //Recorremos y mostramos cada fila almacenada en el rs
                
                while (rs.next()){
                    Usuario us = new Usuario();
                    us.setIdUsuario(rs.getInt("idUsuario"));
                    us.setCedula(rs.getString("cedula"));
                    us.setNombre(rs.getString("nombre"));
                    us.setRol(rs.getString("rol"));
                    us.setEmail(rs.getString("email"));
                    us.setContrasena( rs.getString("contrasena")); 
                    lista.add(us);
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
    public Usuario ListarUsuarioUnico(int idUsuario){
       Usuario us = new Usuario();
       Conexion cn= new Conexion();
       try (Connection con= cn.getConnection()) {
           
           ResultSet rs;
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{? = call Listar_UsuarioUni(?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.registerOutParameter(1, Types.REF_CURSOR);
                cstmt.setInt(2, idUsuario);
                
                
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

                System.out.println("Funcion ejecutada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al llamar a la funcion: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
        return us;
    }
    
    @Override
    public boolean AgregarUsuario(Usuario voUsuario){
        
       Conexion cn= new Conexion();
       
       try (Connection con = cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call INSERTAR_USUARIO(?, ?, ?, ?, ? )}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setString(1, voUsuario.getCedula());
                cstmt.setString(2, voUsuario.getNombre());
                cstmt.setString(3, voUsuario.getRol());
                cstmt.setString(4, voUsuario.getEmail());
                cstmt.setString(5, voUsuario.getContrasena());
                
                
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
    public boolean EliminarUsuario(int idUsuario){
       
       Conexion cn= new Conexion();
        
       try (Connection con= cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call ELIMINAR_Usuario(?)}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, idUsuario);
                
                
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
    public boolean ActualizarUsuario(Usuario voUsuario){
        
       Conexion cn = new Conexion();
       
       try (Connection con = cn.getConnection()) {
            // Llamada de la funcion
            try (CallableStatement cstmt = con.prepareCall("{ call modificar_usuario(?, ?, ?, ?, ?, ? )}")) {
                // Establecer los valores de los parámetros
                
                cstmt.setInt(1, voUsuario.getIdUsuario());
                cstmt.setString(2, voUsuario.getCedula());
                cstmt.setString(3, voUsuario.getNombre());
                cstmt.setString(4, voUsuario.getRol());
                cstmt.setString(5, voUsuario.getEmail());
                cstmt.setString(6, voUsuario.getContrasena());
                
                
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
