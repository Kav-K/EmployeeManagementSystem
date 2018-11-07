package me.kaveenk.ems;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HashTable {

    private ArrayList<Employee>[] buckets;
    private int length;

    /**
     * @author Kaveen Kumarasinghe
     * @version 1.0
     *
     * HashTable implementation for ICS4UO - Mr. Dutton
     *
     */
    public HashTable(int howManyBuckets) {
        buckets = new ArrayList[howManyBuckets];
        length = howManyBuckets;

        for (int i = 0; i < howManyBuckets; i++) {
            buckets[i] = new ArrayList<Employee>();

        }

    }

    /**
     * Adds an employee to a bucket
     *
     * @param addition Employee variable to add to the table
     */
    public void addToTable(Employee addition) {
        int toPlace = addition.getEmployeeNumber() % length;

        for (int i = 0; i < length; i++) {
            if (!(i == toPlace)) {
                continue;
            }
            buckets[i].add(addition);
            break;

        }

    }

    /**
     * Searches the HashTable for an Employee object containing the param
     * lastName
     *
     * @param lastName String last name to search
     * @return ArrayList(Employee) object
     */
    public ArrayList<Employee> getByLastName(String lastName) {
        ArrayList<Employee> resultList = new ArrayList<Employee>();
        for (int i = 0; i < length; i++) {
            if (buckets[i].isEmpty()) {
                continue;
            }

            for (Employee employee : buckets[i]) {
                if (employee.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                    resultList.add(employee);
                }
            }
        }
        return resultList;
    }

    /**
     * Get an Employee object by employeeNumber
     *
     * @param employeeNumber integer employeeNumber to get the object by
     * @return Employee object
     */
    public Employee get(int employeeNumber) {
        int toRetrieve = employeeNumber % length;
        for (int i = 0; i < length; i++) {
            if (!(i == toRetrieve)) {
                continue;
            }

            for (Employee info : buckets[i]) {
                if (info.getEmployeeNumber() == employeeNumber) {
                    return info;
                }
            }

        }
        return null;

    }

    /**
     * Remove an Employee object by employeeNumber
     *
     * @param employeeNumber integer employee number associated with the object
     * you wish to remove
     */
    public void remove(int employeeNumber) {
        int toRemove = employeeNumber % length;
        for (int i = 0; i < length; i++) {
            if (!(i == toRemove)) {
                continue;
            }
            for (Employee info : buckets[i]) {
                if (info.getEmployeeNumber() == employeeNumber) {
                    buckets[i].remove(info);
                    EMSMain.logger.warning("The employee " + info.getFirstName() + " " + info.getLastName() + " has been removed from the system.");
                    return;

                }

            }

        }

    }

    /**
     * Turn the hashtable into a single ArrayList(Employee) object for ease of
     * serialization
     *
     * @return ArrayList(Employee) object
     */
    public ArrayList<Employee> toArray() {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        for (int i = 0; i < length; i++) {

            if (buckets[i].isEmpty()) {
                continue;
            }

            for (int k = 0; k < buckets[i].size(); k++) {
                employeeList.add(buckets[i].get(k));

            }

        }
        return employeeList;

    }

    /**
     * Iterate through the HashTable, populating the Main Menu's JTable upon
     * each iteration.
     *
     * @param table JTable to populate
     */
    public void populateJFrameTable(JTable table) {
        EMSMain.logger.info("Preparing to populate JTable");
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = 0; i < length; i++) {

            if (buckets[i].isEmpty()) {

                continue;
            }

            for (int k = 0; k < buckets[i].size(); k++) {
                if (buckets[i].get(k) instanceof PartTimeEmployee) {
                    PartTimeEmployee employee = (PartTimeEmployee) buckets[i].get(k);

                    double salaryCalculated = (employee.getHourlyWage() * employee.getHoursPerWeek() * employee.getWeeksPerYear()) * (employee.getDeductionRate() / 100);
                    model.addRow(new Object[]{employee.getFirstName(), employee.getLastName(), employee.getEmployeeNumber(), "Part Time", salaryCalculated});
                } else if (buckets[i].get(k) instanceof FullTimeEmployee) {
                    FullTimeEmployee employee = (FullTimeEmployee) buckets[i].get(k);
                    double salaryCalculated = employee.getYearlySalary() * (employee.getDeductionRate() / 100);
                    model.addRow(new Object[]{employee.getFirstName(), employee.getLastName(), employee.getEmployeeNumber(), "Full Time", salaryCalculated});
                } else {
                   //Silence
                }

            }
            EMSMain.logger.info("The Main Menu JTable has been populated.");

        }

    }

    /**
     * Not used in the actual GUI, but just available as a debug function.
     *
     */
    public void displayTable() {
        for (int i = 0; i < length; i++) {

            System.out.println("Bucket number: " + i);
            if (buckets[i].isEmpty()) {
                System.out.println("Empty Bucket");
                continue;
            }

            for (int k = 0; k < buckets[i].size(); k++) {
                System.out.println(buckets[i].get(k).getFirstName() + " " + buckets[i].get(k).getLastName() + " "
                        + buckets[i].get(k).getEmployeeNumber());

            }

        }

    }

    /**
     * Checks if the HashTable is empty
     *
     * @return boolean signifyiheng if the HashTable is empty or not.
     */
    public boolean isEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < length; i++) {
            if (buckets[i].isEmpty()) {
                isEmpty = false;
            }

        }
        return isEmpty;

    }

}
