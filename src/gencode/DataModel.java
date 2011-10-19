package gencode;

import utilities.Tool;

public abstract class DataModel {

	protected String name;

	protected char upperValue;
	protected char lowerValue;
	
	protected String type;
	protected String visibility;
	
	protected boolean visbPrivate;
	protected boolean isStatic;

	protected String tabInd;
	protected int tab;
	
	public DataModel(String name) {
		this.name = name;
		this.visibility = "public";
		this.upperValue = '-';
		this.lowerValue = '-';
		this.type = "void";
	}

	// set

	public void setUpperValue(char upperValue) {
		this.upperValue = upperValue;
	}

	public void setLowerValue(char lowerValue) {
		this.lowerValue = lowerValue;
	}

	public void setVisibility(String visibility) {
		if((visibility != null)){
			if( (visibility.equals("protected")) || (visibility.equals("private")) ){
				this.visbPrivate = true;
			}
			this.visibility = visibility;
		}
	}
	
	// set
	public void setType(String type) {
		this.type = type;
	}

	
	
	// get

	public char getUpperValue() {
		return this.upperValue;
	}

	public char getLowerValue() {
		return this.lowerValue;
	}

	public String getName() {
		return this.name;
	}

	public String getVisibility() {
		return this.visibility;
	}
	
	// get
	public String getType() {
		return this.type;
	}

	
	/**
	 * Função auxiliar: parserAtribute, parserParameter, parserReturn
	 * @param str
	 * @param line
	 * @return
	 */
	protected static String parserType(String str, String line){		
		str = Tool.manipulate(line, "pathmap:", "#", "\"");
		if(line.contains("Ecore.metamodel.uml")){
			str = str.substring(1).toLowerCase();
		}
		if(line.contains("UMLPrimitiveTypes")){
			if(str.contains("Integer")){
				str = "int";
			}
			if(!(str.equals("String"))){
				str = str.toLowerCase();
			}
		}
		return str;
	}

	
	public abstract void printProp();
	
}
