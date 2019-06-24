/* CST-105
 * Utils class
 * This class contains utility methods used throughout the banking app
 * Method contains the single Scanner for System.in
 * Method is abstract so no objects can be created
 * All data and methods are static so they can be called without an object reference
 * 
 * @author:  Roy Chancellor
 * @version:  June 17, 2019
 */
package CST_105_Banking_App.BankingApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class Utils {
	public static final Scanner keys = new Scanner(System.in);
	
	//Waits until the user presses ONLY Enter from the keyboard
	public static void pressEnter() {
		String str;
		
		//clear the input stream of the remaining newline character not absorbed by the menu option nextInt() call		
		keys.nextLine();
		
		//Read in lines from the keyboard until the user presses ONLY Enter (length will be zero)
		do {
			str = keys.nextLine();
		} while(str.length() != 0);
	}
	
	//Returns a string of the current date and time format
	public static String getDateTime() {
		LocalDateTime ldtNow = LocalDateTime.now(); 
	    DateTimeFormatter ldtNowFormatted = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		
		return ldtNowFormatted.format(ldtNow);
	}
}
