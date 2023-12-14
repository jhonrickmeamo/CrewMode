/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package staffcrew;

/**
 *
 * @author meamo
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import javax.swing.*;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;
public class mainUI extends javax.swing.JFrame {

    /**
     * Creates new form mainUI
     */
    
    private String jdbcUrl = "jdbc:mysql://192.168.25.119:3306/buboyconnection";
    private String username = "admin";
    private String password = "admin";
    private int tableNumber = 1; // Set your default table number
    
    
    String receiptText1;
    String receiptText2;
    String receiptText3;
    
    public mainUI() {
        initComponents();
        startAutoUpdate();
    }
    
    private void startAutoUpdate() {
        // Schedule the timer to update the JTextArea every 5 seconds (adjust as needed)
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateReceipt();
            }
        }, 0, 3000); // 3000 milliseconds = 3 seconds
    }

    private void updateReceipt() {
        // Fetch orders for the specified table number
        receiptText1 = fetchOrdersForTable1(1);
        receiptText2 = fetchOrdersForTable2(2);
        receiptText3 = fetchOrdersForTable3(3);

        // Update the JTextArea with the receipt
        SwingUtilities.invokeLater(() -> {
            jTextArea1.setText(receiptText1);
            jTextArea2.setText(receiptText2);
            jTextArea3.setText(receiptText3);
        });
    }

    private String fetchOrdersForTable1(int tableNumber) {
    StringBuilder receiptText = new StringBuilder();
    BigDecimal totalOrderPrice = BigDecimal.ZERO;

    try {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "SELECT orders.order_date, menus.item_name, orderitems.quantity, menus.price " +
                     "FROM orders " +
                     "JOIN orderitems ON orders.order_id = orderitems.order_id " +
                     "JOIN menus ON orderitems.menu_id = menus.menu_id " +
                     "WHERE orders.table_number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tableNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if there are no orders for the specified table
            if (!resultSet.isBeforeFirst()) {
                printBtn.setEnabled(false);
                return "Table No Orders";
            } else {
                printBtn.setEnabled(true);
            }

            while (resultSet.next()) {
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                BigDecimal price = resultSet.getBigDecimal("price");

                BigDecimal totalPriceForItem = price.multiply(BigDecimal.valueOf(quantity));
                totalOrderPrice = totalOrderPrice.add(totalPriceForItem);

                receiptText.append(itemName)
                           .append(" x").append(quantity)
                           .append(" - Total: ").append(totalPriceForItem).append("php \n");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    receiptText.append("\nTotal Order Price: PHP ").append(totalOrderPrice);
    return receiptText.toString();
}

    
    private static void saveReceiptToFile(String receiptText) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose file location to save receipt");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath() + ".txt"))) {
                writer.write(receiptText);
                System.out.println("Receipt saved to: " + fileToSave.getAbsolutePath() + ".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Receipt not saved. User canceled the operation.");
        }
    }
    
    private static void saveReceiptToFile2(String receiptText) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose file location to save receipt");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath() + ".txt"))) {
                writer.write(receiptText);
                System.out.println("Receipt saved to: " + fileToSave.getAbsolutePath() + ".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Receipt not saved. User canceled the operation.");
        }
    }
    
    private static void saveReceiptToFile3(String receiptText) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose file location to save receipt");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath() + ".txt"))) {
                writer.write(receiptText);
                System.out.println("Receipt saved to: " + fileToSave.getAbsolutePath() + ".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Receipt not saved. User canceled the operation.");
        }
    }
    
    private String fetchOrdersForTable2(int tableNumber) {
        StringBuilder receiptText = new StringBuilder();
    BigDecimal totalOrderPrice = BigDecimal.ZERO;

    try {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "SELECT orders.order_date, menus.item_name, orderitems.quantity, menus.price " +
                     "FROM orders " +
                     "JOIN orderitems ON orders.order_id = orderitems.order_id " +
                     "JOIN menus ON orderitems.menu_id = menus.menu_id " +
                     "WHERE orders.table_number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tableNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if there are no orders for the specified table
            if (!resultSet.isBeforeFirst()) {
                printBtn.setEnabled(false);
                return "Table No Orders";
            } else {
                printBtn.setEnabled(true);
            }

            while (resultSet.next()) {
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                BigDecimal price = resultSet.getBigDecimal("price");

                BigDecimal totalPriceForItem = price.multiply(BigDecimal.valueOf(quantity));
                totalOrderPrice = totalOrderPrice.add(totalPriceForItem);

                receiptText.append(itemName)
                           .append(" x").append(quantity)
                           .append(" - Total: ").append(totalPriceForItem).append("php \n");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    receiptText.append("\nTotal Order Price: PHP ").append(totalOrderPrice);
    return receiptText.toString();
    }
    
    private String fetchOrdersForTable3(int tableNumber) {
        StringBuilder receiptText = new StringBuilder();
    BigDecimal totalOrderPrice = BigDecimal.ZERO;

    try {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "SELECT orders.order_date, menus.item_name, orderitems.quantity, menus.price " +
                     "FROM orders " +
                     "JOIN orderitems ON orders.order_id = orderitems.order_id " +
                     "JOIN menus ON orderitems.menu_id = menus.menu_id " +
                     "WHERE orders.table_number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tableNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if there are no orders for the specified table
            if (!resultSet.isBeforeFirst()) {
                printBtn.setEnabled(false);
                return "Table No Orders";
            } else {
                printBtn.setEnabled(true);
            }

            while (resultSet.next()) {
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                BigDecimal price = resultSet.getBigDecimal("price");

                BigDecimal totalPriceForItem = price.multiply(BigDecimal.valueOf(quantity));
                totalOrderPrice = totalOrderPrice.add(totalPriceForItem);

                receiptText.append(itemName)
                           .append(" x").append(quantity)
                           .append(" - Total: ").append(totalPriceForItem).append("php \n");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    receiptText.append("\nTotal Order Price: PHP ").append(totalOrderPrice);
    return receiptText.toString();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        printBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        clearBtn2 = new javax.swing.JButton();
        printBtn2 = new javax.swing.JButton();
        clearBtn3 = new javax.swing.JButton();
        printBtn3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Table 1");

        jLabel2.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Table 2");

        jLabel3.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Table 3");

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea3.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 12)); // NOI18N
        jTextArea3.setRows(5);
        jScrollPane2.setViewportView(jTextArea3);

        jTextArea2.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 12)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        printBtn.setText("PRINT RECEIPT");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        clearBtn2.setText("CLEAR");
        clearBtn2.setToolTipText("");
        clearBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtn2ActionPerformed(evt);
            }
        });

        printBtn2.setText("PRINT RECEIPT");
        printBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtn2ActionPerformed(evt);
            }
        });

        clearBtn3.setText("CLEAR");
        clearBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtn3ActionPerformed(evt);
            }
        });

        printBtn3.setText("PRINT RECEIPT");
        printBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(clearBtn)
                .addGap(48, 48, 48)
                .addComponent(printBtn)
                .addGap(106, 106, 106)
                .addComponent(clearBtn2)
                .addGap(79, 79, 79)
                .addComponent(printBtn2)
                .addGap(95, 95, 95)
                .addComponent(clearBtn3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(printBtn3)
                .addGap(55, 55, 55))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(printBtn)
                    .addComponent(clearBtn)
                    .addComponent(clearBtn2)
                    .addComponent(printBtn2)
                    .addComponent(clearBtn3)
                    .addComponent(printBtn3))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 66, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        
        clearDataForTableNumber1(1);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void clearBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtn2ActionPerformed
        // TODO add your handling code here:
        clearDataForTableNumber2(2);
    }//GEN-LAST:event_clearBtn2ActionPerformed

    private void clearBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtn3ActionPerformed
        // TODO add your handling code here:
        clearDataForTableNumber3(3);
    }//GEN-LAST:event_clearBtn3ActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        // TODO add your handling code here:
        
        saveReceiptToFile(receiptText1);
    }//GEN-LAST:event_printBtnActionPerformed

    private void printBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtn2ActionPerformed
        // TODO add your handling code here:
        
        saveReceiptToFile2(receiptText2);
    }//GEN-LAST:event_printBtn2ActionPerformed

    private void printBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtn3ActionPerformed
        // TODO add your handling code here:
        
        saveReceiptToFile3(receiptText3);
    }//GEN-LAST:event_printBtn3ActionPerformed

    private void clearDataForTableNumber1(int tableNumber) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Clear order items for the specified table number
            String clearOrderItemsSql = "DELETE FROM orderitems " +
                                        "WHERE order_id IN (SELECT order_id FROM orders WHERE table_number = ?)";
            try (PreparedStatement clearOrderItemsStatement = connection.prepareStatement(clearOrderItemsSql)) {
                clearOrderItemsStatement.setInt(1, tableNumber);
                clearOrderItemsStatement.executeUpdate();
            }

            // Clear staff orders for the specified table number
            String clearStaffOrdersSql = "DELETE FROM stafforders " +
                                         "WHERE order_id IN (SELECT order_id FROM orders WHERE table_number = ?)";
            try (PreparedStatement clearStaffOrdersStatement = connection.prepareStatement(clearStaffOrdersSql)) {
                clearStaffOrdersStatement.setInt(1, tableNumber);
                clearStaffOrdersStatement.executeUpdate();
            }

            // Clear orders for the specified table number
            String clearOrdersSql = "DELETE FROM orders " +
                                    "WHERE table_number = ?";
            try (PreparedStatement clearOrdersStatement = connection.prepareStatement(clearOrdersSql)) {
                clearOrdersStatement.setInt(1, tableNumber);
                clearOrdersStatement.executeUpdate();
            }

            // You can add additional clearing logic for other tables as needed

            JOptionPane.showMessageDialog(this, "Data cleared for Table " + tableNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error clearing data for Table " + tableNumber);
        }
    }
    private void clearDataForTableNumber2(int tableNumber) {
    try {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        // Clear order items for the specified table number
        String clearOrderItemsSql = "DELETE FROM orderitems " +
                                    "WHERE order_id IN (SELECT order_id FROM orders WHERE table_number = ?)";
        try (PreparedStatement clearOrderItemsStatement = connection.prepareStatement(clearOrderItemsSql)) {
            clearOrderItemsStatement.setInt(1, tableNumber);
            clearOrderItemsStatement.executeUpdate();
        }

        // Clear staff orders for the specified table number
        String clearStaffOrdersSql = "DELETE FROM stafforders " +
                                     "WHERE order_id IN (SELECT order_id FROM orders WHERE table_number = ?)";
        try (PreparedStatement clearStaffOrdersStatement = connection.prepareStatement(clearStaffOrdersSql)) {
            clearStaffOrdersStatement.setInt(1, tableNumber);
            clearStaffOrdersStatement.executeUpdate();
        }

        // Clear orders for the specified table number
        String clearOrdersSql = "DELETE FROM orders " +
                                "WHERE table_number = ?";
        try (PreparedStatement clearOrdersStatement = connection.prepareStatement(clearOrdersSql)) {
            clearOrdersStatement.setInt(1, tableNumber);
            clearOrdersStatement.executeUpdate();
        }

        // You can add additional clearing logic for other tables as needed

        JOptionPane.showMessageDialog(this, "Data cleared for Table " + tableNumber);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error clearing data for Table " + tableNumber);
    }
    }
    private void clearDataForTableNumber3(int tableNumber) {
    try {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        // Clear order items for the specified table number
        String clearOrderItemsSql = "DELETE FROM orderitems " +
                                    "WHERE order_id IN (SELECT order_id FROM orders WHERE table_number = ?)";
        try (PreparedStatement clearOrderItemsStatement = connection.prepareStatement(clearOrderItemsSql)) {
            clearOrderItemsStatement.setInt(1, tableNumber);
            clearOrderItemsStatement.executeUpdate();
        }

        // Clear staff orders for the specified table number
        String clearStaffOrdersSql = "DELETE FROM stafforders " +
                                     "WHERE order_id IN (SELECT order_id FROM orders WHERE table_number = ?)";
        try (PreparedStatement clearStaffOrdersStatement = connection.prepareStatement(clearStaffOrdersSql)) {
            clearStaffOrdersStatement.setInt(1, tableNumber);
            clearStaffOrdersStatement.executeUpdate();
        }

        // Clear orders for the specified table number
        String clearOrdersSql = "DELETE FROM orders " +
                                "WHERE table_number = ?";
        try (PreparedStatement clearOrdersStatement = connection.prepareStatement(clearOrdersSql)) {
            clearOrdersStatement.setInt(1, tableNumber);
            clearOrdersStatement.executeUpdate();
        }

        // You can add additional clearing logic for other tables as needed

        JOptionPane.showMessageDialog(this, "Data cleared for Table " + tableNumber);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error clearing data for Table " + tableNumber);
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
            java.util.logging.Logger.getLogger(mainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton clearBtn2;
    private javax.swing.JButton clearBtn3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton printBtn2;
    private javax.swing.JButton printBtn3;
    // End of variables declaration//GEN-END:variables
}
