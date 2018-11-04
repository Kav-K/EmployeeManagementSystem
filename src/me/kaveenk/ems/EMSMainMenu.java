/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kaveen
 */
public class EMSMainMenu extends javax.swing.JFrame {

    public static boolean activeEditor = false;
    private static boolean searchEnabled = true;
    static Point mouseDownCompCoords = null;

    /**
     * Creates new form EMSMainMenu
     */
    public EMSMainMenu() {
        this.setUndecorated(true);
        this.setResizable(false);
        BufferedImage background = null;

        try {
            background = ImageIO.read(new File("resources/bg.jpg"));

        } catch (Exception e) {
            //Replace this later this is just for testing
            //TODO recovery
            System.out.println("fail!");
        }
        JLabel backgroundlabel = new JLabel(new ImageIcon(background));
        backgroundlabel.setBounds(0, 0, backgroundlabel.getPreferredSize().width, backgroundlabel.getPreferredSize().height);
        //Pane initialization
        initComponents();
        mainLabel.setForeground(Color.WHITE);
        this.getContentPane().add(backgroundlabel);
        this.revalidate();
        this.repaint();

        EMSMainAndLogin.employeeTable.populateJFrameTable(employeeJTable);
        center();

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

        tablePanel = new javax.swing.JPanel();
        mainLabel = new javax.swing.JLabel();
        viewEditButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tableScrollPane = new javax.swing.JScrollPane();
        employeeJTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
        );

        mainLabel.setBackground(new java.awt.Color(206, 226, 236));
        mainLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        mainLabel.setText("Employee Management System");

        viewEditButton.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        viewEditButton.setText("View/Edit");
        viewEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEditButtonActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        employeeJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Employee #", "Status", "Yearly Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeJTable.setColumnSelectionAllowed(true);
        employeeJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeJTableMouseClicked(evt);
            }
        });
        employeeJTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                employeeJTablePropertyChange(evt);
            }
        });
        tableScrollPane.setViewportView(employeeJTable);

        searchField.setToolTipText("Search using an employee number or by last name.");
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewEditButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainLabel))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(exitButton)
                                .addComponent(minimizeButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchButton)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewEditButton)
                            .addComponent(jButton2))
                        .addGap(2, 2, 2)
                        .addComponent(tableScrollPane))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 51, Short.MAX_VALUE)
                        .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeeJTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_employeeJTablePropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_employeeJTablePropertyChange
    private void initializeEditor(Employee employee) {
        if (employee instanceof FullTimeEmployee) {
            new EMSEditorFTE((FullTimeEmployee) employee, employeeJTable).setVisible(true);
            activeEditor = true;
        } else if (employee instanceof PartTimeEmployee) {
            new EMSEditorPTE((PartTimeEmployee) employee, employeeJTable).setVisible(true);
            activeEditor = true;
        }

    }
    private void employeeJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeJTableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            JTable source = (JTable) evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            if (!activeEditor) {
                initializeEditor(EMSMainAndLogin.employeeTable.toArray().get(row));
            }

        }

    }//GEN-LAST:event_employeeJTableMouseClicked

    private void viewEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEditButtonActionPerformed
        JOptionPane.showMessageDialog(null, "To view or edit an employees details, simply double click their entry in the table to the right of the main page.");
    }//GEN-LAST:event_viewEditButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String[] options = new String[]{"Part Time", "Full Time"};
        int response = JOptionPane.showOptionDialog(null, "What type of employee would you like to add?", "Employee Addition",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {
            //Part Time
            new EMSAdditionEditorPTE(employeeJTable).setVisible(true);
            activeEditor = true;

        } else if (response == 1) {
            new EMSAdditionEditorFTE(employeeJTable).setVisible(true);
            activeEditor = true;
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (searchEnabled) {
            String searchText = searchField.getText();

            boolean isNumber = false;
            try {
                Integer.parseInt(searchText);
                isNumber = true;
            } catch (Exception e) {
                isNumber = false;
            }

            if (isNumber) {
                int searchNumber = Integer.parseInt(searchText);

                if (EMSMainAndLogin.employeeTable.get(searchNumber) == null) {
                    JOptionPane.showMessageDialog(null, "No results found!");
                    return;
                }

                DefaultTableModel model = (DefaultTableModel) employeeJTable.getModel();
                model.setRowCount(0);
                Employee employee = EMSMainAndLogin.employeeTable.get(searchNumber);
                if (employee instanceof PartTimeEmployee) {
                    PartTimeEmployee e = (PartTimeEmployee) employee;
                    model.addRow(new Object[]{e.getFirstName(), e.getLastName(), e.getEmployeeNumber(), "Part Time", 123});
                } else if (employee instanceof FullTimeEmployee) {
                    FullTimeEmployee e = (FullTimeEmployee) employee;
                    model.addRow(new Object[]{e.getFirstName(), e.getLastName(), e.getEmployeeNumber(), "Full Time", e.getYearlySalary()});
                }

                searchEnabled = false;
                searchButton.setText("Reset Table");
                return;
            }

            //Last Name Search
            ArrayList<Employee> resultList = EMSMainAndLogin.employeeTable.getByLastName(searchText);

            if (resultList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No results found!");
                return;
            } else {
                searchEnabled = false;
                searchButton.setText("Reset Table");
                DefaultTableModel model = (DefaultTableModel) employeeJTable.getModel();
                model.setRowCount(0);
                for (Employee employee : resultList) {

                    if (employee instanceof PartTimeEmployee) {
                        PartTimeEmployee e = (PartTimeEmployee) employee;
                        model.addRow(new Object[]{e.getFirstName(), e.getLastName(), e.getEmployeeNumber(), "Part Time", 123});
                    } else if (employee instanceof FullTimeEmployee) {
                        FullTimeEmployee e = (FullTimeEmployee) employee;
                        model.addRow(new Object[]{e.getFirstName(), e.getLastName(), e.getEmployeeNumber(), "Full Time", e.getYearlySalary()});
                    }

                }

            }

        } else {
            searchField.setText("");
            searchEnabled = true;
            searchButton.setText("Search");
            EMSMainAndLogin.employeeTable.populateJFrameTable(employeeJTable);

        }


    }//GEN-LAST:event_searchButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        Employee.serialize();
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EMSMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EMSMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EMSMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EMSMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EMSMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable employeeJTable;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JButton viewEditButton;
    // End of variables declaration//GEN-END:variables
}
