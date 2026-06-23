/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinicmanagementsystem.gui.nurse;

import clinicmanagementsystem.util.FileManager;
import clinicmanagementsystem.util.SessionManager;
import clinicmanagementsystem.util.ValidationUtil;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class RecordPatientVitals extends javax.swing.JFrame {
    
    private static final String VITALS_FILE   = "src/clinicmanagementsystem/data/vitals.txt";
    private static final String PATIENTS_FILE = "src/clinicmanagementsystem/data/patients.txt";

    public RecordPatientVitals() {
        initComponents();
        txtVitalsId.setText(nextVitalId());
        txtVitalsId.setEditable(false);
        txtNurseId.setText(SessionManager.getCurrentUserId());
        txtNurseId.setEditable(false);
        txtDate.setText(LocalDate.now().toString());
        setupTableListener();
        refreshVitalsTable("");
    }

    private void clearForm() {
        txtVitalsId.setText(nextVitalId());
        txtPatientId.setText("");
        txtDate.setText(LocalDate.now().toString());
        txtTemp.setText("");
        txtBP.setText("");
        txtHeartRate.setText("");
        txtWeight.setText("");
        txtNotes.setText("");
    }
    
    public RecordPatientVitals(String appointmentId, String patientId) {
        initComponents();
        prefill(appointmentId, patientId);
        txtDate.setText(LocalDate.now().toString());
        setupTableListener();
        refreshVitalsTable(patientId != null ? patientId : "");
    }
    
    private void setupTableListener() {
        tblVitals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblVitals.getSelectedRow();
                if (row >= 0) {
                    String vitalsId = tblVitals.getValueAt(row, 0).toString();
                    txtVitalsId.setText(vitalsId);
                    
                    Object patientObj = tblVitals.getValueAt(row, 1);
                    txtPatientId.setText(patientObj != null ? patientObj.toString() : "");
                    
                    Object tempObj = tblVitals.getValueAt(row, 2);
                    txtTemp.setText(tempObj != null ? tempObj.toString() : "");
                    
                    Object bpObj = tblVitals.getValueAt(row, 3);
                    txtBP.setText(bpObj != null ? bpObj.toString() : "");
                    
                    Object hrObj = tblVitals.getValueAt(row, 4);
                    txtHeartRate.setText(hrObj != null ? hrObj.toString() : "");
                    
                    Object weightObj = tblVitals.getValueAt(row, 5);
                    txtWeight.setText(weightObj != null ? weightObj.toString() : "");
                    
                    Object notesObj = tblVitals.getValueAt(row, 6);
                    txtNotes.setText(notesObj != null ? notesObj.toString() : "");
                    
                    Object dateObj = tblVitals.getValueAt(row, 7);
                    txtDate.setText(dateObj != null ? dateObj.toString() : "");
                }
            }
        });
    }
    
    private void prefill(String apptId, String patientId) {
        txtVitalsId.setText(nextVitalId());          // auto generated ID
        txtVitalsId.setEditable(false);
        txtNurseId.setText(SessionManager.getCurrentUserId());  // current nurse
        txtNurseId.setEditable(false);
        if (patientId != null) txtPatientId.setText(patientId);
    }
    
    private void refreshVitalsTable(String patientId) {
        DefaultTableModel model = (DefaultTableModel) tblVitals.getModel();
        model.setRowCount(0);
        try {
            String[] lines = FileManager.readLines(VITALS_FILE);
            for (String line : lines) {
                if (line == null) continue;
                String[] p = line.split(",", -1);
                if (p.length < 6) continue;            // skip blank/bad rows
                if (patientId == null || patientId.isEmpty() || p[1].equalsIgnoreCase(patientId)) {
                    String weight = p.length > 5 ? p[5] : "";
                    String notes = p.length > 6 ? p[6] : "";
                    String date = p.length > 7 ? p[7] : "";
                    model.addRow(new Object[]{ p[0], p[1], p[2], p[3], p[4], weight, notes, date });
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not read vitals: " + ex.getMessage());
        }
    }
    
    private boolean validateInput() {
        String patientId = txtPatientId.getText().trim();
        String date      = txtDate.getText().trim();
        String temp      = txtTemp.getText().trim();
        String bp        = txtBP.getText().trim();
        String hr        = txtHeartRate.getText().trim();
        String weight    = txtWeight.getText().trim();
        String notes     = txtNotes.getText().trim();

        if (ValidationUtil.isNullOrEmpty(patientId) || ValidationUtil.isNullOrEmpty(date)
                || ValidationUtil.isNullOrEmpty(temp) || ValidationUtil.isNullOrEmpty(bp)
                || ValidationUtil.isNullOrEmpty(hr)) {
            JOptionPane.showMessageDialog(this, "Patient ID, Date, Temperature, Blood Pressure and Heart Rate are required.");
            return false;
        }
        if (!patientExists(patientId)) {
            JOptionPane.showMessageDialog(this, "Patient ID not found: " + patientId); return false;
        }
        if (!ValidationUtil.isValidDate(date)) {
            JOptionPane.showMessageDialog(this, "Date must be yyyy-MM-dd."); return false;
        }
        if (!ValidationUtil.isDouble(temp)) { JOptionPane.showMessageDialog(this, "Temperature must be a number."); return false; }
        if (!ValidationUtil.isInteger(hr))  { JOptionPane.showMessageDialog(this, "Heart rate must be an integer."); return false; }
        if (!ValidationUtil.isNullOrEmpty(weight) && !ValidationUtil.isDouble(weight)) { JOptionPane.showMessageDialog(this, "Weight must be a number."); return false; }

        // A comma would corrupt the CSV row, so block it in the stored fields.
        if (ValidationUtil.containsComma(patientId) || ValidationUtil.containsComma(bp) || ValidationUtil.containsComma(weight) || ValidationUtil.containsComma(notes)) {
            JOptionPane.showMessageDialog(this, "Fields cannot contain a comma."); return false;
        }

        // Loose sanity ranges so an obvious typo (e.g. 370 instead of 37.0) is caught.
        double tempVal = Double.parseDouble(temp);
        int    hrVal   = Integer.parseInt(hr);
        if (tempVal < 25 || tempVal > 45) { JOptionPane.showMessageDialog(this, "Temperature looks out of range (25-45 C)."); return false; }
        if (hrVal   < 20 || hrVal   > 250){ JOptionPane.showMessageDialog(this, "Heart rate looks out of range (20-250 bpm)."); return false; }
        return true;
}

    /** Next id like V001, V002 by scanning the file. */
    private String nextVitalId() {
        int max = 0;
        try {
            String[] lines = FileManager.readLines(VITALS_FILE);
            for (String line : lines) {
                if (line == null) continue;
                String[] p = line.split(",");
                if (p.length >= 1 && p[0].length() > 1) {
                    int n = ValidationUtil.parseIntOr(p[0].substring(1), 0);
                    if (n > max) max = n;
                }
            }
        } catch (IOException ex) {
            // file may not exist yet; start from V001
        }
        return String.format("V%03d", max + 1);
    }

    /** True if the patient id exists in patients.txt. */
    private boolean patientExists(String patientId) {
        try {
            String[] lines = FileManager.readLines(PATIENTS_FILE);
            for (String line : lines) {
                if (line == null) continue;
                String[] p = line.split(",");
                if (p.length >= 1 && p[0].equalsIgnoreCase(patientId)) return true;
            }
        } catch (IOException ex) {
            // ignore
        }
        return false;
    }

    /** Build one CSV line from the form (shared by Add and Update). */
    private String formToLine() {
        return txtVitalsId.getText().trim() + "," + txtPatientId.getText().trim() + ","
             + txtTemp.getText().trim() + "," + txtBP.getText().trim() + ","
             + txtHeartRate.getText().trim() + "," + txtWeight.getText().trim() + ","
             + txtNotes.getText().trim() + "," + txtDate.getText().trim();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtVitalsId = new javax.swing.JTextField();
        txtPatientId = new javax.swing.JTextField();
        txtNurseId = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtTemp = new javax.swing.JTextField();
        txtBP = new javax.swing.JTextField();
        txtHeartRate = new javax.swing.JTextField();
        txtWeight = new javax.swing.JTextField();
        txtNotes = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVitals = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtVitalsId.setEditable(false);

        txtPatientId.addActionListener(this::txtPatientIdActionPerformed);

        txtNurseId.addActionListener(this::txtNurseIdActionPerformed);

        txtTemp.addActionListener(this::txtTempActionPerformed);

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        tblVitals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vitals ID", "Patient ID", "Temp", "BP", "Heart Rate", "Weight", "Notes", "Date"
            }
        ));
        jScrollPane1.setViewportView(tblVitals);

        jLabel1.setText("Vitals ID:");

        jLabel3.setText("Patient ID:");

        jLabel4.setText("Nurse ID:");

        jLabel5.setText("Date:");

        jLabel6.setText("Temperature:");

        jLabel7.setText("Blood Pressure:");

        jLabel8.setText("Heart Rate:");

        jLabel9.setText("Weight:");

        jLabel10.setText("Notes:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel11.setText("Record Patient Vitals");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd)
                            .addComponent(jLabel1))
                        .addGap(48, 48, 48)
                        .addComponent(btnUpdate)
                        .addGap(40, 40, 40)
                        .addComponent(btnSearch)
                        .addGap(49, 49, 49)
                        .addComponent(btnClear)
                        .addGap(52, 52, 52)
                        .addComponent(btnBack)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTemp, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBP, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHeartRate, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtWeight, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNotes, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNurseId)
                                    .addComponent(txtDate)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtVitalsId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(274, 274, 274))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVitalsId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNurseId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHeartRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(btnBack))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPatientIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPatientIdActionPerformed
         btnSearchActionPerformed(evt);
    }//GEN-LAST:event_txtPatientIdActionPerformed

    private void txtNurseIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNurseIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNurseIdActionPerformed

    private void txtTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String patientId = txtPatientId.getText().trim();
        if (ValidationUtil.isNullOrEmpty(patientId)) {
            JOptionPane.showMessageDialog(this, "Enter a Patient ID to search."); return;
        }
        refreshVitalsTable(patientId);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       if (!validateInput()) return;

       String id = txtVitalsId.getText().trim();
       try {
           String[] lines = FileManager.readLines(VITALS_FILE);
           boolean found = false;
           for (int i = 0; i < lines.length; i++) {
               if (lines[i] == null) continue;
               String[] p = lines[i].split(",");
               if (p.length >= 1 && p[0].equalsIgnoreCase(id)) {
                   lines[i] = formToLine();
                   found = true;
               }
           }
           if (found) {
               FileManager.writeLines(VITALS_FILE, lines);
               JOptionPane.showMessageDialog(this, "Updated.");
               refreshVitalsTable(txtPatientId.getText().trim());
               clearForm();
           } else {
               JOptionPane.showMessageDialog(this, "Vitals ID not found.");
           }
       } catch (IOException ex) {
           JOptionPane.showMessageDialog(this, "Could not update: " + ex.getMessage());
       }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!validateInput()) return;

        String patientId = txtPatientId.getText().trim();
        try {
            FileManager.appendLine(VITALS_FILE, formToLine());
            JOptionPane.showMessageDialog(this, "Vitals recorded: " + txtVitalsId.getText().trim());
            refreshVitalsTable(patientId);
            clearForm();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save vitals: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
       clearForm();
       refreshVitalsTable("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new clinicmanagementsystem.gui.NurseDashboardGUI().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new RecordPatientVitals().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVitals;
    private javax.swing.JTextField txtBP;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtHeartRate;
    private javax.swing.JTextField txtNotes;
    private javax.swing.JTextField txtNurseId;
    private javax.swing.JTextField txtPatientId;
    private javax.swing.JTextField txtTemp;
    private javax.swing.JTextField txtVitalsId;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables
}
