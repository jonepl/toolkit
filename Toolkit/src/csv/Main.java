package csv;

public class Main {
	public static void main(String[] args) {
		
		String file = "Expenses.csv";
		CSV csv1 = new CSV(file,",",true);
		csv1.printHeader();
		
		//csv1.loadCSVtoDB(file);
		//csv1.printCSV();
	}
}
