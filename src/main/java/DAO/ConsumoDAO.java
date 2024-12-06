/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Beans.Consumo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Roger
 */
public class ConsumoDAO {
    /**
     * Inserta un registro de consumo en la tabla "consumos".
     * @param consumo Objeto Consumo con los datos a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarConsumo(Consumo consumo) {
        String sql = "INSERT INTO consumos (idUsuario, mes, consumoMensual) VALUES (?, ?, ?)";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, consumo.getIdUsuario());
            ps.setString(2, consumo.getMes());
            ps.setDouble(3, consumo.getConsumoMensual());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene una lista de consumos por usuario según su ID.
     * @param idUsuario ID del usuario.
     * @return Lista de objetos Consumo asociados al usuario.
     */
    public List<Consumo> obtenerConsumosPorUsuario(int idUsuario) {
        List<Consumo> listaConsumos = new ArrayList<>();
        String sql = "SELECT * FROM consumos WHERE idUsuario = ?";

        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Consumo consumo = new Consumo();
                consumo.setIdConsumo(rs.getInt("idConsumo"));
                consumo.setIdUsuario(rs.getInt("idUsuario"));
                consumo.setMes(rs.getString("mes"));
                consumo.setConsumoMensual(rs.getDouble("consumoMensual"));

                listaConsumos.add(consumo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaConsumos;
    }

    /**
     * Elimina un registro de consumo por su ID.
     * @param idConsumo ID del consumo a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarConsumo(int idConsumo) {
        String sql = "DELETE FROM consumos WHERE idConsumo = ?";
        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idConsumo);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Consumo> obtenerTodosLosConsumos() {
        List<Consumo> listaConsumos = new ArrayList<>();
        String sql = "SELECT * FROM consumos";

        try (Connection con = ConexionSQL.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Consumo consumo = new Consumo();
                consumo.setIdConsumo(rs.getInt("idConsumo"));
                consumo.setIdUsuario(rs.getInt("idUsuario"));
                consumo.setMes(rs.getString("mes"));
                consumo.setConsumoMensual(rs.getDouble("consumoMensual"));

                listaConsumos.add(consumo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaConsumos;
    }
    
    

}
