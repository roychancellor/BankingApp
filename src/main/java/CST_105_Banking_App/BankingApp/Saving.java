/* CST-105
 * Saving class
 * This class contains all of the data and methods unique to a savings account
 * Savings is a child of Account, so it inherits all of the Account methods
 * Savings overrides the Account method for doWithdraw to allow functions unique to savings accounts
 * 
 * @author:  Roy Chancellor
 * @version:  June 19, 2019
 */
package CST_105_Banking_App.BankingApp;

public class Saving extends Account {
	//Class fields
	private double serviceFee;
	private double annualInterestRate;
	private double minBalance;
	private final double SERVICE_FEE = 25.00;
	private final double ANNUAL_INTEREST_RATE = 0.06;
	private final double MINIMUM_BALANCE = 200;
	private final int MONTHS_PER_YEAR = 12;
	
	//Constructor
	Saving(double initialBalance, String savingName, Customer customer) {
		//call the parent Account constructor because all accounts need these data
		super(initialBalance, savingName, customer);
		//set the data specifically for Saving objects
		setServiceFee(SERVICE_FEE);
		setAnnualInterestRate(ANNUAL_INTEREST_RATE);
		setMinBalance(MINIMUM_BALANCE);
	}

	//Getters and setters
	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	
	//Class methods
	
	//doWithdraw OVERRIDES the doWithdraw method in the Account super class
	//Prevents user from withdrawing more than the available balance
	public double doWithdraw() {
		double withdrawAmount;
		boolean balanceExceeded;
		//Request the withdraw amount from the user and validate that it is less than the available balance
		do {
			balanceExceeded = false;
			System.out.println("\nEnter an amount to withdraw: ");
			withdrawAmount = Utils.keys.nextDouble();
			
			if(balanceExceeded = withdrawAmount > getBalance()) {
				System.out.println("\t"
					+ Bank.money.format(withdrawAmount)
					+ " exceeds your available balance.");
			}
		} while(balanceExceeded);
		
		//update the balance with the validated amount
		setBalance(getBalance() - withdrawAmount);
		
		return withdrawAmount;  //for the calling method to use if it wants
	}
	
	//computeMonthlyInterest computes the MONTHLY SIMPLE interest
	public double computeMonthlyInterest() {
		double interestEarned = getBalance() * getAnnualInterestRate() / MONTHS_PER_YEAR;
		
		//add the interest to the balance
		setBalance(getBalance() + interestEarned);
		
		return interestEarned;
	}
	
	public boolean computeServiceFee() {  //returns true if a fee was assessed
		if(getBalance() < getMinBalance()) {
			setBalance(getBalance() - getServiceFee());
			return true;
		}
		return false;
	}
}
