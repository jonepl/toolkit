/*
 * Author: Purnell Jones
 * File DBOperations.java
 * Description: This class is used to interface with
 * 				mySQL database. 
 * 
 */

package db;

import java.io.FileReader;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	// Executes a SQL query
	public void query(String query){
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public void createTable(String sql) {
		query(sql);
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
		
	// Removes a tuple
	public void delete(String sql){
		query(sql);
	}
	
	// Updates a tuple
	public void update(String sql){
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
	public void setCredentials(String db, String un, String pd){
		
		dbName = db;
		userName = un;
		password = pd;
	}
}
