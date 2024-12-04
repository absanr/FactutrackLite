/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DERICK ALEXIS
 */
public class ClienteDAO {
    // Método para insertar un nuevo cliente
    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (dni, nombre, direccion, telefono, EstadoServicio, tieneMedidor, fechaInstalacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getEstadoServicio()); 
            ps.setString(6, cliente.getTieneMedidor());   
            ps.setString(7, cliente.getFechaInstalacion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener todos los clientes de la tabla
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection con = ConexionSQL.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdUsuario(rs.getInt("idUsuario")); 
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstadoServicio(rs.getString("EstadoServicio")); 
                cliente.setTieneMedidor(rs.getString("tieneMedidor"));    
                cliente.setFechaInstalacion(rs.getString("fechaInstalacion"));

                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los clientes: " + e.getMessage());
        }

        return listaClientes;
    }

    // Método para actualizar un cliente existente
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET dni = ?, nombre = ?, direccion = ?, telefono = ?, EstadoServicio = ?, tieneMedidor = ?, fechaInstalacion = ? WHERE idUsuario = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getEstadoServicio()); 
            ps.setString(6, cliente.getTieneMedidor());   
            ps.setString(7, cliente.getFechaInstalacion());
            ps.setInt(8, cliente.getIdUsuario());         

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un cliente por su ID
    public boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM clientes WHERE idUsuario = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
    
    public int obtenerIdPorDniOId(String idODni) {
        String sql = "SELECT idUsuario FROM clientes WHERE idUsuario = ? OR dni = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Configurar parámetros para búsqueda
            ps.setString(1, idODni); // Buscar por ID
            ps.setString(2, idODni); // Buscar por DNI

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idUsuario");
            } else {
                return -1; // Si no se encuentra
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener ID por DNI o ID: " + e.getMessage());
            return -1;
        }
    }

    public Cliente obtenerClientePorId(int idUsuario) {
        String sql = "SELECT * FROM clientes WHERE idUsuario = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdUsuario(rs.getInt("idUsuario"));
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstadoServicio(rs.getString("EstadoServicio"));
                cliente.setTieneMedidor(rs.getString("tieneMedidor"));
                cliente.setFechaInstalacion(rs.getString("fechaInstalacion"));

                return cliente;
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
        }

        return null; // Retornar null si no se encuentra
    }

    public Cliente obtenerClientePorDniOId(String idODni) {
        String sql = "SELECT * FROM clientes WHERE idUsuario = ? OR dni = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idODni); // Buscar por ID
            ps.setString(2, idODni); // Buscar por DNI
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdUsuario(rs.getInt("idUsuario"));
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstadoServicio(rs.getString("EstadoServicio"));
                cliente.setTieneMedidor(rs.getString("tieneMedidor"));
                cliente.setFechaInstalacion(rs.getString("fechaInstalacion"));

                return cliente;
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por DNI o ID: " + e.getMessage());
        }

        return null; // Retornar null si no se encuentra
    }


}
