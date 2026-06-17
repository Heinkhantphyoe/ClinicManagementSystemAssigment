/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinicmanagementsystem.gui.nurse;

import clinicmanagementsystem.model.VitalRecord;
import clinicmanagementsystem.service.PatientService;
import clinicmanagementsystem.service.VitalService;
import clinicmanagementsystem.util.SessionManager;
import clinicmanagementsystem.util.UI;
import clinicmanagementsystem.util.ValidationUtil;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class VitalsForm extends javax.swing.JFrame {
    
    private final VitalService vitalService = new VitalService();
    private final PatientService patientService = new PatientService();

    public VitalsForm() {
        initComponents();
        txtVitalsId.setText(vitalService.nextId());
        txtVitalsId.setEditable(false);
        txtNurseId.setText(SessionManager.getCurrentUserId());
        txtNurseId.setEditable(false);
    }

    private void clearForm() {
        txtVitalsId.setText(vitalService.nextId());
        txtAppointmentId.setText("");
        txtPatientId.setText("");
        txtDate.setText(LocalDate.now().toString());
        txtTemp.setText("");
        txtBP.setText("");txtHeartRate.setText("");
        txtWeight.setText("");
        txtNotes.setText("");
    }
    
    public VitalsForm(String appointmentId, String patientId) {
        initComponents();
        prefill(appointmentId, patientId);
    }
    
    private void prefill(String apptId, String patientId) {
        txtVitalsId.setText(vitalService.nextId());  // auto generated ID
        txtVitalsId.setEditable(false);
        txtNurseId.setText(SessionManager.getCurrentUserId());  // current nurse
        txtNurseId.setEditable(false);
        if (apptId != null)    txtAppointmentId.setText(apptId);
        if (patientId != null) txtPatientId.setText(patientId);
    }
    
    private void refreshVitalsTable(String patientId) {
        DefaultTableModel model = (DefaultTableModel) tblVitals.getModel();
        model.setRowCount(0);
        for (VitalRecord v : vitalService.findByPatientId(patientId)) {
            // VitalRecord has no weight/notes, so those two columns stay blank.
            model.addRow(new Object[]{
                v.getVitalId(), v.getRecordDate(), v.getTemperature(),
                v.getBloodPressure(), v.getHeartRate(), "", ""
            });
        }
    }
    
    private boolean validateInput() {
        // VitalRecord only stores patientId, temperature, bloodPressure, heartRate,
        // recordDate. Appointment ID / Nurse ID / Weight / Notes are UI-only here.
        String patientId = txtPatientId.getText().trim();
        String date      = txtDate.getText().trim();
        String temp      = txtTemp.getText().trim();
        String bp        = txtBP.getText().trim();
        String hr        = txtHeartRate.getText().trim();

        if (ValidationUtil.isNullOrEmpty(patientId) || ValidationUtil.isNullOrEmpty(date)
                || ValidationUtil.isNullOrEmpty(temp) || ValidationUtil.isNullOrEmpty(bp)
                || ValidationUtil.isNullOrEmpty(hr)) {
            JOptionPane.showMessageDialog(this, "Patient ID, Date, Temperature, Blood Pressure and Heart Rate are required.");
            return false;
        }
        if (patientService.findByPatientId(patientId) == null) {
            JOptionPane.showMessageDialog(this, "Patient ID not found: " + patientId); return false;
        }
        if (!ValidationUtil.isValidDate(date)) {
            JOptionPane.showMessageDialog(this, "Date must be yyyy-MM-dd."); return false;
        }
        if (!ValidationUtil.isDouble(temp)) { JOptionPane.showMessageDialog(this, "Temperature must be a number."); return false; }
        if (!ValidationUtil.isInteger(hr))  { JOptionPane.showMessageDialog(this, "Heart rate must be an integer."); return false; }

        // A comma would corrupt the CSV row, so block it in the stored fields.
        if (ValidationUtil.containsComma(patientId) || ValidationUtil.containsComma(bp)) {
            JOptionPane.showMessageDialog(this, "Fields cannot contain a comma."); return false;
        }

        // Loose sanity ranges so an obvious typo (e.g. 370 instead of 37.0) is caught.
        double tempVal = Double.parseDouble(temp);
        int    hrVal   = Integer.parseInt(hr);
        if (tempVal < 25 || tempVal > 45) { JOptionPane.showMessageDialog(this, "Temperature looks out of range (25-45 C)."); return false; }
        if (hrVal   < 20 || hrVal   > 250){ JOptionPane.showMessageDialog(this, "Heart rate looks out of range (20-250 bpm)."); return false; }
        return true;
}

    /** Build a VitalRecord from the current form fields (shared by Add and Update). */
    private VitalRecord buildVitalFromForm() {
        return new VitalRecord(
            txtVitalsId.getText().trim(), txtPatientId.getText().trim(),
            txtTemp.getText().trim(), txtBP.getText().trim(),
            txtHeartRate.getText().trim(), txtDate.getText().trim());
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
        txtAppointmentId = new javax.swing.JTextField();
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Vitals ID", "Date", "Temp", "BP", "Heart Rate", "Weight", "Notes"
            }
        ));
        jScrollPane1.setViewportView(tblVitals);

        jLabel1.setText("Vitals ID:");

        jLabel2.setText("Appointment ID:");

        jLabel3.setText("Patient ID:");

        jLabel4.setText("Nurse ID:");

        jLabel5.setText("Date:");

        jLabel6.setText("Temperature:");

        jLabel7.setText("Blood Pressure:");

        jLabel8.setText("Heart Rate:");

        jLabel9.setText("Weight:");

        jLabel10.setText("Notes:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUpdate)
                        .addGap(40, 40, 40)
                        .addComponent(btnSearch)
                        .addGap(49, 49, 49)
                        .addComponent(btnClear)
                        .addGap(52, 52, 52)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHeartRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAppointmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNurseId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVitalsId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVitalsId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAppointmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(4, 4, 4)
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
                .addContainerGap(89, Short.MAX_VALUE))
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

       VitalRecord v = buildVitalFromForm();
       try {
           boolean ok = vitalService.update(v);
           JOptionPane.showMessageDialog(this, ok ? "Updated." : "Vitals ID not found.");
           refreshVitalsTable(v.getPatientId());
       } catch (IOException ex) {
           JOptionPane.showMessageDialog(this, "Could not update: " + ex.getMessage());
       }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!validateInput()) return;

        VitalRecord v = buildVitalFromForm();
        try {
            vitalService.addVital(v);
            JOptionPane.showMessageDialog(this, "Vitals recorded: " + v.getVitalId());
            refreshVitalsTable(v.getPatientId());
            clearForm();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save vitals: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
       clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        UI.setNimbus();
        java.awt.EventQueue.invokeLater(() -> new VitalsForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
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
    private javax.swing.JTable tblVitals;
    private javax.swing.JTextField txtAppointmentId;
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
