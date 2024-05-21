package modelos;

public class Usuario {
  
    private int idUsuario;
    private String cedula;
    private String nombre;
    private String rol;
    private String email;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int idUsuario, String cedula, String nombre, String rol, String email, String contrasena) {
        this.idUsuario = idUsuario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.rol = rol;
        this.email = email;
        this.contrasena = contrasena;
    }

    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    
}
