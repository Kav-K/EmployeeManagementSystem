/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JTable;

/**
 *
 * @author kaveen
 */
public class EMSEditorPTE extends javax.swing.JFrame {

    private static PartTimeEmployee employee;
    private static JTable employeeJTable;

    /**
     * Creates new form EMSEditorFTE
     */
    public EMSEditorPTE(PartTimeEmployee employee, JTable employeeJTable) {
        this.employee = employee;
        this.employeeJTable = employeeJTable;
        initComponents();
        center();

        lockFields();
        setFields();

    }

    private void unlockFields() {
        workLocationField.setEnabled(true);
        firstNameField.setEnabled(true);
        lastNameField.setEnabled(true);
        employeeNumberField.setEnabled(true);
        hourlyWageField.setEnabled(true);
        maleButton.setEnabled(true);
        femaleButton.setEnabled(true);
        hourlyWageField.setEnabled(true);
        hoursPerWeekField.setEnabled(true);
        weeksPerYearField.setEnabled(true);
    }

    private void lockFields() {
        workLocationField.setMaximumSize(workLocationField.getPreferredScrollableViewportSize());
        firstNameField.setMaximumSize(firstNameField.getPreferredScrollableViewportSize());
        lastNameField.setMaximumSize(lastNameField.getPreferredScrollableViewportSize());
        employeeNumberField.setMaximumSize(employeeNumberField.getPreferredScrollableViewportSize());
        hourlyWageField.setMaximumSize(hourlyWageField.getPreferredScrollableViewportSize());
        hoursPerWeekField.setMaximumSize(hoursPerWeekField.getPreferredScrollableViewportSize());
        weeksPerYearField.setMaximumSize(weeksPerYearField.getPreferredScrollableViewportSize());
        hoursPerWeekField.setEnabled(false);
        weeksPerYearField.setEnabled(false);
        workLocationField.setEnabled(false);
        firstNameField.setEnabled(false);
        lastNameField.setEnabled(false);
        employeeNumberField.setEnabled(false);
        hourlyWageField.setEnabled(false);
        maleButton.setEnabled(false);
        femaleButton.setEnabled(false);
        saveButton.setVisible(false);
        revertButton.setVisible(false);

    }

    private void setFields() {
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        employeeNumberField.setText("" + employee.getEmployeeNumber());
        if (employee.getSex() == 0) {
            maleButton.setSelected(true);
        } else {
            femaleButton.setSelected(true);
        }
        workLocationField.setText(employee.getWorkLocation());
        hourlyWageField.setText("" + employee.getHourlyWage());
        hoursPerWeekField.setText("" + employee.getHoursPerWeek());
        weeksPerYearField.setText("" + employee.getWeeksPerYear());
        return;

    }

    private void center() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        employeeEditorLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        employeeNumberLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        sexLabel = new javax.swing.JLabel();
        workLocationLabel = new javax.swing.JLabel();
        hourlyWageLabel = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        employeeNumberField = new javax.swing.JTextField();
        workLocationField = new javax.swing.JTextField();
        hourlyWageField = new javax.swing.JTextField();
        maleButton = new javax.swing.JRadioButton();
        femaleButton = new javax.swing.JRadioButton();
        saveButton = new javax.swing.JButton();
        revertButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JToggleButton();
        editButton = new javax.swing.JToggleButton();
        hoursPerWeekLabel = new javax.swing.JLabel();
        weeksPerYearLabel = new javax.swing.JLabel();
        hoursPerWeekField = new javax.swing.JTextField();
        weeksPerYearField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        employeeEditorLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        employeeEditorLabel.setText("Employee Viewer (Full Time)");

        firstNameLabel.setText("First Name:");

        employeeNumberLabel.setText("Employee Number:");

        lastNameLabel.setText("Last Name:");

        sexLabel.setText("Sex:");

        workLocationLabel.setText("Work Location:");

        hourlyWageLabel.setText("Hourly Wage:");

        firstNameField.setText("jTextField1");
        firstNameField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        firstNameField.setPreferredSize(new java.awt.Dimension(177, 33));
        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });

        lastNameField.setText("jTextField2");
        lastNameField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        lastNameField.setPreferredSize(new java.awt.Dimension(177, 33));

        employeeNumberField.setText("jTextField3");
        employeeNumberField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        employeeNumberField.setPreferredSize(new java.awt.Dimension(177, 33));

        workLocationField.setText("jTextField5");
        workLocationField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        workLocationField.setPreferredSize(new java.awt.Dimension(177, 33));

        hourlyWageField.setText("jTextField6");
        hourlyWageField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        hourlyWageField.setPreferredSize(new java.awt.Dimension(177, 33));
        hourlyWageField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourlyWageFieldActionPerformed(evt);
            }
        });

        maleButton.setText("Male");
        maleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleButtonActionPerformed(evt);
            }
        });

        femaleButton.setText("Female");
        femaleButton.setToolTipText("");
        femaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        saveButton.setText("Save Changes");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        revertButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        revertButton.setText("Revert Changes");
        revertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revertButtonActionPerformed(evt);
            }
        });

        errorLabel.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        errorLabel.setForeground(new java.awt.Color(255, 34, 0));

        deleteButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        hoursPerWeekLabel.setText("Hours/Week:");

        weeksPerYearLabel.setText("Weeks/Year:");

        hoursPerWeekField.setText("jTextField6");
        hoursPerWeekField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        hoursPerWeekField.setPreferredSize(new java.awt.Dimension(177, 33));
        hoursPerWeekField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoursPerWeekFieldActionPerformed(evt);
            }
        });

        weeksPerYearField.setText("jTextField6");
        weeksPerYearField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        weeksPerYearField.setPreferredSize(new java.awt.Dimension(177, 33));
        weeksPerYearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weeksPerYearFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(sexLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(weeksPerYearLabel)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(workLocationLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(hoursPerWeekLabel)
                                        .addComponent(hourlyWageLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lastNameLabel)
                                    .addComponent(employeeNumberLabel)
                                    .addComponent(firstNameLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(maleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(femaleButton))
                    .addComponent(firstNameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lastNameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeeNumberField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workLocationField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hourlyWageField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hoursPerWeekField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(weeksPerYearField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(revertButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(employeeEditorLabel)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employeeNumberLabel)
                            .addComponent(employeeNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sexLabel)
                            .addComponent(maleButton)
                            .addComponent(femaleButton))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(workLocationLabel)
                            .addComponent(workLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hourlyWageLabel)
                            .addComponent(hourlyWageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoursPerWeekLabel)
                            .addComponent(hoursPerWeekField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(weeksPerYearLabel)
                            .addComponent(weeksPerYearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(employeeEditorLabel)
                        .addGap(18, 18, 18)
                        .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editButton)
                            .addComponent(deleteButton))
                        .addGap(18, 18, 18)
                        .addComponent(revertButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleButtonActionPerformed
        // TODO add your handling code here:
        if (femaleButton.isSelected()) {
            femaleButton.setSelected(false);
            maleButton.setSelected(true);
        } else if (!(femaleButton.isSelected() && maleButton.isSelected())) {
            maleButton.setSelected(true);
        }

    }//GEN-LAST:event_maleButtonActionPerformed

    private void femaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleButtonActionPerformed
        // TODO add your handling code here:
        if (maleButton.isSelected()) {
            maleButton.setSelected(false);
            femaleButton.setSelected(true);
        } else if (!(femaleButton.isSelected() && maleButton.isSelected())) {
            femaleButton.setSelected(true);
        }

    }//GEN-LAST:event_femaleButtonActionPerformed

    private void firstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFieldActionPerformed
    private boolean validate(String employeeNumber, String hourlyWage, String hoursPerWeek, String weeksPerYear) {
        int employeeNumParsed;
        try {
            employeeNumParsed = Integer.parseInt(employeeNumber);
        } catch (Exception e) {
            errorLabel.setText("Invalid employee number.");
            return false;
        }

        try {
            Double.parseDouble(hourlyWage);
        } catch (Exception e) {
            errorLabel.setText("Invalid yearly salary.");
            return false;
        }
        try {
            Double.parseDouble(hoursPerWeek);
        } catch (Exception e) {
            errorLabel.setText("Invalid hours/week.");
            return false;
        }
        try {
            Double.parseDouble(weeksPerYear);
        } catch (Exception e) {
            errorLabel.setText("Invalid weeks/year.");
            return false;
        }

        if ((employeeNumParsed != employee.getEmployeeNumber()) && EMSMainAndLogin.employeeTable.toArray().contains(EMSMainAndLogin.employeeTable.get(employeeNumParsed))) {
            errorLabel.setText("Employee number already exists");
            return false;

        }
        return true;
    }
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (validate(employeeNumberField.getText(), hourlyWageField.getText(), hoursPerWeekField.getText(), weeksPerYearField.getText())) {

            employee.setEmployeeNumber(Integer.parseInt(employeeNumberField.getText()));
            employee.setFirstName(firstNameField.getText());
            employee.setLastName(lastNameField.getText());
            if (maleButton.isSelected()) {
                employee.setSex(0);
            } else {
                employee.setSex(1);
            }
            employee.setWorkLocation(workLocationField.getText());
            employee.setHourlyWage(Double.parseDouble(hourlyWageField.getText()));
            employee.setHoursPerWeek(Double.parseDouble(hoursPerWeekField.getText()));
            employee.setWeeksPerYear(Double.parseDouble(weeksPerYearField.getText()));
            Employee.serialize();
            //this.setVisible(false);
            EMSMainAndLogin.employeeTable.populateJFrameTable(this.employeeJTable);
            editButton.setEnabled(true);
            editButton.setSelected(false);
            saveButton.setVisible(false);
            revertButton.setVisible(false);
            deleteButton.setEnabled(true);

            lockFields();
            setFields();

        }


    }//GEN-LAST:event_saveButtonActionPerformed

    private void hourlyWageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourlyWageFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourlyWageFieldActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        EMSMainAndLogin.employeeTable.remove(employee.getEmployeeNumber());
        Employee.serialize();
        EMSMainAndLogin.employeeTable.populateJFrameTable(employeeJTable);
        this.setVisible(false);


    }//GEN-LAST:event_deleteButtonActionPerformed

    private void revertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revertButtonActionPerformed
        setFields();
        lockFields();
        editButton.setEnabled(true);
        editButton.setSelected(false);
        deleteButton.setEnabled(true);
    }//GEN-LAST:event_revertButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        editButton.setSelected(true);
        editButton.setEnabled(false);
        unlockFields();
        saveButton.setVisible(true);
        revertButton.setVisible(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void hoursPerWeekFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoursPerWeekFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoursPerWeekFieldActionPerformed

    private void weeksPerYearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weeksPerYearFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weeksPerYearFieldActionPerformed

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
            java.util.logging.Logger.getLogger(EMSEditorPTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EMSEditorPTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EMSEditorPTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EMSEditorPTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EMSEditorPTE(employee, employeeJTable).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton deleteButton;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JLabel employeeEditorLabel;
    private javax.swing.JTextField employeeNumberField;
    private javax.swing.JLabel employeeNumberLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JRadioButton femaleButton;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField hourlyWageField;
    private javax.swing.JLabel hourlyWageLabel;
    private javax.swing.JTextField hoursPerWeekField;
    private javax.swing.JLabel hoursPerWeekLabel;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JButton revertButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel sexLabel;
    private javax.swing.JTextField weeksPerYearField;
    private javax.swing.JLabel weeksPerYearLabel;
    private javax.swing.JTextField workLocationField;
    private javax.swing.JLabel workLocationLabel;
    // End of variables declaration//GEN-END:variables
}
