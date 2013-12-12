package model.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import utilities.Tool;

public class Fragment extends DataSequence{

	/*
	 *Attributes
	 */
	private OperationEvent event;
	
	private Message message;
	
	private String operator;
	
	private String xmi;
	private boolean send;
	
	private ArrayList<Operand> operands;

	private Fragment start;
	private Fragment finish;
	private Fragment execution;
	
	private Interaction refersTo;

	private Lifeline covered;
	
	
	
	/*
	 *Constructor
	 */
	public Fragment(String name){
		super(name);
		if(name.contains("_MessageSend")){
			send = true;
			this.name = name.substring(0, name.indexOf("_MessageSend"));
		}
		operands = new ArrayList<Operand>();
		refersTo = null;
	}

	
	/*
	 *Set
	 */
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	
	public void setEvent( OperationEvent event ){
		this.event = event;
	}
	
	public void setMessage( Message message ){
		this.message = message;
	}
	

	/*
	 *Get
	 */
	public boolean isSend(){
		return this.send;
	}
	
	public OperationEvent getEvent(){
		return event;
	}
	
	public Message getMessage(){
		return message;
	}
	
	public String getXMI(){
		return xmi;
	}
	
	public String getOperator(){
		return this.operator;
	}
	
	public Lifeline getCovered(){
		return covered;
	}
	
	public Interaction getRefersTo(){
		return this.refersTo;
	}
	
	public ArrayList<Operand> getOperands(){
		return this.operands;
	}
	
	
	
	
	public void load(BufferedReader bf, String line) throws IOException{
		String value, key;
		if (line.contains("/>")){
			line = "</fragment>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</fragment>")) ; line = bf.readLine() ){
				if(line.contains("<fragment")){
					key = Tool.manipulate(line, "xmi:id");
					value = Tool.manipulate(line, "name=");
					Fragment fragment = new Fragment(value);
					Tool.putTrieFragment(key, fragment);
				}
			}
		}
	}
	
	/*
	 *Abstract Method of Super
	 */
	public void parser(BufferedReader bf, String line) throws IOException{
		String value, key;
		xmi = Tool.manipulate(line,"xmi:id=");
		type = Tool.manipulate(line, "xmi:type=");
		visibility = Tool.manipulate(line, "visibility=");
		//key = Tool.manipulate(line, "event=");
		//event = Tool.getTrieOperationEvent(key);
		key = Tool.manipulate(line, "message="); 
		message = Tool.getTrieMessage(key);
		key = Tool.manipulate(line, "covered=");
		covered = Tool.getTrieLifeline(key);
		operator = Tool.manipulate(line,"interactionOperator=");
		key = Tool.manipulate(line, "start=");
		start = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "start=");
		start = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "finish=");
		finish = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "execution=");
		execution = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "refersTo=");
		refersTo = Tool.getTrieInteraction(key);
		if (line.contains("/>")){
			line = "</fragment>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</fragment>")) ; line = bf.readLine() ){
				if(line.contains("<operand")){
					value = Tool.manipulate(line, "name=");
					Operand operand = new Operand(value);
					operand.parser(bf, line);
					operands.add(operand);
				}
			}
		}
	}


	public void printProp() {
		try{
			System.out.println("Fragment: " + this.name);
			System.out.println("\tType: " + this.type);
			//System.out.println("\tEvent: " + this.event.getName());
			System.out.println("\tOperator: " + this.operator);
			if(refersTo != null)
			System.err.println("\tRefer: " + refersTo.name);
			for(int i = 0; i < this.operands.size(); i++){
				this.operands.get(i).printProp();
			}
			this.message.printProp();
			//this.event.printProp();
			System.err.println("\trepresents " + this.covered.getRepresents().getUpperValue());
			
		} catch (NullPointerException e){}
	}


	
}