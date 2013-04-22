package de.rechnertechnik.picsim.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PIC_Logger {

	public final static Logger LOGGER = Logger.getLogger(PIC_Logger.class.getName());

	/**
	 * Init the Logger with File
	 */
	public static void initLogger(String filename) {

		Handler handler;
		try {

			handler = new FileHandler(filename, true);

			// No XML File for Log
			handler.setFormatter(new SimpleFormatter());

			LOGGER.addHandler(handler);
			LOGGER.setLevel(Level.INFO);

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
		LOGGER.setLevel(Level.INFO);
	}

}
