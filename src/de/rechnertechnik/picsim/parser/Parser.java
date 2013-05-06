package de.rechnertechnik.picsim.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.rechnertechnik.picsim.logger.PIC_Logger;

/**
 * 
 * @author Michael
 * 
 */
public class Parser {

	ArrayList<String> sourceLine = new ArrayList<String>();
	ArrayList<Integer> asmProg = new ArrayList<Integer>();

	private File file;

	/**
	 * Create Parser and init with File
	 * 
	 * @param String
	 *            LST File
	 */
	public Parser(String pathname) {
		this.file = new File(pathname);
		try {
			readFile();
		}
		catch(FileNotFoundException e) {
			System.err.println("File: " + pathname + " not found!");
			e.printStackTrace();
		}

		extractAssemblercode();

	}

	/**
	 * Extracts the Assemblercode from the String
	 */
	private void extractAssemblercode() {

		for(String currentLine : sourceLine) {

			if(!(currentLine.charAt(0) == ' ')) {
				String dst = currentLine.substring(5, 9);
//				System.out.println(dst);
				Integer befehl = (int)Integer.parseInt(dst, 16);
				System.out.println(befehl);
				asmProg.add(befehl);
			}

		}
		
		PIC_Logger.LOGGER.info("Finished Parsing");
	}

	/**
	 * Reads single line and puts its Content into the progLine Arraylist
	 * 
	 * @param content
	 */
	private void fillArrayList(String content) {
		sourceLine.add(content);
	}

	public void readFile() throws FileNotFoundException {

		FileReader fileReader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(fileReader);
		String thisLine = "";

		try {
			while((thisLine = buffReader.readLine()) != null) {
				// TODO Insert Logger

				// Reads Current Line and calls readline with its Content
				fillArrayList(thisLine);
			}

		}
		catch(IOException e) {
			System.err.println("Sorry I/O Error");
			e.printStackTrace();
		}

	}

	public ArrayList<Integer> getAsmProg() {
		return asmProg;
	}

	public ArrayList<String> getSourceLine() {
		return sourceLine;
	}
	
	

}
