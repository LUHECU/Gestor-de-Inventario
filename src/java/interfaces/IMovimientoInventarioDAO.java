
package interfaces;

import java.util.List;
import modelos.Usuario;

public interface IMovimientoInventarioDAO {
    public List ListarMovimientos();
    public Usuario InicioSesion(String cedula, String contrasena);
}
