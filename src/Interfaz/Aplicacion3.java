/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Andresmrllo
 */
public class Aplicacion3 extends javax.swing.JFrame {
    public static int orden;
    List<JTextField> listaMatriz= new ArrayList<>();
    List<JTextField> listaSoluciones= new ArrayList<>();
    public static double[][] matriz;
    public static double[][] matrizL;
    public static double[][] matrizU;
    /**
     * Creates new form Aplicacion3
     * @param cantidad
     */
    
    public Aplicacion3(int cantidad) {
        orden=cantidad;
        initComponents();
        this.getContentPane().setBackground(new Color(20,138,156));
        panelMatriz.setBackground(new Color(20,138,156));
        panelSolucion.setBackground(new Color(20,138,156));
        matriz = new double[orden][orden];
        matrizL = new double[orden][orden];
        matrizU = new double[orden][orden];
        generarMatrices();
    }
    
    public Aplicacion3(int cantidad,double[][] matrizp,double [] soluciones) {
        orden=cantidad;
        initComponents();
        this.getContentPane().setBackground(new Color(20,138,156));
        panelMatriz.setBackground(new Color(20,138,156));
        panelSolucion.setBackground(new Color(20,138,156));
        matriz = matrizp;
        matrizL = new double[orden][orden];
        matrizU = new double[orden][orden];
       generarMatricesConValores(matrizp,soluciones);
    }
    
    /**
     * Genera la matriz presentada en el Frame.
     */
    private void generarMatrices() 
    {
        int contadorFilas1 = 1;
        int contadorColumnas1 = 1;
        int x = 20;
        int y = 40;
        while (contadorFilas1 <= orden) {
            if (contadorColumnas1 <= orden) {
                JTextField entrada = new JTextField();
                entrada.setName("Entrada" + contadorFilas1 + contadorColumnas1);
                panelMatriz.add(entrada);
                listaMatriz.add(entrada);
                entrada.setBounds(x, y, 60, 40);
                x += 60;
                contadorColumnas1++;
            } else {
                contadorColumnas1 = 1;
                y += 40;
                x = 20;
                contadorFilas1++;
            }
        }
        x=10;
        y = 40;
        for (int i = 1;i<=orden;i++)
        {
            JTextField entrada = new JTextField();
            entrada.setName("Entrada" + contadorFilas1 + contadorColumnas1);
            panelSolucion.add(entrada);
            listaSoluciones.add(entrada);
            entrada.setBounds(x, y, 60, 40);
            y += 40;
        }
    }
    
        private void generarMatricesConValores(double matrizOriginal[][],double[] soluciones) {
        int contadorFilas1 = 1;
        int contadorColumnas1 = 1;
        int x = 20;
        int y = 40;
        while (contadorFilas1 <= orden) 
        {
            if (contadorColumnas1 <= orden) {
                JTextField entrada = new JTextField();
                entrada.setName("Entrada" + contadorFilas1 + contadorColumnas1);
                entrada.setText(Double.toString(Math.rint((matrizOriginal[contadorFilas1 - 1][contadorColumnas1 - 1]) * 100) / 100));
                panelMatriz.add(entrada);
                listaMatriz.add(entrada);
                entrada.setBounds(x, y, 60, 40);
                entrada.setBounds(x, y, 60, 40);
                x += 60;
                contadorColumnas1++;
            } else {
                contadorColumnas1 = 1;
                y += 40;
                x = 20;
                contadorFilas1++;
            }
        }
        x=10;
        y = 40;
        for (int i = 0;i<orden;i++)
        {
            JTextField entrada = new JTextField();
            entrada.setName("Entrada" + contadorFilas1 + contadorColumnas1);
            entrada.setText(Double.toString(Math.rint((soluciones[i]) * 100) / 100));
            panelSolucion.add(entrada);
            listaSoluciones.add(entrada);
            entrada.setBounds(x, y, 60, 40);
            y += 40;
        }
    }
    
    public void llenarL() {
        for (int i = 0; i < orden; i++) {
            for (int j = 0; j < orden; j++) {
                if (i == j) {
                    matrizL[i][j] = 1;
                } else {
                    matrizL[i][j] = 0;
                }
            }
        }
    }

    public void llenarU() {
        for (int i = 0; i < orden; i++) {
            for (int j = 0; j < orden; j++) {
                matrizU[i][j] = 0;
            }
        }
    }

    public void factorizarLU() {
        double suma;

        llenarL();
        llenarU();

        for (int i = 1; i <= orden; i++) {
            for (int j = i; j <= orden; j++) {
                suma = 0;
                for (int k = 1; k <= i - 1; k++) {
                    suma = matrizL[i - 1][k - 1] * matrizU[k - 1][j - 1] + suma;
                }

                matrizU[i - 1][j - 1] = matriz[i - 1][j - 1] - suma;
            }

            for (int j = i + 1; j <= orden; j++) {
                suma = 0;
                for (int k = 1; k <= i - 1; k++) {
                    suma = matrizL[j - 1][k - 1] * matriz[k - 1][i - 1] + suma;
                }
                matrizL[j - 1][i - 1] = (matriz[j - 1][i - 1] - suma) / matrizU[i - 1][i - 1];
            }
            matrizL[i - 1][i - 1] = 1;
        }
    }
    
    /**
     * Retorna la matriz que se obtiene de los datos introducidos 
     * por el usuario.
    */
    
    private boolean CamposVacios()
    {
        boolean vacio = false;
        for (JTextField f : listaMatriz )
        {
            if (f.getText().isEmpty()) 
            {
                return true;
            }
        }
        
        for (JTextField f : listaSoluciones )
        {
            if (f.getText().isEmpty()) 
            {
                return true;
            }
        }
        return vacio;
    }
    
    private double[][] getMatriz()
    {
        int contadorFilas = 1;
        int contadorColumnas = 1;
        double [][] normal = new double[orden][orden];
        while (contadorFilas <= orden) 
        {
            if (contadorColumnas <= orden) 
            {
                for (JTextField f : listaMatriz) 
                {
                    if (f.getName().equals("Entrada" + contadorFilas + contadorColumnas)) 
                    {
                        normal[contadorFilas - 1][contadorColumnas - 1] = Float.parseFloat(f.getText());
                        contadorColumnas++;
                        break;
                    }
                }
            } 
            else 
            {
                contadorFilas++;
                contadorColumnas = 1;
            }
        }
        System.out.println(Arrays.toString(normal[0]));
        System.out.println(Arrays.toString(normal[1]));
        System.out.println(Arrays.toString(normal[2]));
        return normal;
    }
    
    private double[] getListaSolucion()
    {
        double[] soluciones = new double[orden];
        for (int i = 0; i<listaSoluciones.size(); i++)
        {
            soluciones[i]= Float.parseFloat(listaSoluciones.get(i).getText());
            
        }
       // System.out.println(Arrays.toString(soluciones));
        return soluciones;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMatriz = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonPasoaPaso = new javax.swing.JButton();
        panelSolucion = new javax.swing.JPanel();
        jButtonAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistemas de Ecuaciones por LU");

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTitulo.setText("Sistema de ecuaciones");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("=");

        jButtonPasoaPaso.setText("Paso a Paso");
        jButtonPasoaPaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasoaPasoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMatrizLayout = new javax.swing.GroupLayout(panelMatriz);
        panelMatriz.setLayout(panelMatrizLayout);
        panelMatrizLayout.setHorizontalGroup(
            panelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMatrizLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMatrizLayout.createSequentialGroup()
                        .addComponent(jLabelTitulo)
                        .addGap(83, 83, 83))
                    .addComponent(jButtonPasoaPaso, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelMatrizLayout.setVerticalGroup(
            panelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMatrizLayout.createSequentialGroup()
                .addComponent(jLabelTitulo)
                .addGap(97, 97, 97)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jButtonPasoaPaso)
                .addContainerGap())
        );

        jButtonAtras.setText("Atras");
        jButtonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSolucionLayout = new javax.swing.GroupLayout(panelSolucion);
        panelSolucion.setLayout(panelSolucionLayout);
        panelSolucionLayout.setHorizontalGroup(
            panelSolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSolucionLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jButtonAtras)
                .addContainerGap())
        );
        panelSolucionLayout.setVerticalGroup(
            panelSolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSolucionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAtras)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelSolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtrasActionPerformed
        // TODO add your handling code here:
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAtrasActionPerformed

    private void jButtonPasoaPasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasoaPasoActionPerformed
        // TODO add your handling code here:
        if(CamposVacios())
        {
            JOptionPane.showMessageDialog(null, "Debe completar todos los datos");
        }
        else
        {
            matriz = getMatriz();
            factorizarLU();
            System.out.println("Matriz L:" + Arrays.deepToString(matrizL));
            System.out.println("Matriz U:" + Arrays.deepToString(matrizU));
            PasoaPasoSE3 Saplicacion3 = new PasoaPasoSE3(matrizU,matrizL,matriz,getListaSolucion());
            Saplicacion3.setVisible(true);
            this.dispose();
            /*RealMatrix matriz = MatrixUtils.createRealMatrix(getMatriz());
            if (new LUDecomposition(matriz).getU() != null && new LUDecomposition(matriz).getL()!= null)
            {
                PasoaPasoSE3 Saplicacion3 = new PasoaPasoSE3(new LUDecomposition(matriz).getU(),new LUDecomposition(matriz).getL(),getListaSolucion());
                Saplicacion3.setVisible(true);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "La matriz es singular por lo tanto no se puede factorizar");
            }*/
        }
        
        /**
         * RealMatrix matriz = MatrixUtils.createRealMatrix(getMatriz());
         *getListaSolucion();
         *PasoaPasoSE3 Saplicacion3 = new PasoaPasoSE3(new LUDecomposition(matriz).getU(),new LUDecomposition(matriz).getL(),getListaSolucion());
         *Saplicacion3.setVisible(true);
         *this.dispose();
        */
    }//GEN-LAST:event_jButtonPasoaPasoActionPerformed

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
            java.util.logging.Logger.getLogger(Aplicacion3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplicacion3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplicacion3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicacion3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Aplicacion3(orden).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtras;
    private javax.swing.JButton jButtonPasoaPaso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel panelMatriz;
    private javax.swing.JPanel panelSolucion;
    // End of variables declaration//GEN-END:variables
}
