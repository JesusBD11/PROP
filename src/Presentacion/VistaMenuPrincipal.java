/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

/**
 * @file VistaMenuPrincipal.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief Panel que gestiona la sección de VistaPrincipal para mostrar el menú principal
 * 
 * Panel que da acceso a las funcionalidades principales del programa: Jugar, consultar el ránking y cambiar de perfil.
 */
 
 /**
 * @class VistaMenuPrincipal
 * @brief Clase que extiende JPanel que inicializa, gestiona y modifica el panel de la VistaPrincipal para mostrar el menú principal.
 */
public class VistaMenuPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form VistaMenuPrincipal
     */
    private VistaPrincipal vp;
    
    public VistaMenuPrincipal() {
        initComponents();
    }

    VistaMenuPrincipal(VistaPrincipal vistaPrincipal) {
       
        vp = vistaPrincipal;  
         initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JLabel();
        Jugar = new javax.swing.JButton();
        perfiles = new javax.swing.JButton();
        ranking = new javax.swing.JButton();

        jLabel1.setText("Bienvenido:");

        //String text = vp.getUsuario();
        nombreUsuario.setText("Default");

        Jugar.setText("Jugar");
        Jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JugarActionPerformed(evt);
            }
        });

        perfiles.setText("Cambiar Perfil");
        perfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilesActionPerformed(evt);
            }
        });

        ranking.setText("Ranking");
        ranking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(nombreUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Jugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ranking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(perfiles, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreUsuario))
                .addGap(34, 34, 34)
                .addComponent(Jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ranking, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(perfiles, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void perfilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilesActionPerformed
        System.out.println("Perfiles under construction.....");
        vp.changeToPerfiles();
    }//GEN-LAST:event_perfilesActionPerformed

    private void JugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JugarActionPerformed
        System.out.println("Jugar pressed!!");
        vp.changeToPartida();
    }//GEN-LAST:event_JugarActionPerformed

    private void rankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankingActionPerformed
       System.out.println("Ranking pressed!!");
       vp.changeToRanking();
    }//GEN-LAST:event_rankingActionPerformed
    public void setUsuario(String User)
    {
        nombreUsuario.setText(User);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Jugar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nombreUsuario;
    private javax.swing.JButton perfiles;
    private javax.swing.JButton ranking;
    // End of variables declaration//GEN-END:variables
}
