/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinicmanagementsystem.gui.receptionist;

/**
 *
 * @author Joey
 */
import clinicmanagementsystem.gui.ReceptionistDashboardGUI;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.swing.JOptionPane;

public class ManagePatientsGUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManagePatientsGUI.class.getName());

    /**
     * Creates new form NewJFrame
     */
    public ManagePatientsGUI() {

        initComponents();
        clinicmanagementsystem.util.UIUtils.styleButtons(this);
        loadPatients();
    }

    private void loadPatients() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/clinicmanagementsystem/data/patients.txt")
            );

            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine && line.toLowerCase().startsWith("patientid")) {
                    isFirstLine = false;
                    continue;
                }
                isFirstLine = false;

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",", -1);
                String[] padded = new String[10];
                for (int i = 0; i < 10; i++) {
                    padded[i] = (i < data.length) ? data[i] : "";
                }
                model.addRow(new Object[]{padded[0], padded[1], padded[3], padded[4], padded[5], padded[6], padded[7], padded[8], padded[9]});
            }

            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading patients: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtPatientID.setText("");
        txtName.setText("");
        txtGender.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAge.setText("");
        txtBloodType.setText("");
        txtAddress.setText("");
        txtEmergencyContact.setText("");
    }

    private boolean validateInput() {
        if (txtPatientID.getText().trim().isEmpty() ||
            txtName.getText().trim().isEmpty() ||
            txtGender.getText().trim().isEmpty() ||
            txtPhone.getText().trim().isEmpty() ||
            txtEmail.getText().trim().isEmpty() ||
            txtAge.getText().trim().isEmpty() ||
            txtBloodType.getText().trim().isEmpty() ||
            txtAddress.getText().trim().isEmpty() ||
            txtEmergencyContact.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "All fields are required. Please fill in all the details.");
            return false;
        }

        try {
            int age = Integer.parseInt(txtAge.getText().trim());
            if (age <= 0 || age > 150) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number.");
            return false;
        }

        String email = txtEmail.getText().trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            return false;
        }

        String phone = txtPhone.getText().trim();
        if (!phone.matches("^[0-9+\\-]+$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number.");
            return false;
        }

        String bloodType = txtBloodType.getText().trim().toUpperCase();
        if (!bloodType.matches("^(A|B|AB|O)[+-]?$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid blood type (e.g., A, A+, O, AB-).");
            return false;
        }

        String gender = txtGender.getText().trim();
        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female") && !gender.equalsIgnoreCase("Other")) {
            JOptionPane.showMessageDialog(this, "Gender must be Male, Female, or Other.");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtPatientID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        txtBloodType = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtEmergencyContact = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "Name ", "Gender", "Phone", "Email ", "Age  ", "Blood Type", "Address ", "Emergency Contact"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtPatientID.addActionListener(this::txtPatientIDActionPerformed);

        txtGender.addActionListener(this::txtGenderActionPerformed);

        txtEmail.addActionListener(this::txtEmailActionPerformed);

        txtAddress.addActionListener(this::txtAddressActionPerformed);

        txtEmergencyContact.addActionListener(this::txtEmergencyContactActionPerformed);

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel1.setText("Patient ID");

        jLabel2.setText("Name");

        jLabel3.setText("Gender");

        jLabel4.setText("Phone");

        jLabel5.setText("Email ");

        jLabel6.setText("Age");

        jLabel7.setText("Blood Type");

        jLabel8.setText("Address");

        jLabel9.setText("Emergency Contact");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Manage Patients");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPatientID, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(txtName)
                                    .addComponent(txtGender)
                                    .addComponent(txtPhone))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBloodType)
                                    .addComponent(txtAddress)
                                    .addComponent(txtEmergencyContact)
                                    .addComponent(txtAge, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(186, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmergencyContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGenderActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void txtEmergencyContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmergencyContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmergencyContactActionPerformed

    private void txtPatientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPatientIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPatientIDActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String patientID = txtPatientID.getText();

        try {
            File inputFile = new File("src/clinicmanagementsystem/data/patients.txt");
            File tempFile = new File("src/clinicmanagementsystem/data/temp.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(tempFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                if (!line.startsWith(patientID + ",")) {
                    writer.println(line);
                }
            }

            scanner.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            JOptionPane.showMessageDialog(this, "Patient deleted successfully!");

            loadPatients();
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }

        if (jTable1.getValueAt(selectedRow, 0) == null) {
            return;
        }
        txtPatientID.setText(jTable1.getValueAt(selectedRow, 0) != null ? jTable1.getValueAt(selectedRow, 0).toString() : "");
        txtName.setText(jTable1.getValueAt(selectedRow, 1) != null ? jTable1.getValueAt(selectedRow, 1).toString() : "");
        txtGender.setText(jTable1.getValueAt(selectedRow, 2) != null ? jTable1.getValueAt(selectedRow, 2).toString() : "");
        txtPhone.setText(jTable1.getValueAt(selectedRow, 3) != null ? jTable1.getValueAt(selectedRow, 3).toString() : "");
        txtEmail.setText(jTable1.getValueAt(selectedRow, 4) != null ? jTable1.getValueAt(selectedRow, 4).toString() : "");
        txtAge.setText(jTable1.getValueAt(selectedRow, 5) != null ? jTable1.getValueAt(selectedRow, 5).toString() : "");
        txtBloodType.setText(jTable1.getValueAt(selectedRow, 6) != null ? jTable1.getValueAt(selectedRow, 6).toString() : "");
        txtAddress.setText(jTable1.getValueAt(selectedRow, 7) != null ? jTable1.getValueAt(selectedRow, 7).toString() : "");
        txtEmergencyContact.setText(jTable1.getValueAt(selectedRow, 8) != null ? jTable1.getValueAt(selectedRow, 8).toString() : "");
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        ReceptionistDashboardGUI dashboard = new ReceptionistDashboardGUI();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        loadPatients();
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String patientID = txtPatientID.getText().trim();

        if (patientID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter or select a Patient ID.");
            return;
        }

        if (!validateInput()) {
            return;
        }

        boolean found = false;

        try {
            File inputFile = new File("src/clinicmanagementsystem/data/patients.txt");
            File tempFile = new File("src/clinicmanagementsystem/data/temp.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(tempFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                if (line.startsWith(patientID + ",")) {
                    String[] oldData = line.split(",");
                    String password = (oldData.length > 2) ? oldData[2] : "1234";
                    String updatedData = txtPatientID.getText() + ","
                            + txtName.getText() + ","
                            + password + ","
                            + txtGender.getText() + ","
                            + txtPhone.getText() + ","
                            + txtEmail.getText() + ","
                            + txtAge.getText() + ","
                            + txtBloodType.getText() + ","
                            + txtAddress.getText() + ","
                            + txtEmergencyContact.getText();

                    writer.println(updatedData);
                    found = true;
                } else {
                    writer.println(line);
                }
            }

            scanner.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (found) {
                JOptionPane.showMessageDialog(this, "Patient updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Patient ID not found.");
            }

            loadPatients();
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!validateInput()) {
            return;
        }

        // Check for duplicate Patient ID
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/clinicmanagementsystem/data/patients.txt"));
            String line;
            String newID = txtPatientID.getText().trim();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data[0].equalsIgnoreCase(newID)) {
                    JOptionPane.showMessageDialog(this, "Patient ID already exists. Please use a different ID.");
                    br.close();
                    return;
                }
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error checking Patient ID: " + e.getMessage());
            return;
        }

        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/clinicmanagementsystem/data/patients.txt", true)
            );

            String data = txtPatientID.getText() + ","
                    + txtName.getText() + ","
                    + "1234" + "," // Default password
                    + txtGender.getText() + ","
                    + txtPhone.getText() + ","
                    + txtEmail.getText() + ","
                    + txtAge.getText() + ","
                    + txtBloodType.getText() + ","
                    + txtAddress.getText() + ","
                    + txtEmergencyContact.getText();

            bw.write(data);
            bw.newLine();
            bw.close();

            JOptionPane.showMessageDialog(this, "Patient added successfully!");

            loadPatients();
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchID = txtPatientID.getText().trim();

        if (searchID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Patient ID to search.");
            loadPatients();
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/clinicmanagementsystem/data/patients.txt")
            );

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().startsWith("patientid")) {
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data[0].equalsIgnoreCase(searchID)) {
                    String[] padded = new String[10];
                    for (int i = 0; i < 10; i++) {
                        padded[i] = (i < data.length) ? data[i] : "";
                    }
                    model.addRow(new Object[]{padded[0], padded[1], padded[3], padded[4], padded[5], padded[6], padded[7], padded[8], padded[9]});
                    
                    txtPatientID.setText(padded[0]);
                    txtName.setText(padded[1]);
                    txtGender.setText(padded[3]);
                    txtPhone.setText(padded[4]);
                    txtEmail.setText(padded[5]);
                    txtAge.setText(padded[6]);
                    txtBloodType.setText(padded[7]);
                    txtAddress.setText(padded[8]);
                    txtEmergencyContact.setText(padded[9]);

                    found = true;
                    break;
                }
            }

            br.close();

            if (!found) {
                JOptionPane.showMessageDialog(this, "Patient not found.");
                loadPatients();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ManagePatientsGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtBloodType;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmergencyContact;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPatientID;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
