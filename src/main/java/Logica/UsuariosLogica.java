/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.UsuariosDAO;
import Beans.Usuario;
import java.util.Vector;

/**
 *
 * @author Roger
 */
public class UsuariosLogica {
    private UsuariosDAO usuariosDAO;

    public UsuariosLogica() {
        this.usuariosDAO = new UsuariosDAO();
    }

    /**
     * Valida las credenciales de un usuario.
     *
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarCredenciales(String usuario, String contrasena) {
        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            return false; // Información incompleta
        }
        return usuariosDAO.encontrarUsuario(usuario, contrasena);
    }

    /**
     * Registra un nuevo usuario.
     *
     * @param usuario Objeto Usuario con los datos.
     * @return true si el registro es exitoso, false en caso contrario.
     */
    public boolean registrarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getNombreUsuario().isEmpty() || usuario.getContrasena().isEmpty()) {
            return false; // Información incompleta
        }
        return usuariosDAO.insertarUsuario(usuario);
    }

    /**
     * Obtiene una lista de todos los usuarios registrados.
     *
     * @return Un vector con todos los usuarios, o null si ocurre un error.
     */
    public Vector<Usuario> listarUsuarios() {
        try {
            return usuariosDAO.listarUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
