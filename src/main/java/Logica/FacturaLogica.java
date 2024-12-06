/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.FacturaDAO;
import Beans.Factura;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Roger
 */
public class FacturaLogica {
    
    private FacturaDAO facturaDAO;

    public FacturaLogica() {
        this.facturaDAO = new FacturaDAO();
    }

    public boolean generarFactura(Factura factura) {
        if (factura.getMonto() <= 0) {
            return false; // Monto inválido
        }
        return facturaDAO.insertarFactura(factura);
    }

    public List<Factura> obtenerFacturasPorUsuario(int idUsuario) {
        return facturaDAO.obtenerFacturasPorUsuario(idUsuario);
    }

    public boolean pagarFactura(int idFactura) {
        return facturaDAO.actualizarEstadoFactura(idFactura, "Pagado");
    }

    public boolean cancelarFactura(int idFactura) {
        return facturaDAO.cancelarFactura(idFactura);
    }

    public List<Factura> obtenerFacturasPendientesPorUsuario(int idUsuario) {
        return facturaDAO.obtenerFacturasPorEstadoYUsuario(idUsuario, "Pendiente");
    }

    public boolean actualizarEstadoPagoParcial(int idFactura, double montoPagado) {
        return facturaDAO.actualizarPagoParcial(idFactura, montoPagado);
    }

    /**
     * Genera una fecha de vencimiento (+30 días desde la emisión).
     */
    private String generarFechaVencimiento(String fechaEmision) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaEmision);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Registra un consumo y genera una factura asociada.
     * @param idUsuario ID del usuario.
     * @param consumoMensual Consumo mensual en m³.
     * @param fechaSeleccionada Fecha de consumo.
     * @return "success" si todo es exitoso, de lo contrario, un mensaje de error.
     */
    public String registrarConsumoYFactura(int idUsuario, double consumoMensual, Date fechaSeleccionada) {
        try {
            // Fecha de emisión (consumo)
            String fechaEmision = (fechaSeleccionada != null)
                    ? new SimpleDateFormat("yyyy-MM-dd").format(fechaSeleccionada)
                    : new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // Generar factura
            Factura factura = new Factura();
            factura.setIdUsuario(idUsuario);
            factura.setFechaEmision(fechaEmision);
            factura.setFechaVencimiento(generarFechaVencimiento(fechaEmision));
            factura.setMonto(consumoMensual * 1.5); 
            factura.setMontoPendiente(consumoMensual * 1.5); 
            factura.setEstadoPago("Pendiente");

            // Insertar la factura
            if (!facturaDAO.insertarFactura(factura)) {
                return "Error al generar la factura.";
            }

            return "success";
        } catch (Exception e) {
            System.err.println("Error al registrar consumo y factura: " + e.getMessage());
            return "Error inesperado al registrar consumo y factura.";
        }
    }

    public Factura obtenerFacturaPorId(int idFactura) {
        try {
            return facturaDAO.obtenerFacturaPorId(idFactura);
        } catch (Exception e) {
            System.err.println("Error al obtener la factura por ID: " + e.getMessage());
            return null;
        }
    }

    public List<Factura> obtenerTodasLasFacturasPendientes() {
        return facturaDAO.obtenerFacturasPorEstado("Pendiente");
    }

    public boolean registrarPago(int idFactura, double montoPagado) {
        Factura factura = facturaDAO.obtenerFacturaPorId(idFactura);

        if (factura == null) {
            System.err.println("Factura no encontrada con ID: " + idFactura);
            return false;
        }

        double montoPendiente = factura.getMontoPendiente();

        if (montoPagado > montoPendiente) {
            System.err.println("El monto pagado excede el monto pendiente.");
            return false; 
        }

        if (montoPagado == montoPendiente) {
            // Pago completo
            factura.setMontoPendiente(0);
            factura.setEstadoPago("Pagado");
        } else {
            // Pago parcial
            factura.setMontoPendiente(montoPendiente - montoPagado);
            factura.setEstadoPago("Parcial");
        }

        return facturaDAO.actualizarFactura(factura);
    }

    public List<Factura> obtenerTodasLasFacturas() {
        return facturaDAO.obtenerTodasLasFacturas();
    }

    // Nuevos métodos para obtener facturas Pendientes o Parciales
    public List<Factura> obtenerFacturasPendientesYParciales() {
        return facturaDAO.obtenerFacturasPendientesYParciales();
    }

    public List<Factura> obtenerFacturasPendientesYParcialesPorUsuario(int idUsuario) {
        return facturaDAO.obtenerFacturasPendientesYParcialesPorUsuario(idUsuario);
    }

    public int contarFacturasPorEstado(String estado) {
        try {
            // Obtenemos la lista de facturas por estado y contamos cuántas hay
            List<Factura> facturas = obtenerFacturasPorEstado(estado);
            return facturas.size();
        } catch (Exception e) {
            System.err.println("Error al contar facturas por estado: " + e.getMessage());
            return 0;
        }
    }

    public List<Factura> obtenerFacturasPorEstado(String estado) {
        try {
            return facturaDAO.obtenerFacturasPorEstado(estado);
        } catch (Exception e) {
            System.err.println("Error al obtener facturas por estado: " + e.getMessage());
            return null;
        }
    }

}
