package Beans;

public class Factura {
    private int idFactura;
    private int idUsuario;
    private String fechaEmision;
    private String fechaVencimiento;
    private double monto;
    private double montoPendiente; // Asegúrate de que sea double
    private String estadoPago;
    private String status;

    // Métodos Getter y Setter
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(String fechaEmision) { this.fechaEmision = fechaEmision; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public double getMontoPendiente() { return montoPendiente; }
    public void setMontoPendiente(double montoPendiente) { this.montoPendiente = montoPendiente; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
}
