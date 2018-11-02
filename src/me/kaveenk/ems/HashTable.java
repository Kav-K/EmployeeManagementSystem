package me.kaveenk.ems;

import java.util.ArrayList;

public class HashTable {

    private ArrayList<Employee>[] buckets;
    private int length;

    /**
     * @author Kaveen Kumarasinghe
     * @version 1.0
     * @date 10/17/2018
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

    public void remove(int employeeNumber) {
        int toRemove = employeeNumber % length;
        for (int i = 0; i < length; i++) {
            if (!(i == toRemove)) {
                continue;
            }
            for (Employee info : buckets[i]) {
                if (info.getEmployeeNumber() == employeeNumber) {
                    buckets[i].remove(info);
                    return;

                }

            }

        }

    }
    
    public ArrayList<Employee> obtainSingleArrayObject() {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        for (int i = 0; i < length; i++) {

            System.out.println("Bucket number: " + i);
            if (buckets[i].isEmpty()) {
                System.out.println("Empty Bucket");
                continue;
            }

            for (int k = 0; k < buckets[i].size(); k++) {
                employeeList.add(buckets[i].get(k));

            }

        }
        return employeeList;
        
    }

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
