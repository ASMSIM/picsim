package de.rechnertechnik.picsim.prozessor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.rechnertechnik.picsim.commands.PIC_Befehle;
import de.rechnertechnik.picsim.commands.CommandTable;
import de.rechnertechnik.picsim.commands.ECommands;
import de.rechnertechnik.picsim.logger.PIC_Logger;
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

			
			
			
			// GOTO
			if(akt_Befehl>= cmdTable.getAssemblerCommand().get(ECommands.GOTO).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.GOTO).getTo()) {
					PIC_Logger.LOGGER.info("GOTO");

					pc = PIC_Befehle.asm_goto(akt_Befehl);
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.ADDWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.ADDWF).getTo()) {
					System.out.println("ADDWF");
					// TODO ADDWF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.ANDWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.ANDWF).getTo()) {
					System.out.println("ANDWF");
					// TODO ANDWF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.CLRF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.CLRF).getTo()) {
					System.out.println("CLRF");
					// TODO CLRF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.CLRW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.CLRW).getTo()) {
					System.out.println("CLRW");
					// TODO CLRW
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.COMF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.COMF).getTo()) {
					System.out.println("COMF");
					// TODO COMF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.DECF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.DECF).getTo()) {
					System.out.println("DECF");
					// TODO DEC
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.DECFSZ).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.DECFSZ).getTo()) {
					System.out.println("DECFSZ");
					// TODO DECFSZ
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.INCF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.INCF).getTo()) {
					System.out.println("INCF");
					// TODO INCF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.INCFSZ).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.INCFSZ).getTo()) {
					System.out.println("INCFSZ");
					// TODO INCFSZ
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.IORWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.IORWF).getTo()) {
					System.out.println("IORWF");
					// TODO IORWF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.MOVF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.MOVF).getTo()) {
					System.out.println("MOVF");
					// TODO MOVF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.MOVWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.MOVWF).getTo()) {
					System.out.println("MOVWF");
					// TODO MOVWF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.NOP).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.NOP).getTo()) {
					System.out.println("NOP");
					// TODO NOP
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RLF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RLF).getTo()) {
					System.out.println("RLF");
					// TODO RLF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RRF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RRF).getTo()) {
					System.out.println("RRF");
					// TODO RRF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.SUBWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.SUBWF).getTo()) {
					System.out.println("SUBWF");
					// TODO SUBWF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.SWAPF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.SWAPF).getTo()) {
					System.out.println("SWAPF");
					// TODO SWAPF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.XORWF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.XORWF).getTo()) {
					System.out.println("XORWF");
					// TODO XORWF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.BCF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.BCF).getTo()) {
					System.out.println("BCF");
					// TODO BCF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.BSF).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.BSF).getTo()) {
					System.out.println("BSF");
					// TODO BSF
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.BTFSC).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.BTFSC).getTo()) {
					System.out.println("BTFSC");
					// TODO BTFSC
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.BTFSS).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.BTFSS).getTo()) {
					System.out.println("BTFSS");
					// TODO BTFSS
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.ADDLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.ADDLW).getTo()) {
					System.out.println("ADDLW");
					// TODO ADDLW
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.ANDLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.ANDLW).getTo()) {
					System.out.println("ANDLW");
					// TODO ANDLW
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.CALL).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.CALL).getTo()) {
					System.out.println("CALL");
					// TODO CALL
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.CLRWDT).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.CLRWDT).getTo()) {
					System.out.println("CLRWDT");
					// TODO CLRWDT
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.IORLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.IORLW).getTo()) {
					System.out.println("IORLW");
					// TODO IORLW
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.MOVLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.MOVLW).getTo()) {
					System.out.println("MOVLW");
					// TODO MOVLW
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RETFIE).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RETFIE).getTo()) {
					System.out.println("RETFIE");
					// TODO RETFIE
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RETLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RETLW).getTo()) {
					System.out.println("RETLW");
					// TODO RETLW
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.RETURN).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.RETURN).getTo()) {
					System.out.println("RETURN");
					// TODO RETURN
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.SLEEP).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.SLEEP).getTo()) {
					System.out.println("SLEEP");
					// TODO SLEEP
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.SUBLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.SUBLW).getTo()) {
					System.out.println("SUBLW");
					// TODO SUBLW
				}
			}

			else if(akt_Befehl >= cmdTable.getAssemblerCommand().get(ECommands.XORLW).getFrom()) {
				if(akt_Befehl <= cmdTable.getAssemblerCommand().get(ECommands.XORLW).getTo()) {
					System.out.println("XORLW");
					// TODO XORLW
				}
			}

			else {
				System.out.println("Befehl nicht implementiert !");
				this.stopProgram = true;
			}

			try {
				System.in.read();
			}
			catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private Integer nextCommand() {
		// TODO PROGRAM DUMMY
		// READ COMMAND FROM FILE

		Integer next = this.programmSpeicher.getZelle(pc);
		// System.out.println(this.memory.getZelle(0));
		return next;
	}
}
