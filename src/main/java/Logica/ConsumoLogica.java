/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.ConsumoDAO;
import modelo.Consumo;
import java.util.List;
/**
 *
 * @author Roger
 */
public class ConsumoLogica {
    private ConsumoDAO consumoDAO;

    public ConsumoLogica() {
        this.consumoDAO = new ConsumoDAO();
    }

    public boolean registrarConsumo(Consumo consumo) {
        if (consumo.getConsumoMensual() <= 0) {
            return false; // Consumo inválido
        }
        return consumoDAO.insertarConsumo(consumo);
    }

    public List<Consumo> obtenerConsumosPorUsuario(int idUsuario) {
        return consumoDAO.obtenerConsumosPorUsuario(idUsuario);
    }

    public boolean eliminarConsumo(int idConsumo) {
        return consumoDAO.eliminarConsumo(idConsumo);
    }
}
