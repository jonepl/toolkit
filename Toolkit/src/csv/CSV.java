/* Author: 		Purnell Jones
 * File:		CSV.java
 * Description:	This class will take in a csv file, store its header
 * 		number of rows, columns, determine if the CSV is
 * 		valid. This is meant to be used to interface with
 * 		mySQL database
 * 
 */

package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import db.ColumnStructure;

// TODO Decide whether to abstract out CSV Column logic or combine into one large object

public class CSV {
	
	private String fileName;
	private String delimiter;
	private String[] header;
	private String[] sampleRow;
	private int numCols;
	private int numRows;
	// TODO export recordListCnt to configuration file
	private static int recordListCnt = 150;
		
	// Constructor
	public CSV(String file, String delim, boolean hasHeader){
		
		this.fileName = file;
		this.delimiter = delim;
		this.numCols = 0;
		this.numRows = 0;
		this.sampleRow = null;
		this.header = null;
		// Sets Col, Rows, Header, Column Structure
		parseCSV(file);			
	}
	
	// Sets number of rows, columns and set header
	public void parseCSV(String file){
		// TODO: Add logic for determining a csv with header versus without header
		// Sets the header and numCol data members 
		numCols = retrieveHeader();
		// Sets the rows and data member and validates CSV
		numRows = validateCSV();
	} 
	
	// Reads in the first row of the csv file
	public int retrieveHeader(){
		
		String line = "";
		int colCnt = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			line = br.readLine();
			header = removeSpaces(line.split(delimiter));
			colCnt = header.length;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return colCnt;
	}
	
	public String [] removeSpaces(String[] header) {
		
		for(int i = 0; i < header.length; i++ ) { 
			header[i] = header[i].replaceAll(" ", ""); 
		}
		return header;
	}
	
	/*	Reads entire file to ensure no row contains more columns than header
	 *	specifies.
	 *	Retrieve a sample Row from the 3 column.
	 *	RETURNS: number of columns if valid
	 */
	public int validateCSV(){
		
		int rowCnt = -1;	
		BufferedReader br = null;
		String line = "";
		try{
			br = new BufferedReader(new FileReader(fileName));

			// Read each line of the file			
			while((line = br.readLine()) != null) {
				++rowCnt;
				// TODO: Find better method to set sampleRow 
				String[] row = line.split(delimiter);
				if(rowCnt == 3){ sampleRow = line.split(delimiter); }
				// if any row has more entries than columns the CSV is invalid
				if(row.length > header.length){
					rowCnt = 0;
					break;
				}
			}
		} catch (FileNotFoundException e){
			
			e.printStackTrace();
		} catch (IOException e){

			e.printStackTrace();
		}finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		return rowCnt;
	}
	
	public String[] getInsertValueList(CSV csv, ColumnStructure cs){
		// Creates n amount of value packages
		int recordListSize = ((int)Math.ceil(csv.getNumRows()/recordListCnt)) + 1;
		String[] recordList = new String[recordListSize];
		String recordListStr = "";
		int lineCounter = 0;
		int recordCounter = 0;
		BufferedReader br = null;
		String line = "";
		
		try{
			br = new BufferedReader(new FileReader(fileName));
			line = br.readLine();
			
			// Read each line of the file			
			while((line = br.readLine()) != null) {
				
				++lineCounter;
				recordListStr += " (";
				String[] row = line.split(delimiter,-1);
				// TODO: switch for record size with bigger chuncks that 150
				for(int i = 0; i < row.length; i++) {
					
					if(!lastElement(i,row.length))
					{

						String temp = cs.getColStruct()[i].formatData(row[i]);
						recordListStr += temp + ", ";
					}
					else{
						
						String temp = cs.getColStruct()[i].formatData(row[i]);
						recordListStr += temp + "),\n";
					}
				}
				
				if(lineCounter%recordListCnt == 0 && lineCounter != 0){ 
					
					recordList[recordCounter] = recordListStr;
					recordListStr = "";
					++recordCounter;
				}
			}
			
			String str = recordListStr;
			str = str.substring(0, str.length()-2);
			recordList[recordCounter] = str;
			
			for(int i = 0; i < recordList.length; i++){

				System.out.print(recordList[i]);
			}
		} catch (FileNotFoundException e){
			
			e.printStackTrace();
		} catch (IOException e){

			e.printStackTrace();
		}finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return recordList;
	}

	public boolean lastElement(int index, int upperbound) {
		boolean status = false;
		if (index >= upperbound - 1) status = true;
		return status;
	}
	/******* Getters & Setters **************/
	public void printHeader(){
		
		for(int i = 0; i < header.length; i++){
			if(lastElement(i,header.length)){
				System.out.print(header[i] + ", ");
			} else {
				System.out.print(header[i]);
			}
			
		}
	}
	
	// Comma delimited String
	public void setHeader(String headerStr){}
	
	public String getFileName() {
		return fileName;
	}
	
	public int getNumRows(){
		return numRows;
	} 
	
	public int getNumCols(){
		return numCols;
	}
	
	public String[] getSampleRow(){
		return sampleRow;
	}
	
	public String[] getHeader(){
		return header;
	}
}
