/*
 * Author: Purnell Jones
 * File DBOperations.java
 * Description: This class is used to interface with MySQL database.  
 */

package db;

import java.io.FileReader;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import csv.CSV;

public class DBOperations {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private String dbName;
	private String userName;
	private String password;
	
	public DBOperations(){
		
		con = null;
		st = null;
		rs = null;
		setDefaultCredentials();
		connect();
	}
	// Establishes connection with mySQL
	public void connect(){
		
		try{
			// ********************
			Class.forName("com.mysql.jdbc.Driver");
			// Makes connection to mySQL DB
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,userName,password);
			
		} catch(Exception ex){
			System.out.println("connect() Error: " + ex);
		}
	}
	
	// Executes non data manipulating SQL queries
	public void query(String query){
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Executes a SQL query
	public void queryUpdate(String query){
		
		try {
			st = con.createStatement();
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Returns the ResultSet data member
	public ResultSet getResultSet(){
			return rs;
	}
	
	// Creates a database
	public void createDB(String db){
		query("CREATE DATABASE " + db);
		System.out.println("Database created successfully...");
	}
	
	// Drops a database
	public void dropDB(String db){
		query("DROP DATABASE " + db);
		System.out.println("Database deleted successfully...");
	}
	
	// Creates a new table
	public void createTable(String table, ColumnStructure cs) {
		
		String sql = "CREATE TABLE IF NOT EXISTS " + dbName + "." + table + " (";
		int csSize = cs.getColStruct().length;
		// Appends the strings require to create each column
		for(int i = 0; i < csSize; i++){
			// Appends a comma to all except last row
			if(!lastElement(i,csSize)){
				sql += cs.getColStruct()[i].getSQL() + ", ";
			}else{
				sql += cs.getColStruct()[i].getSQL() + ")";
			}
		}
		
		System.out.println("Executing SQL Statement: " + sql);
		queryUpdate(sql);
	}
	
	// Removes all records and Database
	public void dropTable(String tb){
		query("DROP Table " + tb);
	}
	
	// Removes all records from tb
	public void emptyTable(String tb){
		query("TRUNCATE " + tb);
	}
	
	// Inserts a tuple
	public void insert(String sql){
		query(sql);
	}
	
	public void insertCSV(String tableName, CSV csv, ColumnStructure cs) {
		String q = "INSERT INTO " + tableName + " (";
		String[] header = csv.getHeader();
		// Appends headers to query statement
		// TODO headers are stored with spaces 
		for(int i = 0; i < csv.getNumCols(); i++) {
			
			if(!lastElement(i,csv.getNumCols())){
				q += header[i] + ", ";
			}
			else {
				q += header[i] + ") ";
			}
		}
		q += "VALUES ";
		
		System.out.println(q);
		// TODO change function to use this and remove csv parameter
		String[] value = csv.getInsertValueList(csv,cs);
		for(int i = 0; i < value.length; i++){
			queryUpdate(q + value[i]);
		}
	}
		
	// Removes a tuple
	public void delete(String sql){
		String q = "DELETE FROM tableName WHERE condition";
		//DELETE FROM tutorials_tbl WHERE tutorial_id=3;
		query(sql);
	}
	
	// Removes a tuple
	public void delete(String table, String condition){
		String q = "DELETE FROM" + table + " tableName WHERE " + condition;
		//DELETE FROM tutorials_tbl WHERE tutorial_id=3;
		query(q);
	}
	
	// Updates a tuple
	public void update(String sql){
		String q = "UPDATE table SET condition1, condition2";
		query(sql);
	}
		
	public void set(String sql){
		query(sql);
	}
	
	// Sets the default credentials from config folder 
	private void setDefaultCredentials(){
		try(FileReader reader = new FileReader("config")){
			Properties properties = new Properties();
			properties.load(reader);
			
			dbName = properties.getProperty("dbName");
			userName = properties.getProperty("userName");
			password = properties.getProperty("password");
			
		}catch (Exception e){
			
			e.printStackTrace();
		}
	}
	
	
	// Sets DB credentials
	public void setCredentials(String db, String un, String pw){
		
		dbName = db;
		userName = un;
		password = pw;
	}
	
	public boolean lastElement(int index, int upperbound) {
		boolean status = false;
		if (index >= upperbound - 1) status = true;
		return status;
	}
	
	public void loadCSV(CSV csv){
		System.out.println("NEED LOGIC");
	}
}
