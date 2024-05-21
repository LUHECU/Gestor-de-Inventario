
package interfaces;

import java.util.List;
import modelos.Producto;
import modelos.Usuario;


public interface IInicioDAO {
    public Usuario InicioSesion(String cedula, String contrasena);
    public Producto calcularCantidadProductosPorProveedor(int idProveedor);
    public List ListarNomProveedores();
    public List vista1();
    public List vista2();
    public List vista3();
}
