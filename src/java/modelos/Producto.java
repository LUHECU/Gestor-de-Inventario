package modelos;


public class Producto {
    
    private int idProducto;
    private int idProveedor;
    private int cantidad;
    private String nombre;
    private float costo;
    private float precioVenta;
    private String nombreProveedor;
    private String nombreUsuario;
    private int calcularCantidadProductosPorProveedor;
    private String ubicacion;
    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Producto() {
    }

    public Producto(int idProducto, int idProveedor, int cantidad, String nombre, float costo, float precioVenta, String nombreProveedor, String nombreUsuario, int calcularCantidadProductosPorProveedor, String ubicacion) {
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.costo = costo;
        this.precioVenta = precioVenta;
        this.nombreProveedor = nombreProveedor;
        this.nombreUsuario = nombreUsuario;
        this.calcularCantidadProductosPorProveedor = calcularCantidadProductosPorProveedor;
        this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public int getCalcularCantidadProductosPorProveedor() {
        return calcularCantidadProductosPorProveedor;
    }

    public void setCalcularCantidadProductosPorProveedor(int calcularCantidadProductosPorProveedor) {
        this.calcularCantidadProductosPorProveedor = calcularCantidadProductosPorProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    
    
}
