/* CST-105
 * Checking class
 * This class contains all of the data and methods unique to a checking account
 * Checking is a child of Account, so it inherits all of the Account methods
 * Checking overrides the Account method for doWithdraw to allow functions unique to checking accounts
 * 
 * @author:  Roy Chancellor
 * @version:  June 19, 2019
 */
package CST_105_Banking_App.BankingApp;

public class Checking extends Account {
	//Class data
	private double overDraftFee;
	private final double OVER_DRAFT_FEE = 45.00;
	
	//Constructor
	Checking(double initialBalance, String checkingName, Customer customer) {
		//call the parent Account constructor because all accounts need these data
		super(initialBalance, checkingName, customer);
		//set the data specifically for Checking objects
		setOverDraftFee(OVER_DRAFT_FEE);  //unique to Checking objects
	}
	
	//Getters and setters
	public double getOverDraftFee() {
		return overDraftFee;
	}

	public void setOverDraftFee(double overDraftFee) {
		this.overDraftFee = overDraftFee;
	}
	
	//Class methods
	
	//doWithdraw OVERRIDES the doWithdraw method in the Account super class to allow for the overdraft fee feature
	public boolean doWithdraw(double amount) {
		boolean feeAssessed = false;
		
		//If the user wrote a check greater than the current balance, deduct an overdraft fee
		if(amount > getBalance()) {
			amount += getOverDraftFee();  //add the overdraft fee to the withdraw amount
			System.out.println("\tOVERDRAFT FEE CHARGED");
			feeAssessed = true;
		}
		
		//Deduct the check amount (+ overdraft fee, if applicable) from the balance
		setBalance(getBalance() - amount);
		
		return feeAssessed;
	}
}
