/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package codigo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.Scanner;

/**
 *
 * @author santi
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }    
    
    private void RaizEcuacion(){}

    
    private void AnalizarLexico() throws IOException{
    String expr = (String) txtResultado.getText();
    Lexer lexer = new Lexer(new StringReader(expr));

    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Linea");
    modelo.addColumn("Tipo");
    modelo.addColumn("Token");
    modelo.addColumn("Columna");

    while (true) {
        Tokens token = lexer.yylex();
        if (token == null) {
            tablaLexico.setModel(modelo);
            return;
        }
        switch (token) {      
            case Igual -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Operador Igual";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;   
                modelo.addRow(fila);
            }
            case Suma -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Operador Suma";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Resta -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Operador Resta";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Multiplicacion -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Operador Multiplicación";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Division -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Operador División";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Parentesis_A -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Parentesis de apertura";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Parentesis_C -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Parentesis de cierre";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Llave_A -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Llave de Apertura";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Llave_C -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Llave de cierre";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }  
            case Variable -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Variable";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Numero -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Número";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case SimbEsp -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Simbolo especial";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case Exponente -> {
                Object[] fila = new Object[4];
                fila [0] = lexer.line + 1;
                fila [1] = "Exponente";
                fila [2] = lexer.lexeme;
                fila [3] = lexer.column + 1;
                modelo.addRow(fila);
            }
            case ERROR -> {
                Object[] fila = new Object[4];
                fila[0] = lexer.line + 1;
                fila[1] = "Simbolo no definido";
                fila[2] = lexer.lexeme;
                fila[3] = lexer.column + 1;
                modelo.addRow(fila);
            }
        }     
    }       
}
   
    private void ExportTextToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            try {
                try (FileWriter writer = new FileWriter(filePath)) {
                    for (int i = 0; i < tablaLexico.getRowCount(); i++) {
                        for (int j = 0; j < tablaLexico.getColumnCount(); j++) {
                            writer.write(tablaLexico.getValueAt(i, j).toString());
                            if (j < tablaLexico.getColumnCount() - 1) {
                                writer.write("\t"); // Separador de tabulación
                            }
                        }
                        writer.write("\n"); // Salto de línea
                    }   }
            } catch (IOException ex) {
            }       
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

        btnArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSintactico = new javax.swing.JTextArea();
        btnAnalizarLex = new javax.swing.JButton();
        btnAnalizarSin = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaLexico = new javax.swing.JTable();
        btnRaiz = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRaiz = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(153, 255, 204));

        btnArchivo.setBackground(new java.awt.Color(204, 255, 255));
        btnArchivo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnArchivo.setText("Buscar Texto");
        btnArchivo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        txtResultado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txtResultado);

        txtSintactico.setEditable(false);
        txtSintactico.setColumns(20);
        txtSintactico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSintactico.setRows(5);
        txtSintactico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(txtSintactico);

        btnAnalizarLex.setBackground(new java.awt.Color(204, 255, 255));
        btnAnalizarLex.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAnalizarLex.setText("Analizar Léxico");
        btnAnalizarLex.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAnalizarLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarLexActionPerformed(evt);
            }
        });

        btnAnalizarSin.setBackground(new java.awt.Color(204, 255, 255));
        btnAnalizarSin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAnalizarSin.setText("Analísis Sintactico");
        btnAnalizarSin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAnalizarSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarSinActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnExportar.setBackground(new java.awt.Color(204, 255, 255));
        btnExportar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        tablaLexico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaLexico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Linea", "Tipo", "Token", "Columna"
            }
        ));
        jScrollPane2.setViewportView(tablaLexico);

        btnRaiz.setBackground(new java.awt.Color(204, 255, 255));
        btnRaiz.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRaiz.setText("Raíz");
        btnRaiz.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRaiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaizActionPerformed(evt);
            }
        });

        txtRaiz.setEditable(false);
        txtRaiz.setColumns(20);
        txtRaiz.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtRaiz.setRows(5);
        txtRaiz.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane4.setViewportView(txtRaiz);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Raíz Obtenida");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                .addGap(51, 51, 51))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(btnAnalizarLex, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addGap(127, 127, 127))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnalizarSin)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(12, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(btnRaiz, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(btnAnalizarLex, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(36, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAnalizarSin, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRaiz, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter Filtro = new FileNameExtensionFilter("Archivos de texto (*.txt)","txt");
        chooser.setFileFilter(Filtro);
        chooser.showOpenDialog(this);
        
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
        
        try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtResultado.setText(ST);  
            JOptionPane.showMessageDialog(null, "Cargado con éxito"); 
      } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);  
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);          
        }
        
    }//GEN-LAST:event_btnArchivoActionPerformed

    private void btnAnalizarSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarSinActionPerformed
        // TODO add your handling code here:
        String ST = txtResultado.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
        try {
            s.parse();
            txtSintactico.setText("Analisis realizado correctamente");
            txtSintactico.setForeground(new Color(25, 111, 61));
            JOptionPane.showMessageDialog(null, "Analizado con exito");
        } catch (Exception ex) {
            Symbol sym = s.getS();
            txtSintactico.setText("Error de sintaxis. Linea: "+ (sym.right + 1) + "Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"" );
            txtSintactico.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "Analizado sin exito");
        }
    }//GEN-LAST:event_btnAnalizarSinActionPerformed

    private void btnAnalizarLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarLexActionPerformed
        try {
            AnalizarLexico();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Analizado con exito");
    }//GEN-LAST:event_btnAnalizarLexActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        txtResultado.setText(null);
        DefaultTableModel model = (DefaultTableModel) tablaLexico.getModel();
        model.setRowCount(0);
        txtSintactico.setText(null);
        JOptionPane.showMessageDialog(null, "Limpiado");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
        ExportTextToFile();
        JOptionPane.showMessageDialog(null, "Exportado con éxito"); 
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnRaizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaizActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnRaizActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizarLex;
    private javax.swing.JButton btnAnalizarSin;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRaiz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tablaLexico;
    private javax.swing.JTextArea txtRaiz;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JTextArea txtSintactico;
    // End of variables declaration//GEN-END:variables

    private int setColorGrid(Color red) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
