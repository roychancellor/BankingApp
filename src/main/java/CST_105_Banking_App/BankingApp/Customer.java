/* CST-105
 * Customer class
 * This class contains the data and methods to create a bank Customer
 * Overrides the Java default toString to return "lastName, firstName"
 * 
 * @author:  Roy Chancellor
 * @version:  June 17, 2019
 */
package CST_105_Banking_App.BankingApp;

public class Customer {
	//Class data
	private String lastName;
	private String firstName;
	
	//Constructor
	Customer(String lastName, String firstName) {
		this.setLastName(lastName);
		this.setFirstName(firstName);
	}
	
	//Getters and setters
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	//Class methods
	public String toString() {
		return getLastName() + ", " + getFirstName();
	}
}
