/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**adminhh
 *
 * @author DERICK ALEXIS
 */
public class FondoPanel extends JPanel { 
    private Image imagen;

    public FondoPanel(String rutaRelativa) {
        // Intenta cargar la imagen desde los recursos
        URL url = getClass().getResource(rutaRelativa);
        if (url != null) {
            this.imagen = new ImageIcon(url).getImage();
        } else {
            System.err.println("No se encontró la imagen: " + rutaRelativa);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Fondo por defecto si la imagen no se cargó
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
