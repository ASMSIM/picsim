package de.rechnertechnik.picsim.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PIC_Logger {

	public static Logger logger = Logger.getLogger(PIC_Logger.class.getName());

	/**
	 * Init the Logger with File
	 */
	public static void initLogger(String filename) {
		
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
		
		Handler fileHandler;
		MyFormatter formatter = new MyFormatter();
		
		try {
			
			fileHandler = new FileHandler(filename, true);
			fileHandler.setFormatter(formatter);
			
			// No XML File for Log
			fileHandler.setLevel(Level.ALL);
			logger.addHandler(fileHandler);


			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(formatter);
			logger.addHandler(consoleHandler);
	        

		}
		catch(SecurityException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Init the Logger
	 */
	public static void initLogger() {
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
	}

}
