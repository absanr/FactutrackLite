/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.ClienteDAO;
import modelo.Cliente;
import java.util.List;
/**
 *
 * @author Roger
 */
public class ClienteLogica {
    private ClienteDAO clienteDAO;

    public ClienteLogica() {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean agregarCliente(Cliente cliente) {
        // Valida datos antes de agregar
        if (cliente.getDni().isEmpty() || cliente.getNombre().isEmpty()) {
            return false; // Información incompleta
        }
        return clienteDAO.insertarCliente(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clienteDAO.obtenerTodosLosClientes();
    }

    public boolean actualizarCliente(Cliente cliente) {
        return clienteDAO.actualizarCliente(cliente);
    }

    public boolean eliminarCliente(int idCliente) {
        return clienteDAO.eliminarCliente(idCliente);
    }

    public int obtenerIdUsuarioPorDniOId(String idODni) {
        try {
            return clienteDAO.obtenerIdPorDniOId(idODni);
        } catch (Exception e) {
            System.err.println("Error al obtener el ID del usuario: " + e.getMessage());
            return -1; // Retornar -1 si hay un error o no se encuentra
        }
    }

    public Cliente obtenerClientePorId(int idUsuario) {
        try {
            return clienteDAO.obtenerClientePorId(idUsuario);
        } catch (Exception e) {
            System.err.println("Error al obtener el cliente por ID: " + e.getMessage());
            return null; // Retornar null si hay un error
        }
    }

    public Cliente obtenerClientePorDniOId(String idODni) {
        try {
            return clienteDAO.obtenerClientePorDniOId(idODni);
        } catch (Exception e) {
            System.err.println("Error al obtener el cliente por DNI o ID: " + e.getMessage());
            return null; // Retornar null si hay un error
        }
    }


}
