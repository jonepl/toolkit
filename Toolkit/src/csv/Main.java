/*	Author: Purnell Jones
 * 	File: Main.java
 * 	Description: A packaged main used for debugging csv
 */

package csv;

public class Main {
	public static void main(String[] args) {
		
		String file = "Expenses.csv";
		CSV csv1 = new CSV(file,",",true);
		csv1.printHeader();
	}
}
