/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinicmanagementsystem.gui.doctor;
import javax.swing.table.DefaultTableModel;
import clinicmanagementsystem.gui.DoctorDashboardGUI;
import clinicmanagementsystem.model.MedicalRecord;
import clinicmanagementsystem.util.FileManager;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ManageMedicalRecordGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageMedicalRecordGUI.class.getName());
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = {"RECORD ID" , "PATIENT ID" , "RECORDED BY" ,"DIAGNOSIS" , "TREATMENT PLAN" , "NOTES", "DATE"};
    private int row = -1;
    /**
     * Creates new form ManageMedicalRecordGUI
     */
    public ManageMedicalRecordGUI() {
        model.setColumnIdentifiers(columnName);
        initComponents();
        setupSearchPlaceholder();
        addMedicalRecordIntoTable();
        setMinimumSize(getSize());
    }

    private void setupSearchPlaceholder() {
        jTextField2.setText("search with patientID");
        jTextField2.setForeground(java.awt.Color.GRAY);
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextField2.getText().equals("search with patientID")) {
                    jTextField2.setText("");
                    jTextField2.setForeground(java.awt.Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextField2.getText().trim().isEmpty()) {
                    jTextField2.setForeground(java.awt.Color.GRAY);
                    jTextField2.setText("search with patientID");
                }
            }
        });
    }
    private String[] loadMedicalRecord(){
          try {
                return FileManager.readLines("src/clinicmanagementsystem/data/medical_records.txt");
            }
            catch(Exception e){
                logger.log(java.util.logging.Level.SEVERE, "Failed to load medical records", e);
            }
            return new String[0];
        }
    
    private void addMedicalRecordIntoTable(){
        try {
            model.setRowCount(0);
            String[] lines = loadMedicalRecord();

            for(String line : lines) {
                if (line == null || line.isEmpty()) {
                    continue;
                }
                String[] fields = line.split(",", -1);
                if (fields.length < columnName.length) {
                    String[] padded = new String[columnName.length];
                    for (int i = 0; i < columnName.length; i++) padded[i] = (i < fields.length) ? fields[i] : "";
                    fields = padded;
                }
                model.addRow(fields); 
            }
        }
        catch(Exception e) {
                 logger.log(java.util.logging.Level.SEVERE, "Failed to load medical records", e);
                }
    }
    
    private void clearField(){
        if(row >-1){
            jTextField1.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            NoteTextField.setText("");
        }
    }
    
    private void fileUpdate(String id){
         try{
            String[] lines = FileManager.readLines("src/clinicmanagementsystem/data/medical_records.txt");

            for(int i = 0; i < lines.length; i++) {
                String line = lines[i];
                if (line == null || line.isEmpty()) {
                    continue;
                }
                String[] fields = line.split(",", -1);
                if (fields.length < columnName.length) {
                    String[] padded = new String[columnName.length];
                    for (int j = 0; j < columnName.length; j++) padded[j] = (j < fields.length) ? fields[j] : "";
                    fields = padded;
                }
              String database_recordId = fields[0];
              if(database_recordId.equals(id)){
                String recordId = String.valueOf(model.getValueAt(row , 0));
                String patientId = String.valueOf(model.getValueAt(row, 1));
                String recordIdBy = String.valueOf(model.getValueAt(row,2));
                String diagnosis = String.valueOf(model.getValueAt(row, 3));
                String treatment = String.valueOf(model.getValueAt(row , 4));
                String notes = String.valueOf(model.getValueAt(row, 5));
                String date = String.valueOf(model.getValueAt(row, 6));
                
                lines[i] = recordId + "," + patientId + "," + recordIdBy + "," + diagnosis + "," + treatment + "," + notes + "," + date;
                break;
              }
            }

            FileManager.writeLines("src/clinicmanagementsystem/data/medical_records.txt", lines);
         }catch(Exception e){
             logger.log(java.util.logging.Level.SEVERE, "Failed to update medical record", e);
         }
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        NoteTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backButton.setText("Back");
        backButton.addActionListener(this::backButtonActionPerformed);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel1.setText("Patient Medical Records");

        jTable1.setModel(model);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Record ID");

        jLabel3.setText("Patient ID");

        jLabel4.setText("Recorded By");

        jLabel5.setText("Diagnosis");

        jLabel6.setText("Treatment Plan");

        jLabel7.setText("Date");

        jTextField2.addActionListener(this::jTextField2ActionPerformed);

        jButton1.setText("Search");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        jTextField4.addActionListener(this::jTextField4ActionPerformed);

        jTextField5.addActionListener(this::jTextField5ActionPerformed);

        jTextField6.addActionListener(this::jTextField6ActionPerformed);

        jTextField7.addActionListener(this::jTextField7ActionPerformed);

        jButton2.setText("Update / Create Records");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Refresh");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel8.setText("Note");

        NoteTextField.addActionListener(this::NoteTextFieldActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                    .addComponent(NoteTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField6)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel2))
                                                .addGap(33, 33, 33))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel4))
                                                .addGap(18, 18, 18)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField5)))
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(352, 352, 352)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backButton)
                            .addComponent(jButton3))
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addComponent(NoteTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        DoctorDashboardGUI doctorDashboardGUI = new DoctorDashboardGUI();
        doctorDashboardGUI.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        loadMedicalRecord();
        addMedicalRecordIntoTable();
        clearField();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        // TODO add your handling code here:
        row = jTable1.getSelectedRow();
        if (row > -1) {
            String recordId = model.getValueAt(row, 0) != null ? model.getValueAt(row, 0).toString() : "";
            String patientId = model.getValueAt(row, 1) != null ? model.getValueAt(row, 1).toString() : "";
            String recordIdBy = model.getValueAt(row, 2) != null ? model.getValueAt(row, 2).toString() : "";
            String diagnosis = model.getValueAt(row, 3) != null ? model.getValueAt(row, 3).toString() : "";
            String treatment = model.getValueAt(row, 4) != null ? model.getValueAt(row, 4).toString() : "";
            String notes = model.getValueAt(row, 5) != null ? model.getValueAt(row, 5).toString() : "";
            String date = model.getValueAt(row, 6) != null ? model.getValueAt(row, 6).toString() : "";

            jTextField1.setText(recordId);
            jTextField3.setText(patientId);
            jTextField4.setText(recordIdBy);
            jTextField5.setText(diagnosis);
            jTextField6.setText(treatment);
            NoteTextField.setText(notes);
            jTextField7.setText(date);
        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(row > -1 ){
           
            String newrecordId = jTextField1.getText();
            String newpatientId = jTextField3.getText();
            String newrecordedBy = jTextField4.getText();
            String newdiagosis = jTextField5.getText();
            String newtreatment = jTextField6.getText();
            String newNote = NoteTextField.getText();
            String newdate = jTextField7.getText();
            
            model.setValueAt(newrecordId , row , 0);
            model.setValueAt(newpatientId , row , 1);
            model.setValueAt(newrecordedBy , row , 2);
            model.setValueAt(newdiagosis , row , 3);
            model.setValueAt(newtreatment , row , 4);
            model.setValueAt(newNote , row , 5);
            model.setValueAt(newdate , row , 6);
            
            fileUpdate(newrecordId);
            clearField();
        }else {
            JOptionPane.showMessageDialog(this, "You need to select a row to Edit!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String searchKeyWord = jTextField2.getText().trim();
        
        if (searchKeyWord.isEmpty() || searchKeyWord.equals("search with patientID")) {
            JOptionPane.showMessageDialog(this, "Please enter a Patient ID to search!", "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{ 
            model.setRowCount(0);
            String[] lines = loadMedicalRecord();
            boolean found = false;
            
            for(String line : lines){
               if (line == null || line.isEmpty()) {
                   continue;
               }
               String[] fields = line.split(",", -1);
               if (fields.length < columnName.length) {
                    String[] padded = new String[columnName.length];
                    for (int j = 0; j < columnName.length; j++) padded[j] = (j < fields.length) ? fields[j] : "";
                    fields = padded;
               }
               String database_patientId = fields[1];
               if(database_patientId.equalsIgnoreCase(searchKeyWord)){
                   model.addRow(fields);
                   found = true;
               }
            }
            
            if (!found) {
                JOptionPane.showMessageDialog(this, "No data found with Patient ID: " + searchKeyWord, "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
            
            clearField();
        }catch(Exception e){
            logger.log(java.util.logging.Level.SEVERE, "Failed to search medical record", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NoteTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoteTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoteTextFieldActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ManageMedicalRecordGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NoteTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
