package de.rechnertechnik.picsim.prozessor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.rechnertechnik.picsim.commands.PIC_Befehle;
import de.rechnertechnik.picsim.commands.CommandTable;
import de.rechnertechnik.picsim.commands.ECommands;
import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.register.SpecialFunctionRegister;
import de.rechnertechnik.picsim.register.Statusregister;

public class Prozessor implements Runnable {

	private boolean stopProgram = false;
	private boolean breakpoint = false;
	private CommandTable cmdTable;
	private Programmspeicher programmSpeicher;
	private Speicher ram;

	/**
	 * Programmcounter
	 */
	private Integer pc = 0;
	
	private SpecialFunctionRegister w = new SpecialFunctionRegister(0);
	private Statusregister status = new Statusregister(0);
	
	
	
	

	public Prozessor(CommandTable cmdTable, Programmspeicher programmSpeicher, Speicher ram) {
		this.cmdTable = cmdTable;
		this.programmSpeicher = programmSpeicher;
		this.ram = ram;

		Thread runProgram = new Thread(this);
		runProgram.start();
	}

	/**
	 * Enthält die Befehlsverarbeitunsschleife
	 */
	@Override
	public void run() {
		// TODO CREATE

		// INIT
		while(!stopProgram) {

			if(breakpoint) {
				// TODO
			}

			Integer akt_Befehl = nextCommand();

			PIC_Logger.LOGGER.log(Level.INFO, "Next command: 0x"
					+ Integer.toHexString(akt_Befehl));

			if(isIntegerCommand(ECommands.GOTO, akt_Befehl)) {
				PIC_Logger.LOGGER.info("GOTO");

				pc = PIC_Befehle.asm_goto(akt_Befehl);
			}

			else if(isIntegerCommand(ECommands.ADDWF, akt_Befehl)) {
				System.out.println("ADDWF");
				// TODO ADDWF
			}

			else if(isIntegerCommand(ECommands.ANDWF, akt_Befehl)) {
				System.out.println("ANDWF");
				// TODO ANDWF
			}

			else if(isIntegerCommand(ECommands.CLRF, akt_Befehl)) {
				System.out.println("CLRF");
				// TODO CLRF
			}

			else if(isIntegerCommand(ECommands.CLRW, akt_Befehl)) {
				System.out.println("CLRW");
			}
			
			else if(isIntegerCommand(ECommands.COMF, akt_Befehl)) {
				System.out.println("COMF");
			}
			
			else if(isIntegerCommand(ECommands.DECF, akt_Befehl)) {
				System.out.println("DECF");
			}
			
			else if(isIntegerCommand(ECommands.DECFSZ, akt_Befehl)) {
				System.out.println("DECFSZ");		
			}
			
			else if(isIntegerCommand(ECommands.INCF, akt_Befehl))	{
					System.out.println("INCF");
			}

			else if(isIntegerCommand(ECommands.INCFSZ, akt_Befehl)) {
					System.out.println("INCFSZ");
			}
	
			
			else if(isIntegerCommand(ECommands.IORWF, akt_Befehl)) {
					System.out.println("IORWF");
			}

			else if(isIntegerCommand(ECommands.MOVF, akt_Befehl)) {
					System.out.println("MOVF");
			}

			else if(isIntegerCommand(ECommands.MOVWF, akt_Befehl)) {
					System.out.println("MOVWF");
			}

			else if(isIntegerCommand(ECommands.NOP, akt_Befehl)) {
					System.out.println("NOP");
			}

			else if(isIntegerCommand(ECommands.RLF, akt_Befehl)) {
					System.out.println("RLF");
			}

			else if(isIntegerCommand(ECommands.RRF, akt_Befehl)) {
					System.out.println("RRF");
			}

			else if(isIntegerCommand(ECommands.SUBWF, akt_Befehl)) {
					System.out.println("SUBWF");
			}

			else if(isIntegerCommand(ECommands.SWAPF, akt_Befehl)) {
					System.out.println("SWAPF");
			}

			else if(isIntegerCommand(ECommands.XORWF, akt_Befehl)) {
					System.out.println("XORWF");
			}

			else if(isIntegerCommand(ECommands.BCF, akt_Befehl)) {
					System.out.println("BCF");
			}

			else if(isIntegerCommand(ECommands.BSF, akt_Befehl)) {
					System.out.println("BSF");
			}

			else if(isIntegerCommand(ECommands.BTFSC, akt_Befehl)) {
					System.out.println("BTFSC");
			}

			else if(isIntegerCommand(ECommands.BTFSS, akt_Befehl)) {
					System.out.println("BTFSS");
			}

			else if(isIntegerCommand(ECommands.ADDLW, akt_Befehl)) {
					System.out.println("ADDLW");
			}

			else if(isIntegerCommand(ECommands.ANDLW, akt_Befehl)) {
					System.out.println("ANDLW");
			}

			else if(isIntegerCommand(ECommands.CALL, akt_Befehl)) {
					System.out.println("CALL");
			}

			else if(isIntegerCommand(ECommands.CLRWDT, akt_Befehl)) {
					System.out.println("CLRWDT");
			}

			else if(isIntegerCommand(ECommands.IORLW, akt_Befehl)) {
					System.out.println("IORLW");
			}

			else if(isIntegerCommand(ECommands.MOVLW, akt_Befehl)) {
					System.out.println("MOVLW");
					PIC_Befehle.asm_movlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RETFIE, akt_Befehl)) {
					System.out.println("RETFIE");
			}

			else if(isIntegerCommand(ECommands.RETLW, akt_Befehl)) {
					System.out.println("RETLW");
			}

			else if(isIntegerCommand(ECommands.RETURN, akt_Befehl)) {
					System.out.println("RETURN");
			}

			else if(isIntegerCommand(ECommands.SLEEP, akt_Befehl)) {
					System.out.println("SLEEP");
			}

			else if(isIntegerCommand(ECommands.SUBLW, akt_Befehl)) {
					System.out.println("SUBLW");
			}

			else if(isIntegerCommand(ECommands.XORLW, akt_Befehl)) {
					System.out.println("XORLW");
			}

			else {
				System.out.println("Befehl nicht implementiert !");
				this.stopProgram = true;
			}

			try {
				// Zu Testzwecken -> Enter = nä Befehl!
				System.in.read();
			}
			catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	
	
	

	public SpecialFunctionRegister getW() {
		return w;
	}


	public Statusregister getStatus() {
		return status;
	}

	public void setStatus(Statusregister status) {
		this.status = status;
	}

	/**
	 * Testet, ob einen Integerwert auf einen bestimmten Befehl. Liefert true,
	 * wenn der Wert im Befehl liegt
	 * 
	 * @param testing_command
	 * @param intCommand
	 * @return
	 */
	private boolean isIntegerCommand(ECommands testing_command, Integer intCommand) {
		if(intCommand >= cmdTable.getAssemblerCommand().get(testing_command).getFrom()) {
			if(intCommand <= cmdTable.getAssemblerCommand().get(testing_command).getTo()) {
				return true;
			}
		}
		return false;

	}

	private Integer nextCommand() {
		// TODO PROGRAM DUMMY
		// READ COMMAND FROM FILE

		Integer next = this.programmSpeicher.getZelle(pc);
		// System.out.println(this.memory.getZelle(0));
		return next;
	}
}
