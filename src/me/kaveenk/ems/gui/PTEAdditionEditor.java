/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems.gui;

import me.kaveenk.ems.main.EMSMain;
import me.kaveenk.ems.types.Employee;
import me.kaveenk.ems.types.PartTimeEmployee;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import static me.kaveenk.ems.main.EMSMain.logger;

/**
 *
 * @author kaveen
 */
public class PTEAdditionEditor extends javax.swing.JFrame {

    private static JTable employeeJTable;
    private static Point mouseDownCompCoords = null;

    /**
     * Create new PTE addition editor
     *
     * @param employeeJTable Instance of Main Menu JTable
     */
    public PTEAdditionEditor(JTable employeeJTable) {
        this.setUndecorated(true);
        this.setResizable(false);

        this.employeeJTable = employeeJTable;
        
        initComponents();

        stylizeFields();
        stylizeLabels();
        stylizeButtons();
        setBackgroundLabel();
        center();

        lockFields();

        initWindowListener();
        initMouseListener();

    }

    private void initWindowListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                MainMenu.activeEditor = false;
                event.getWindow().dispose();
            }
        });
    }

    private void initMouseListener() {
        try {
            this.addMouseListener(new MouseListener() {
                public void mouseReleased(MouseEvent e) {
                    mouseDownCompCoords = null;
                }

                public void mousePressed(MouseEvent e) {
                    mouseDownCompCoords = e.getPoint();
                }

                public void mouseExited(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseClicked(MouseEvent e) {
                }
            });

            this.addMouseMotionListener(new MouseMotionListener() {
                public void mouseMoved(MouseEvent e) {
                }

                public void mouseDragged(MouseEvent e) {
                    Point currCoords = e.getLocationOnScreen();
                    setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
                }
            });
        } catch (Exception e) {
            //Silence
        }

    }

    private void stylizeFields() {
        firstNameField.setBackground(new Color(17, 17, 17));
        firstNameField.setForeground(new Color(230, 230, 230));
        lastNameField.setBackground(new Color(17, 17, 17));
        lastNameField.setForeground(new Color(230, 230, 230));
        employeeNumberField.setBackground(new Color(17, 17, 17));
        employeeNumberField.setForeground(new Color(230, 230, 230));
        workLocationField.setBackground(new Color(17, 17, 17));
        workLocationField.setForeground(new Color(230, 230, 230));
        hourlyWageField.setBackground(new Color(17, 17, 17));
        hourlyWageField.setForeground(new Color(230, 230, 230));
        hoursPerWeekField.setBackground(new Color(17, 17, 17));
        hoursPerWeekField.setForeground(new Color(230, 230, 230));
        weeksPerYearField.setBackground(new Color(17, 17, 17));
        weeksPerYearField.setForeground(new Color(230, 230, 230));
        deductionRateField.setBackground(new Color(17, 17, 17));
        deductionRateField.setForeground(new Color(230, 230, 230));;

    }

    private void stylizeLabels() {
        firstNameLabel.setForeground(new Color(230, 230, 230));
        lastNameLabel.setForeground(new Color(230, 230, 230));
        employeeNumberLabel.setForeground(new Color(230, 230, 230));
        sexLabel.setForeground(new Color(230, 230, 230));
        workLocationLabel.setForeground(new Color(230, 230, 230));
        hourlyWageLabel.setForeground(new Color(230, 230, 230));
        hoursPerWeekLabel.setForeground(new Color(230, 230, 230));
        weeksPerYearLabel.setForeground(new Color(230, 230, 230));
        employeeEditorLabel.setForeground(new Color(230, 230, 230));
        femaleButton.setForeground(new Color(230, 230, 230));
        maleButton.setForeground(new Color(230, 230, 230));
        deductionRateLabel.setForeground(new Color(230, 230, 230));

    }

    private void stylizeButtons() {
        addButton.setBackground(new Color(17, 17, 17));
        addButton.setForeground(new Color(230, 230, 230));

        exitButton.setBackground(new Color(1, 1, 1));
        exitButton.setForeground(new Color(230, 230, 230));
        minimizeButton.setBackground(new Color(1, 1, 1));
        minimizeButton.setForeground(new Color(230, 230, 230));
    }

    private void reDraw() {
        this.revalidate();
        this.repaint();
    }

    private void setBackgroundLabel() {
        BufferedImage background = null;
        try {
            background = ImageIO.read(new File("resources/bg.jpg"));

        } catch (Exception e) {
            logger.error("There was an error in loading the background image for the program!", e);
        }

        JLabel backgroundLabel = new JLabel(new ImageIcon(background));
        backgroundLabel.setBounds(0, 0, backgroundLabel.getPreferredSize().width, backgroundLabel.getPreferredSize().height);
        this.getContentPane().add(backgroundLabel);

    }

    private void lockFields() {
        workLocationField.setMaximumSize(workLocationField.getPreferredScrollableViewportSize());
        firstNameField.setMaximumSize(firstNameField.getPreferredScrollableViewportSize());
        lastNameField.setMaximumSize(lastNameField.getPreferredScrollableViewportSize());
        employeeNumberField.setMaximumSize(employeeNumberField.getPreferredScrollableViewportSize());
        hourlyWageField.setMaximumSize(hourlyWageField.getPreferredScrollableViewportSize());
        hoursPerWeekField.setMaximumSize(hoursPerWeekField.getPreferredScrollableViewportSize());
        weeksPerYearField.setMaximumSize(weeksPerYearField.getPreferredScrollableViewportSize());

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
        errorLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JToggleButton();
        hoursPerWeekLabel = new javax.swing.JLabel();
        weeksPerYearLabel = new javax.swing.JLabel();
        hoursPerWeekField = new javax.swing.JTextField();
        weeksPerYearField = new javax.swing.JTextField();
        exitButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();
        deductionRateField = new javax.swing.JTextField();
        deductionRateLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        employeeEditorLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        employeeEditorLabel.setText("Employee Addition (Part Time)");

        firstNameLabel.setText("First Name:");

        employeeNumberLabel.setText("Employee Number:");

        lastNameLabel.setText("Last Name:");

        sexLabel.setText("Sex:");

        workLocationLabel.setText("Work Location:");

        hourlyWageLabel.setText("Hourly Wage:");

        firstNameField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        firstNameField.setPreferredSize(new java.awt.Dimension(177, 33));
        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });

        lastNameField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        lastNameField.setPreferredSize(new java.awt.Dimension(177, 33));

        employeeNumberField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        employeeNumberField.setPreferredSize(new java.awt.Dimension(177, 33));

        workLocationField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        workLocationField.setPreferredSize(new java.awt.Dimension(177, 33));

        hourlyWageField.setToolTipText("");
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

        errorLabel.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        errorLabel.setForeground(new java.awt.Color(255, 34, 0));

        addButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        hoursPerWeekLabel.setText("Hours/Week:");

        weeksPerYearLabel.setText("Weeks/Year:");

        hoursPerWeekField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        hoursPerWeekField.setPreferredSize(new java.awt.Dimension(177, 33));
        hoursPerWeekField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoursPerWeekFieldActionPerformed(evt);
            }
        });

        weeksPerYearField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        weeksPerYearField.setPreferredSize(new java.awt.Dimension(177, 33));
        weeksPerYearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weeksPerYearFieldActionPerformed(evt);
            }
        });

        exitButton.setText("X");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        minimizeButton.setText("-");
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });

        deductionRateField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        deductionRateField.setPreferredSize(new java.awt.Dimension(177, 33));
        deductionRateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionRateFieldActionPerformed(evt);
            }
        });

        deductionRateLabel.setText("Deduction Rate:");

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
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(workLocationLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(hoursPerWeekLabel)
                                .addGap(3, 3, 3))
                            .addComponent(lastNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(employeeNumberLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(firstNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(weeksPerYearLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hourlyWageLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deductionRateLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deductionRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(hoursPerWeekField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(minimizeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(employeeEditorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(weeksPerYearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addGap(1, 1, 1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(employeeEditorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(exitButton)
                                .addComponent(minimizeButton)))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workLocationLabel)
                    .addComponent(workLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourlyWageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hourlyWageLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hoursPerWeekLabel)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hoursPerWeekField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weeksPerYearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weeksPerYearLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deductionRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deductionRateLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    /**
     * Validate if the entries into each of the fields of the editor are
     * possible to put into the HashTable and are accepted values.
     *
     * @param firstName
     * @param lastName
     * @param workLocation
     * @param employeeNumber
     * @param hourlyWage
     * @param hoursPerWeek
     * @param weeksPerYear
     * @return boolean signifying if the entered params are valid or not.
     */
    private boolean validate(String firstName, String lastName, String workLocation, String employeeNumber, String hourlyWage, String hoursPerWeek, String weeksPerYear, String deductionRate) {
        int employeeNumParsed;
        try {
            employeeNumParsed = Integer.parseInt(employeeNumber);
            if (employeeNumParsed <= 0) {
                errorLabel.setText("Invalid employee number.");
                return false;
            }
        } catch (Exception e) {
            errorLabel.setText("Invalid employee number.");
            return false;
        }
        double deductionRateDouble;
        try {
            deductionRateDouble = Double.parseDouble(deductionRate);
            if (deductionRateDouble < 0 || deductionRateDouble > 100) {
                errorLabel.setText("Invalid deduction rate.");
                return false;
            }

        } catch (Exception e) {
            errorLabel.setText("Invalid deduction rate.");
            return false;
        }
        if (firstName.isEmpty() || EMSMain.regexNameValidate(firstName)) {
            errorLabel.setText("Invalid first name.");
            return false;
        }
        if (lastName.isEmpty() || EMSMain.regexNameValidate(lastName)) {
            errorLabel.setText("Invalid last name.");
            return false;
        }
        if (workLocation.isEmpty()) {
            errorLabel.setText("Invalid work location.");
            return false;
        }

        try {
            double hourlyWageParsed = Double.parseDouble(hourlyWage);
            if (hourlyWageParsed <= 0) {
                errorLabel.setText("Invalid hourly wage.");
                return false;
            }
        } catch (Exception e) {
            errorLabel.setText("Invalid hourly wage.");
            return false;
        }
        try {
            double hoursPerWeekParsed = Double.parseDouble(hoursPerWeek);
            if (hoursPerWeekParsed <= 0) {
                errorLabel.setText("Invalid hours per week.");
                return false;
            }
            if (hoursPerWeekParsed > 168) {
                errorLabel.setText("There are max 168 hours/week.");
                return false;
            }

        } catch (Exception e) {
            errorLabel.setText("Invalid hours per week.");
            return false;
        }
        try {
            double weeksPerYearParsed = Double.parseDouble(weeksPerYear);
            if (weeksPerYearParsed <= 0) {
                errorLabel.setText("Invalid weeks per year.");
                return false;
            }
            //Maximum weeks per year.
            if (weeksPerYearParsed > 52.14) {
                errorLabel.setText("Maximum weeks/year is 52.");
                return false;
            }
        } catch (Exception e) {
            errorLabel.setText("Invalid weeks per year.");
            return false;
        }

        if (EMSMain.getEmployeeTable().toArray().contains(EMSMain.getEmployeeTable().get(employeeNumParsed))) {
            errorLabel.setText("Employee number already exists");
            return false;

        }
        return true;
    }
    private void hourlyWageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourlyWageFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourlyWageFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addButton.setSelected(false);
        if (validate(firstNameField.getText(), lastNameField.getText(), workLocationField.getText(), employeeNumberField.getText(), hourlyWageField.getText(), hoursPerWeekField.getText(), weeksPerYearField.getText(), deductionRateField.getText())) {
            int sex = 0;
            if (maleButton.isSelected()) {
                sex = 0;
            } else {
                sex = 1;
            }
            PartTimeEmployee e = new PartTimeEmployee(firstNameField.getText(), lastNameField.getText(), Integer.parseInt(employeeNumberField.getText()), sex, workLocationField.getText(), Double.parseDouble(hourlyWageField.getText()), Double.parseDouble(hoursPerWeekField.getText()), Double.parseDouble(weeksPerYearField.getText()), Double.parseDouble(deductionRateField.getText()));
            EMSMain.getEmployeeTable().addToTable(e);
            Employee.serialize();
            EMSMain.getEmployeeTable().populateJFrameTable(employeeJTable);
            MainMenu.activeEditor = false;
            EMSMain.logger.info("The employee " + e.getFirstName() + " " + e.getLastName() + " has been added into the system.");
            this.dispose();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void hoursPerWeekFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoursPerWeekFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoursPerWeekFieldActionPerformed

    private void weeksPerYearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weeksPerYearFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weeksPerYearFieldActionPerformed

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        MainMenu.activeEditor = false;
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void deductionRateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionRateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionRateFieldActionPerformed

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
            java.util.logging.Logger.getLogger(PTEAdditionEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PTEAdditionEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PTEAdditionEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PTEAdditionEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PTEAdditionEditor(employeeJTable).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addButton;
    private javax.swing.JTextField deductionRateField;
    private javax.swing.JLabel deductionRateLabel;
    private javax.swing.JLabel employeeEditorLabel;
    private javax.swing.JTextField employeeNumberField;
    private javax.swing.JLabel employeeNumberLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton exitButton;
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
    private javax.swing.JButton minimizeButton;
    private javax.swing.JLabel sexLabel;
    private javax.swing.JTextField weeksPerYearField;
    private javax.swing.JLabel weeksPerYearLabel;
    private javax.swing.JTextField workLocationField;
    private javax.swing.JLabel workLocationLabel;
    // End of variables declaration//GEN-END:variables
}
