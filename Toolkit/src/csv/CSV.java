/* Author: 		Purnell Jones
 * File:		CSV.java
 * Description:	This class will take in a CSV, store its header
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
		System.out.println(sampleRow[0]);
	}
	
	public void parseCSV(String file){
		
		// Sets the header and numCol data members 
		numCols = retrieveHeader();
		// Sets the rows and data member and validates CSV
		numRows = validateCSV();
	} 

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
	
	public int validateCSV(){
		
		int rowCnt = 0;	
		BufferedReader br = null;
		String line = "";
		try{
			br = new BufferedReader(new FileReader(fileName));

			// Read each line of the file			
			while((line = br.readLine()) != null) {
				++rowCnt;
				String[] row = line.split(delimiter);
				if(rowCnt == 3){ sampleRow = line.split(delimiter); }
				// if any row has more than entries than columns the CSV isn't valid
				if(row.length > header.length){
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
	
	public String[] getHeader(){
		return header;
	}
}
