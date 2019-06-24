/* CST-105
 * Banking Application
 * This is the main method where the banking application begins
 * The main method creates the three objects necessary for banking:  Bank, Checking, and Saving
 * then calls the displayMenu method that takes over control of the banking functions
 * 
 * @author:  Roy Chancellor
 * @version:  June 19, 2019
 */
package CST_105_Banking_App.BankingApp;

public class BankingClient {
	public static void main(String[] args) {
		//Create Bank, Checking, and Saving objects and call the main menu which takes over control
		Bank gcu = new Bank("GCU Credit Union");
		Saving savingAccount = new Saving(5000.00, "SAV191923", new Customer("Chancellor", "Roy"));
		Checking checkingAccount = new Checking(5000.00, "CHK991773", new Customer("Chancellor", "Roy"));
		
		//Call the main menu screen to begin the application
		gcu.displayMenu(checkingAccount, savingAccount);
	}
}
