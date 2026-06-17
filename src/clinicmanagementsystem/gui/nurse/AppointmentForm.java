/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinicmanagementsystem.gui.nurse;

import clinicmanagementsystem.model.Appointment;
import clinicmanagementsystem.model.Patient;
import clinicmanagementsystem.model.Roster;
import clinicmanagementsystem.service.AppointmentService;
import clinicmanagementsystem.service.PatientService;
import clinicmanagementsystem.service.RosterService;
import clinicmanagementsystem.util.SessionManager;
import clinicmanagementsystem.util.UI;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author PC
 */
public class AppointmentForm extends javax.swing.JFrame {
    
    private final AppointmentService appointmentService = new AppointmentService();
    private final PatientService patientService = new PatientService();
    private final RosterService rosterService = new RosterService();

    public AppointmentForm() {
        initComponents();
        loadAppts(assignedAppointments(null));
    }

    /**
     * Option A: the appointments a nurse is responsible for are the ones that
     * fall on the dates the nurse is rostered for (the Appointment model has no
     * nurseId). Pass a date to narrow to that single day.
     */
    private List<Appointment> assignedAppointments(String dateFilter) {
        Set<String> dutyDates = new HashSet<>();
        String staffId = SessionManager.getCurrentUserId();
        if (staffId != null) {
            for (Roster r : rosterService.findByStaffId(staffId)) dutyDates.add(r.getDutyDate());
        }
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentService.getAllAppointments()) {
            if (!dutyDates.contains(a.getAppointmentDate())) continue;
            if (dateFilter != null && !a.getAppointmentDate().equals(dateFilter)) continue;
            result.add(a);
        }
        return result;
    }

    private void loadAppts(List<Appointment> list) {
        // Load every patient ONCE into a lookup map instead of re-reading the
        // whole patients file for every single appointment row (was N+1).
        Map<String, String> nameById = new HashMap<>();
        for (Patient p : patientService.getAllPatients()) {
            nameById.put(p.getPatientId().toLowerCase(), p.getName());
        }
        DefaultTableModel model = (DefaultTableModel) tblAppointments.getModel();
        model.setRowCount(0);
        for (Appointment a : list) {
            String patientName = nameById.getOrDefault(a.getPatientId().toLowerCase(), "Unknown");
            model.addRow(new Object[]{
                a.getAppointmentId(), a.getPatientId(), patientName,
                a.getAppointmentDate(), a.getAppointmentTime(), a.getDoctorId(), a.getStatus()
            });
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtDate = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        jButton3.setText("RecordVitals");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        txtDate.setText("Date");

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Appt ID", " Patient ID", "Patient Name", "Date", "Time", "Doctor", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblAppointments);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh)
                    .addComponent(btnBack)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String date = txtDate.getText().trim();
        boolean noFilter = date.isEmpty() || date.equalsIgnoreCase("Date");  // ignore the placeholder
        loadAppts(assignedAppointments(noFilter ? null : date));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtDate.setText("");
        loadAppts(assignedAppointments(null));
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         int row = tblAppointments.getSelectedRow();
         if (row < 0) {
             JOptionPane.showMessageDialog(this, "Please select an appointment first.");
             return;
         }
         String apptId    = tblAppointments.getValueAt(row, 0).toString();
         String patientId = tblAppointments.getValueAt(row, 1).toString();
         new VitalsForm(apptId, patientId).setVisible(true);    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        UI.setNimbus();
        java.awt.EventQueue.invokeLater(() -> new AppointmentForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtDate;
    // End of variables declaration//GEN-END:variables
}
