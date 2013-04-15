package de.rechnertechnik.picsim.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Michael
 * 
 */
public class Parser {

	// TODO ARRAYLIST?
	// AUS DATEI AUSLESEN, wie viel Zeilen vorhanden sind -> Array anpassen
	private String[] progLine = new String[200];
	private File file;
	
	
	/**
	 * Create Parser and init with File
	 * 
	 * @param String LST File
	 */
	public Parser(String pathname) {
		this.file = new File(pathname);
		try {
			readFile();
		} catch (FileNotFoundException e) {
			System.err.println("File: "+pathname+" not found!");
			e.printStackTrace();
		}
	}
	
	
	
	private void readLine() {
		// TODO Auto-generated method stub
	}
	
	
	public void readFile() throws FileNotFoundException{
		
		FileReader fileReader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(fileReader);
		
		try {
			
			while(buffReader.readLine() != null){
				System.out.println(buffReader.readLine());
			}
			
			
		} catch (IOException e) {
			System.err.println("Sorry I/O Error");
			e.printStackTrace();
		}
		
		
	
	}
	
	
	
	
	
	
}
