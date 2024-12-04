/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Consumo {
    private int idConsumo;          // Clave primaria
    private int idUsuario;          // ID del usuario (clave foránea)
    private String mes;             // Ejemplo: "Enero", "Febrero", etc.
    private double consumoMensual;  // En metros cúbicos

    // Getters y Setters
    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getConsumoMensual() {
        return consumoMensual;
    }

    public void setConsumoMensual(double consumoMensual) {
        this.consumoMensual = consumoMensual;
    }
    
}
