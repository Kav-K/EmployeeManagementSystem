/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import me.kaveenk.ems.main.EMSMain;
import me.kaveenk.ems.types.Employee;

/**
 * Thread to allow the EMS to concurrently save employee data to a file in a
 * non-blocking mechanism.
 *
 * @author Kaveen Kumarasinghe
 */
public class SaveThread extends Thread {

    private String serialFile;
    private ArrayList<Employee> employeeList;

    /**
     * Initialize a SaveThread to save to a specific serial file with a specific
     * employeeList
     *
     * @param serialFile The file to save to
     * @param employeeList The list to serialize.
     */
    public SaveThread(String serialFile, ArrayList<Employee> employeeList) {
        this.serialFile = serialFile;
        this.employeeList = employeeList;
    }

    @Override
    public void run() {

        try {
            FileOutputStream fileOut = new FileOutputStream(serialFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employeeList);
            out.close();
            fileOut.close();
            EMSMain.logger.info("EMS Data has been serialized to " + serialFile);
        } catch (IOException i) {
            EMSMain.logger.error("There was an error during serialization", i);

        }
    }

}
