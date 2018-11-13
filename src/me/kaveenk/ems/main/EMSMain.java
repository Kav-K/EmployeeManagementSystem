/** MIT License
 *
 * Copyright (c) 2018 Kaveen Kumarasinghe
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * “Commons Clause” License Condition v1.0
 * The Software is provided to you by the Licensor under the License, as defined below, subject to the following condition.
 * Without limiting other conditions in the License, the grant of rights under the License will not include, and the License does not grant to you, the right to Sell the Software.
 * For purposes of the foregoing, “Sell” means practicing any or all of the rights granted to you under the License to provide to third parties, for a fee or other consideration (including without limitation fees for hosting or consulting/ support services related to the Software), a product or service whose value derives, entirely or substantially, from the functionality of the Software. Any license notice or attribution required by the License must also include this Commons Cause License Condition notice.
 *
 * Software: EmployeeManagementSystem
 *
 * License: MIT License
 *
 * Licensor: Kaveen Kumarasinghe
 */
package me.kaveenk.ems.main;

import me.kaveenk.ems.types.Employee;
import me.kaveenk.ems.utils.HashTable;
import me.kaveenk.ems.utils.LogHandler;
import me.kaveenk.ems.utils.CryptographyUtil;
import me.kaveenk.ems.utils.RedundantSavingTask;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import me.kaveenk.ems.gui.MainMenu;
import me.kaveenk.ems.types.FullTimeEmployee;
import me.kaveenk.ems.utils.FileLockUtil;

/**
 *
 * @author Kaveen Kumarasinghe
 *
 * This is the EmployeeManagementSystem project for ICS4UO - Mr. Dutton (Fall
 * 2018)
 *
 * A Github repository and timeline for this project is available at
 * https://github.com/Kav-K/EmployeeManagementSystem
 *
 * A manual and javadoc repository is available at
 * https://kaveenk.me/ics4uo/emshelp.
 *
 * The password for purposes of demonstration is currently "securepassword123".
 * To change the password, a new SHA-512 hash must be generated with the desired
 * password and put into the VALID_PASSWORD field.
 *
 *
 * TODO: Resource packaging within the JAR file itself.
 *
 */
public class EMSMain extends javax.swing.JFrame {

    private static HashTable employeeTable;
    private static final int NUM_BUCKETS = 5;
    private static final String VALID_PASSWORD = "dda69783f28fdf6f1c5a83e8400f2472e9300887d1dffffe12a07b92a3d0aa25";
    private static final String LOG_FILE = "log.txt";
    private static final int AUTO_SAVE_INTERVAL = 30;

    public static LogHandler logger = new LogHandler(LOG_FILE);

    private TimerTask saveTask;
    private static Point mouseDownCompCoords = null;

    /**
     * Creates the initial login JFrame.
     */
    public EMSMain() {
        //Check if the program is already active
        FileLockUtil fLockUtil = new FileLockUtil("EmployeeManagementSystem");
        if (fLockUtil.isAppAlreadyRunning()) {
            JOptionPane.showMessageDialog(
                    ((Supplier<JDialog>) () -> {
                        final JDialog dialog = new JDialog();
                        dialog.setAlwaysOnTop(true);
                        return dialog;
                    }).get(),
                    "There is already an instance of EMS running. Please close all other instances before starting a new one.");
            System.exit(0);

        }

        this.setResizable(false);
        this.setUndecorated(true);

        //Load Data
        new Employee(this);
        employeeTable = new HashTable(NUM_BUCKETS);
        Employee.load(Employee.SERIAL_FILE);
        //End load data

        //Extra styles
        initComponents();
        stylizeLabels();
        stylizeButtons();
        setBackgroundLabel();
        center();
        reDraw();
        //End extra styles

        initMouseListener();
        initFieldListener();
        passwordField.requestFocus();

        startRedundantSaving();
        logger.info("Program successfully initialized.");

    }

    /**
     * Start the redundant saving task as defined in RedundantSavingTask.java
     */
    private void startRedundantSaving() {
        saveTask = new RedundantSavingTask(AUTO_SAVE_INTERVAL);
        //Running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(saveTask, 0, AUTO_SAVE_INTERVAL * 1000);

    }

    /**
     * Initialize a field listener for the passwordField in order to allow login
     * by pressing the enter key.
     */
    private void initFieldListener() {
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validatePassword();
            }
        };

        passwordField.addActionListener(action);
    }

    /**
     * Make the undecorated JFrame draggable.
     */
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

    private void stylizeButtons() {
        exitButton.setBackground(new Color(1, 1, 1));
        exitButton.setForeground(new Color(230, 230, 230));
        minimizeButton.setBackground(new Color(1, 1, 1));
        minimizeButton.setForeground(new Color(230, 230, 230));

    }

    private void stylizeLabels() {
        titleLabel.setForeground(new Color(230, 230, 230));

        loginButton.setBackground(new Color(17, 17, 17));
        loginButton.setForeground(new Color(230, 230, 230));

        passwordField.setForeground(new Color(230, 230, 230));
        passwordField.setBackground(new Color(17, 17, 17));
    }

    private void reDraw() {
        this.revalidate();
        this.repaint();
    }

    /**
     * Validate the password entered by the user by converting it into an SHA
     * hash and comparing it against the VALID_PASSWORD field.
     */
    private void validatePassword() {
        if (passwordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You didn't enter a password.");
            return;
        }
        if (!(CryptographyUtil.hash(passwordField.getText()).equals(VALID_PASSWORD))) {
            JOptionPane.showMessageDialog(this, "Invalid Password.");
        } else {
            logger.info("User successfully authenticated.");
            new MainMenu().setVisible(true);
            this.setVisible(false);
        }
    }

    public static HashTable getEmployeeTable() {
        return employeeTable;
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

    /**
     * Check if there are any invalid characters input into the param text
     * (name)
     *
     * @param name The String to check for invalid characters against
     * @return Boolean signifying if the text is invalid or not.
     */
    public static boolean regexNameValidate(String name) {
        Pattern p = Pattern.compile("[^a-z-0-9- ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        boolean b = m.find();
        return b;

    }

    /**
     * Center the JFrame to the monitors middle.
     */
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

        titleLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("emsMain"); // NOI18N

        titleLabel.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        titleLabel.setText("Employee Management System");

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        exitButton.setText("X");
        exitButton.setToolTipText("");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        minimizeButton.setText("-");
        minimizeButton.setToolTipText("");
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
                .addGap(119, 119, 119)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(minimizeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(49, 49, 49))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(minimizeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginButton)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        validatePassword();

    }//GEN-LAST:event_loginButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        saveTask.cancel();
        Employee.serialize();
        Employee.serialize(Employee.BACKUP_SERIAL_FILE);
        logger.info("System gracefully exiting");
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
            java.util.logging.Logger.getLogger(EMSMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EMSMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EMSMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EMSMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new EMSMain().setVisible(true);
            }
        });

    }

    public static void resetDeclaredTable() {
        employeeTable = new HashTable(EMSMain.NUM_BUCKETS);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
