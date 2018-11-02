package me.kaveenk.ems;

public class Employee {
	private String firstName;
	private String lastName;
	private int employeeNum;

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

	public int getStudentNumber() {
		return this.employeeNum;
	}

	public void setStudentNumber(int studentNumber) {
		this.employeeNum = studentNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
        
        public void save() {
            
        }
        
        private void serialize() {
            
        }

}
