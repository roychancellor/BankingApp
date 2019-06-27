# Banking App 1.0
Banking application written as part of the first course of a GCU certificate in Java programming.
Implements a simple bank, much like an ATM, with capability to withdraw, deposit, get balances, and list transactions
(something I added beyond the project assignment)
* BankingClient.java contains main()
* Bank.java is the class with the primary methods to run the bank
* Account.java is an abstract superclass for creating sub-classes for Checking and Savings accounts
* Saving.java is a subclass of Account that overrides the doWithdraw method in Account and adds fees unique to Savings accts
* Checking.java is a subclass of Account that overrides the doWithdraw method in Account and adds fees unique to Checking accts
* Customer.java is a class for creating customers which currently only has names
* Utils.java has a couple of static utility methods for the app
# SKILLS USED:  inheritance, polymorphism, encapsulation, abstraction, method overriding, collections (ArrayList)
