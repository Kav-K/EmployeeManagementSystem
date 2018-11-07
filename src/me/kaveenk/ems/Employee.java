package me.kaveenk.ems;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Employee implements Serializable {

    private static int loadFailures = 0;
    private static final long serialVersionUID = -2722972583608119832L;
    public static final String SERIAL_FILE = "employeeData.ser";
    public static final String BACKUP_SERIAL_FILE = ".employeeData.ser";
    private String firstName;
    private String lastName;
    private int employeeNum;
    private int sex;
    private String workLocation;

    public static EMSMain mainInstance;

    /**
     * Overload to pass an instance of the main class to Employee.java
     *
     * @param instance Main class instance
     */
    public Employee(EMSMain instance) {
        this.mainInstance = instance;
    }

    /**
     * Create new Employee object
     *
     * @param firstName
     * @param lastName
     * @param employeeNum
     * @param sex
     * @param workLocation
     */
    public Employee(String firstName, String lastName, int employeeNum, int sex, String workLocation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNum = employeeNum;
        this.sex = sex;
        this.workLocation = workLocation;
    }

    public String getFirstName() {
        return this.firstName;

    }

    public String getLastName() {

        return this.lastName;
    }

    public int getEmployeeNumber() {
        return this.employeeNum;
    }

    public int getSex() {
        return this.sex;
    }

    public String getWorkLocation() {
        return this.workLocation;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNum = employeeNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * Serializes the HashTable by obtaining a single ArrayList(Employee) object
     * and writing it to a file using an ObjectOutputStream.
     */
    public static void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream(SERIAL_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(mainInstance.employeeTable.toArray());
            out.close();
            fileOut.close();
            EMSMain.logger.info("EMS Data has been serialized to " + SERIAL_FILE);
        } catch (IOException i) {
            EMSMain.logger.error("There was an error during serialization", i);

        }
    }

    //Overload for serialize
    public static void serialize(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(mainInstance.employeeTable.toArray());
            out.close();
            fileOut.close();
            EMSMain.logger.info("EMS Data has been serialized to " + fileName);
        } catch (IOException i) {
            EMSMain.logger.error("There was an error during serialization", i);
        }
    }

    /**
     * Loads the serialized data using an ObjectInputStream as an
     * ArrayList(Employee), then iterates through that arraylist and adds it to
     * the HashTable.
     */
    public static void load(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);

            ObjectInputStream in = new ObjectInputStream(fileIn);

            ArrayList<Employee> eList = (ArrayList<Employee>) in.readObject();
            in.close();
            fileIn.close();

            for (Employee e : eList) {

                if (e instanceof PartTimeEmployee) {
                    PartTimeEmployee addition = (PartTimeEmployee) e;
                    mainInstance.employeeTable.addToTable(addition);
                } else if (e instanceof FullTimeEmployee) {
                    FullTimeEmployee addition = (FullTimeEmployee) e;
                    mainInstance.employeeTable.addToTable(addition);
                } else {
                    Employee addition = e;
                    mainInstance.employeeTable.addToTable(addition);
                }
                continue;

            }
            Employee.serialize();

        } catch (IOException i) {
            if (loadFailures == 0) {
                loadFailures++;
                EMSMain.logger.severe("There was an error loading the main serial file, trying the backup.");
                load(Employee.BACKUP_SERIAL_FILE);

            } else if (loadFailures > 0) {
                JOptionPane.showMessageDialog(null, "A valid serial file could not be loaded. Initializing from empty..");
                EMSMain.logger.severe("A valid serial file was not loaded so an empty one has been created from scratch.");
                Employee.serialize();
                return;
            }
            return;
        } catch (ClassNotFoundException c) {
            EMSMain.logger.error("There was an error finding the Employee class for deserialization", c);
            return;
        }
    }

}
