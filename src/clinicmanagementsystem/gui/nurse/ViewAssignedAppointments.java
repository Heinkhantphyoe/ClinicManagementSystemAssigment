/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinicmanagementsystem.gui.nurse;

import clinicmanagementsystem.util.FileManager;
import clinicmanagementsystem.util.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class ViewAssignedAppointments extends javax.swing.JFrame {
    
    private static final String APPOINTMENTS_FILE = "src/clinicmanagementsystem/data/appointments.txt";
    private static final String ROSTERS_FILE      = "src/clinicmanagementsystem/data/rosters.txt";
    private static final String PATIENTS_FILE     = "src/clinicmanagementsystem/data/patients.txt";

    public ViewAssignedAppointments() {
        initComponents();
        loadAppts(null);
    }

    /**
     * Load appointments assigned to the current nurse.
     * Pass a date to show only that day, or null to show all.
     */
    private void loadAppts(String dateFilter) {
        DefaultTableModel model = (DefaultTableModel) tblAppointments.getModel();
        model.setRowCount(0);
        String nurseId = SessionManager.getCurrentUserId();
        try {
            // 1) read patients once (for the name column)
            String[] patientLines = FileManager.readLines(PATIENTS_FILE);
            // 2) show appointments assigned to this nurse
            for (String line : FileManager.readLines(APPOINTMENTS_FILE)) {
                String[] a = line.split(",");   // apptId,patientId,doctorId,nurseId,date,time,appointType,status,notes
                if (a.length < 8) continue;
                if (nurseId == null || !a[3].trim().equalsIgnoreCase(nurseId.trim())) continue;
                if (dateFilter != null && !a[4].equals(dateFilter)) continue;
                model.addRow(new Object[]{
                    a[0], a[1], findName(patientLines, a[1]), a[4], a[5], a[2], a[7]
                });
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not read data: " + ex.getMessage());
        }
    }

    /** Find a patient's name in the already-read patient lines (or "Unknown"). */
    private String findName(String[] patientLines, String patientId) {
        for (String line : patientLines) {
            String[] p = line.split(",");
            if (p.length >= 2 && p[0].equalsIgnoreCase(patientId)) return p[1];
        }
        return "Unknown";
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        txtDate = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
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
                    .addComponent(btnBack))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String date = txtDate.getText().trim();
        boolean noFilter = date.isEmpty() || date.equalsIgnoreCase("Date");  // ignore the placeholder
        loadAppts(noFilter ? null : date);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtDate.setText("");
        loadAppts(null);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new clinicmanagementsystem.gui.NurseDashboardGUI().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ViewAssignedAppointments().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtDate;
    // End of variables declaration//GEN-END:variables
}
