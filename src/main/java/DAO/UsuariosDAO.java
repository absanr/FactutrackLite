/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import modelo.Usuario;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Roger
 */
public class UsuariosDAO {
   /*
        public Vector listando_Usuarios() throws Exception {
        Vector lista = new Vector();
        String consultaSql = "SELECT id, nombre_usuario, contrasena FROM categoria";

    try (Connection establecerConexion = ConexionSQL.getConexion();
         Statement prepararConsulta = establecerConexion.createStatement();
         ResultSet rs = prepararConsulta.executeQuery(consultaSql)) {

        while (rs.next()) {
            Usuario nuevoObjeto = new Usuario();
            nuevoObjeto.setId(rs.getInt("id"));
            nuevoObjeto.setNombreUsuario(rs.getString("nombre_usuario"));
            nuevoObjeto.setContrasena(rs.getString("contrasena"));
            lista.add(nuevoObjeto);
        }
    } catch (SQLException e) {
        throw new Exception("Error al listar categorías: " + e.getMessage(), e);
    }
    return lista;
    }
   */
    
    /**
     * Verifica si un usuario con las credenciales dadas existe en la base de datos.
     *
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean encontrarUsuario(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Devuelve true si encuentra el usuario
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario Objeto Usuario con los datos a insertar.
     * @return true si la operación es exitosa, false en caso contrario.
     */
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena) VALUES (?, ?)";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos los usuarios en la base de datos.
     *
     * @return Un vector con objetos Usuario representando cada usuario.
     * @throws Exception Si ocurre algún error durante la consulta.
     */
    public Vector<Usuario> listarUsuarios() throws Exception {
        String sql = "SELECT id, nombre_usuario, contrasena FROM usuarios";
        Vector<Usuario> listaUsuarios = new Vector<>();

        try (Connection con = ConexionSQL.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            throw new Exception("Error al listar usuarios: " + e.getMessage(), e);
        }
        return listaUsuarios;
    }
}


