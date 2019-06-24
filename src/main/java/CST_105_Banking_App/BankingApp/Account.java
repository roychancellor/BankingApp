/* CST-105
 * Account class
 * This abstract class contains all of the elements for constructing account types
 * The class is abstract to prevent creation of Account objects
 * 
 * @author:  Roy Chancellor
 * @version:  June 19, 2019
 */
package CST_105_Banking_App.BankingApp;

import java.util.ArrayList;

public abstract class Account {  //class is abstract with no constructor defined so no other class can make Account objects
	//Data fields
	private double balance;
	private String accountName;
	private ArrayList<String> transactions = new ArrayList<String>();  //holds all transaction recordings
	private Customer customer;  //attach a customer to an account
	
	//Constructor
	Account(double initialBalance, String accountName, Customer customer) {
		setBalance(initialBalance);
		setAccountName(accountName);
		setCustomer(customer);
		//Set the initial balance for the account
		addTransaction(Utils.getDateTime() + ": INITIAL BALANCE: " + Bank.money.format(initialBalance));
	}
	
	//Getters and setters
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public ArrayList<String> getTransactions() {
		return transactions;
	}
		
	//Class methods (subclasses may override these for functionality specific to those classes
	public boolean doWithdraw(double amount) {
		setBalance(getBalance() - amount);
		return true;
	}
	
	public void doDeposit(double amount) {
		setBalance(getBalance() + amount);
	}
	
	//Writes a transaction into the list of transactions for the account object
	public void addTransaction(String transaction) {
		transactions.add(transaction);  //add transaction to the ArrayList of Strings
	}
	
	//Prints out all transactions recorded during the banking session (FUTURE: add a date range)
	public void printTransactions(ArrayList<String> transactionList) {
		for(String trans : transactionList) {
			System.out.println("\t" + trans);
		}
		//Print current balance
		System.out.println("--------------------------------------------------");
		System.out.println("\tENDING BALANCE: " + Bank.money.format(getBalance()));
	}	
}
