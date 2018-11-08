package me.kaveenk.ems.gui;

import me.kaveenk.ems.main.EMSMain;
import me.kaveenk.ems.types.Employee;
import me.kaveenk.ems.types.FullTimeEmployee;
import me.kaveenk.ems.types.PartTimeEmployee;
import me.kaveenk.ems.utils.HashTable;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static me.kaveenk.ems.main.EMSMain.employeeTable;
import static me.kaveenk.ems.main.EMSMain.logger;

/**
 *
 * @author Kaveen Kumarasinghe
 *
 * The main menu displayed after a user logs into the system.
 */
public class MainMenu extends javax.swing.JFrame {

    public static boolean activeEditor = false;
    public static boolean activeHelp = false;
    private static boolean searchEnabled = true;
    static Point mouseDownCompCoords = null;

    /**
     * Creates new JFrame MainMenu
     */
    public MainMenu() {

        this.setUndecorated(true);
        this.setResizable(false);
        //Extra styling
        initComponents();
        stylizeLabels();
        stylizeButtons();
        stylizeFields();
        setBackgroundLabel();
        center();
        reDraw();
        //End extra styling

        EMSMain.employeeTable.populateJFrameTable(employeeJTable);

        initMouseListener();
        initSearchFieldListener();
        employeeJTable.setAutoCreateRowSorter(true);

    }

    /**
     * Initialize the listener that allows search functionality when pressing
     * the enter key.
     */
    private void initSearchFieldListener() {

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchEnabled) {
                    search(searchField.getText());
                }
            }
        };

        searchField.addActionListener(action);

    }

    private void reDraw() {
        this.revalidate();
        this.repaint();
    }

    /**
     * Mouse listener to allow the window to be draggable even though it is
     * undecorated and unresizable.
     *
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

    private void stylizeLabels() {
        mainLabel.setForeground(new Color(230, 230, 230));

    }

    private void stylizeButtons() {
        addButton.setBackground(new Color(17, 17, 17));
        viewEditButton.setBackground(new Color(17, 17, 17));
        addButton.setForeground(new Color(230, 230, 230));
        viewEditButton.setForeground(new Color(230, 230, 230));

        exportButton.setBackground(new Color(17, 17, 17));
        exportButton.setForeground(new Color(230, 230, 230));

        searchButton.setBackground(new Color(17, 17, 17));
        searchButton.setForeground(new Color(230, 230, 230));

        exitButton.setBackground(new Color(1, 1, 1));
        minimizeButton.setBackground(new Color(1, 1, 1));
        exitButton.setForeground(Color.WHITE);
        minimizeButton.setForeground(Color.WHITE);

        importButton.setBackground(new Color(17, 17, 17));
        importButton.setForeground(new Color(230, 230, 230));

    }

    private void stylizeFields() {
        searchField.setBackground(new Color(17, 17, 17));
        searchField.setForeground(new Color(230, 230, 230));
    }

    /**
     * Obtain background label from the resources folder and set it as the
     * background overlay of the JFrame by spoofing it as a JLabel.
     */
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
        addButton = new javax.swing.JButton();
        tableScrollPane = new javax.swing.JScrollPane();
        employeeJTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        importButton = new javax.swing.JButton();

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

        addButton.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        employeeJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Employee #", "Status", "Gross Yearly Salary"
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

        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        exportButton.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        importButton.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        importButton.setText("Import");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewEditButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exportButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(importButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(helpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                .addComponent(minimizeButton)
                                .addComponent(helpButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchButton)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewEditButton)
                            .addComponent(addButton)
                            .addComponent(exportButton)
                            .addComponent(importButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    /**
     * Start a new instance of an editor panel.
     *
     * @param employee The employee that is about to be edited.
     */
    private void initializeEditor(Employee employee) {
        if (employee instanceof FullTimeEmployee) {
            new FTEEditor((FullTimeEmployee) employee, employeeJTable).setVisible(true);
            activeEditor = true;
        } else if (employee instanceof PartTimeEmployee) {
            new PTEEditor((PartTimeEmployee) employee, employeeJTable).setVisible(true);
            activeEditor = true;
        }

    }
    private void employeeJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeJTableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            JTable source = (JTable) evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            if (!activeEditor) {
                initializeEditor(EMSMain.employeeTable.get((int) employeeJTable.getValueAt(row, 2)));
            }

        }

    }//GEN-LAST:event_employeeJTableMouseClicked

    private void viewEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEditButtonActionPerformed
        JOptionPane.showMessageDialog(null, "To view or edit an employees details, simply double click their entry in the table to the right of the main page.");
    }//GEN-LAST:event_viewEditButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String[] options = new String[]{"Part Time", "Full Time"};
        int response = JOptionPane.showOptionDialog(null, "What type of employee would you like to add?", "Employee Addition",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {
            //Part Time
            new PTEAdditionEditor(employeeJTable).setVisible(true);
            activeEditor = true;

        } else if (response == 1) {
            new FTEAdditionEditor(employeeJTable).setVisible(true);
            activeEditor = true;
        }


    }//GEN-LAST:event_addButtonActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed
    /**
     * Search by employee number or by name.
     *
     * @param searchText The text entered in the searchField field.
     */
    private void search(String searchText) {
        boolean isNumber = false;
        try {
            Integer.parseInt(searchText);
            isNumber = true;
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            int searchNumber = Integer.parseInt(searchText);

            if (EMSMain.employeeTable.get(searchNumber) == null) {
                JOptionPane.showMessageDialog(null, "No results found!");
                return;
            }

            DefaultTableModel model = (DefaultTableModel) employeeJTable.getModel();
            model.setRowCount(0);
            Employee employee = EMSMain.employeeTable.get(searchNumber);
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
        ArrayList<Employee> resultList = EMSMain.employeeTable.getByLastName(searchText);

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
    }
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (searchEnabled) {
            search(searchField.getText());

        } else {
            searchField.setText("");
            searchEnabled = true;
            searchButton.setText("Search");
            EMSMain.employeeTable.populateJFrameTable(employeeJTable);

        }


    }//GEN-LAST:event_searchButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        Employee.serialize();
        Employee.serialize(Employee.BACKUP_SERIAL_FILE);
        logger.info("System gracefully exiting");

        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    /**
     * Opens a new JxBrowser instance when pressing the help button that leads
     * to the emshelp page that is set up on the web server at
     * https://kaveenk.me/ics4uo/emshelp.
     *
     * @param evt
     */
    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        if (activeHelp) {
            System.out.println(activeHelp);
            return;
        }

        try {
            activeHelp = true;
            Browser browser = new Browser();
            BrowserView view = new BrowserView(browser);

            JFrame frame = new JFrame("Employee Management System Help");
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JButton closeButton = new JButton("Close Manual");

            closeButton.setBackground(new Color(17, 17, 17));
            closeButton.setForeground(new Color(230, 230, 230));
            closeButton.addActionListener((e) -> {
                browser.dispose();
                frame.dispose();
                activeHelp = false;
            });

            JButton backButton = new JButton("Back");
            backButton.setBackground(new Color(17, 17, 17));
            backButton.setForeground(new Color(230, 230, 230));
            backButton.addActionListener((e) -> {
                if (!browser.getURL().equals("https://kaveenk.me/ics4uo/emshelp/")) {
                    browser.goBack();
                }

            });
            frame.add(backButton, BorderLayout.AFTER_LAST_LINE);

            frame.add(closeButton, BorderLayout.BEFORE_FIRST_LINE);

            frame.add(view, BorderLayout.CENTER);
            frame.setSize(500, 700);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setUndecorated(true);
            frame.setVisible(true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {

                    MainMenu.activeHelp = false;
                    System.out.println("falsed");
                    browser.dispose();
                    frame.dispose();
                    event.getWindow().dispose();
                }
            });

            browser.getCacheStorage().clearCache();
            browser.loadURL("https://kaveenk.me/ics4uo/emshelp/");
            browser.getCacheStorage().clearCache();

        } catch (Exception e) {
            logger.error("There was an error in opening the JxBrowser library's functionalities!", e);
            JOptionPane.showMessageDialog(null, "Something went wrong with JxBrowser's license.");
        }
    }//GEN-LAST:event_helpButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv", "csv");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose Export Location");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            export(chooser.getSelectedFile().getAbsolutePath());

        } else {
            //No Selection, silence!

        }

    }//GEN-LAST:event_exportButtonActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv", "csv");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose Import File");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            importFromCSV(chooser.getSelectedFile().getAbsolutePath());

        } else {
            //No Selection, silence!

        }
    }//GEN-LAST:event_importButtonActionPerformed

    /**
     * Export the employeeTable hashtable to a file in CSV format (spreadsheet)
     *
     * @param exportFile the absolute path of the file to be output to
     */
    private void export(String exportFile) {
        if (!exportFile.toLowerCase().endsWith(".csv")) {
            exportFile = exportFile + ".csv";
        }
        logger.info("Exporting data to " + exportFile);
        try {
            if (new File(exportFile).exists()) {
                if (JOptionPane.showConfirmDialog(null, "File already exists, would you like to overwrite it?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    //Just continue
                } else {
                    return;
                }
            }
            PrintWriter pw = new PrintWriter(new File(exportFile));
            StringBuilder sb = new StringBuilder();
            sb.append("Type");
            sb.append(',');
            sb.append("First Name");
            sb.append(',');
            sb.append("Last Name");
            sb.append(',');
            sb.append("Employee Number");
            sb.append(',');
            sb.append("Work Location");
            sb.append(',');
            sb.append("Sex");
            sb.append(',');
            sb.append("Deduction Rate");
            sb.append(',');
            sb.append("Yearly Salary");
            sb.append(',');
            sb.append("Hourly Wage");
            sb.append(',');
            sb.append("Hours Per Week");
            sb.append(',');
            sb.append("Weeks Per Year");
            sb.append('\n');

            for (Employee e : employeeTable.toArray()) {
                if (e instanceof FullTimeEmployee) {
                    FullTimeEmployee e2 = (FullTimeEmployee) e;

                    sb.append("Full Time");
                    sb.append(",");
                    sb.append(e2.getFirstName());
                    sb.append(",");
                    sb.append(e2.getLastName());
                    sb.append(",");
                    sb.append(e2.getEmployeeNumber());
                    sb.append(",");
                    sb.append(e2.getWorkLocation());
                    sb.append(",");
                    if (e.getSex() == 0) {
                        sb.append("Male");
                    } else {
                        sb.append("Female");
                    }
                    sb.append(",");
                    sb.append(e2.getDeductionRate());
                    sb.append(",");
                    sb.append(e2.getYearlySalary());
                    sb.append(",");
                    sb.append("N/A");
                    sb.append(",");
                    sb.append("N/A");
                    sb.append(",");
                    sb.append("N/A");
                    sb.append("\n");
                } else {
                    PartTimeEmployee e2 = (PartTimeEmployee) e;
                    sb.append("Part Time");
                    sb.append(",");
                    sb.append(e2.getFirstName());
                    sb.append(",");
                    sb.append(e2.getLastName());
                    sb.append(",");
                    sb.append(e2.getEmployeeNumber());
                    sb.append(",");
                    sb.append(e2.getWorkLocation());
                    sb.append(",");
                    if (e.getSex() == 0) {
                        sb.append("Male");
                    } else {
                        sb.append("Female");
                    }
                    sb.append(",");
                    sb.append(e2.getDeductionRate());
                    sb.append(",");
                    sb.append(e2.getHourlyWage() * e2.getHoursPerWeek() * e2.getWeeksPerYear());
                    sb.append(",");
                    sb.append(e2.getHourlyWage());
                    sb.append(",");
                    sb.append(e2.getHoursPerWeek());
                    sb.append(",");
                    sb.append(e2.getWeeksPerYear());
                    sb.append("\n");
                }
            }

            pw.write(sb.toString());
            pw.close();
            logger.info("Successfuly exported data");
            JOptionPane.showMessageDialog(null, "Successfully exported file to: " + exportFile);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There was an error while exporting! Check your filesystem permissions and choose another directory.");
            logger.error("There was an error exporting data", e);
        }
    }

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
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTable employeeJTable;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JButton viewEditButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Import a comma separated values file to use in the
     * employeemanagementsystem
     *
     * @param absolutePath The path to the file being imported.
     */
    private void importFromCSV(String absolutePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            String line = "nothing";
            ArrayList<Employee> importArray = new ArrayList<Employee>();
            int counter = 0;
            while ((line = br.readLine()) != null) {
                if (counter == 0) {
                    counter++;
                    continue;
                }

                // use comma as separator
                String[] employeeUnparsed = line.split(",");
                String firstName = employeeUnparsed[1];
                String lastName = employeeUnparsed[2];
                int employeeNumber = Integer.parseInt(employeeUnparsed[3]);
                String workLocation = employeeUnparsed[4];
                int sex = 0;
                String sexString = employeeUnparsed[5];
                if (sexString.toLowerCase().contains("male")) {
                    sex = 0;
                } else {
                    sex = 1;
                }
                double deductionRate = Double.parseDouble(employeeUnparsed[6]);

                if (line.toLowerCase().contains("n/a")) {
                    double yearlySalary = Double.parseDouble(employeeUnparsed[7]);
                    FullTimeEmployee addition = new FullTimeEmployee(firstName, lastName, employeeNumber, sex, workLocation, yearlySalary, deductionRate);
                    importArray.add(addition);
                } else {
                    double hourlyWage = Double.parseDouble(employeeUnparsed[8]);
                    double hoursPerWeek = Double.parseDouble(employeeUnparsed[9]);
                    double weeksPerYear = Double.parseDouble(employeeUnparsed[10]);
                    PartTimeEmployee addition = new PartTimeEmployee(firstName, lastName, employeeNumber, sex, workLocation, hourlyWage, hoursPerWeek, weeksPerYear, deductionRate);
                    importArray.add(addition);
                }

            }

            JOptionPane.showMessageDialog(null, "Please choose where to back up your old data");
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv", "csv");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choose Old Data Backup Location");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                export(chooser.getSelectedFile().getAbsolutePath());

            } else {
                return;

            }

            EMSMain.employeeTable = new HashTable(EMSMain.NUM_BUCKETS);
            importArray.forEach((employee) -> {
                employeeTable.addToTable(employee);
            });
            JOptionPane.showMessageDialog(null, "Successfully imported.");
            EMSMain.employeeTable.populateJFrameTable(employeeJTable);
            Employee.serialize();

        } catch (Exception e) {
            logger.error("There was an error importing data.", e);
            JOptionPane.showMessageDialog(null, "There was an error importing data! Please check that your CSV file is in the proper format.");
        }
    }
}
