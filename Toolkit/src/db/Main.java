/*	Author: Purnell Jones
 * 	File: Main.java
 * 	Description: A packaged main used for debugging db
 * 
 */

package db;

import csv.CSV;

public class Main {

	public static void main(String[] args) {
		
		
		String file = "Expenses.csv";
		CSV csv = new CSV(file,",",true);
		ColumnStructure cs = new ColumnStructure(csv);
		
		System.out.println("~~~~~~~~~~~~~~~~~~~");
		//cs.getColStructDetails();
		
		DBOperations dbo = new DBOperations();
//		dbo.createTable("MyDBTable",cs);
		
//		Column c = new Column();
//		boolean result = c.isAlphabetical("1Hello1");
//		
//		System.out.println("Result of your test: " + result);
		dbo.createDB("javadb");
		dbo.createTable("duh",cs);
		dbo.insertCSV("duh", csv, cs);
	}
}
