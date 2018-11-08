/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems.types;

/**
 *
 * @author kaveen
 */
public class FullTimeEmployee extends Employee {

    private double yearlySalary;

    /**
     * Create a new FullTimeEmployee object with the given parameters.
     *
     * @param firstName
     * @param lastName
     * @param employeeNum
     * @param sex
     * @param workLocation
     * @param yearlySalary
     */
    public FullTimeEmployee(String firstName, String lastName, int employeeNum, int sex, String workLocation, double yearlySalary, double deductionRate) {
        super(firstName, lastName, employeeNum, sex, workLocation, deductionRate);
        this.yearlySalary = yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double getYearlySalary() {
        return this.yearlySalary;
    }

}
