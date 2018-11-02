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

    public FullTimeEmployee(String firstName, String lastName, int employeeNum, double yearlySalary) {
        super(firstName, lastName, employeeNum);
    }
    
    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }
    public double getYearlySalary() {
        return this.yearlySalary;
    }

}
