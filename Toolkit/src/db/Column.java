/*
 * Author: Purnell Jones
 * File: Column.java
 * Description: This class holds all data types for creating a MySQL Column
 */
package db;

import java.util.regex.*;

public class Column {

	private String name;
	private String type;
	private String lengthValue;
	private String defaultCol;
	private String collation;
	private String atrributes;
	private String nullCol;
	private String index;
	private String sql;
	
	public Column(){
		name = "";
		type = "";
		lengthValue = "";
		defaultCol = "";
		collation = "";
		atrributes = "";
		nullCol = "";
		index = "";
	}
	// Uses regular expressions to determine and set the data members column
	public String determineSQLStatment(String header, String type){
		
		name = header.replaceAll(" ","");
		
		if(isAlphabetical(type)){
		
			lengthValue = "255";
			type = "VARCHAR(" + lengthValue + ")";
			sql = name + " " + type;
			/******************/
			System.out.println(name + " is a String");
			System.out.println("SQL Column statement: " + sql);
		}
		
		else if(isNumerical(type)){
			
			type = "INT";
			sql = name + " " + type;
			/***********************/
			System.out.println(name + " is a Number");
			System.out.println("SQL Column statement: " + sql);
		}
		else if(isAFloat(type)){

			type = "FLOAT";
			sql = name + " " + type;
			System.out.println(name + " is a float");
			System.out.println("SQL Column statement: " + sql);
		}
		else if(isADateTime(type)){

			type = "DATETIME";
			sql = name + " " + type;
			System.out.println(name + " is a DateTime");
			System.out.println("SQL Column statement: " + sql);
		}
		else if(isADate(type)) {

			type = "DATE";
			sql = name + " " + type;
			System.out.println(name + " is a Date");
			System.out.println("SQL Column statement: " + sql);
		}
		else{

			type = "VARCHAR(255)";
			sql = name + " " + type;
			System.out.println("sql can not be determined...");
			System.out.println("SQL Column statement: " + sql);
		}
		return sql;
	}
	
	public boolean isAlphabetical(String str){
		
		boolean status = false;
		String alphaRegex = "[A-za-z]*";
		
		if(regexChecker(alphaRegex, str)){
			status = true;
		}
		return status;
	}
	
	public boolean isNumerical(String str){
		
		boolean status = false;
		String numRegex = "^[0-9]*$";
		
		if(regexChecker(numRegex, str)){
			status = true;
		}
		
		return status;
	}
	
	public boolean isAFloat(String str){
		
		boolean status = false;
		String floatRegex = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$";
		
		if(regexChecker(floatRegex, str)){
			status = true;
		}
		return status;
	}
	
	public boolean isADate(String str){
		
		boolean status = false;
		String dateRegex = "[0-9]{1,2}(\\/)[0-9]{1,2}(\\/)[0-9]{4}|(\\d{4})(-)(\\d{2})(-)(\\d{2})";
		
		if(regexChecker(dateRegex, str)){
			status = true;
		}
		
		return status;
	}
	
	public boolean isADateTime(String str){
		boolean status = false;
		
		String regex = "(\\d{4})(-)(\\d{2})(-)(\\d{2})( )(\\d{2})(:)(\\d{2})(:)(\\d{2})";
		
		if(regexChecker(regex,str)){
			System.out.println("This is a DateTime column");
			status = true;
		}
		return status;
	}
	
	public static boolean regexChecker(String theRegex, String str2Check){
		
		boolean status = false;
		
		Pattern checkRegex = Pattern.compile(theRegex);
		
		Matcher regexMatcher = checkRegex.matcher(str2Check);
		
		while(regexMatcher.find()){
			if(regexMatcher.group().length() != 0){
				//System.out.println(regexMatcher.group().trim());
				status = true;
			}
			
			//System.out.println("Start index: " + regexMatcher.start());
			//System.out.println("End index: " + regexMatcher.end());
		}
		
		return status;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLengthValue() {
		return lengthValue;
	}

	public void setLengthValue(String length_Value) {
		this.lengthValue = length_Value;
	}

	public String getDefaultCol() {
		return defaultCol;
	}

	public void setDefaultCol(String defaultCol) {
		this.defaultCol = defaultCol;
	}

	public String getCollation() {
		return collation;
	}

	public void setCollation(String collation) {
		this.collation = collation;
	}

	public String getAtrributes() {
		return atrributes;
	}

	public void setAtrributes(String atrributes) {
		this.atrributes = atrributes;
	}

	public String getNullCol() {
		return nullCol;
	}

	public void setNullCol(String nullCol) {
		this.nullCol = nullCol;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getSQL(){
		return sql;
	}
	
	public void setSQL(String sql){
		this.sql = sql;
	}
}
