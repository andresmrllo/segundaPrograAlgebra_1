package Interfaz;

import Jama.Matrix;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Arrays;

public class vectoresGenerados extends javax.swing.JFrame {
    public static String condicionEntrante;
    public static int cantidadEntrante;
    public static ArrayList<JTextField> listaTextos = new ArrayList<>();
    public static int sumatoria;
    public vectoresGenerados(String condicion, int cantidad) {
        condicionEntrante = condicion;
        cantidadEntrante = cantidad;
        sumatoria = 30;
        initComponents();
        generarVectores();
        this.setSize(sumatoria, 200);
        this.panelEntradas.setBackground(new Color (20,138,156));
    }
public void generarVectores() {
        int contadorVectores = 1;
        int contadorElementos = 1;
        int cantidadElementos;
        if(condicionEntrante.equals("R2"))
            cantidadElementos = 2;
        else
            cantidadElementos = 3;
        int x = 20;
        int y = 40;
        sumatoria += 20;
        while (contadorVectores <= cantidadEntrante) {
            JTextField parentesisAbrir = new JTextField();
            parentesisAbrir.setText("(");
            panelEntradas.add(parentesisAbrir);
            parentesisAbrir.setBounds(x, y, 25, 40);
            parentesisAbrir.setEnabled(false);
            x += 25;
            sumatoria += 25;
            while(contadorElementos <= cantidadElementos){
                JTextField entrada = new JTextField();
                entrada.setName("Entrada" + contadorVectores + contadorElementos);
                panelEntradas.add(entrada);
                listaTextos.add(entrada);
                entrada.setBounds(x, y, 60, 40);
                x += 60;
                sumatoria += 60;
                contadorElementos++;
            }
            contadorVectores++;
            contadorElementos = 1;
            JTextField parentesisCerrar = new JTextField();
            parentesisCerrar.setText(")");
            panelEntradas.add(parentesisCerrar);
            parentesisCerrar.setBounds(x, y, 25, 40);
            parentesisCerrar.setEnabled(false);
            x += 25;
            sumatoria += 25;
        }
    }

public double[][] obtenerMatrizAsociada() {
        int columnas = 0;
        if(condicionEntrante.equals("R2"))
            columnas = 2;
        else
            columnas = 3;
        int contadorFilas = 1;
        int contadorColumnas = 1;
        double [][] normalA = new double[cantidadEntrante][columnas];
        while (contadorColumnas <= columnas) {
            if (contadorFilas <= cantidadEntrante) {
                for (JTextField f : listaTextos) {
                    if (f.getName().equals("Entrada" + contadorFilas + contadorColumnas)) {
                        normalA[contadorFilas - 1][contadorColumnas - 1] = Float.parseFloat(f.getText());
                        contadorFilas++;
                        break;
                    }
                }
            } else {
                contadorColumnas++;
                contadorFilas = 1;
            }
        }
        double [][]retorno;
        Matrix generada = new Matrix(normalA);
        retorno = generada.transpose().getArray();
        System.out.println(Arrays.deepToString(retorno));
        return retorno;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEntradas = new javax.swing.JPanel();
        botonAtras = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelEntradas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        botonAtras.setText("Atras");
        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });

        jButton1.setText("¿Dependencia?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEntradasLayout = new javax.swing.GroupLayout(panelEntradas);
        panelEntradas.setLayout(panelEntradasLayout);
        panelEntradasLayout.setHorizontalGroup(
            panelEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEntradasLayout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(66, 66, 66)
                .addComponent(botonAtras)
                .addContainerGap())
        );
        panelEntradasLayout.setVerticalGroup(
            panelEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEntradasLayout.createSequentialGroup()
                .addContainerGap(262, Short.MAX_VALUE)
                .addGroup(panelEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAtras)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_botonAtrasActionPerformed

    public double CalcularDeterminante(double[][] matrizDeterminante) {
        int n = 1;
        double det;
        if (matrizDeterminante.length == 2) {
            det = (matrizDeterminante[0][0] * matrizDeterminante[1][1]) - (matrizDeterminante[1][0] * matrizDeterminante[0][1]);
            return det;
        }
        float suma = 0;
        for (int i = 0; i < matrizDeterminante.length; i++) {
            double[][] nm = new double[matrizDeterminante.length - 1][matrizDeterminante.length - 1];
            for (int j = 0; j < matrizDeterminante.length; j++) {
                if (j != i) {
                    for (int k = 1; k < matrizDeterminante.length; k++) {
                        int indice = -1;
                        if (j < i) {
                            indice = j;
                        } else if (j > i) {
                            indice = j - 1;
                        }
                        nm[indice][k - 1] = matrizDeterminante[j][k];
                    }
                }
            }
            if (i % 2 == 0) {
                suma += matrizDeterminante[i][0] * CalcularDeterminante(nm);
            } else {
                suma -= matrizDeterminante[i][0] * CalcularDeterminante(nm);
            }
        }
        return suma;
    }
    
public boolean esCuadrada(){
    boolean retorno;
    int cColumnas = 0;
    if(condicionEntrante.equals("R2"))
        cColumnas = 2;
    else
        cColumnas = 3;
    if(cColumnas == cantidadEntrante)
        retorno = true;
    else
        retorno = false;
    return retorno;
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(condicionEntrante.equals("R3") && cantidadEntrante>3){
            JOptionPane.showMessageDialog(this, "Los vectores ingresadas son LD");
        }
        else if(condicionEntrante.equals("R2") && cantidadEntrante>2){
            JOptionPane.showMessageDialog(this, "Los vectores ingresadas son LI");
        }
        else{
            double [][] obtenida = obtenerMatrizAsociada();
            boolean cuadrada = esCuadrada();
            double determinante = -1;
            if(cuadrada == true){
                determinante = CalcularDeterminante(obtenida);
                if(determinante == 0){
                    JOptionPane.showMessageDialog(this, "Los vectores ingresados son LD");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Los vectores ingresados son LI");
                }
            }
            else{
                try{
                double [][] resultado = new double[obtenida.length][1];
                int contador = 0;
                while(contador<cantidadEntrante){
                    resultado[contador][0] = 0;
                    contador++;
                }
                Matrix mResultado = new Matrix(resultado);
                Matrix asociada = new Matrix(obtenida);
                Matrix c = asociada.solve(mResultado);
                resultado = c.getArray();
                boolean li = true;
                contador = 0;
                while(contador<resultado.length){
                    double evaluacion = resultado[0][contador];
                    if(evaluacion>0 || evaluacion<0){
                        li = false;
                        break;
                    }
                    contador++;
                }
                if(li==true){
                    JOptionPane.showMessageDialog(this, "Los vectores ingresados son LI");
                }
                else
                    JOptionPane.showMessageDialog(this, "Los vectores ingresados son LD");
                }catch(Exception e){
                    JOptionPane.showMessageDialog(this,"Sistema inconsistente, intentelo con otros vectores");
            }       
           }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(vectoresGenerados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vectoresGenerados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vectoresGenerados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vectoresGenerados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vectoresGenerados(condicionEntrante, cantidadEntrante).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAtras;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelEntradas;
    // End of variables declaration//GEN-END:variables
}
