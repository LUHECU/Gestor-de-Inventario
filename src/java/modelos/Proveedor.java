package modelos;

public class Proveedor {
  
    private int idProvedor;
    private String nombre;
    private String ubicacion;
    private String telefono;
    private String email;
    private String asesor;

    public Proveedor() {
    }

    public Proveedor(int idProvedor, String nombre, String ubicacion, String telefono, String email, String asesor) {
        this.idProvedor = idProvedor;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.email = email;
        this.asesor = asesor;
    }

    public int getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(int idProvedor) {
        this.idProvedor = idProvedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }
    
    
}
