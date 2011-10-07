package utilities;

import gencode.Atributte;
import gencode.Interface;
import gencode.Method;
import gencode.Operation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import sequence.Fragment;
import sequence.Interaction;
import sequence.Lifeline;
import sequence.Message;
import sequence.OperationEvent;



public class Tool {
	
	/*
	 * Trie Structural 
	 */
	private static TreeMap<String, String> trieID = new TreeMap<String, String>();
	
	private static TreeMap<String, Method> trieMethod = new TreeMap<String, Method>(); 
	
	private static TreeMap< String, ArrayList<Method> > trieAbstractMethod = new TreeMap< String, ArrayList<Method> >();
	
	private static TreeMap<String, Atributte> trieAtributte = new TreeMap<String, Atributte>(); 
	
	private static TreeMap<String, Operation> trieOperation = new TreeMap<String, Operation>();
	
	private static TreeMap<String, Interface> trieInterface = new TreeMap<String, Interface>();
	
	/*
	 * Trie Sequence 				 
	 */
	private static TreeMap<String, Interaction> trieInteraction = new TreeMap<String, Interaction>(); 
	
	private static TreeMap<String, Lifeline> trieLifeline = new TreeMap<String, Lifeline>();
	
	private static TreeMap<String, Fragment> trieFragment = new TreeMap<String, Fragment>();

	private static TreeMap<String, Message> trieMessage = new TreeMap<String, Message>();
	
	private static TreeMap<String, OperationEvent> trieOperationEvent = new TreeMap<String, OperationEvent>();

	private static TreeMap<String, String> trieXMIfragment = new TreeMap<String, String>();
	
	
	/**
	 * loadXMI: First step, created trie need to forward references
	 * @param inputFileName
	 */
	public static void loadXMI(String inputFileName) throws IOException {
		String line, key, value;
		BufferedReader bf = new BufferedReader(new FileReader(inputFileName));
		while (bf.ready()) {
			line = bf.readLine();
			key = Tool.manipulate(line, "xmi:id");
			if (key != null) {
				value = Tool.manipulate(line, "name=");
				trieID.put(key, value);
			}
		}
	}
	
	/**
	 * manipulate: Return a substring of key from String str.
	 * 			   Delimited for String first and last
	 * @param str
	 * @param key
	 * @param first
	 * @param last
	 * @return String
	 */
	public static String manipulate(String str, String key, String first,
			String last) {

		int n = str.indexOf(key);
		if (n == -1) {
			return null;
		}
		int x = str.indexOf(first, n);
		int x1 = str.indexOf(last, x + 1);
		return str.substring(x + 1, x1);
	}
	
	/*
	 * manipulate: Return a substring of key from String str.
	 */
	public static String manipulate(String str, String key) {
		int n = str.indexOf(key);
		if (n == -1) {
			return null;
		}
		int x = str.indexOf("\"", n);
		int x1 = str.indexOf("\"", x + 1);
		return str.substring(x + 1, x1);
	}

	/*
	 * indentation: Return a string with \t * tab to indentation file
	 */
	public static String indentation(int tab){
		StringBuilder tabInd = new StringBuilder("\t");
		for(int i=0; i < tab - 1; i++){
			tabInd.append("\t");
		}
		return tabInd.toString();
	}
	
	/*
	 * Trie ID
	 */
	public static void putTrieID(String key, String value){
		trieID.put(key, value);
	}
	
	public static String getTrieID(String key){
		return trieID.get(key);
	}
	
	/*
	 * Trie Structural
	 */
	/*
	 * PUT
	 */
	public static void putTrieMetodo(String key, Method metodo){
		trieMethod.put(key, metodo);
	}
	
	public static void putTrieAtributte(String key, Atributte atributte){
		trieAtributte.put(key, atributte);
	}
	
	public static void putTrieOperation(String key, Operation operation){
		trieOperation.put(key,operation);
	}

	public static void putTrieInterface(String key, Interface inter) {
		trieInterface.put(key,inter);
	}
	
	public static void putTrieAbstractMethod(String name, ArrayList<Method> listAbstractMethod){
		trieAbstractMethod.put(name, listAbstractMethod);
	}

	public static void addTrieAbstractMethod(String name, Method abstractMethod){
		trieAbstractMethod.get(name).add(abstractMethod);
	}
	
	/*
	 * GET
	 */
	public static Method getTrieMetodo(String key){
		return key == null ? null : trieMethod.get(key);
	}
	
	public static Atributte getTrieAtributte(String key){
		return key == null ? null : trieAtributte.get(key);
	}
	
	public static Operation getTrieOperation(String key){
		return key == null ? null : trieOperation.get(key);
	}
	
	public static Interface getTrieInterface(String key) {
		return key == null ? null : trieInterface.get(key);
	}
	
	public static ArrayList<Method> getTrieAbstractMethod(String name){
		return trieAbstractMethod.get(name);
	}
	
	public static boolean containsKeyTrieAbstractMethod(String name){
		return trieAbstractMethod.containsKey(name);
	}
	
	/*
	 * Trie Sequence 
	 */
	/*
	 * PUT
	 */
	public static void putTrieOperationEvent(String key, OperationEvent operationEvent){
		trieOperationEvent.put(key, operationEvent);
	}

	public static void putTrieMessage(String key, Message message){
		trieMessage.put(key, message);
	}

	public static void putTrieFragment(String key, Fragment fragment){
		trieFragment.put(key, fragment);
	}
	
	public static void putTrieLifeline(String key, Lifeline lifeline){
		trieLifeline.put(key, lifeline);
	}
	
	public static void putTrieInteraction(String key, Interaction interaction){
		trieInteraction.put(key, interaction);
	}
	
	public static void putTrieXMIfragment(String key) {
		trieXMIfragment.put(key,key);
	}
	
	/*
	 * GET 
	 */
	public static OperationEvent getTrieOperationEvent(String key){
		return key == null ? null : trieOperationEvent.get(key);
	}

	public static Message getTrieMessage(String key){
		return key == null ? null : trieMessage.get(key);
	}

	public static Fragment getTrieFragment(String key){
		return key == null ? null : trieFragment.get(key);
	}
	
	public static Lifeline getTrieLifeline(String key){
		return key == null ? null : trieLifeline.get(key);
	}
	
	public static Interaction getTrieInteraction(String key){
		return key == null ? null : trieInteraction.get(key);
	}

	public static boolean containsTrieXMIfragment(String key) {
		return key == null ? false : trieXMIfragment.containsKey(key);
	}

}