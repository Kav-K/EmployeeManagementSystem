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
        private int sex;
        private String workLocation;
        
        public static EMSMain mainInstance;
        public Employee(EMSMain instance) {
            this.mainInstance = instance;
        }
        
	public Employee(String firstName, String lastName, int employeeNum,int sex, String workLocation) {
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
        
        public static void serialize() {
         try {
         FileOutputStream fileOut = new FileOutputStream("employeeData.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(mainInstance.employeeTable.toArray());
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
