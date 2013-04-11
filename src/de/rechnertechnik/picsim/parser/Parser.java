package de.rechnertechnik.picsim.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Michael
 * 
 */
public class Parser {

	ArrayList<String> progLine = new ArrayList<String>();
	
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
		
		
		System.out.println("Debug DELME");
	}

	/**
	 * Reads single line and puts its Content into the progLine Array
	 * 
	 * @param content
	 */
	private void readLine(String content) {
		progLine.add(content);
	}

	public void readFile() throws FileNotFoundException {

		FileReader fileReader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(fileReader);
		String thisLine = "";

		try {
			while((thisLine = buffReader.readLine()) != null) {
				// TODO Insert Logger
				System.out.println(thisLine);

				// Reads Current Line and calls readline with its Content
				readLine(thisLine);
			}

		}
		catch(IOException e) {
			System.err.println("Sorry I/O Error");
			e.printStackTrace();
		}

	}

}
