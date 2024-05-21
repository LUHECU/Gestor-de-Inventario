
package interfaces;

import java.util.List;
import modelos.Proveedor;
import modelos.Usuario;


public interface IProveedorDAO {
    public List Listar();
    public Proveedor ListarProveedorUnico(int idProveedor);
    public Usuario InicioSesion(String cedula, String contrasena);
    public boolean AgregarProveedor(Proveedor voProveedor);
    public boolean EliminarProveedor(int idProveedor);
    public boolean ActualizarProveedor(Proveedor voProveedor);
}
