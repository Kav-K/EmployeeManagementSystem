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
        int toPlace = addition.getStudentNumber() % length;

        for (int i = 0; i < length; i++) {
            if (!(i == toPlace)) {
                continue;
            }

            buckets[i].add(addition);
            break;

        }

    }

    public Employee get(int studentNumber) {
        int toRetrieve = studentNumber % length;
        for (int i = 0; i < length; i++) {
            if (!(i == toRetrieve)) {
                continue;
            }

            for (Employee info : buckets[i]) {
                if (info.getStudentNumber() == studentNumber) {
                    return info;
                }
            }

        }
        return null;

    }

    public void remove(int studentNumber) {
        int toRemove = studentNumber % length;
        for (int i = 0; i < length; i++) {
            if (!(i == toRemove)) {
                continue;
            }
            for (Employee info : buckets[i]) {
                if (info.getStudentNumber() == studentNumber) {
                    buckets[i].remove(info);
                    return;

                }

            }

        }

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
                        + buckets[i].get(k).getStudentNumber());

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
