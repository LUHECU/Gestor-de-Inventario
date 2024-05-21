
package modelos;

public class CuentaProveedor {
    
    private int idFacProvedor;
    private int idProvedor;
    private String fechaEmision;
    private float monto;
    private String metodoPago;
    private String proveedor;
    
    public CuentaProveedor(){}
    
    public CuentaProveedor(int idFacProvedor, int idProvedor, String fechaEmision, float monto, String metodoPago, String proveedor){
        this.idFacProvedor = idFacProvedor;
        this.idProvedor = idProvedor;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.proveedor = proveedor;
    }

    public int getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(int idProvedor) {
        this.idProvedor = idProvedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    public int getIdFacProvedor() {
        return idFacProvedor;
    }

    public void setIdFacProvedor(int idFacProvedor) {
        this.idFacProvedor = idFacProvedor;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    
    
    
}
