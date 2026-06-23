package clinicmanagementsystem.gui.admin;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageStaffRoster extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageStaffRoster.class.getName());
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnList = {"RosterID", "StaffID", "StaffName", "Role", "Duty Date", "Shift"};
    private int row = -1; // -1 is used to indicate no row selected in the beginning

    /**
     * Creates new form ManageStaffRoster
     */
    public ManageStaffRoster() {
        model.setColumnIdentifiers(columnList);
        loadDataToTable("src/clinicmanagementsystem/data/rosters.txt");
        initComponents();
        loadUserIDs();
        if (jTextField1 != null) {
            jTextField1.setText("2026-06-18");
        }
        if (checkBtn != null) {
            checkBtn.addActionListener(e -> updateStatusLabels());
        }
        model.addTableModelListener(e -> updateStatusLabels());
        updateStatusLabels();
    }

    private void updateStatusLabels() {
        int doctorCount = 0;
        int nurseCount = 0;
        String filterDate = (jTextField1 != null) ? jTextField1.getText().trim() : "";
        
        for (int i = 0; i < model.getRowCount(); i++) {
            Object dateObj = model.getValueAt(i, 4);
            if (!filterDate.isEmpty() && dateObj != null && !dateObj.toString().equals(filterDate)) {
                continue;
            }
            Object roleObj = model.getValueAt(i, 3);
            if (roleObj != null) {
                String role = roleObj.toString();
                if (role.equalsIgnoreCase("Doctor")) {
                    doctorCount++;
                } else if (role.equalsIgnoreCase("Nurse")) {
                    nurseCount++;
                }
            }
        }
        if (jLabel10 != null && jLabel11 != null && jLabel12 != null) {
            jLabel10.setText(String.valueOf(doctorCount));
            jLabel11.setText(String.valueOf(nurseCount));
            
            if (doctorCount >= 2 && nurseCount >= 3) {
                jLabel12.setText("valid");
                jLabel12.setForeground(new java.awt.Color(0, 153, 0));
            } else {
                jLabel12.setText("invalid");
                jLabel12.setForeground(new java.awt.Color(255, 0, 0));
            }
        }
    }

    private void loadUserIDs() {
        jComboBox1.addItem("-- Select Staff --");
        try {
            String[] lines = clinicmanagementsystem.util.FileManager.readLines("src/clinicmanagementsystem/data/users.txt");
            for (String line : lines) {
                String[] parts = line.split(",", -1);
                if (parts.length < 8) {
                    String[] padded = new String[8];
                    for (int i = 0; i < 8; i++) padded[i] = (i < parts.length) ? parts[i] : "";
                    parts = padded;
                }
                if (parts.length > 0) {
                    jComboBox1.addItem(parts[0]);
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataToTable(String fileName) {
        clinicmanagementsystem.util.FileManager.loadDataToTable(fileName, model);
    }

    private void clearAllTextFields() {
        if (jComboBox1.getItemCount() > 0) {
            jComboBox1.setSelectedIndex(0);
        }
        RoleTextField.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jComboBox2.setSelectedIndex(0);
        row = -1;
        model.setRowCount(0);
        loadDataToTable("src/clinicmanagementsystem/data/rosters.txt");
    }

    private String[] getStaffInfo(String staffId) {
        try {
            String[] lines = clinicmanagementsystem.util.FileManager.readLines("src/clinicmanagementsystem/data/users.txt");
            for (String line : lines) {
                String[] parts = line.split(",", -1);
                if (parts.length < 8) {
                    String[] padded = new String[8];
                    for (int i = 0; i < 8; i++) padded[i] = (i < parts.length) ? parts[i] : "";
                    parts = padded;
                }
                if (parts[0].equals(staffId)) {
                    // return name and role (index 1 is Name, index 7 is Role in users.txt)
                    return new String[]{parts[1], parts[7]};
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateNextRosterId() {
        int maxId = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String idStr = model.getValueAt(i, 0).toString().substring(1);
            int id = Integer.parseInt(idStr);
            if (id > maxId) {
                maxId = id;
            }
        }
        return String.format("R%03d", maxId + 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchButton = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        RoleTextField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        checkBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Mange Staff Roster");

        jTable1.setModel(model);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        searchButton.setText("Search");
        searchButton.addActionListener(this::searchButtonActionPerformed);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Assigment Form");

        jLabel2.setText("Staff ID");

        jLabel3.setText("Duty Date");

        jLabel6.setText("Date Format must be : 2026-06-18");

        jLabel5.setText("Shift");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Morning", "Afternoon", "Night", "All day" }));

        addButton.setText("Add");
        addButton.addActionListener(this::addButtonActionPerformed);

        updateButton.setText("Update");
        updateButton.addActionListener(this::updateButtonActionPerformed);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(this::deleteButtonActionPerformed);

        clearButton.setText("Clear");
        clearButton.addActionListener(this::clearButtonActionPerformed);

        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jLabel14.setText("Role");

        RoleTextField.setEditable(false);
        RoleTextField.addActionListener(this::RoleTextFieldActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deleteButton)
                            .addComponent(addButton))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateButton)
                            .addComponent(clearButton)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox1, 0, 118, Short.MAX_VALUE)
                                        .addComponent(RoleTextField))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(RoleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(updateButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(clearButton))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        backButton.setText("Back");
        backButton.addActionListener(this::backButtonActionPerformed);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setText("Doctor Schduled");

        jLabel10.setText("0");

        jLabel8.setText("Nurse Schduled");

        jLabel11.setText("0");

        jLabel9.setText("Status");

        jLabel12.setText("valid");

        jLabel13.setText("Duty Date");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        checkBtn.setText("check");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(checkBtn)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(searchButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(backButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(536, 536, 536))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new clinicmanagementsystem.gui.AdminDashboardGUI().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearAllTextFields();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this roster?", "Confirm", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm ==JOptionPane.YES_OPTION) {
            model.removeRow(row);
            clinicmanagementsystem.util.FileManager.rewriteFile("src/clinicmanagementsystem/data/rosters.txt", model);
            clearAllTextFields();
            javax.swing.JOptionPane.showMessageDialog(this, "Roster deleted successfully!");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a row to update!");
            return;
        }

        String sId = jComboBox1.getSelectedItem() != null ? jComboBox1.getSelectedItem().toString() : "";
        String date = jTextField2.getText();
        String shift = jComboBox2.getSelectedItem().toString();

        if (sId.isEmpty() || sId.equals("-- Select Staff --") || date.isEmpty() || shift.isEmpty() || shift.equals("-- Select Shift --") || shift.equals("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill in all fields and select a valid staff and shift!");
            return;
        }

        if (!clinicmanagementsystem.util.DateUtil.isValidDate(date)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid date format! Please use yyyy-MM-dd.");
            return;
        }

        String[] staffInfo = getStaffInfo(sId);
        if (staffInfo == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Staff ID not found!");
            return;
        }

        String name = staffInfo[0];
        String role = staffInfo[1];

        model.setValueAt(sId, row, 1);
        model.setValueAt(name, row, 2);
        model.setValueAt(role, row, 3);
        model.setValueAt(date, row, 4);
        model.setValueAt(shift, row, 5);

        clinicmanagementsystem.util.FileManager.rewriteFile("src/clinicmanagementsystem/data/rosters.txt", model);
        clearAllTextFields();
        JOptionPane.showMessageDialog(this, "Roster updated successfully!");
    }//GEN-LAST:event_updateButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String sId = jComboBox1.getSelectedItem() != null ? jComboBox1.getSelectedItem().toString() : "";
        String date = jTextField2.getText();
        String shift = jComboBox2.getSelectedItem().toString();

        if (sId.isEmpty() || sId.equals("-- Select Staff --") || date.isEmpty() || shift.isEmpty() || shift.equals("-- Select Shift --") || shift.equals("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill in all fields and select a valid staff and shift!");
            return;
        }

        if (!clinicmanagementsystem.util.DateUtil.isValidDate(date)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid date format! Please use yyyy-MM-dd.");
            return;
        }

        String[] staffInfo = getStaffInfo(sId);
        if (staffInfo == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Staff ID not found!");
            return;
        }

        String name = staffInfo[0];
        String role = staffInfo[1];

        String rId = generateNextRosterId();
        String[] rowData = {rId, sId, name, role, date, shift};
        model.addRow(rowData);
        clinicmanagementsystem.util.FileManager.rewriteFile("src/clinicmanagementsystem/data/rosters.txt", model);
        clearAllTextFields();
        javax.swing.JOptionPane.showMessageDialog(this, "Roster added successfully!");
    }//GEN-LAST:event_addButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String searchId = jTextField3.getText().trim();

        // If search box is empty, load all records back
        if (searchId.isEmpty()) {
            model.setRowCount(0);
            loadDataToTable("src/clinicmanagementsystem/data/rosters.txt");
            return;
        }

        // Clear current rows and read directly from file to look for matches
        model.setRowCount(0);
        try {
            String[] lines = clinicmanagementsystem.util.FileManager.readLines("src/clinicmanagementsystem/data/rosters.txt");
            boolean found = false;

            for (String line : lines) {
                String[] parts = line.split(",", -1);
                if (parts.length < 6) {
                    String[] padded = new String[6];
                    for (int i = 0; i < 6; i++) padded[i] = (i < parts.length) ? parts[i] : "";
                    parts = padded;
                }
                // parts[1] contains the Staff ID/User ID
                if (parts[1].equalsIgnoreCase(searchId)) {
                    model.addRow(parts);
                    found = true;
                }
            }

            if (!found) {
                javax.swing.JOptionPane.showMessageDialog(this, "No roster entries found for Staff ID: " + searchId);
                // Reload all if nothing found
                loadDataToTable("src/clinicmanagementsystem/data/rosters.txt");
                jTextField3.setText("");
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        row = jTable1.getSelectedRow();
        jComboBox1.setSelectedItem(model.getValueAt(row, 1) != null ? model.getValueAt(row, 1).toString() : "");
        if (model.getValueAt(row, 3) != null) {
            RoleTextField.setText(model.getValueAt(row, 3).toString());
        } else {
            RoleTextField.setText("");
        }
        jTextField2.setText(model.getValueAt(row, 4) != null ? model.getValueAt(row, 4).toString() : "");
        jComboBox2.setSelectedItem(model.getValueAt(row, 5) != null ? model.getValueAt(row, 5).toString() : "");
    }//GEN-LAST:event_jTable1MouseReleased

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        Object selected = jComboBox1.getSelectedItem();
        if (selected == null || selected.toString().equals("-- Select Staff --")) {
            RoleTextField.setText("");
            return;
        }
        String staffId = selected.toString();
        String[] staffInfo = getStaffInfo(staffId);
        if (staffInfo != null) {
            RoleTextField.setText(staffInfo[1]);
        } else {
            RoleTextField.setText("");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void RoleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoleTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoleTextFieldActionPerformed
   

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
        java.awt.EventQueue.invokeLater(() -> new ManageStaffRoster().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField RoleTextField;
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton checkBtn;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
