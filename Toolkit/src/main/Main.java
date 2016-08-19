/* 
 * Author: Purnell Jones
 * File: Main.java
 * Description:	This is the driver for all the Toolkit classes
 */

package main;
// User defined class imports
import csv.CSV;
import db.ColumnStructure;
import db.DBOperations;


// Java library library imports
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// loads basic information and validates csv file
		String file = "Expenses.csv";
		CSV csv = new CSV(file,",",true);
				
		// Creates Column Structure base of csv class information
		ColumnStructure cs = new ColumnStructure(csv);	
		// Creates Database Operations
		DBOperations dbo = new DBOperations();	
		
		
		// Opens Scanner Object
		Scanner sc = new Scanner(System.in);
		int userNumInput = -1;
		String userTextInput = "";
		
		while(userNumInput != 0){
			
			displayPrompt();
			userNumInput = sc.nextInt();
			switch(userNumInput){
				
				case -1:
					break;
					
				case 0:	
					System.out.println("Exiting...");
					break;
				
				case 1:	
					// Skips new line
					sc.nextLine();
					// Gets Table name
					userTextInput = sc.nextLine();
					// TODO Ensure input is in correct format
					System.out.println("Creating New Database with name: " + userTextInput);
					dbo.createDB(userTextInput);
					break;
					
				case 2:
					// Skips new line
					sc.nextLine();
					userTextInput = sc.nextLine();
					// TODO Ensure input is in correct format
					System.out.println("Creating New Table with name: " + userTextInput);
					dbo.createTable(userTextInput,cs);
					dbo.loadCSV(csv);
					break;
					
				default: 
					System.out.println("Default Case.");
					break;
			}
			
		}
		sc.close();
	}
	
	public void welcomePrompt(){
		
		System.out.println("Welcome...");
	}

	public static void displayPrompt(){
		
		System.out.println("\nPlease select one of the options below to start.\n");
		System.out.println("0. Exit Program\n"
						 + "1. Create a NEW MySQL Datatbase\n"
						 + "2. Create and Load NEW MySQL Table\n"
						 + "3. ...\n");
	}
	
	public void createDBPrompt(){
		
	}
	
	public void createTablePrompt(){}
}
