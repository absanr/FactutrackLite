/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Logica.ClienteLogica;
import Logica.FacturaLogica;
import Beans.Factura;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Ventana para la gestión de pagos.
 */

/**
 *
 * @author DERICK ALEXIS
 */
public class PagosFrame extends javax.swing.JFrame {
    private Dashboard v2;

    public PagosFrame() {
        initComponents();
        configurarModeloTabla(); // Configurar el modelo de tabla
        this.setLocationRelativeTo(this);
        configurarTabla(); // Configurar eventos de la tabla
        cargarDatosTabla(-1); // Cargar datos iniciales
        jLabel_nombre_cliente.setText("Todos los clientes con deuda (Pendiente/Parcial)"); 
    }

    public void setV2(Dashboard v2) {
        this.v2 = v2;
    }

    private void configurarModeloTabla() {
        jTable_pagos.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {},
        new String [] {
            "ID Factura", "Fecha Emisión", "Monto", "Monto Pendiente", "Estado Pago", "Nombre Cliente"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
        };

        @Override
        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false; // Hacer todas las celdas no editables
        }
    });
    }

    /**
     * Configura la tabla para detectar cambios en la selección de filas.
     */
    private void configurarTabla() {
        jTable_pagos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarMontoSeleccionado();
                }
            }
        });
    }

    /**
     * Actualiza el campo "Monto a pagar" basado en la fila seleccionada.
     */
    private void actualizarMontoSeleccionado() {
        int filaSeleccionada = jTable_pagos.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) jTable_pagos.getModel();
            double monto = (double) modelo.getValueAt(filaSeleccionada, 2); // Columna del monto
            jTextField_monto_a_pagar.setText(String.valueOf(monto));
        }
    }

    /**
    * Carga las facturas con estado "Pendiente" o "Parcial" en la tabla.
    *
    * @param idUsuario ID del usuario. Si es -1, carga facturas de todos los usuarios.
    */
    private void cargarDatosTabla(int idUsuario) {
        DefaultTableModel modelo = (DefaultTableModel) jTable_pagos.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        FacturaLogica facturaLogica = new FacturaLogica();
        ClienteLogica clienteLogica = new ClienteLogica();
        List<Factura> facturasDeuda;

        // Aquí utilizamos métodos que obtengan facturas con estado "Pendiente" o "Parcial"
        // Debes haber creado estos métodos en FacturaLogica y FacturaDAO para filtrar ambos estados:
        // por ejemplo: obtenerFacturasPendientesYParciales() y obtenerFacturasPendientesYParcialesPorUsuario(idUsuario)

        if (idUsuario == -1) {
            facturasDeuda = facturaLogica.obtenerFacturasPendientesYParciales();
            jLabel_nombre_cliente.setText("Todos los clientes con deuda (Pendiente/Parcial)");
        } else {
            facturasDeuda = facturaLogica.obtenerFacturasPendientesYParcialesPorUsuario(idUsuario);
            var cliente = clienteLogica.obtenerClientePorId(idUsuario);
            if (cliente != null) {
                jLabel_nombre_cliente.setText("Cliente: " + cliente.getNombre());
            } else {
                jLabel_nombre_cliente.setText("Cliente no encontrado");
            }
        }

        for (Factura factura : facturasDeuda) {
            String nombreCliente = clienteLogica.obtenerClientePorId(factura.getIdUsuario()).getNombre();

            modelo.addRow(new Object[] {
                factura.getIdFactura(),
                factura.getFechaEmision(),
                factura.getMonto(),
                factura.getMontoPendiente(), 
                factura.getEstadoPago(),
                nombreCliente
            });
        }

        if (modelo.getRowCount() == 0) {
            jTextField_monto_a_pagar.setText("");
        }
    }









    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_buscar_id_dni = new javax.swing.JTextField();
        jButton_buscar = new javax.swing.JButton();
        jLabel_nombre_cliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_pagos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton_atras = new javax.swing.JButton();
        jTextField_monto_a_pagar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton_registrarPago = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setText("Buscar por idusuario o DNI:");

        jButton_buscar.setBackground(new java.awt.Color(0, 153, 204));
        jButton_buscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton_buscar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_buscar.setText("Buscar");
        jButton_buscar.setBorder(null);
        jButton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscarActionPerformed(evt);
            }
        });

        jLabel_nombre_cliente.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_nombre_cliente)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_buscar_id_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1))
                    .addComponent(jTextField_buscar_id_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_nombre_cliente)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        jTable_pagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_pagos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 340, 770, 160));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 204, 255));
        jLabel10.setText("Dashboard >  Realizar Pagos");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Realizar Pagos");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        jButton_atras.setBackground(new java.awt.Color(255, 102, 102));
        jButton_atras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton_atras.setForeground(new java.awt.Color(255, 255, 255));
        jButton_atras.setText("Atras");
        jButton_atras.setBorder(null);
        jButton_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_atrasActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 100, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 160));
        getContentPane().add(jTextField_monto_a_pagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 120, 40));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setText("Monto a pagar (soles)");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, 20));

        jButton_registrarPago.setBackground(new java.awt.Color(102, 153, 255));
        jButton_registrarPago.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton_registrarPago.setForeground(new java.awt.Color(255, 255, 255));
        jButton_registrarPago.setText("Pagar");
        jButton_registrarPago.setBorder(null);
        jButton_registrarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registrarPagoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_registrarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 110, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_atrasActionPerformed
        // TODO add your handling code here:
        v2.setVisible(true); // Nos regresa atras a la pantalla V2
        this.setVisible(false);
    }//GEN-LAST:event_jButton_atrasActionPerformed

    private void jButton_registrarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_registrarPagoActionPerformed
        // Leer el valor ingresado en el campo de texto
        String montoTexto = jTextField_monto_a_pagar.getText().trim();

        if (montoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el monto a pagar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double montoPagado = Double.parseDouble(montoTexto);

            int filaSeleccionada = jTable_pagos.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una factura de la tabla para registrar el pago.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DefaultTableModel modelo = (DefaultTableModel) jTable_pagos.getModel();
            int idFactura = (int) modelo.getValueAt(filaSeleccionada, 0); 
            double montoPendiente = (double) modelo.getValueAt(filaSeleccionada, 3); 

            if (montoPagado > montoPendiente) {
                JOptionPane.showMessageDialog(this, "El monto ingresado excede el monto pendiente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            FacturaLogica facturaLogica = new FacturaLogica();
            boolean resultado = facturaLogica.registrarPago(idFactura, montoPagado);

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Pago registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            cargarDatosTabla(-1);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el monto a pagar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_registrarPagoActionPerformed

    private void jButton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscarActionPerformed
        // TODO add your handling code here:
        String idODni = jTextField_buscar_id_dni.getText().trim();
        ClienteLogica clienteLogica = new ClienteLogica();
        int idUsuario = -1;

        if (!idODni.isEmpty()) {
            idUsuario = clienteLogica.obtenerIdUsuarioPorDniOId(idODni);

            if (idUsuario == -1) {
                JOptionPane.showMessageDialog(this, "No se encontró un usuario con el ID o DNI proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                jLabel_nombre_cliente.setText("Cliente no encontrado");
                return;
            }
        }

        cargarDatosTabla(idUsuario);

        DefaultTableModel modelo = (DefaultTableModel) jTable_pagos.getModel();
        if (modelo.getRowCount() > 0) {
            jTable_pagos.setRowSelectionInterval(0, 0);
            actualizarMontoSeleccionado();
        } else {
            jTextField_monto_a_pagar.setText("");
            JOptionPane.showMessageDialog(this, "No hay facturas pendientes o parciales para este usuario.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_buscarActionPerformed
        
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_atras;
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JButton jButton_registrarPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_nombre_cliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_pagos;
    private javax.swing.JTextField jTextField_buscar_id_dni;
    private javax.swing.JTextField jTextField_monto_a_pagar;
    // End of variables declaration//GEN-END:variables
}
