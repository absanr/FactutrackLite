package Beans;
public class Cliente {
    private int idUsuario;
    private String dni;
    private String nombre;
    private String direccion;
    private String telefono;
    private String estadoServicio; // Activo o Suspendido
    private String tieneMedidor;   // Sí o No
    private String fechaInstalacion; // Formato "YYYY-MM-DD"

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(String estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public String getTieneMedidor() {
        return tieneMedidor;
    }

    public void setTieneMedidor(String tieneMedidor) {
        this.tieneMedidor = tieneMedidor;
    }

    public String getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(String fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

   
    
    
}
