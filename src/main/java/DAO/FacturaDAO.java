/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import modelo.Factura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Roger
 */
public class FacturaDAO {
    public boolean insertarFactura(Factura factura) {
        String sql = "INSERT INTO facturas (idUsuario, fechaEmision, fechaVencimiento, monto, estadoPago, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, factura.getIdUsuario());
            ps.setString(2, factura.getFechaEmision());
            ps.setString(3, factura.getFechaVencimiento());
            ps.setDouble(4, factura.getMonto());
            ps.setString(5, factura.getEstadoPago());
            ps.setString(6, "Activa"); // La factura es activa por defecto

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Factura> obtenerFacturasPorUsuario(int idUsuario) {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT * FROM facturas WHERE idUsuario = ? AND status = 'Activa'";

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
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));

                listaFacturas.add(factura);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
        String sql = "SELECT * FROM facturas WHERE idUsuario = ? AND estadoPago = ? AND status = 'Activa'";

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
        String sql = "UPDATE facturas SET monto = monto - ?, estadoPago = CASE WHEN monto - ? <= 0 THEN 'Pagado' ELSE 'Parcial' END WHERE idFactura = ? AND status = 'Activa'";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, montoPagado); // Reducir el monto
            ps.setDouble(2, montoPagado); // Determinar el estado
            ps.setInt(3, idFactura);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar pago parcial: " + e.getMessage());
            return false;
        }
    }
    
    public Factura obtenerFacturaPorId(int idFactura) {
        String sql = "SELECT * FROM facturas WHERE idFactura = ?";
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
                factura.setEstadoPago(rs.getString("estadoPago"));
                factura.setStatus(rs.getString("status"));
                return factura;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener factura por ID: " + e.getMessage());
        }
        return null; // Si no se encuentra o hay un error
    }



}
