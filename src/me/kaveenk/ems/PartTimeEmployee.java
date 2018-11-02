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
public class PartTimeEmployee extends Employee {

    private double hourlyWage;
    private double hoursPerWeek;
    private double weeksPerYear;

    public PartTimeEmployee(double hourlyWage, double hoursPerWeek, double weeksPerYear, String firstName, String lastName, int employeeNum) {
        super(firstName, lastName, employeeNum);
        this.hourlyWage = hourlyWage;
        this.hoursPerWeek = hoursPerWeek;
        this.weeksPerYear = weeksPerYear;
    }
    public double getHourlyWage() {
        return this.hourlyWage;
    }
    public double getHoursPerWeek() {
        return this.hoursPerWeek;
    }
    public double getWeeksPerYear() {
        return this.weeksPerYear;
    }
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
    public void setWeeksPerYear(double weeksPerYear) {
        this.weeksPerYear = weeksPerYear;
    }
    
    
    

}
