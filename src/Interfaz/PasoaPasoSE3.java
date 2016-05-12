/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;


import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.math3.linear.*;

/**
 *
 * @author Andresmrllo
 */
public class PasoaPasoSE3 extends javax.swing.JFrame {
    public int orden;
    public int opcion = 1;
    public int fila = 1;
    public int columna = 1;
    public double [][] MatrizU;
    public double [][] MatrizL;
    public double [] listastaSoluciones;
    public double [] resultadosTemp;
    public double [] resultadosFinal;
    List<JTextField> lista= new ArrayList<>();
    /**
     * Creates new form PasoaPasoSE3
     */
    public PasoaPasoSE3() {
        initComponents();
    }
    
    public PasoaPasoSE3(RealMatrix matrizU, RealMatrix matrizL, double[] lSoluciones) {
        initComponents();
        this.getContentPane().setBackground(new Color(20,138,156));
        jPanelL.setBackground(new Color(20,138,156));
        jPanelU.setBackground(new Color(20,138,156));
        MatrizU = formatearDouble(matrizU.getData());
        MatrizL = formatearDouble(matrizL.getData());
        orden = lSoluciones.length;
        resultadosTemp = new double[orden];
        resultadosFinal = new double[orden];
        listastaSoluciones = lSoluciones;
        generarMatricesU();
        generarMatricesL();
    }

    
    private void generarMatricesU()
    {
        int contadorFilas1 = 1;
        int contadorColumnas1 = 1;
        int x = 20;
        int y = 40;
        while (contadorFilas1 <= orden) {
            if (contadorColumnas1 <= orden) {
                JTextField entrada1 = new JTextField();
                entrada1.setName("Matriz U " + contadorFilas1 + contadorColumnas1);
                entrada1.setText(Double.toString(MatrizU [contadorFilas1 - 1][contadorColumnas1 - 1]));
                jPanelU.add(entrada1);
                entrada1.setBounds(x, y, 60, 40);
                entrada1.setEnabled(false);
                x += 60;
                contadorColumnas1++;
            } else {
                contadorColumnas1 = 1;
                y += 40;
                x = 20;
                contadorFilas1++;
            }
        }
    }
    
    private double[][] formatearDouble(double[][] matriz)
    {
        double resultado[][] = new double[matriz.length][matriz.length];
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        for (int i = 0; i<matriz.length;i++)
        {
            for(int j = 0;j< matriz[i].length;j++)
            {
                Number n = matriz[i][j];
                Double d = n.doubleValue();
                resultado[i][j]= Double.parseDouble(df.format(d));
            }
        }
        
        return resultado;
    }
    
    private void generarMatricesL() {
        int contadorFilas1 = 1;
        int contadorColumnas1 = 1;
        int x = 20;
        int y = 40;
        while (contadorFilas1 <= orden) {
            if (contadorColumnas1 <= orden) {
                JTextField entrada1 = new JTextField();
                entrada1.setName("Matriz L" + contadorFilas1 + contadorColumnas1);
                
                entrada1.setText(Double.toString(MatrizL [contadorFilas1 - 1][contadorColumnas1 - 1]));
                jPanelL.add(entrada1);
                entrada1.setBounds(x, y, 60, 40);
                entrada1.setEnabled(false);
                x += 60;
                contadorColumnas1++;
            } else {
                contadorColumnas1 = 1;
                y += 40;
                x = 20;
                contadorFilas1++;
            }
        }
    }
      
    public void pasoApasoL(double[][] matriz,double[] listaresultados,double[] soluciones)
    {
       if (fila-1>0)
       {
           pasoApasoAUXL(matriz,listaresultados,soluciones);
       }
       else
       {
           listaresultados[fila-1] = soluciones[fila-1];
           jTextAreaResultado.append("abc"+(fila-1)+" ="+listaresultados[fila-1]+"\n");
           fila++;
           
       }
    }
    
    public void pasoApasoAUXL(double[][] matriz,double[] listaresultados,double[] soluciones)
    {
        for(int i=1;i<=fila-1;i++)
        {
            if(i>1)
            {
                listaresultados[fila-1] -= listaresultados[columna-1]*matriz[fila-1][columna-1];
            }
            else
            {
                listaresultados[fila-1] = soluciones[fila-1]-listaresultados[columna-1]*matriz[fila-1][columna-1];
            }
            columna++;
        }
        jTextAreaResultado.append("abc"+(fila-1)+" ="+listaresultados[fila-1]+"\n");
        if(fila == orden)
        {
            opcion++;
            fila--;
        }
        fila++;
        columna=1;
    }
    
    public void pasoApasoU(double[][] matriz,double[] listaresultados,double[] soluciones)
    {
       if (fila<orden)
       {
           pasoApasoAUXU(matriz,listaresultados,soluciones);
       }
       else
       {
           listaresultados[fila-1] = (soluciones[fila-1])/matriz[fila-1][orden-1];
           jTextAreaResultado.append("S"+(fila-1)+" ="+listaresultados[fila-1]+"\n");
           //System.out.println((soluciones[fila-1])+" / "+matriz[fila-1][orden-1]);
           //System.out.println("resultado xyz1 ="+listaresultados[fila-1]);
           fila--;
       }
    }
    
    public void pasoApasoAUXU(double[][] matriz,double[] listaresultados,double[] soluciones)
    {
        ///System.out.println("Fila2 = "+fila);
        for(int i=fila;i<=orden;i++)
        {
            if(i != fila)
            {
                if (orden==i)
                {
                    listaresultados[fila-1] = listaresultados[fila-1]/matriz[fila-1][columna-1];
                    System.out.println("S"+(fila-1)+" ="+listaresultados[fila-1]+"\n");
                }
                else 
                {
                    listaresultados[fila-1] = listaresultados[fila-1] -(listaresultados[columna-1]*matriz[fila-1][columna-1]);
                    System.out.println("resultado -= "+listaresultados[columna-1]+" * "+matriz[fila-1][columna-1]);
                }
            }
            else
            {
                listaresultados[fila-1] = soluciones[fila-1]-(listaresultados[columna-1]*matriz[fila-1][columna-1]);
                System.out.println("resultado = "+soluciones[fila-1]+" - "+listaresultados[columna-1]+" * "+matriz[fila-1][columna-1]);
            }
            //
            columna--;
        } 
        jTextAreaResultado.append("S"+(fila-1)+" ="+listaresultados[fila-1]+"\n");
        fila--;
        columna=orden;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanelL = new javax.swing.JPanel();
        jPanelU = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaResultado = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelLLayout = new javax.swing.GroupLayout(jPanelL);
        jPanelL.setLayout(jPanelLLayout);
        jPanelLLayout.setHorizontalGroup(
            jPanelLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        jPanelLLayout.setVerticalGroup(
            jPanelLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelULayout = new javax.swing.GroupLayout(jPanelU);
        jPanelU.setLayout(jPanelULayout);
        jPanelULayout.setHorizontalGroup(
            jPanelULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        jPanelULayout.setVerticalGroup(
            jPanelULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        jTextAreaResultado.setColumns(20);
        jTextAreaResultado.setRows(5);
        jScrollPane1.setViewportView(jTextAreaResultado);

        jButton1.setText("Atras");

        jButton2.setText("Siguiente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Matriz L");

        jLabel2.setText("Matriz U");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(140, 140, 140)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(15, 15, 15)
                        .addComponent(jButton1)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       /* RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 1, 4, -2 }, { 3, -2, 5 }, { 2, 3, 1 } }, false);
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(new double[] { 3, 14, 11 }, false);
        RealVector solution = solver.solve(constants);
        
        System.out.println("1 "+solution.getEntry(0));
        System.out.println("2 "+solution.getEntry(1));
        System.out.println("3 "+solution.getEntry(2));*/
       
        if (opcion==1)
        {
            pasoApasoL(MatrizL,resultadosTemp,listastaSoluciones);
        }
        else
        {
            if(fila==0)
            {
                JOptionPane.showMessageDialog(null, "Se terminaron los pasos");
            }
            else
            {
                columna = orden;
                //System.out.println("columna :"+columna);
                //System.out.println("fila :"+fila);
                pasoApasoU(MatrizU,resultadosFinal,resultadosTemp);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(PasoaPasoSE3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PasoaPasoSE3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PasoaPasoSE3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PasoaPasoSE3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PasoaPasoSE3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelL;
    private javax.swing.JPanel jPanelU;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextAreaResultado;
    // End of variables declaration//GEN-END:variables
}
