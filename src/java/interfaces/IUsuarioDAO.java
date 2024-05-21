
package interfaces;

import java.util.List;
import modelos.Usuario;

public interface IUsuarioDAO {
    public List Listar();
    public Usuario ListarUsuarioUnico(int idUsuario);
    public Usuario InicioSesion(String cedula, String contrasena);
    public boolean AgregarUsuario(Usuario voUsuario);
    public boolean EliminarUsuario(int idUsuario);
    public boolean ActualizarUsuario(Usuario voUsuario);
}
