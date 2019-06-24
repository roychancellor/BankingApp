package CST_105_Banking_App.BankingApp;

import java.text.DecimalFormat;

public class Test {

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("$#,##0.00;($#)");
		System.out.println(df.format(100));
		System.out.println(df.format(-100));
	}

}
