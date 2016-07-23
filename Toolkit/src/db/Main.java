package db;

import java.util.Scanner;



public class Main {

	public static void main(String[] args) {
		
		DBOperations dbo = new DBOperations();
		dbo.createDB("STUDENT");
		Scanner sc = new Scanner(System.in);
		
		int userInput = sc.nextInt();
		while(userInput != 0){
			userInput = sc.nextInt();
		}
		dbo.dropDB("STUDENT");
		sc.close();
	}
}
