/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

/**
 * @file VistaNuevaPartida.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief Panel que gestiona la sección de VistaPrincipal para jugar una nueva partida
 * 
 * Panel que muestra al usuario las diferentes opciones para crear una nueva partida: crear una partida con un kakuro de la biblioteca o crear un nuevo kakuro y jugarlo.
 */
 
 /**
 * @class VistaNuevaPartida
 * @brief Clase que extiende JPanel que inicializa, gestiona y modifica el panel de la VistaPrincipal para jugar una nueva partida.
 */
public class VistaNuevaPartida extends javax.swing.JPanel {
     private VistaPrincipal vp;
    /**
     * Creates new form VistaCrearKakuro
     */
    public VistaNuevaPartida() {
        initComponents();
    }
    public VistaNuevaPartida(VistaPrincipal vistaPrincipal) {
        initComponents();
        vp = vistaPrincipal;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crearKakuro = new javax.swing.JButton();
        selectKakuro = new javax.swing.JButton();
        volver = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(400, 300));

        crearKakuro.setText("Crear Kakuro");
        crearKakuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearKakuroActionPerformed(evt);
            }
        });

        selectKakuro.setText("Seleccionar Kakuro");
        selectKakuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectKakuroActionPerformed(evt);
            }
        });

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(volver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crearKakuro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectKakuro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(selectKakuro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(crearKakuro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void crearKakuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearKakuroActionPerformed
        System.out.println("crear Kakuro pressed!!!");
        vp.changeToCrearKakuro();
    }//GEN-LAST:event_crearKakuroActionPerformed

    private void selectKakuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectKakuroActionPerformed
        System.out.println("select Kakuro pressed!!!");
        vp.changeToSeleccionarKakuro();
    }//GEN-LAST:event_selectKakuroActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        System.out.println("volver pressed!!!");
        vp.changeToPartida();
    }//GEN-LAST:event_volverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearKakuro;
    private javax.swing.JButton selectKakuro;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
