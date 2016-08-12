/*	Author: Purnell Jones
 * 	File: Main.java
 * 	Description: A packaged main used for debugging db
 * 
 */

package db;
import csv.CSV;

public class Main {

	public static void main(String[] args) {
		
		
		String file = "MySpreadsheet.csv";
		CSV csv = new CSV(file,",",true);
		ColumnStructure cs = new ColumnStructure(csv);
		
		DBOperations dbo = new DBOperations();
		dbo.createTable("myTable",cs);
	}
}
