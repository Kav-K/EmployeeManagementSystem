package me.kaveenk.ems;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Employee implements Serializable {
        private static final long serialVersionUID = -2722972583608119832L;
	private String firstName;
	private String lastName;
	private int employeeNum;
        
        public static EMSMainAndLogin mainInstance;
        public Employee(EMSMainAndLogin instance) {
            this.mainInstance = instance;
        }
        
	public Employee(String firstName, String lastName, int employeeNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeNum = employeeNum;
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

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNum = employeeNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
        
        public static void serialize() {
         try {
         FileOutputStream fileOut = new FileOutputStream("employeeData.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(mainInstance.employeeTable.obtainSingleArrayObject());
         out.close();
         fileOut.close();
         System.out.printf("Serialized to employeeData.ser");
      } catch (IOException i) {
         i.printStackTrace();
      }
        }
        
 
        public static void load() {
      try {
         FileInputStream fileIn = new FileInputStream("employeeData.ser");
         
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
             } continue;
             
              
         }
         
         
 
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("Class Failure");
         c.printStackTrace();
         return;
      }
        }
        

}
