package Interfaz;

import java.awt.Color;
import javax.swing.JOptionPane;
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.getContentPane().setBackground(new Color(20,138,156));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnDescLu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonSEcuaciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Segunda Tarea Programada - Algebra IC");

        jButton1.setText("BASES DE R2 Y R3");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnDescLu.setText("Descomposición L.U");
        btnDescLu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescLuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 0));
        jLabel1.setText("Menu Sistema");

        jButtonSEcuaciones.setText("Ax=b");
        jButtonSEcuaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSEcuacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonSEcuaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDescLu, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(116, 116, 116))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btnDescLu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSEcuaciones)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Object entrada = JOptionPane.showInputDialog(null,"Seleccione el tipo de base","Bases",JOptionPane.QUESTION_MESSAGE, null, new Object[]{"R2","R3"},"Seleccione");
        String entradaCaptada="";
        int cantidadCaptada = 0;
        if(entrada != null){
            entradaCaptada = entrada.toString();
            Object cantidad = JOptionPane.showInputDialog(null,"Seleccione la cantidad de vectores","Vectores",JOptionPane.QUESTION_MESSAGE, null, new Object[]{"1","2","3","4","5"},"Seleccione");
            if(cantidad != null)
                cantidadCaptada = Integer.parseInt(cantidad.toString());
        }
        if(!entradaCaptada.equals("") && cantidadCaptada>0){
            vectoresGenerados pantallaVectores = new vectoresGenerados(entradaCaptada,cantidadCaptada);
            pantallaVectores.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDescLuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescLuActionPerformed
        Object cantidad = JOptionPane.showInputDialog(null,"Seleccione el orden de la matriz","Orden",JOptionPane.QUESTION_MESSAGE, null, new Object[]{"1","2","3","4","5"},"Seleccione");
        int orden = Integer.parseInt(cantidad.toString());
        matrizLU MatrizLU = new matrizLU(orden);
        MatrizLU.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDescLuActionPerformed

    private void jButtonSEcuacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSEcuacionesActionPerformed
        // TODO add your handling code here:
        Object cantidad = JOptionPane.showInputDialog(null,"Seleccione la cantidad de variables\n del sistema de ecuaciones","Orden",JOptionPane.QUESTION_MESSAGE, null, new Object[]{"2","3","4","5"},"Seleccione");
        int orden = Integer.parseInt(cantidad.toString());
        Aplicacion3 Saplicacion3 = new Aplicacion3(orden);
        Saplicacion3.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonSEcuacionesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescLu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonSEcuaciones;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
