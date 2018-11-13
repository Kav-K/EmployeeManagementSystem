/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems.gui;

import me.kaveenk.ems.main.EMSMain;
import me.kaveenk.ems.types.Employee;
import me.kaveenk.ems.types.FullTimeEmployee;
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
public class PTEEditor extends javax.swing.JFrame {
    private static Point mouseDownCompCoords;
    private static PartTimeEmployee employee_s;
    private PartTimeEmployee employee;
    private static JTable employeeJTable;

    /**
     * Creates new JFrame PTEEditor
     *
     * @param employee Employee object being edited
     * @param employeeJTable instance of the Main Menu JTable
     */
    public PTEEditor(PartTimeEmployee employee, JTable employeeJTable) {
        this.setUndecorated(true);
        this.setResizable(false);
        //Static employee instance
        this.employee_s = employee;
        //Non-static employee
        this.employee = employee;

        this.employeeJTable = employeeJTable;
        initComponents();

        setBackgroundLabel();
        stylizeLabels();
        stylizeButtons();
        stylizeFields();
        center();
        reDraw();

        lockFields();
        setFields();

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
        deductionRateField.setForeground(new Color(230, 230, 230));

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
        grossIncomeLabel.setForeground(new Color(230, 230, 230));

    }

    private void stylizeButtons() {
        editButton.setBackground(new Color(17, 17, 17));
        editButton.setForeground(new Color(230, 230, 230));
        deleteButton.setBackground(new Color(17, 17, 17));
        deleteButton.setForeground(new Color(230, 230, 230));
        saveButton.setBackground(new Color(17, 17, 17));
        saveButton.setForeground(new Color(230, 230, 230));
        revertButton.setBackground(new Color(17, 17, 17));
        revertButton.setForeground(new Color(230, 230, 230));
        makeFullTimeButton.setBackground(new Color(17, 17, 17));
        makeFullTimeButton.setForeground(new Color(230, 230, 230));

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
        deductionRateField.setEnabled(true);
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
        deductionRateField.setEnabled(false);
        grossIncomeField.setEnabled(false);

    }

    private void setFields() {
        firstNameField.setText(employee_s.getFirstName());
        lastNameField.setText(employee_s.getLastName());
        employeeNumberField.setText("" + employee_s.getEmployeeNumber());
        if (employee_s.getSex() == 0) {
            maleButton.setSelected(true);
        } else {
            femaleButton.setSelected(true);
        }
        workLocationField.setText(employee_s.getWorkLocation());
        hourlyWageField.setText("" + employee_s.getHourlyWage());
        hoursPerWeekField.setText("" + employee_s.getHoursPerWeek());
        weeksPerYearField.setText("" + employee_s.getWeeksPerYear());
        deductionRateField.setText("" + employee_s.getDeductionRate());
        calcAndSetGrossIncomeField();

        return;

    }

    private void calcAndSetGrossIncomeField() {
        double deduction = employee.getDeductionRate();
        double income = employee.getHourlyWage() * employee.getWeeksPerYear() * employee.getHoursPerWeek();
        double gross = income * (deduction / 100);
        if (deduction == 0) {
            grossIncomeField.setText("" + income);
            return;
        }
        grossIncomeField.setText("" + gross);

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

        jButton1 = new javax.swing.JButton();
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
        exitButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();
        deductionRateField = new javax.swing.JTextField();
        deductionRateLabel = new javax.swing.JLabel();
        makeFullTimeButton = new javax.swing.JButton();
        grossIncomeLabel = new javax.swing.JLabel();
        grossIncomeField = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        employeeEditorLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        employeeEditorLabel.setText("Employee Viewer (Part Time)");

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
        workLocationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workLocationFieldActionPerformed(evt);
            }
        });

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

        deductionRateField.setText("jTextField6");
        deductionRateField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        deductionRateField.setPreferredSize(new java.awt.Dimension(177, 33));
        deductionRateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionRateFieldActionPerformed(evt);
            }
        });

        deductionRateLabel.setText("Deduction Rate(%):");

        makeFullTimeButton.setText("Make Employee Full-Time");
        makeFullTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeFullTimeButtonActionPerformed(evt);
            }
        });

        grossIncomeLabel.setText("Gross Income:");

        grossIncomeField.setText("jTextField6");
        grossIncomeField.setDisabledTextColor(new java.awt.Color(62, 62, 62));
        grossIncomeField.setPreferredSize(new java.awt.Dimension(177, 33));
        grossIncomeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grossIncomeFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(workLocationLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(hoursPerWeekLabel)
                                .addComponent(hourlyWageLabel))
                            .addComponent(lastNameLabel)
                            .addComponent(employeeNumberLabel)
                            .addComponent(firstNameLabel)
                            .addComponent(weeksPerYearLabel)
                            .addComponent(deductionRateLabel)
                            .addComponent(sexLabel)
                            .addComponent(grossIncomeLabel))
                        .addGap(4, 4, 4)
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
                            .addComponent(weeksPerYearField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deductionRateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(grossIncomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                    .addComponent(makeFullTimeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(employeeEditorLabel)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(editButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(deleteButton)
                                            .addGap(28, 28, 28))
                                        .addComponent(errorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(revertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saveButton)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minimizeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(workLocationLabel)
                            .addComponent(workLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hourlyWageLabel)
                            .addComponent(hourlyWageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoursPerWeekLabel)
                            .addComponent(hoursPerWeekField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(weeksPerYearLabel)
                            .addComponent(weeksPerYearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exitButton)
                            .addComponent(minimizeButton))
                        .addGap(32, 32, 32)
                        .addComponent(employeeEditorLabel)
                        .addGap(18, 18, 18)
                        .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editButton)
                            .addComponent(deleteButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(revertButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deductionRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deductionRateLabel))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grossIncomeLabel)
                    .addComponent(grossIncomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(makeFullTimeButton)
                .addGap(22, 22, 22))
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

        if ((employeeNumParsed != employee_s.getEmployeeNumber()) && EMSMain.getEmployeeTable().toArray().contains(EMSMain.getEmployeeTable().get(employeeNumParsed))) {
            errorLabel.setText("Employee number already exists");
            return false;

        }
        return true;
    }
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (validate(firstNameField.getText(), lastNameField.getText(), workLocationField.getText(), employeeNumberField.getText(), hourlyWageField.getText(), hoursPerWeekField.getText(), weeksPerYearField.getText(), deductionRateField.getText())) {

            employee_s.setEmployeeNumber(Integer.parseInt(employeeNumberField.getText()));
            employee_s.setFirstName(firstNameField.getText());
            employee_s.setLastName(lastNameField.getText());
            if (maleButton.isSelected()) {
                employee_s.setSex(0);
            } else {
                employee_s.setSex(1);
            }
            employee_s.setWorkLocation(workLocationField.getText());
            employee_s.setHourlyWage(Double.parseDouble(hourlyWageField.getText()));
            employee_s.setHoursPerWeek(Double.parseDouble(hoursPerWeekField.getText()));
            employee_s.setWeeksPerYear(Double.parseDouble(weeksPerYearField.getText()));
            employee_s.setDeductionRate(Double.parseDouble(deductionRateField.getText()));
            Employee.serialize();
            //this.setVisible(false);
            EMSMain.getEmployeeTable().populateJFrameTable(this.employeeJTable);
            editButton.setEnabled(true);
            editButton.setSelected(false);
            saveButton.setVisible(false);
            revertButton.setVisible(false);
            deleteButton.setEnabled(true);
            makeFullTimeButton.setVisible(true);

            lockFields();
            setFields();
            EMSMain.logger.info("The employee " + employee_s.getFirstName() + " " + employee_s.getLastName() + " has been edited.");

        }


    }//GEN-LAST:event_saveButtonActionPerformed

    private void hourlyWageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourlyWageFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourlyWageFieldActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        EMSMain.getEmployeeTable().remove(employee_s.getEmployeeNumber());
        Employee.serialize();
        EMSMain.getEmployeeTable().populateJFrameTable(employeeJTable);
        MainMenu.activeEditor = false;
        this.dispose();


    }//GEN-LAST:event_deleteButtonActionPerformed

    private void revertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revertButtonActionPerformed
        setFields();
        lockFields();
        editButton.setEnabled(true);
        editButton.setSelected(false);
        deleteButton.setEnabled(true);
        makeFullTimeButton.setVisible(true);

    }//GEN-LAST:event_revertButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        editButton.setSelected(true);
        editButton.setEnabled(false);
        unlockFields();
        saveButton.setVisible(true);
        revertButton.setVisible(true);
        makeFullTimeButton.setVisible(false);
    }//GEN-LAST:event_editButtonActionPerformed

    private void hoursPerWeekFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoursPerWeekFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoursPerWeekFieldActionPerformed

    private void weeksPerYearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weeksPerYearFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weeksPerYearFieldActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        MainMenu.activeEditor = false;
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void deductionRateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionRateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionRateFieldActionPerformed

    private void workLocationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workLocationFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_workLocationFieldActionPerformed

    private void makeFullTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeFullTimeButtonActionPerformed
        //Remove from the hashtable and convert into a full time employee_s.ec
        int sex = 0;
        if (femaleButton.isSelected()) {
            sex = 1;
        }

        FullTimeEmployee employee = new FullTimeEmployee(firstNameField.getText(), lastNameField.getText(), Integer.parseInt(employeeNumberField.getText()), sex, workLocationField.getText(), Double.parseDouble(hourlyWageField.getText()) * Double.parseDouble(hoursPerWeekField.getText()) * Double.parseDouble(weeksPerYearField.getText()), Double.parseDouble(deductionRateField.getText()));
        EMSMain.getEmployeeTable().remove(employee.getEmployeeNumber());
        EMSMain.getEmployeeTable().addToTable(employee);
        new FTEEditor(employee, employeeJTable).setVisible(true);
        this.dispose();
        EMSMain.getEmployeeTable().populateJFrameTable(employeeJTable);

    }//GEN-LAST:event_makeFullTimeButtonActionPerformed

    private void grossIncomeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grossIncomeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grossIncomeFieldActionPerformed

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
            java.util.logging.Logger.getLogger(PTEEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PTEEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PTEEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PTEEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PTEEditor(employee_s, employeeJTable).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField deductionRateField;
    private javax.swing.JLabel deductionRateLabel;
    private javax.swing.JToggleButton deleteButton;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JLabel employeeEditorLabel;
    private javax.swing.JTextField employeeNumberField;
    private javax.swing.JLabel employeeNumberLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JRadioButton femaleButton;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField grossIncomeField;
    private javax.swing.JLabel grossIncomeLabel;
    private javax.swing.JTextField hourlyWageField;
    private javax.swing.JLabel hourlyWageLabel;
    private javax.swing.JTextField hoursPerWeekField;
    private javax.swing.JLabel hoursPerWeekLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JButton makeFullTimeButton;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JButton revertButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel sexLabel;
    private javax.swing.JTextField weeksPerYearField;
    private javax.swing.JLabel weeksPerYearLabel;
    private javax.swing.JTextField workLocationField;
    private javax.swing.JLabel workLocationLabel;
    // End of variables declaration//GEN-END:variables
}
