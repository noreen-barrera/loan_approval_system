/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package admin;


import models.User;
import database.ConnectionManager;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author noreen
 */
public class ReportsPanel extends javax.swing.JPanel {

    /**
     * Creates new form ReportsPanel
     */
    private User currentUser;
    
    public ReportsPanel(User user) {
        this.currentUser = user;
        initComponents();
         ReportData();
    }
    
 private void ReportData() {
        try (Connection conn = ConnectionManager.getConnection()) {
            // 1. Load application counts
            String appSql = "SELECT status, COUNT(*) as count FROM loan_applications GROUP BY status";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(appSql)) {
                
                int total = 0, autoApproved = 0, approved = 0, rejected = 0, autoRejected = 0;
                
                while (rs.next()) {
                    String status = rs.getString("status");
                    int count = rs.getInt("count");
                    total += count;
                    
                    switch (status) {
                        case "Auto-Approved": autoApproved = count; break;
                        case "Approved": approved = count; break;
                        case "Rejected": rejected = count; break;
                        case "Auto-Rejected": autoRejected = count; break;
                    }
                }
                
                totalApplicationsCountLabel.setText(String.valueOf(total));
                autoApprovedCountLabel.setText(String.valueOf(autoApproved));
                approvedCountLabel.setText(String.valueOf(approved));
                rejectedCountLabel.setText(String.valueOf(rejected));
                autoRejectedCountLabel.setText(String.valueOf(autoRejected));
            }

            String rejectSql = "SELECT flagged_reason FROM loan_applications WHERE status IN ('Rejected', 'Auto-Rejected')";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(rejectSql)) {
                
                int lowCredit = 0, missingDocs = 0, suspicious = 0;
                
                while (rs.next()) {
                    String reason = rs.getString("flagged_reason");
                    if (reason != null) {
                        if (reason.contains("Low Credit")) lowCredit++;
                        if (reason.contains("Missing Documents")) missingDocs++;
                        if (reason.contains("Suspicious")) suspicious++;
                    }
                }
                
                lowCreditScoreCountLabel.setText(String.valueOf(lowCredit));
                missingDocuCountLabel.setText(String.valueOf(missingDocs));
                suspiciousCountLabel.setText(String.valueOf(suspicious));
            }

             String[] levels = {"Platinum", "Gold", "Silver", "Red"};
            for (String level : levels) {
                String prioritySql = "SELECT " +
                    "COUNT(*) as total, " +
                    "SUM(CASE WHEN status = 'Approved' THEN 1 ELSE 0 END) as approved, " +
                    "SUM(CASE WHEN status IN ('Rejected','Auto-Rejected') THEN 1 ELSE 0 END) as rejected " +
                    "FROM loan_applications WHERE priority_level = '" + level + "'";
                
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(prioritySql)) {
                    
                    if (rs.next()) {
                        updatePriorityLabels(level, rs.getInt("total"), rs.getInt("approved"), rs.getInt("rejected"));
                    }
                }
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading report: " + e.getMessage());
        }
    }

    private void updatePriorityLabels(String level, int total, int approved, int rejected) {
        switch (level) {
            case "Platinum":
                platinumTotalAppCountLabel.setText(String.valueOf(total));
                platinumApprovedCountLabel.setText(String.valueOf(approved));
                platinumRejectedCountLabel.setText(String.valueOf(rejected));
                break;
            case "Gold":
                goldTotalAppCountLabel.setText(String.valueOf(total));
                goldApprovedCountLabel.setText(String.valueOf(approved));
                goldRejectedCountLabel.setText(String.valueOf(rejected));
                break;
            case "Silver":
                silverTotalAppCountLabel.setText(String.valueOf(total));
                silverApprovedCountLabel.setText(String.valueOf(approved));
                silverRejectedCountLabel.setText(String.valueOf(rejected));
                break;
            case "Red":
                redTotalAppCountLabel.setText(String.valueOf(total));
                redApprovedCountLabel.setText(String.valueOf(approved));
                redRejectedCountLabel.setText(String.valueOf(rejected));
                break;
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

        jLabel1 = new javax.swing.JLabel();
        applicationsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        totalApplicationsCountLabel = new javax.swing.JLabel();
        autoApprovedCountLabel = new javax.swing.JLabel();
        approvedCountLabel = new javax.swing.JLabel();
        rejectedCountLabel = new javax.swing.JLabel();
        autoRejectedCountLabel = new javax.swing.JLabel();
        rejectsPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lowCreditScoreCountLabel = new javax.swing.JLabel();
        missingDocuCountLabel = new javax.swing.JLabel();
        suspiciousCountLabel = new javax.swing.JLabel();
        priorityPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        platinumTotalAppCountLabel = new javax.swing.JLabel();
        goldTotalAppCountLabel = new javax.swing.JLabel();
        silverTotalAppCountLabel = new javax.swing.JLabel();
        redTotalAppCountLabel = new javax.swing.JLabel();
        platinumApprovedCountLabel = new javax.swing.JLabel();
        goldApprovedCountLabel = new javax.swing.JLabel();
        silverApprovedCountLabel = new javax.swing.JLabel();
        redApprovedCountLabel = new javax.swing.JLabel();
        platinumRejectedCountLabel = new javax.swing.JLabel();
        goldRejectedCountLabel = new javax.swing.JLabel();
        silverRejectedCountLabel = new javax.swing.JLabel();
        redRejectedCountLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LOAN SYSTEM REPORT SUMMARY:");

        applicationsPanel.setBackground(new java.awt.Color(245, 246, 250));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel2.setText("Total Applications:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel3.setText("Auto-Approved:");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("Approved: ");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel6.setText("Rejected:");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel7.setText("Auto-Rejected:");

        javax.swing.GroupLayout applicationsPanelLayout = new javax.swing.GroupLayout(applicationsPanel);
        applicationsPanel.setLayout(applicationsPanelLayout);
        applicationsPanelLayout.setHorizontalGroup(
            applicationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(applicationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(applicationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(applicationsPanelLayout.createSequentialGroup()
                        .addComponent(totalApplicationsCountLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(applicationsPanelLayout.createSequentialGroup()
                        .addComponent(autoApprovedCountLabel)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(approvedCountLabel)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rejectedCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoRejectedCountLabel)
                        .addGap(83, 83, 83))))
        );
        applicationsPanelLayout.setVerticalGroup(
            applicationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(applicationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalApplicationsCountLabel))
                .addGap(29, 29, 29)
                .addGroup(applicationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(autoApprovedCountLabel)
                    .addComponent(approvedCountLabel)
                    .addComponent(rejectedCountLabel)
                    .addComponent(autoRejectedCountLabel))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        rejectsPanel.setBackground(new java.awt.Color(245, 246, 250));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel5.setText("Rejection Reasons:");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel8.setText("Reason:");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel9.setText("Low Credit Score:");

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel10.setText("Missing Documents:");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel11.setText("Suspicious Information:");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel12.setText("Count:");

        javax.swing.GroupLayout rejectsPanelLayout = new javax.swing.GroupLayout(rejectsPanel);
        rejectsPanel.setLayout(rejectsPanelLayout);
        rejectsPanelLayout.setHorizontalGroup(
            rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rejectsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rejectsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(rejectsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(56, 56, 56))
                    .addGroup(rejectsPanelLayout.createSequentialGroup()
                        .addGroup(rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addGroup(rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lowCreditScoreCountLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(missingDocuCountLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(suspiciousCountLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(50, 50, 50))))
        );
        rejectsPanelLayout.setVerticalGroup(
            rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rejectsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lowCreditScoreCountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(missingDocuCountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rejectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(suspiciousCountLabel))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        priorityPanel.setBackground(new java.awt.Color(245, 246, 250));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel13.setText("Priority Level Distribution:");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel14.setText("Priority Level");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel15.setText("Platinum");

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel16.setText("Gold");

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel17.setText("Silver");

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel18.setText("Applications");

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel19.setText("Red");

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel20.setText("Approved");

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel21.setText("Rejected");

        javax.swing.GroupLayout priorityPanelLayout = new javax.swing.GroupLayout(priorityPanel);
        priorityPanel.setLayout(priorityPanelLayout);
        priorityPanelLayout.setHorizontalGroup(
            priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(priorityPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(priorityPanelLayout.createSequentialGroup()
                        .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(priorityPanelLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18))
                            .addGroup(priorityPanelLayout.createSequentialGroup()
                                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16))
                                .addGap(53, 53, 53)
                                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(redTotalAppCountLabel)
                                    .addComponent(goldTotalAppCountLabel)
                                    .addComponent(platinumTotalAppCountLabel)
                                    .addComponent(silverTotalAppCountLabel))))
                        .addGap(18, 18, 18)
                        .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(priorityPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(goldApprovedCountLabel)
                                    .addComponent(platinumApprovedCountLabel)
                                    .addComponent(silverApprovedCountLabel)
                                    .addComponent(redApprovedCountLabel))))
                        .addGap(18, 18, 18)
                        .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(platinumRejectedCountLabel)
                            .addComponent(jLabel21)
                            .addComponent(goldRejectedCountLabel)
                            .addComponent(silverRejectedCountLabel)
                            .addComponent(redRejectedCountLabel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        priorityPanelLayout.setVerticalGroup(
            priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(priorityPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(platinumTotalAppCountLabel)
                    .addComponent(platinumApprovedCountLabel)
                    .addComponent(platinumRejectedCountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(goldTotalAppCountLabel)
                    .addComponent(goldApprovedCountLabel)
                    .addComponent(goldRejectedCountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(silverTotalAppCountLabel)
                    .addComponent(silverApprovedCountLabel)
                    .addComponent(silverRejectedCountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(priorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(redTotalAppCountLabel)
                    .addComponent(redApprovedCountLabel)
                    .addComponent(redRejectedCountLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(rejectsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(priorityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(applicationsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(applicationsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(priorityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rejectsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel applicationsPanel;
    private javax.swing.JLabel approvedCountLabel;
    private javax.swing.JLabel autoApprovedCountLabel;
    private javax.swing.JLabel autoRejectedCountLabel;
    private javax.swing.JLabel goldApprovedCountLabel;
    private javax.swing.JLabel goldRejectedCountLabel;
    private javax.swing.JLabel goldTotalAppCountLabel;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lowCreditScoreCountLabel;
    private javax.swing.JLabel missingDocuCountLabel;
    private javax.swing.JLabel platinumApprovedCountLabel;
    private javax.swing.JLabel platinumRejectedCountLabel;
    private javax.swing.JLabel platinumTotalAppCountLabel;
    private javax.swing.JPanel priorityPanel;
    private javax.swing.JLabel redApprovedCountLabel;
    private javax.swing.JLabel redRejectedCountLabel;
    private javax.swing.JLabel redTotalAppCountLabel;
    private javax.swing.JLabel rejectedCountLabel;
    private javax.swing.JPanel rejectsPanel;
    private javax.swing.JLabel silverApprovedCountLabel;
    private javax.swing.JLabel silverRejectedCountLabel;
    private javax.swing.JLabel silverTotalAppCountLabel;
    private javax.swing.JLabel suspiciousCountLabel;
    private javax.swing.JLabel totalApplicationsCountLabel;
    // End of variables declaration//GEN-END:variables

}
