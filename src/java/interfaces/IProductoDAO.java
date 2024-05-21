
package interfaces;

import java.util.List;
import modelos.Producto;
import modelos.Usuario;

public interface IProductoDAO {
    
    public List ListarProductos();
    public List ListarNomProveedores();
    public Producto ListarProductoUnico(int idProducto);
    public Usuario InicioSesion(String cedula, String contrasena);
    public boolean AgregarProducto(Producto voProducto);
    public boolean EliminarProducto(int idProducto, String nombreUsuario);
    public boolean ActualizarProducto(Producto voProducto);
    public boolean AumentoProducto();
    public boolean RebajaProducto();
    
    
}
