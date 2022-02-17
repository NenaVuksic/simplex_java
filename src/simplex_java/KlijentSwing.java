/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package simplex_java;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.InputVerifier;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Korisničko sučelje.
 * 
 * @author Mirna I.
 */
public class KlijentSwing extends javax.swing.JFrame {
    
    private int izbor = 1;
    private int brojRedaka = 0;
    private int brojStupaca = 0;
    private DefaultTableModel model1 = new DefaultTableModel();
    private DefaultTableModel model2 = new DefaultTableModel();
    private DefaultTableModel model3 = new DefaultTableModel();
    
    //mrezni stuff:
    private Socket uticnica = null;
    private PrintWriter zaPoslatiServeru = null;
    private BufferedReader primljenoOdServera = null;
    

    /**
     * Creates new form KlijentSwing
     */
    public KlijentSwing() {
        initComponents();
        
        jTable1.setModel(model1);
        jTable1.setTableHeader(null);
        jScrollPane1.setColumnHeaderView(null);
        
        jTable2.setModel(model2);
        jTable2.setTableHeader(null);
        jScrollPane2.setColumnHeaderView(null);
        
        jTable3.setModel(model3);
        jTable3.setTableHeader(null);
        jScrollPane3.setColumnHeaderView(null);
        
//        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
//        jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
//        jScrollPane3.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        
        try {
            uticnica = new Socket("127.0.0.1", 7777);                           //ovdje treba staviti IP adresu i port računala s faksa!!
        } catch (IOException ex) {
            Logger.getLogger(KlijentSwing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            zaPoslatiServeru = new PrintWriter(uticnica.getOutputStream(), true);
            primljenoOdServera = new BufferedReader(new InputStreamReader(uticnica.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(KlijentSwing.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jToggleButton4 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(855, 597));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 566));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 60));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(114, 81, 181));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Linearno programiranje", "Razdvajajuća hiperravnina", "Rang matrice" }));
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(114, 81, 181), 1, true));
        jComboBox1.setPreferredSize(new java.awt.Dimension(200, 40));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(114, 81, 181));
        jTextField1.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, -1, 33));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(114, 81, 181));
        jTextField2.setPreferredSize(new java.awt.Dimension(80, 25));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, -1, 33));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(114, 81, 181));
        jLabel1.setText("<html><p style=\"text-align:center;\">Molimo unesite veličinu tablice<br/>te odaberite koji problem želite riješiti</p></html>");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 310, -1));

        jToggleButton1.setBackground(new java.awt.Color(114, 81, 181));
        jToggleButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(114, 81, 181));
        jToggleButton1.setText("Unesi");
        jToggleButton1.setOpaque(true);
        jToggleButton1.setPreferredSize(new java.awt.Dimension(80, 25));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 91, 46));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(114, 81, 181));
        jLabel4.setText("<html><p style=\"text-align:center;\">Broj redaka:</p></html>");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 100, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(114, 81, 181));
        jLabel5.setText("<html><p style=\"text-align:center;\">Broj stupaca:</p></html>");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 110, -1));

        jPanel2.setBackground(new java.awt.Color(114, 81, 181));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<html><p style=\"text-align:center;\">Welcome to<br/>simplex!</p></html>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -7, 340, 580));

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(486, 330));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(411, 300));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(114, 81, 181)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setPreferredSize(new java.awt.Dimension(411, 300));
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 420, 317));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(411, 30));

        jTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(114, 81, 181), 1, true));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setPreferredSize(new java.awt.Dimension(411, 16));
        jTable2.setShowGrid(true);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 419, -1));

        jScrollPane3.setPreferredSize(new java.awt.Dimension(300, 60));

        jTable3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(114, 81, 181), 1, true));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.setPreferredSize(new java.awt.Dimension(60, 300));
        jTable3.setShowGrid(true);
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 59, 317));

        jButton1.setBackground(new java.awt.Color(114, 81, 181));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(114, 81, 181));
        jButton1.setText("KRENI");
        jButton1.setPreferredSize(new java.awt.Dimension(60, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, 90, 38));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(114, 81, 181));
        jLabel3.setText("<html><p style=\"text-align:center;\">A</p></html>");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 31, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(114, 81, 181));
        jLabel6.setText("<html><p style=\"text-align:center;\">Molimo unesite podatke:</p></html>");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 230, -1));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(114, 81, 181));
        jLabel8.setText("<html><p style=\"text-align:center;\">b</p></html>");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 20, -1));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(114, 81, 181));
        jLabel9.setText("<html><p style=\"text-align:center;\"> &#8804;</p></html>");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 31, 20));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(114, 81, 181));
        jLabel10.setText("<html><p style=\"text-align:center;\">z<sup>&#964;</sup></p></html>");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 31, -1));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(114, 81, 181));
        jLabel11.setText("<html><p style=\"text-align:center;\">*x</p></html>");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 31, -1));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(114, 81, 181));
        jLabel12.setText("<html><p style=\"text-align:center;\">*x</p></html>");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 31, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(114, 81, 181));
        jLabel18.setText("<html><p style=\"text-align:center;\">&#8594; max</p></html>");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 130, -1));

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(295, 400));
        jPanel4.setVerifyInputWhenFocusTarget(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton2.setBackground(new java.awt.Color(114, 81, 181));
        jToggleButton2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(114, 81, 181));
        jToggleButton2.setText("Prikaži korake");
        jToggleButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(114, 81, 181), 1, true));
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 145, 50));

        jToggleButton3.setBackground(new java.awt.Color(114, 81, 181));
        jToggleButton3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jToggleButton3.setForeground(new java.awt.Color(114, 81, 181));
        jToggleButton3.setText("Završi sesiju");
        jToggleButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(114, 81, 181), 1, true));
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jToggleButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 144, 50));

        jScrollPane5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(114, 81, 181), 1, true));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 300, 80));
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(114, 81, 181));
        jLabel14.setText("<html><p style=\"text-align:center;\">Traženo rješenje je...</p></html>");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 230, -1));

        jPanel7.setBackground(new java.awt.Color(114, 81, 181));

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("<html><p style=\"text-align:center;\">Hvala na<br/>strpljenju!</p></html>");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, 570));

        jTabbedPane1.addTab("tab3", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(114, 81, 177), 1, true));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(295, 333));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setPreferredSize(new java.awt.Dimension(400, 333));
        jScrollPane4.setViewportView(jList1);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 420, 230));

        jPanel8.setBackground(new java.awt.Color(114, 81, 181));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("<html><p style=\"text-align:center;\">Hvala na<br/>strpljenju!</p></html>");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, 570));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(114, 81, 181));
        jLabel17.setText("<html><p style=\"text-align:center;\">Postupak rješavanja:</p></html>");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 230, -1));

        jToggleButton4.setBackground(new java.awt.Color(114, 81, 181));
        jToggleButton4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jToggleButton4.setForeground(new java.awt.Color(114, 81, 181));
        jToggleButton4.setText("Završi sesiju");
        jToggleButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(114, 81, 181), 1, true));
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jToggleButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 144, 50));

        jTabbedPane1.addTab("tab4", jPanel6);

        jPanel5.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 850, 570));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 520));
        jPanel5.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if("Linearno programiranje".equals(jComboBox1.getSelectedItem().toString())) izbor = 1;
        else if("Razdvajajuća hiperravnina".equals(jComboBox1.getSelectedItem().toString())) izbor = 2;
        else izbor = 3;
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        try {
            brojRedaka = Integer.parseInt(jTextField1.getText());
            brojStupaca = Integer.parseInt(jTextField2.getText());
        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Uneseni brojevi moraju biti prirodni!", "Pogrešan unos", JOptionPane.WARNING_MESSAGE);
        }
        
        model1.getDataVector().removeAllElements();
        model1.setRowCount(brojRedaka);
        model1.setColumnCount(brojStupaca);
        model1.fireTableDataChanged();
        
        model2.getDataVector().removeAllElements();
        model2.setRowCount(1);
        model2.setColumnCount(brojStupaca);
        model2.fireTableDataChanged();
        
        model3.getDataVector().removeAllElements();
        model3.setRowCount(brojRedaka);
        model3.setColumnCount(1);
        model3.fireTableDataChanged();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private boolean provjeriUnos(Vector<Vector> v) {
        for(var i : v) {   //provjera unosa
            for(var j : i) {
                try{
                    double temp = Double.parseDouble(j.toString());
                } catch(NumberFormatException nfe) {
                    try{
                        Integer temp2 = Integer.parseInt(j.toString());
                    } catch(NumberFormatException nfe2){
                        JOptionPane.showMessageDialog(this, "Potrebno je unijeti isključivo brojeve u sva polja!", "Pogrešan unos", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                } catch(NullPointerException npe) {
                    JOptionPane.showMessageDialog(this, "Potrebno je unijeti isključivo brojeve u sva polja!", "Pogrešan unos", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean provjera1 = provjeriUnos(model1.getDataVector());
        boolean provjera2 = true;
        if(izbor != 3) provjera2 = provjeriUnos(model2.getDataVector());        //z se provjerava samo ako radimo linprog ili razdvajajucu hiper.
        boolean provjera3 = true;
        if(izbor == 1) provjera3 = provjeriUnos(model3.getDataVector());        //b se provjerava samo ako radimo linprog
        
        if(!provjera1 || !provjera2 || !provjera3) return;
        
        zaPoslatiServeru.println(izbor + " " + brojRedaka + " " + brojStupaca);
        
        if(izbor == 1) {    
            zaPoslatiServeru.println("b");
            String bTekst = "";
            for(var i : model3.getDataVector()) {
                for(var j : i) {
                    bTekst += j.toString() + " ";
                }
            }
            zaPoslatiServeru.println(bTekst);
        }
        
        if(izbor != 3) {
            zaPoslatiServeru.println("z");
            String zTekst = "";
            for(var i : model2.getDataVector()) {
                for(var j : i) {
                    zTekst += j.toString() + " ";
                }
            }
            zaPoslatiServeru.println(zTekst);
        }    
            
        zaPoslatiServeru.println("A");
        for(var i: model1.getDataVector()) {
            String ATekst = "";
            for(var j : i){
                ATekst += j.toString() + " ";
            }
            zaPoslatiServeru.println(ATekst);
        }
        
        try {
            String line = primljenoOdServera.readLine();
            if(izbor == 1) {
                if(line.equals("kontradikcija")) {
                    jTextArea1.setText("Unesena zadaća je u kontradikciji.");
                }
                else if(line.equals("neogranicena fja")) {
                    jTextArea1.setText("Funkcija cilja je neograničena na danom skupu.");
                }
                else {
                    String tocka[] = line.split(" ");
                    String ispis = "Maksimum se postiže u točki (";
                    for(int i = 0; i < tocka.length - 1; i++) ispis += tocka[i] + ", ";
                    ispis += tocka[tocka.length - 1] + "), a vrijednost funkcije cilja u toj točki je " + primljenoOdServera.readLine() + ".";
                    jTextArea1.setText(ispis);
                }
            }
            else if(izbor == 2) {
                if(line.equals("lose dimenzije")) {
                    jTextArea1.setText("Unesena matrica ima manje redaka nego stupaca, stoga se algoritam ne može provesti.");
                }
                else if(line.equals("lin zavisno")) {
                    jTextArea1.setText("Retci matrice su međusobno linearno zavisni.");
                }
                else if(line.equals("pripada konusu")) {
                    jTextArea1.setText("Uneseni vektor pripada konusnoj ljusci; ne postoji razdvajajuća hiperravnina.");
                }
                else {
                    String vektor[] = line.split(" ");
                    String ispis = "Vektor normale razdvajajuće hiperravnine je [";
                    for(int i = 0; i < vektor.length - 1; i++) ispis += vektor[i] + ", ";
                    ispis += vektor[vektor.length - 1] + "].";
                    jTextArea1.setText(ispis);
                }
            }
            else if(izbor == 3) {
                jTextArea1.setText("Rang matrice je " + line + ".");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(KlijentSwing.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        zaPoslatiServeru.println("kraj");
        try {
            uticnica.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        zaPoslatiServeru.println("poslati korake");
        
        Vector<String> listData = new Vector<>();
        
        //citanje koraka i spremanje u listu
        try {
            String line = primljenoOdServera.readLine();
            int brojTablica = Integer.parseInt(line);
            for(int i = 0; i < brojTablica; i++) listData.add(primljenoOdServera.readLine());
            
            jList1.setListData(listData);
        } catch (IOException ex) {
            Logger.getLogger(KlijentSwing.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // TODO add your handling code here:
        jToggleButton3ActionPerformed(evt);
    }//GEN-LAST:event_jToggleButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(KlijentSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KlijentSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KlijentSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KlijentSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KlijentSwing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    // End of variables declaration//GEN-END:variables
}
