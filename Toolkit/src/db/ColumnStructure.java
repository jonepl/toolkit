package db;

public class ColumnStructure {

	private String name;
	private String type;
	private String lengthValue;
	private String defaultCol;
	private String collation;
	private String atrributes;
	private String nullCol;
	private String index;
	
	public ColumnStructure(){
		String name = null;
		String type = null;
		String lengthValue = null;
		String defaultCol = null;
		String collation = null;
		String atrributes = null;
		String nullCol = null;
		String index = null;
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

}
