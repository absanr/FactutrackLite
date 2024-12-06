/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.ConsumoDAO;
import Beans.Consumo;
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
            System.err.println("Error: El consumo mensual debe ser mayor a 0. Valor proporcionado: " + consumo.getConsumoMensual());
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

    public List<Consumo> obtenerTodosLosConsumos() {
        return consumoDAO.obtenerTodosLosConsumos();
    }

    
    
}
