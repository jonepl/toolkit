package db;

import csv.CSV;

public class ColumnStructure {

	private Column[] colStruct;
	
	public ColumnStructure(CSV csv){
		
		createColumnStructure(csv);
	}
	// Determines all SQL Data types base off of Sample column from CSV file
	public void createColumnStructure(CSV csv){

		// Gets number of columns from CSV object
		int length = csv.getNumCols(); 
		System.out.println("This is length: " + length);
		colStruct = new Column[length];
		// Gets sample row for type interruption
		String[] type = csv.getSampleRow();
		// Retrieves the header name
		String[] header = csv.getHeader();
		
		// Create appropriate number of columns
		for(int i = 0; i < colStruct.length; i++){
			
			colStruct[i] = new Column();
			try{
				colStruct[i].determineSQLStatment(header[i],type[i]);
			} catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Index: " + i + " is empty replacing wit empty string");
				colStruct[i].determineSQLStatment(header[i],"");
			}
			
			
		}
	}
	
	// Prints information about each column
	public void getColStructDetails(){
		
		for(int i = 0; i < colStruct.length; i++){
			
			System.out.print(colStruct[i].getName() + " , ");
			System.out.print(colStruct[i].getType() + " , ");
			System.out.println(colStruct[i].getSQL());
		}
	}
	
	/***************** Getters and Setters ********************/
	public Column[] getColStruct() {
		return colStruct;
	}

	public void setColStruct(Column[] colStruct) {
		this.colStruct = colStruct;
	}

}
