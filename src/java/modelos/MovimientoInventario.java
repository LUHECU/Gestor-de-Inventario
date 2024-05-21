package modelos;

public class MovimientoInventario {
    
    private int IdMovimiento;
    private int IdProducto;
    private String NombreUsuario; 
    private String fecha;
    private String producto;
    private String usuario;
    private String Nombre; 
    private int Cantidad; 
    private double Costo; 
    private double precioVenta; 
    private String TipoMovimiento;

    public MovimientoInventario() {
    }

    public MovimientoInventario(int IdMovimiento, int IdProducto, String NombreUsuario, String fecha, String producto, String usuario, String Nombre, int Cantidad, double Costo, double precioVenta, String TipoMovimiento) {
        this.IdMovimiento = IdMovimiento;
        this.IdProducto = IdProducto;
        this.NombreUsuario = NombreUsuario;
        this.fecha = fecha;
        this.producto = producto;
        this.usuario = usuario;
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
        this.Costo = Costo;
        this.precioVenta = precioVenta;
        this.TipoMovimiento = TipoMovimiento;
    }

    public int getIdMovimiento() {
        return IdMovimiento;
    }

    public void setIdMovimiento(int IdMovimiento) {
        this.IdMovimiento = IdMovimiento;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getTipoMovimiento() {
        return TipoMovimiento;
    }

    public void setTipoMovimiento(String TipoMovimiento) {
        this.TipoMovimiento = TipoMovimiento;
    }
    
    
    
}
