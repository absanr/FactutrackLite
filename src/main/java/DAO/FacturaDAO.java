/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Beans.Factura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Roger
 */
public class FacturaDAO {
    
    public boolean insertarFactura(Factura factura) {
        String sql = "INSERT INTO facturas (idUsuario, fechaEmision, fechaVencimiento, monto, montoPendiente, estadoPago, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, factura.getIdUsuario());
            ps.setString(2, factura.getFechaEmision());
            ps.setString(3, factura.getFechaVencimiento());
            ps.setDouble(4, factura.getMonto());
            ps.setDouble(5, factura.getMontoPendiente());
            ps.setString(6, factura.getEstadoPago());
            ps.setString(7, "Activa");

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar factura: " + e.getMessage());
            return false;
        }
    }

    public List<Factura> obtenerFacturasPorUsuario(int idUsuario) {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT idFactura, idUsuario, fechaEmision, fechaVencimiento, monto, IFNULL(montoPendiente,monto) AS montoPendiente, estadoPago, status " +
                     "FROM facturas WHERE idUsuario = ? AND status = 'Activa'";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdUsuario(rs.getInt("idUsuario"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
                factura.setFechaVencimiento(rs.getString("fechaVencimiento"));
                factura.setMonto(rs.getDouble("monto"));
                factura.setMontoPendiente(rs.getDouble("montoPendiente"));
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));
                listaFacturas.add(factura);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener facturas: " + e.getMessage());
        }

        return listaFacturas;
    }

    public boolean actualizarEstadoFactura(int idFactura, String estado) {
        String sql = "UPDATE facturas SET estadoPago = ? WHERE idFactura = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idFactura);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelarFactura(int idFactura) {
        String sql = "UPDATE facturas SET status = 'Cancelada' WHERE idFactura = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idFactura);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Factura> obtenerFacturasPorEstadoYUsuario(int idUsuario, String estado) {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT idFactura, idUsuario, fechaEmision, fechaVencimiento, monto, IFNULL(montoPendiente,monto) AS montoPendiente, estadoPago, status " +
                     "FROM facturas WHERE idUsuario = ? AND estadoPago = ? AND status = 'Activa'";

        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, estado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdUsuario(rs.getInt("idUsuario"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
                factura.setFechaVencimiento(rs.getString("fechaVencimiento"));
                factura.setMonto(rs.getDouble("monto"));
                factura.setMontoPendiente(rs.getDouble("montoPendiente"));
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));

                listaFacturas.add(factura);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener facturas por estado: " + e.getMessage());
        }

        return listaFacturas;
    }

    public boolean actualizarPagoParcial(int idFactura, double montoPagado) {
        String sql = "UPDATE facturas SET montoPendiente = montoPendiente - ?, " +
                     "estadoPago = CASE WHEN montoPendiente - ? <= 0 THEN 'Pagado' ELSE 'Parcial' END " +
                     "WHERE idFactura = ? AND status = 'Activa'";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, montoPagado);
            ps.setDouble(2, montoPagado);
            ps.setInt(3, idFactura);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar pago parcial: " + e.getMessage());
            return false;
        }
    }

    public Factura obtenerFacturaPorId(int idFactura) {
        String sql = "SELECT idFactura, idUsuario, fechaEmision, fechaVencimiento, monto, IFNULL(montoPendiente,monto) AS montoPendiente, estadoPago, status " +
                     "FROM facturas WHERE idFactura = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idFactura);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdUsuario(rs.getInt("idUsuario"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
                factura.setFechaVencimiento(rs.getString("fechaVencimiento"));
                factura.setMonto(rs.getDouble("monto"));
                factura.setMontoPendiente(rs.getDouble("montoPendiente"));
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));
                return factura;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener factura por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Factura> obtenerFacturasPorEstado(String estado) {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT idFactura, idUsuario, fechaEmision, fechaVencimiento, monto, IFNULL(montoPendiente,monto) AS montoPendiente, estadoPago, status " +
                     "FROM facturas WHERE estadoPago = ? AND status = 'Activa'";

        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdUsuario(rs.getInt("idUsuario"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
                factura.setFechaVencimiento(rs.getString("fechaVencimiento"));
                factura.setMonto(rs.getDouble("monto"));
                factura.setMontoPendiente(rs.getDouble("montoPendiente"));
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));
                listaFacturas.add(factura);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener facturas por estado: " + e.getMessage());
        }

        return listaFacturas;
    }

    public boolean actualizarFactura(Factura factura) {
        String sql = "UPDATE facturas SET montoPendiente = ?, estadoPago = ? WHERE idFactura = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, factura.getMontoPendiente());
            ps.setString(2, factura.getEstadoPago());
            ps.setInt(3, factura.getIdFactura());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la factura: " + e.getMessage());
            return false;
        }
    }

    public List<Factura> obtenerTodasLasFacturas() {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT idFactura, idUsuario, fechaEmision, fechaVencimiento, monto, IFNULL(montoPendiente,monto) AS montoPendiente, estadoPago, status FROM facturas";

        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdUsuario(rs.getInt("idUsuario"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
                factura.setFechaVencimiento(rs.getString("fechaVencimiento"));
                factura.setMonto(rs.getDouble("monto"));
                factura.setMontoPendiente(rs.getDouble("montoPendiente"));
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));

                listaFacturas.add(factura);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaFacturas;
    }

    // Métodos para obtener facturas con estado Pendiente o Parcial (sin filtrar usuario)
    public List<Factura> obtenerFacturasPendientesYParciales() {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT idFactura, idUsuario, fechaEmision, fechaVencimiento, monto, IFNULL(montoPendiente,monto) AS montoPendiente, estadoPago, status " +
                     "FROM facturas WHERE estadoPago IN ('Pendiente','Parcial') AND status = 'Activa'";

        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdUsuario(rs.getInt("idUsuario"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
                factura.setFechaVencimiento(rs.getString("fechaVencimiento"));
                factura.setMonto(rs.getDouble("monto"));
                factura.setMontoPendiente(rs.getDouble("montoPendiente"));
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));
                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener facturas pendientes y parciales: " + e.getMessage());
        }

        return listaFacturas;
    }

    // Métodos para obtener facturas con estado Pendiente o Parcial filtrado por usuario
    public List<Factura> obtenerFacturasPendientesYParcialesPorUsuario(int idUsuario) {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT idFactura, idUsuario, fechaEmision, fechaVencimiento, monto, IFNULL(montoPendiente,monto) AS montoPendiente, estadoPago, status " +
                     "FROM facturas WHERE idUsuario = ? AND estadoPago IN ('Pendiente','Parcial') AND status = 'Activa'";

        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdUsuario(rs.getInt("idUsuario"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
                factura.setFechaVencimiento(rs.getString("fechaVencimiento"));
                factura.setMonto(rs.getDouble("monto"));
                factura.setMontoPendiente(rs.getDouble("montoPendiente"));
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));
                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener facturas pendientes y parciales por usuario: " + e.getMessage());
        }

        return listaFacturas;
    }
}
