package de.rechnertechnik.picsim.prozessor;

import de.rechnertechnik.picsim.logger.PIC_Logger;

/**
 * Stellt die Funktionen des Interrupts dar
 * 
 * Enthält Methoden zum Prüfen und Ausführen des Timer0 Interrupts und des
 * Externen Interrupts
 * 
 * @author michael
 * 
 */
public class Interrupt {

	//Speichere alten Wert zur Flankenerkennung
	private boolean oldValue = false;

	/**
	 * Überprüft, ob ein Interrupt stattgefunden hat
	 * @param cpu
	 */
	public void checkInterrupt(Prozessor cpu) {

		Integer INTCON = cpu.get_RAM_Value(0x0b);
		boolean GIE = ((INTCON & 0x80) == 0x80) ? true : false;
		boolean INTE = ((INTCON & 0x10) == 0x10) ? true : false;
		boolean T0IE = ((INTCON & 0x20) == 0x20) ? true : false;

		System.out.println(Integer.toHexString(INTCON));

		// Global Interrupt enabled
		if(GIE) {

			PIC_Logger.logger.info("Interrupt Global aktiv");
			PIC_Logger.logger.info(Integer.toHexString(INTCON));

			// RB0 Interrupt enabled
			if(INTE) {
				PIC_Logger.logger.info("RB0 Interrupt check...");
				checkExternalInterrupt(cpu, INTCON);
			}

			// Timer0 Interrupt enabled
			if(T0IE) {
				PIC_Logger.logger.info("TMR0 Interrupt check...");
				checkTimer0Interrupt(cpu, INTCON);
			}

		}

	}

	
	/**
	 * Prüft auf Timer0 Interrupt
	 * 
	 * @param cpu
	 * @param iNTCON
	 */
	private void checkTimer0Interrupt(Prozessor cpu, Integer INTCON) {
		boolean T0IF = getBitValue(INTCON, 2);

		// Is Interrupt?
		if(T0IF) {
			// Timerinterrupt
			// Interrupt hat stattgefunden
			PIC_Logger.logger.info("TMR0 Interrupt hat stattgefunden");
			interruptHasOccured(cpu);

		}

	}

	/**
	 * Prüft auf externen Interrupt
	 * 
	 * @param cpu
	 * @param INTCON
	 */
	private void checkExternalInterrupt(Prozessor cpu, Integer INTCON) {

		Integer PortB = cpu.get_RAM_Value(0x06);
		boolean RB0 = ((PortB & 0x01) == 0x01) ? true : false;

		Integer OPTION_REG = cpu.get_RAM_Value(0x81);
		boolean INTEDG = ((OPTION_REG & 0x40) == 0x40) ? true : false; // true =
																		// rising,
																		// false
																		// =
																		// faling
																		// edge

		// Found rising edge
		if(oldValue == false && RB0 == true && INTEDG) {
			PIC_Logger.logger.info("Interrupt Rising");
			externerInterruptRB0(cpu, INTCON);
		}

		// Found falling edge
		else if(oldValue == true && RB0 == false && !INTEDG) {
			PIC_Logger.logger.info("Interrupt Falling");
			externerInterruptRB0(cpu, INTCON);
		}

		// No Interrupt
		else {
		}

		// Save old Value
		oldValue = RB0;
	}

	/**
	 * Wird aufgerufen, wenn ein externer Interrupt stattgefunden hat
	 * 
	 * @param cpu
	 * @param INTCON
	 * @param RB0
	 */
	private void externerInterruptRB0(Prozessor cpu, Integer INTCON) {

		// Interrupt hat stattgefunden
		PIC_Logger.logger.info("RB0 Interrupt hat stattgefunden");

		cpu.setSpeicherzellenWert(0x0b, (INTCON | 0x02), false); /*
																 * Interrupt
																 * Flag setzen
																 */

		interruptHasOccured(cpu);
	}

	/**
	 * Wird aufgerufen, nachdem ein Interrupt stattgefunden hat
	 * 
	 * @param cpu
	 */
	private void interruptHasOccured(Prozessor cpu) {

		Integer INTCON = cpu.get_RAM_Value(0x0b);

		cpu.setSpeicherzellenWert(0x0b, (INTCON & 0x7F), false); // Disable GIE

		Integer pcl = cpu.getPCValue(); // Programmcounter holen
		cpu.getStack().push(pcl); // PCL auf Stack

		cpu.setPCL(0x04); // Springe zum Interruptvektor

	}

	
	private static boolean getBitValue(Integer value, Integer bitNr) {
		return ((value & (1 << bitNr)) == (1 << bitNr)) ? true : false;
	}
}
