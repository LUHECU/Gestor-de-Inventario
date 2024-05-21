
package interfaces;

import java.util.List;
import modelos.CuentaProveedor;
import modelos.Usuario;

public interface ICuentaProveedorDAO {
    
    public List ListarCuentas();
    public Usuario InicioSesion(String cedula, String contrasena);
    public CuentaProveedor ListarCuentaProveedorUnica(int idCuentaProveedor);
     public boolean ActualizarCuentaProveedor(CuentaProveedor voCuentaProveedor);
    
}
