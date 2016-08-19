package dbTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import db.Column;

public class ColumnTest {
	
	private Column c = new Column();
	
	@Test
	public void defineColumnTest(){

		String[] header = {"first Name", "last Name"};
		String[] cellValue = {"John Crow", "Smith II"};
		int length = header.length;
		Column[] c = new Column[length];
		
		for(int i = 0; i < length; i++){
			c[i] = new Column();
			c[i].defineColumn(header[i], cellValue[i]);
		}
		String[] actualName = new String[length];
		String[] actualLengthValue = new String[length];
		String[] actualSQL = new String[length];
		
		for(int i = 0; i < length; i++){
			actualName[i] = c[i].getName();
			actualLengthValue[i] = c[i].getLengthValue();
			actualSQL[i] = c[i].getSQL();
		}
		
		String[] expectedName = {"firstName","lastName"};
		String[] expectedLengthValue = {"255","255"};
		String[] expectedSQL = {"firstName John Crow", "lastName Smith II"};
		
		for(int i = 0; i < length; i++){
			assertEquals(actualName[i],expectedName[i]);
			assertEquals(actualLengthValue[i],expectedLengthValue[i]);
			assertEquals(actualSQL[i],expectedSQL[i]);
		}
	}
	
	@Test
	public void isAlphabeticalTest(){
		
		String word = "Hello World";
		boolean actual = c.isAlphabetical(word);
		boolean expected = true;
		Assert.assertTrue(c.isAlphabetical(word));
		assertEquals(actual,expected);
	}
	
	@Test
	public void isNumericalTest(){
		
		String number = "16";
		boolean actual = c.isNumerical(number);
		boolean expected = true;
		Assert.assertTrue(c.isNumerical(number));
		assertEquals(actual,expected);
	}
	
	@Test
	public void isFloatTest(){
		
		String number = "10.16";
		boolean actual = c.isAFloat(number);
		boolean expected = true;
		Assert.assertTrue(c.isAFloat(number));
		assertEquals(actual,expected);
	}
	
	@Test
	public void isADateTimeTest(){
		
		String date = "1000-01-01 00:00:00";
		boolean actual = c.isADate(date);
		boolean expected = true;
		Assert.assertTrue(c.isADate(date));
		assertEquals(actual,expected);
	}
	
	@Test
	public void getAndSetNameTest(){
		String actual = "John";
		c.setName(actual);
		assertEquals(actual,c.getName());
	}
	
	@Test
	public void getAndSetTypeTest(){
		String actual = "VARCHAR(255)";
		c.setName(actual);
		assertEquals(actual,c.getName());
	}
	
	@Test
	public void setLengthValueTest(){
		String actual = "255";
		c.setName(actual);
		assertEquals(actual,c.getName());
	}
		
	@Test
	public void getAndSetDefaultTest(){}
	
	@Test
	public void getAndSetCollationTest(){}
		
	@Test
	public void getAndSetAttributeTest(){}	
	
	@Test
	public void getAndSetNullColTest(){}
	
	@Test
	public void getAndSetIdexTest(){}
	
	@Test
	public void getAndSetSQLTest(){}
	
}
