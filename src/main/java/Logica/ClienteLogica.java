/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.ClienteDAO;
import Beans.Cliente;
import java.util.List;
/**
 *
 * @author Roger
 */
public class ClienteLogica {
    private final ClienteDAO clienteDAO;

    public ClienteLogica() {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean agregarCliente(Cliente cliente) {
        if (validarCliente(cliente)) {
            return clienteDAO.insertarCliente(cliente);
        }
        return false; // Datos inválidos
    }

    public List<Cliente> obtenerClientes() {
        return clienteDAO.obtenerTodosLosClientes();
    }

    public boolean actualizarCliente(Cliente cliente) {
        if (validarCliente(cliente)) {
            return clienteDAO.actualizarCliente(cliente);
        }
        return false; // Datos inválidos
    }

    public boolean eliminarCliente(int idCliente) {
        return clienteDAO.eliminarCliente(idCliente);
    }

    public int obtenerIdUsuarioPorDniOId(String idODni) {
        try {
            return clienteDAO.obtenerIdPorDniOIdONombre(idODni);
        } catch (Exception e) {
            System.err.println("Error al obtener el ID del usuario: " + e.getMessage());
            return -1;
        }
    }

    public Cliente obtenerClientePorId(int idUsuario) {
        try {
            return clienteDAO.obtenerClientePorId(idUsuario);
        } catch (Exception e) {
            System.err.println("Error al obtener el cliente por ID: " + e.getMessage());
            return null;
        }
    }

    public Cliente obtenerClientePorDniOId(String idODni) {
        try {
            return clienteDAO.obtenerClientePorDniOId(idODni);
        } catch (Exception e) {
            System.err.println("Error al obtener el cliente por DNI o ID: " + e.getMessage());
            return null;
        }
    }

    public String obtenerNombrePorId(int idUsuario) {
        Cliente cliente = obtenerClientePorId(idUsuario);
        return cliente != null ? cliente.getNombre() : "Desconocido";
    }

    private boolean validarCliente(Cliente cliente) {
        return cliente.getDni() != null && !cliente.getDni().isEmpty() &&
               cliente.getNombre() != null && !cliente.getNombre().isEmpty();
    }

    public int contarClientes() {
        try {
            // Podemos simplemente contar la cantidad de clientes retornados por obtenerTodosLosClientes()
            List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();
            return clientes.size();
        } catch (Exception e) {
            System.err.println("Error al contar clientes: " + e.getMessage());
            return 0;
        }
    }

    public List<Cliente> obtenerTodosLosClientes() {
        try {
            return clienteDAO.obtenerTodosLosClientes();
        } catch (Exception e) {
            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
            return null;
        }
    }

}
