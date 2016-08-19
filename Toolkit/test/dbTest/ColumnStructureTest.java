package dbTest;

import static org.junit.Assert.assertEquals;
import csv.CSV;
import db.ColumnStructure;

public class ColumnStructureTest {
	
	private String file = "Expenses.csv";
	private CSV csv = new CSV(file,",",true);
	private ColumnStructure cs = new ColumnStructure(csv);
	
	public void createColumnStructure(){
		
		/* Logic Needed */

	}
	
	public void getAndSetColStruct() {

		/* Logic Needed */
	}
}
