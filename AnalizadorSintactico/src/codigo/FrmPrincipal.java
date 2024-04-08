/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Charly Ponce
 */
public class FrmPrincipal extends javax.swing.JFrame {
DefaultTableModel modelo; 
Object fila []= new Object[3];
String[] divi;
  String finalInter;
private TableRowSorter trsfiltro;
    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
       
        initComponents();
         modelo = (DefaultTableModel) JtableLexer.getModel();
        
        this.setExtendedState(MAXIMIZED_BOTH);
    setIconImage(getIconImage());
    }
    
    @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/buscador.png"));
        
        return retValue;
    }
    
    private void analizarLexico() throws IOException{
      //Analizador Lexico
      //Cont es el numero de fila
        int cont = 1;
        
     //   for (int i = 0; i < JtableLexer.getRowCount(); i++) {
   //     modelo.removeRow(i);
   //     i-=1;
   //         }
        
   
   //tomamos lo que se escribio
        String expr = (String) txtResultado.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA " + cont + "\t\tSIMBOLO\n";
        fila[0]="LINEA " + cont ;
        //si los resultados son verdaderos es decir que existan lineas
        while (true) {
            //crea la variable token
            Tokens token = lexer.yylex();
            if (token == null) {
                txtAnalizarLex.setText(resultado);
                return;
            }
            //segun sea el token
            switch (token) {
                case Linea:
                    cont++;
                    resultado += "LINEA " + cont + "\n";
                    fila[0]="LINEA " + cont ;
                    break;
                case Comillas:
                    resultado += "  <Comillas>\t\t" + lexer.lexeme + "\n";
                    fila[2]="Comillas";
                    fila[1]=lexer.lexeme;
                    break;
                case Cadena:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    fila[2]="Tipo de dato";
                    fila[1]=lexer.lexeme;
                    break;
                case T_dato:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    fila[2]="Tipo de dato";
                    fila[1]=lexer.lexeme;
                    break;
                case If:
                    resultado += "  <Reservada if>\t" + lexer.lexeme + "\n";
                    fila[2]="Reservada if";
                    fila[1]=lexer.lexeme;
                    break;
                case Else:
                    resultado += "  <Reservada else>\t" + lexer.lexeme + "\n";
                    fila[2]="Reservada else";
                    fila[1]=lexer.lexeme;
                    break;
                case Do:
                    resultado += "  <Reservada do>\t" + lexer.lexeme + "\n";
                    fila[2]="Reservada do";
                    fila[1]=lexer.lexeme;
                    break;
                case While:
                    resultado += "  <Reservada while>\t" + lexer.lexeme + "\n";
                     fila[2]="Reservada while";
                    fila[1]=lexer.lexeme;
                    break;
                case For:
                    resultado += "  <Reservada for>\t" + lexer.lexeme + "\n";
                     fila[2]="Reservada for";
                    fila[1]=lexer.lexeme;
                    break;
                case Igual:
                    resultado += "  <Operador igual>\t" + lexer.lexeme + "\n";
                     fila[2]="Operador igual";
                    fila[1]=lexer.lexeme;
                    break;
                case Suma:
                    resultado += "  <Operador suma>\t" + lexer.lexeme + "\n";
                     fila[2]="Operador suma";
                    fila[1]=lexer.lexeme;
                    break;
                case Resta:
                    resultado += "  <Operador resta>\t" + lexer.lexeme + "\n";
                    fila[2]="Operador resta";
                    fila[1]=lexer.lexeme;
                    break;
                case Multiplicacion:
                    resultado += "  <Operador multiplicacion>\t" + lexer.lexeme + "\n";
                    fila[2]="Operador multiplicacion";
                    fila[1]=lexer.lexeme;
                    break;
                case Division:
                    resultado += "  <Operador division>\t" + lexer.lexeme + "\n";
                    fila[2]="Operador division";
                    fila[1]=lexer.lexeme;
                    break;
                case Op_logico:
                    resultado += "  <Operador logico>\t" + lexer.lexeme + "\n";
                      fila[2]="Operador logico";
                    fila[1]=lexer.lexeme;
                    break;
                case Op_incremento:
                    resultado += "  <Operador incremento>\t" + lexer.lexeme + "\n";
                     fila[2]="Operador incremento";
                    fila[1]=lexer.lexeme;
                    break;
                case Op_relacional:
                    resultado += "  <Operador relacional>\t" + lexer.lexeme + "\n";
                    fila[2]="Operador relacional";
                    fila[1]=lexer.lexeme;
                    break;
                case Op_atribucion:
                    resultado += "  <Operador atribucion>\t" + lexer.lexeme + "\n";
                   fila[2]="Operador atribucion";
                    fila[1]=lexer.lexeme;
                    break;
                case Op_booleano:
                    resultado += "  <Operador booleano>\t" + lexer.lexeme + "\n";
                   fila[2]="Operador booleano";
                    fila[1]=lexer.lexeme;
                    break;
                case Parentesis_a:
                    resultado += "  <Parentesis de apertura>\t" + lexer.lexeme + "\n";
                    fila[2]="Parentesis de apertura";
                    fila[1]=lexer.lexeme;
                    break;
                case Parentesis_c:
                    resultado += "  <Parentesis de cierre>\t" + lexer.lexeme + "\n";
                   fila[2]="Parentesis de cierre";
                    fila[1]=lexer.lexeme;
                    break;
                case Llave_a:
                    resultado += "  <Llave de apertura>\t" + lexer.lexeme + "\n";
                      fila[2]="Llave de apertura";
                    fila[1]=lexer.lexeme;
                    break;
                case Llave_c:
                    resultado += "  <Llave de cierre>\t" + lexer.lexeme + "\n";
                     fila[2]="Llave de cierre";
                    fila[1]=lexer.lexeme;
                    break;
                case Corchete_a:
                    resultado += "  <Corchete de apertura>\t" + lexer.lexeme + "\n";
                    fila[2]="Corchete de apertura";
                    fila[1]=lexer.lexeme;
                    break;
                case Corchete_c:
                    resultado += "  <Corchete de cierre>\t" + lexer.lexeme + "\n";
                     fila[2]="Corchete de cierre";
                    fila[1]=lexer.lexeme;
                    break;
                case Main:
                    resultado += "  <Reservada main>\t" + lexer.lexeme + "\n";
                    fila[2]="Reservada main";
                    fila[1]=lexer.lexeme;
                    break;
                case P_coma:
                    resultado += "  <Punto y coma>\t" + lexer.lexeme + "\n";
                   fila[2]="Punto y coma";
                    fila[1]=lexer.lexeme;
                    break;
                case Identificador:
                    resultado += "  <Identificador>\t\t" + lexer.lexeme + "\n";
                    fila[2]="Identificador";
                    fila[1]=lexer.lexeme;
                    break;
                  
                case Numero:
                    resultado += "  <Numero>\t\t" + lexer.lexeme + "\n";
                    fila[2]="Numero";
                    fila[1]=lexer.lexeme;
                    break;
                    
                case ERROR:
                    resultado += "  <Simbolo no definido>\n";
                    fila[2]="Simbolo no definido";
                    
                    break;
                default:
                    resultado += "  < " + lexer.lexeme + " >\n";
                    break;
            }
         //   System.out.println("Fila 0>"+fila[0]+" fila1>"+fila[1]+" fila2>"+fila[2]);
        //    modelo.addRow(fila);
          //  JtableLexer.setModel(modelo); 
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

        jScrollPane4 = new javax.swing.JScrollPane();
        JtableLexer = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        btnAnalizarLex = new javax.swing.JButton();
        btnLimpiarLex = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLimpiarCodigo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnalizarLex = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtIntermedio = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnalizarSin = new javax.swing.JTextArea();
        btnLimpiarSin = new javax.swing.JButton();
        btnAnalizarSin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        JtableLexer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linea", "Variable", "Tipo Variable"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(JtableLexer);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(172, 229, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fases de análisis de un compilador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        btnArchivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnArchivo.setText("Buscar archivo");
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        btnAnalizarLex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAnalizarLex.setText("Ejecutar");
        btnAnalizarLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarLexActionPerformed(evt);
            }
        });

        btnLimpiarLex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiarLex.setText("Limpiar");
        btnLimpiarLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarLexActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel1.setText("Analizador Lexico");

        btnLimpiarCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiarCodigo.setText("Limpiar archivo");
        btnLimpiarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCodigoActionPerformed(evt);
            }
        });

        txtAnalizarLex.setEditable(false);
        txtAnalizarLex.setColumns(20);
        txtAnalizarLex.setRows(5);
        jScrollPane2.setViewportView(txtAnalizarLex);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel2.setText("Joseph Almonte 1-19-2948");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel3.setText("Analizador Sintáctico & Semántico");

        txtIntermedio.setColumns(20);
        txtIntermedio.setRows(5);
        jScrollPane5.setViewportView(txtIntermedio);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Traducir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Guardar archivo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtAnalizarSin.setEditable(false);
        txtAnalizarSin.setColumns(20);
        txtAnalizarSin.setRows(5);
        jScrollPane3.setViewportView(txtAnalizarSin);

        btnLimpiarSin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiarSin.setText("Limpiar");
        btnLimpiarSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSinActionPerformed(evt);
            }
        });

        btnAnalizarSin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAnalizarSin.setText("Ejecutar");
        btnAnalizarSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarSinActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel4.setText("Traductor de código a Java");

        btnCerrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(258, 948, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLimpiarSin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAnalizarSin, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145)
                                .addComponent(jButton2)
                                .addGap(147, 147, 147)
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLimpiarCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAnalizarLex)
                                .addGap(548, 548, 548)
                                .addComponent(btnLimpiarLex))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(540, 540, 540))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnLimpiarCodigo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnalizarLex)
                            .addComponent(btnLimpiarLex)
                            .addComponent(btnArchivo))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(btnCerrar)
                    .addComponent(btnLimpiarSin)
                    .addComponent(btnAnalizarSin))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
        
        try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtResultado.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnArchivoActionPerformed

       
    private void btnLimpiarLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarLexActionPerformed
        // TODO add your handling code here:
        txtAnalizarLex.setText(null);
        
          for (int i = 0; i < JtableLexer.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
            }
        
        
    }//GEN-LAST:event_btnLimpiarLexActionPerformed

    private void btnLimpiarSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSinActionPerformed
        // TODO add your handling code here:
        txtAnalizarSin.setText(null);
        //txtAnalizarSemantico.setText(null);
    }//GEN-LAST:event_btnLimpiarSinActionPerformed

    private void btnAnalizarLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarLexActionPerformed
        try {
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizarLexActionPerformed

    private void btnAnalizarSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarSinActionPerformed
        // TODO add your handling code here:
        String ST = txtResultado.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
        
        //toma la informacion del txt 
        try {
            //si todo esta correcto
            s.parse();
            txtAnalizarSin.setText("Analisis realizado correctamente");
            txtAnalizarSin.setForeground(new Color(25, 111, 61));
        } catch (Exception ex) {
            //si encuentra un error
            //toma el simbolo
            Symbol sym = s.getS();
            //con el simbolo buscamos la linea y la columna y el valor del error y lo presentamos en un txt
            txtAnalizarSin.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
            txtAnalizarSin.setForeground(Color.red);
            //llamamos la funcion Seman  que realiza el analizador semantico
            Seman((sym.right + 1),(sym.left + 1),sym.value);
        }
    }//GEN-LAST:event_btnAnalizarSinActionPerformed

    
    private void Seman(Object rigth,Object left, Object valor)
    {
        String val= valor.toString();
       int val2= 2;
        
        if (rigth!="1") {
         //segun el caso del simbolo nos da las posibles soluciones semanticas       
        switch (val) {
            case "=":
            txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: Declarar un identificador");
            txtAnalizarSin.setForeground(Color.red);
            break;
           
              case "}":
            txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: variables sin cerrar, no se encontre un retorno u accion");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
               case "int":
            txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: falta cerrar la variable");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
               case "\"":
          txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: Los datos no son compatibles");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
            
                 case ")":
          txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: LLenar los campos necesarios entre los ()");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
                   case "(":
          txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: LLenar los campos necesarios entre los (),no necesario en este campo");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
            
            
                     case "+":
          txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: Operador colocado indebidamente");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
            
                    case "-":
          txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: Operador colocado indebidamente");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
                     case "/":
          txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: Operador colocado indebidamente");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
            
                     case "*":
          txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: Operador colocado indebidamente");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
                       default:
                        
          txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: No se encuentra dentro la funcion, no creado, falta de tipo de valor");
            txtAnalizarSin.setForeground(Color.red);
            break;
            
        }// fin del switch
        
        switch(val2)
        {
        
            case 3:
                 txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: Los datos no son compatibles");
            txtAnalizarSin.setForeground(Color.red);
                break;
        
        }//fin otro switch
        
        
        
        
        
    }//fin if de rigth
         if (rigth=="1") {
         txtAnalizarSin.setText("Error de sintaxis. Linea: " + rigth + " Columna: " + left + ", Texto: \"" + valor + "\""+
                    "\nPosible solucion Semántica: Inicializar la funcion main");
            txtAnalizarSin.setForeground(Color.red);
         }
        
    
    } // fin de seman
    
    
    
   public void GenerarIntermedio() 
   {
       //limpiamos el text area
       txtIntermedio.setText(null);
       //tomamos el codigo y lo dividimos por lineas
       String elresul=txtResultado.getText();
       String [] lineas = elresul.split("\n");
       //cantidad de lineas
       int casifin=lineas.length;
       String Intermedio="";
       StringBuilder stringBuilderInter = new StringBuilder();
       
       
       //revisamos todas las lineas
       for (int i = 0; i < lineas.length; i++) {
        
           
           //si en la linea 1 y 2 existe estas palabras introducimos la estructura de java
           if ("int main (){".equals(lineas[0]) || ("int main ()".equals(lineas[0]) && "{".equals(lineas[1]))
               ||"int main(){".equals(lineas[0]) || ("int main()".equals(lineas[0]) && "{".equals(lineas[1]))    ) {

            Intermedio ="package NombreArchivo;"+"\n\n\n"+"public class NombreArchivo{"+"\n\n"+"public static void main(String[] args) {"+"\n\n";
           }// Fin //si en la linea 1 y 2 existe estas palabras introducimos la estructura de java
           
           
           
            if ("}".equals(lineas[casifin-1])) {
               
                finalInter="}\n\n}";
              
           }// Si la ultima tiene } 
            
            
            
            
         //si es mayor a la linea 2 y la penultima linea
           if (i>1 && i<casifin-1) {
           
          
                //si la linea contiene printf
             if (lineas[i].contains("printf")) {
           //remplazamos printf por System.out.println
           String prin= lineas[i].replace("printf","System.out.println");
           
               //Agregamos esta linea al StringBuilder
                   stringBuilderInter.append(prin).append("\n");
               
               
               }// si es un printf



               // si la linea tiene un scanf
              if (lineas[i].contains("scanf")) {
                  // modificamos la parte inicial del codigo de Java importando el Scanner
           Intermedio ="package NombreArchivo;"+"\n"+"import java.util.Scanner;"+"\n\n"+"public class NombreArchivo{"+"\n\n"+"public static void main(String[] args) {"+"\n"
                   + "\nScanner entrada =new Scanner(System.in);\n";
           
           String [] prin= lineas[i].split("&");
           //dividimos la cadena para poder separar la variable
               if (prin[0]!=null) {
                   String lini=prin[1];
                   String divisor="\\)\\;";
                    String [] prin2= lini.split(divisor);
                   String impri=prin2[0]+"= entrada.nextInt();";
                   //agregamos esa linea modificada al Stringbuilder
                   stringBuilderInter.append(impri).append("\n");
               }
               
               }// si es un scanf
               
               
              
              
              
              
               boolean esta=lineas[i].contains("printf");
               boolean esta2=lineas[i].contains("scanf");
               
               //En caso de que no exista ni un printf o un scanf
            if(esta==false && esta2==false) {
                   System.out.println("esta "+esta+"   "+"esta2 "+esta2);   
           //Agregamos la linea de igual forma
          stringBuilderInter.append(lineas[i]).append("\n");
            }
         
     }//Fin si no es linea inicial o final
            

            
            
       }//fin for
      
      
       System.out.println(Intermedio);
       System.out.println(stringBuilderInter);
       System.out.println(finalInter);
      
       
       //creamos un StringBuilder nuevo 
       StringBuilder CodigoIntermedio= new StringBuilder();
       
       //Determinamos la estrcutura que sera intermedio la parte inicial el Stringbuilder Intermedio la parte del centro
       // y finalInter como la parte que cierra el codigo
       CodigoIntermedio.append(Intermedio).append(stringBuilderInter).append(finalInter);
      
       
       //le ponemos el codigo intermedio al text Area
        txtIntermedio.setText(CodigoIntermedio.toString());
       
   }
    
    
    
    private void btnLimpiarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCodigoActionPerformed
       txtResultado.setText(null);
    }//GEN-LAST:event_btnLimpiarCodigoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        GenerarIntermedio();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     // Creamos un archivo dentro de un proyecto Java
    File archivo;
    FileWriter fw = null;
    try {
        archivo = new File("C:/Users/josep/OneDrive/Documents/NetBeansProjects/Tarea Semana 7/AnalizadorSintactico/src/NombreArchivo.java");

        // Si existe este archivo, lo eliminamos
        if (archivo.exists()) {
            archivo.delete();
        }

        // Creamos el archivo
        if (archivo.createNewFile()) {
            System.out.println("Creado bien");
        }

        fw = new FileWriter(archivo);

        BufferedWriter bw = new BufferedWriter(fw);
        String elresul = txtIntermedio.getText();
        // Dividimos el texto por líneas
        String[] lineas = elresul.split("\n");

        // Por cada línea la escribimos en el archivo java
        for (int i = 0; i < lineas.length; i++) {
            try {
                bw.write(lineas[i]);
                bw.newLine();
            } catch (IOException ex) {
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Cuando terminamos de escribir las líneas, cerramos el BufferedWriter
        bw.close();

    } catch (IOException e) {
        System.err.println("Error " + e);
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        // Cerrar la aplicación
        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed
public class AnalizadorSemantico {
        private Map<String, codigo.Symbol> symbolTable;

    public AnalizadorSemantico() {
        symbolTable = new HashMap<>();
    }

    public String realizarAnalisisSemantico(String codigo) {
        String[] lineas = codigo.split("\n");
        String resultado = "";

        for (String linea : lineas) {
            // Aquí deberás dividir cada línea en tokens y analizarla
            // Por ejemplo, buscar declaraciones de variables, usos, etc.
            // Vamos a hacer un análisis muy básico como ejemplo

            if (linea.contains("int ")) {
                String nombreVariable = extraerNombreVariable(linea, "int");
                symbolTable.put(nombreVariable, new codigo.Symbol(nombreVariable, "int", "global"));
            } else if (linea.contains("=")) {
                // Verifica si se está utilizando una variable declarada
                String[] partes = linea.split("=");
                String nombreVariable = partes[0].trim();

                if (!symbolTable.containsKey(nombreVariable)) {
                    resultado += "Error: Variable " + nombreVariable + " no declarada.\n";
                }
            }
            // Más reglas según sea necesario
        }

        if (resultado.isEmpty()) {
            resultado = "Análisis semántico completado sin errores.";
        }

        return resultado;
    }

    private String extraerNombreVariable(String linea, String tipo) {
        // Implementa la lógica para extraer el nombre de la variable de la línea
        // Este es solo un ejemplo básico y puede necesitar ajustes
        String[] partes = linea.split(tipo);
        return partes[1].split(" ")[1].trim().replace(";", "");
    }
}

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
    private javax.swing.JTable JtableLexer;
    private javax.swing.JButton btnAnalizarLex;
    private javax.swing.JButton btnAnalizarSin;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnLimpiarCodigo;
    private javax.swing.JButton btnLimpiarLex;
    private javax.swing.JButton btnLimpiarSin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea txtAnalizarLex;
    private javax.swing.JTextArea txtAnalizarSin;
    private javax.swing.JTextArea txtIntermedio;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
