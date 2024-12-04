/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.FacturaDAO;
import modelo.Factura;
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
        try {
            return facturaDAO.obtenerFacturasPorEstadoYUsuario(idUsuario, "Pendiente");
        } catch (Exception e) {
            System.err.println("Error al obtener facturas pendientes: " + e.getMessage());
            return null; // Retornar null si hay un error
        }
    }

    public boolean actualizarEstadoPagoParcial(int idFactura, double montoPagado) {
        try {
            return facturaDAO.actualizarPagoParcial(idFactura, montoPagado);
        } catch (Exception e) {
            System.err.println("Error al actualizar el pago parcial: " + e.getMessage());
            return false;
        }
    }


}
