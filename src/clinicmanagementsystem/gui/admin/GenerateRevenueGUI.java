/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinicmanagementsystem.gui.admin;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class GenerateRevenueGUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GenerateRevenueGUI.class.getName());
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnList = {"PaymentID", "PatientID", "PatientName", "Amount ($)", "Date", "Method", "Status"};
    private boolean isUpdatingSelectors = false;
    private javax.swing.JLabel selectDateLabel;
    private javax.swing.JComboBox<String> dateComboBox;

    /**
     * Creates new form GenerateRevenueGUI
     */
    public GenerateRevenueGUI() {
        model.setColumnIdentifiers(columnList);
        initComponents();
        jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        
        // Re-layout jPanel1 to vertical GridLayout and add programmatically created components
        jPanel1.removeAll();
        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 5, 5));
        jPanel1.add(reportPeriodLabel);
        jPanel1.add(reportPeriodComboBox);
        jPanel1.add(selectYearLabel);
        jPanel1.add(yearComboBox);
        jPanel1.add(specificPeriodLabel);
        jPanel1.add(monthComboBox);
        
        selectDateLabel = new javax.swing.JLabel("Select Date");
        dateComboBox = new javax.swing.JComboBox<>();
        jPanel1.add(selectDateLabel);
        jPanel1.add(dateComboBox);
        
        jPanel1.revalidate();
        jPanel1.repaint();
        
        initYearComboBox();
        
        reportPeriodComboBox.addActionListener(e -> updateSelectorsAndReload());
        yearComboBox.addActionListener(e -> updateSelectorsAndReload());
        
        monthComboBox.addActionListener(e -> {
            if (isUpdatingSelectors) return;
            String selectedPeriod = (String) reportPeriodComboBox.getSelectedItem();
            if ("daily".equalsIgnoreCase(selectedPeriod)) {
                isUpdatingSelectors = true;
                try {
                    updateDateComboBox();
                } finally {
                    isUpdatingSelectors = false;
                }
            }
            loadRevenueReport();
        });
        
        dateComboBox.addActionListener(e -> {
            loadRevenueReport();
        });
        
        backBtn.addActionListener(e -> {
            new clinicmanagementsystem.gui.AdminDashboardGUI().setVisible(true);
            this.dispose();
        });
        
        updateSelectorsAndReload();
    }

    private void initYearComboBox() {
        java.util.Set<String> years = new java.util.TreeSet<>();
        try {
            String[] lines = clinicmanagementsystem.util.FileManager.readLines("src/clinicmanagementsystem/data/payments.txt");
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length > 3) {
                    String date = parts[3].trim();
                    if (date.contains("-")) {
                        String year = date.split("-")[0];
                        years.add(year);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Add current year and fallback years
        String currentYear = String.valueOf(java.time.LocalDate.now().getYear());
        years.add(currentYear);
        years.add("2025");
        years.add("2026");
        
        yearComboBox.removeAllItems();
        for (String year : years) {
            yearComboBox.addItem(year);
        }
        yearComboBox.setSelectedItem(currentYear);
    }

    private void updateSelectorsAndReload() {
        if (isUpdatingSelectors) return;
        isUpdatingSelectors = true;
        
        try {
            String selectedPeriod = (String) reportPeriodComboBox.getSelectedItem();
            String selectedYear = (String) yearComboBox.getSelectedItem();
            if (selectedPeriod == null || selectedYear == null) return;
            
            Object currentMonthSel = monthComboBox.getSelectedItem();
            
            if (selectedPeriod.equalsIgnoreCase("yearly")) {
                specificPeriodLabel.setVisible(false);
                monthComboBox.setVisible(false);
                selectDateLabel.setVisible(false);
                dateComboBox.setVisible(false);
            } else if (selectedPeriod.equalsIgnoreCase("monthly")) {
                specificPeriodLabel.setVisible(true);
                monthComboBox.setVisible(true);
                specificPeriodLabel.setText("Select Month");
                selectDateLabel.setVisible(false);
                dateComboBox.setVisible(false);
                
                monthComboBox.removeAllItems();
                String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                for (String m : months) {
                    monthComboBox.addItem(m);
                }
                if (currentMonthSel != null) {
                    monthComboBox.setSelectedItem(currentMonthSel);
                }
            } else if (selectedPeriod.equalsIgnoreCase("quarterly")) {
                specificPeriodLabel.setVisible(true);
                monthComboBox.setVisible(true);
                specificPeriodLabel.setText("Select Quarter");
                selectDateLabel.setVisible(false);
                dateComboBox.setVisible(false);
                
                monthComboBox.removeAllItems();
                String[] quarters = {"Q1 (Jan-Mar)", "Q2 (Apr-Jun)", "Q3 (Jul-Sep)", "Q4 (Oct-Dec)"};
                for (String q : quarters) {
                    monthComboBox.addItem(q);
                }
                if (currentMonthSel != null) {
                    monthComboBox.setSelectedItem(currentMonthSel);
                }
            } else if (selectedPeriod.equalsIgnoreCase("daily")) {
                specificPeriodLabel.setVisible(true);
                monthComboBox.setVisible(true);
                specificPeriodLabel.setText("Select Month");
                selectDateLabel.setVisible(true);
                dateComboBox.setVisible(true);
                
                monthComboBox.removeAllItems();
                String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                for (String m : months) {
                    monthComboBox.addItem(m);
                }
                if (currentMonthSel != null) {
                    monthComboBox.setSelectedItem(currentMonthSel);
                } else {
                    int currentMonthIdx = java.time.LocalDate.now().getMonthValue() - 1;
                    monthComboBox.setSelectedIndex(currentMonthIdx);
                }
                
                updateDateComboBox();
            }
        } finally {
            isUpdatingSelectors = false;
        }
        
        loadRevenueReport();
    }

    private void updateDateComboBox() {
        String selectedYear = (String) yearComboBox.getSelectedItem();
        Object selectedMonthObj = monthComboBox.getSelectedItem();
        if (selectedYear == null || selectedMonthObj == null) return;
        
        String monthIndex = getMonthIndex(selectedMonthObj.toString());
        String targetPrefix = selectedYear + "-" + monthIndex + "-";
        
        Object currentDateSel = dateComboBox.getSelectedItem();
        dateComboBox.removeAllItems();
        
        java.util.Set<String> dates = new java.util.TreeSet<>();
        try {
            String[] lines = clinicmanagementsystem.util.FileManager.readLines("src/clinicmanagementsystem/data/payments.txt");
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length > 3) {
                    String dateStr = parts[3].trim();
                    if (dateStr.startsWith(targetPrefix)) {
                        dates.add(dateStr);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if (dates.isEmpty()) {
            dateComboBox.addItem("No Transactions");
        } else {
            for (String d : dates) {
                dateComboBox.addItem(d);
            }
        }
        
        if (currentDateSel != null) {
            dateComboBox.setSelectedItem(currentDateSel);
        }
    }

    private void loadRevenueReport() {
        if (isUpdatingSelectors) return;
        
        model.setRowCount(0);
        
        String selectedPeriod = (String) reportPeriodComboBox.getSelectedItem();
        String selectedYear = (String) yearComboBox.getSelectedItem();
        Object selectedSubPeriod = monthComboBox.getSelectedItem();
        
        if (selectedPeriod == null || selectedYear == null) return;
        
        java.util.Map<String, String> patientNames = new java.util.HashMap<>();
        try {
            String[] patientLines = clinicmanagementsystem.util.FileManager.readLines("src/clinicmanagementsystem/data/patients.txt");
            for (String line : patientLines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    patientNames.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        double totalRevenue = 0.0;
        
        try {
            String[] lines = clinicmanagementsystem.util.FileManager.readLines("src/clinicmanagementsystem/data/payments.txt");
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length > 5) {
                    String paymentId = parts[0].trim();
                    String patientId = parts[1].trim();
                    String amountStr = parts[2].trim();
                    String dateStr = parts[3].trim();
                    String method = parts[4].trim();
                    String status = parts[5].trim();
                    
                    double amount = 0.0;
                    try {
                        amount = Double.parseDouble(amountStr);
                    } catch (NumberFormatException nfe) {
                        // ignore
                    }
                    
                    if (!dateStr.startsWith(selectedYear + "-")) {
                        continue;
                    }
                    
                    boolean matches = false;
                    if (selectedPeriod.equalsIgnoreCase("yearly")) {
                        matches = true;
                    } else if (selectedPeriod.equalsIgnoreCase("monthly")) {
                        if (selectedSubPeriod != null) {
                            String monthName = selectedSubPeriod.toString();
                            String monthIndex = getMonthIndex(monthName);
                            if (dateStr.startsWith(selectedYear + "-" + monthIndex + "-")) {
                                matches = true;
                            }
                        }
                    } else if (selectedPeriod.equalsIgnoreCase("quarterly")) {
                        if (selectedSubPeriod != null) {
                            String quarterName = selectedSubPeriod.toString();
                            int monthValue = getMonthFromDate(dateStr);
                            if (quarterName.startsWith("Q1") && (monthValue >= 1 && monthValue <= 3)) {
                                matches = true;
                            } else if (quarterName.startsWith("Q2") && (monthValue >= 4 && monthValue <= 6)) {
                                matches = true;
                            } else if (quarterName.startsWith("Q3") && (monthValue >= 7 && monthValue <= 9)) {
                                matches = true;
                            } else if (quarterName.startsWith("Q4") && (monthValue >= 10 && monthValue <= 12)) {
                                matches = true;
                            }
                        }
                    } else if (selectedPeriod.equalsIgnoreCase("daily")) {
                        Object selectedDateObj = dateComboBox.getSelectedItem();
                        if (selectedDateObj != null) {
                            String targetDate = selectedDateObj.toString();
                            if (dateStr.equals(targetDate)) {
                                matches = true;
                            }
                        }
                    }
                    
                    if (matches) {
                        String patientName = patientNames.getOrDefault(patientId, "Unknown Patient");
                        model.addRow(new Object[]{paymentId, patientId, patientName, String.format("%.2f", amount), dateStr, method, status});
                        
                        if (status.equalsIgnoreCase("Paid")) {
                            totalRevenue += amount;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        jLabel2.setText(String.format("Total Revenue Earned: $%.2f", totalRevenue));
    }
    
    private String getMonthIndex(String monthName) {
        switch (monthName.toLowerCase()) {
            case "january": return "01";
            case "february": return "02";
            case "march": return "03";
            case "april": return "04";
            case "may": return "05";
            case "june": return "06";
            case "july": return "07";
            case "august": return "08";
            case "september": return "09";
            case "october": return "10";
            case "november": return "11";
            case "december": return "12";
            default: return "01";
        }
    }
    
    private int getMonthFromDate(String dateStr) {
        try {
            String[] parts = dateStr.split("-");
            if (parts.length > 1) {
                return Integer.parseInt(parts[1]);
            }
        } catch (Exception e) {
            // ignore
        }
        return 1;
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
        jPanel1 = new javax.swing.JPanel();
        reportPeriodLabel = new javax.swing.JLabel();
        reportPeriodComboBox = new javax.swing.JComboBox<>();
        selectYearLabel = new javax.swing.JLabel();
        specificPeriodLabel = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox<>();
        monthComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Generate Revenue Report");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        reportPeriodLabel.setText("Report Period Type");

        reportPeriodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "daily", "monthly", "quarterly", "yearly" }));

        selectYearLabel.setText("SelectYear");

        specificPeriodLabel.setText("SelectSpecific Period");

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026" }));

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reportPeriodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(selectYearLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reportPeriodComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 103, Short.MAX_VALUE))
                    .addComponent(specificPeriodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(reportPeriodLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportPeriodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(selectYearLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(specificPeriodLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Total Revenue Earned");

        backBtn.setText("Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(277, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(backBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(backBtn))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GenerateRevenueGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> monthComboBox;
    private javax.swing.JComboBox<String> reportPeriodComboBox;
    private javax.swing.JLabel reportPeriodLabel;
    private javax.swing.JLabel selectYearLabel;
    private javax.swing.JLabel specificPeriodLabel;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
}
