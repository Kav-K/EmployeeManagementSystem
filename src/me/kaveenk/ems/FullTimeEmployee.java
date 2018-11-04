/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems;

/**
 *
 * @author kaveen
 */
public class FullTimeEmployee extends Employee {

    private double yearlySalary;
    /**
     * New FullTimeEmployee object
     * @param firstName
     * @param lastName
     * @param employeeNum
     * @param sex
     * @param workLocation
     * @param yearlySalary 
     */
    public FullTimeEmployee(String firstName, String lastName, int employeeNum, int sex, String workLocation, double yearlySalary) {
        super(firstName, lastName, employeeNum, sex, workLocation);
        this.yearlySalary = yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double getYearlySalary() {
        return this.yearlySalary;
    }

}
