/* CST-105
 * Bank class
 * The Bank class contains all of the top-level methods for operating the banking application
 * displayMenu is the primary method for program control
 * The main method just needs to create objects for Bank, Saving, and Checking, then call bankObject.displayMenu
 * by passing the Checking and Saving objects and displayMenu controls the rest
 * 
 * @author:  Roy Chancellor
 * @version:  June 17, 2019
 */
package CST_105_Banking_App.BankingApp;

import java.text.DecimalFormat;

public class Bank {
	private String bankName;
	//PACKAGE-level format for money outputs in sub classes
	static DecimalFormat money = new DecimalFormat();
	private final String MONEY_FORMAT = "$#,##0.00;($#,##0.00)";
	
	//Constructor
	Bank(String bankName) {
		setBankName(bankName);
		money.applyPattern(MONEY_FORMAT);
	}
	
	//Getters and setters
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		if(bankName != null)
			this.bankName = bankName;
		else
			this.bankName = "ERROR:  null string passed for bank name";
	}

	//Class methods
	
	//displayMenu prints the main menu to the console and waits for user to make a selection
	//Method forces user to enter a correct menu option before processing the user selection
	public void displayMenu(Checking checking, Saving savings) {
		//Scanner menuOption = new Scanner(System.in);
		int menuSelection = 0;
		do {
			do {
				System.out.println("===================================");
				System.out.println(" MAIN MENU");
				System.out.println(" " + this.bankName.toUpperCase());
				System.out.println(" Hello " + checking.getCustomer().getFirstName() + "!");
				System.out.println("===================================");
				System.out.println("Pick an option: ");
				System.out.println("-------------------------------------");
				System.out.println(" 1: : Deposit to Checking (" + checking.getAccountName() + ")");
				System.out.println(" 2: : Deposit to Savings (" + savings.getAccountName() + ")");
				System.out.println(" 3: : Write a Check");
				System.out.println(" 4: : Withdraw from Savings");
				System.out.println(" 5: : Get balance");
				System.out.println(" 6: : Close month");
				System.out.println(" 7: : List transactions");
				System.out.println("-------------------------------------");
				System.out.println(" 9: : Exit");
	
				//Get the user's selection 
				menuSelection = Utils.keys.nextInt();
			} while(menuSelection < 1 || (menuSelection > 7 && menuSelection != 9));

			//Process the user's validated menu selection
			processMenuSelection(menuSelection, checking, savings);
		} while (menuSelection != 9);  //run until the user presses the Exit key
	}
	
	//processMenuSelection calls an action method based on the user's choice from the main menu
	private void processMenuSelection(int menuOption, Checking checking, Saving savings) {
		switch(menuOption) {
		case 1:
			//call displayDepositChecking
			displayDepositChecking(checking);
			break;
		case 2:
			//call displayDepositSavings
			displayDepositSavings(savings);
			break;
		case 3:
			//call displayWithdrawChecking
			displayWithdrawChecking(checking);
			break;
		case 4:
			//call displayWithdrawSavings
			displayWithdrawSavings(savings);
			break;
		case 5:
			//call displayBalanceScreen
			displayBalanceScreen(checking, savings);
			break;
		case 6:
			//call doEndMonth
			doEndMonth(checking, savings);
			break;
		case 7:
			//call displayTransactions
			displayTransactions(checking, savings);
			break;
		case 9:
			//call displayExitScreen
			displayExitScreen();
			break;
		default:
			break;
		}
	}
	
	//ACTION METHODS:  These methods perform the actual banking functions
	
	//OPTION 1:  User wishes to DEPOSIT into CHECKING
	private void displayDepositChecking(Checking checking) {
		String timeStamp = Utils.getDateTime();
		System.out.println("\n*** DEPOSIT into " + checking.getAccountName() + " ***");
		System.out.println("-----------------------------------------");
		System.out.println("Your current checking balance is "
			+ money.format(checking.getBalance())
			+ " as of " + timeStamp);

		//get the amount to deposit
		System.out.println("\nEnter an amount to deposit: ");
		double deposit = Utils.keys.nextDouble();
		
		//update the balance
		checking.doDeposit(deposit);
		
		System.out.println("\nYour new checking balance is "
			+ money.format(checking.getBalance())
			+ " as of " + timeStamp);
		
		//Record the transaction
		checking.addTransaction(timeStamp + ": DEPOSIT INTO CHECKING:" + money.format(deposit));
		
		System.out.println("\nPress Enter to return to the main menu...");
		Utils.pressEnter();
	}
	
	//OPTION 2:  User wishes to DEPOSIT into SAVINGS
	private void displayDepositSavings(Saving savings) {
		String timeStamp = Utils.getDateTime();
		System.out.println("\n*** DEPOSIT into " + savings.getAccountName() + " ***");
		System.out.println("-----------------------------------------");
		System.out.println("Your current savings balance is "
			+ money.format(savings.getBalance())
			+ " as of " + timeStamp);
		
		//get the amount to deposit
		System.out.println("\nEnter an amount to deposit: ");
		double deposit = Utils.keys.nextDouble();
		
		//update the balance
		savings.doDeposit(deposit);
		
		System.out.println("\nYour new savings balance is "
			+ money.format(savings.getBalance())
			+ " as of " + timeStamp);
		
		//Record the transaction
		savings.addTransaction(timeStamp + ": DEPOSIT INTO SAVINGS:" + money.format(deposit));
		
		System.out.println("\nPress Enter to return to the main menu...");
		Utils.pressEnter();
	}
	
	//OPTION 3:  User wishes to WITHDRAW from CHECKING (write a check)
	private void displayWithdrawChecking(Checking checking) {
		String timeStamp = Utils.getDateTime();
		System.out.println("\n*** Write a CHECK from " + checking.getAccountName() + " ***");
		System.out.println("-----------------------------------------");
		System.out.println("Your current checking balance is "
			+ money.format(checking.getBalance())
			+ " as of " + timeStamp);
		
		//Remind user about overdraft fee
		System.out.println("("
			+ money.format(checking.getOverDraftFee())
			+ " overdraft fee if greater than balance)");
		
		//get the amount of the check (withdraw)
		System.out.println("\nEnter amount of check: ");
		double check = Utils.keys.nextDouble();
		
		//perform the withdraw which requires rules for overdrafting
		boolean feeAssessed = checking.doWithdraw(check);
		
		System.out.println("\nYour new checking balance is " + money.format(checking.getBalance()));

		//Record the transaction(s)
		checking.addTransaction(timeStamp + ": WITHDRAW FROM CHECKING:" + money.format(-check));
		if(feeAssessed)
			checking.addTransaction(timeStamp + ": OVERDRAFT FEE:" + money.format(-checking.getOverDraftFee()));
		
		System.out.println("\nPress Enter to return to the main menu...");
		Utils.pressEnter();
	}
	
	//OPTION 4:  User wishes to WITHDRAW from SAVINGS
	private void displayWithdrawSavings(Saving savings) {
		String timeStamp = Utils.getDateTime();
		System.out.println("\n*** WITHDRAW from " + savings.getAccountName() + " ***");
		System.out.println("-----------------------------------------");
		System.out.println("Your current savings balance is "
			+ money.format(savings.getBalance())
			+ " as of " + timeStamp);

		//Get the withdraw amount and set the new balance (validates withdrawal amount)
		double withdrawAmount = savings.doWithdraw();  //overrides the Account doWithdraw method

		System.out.println("\nYour new savings balance is " + money.format(savings.getBalance()));
		
		//Record the transaction
		savings.addTransaction(timeStamp + ": WITHDRAW FROM SAVINGS:" + money.format(-withdrawAmount));
		
		System.out.println("\nPress Enter to return to the main menu...");
		Utils.pressEnter();
	}
	
	//OPTION 5:  User wishes to DISPLAY account BALANCES
	private void displayBalanceScreen(Checking checking, Saving savings) {
		System.out.println("\n*** Get BALANCES for "
			+ checking.getAccountName() + " and "
			+ savings.getAccountName() + " ***");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("As of " + Utils.getDateTime());
		System.out.println("\nYour CHECKING balance is " + money.format(checking.getBalance()));
		System.out.println("\nYour SAVINGS balance is " + money.format(savings.getBalance()));
		System.out.println("\nPress Enter to return to the main menu...");
		Utils.pressEnter();
	}
	
	//OPTION 6:  User wishes to do END OF MONTH
	private void doEndMonth(Checking checking, Saving savings) {
		String timeStamp = Utils.getDateTime();
		System.out.println("\n*** CLOSING for the date " + timeStamp + "***");
		System.out.println("-----------------------------------------");
		System.out.println("Month end balance for "
			+ checking.getAccountName()
			+ " is "
			+ money.format(checking.getBalance()));
		
		//Determine the NEW savings balance from the balance modifiers:  +interest and -service fee
		//INTEREST
		double interestEarned = savings.computeMonthlyInterest();  //updates balance and returns interest earned

		//SERVICE FEE
		boolean feeAssessed = savings.computeServiceFee();  //updates balance and returns true if fee assessed/false if not
		
		System.out.println("\nMonth end balance for "
			+ savings.getAccountName() + " is "
			+ money.format(savings.getBalance()));

		System.out.println("\tIncludes interest earned:  " + money.format(interestEarned));

		//Record the transaction(s)
		savings.addTransaction(timeStamp + ": INTEREST EARNED: " + money.format(interestEarned));

		if(feeAssessed) {
			System.out.println("\tIncludes service fee assessed:  " + money.format(savings.getServiceFee()));
			//Record the transaction
			savings.addTransaction(timeStamp + ": SERVICE FEE: " + money.format(-savings.getServiceFee()));
		}

		System.out.println("\nPress Enter to return to the main menu...");
		Utils.pressEnter();
	}
	
	//OPTION 7:  List all transactions
	private void displayTransactions(Checking checking, Saving savings) {
		System.out.println("\n*** LIST OF TRANSACTIONS for " + checking.getCustomer().getFirstName() + "'s Accounts ***");
		System.out.println("------------------------------------------------------------");
		System.out.println("CHECKING: " + checking.getAccountName());
		checking.printTransactions(checking.getTransactions());
		System.out.println("\nSAVINGS: " + savings.getAccountName());
		savings.printTransactions(savings.getTransactions());
		
		System.out.println("\nPress Enter to return to the main menu...");
		Utils.pressEnter();
	}
	
	//OPTION 9:  User wishes to EXIT
	private void displayExitScreen() {
		System.out.println("\nGOODBYE!");
		System.out.println("-----------------------------------------");
		System.out.println("Thank you for banking with " + getBankName());
		System.out.println("\nHave a wonderful day!");
	}
	
	public String toString() {
		return "\tYou just made a Bank object for the bank " + this.bankName;
	}
}
