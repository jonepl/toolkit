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

public class CSV {
	
	private String fileName;
	private String delimiter;
	private String[] header;
	private String[] sampleRow;
	private int numCols;
	private int numRows;
		
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
	// 
	public void parseCSV(String file){
		// TODO: Add logic for determining a csv with header versus without
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
			header = line.split(delimiter);
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

	/******* Getters & Setters **************/
	public void printHeader(){
		
		for(int i = 0; i < header.length; i++){
			if(i != header.length-1){
				System.out.print(header[i] + ", ");
			} else {
				System.out.print(header[i]);
			}
			
		}
	}
	
	// Comma delimited String
	public void setHeader(String headerStr){}
	
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
