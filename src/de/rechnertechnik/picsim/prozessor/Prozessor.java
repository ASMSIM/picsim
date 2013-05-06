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
	private Speicherzelle status = ram.getStatus(true);
	
	
	
	
	
	
	

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


			//TODO !!! BEFEHLE ABÄNDERN!!!
			
			
			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.DECFSZ).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.DECFSZ).getTo()) {
					System.out.println("DECFSZ");
					// TODO DECFSZ
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.INCF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.INCF).getTo()) {
					System.out.println("INCF");
					// TODO INCF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.INCFSZ).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.INCFSZ).getTo()) {
					System.out.println("INCFSZ");
					// TODO INCFSZ
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.IORWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.IORWF).getTo()) {
					System.out.println("IORWF");
					// TODO IORWF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.MOVF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.MOVF).getTo()) {
					System.out.println("MOVF");
					// TODO MOVF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.MOVWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.MOVWF).getTo()) {
					System.out.println("MOVWF");
					// TODO MOVWF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.NOP).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.NOP).getTo()) {
					System.out.println("NOP");
					// TODO NOP
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RLF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RLF).getTo()) {
					System.out.println("RLF");
					// TODO RLF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RRF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RRF).getTo()) {
					System.out.println("RRF");
					// TODO RRF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.SUBWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.SUBWF).getTo()) {
					System.out.println("SUBWF");
					// TODO SUBWF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.SWAPF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.SWAPF).getTo()) {
					System.out.println("SWAPF");
					// TODO SWAPF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.XORWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.XORWF).getTo()) {
					System.out.println("XORWF");
					// TODO XORWF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.BCF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.BCF).getTo()) {
					System.out.println("BCF");
					// TODO BCF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.BSF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.BSF).getTo()) {
					System.out.println("BSF");
					// TODO BSF
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.BTFSC).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.BTFSC).getTo()) {
					System.out.println("BTFSC");
					// TODO BTFSC
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.BTFSS).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.BTFSS).getTo()) {
					System.out.println("BTFSS");
					// TODO BTFSS
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.ADDLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.ADDLW).getTo()) {
					System.out.println("ADDLW");
					// TODO ADDLW
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.ANDLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.ANDLW).getTo()) {
					System.out.println("ANDLW");
					// TODO ANDLW
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.CALL).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.CALL).getTo()) {
					System.out.println("CALL");
					// TODO CALL
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.CLRWDT).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.CLRWDT).getTo()) {
					System.out.println("CLRWDT");
					// TODO CLRWDT
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.IORLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.IORLW).getTo()) {
					System.out.println("IORLW");
					// TODO IORLW
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.MOVLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.MOVLW).getTo()) {
					System.out.println("MOVLW");
					PIC_Befehle.asm_movlw(akt_Befehl, this);
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RETFIE).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RETFIE).getTo()) {
					System.out.println("RETFIE");
					// TODO RETFIE
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RETLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RETLW).getTo()) {
					System.out.println("RETLW");
					// TODO RETLW
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RETURN).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RETURN).getTo()) {
					System.out.println("RETURN");
					// TODO RETURN
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.SLEEP).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.SLEEP).getTo()) {
					System.out.println("SLEEP");
					// TODO SLEEP
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.SUBLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.SUBLW).getTo()) {
					System.out.println("SUBLW");
					// TODO SUBLW
				}
			}

			if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.XORLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.XORLW).getTo()) {
					System.out.println("XORLW");
					// TODO XORLW
				}
			}

			// System.out.println("Befehl nicht implementiert !");
			// this.stopProgram = true;

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


	public Integer getStatus() {
		return status.getValue();
	}

	public void setStatus(Integer value) {
		try {
			this.status.setWert(value);
		}
		catch(MemoryOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
