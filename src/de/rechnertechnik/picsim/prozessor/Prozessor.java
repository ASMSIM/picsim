package de.rechnertechnik.picsim.prozessor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.rechnertechnik.picsim.commands.PIC_Befehle;
import de.rechnertechnik.picsim.commands.CommandTable;
import de.rechnertechnik.picsim.commands.ECommands;
import de.rechnertechnik.picsim.logger.PIC_Logger;

public class Prozessor implements Runnable {

	private boolean stopProgram = false;
	private boolean breakpoint = false;
	private CommandTable cmdTable;
	private Speicher memory;

	/**
	 * Programmcounter
	 */
	private Integer pc = 0;

	public Prozessor(CommandTable cmdTable, Speicher memory) {
		this.cmdTable = cmdTable;
		this.memory = memory;

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

			Integer next = nextCommand();
			PIC_Logger.LOGGER.log(Level.INFO, "Next command: 0x"
					+ Integer.toHexString(next));

			// GOTO
			if(next >= cmdTable.getAssemblerCommand().get(ECommands.GOTO).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.GOTO).getTo()) {
					PIC_Logger.LOGGER.info("GOTO");
					PIC_Logger.LOGGER.info("Befehl: 0x"
							+ Integer.toHexString(next));
					Integer param = (next & 0x07FF);
					PIC_Logger.LOGGER.info("Parameter: 0x"
							+ Integer.toHexString(param));

					pc = PIC_Befehle.asm_goto(param, pc);
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.ADDWF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.ADDWF).getTo()) {
					System.out.println("ADDWF");
					// TODO ADDWF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.ANDWF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.ANDWF).getTo()) {
					System.out.println("ANDWF");
					// TODO ANDWF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.CLRF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.CLRF).getTo()) {
					System.out.println("CLRF");
					// TODO CLRF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.CLRW).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.CLRW).getTo()) {
					System.out.println("CLRW");
					// TODO CLRW
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.COMF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.COMF).getTo()) {
					System.out.println("COMF");
					// TODO COMF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.DECF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.DECF).getTo()) {
					System.out.println("DECF");
					// TODO DEC
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.DECFSZ).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.DECFSZ).getTo()) {
					System.out.println("DECFSZ");
					// TODO DECFSZ
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.INCF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.INCF).getTo()) {
					System.out.println("INCF");
					// TODO INCF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.INCFSZ).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.INCFSZ).getTo()) {
					System.out.println("INCFSZ");
					// TODO INCFSZ
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.IORWF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.IORWF).getTo()) {
					System.out.println("IORWF");
					// TODO IORWF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.MOVF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.MOVF).getTo()) {
					System.out.println("MOVF");
					// TODO MOVF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.MOVWF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.MOVWF).getTo()) {
					System.out.println("MOVWF");
					// TODO MOVWF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.NOP).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.NOP).getTo()) {
					System.out.println("NOP");
					// TODO NOP
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.RLF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.RLF).getTo()) {
					System.out.println("RLF");
					// TODO RLF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.RRF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.RRF).getTo()) {
					System.out.println("RRF");
					// TODO RRF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.SUBWF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.SUBWF).getTo()) {
					System.out.println("SUBWF");
					// TODO SUBWF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.SWAPF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.SWAPF).getTo()) {
					System.out.println("SWAPF");
					// TODO SWAPF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.XORWF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.XORWF).getTo()) {
					System.out.println("XORWF");
					// TODO XORWF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.BCF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.BCF).getTo()) {
					System.out.println("BCF");
					// TODO BCF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.BSF).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.BSF).getTo()) {
					System.out.println("BSF");
					// TODO BSF
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.BTFSC).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.BTFSC).getTo()) {
					System.out.println("BTFSC");
					// TODO BTFSC
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.BTFSS).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.BTFSS).getTo()) {
					System.out.println("BTFSS");
					// TODO BTFSS
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.ADDLW).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.ADDLW).getTo()) {
					System.out.println("ADDLW");
					// TODO ADDLW
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.ANDLW).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.ANDLW).getTo()) {
					System.out.println("ANDLW");
					// TODO ANDLW
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.CALL).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.CALL).getTo()) {
					System.out.println("CALL");
					// TODO CALL
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.CLRWDT).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.CLRWDT).getTo()) {
					System.out.println("CLRWDT");
					// TODO CLRWDT
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.IORLW).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.IORLW).getTo()) {
					System.out.println("IORLW");
					// TODO IORLW
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.MOVLW).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.MOVLW).getTo()) {
					System.out.println("MOVLW");
					// TODO MOVLW
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.RETFIE).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.RETFIE).getTo()) {
					System.out.println("RETFIE");
					// TODO RETFIE
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.RETLW).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.RETLW).getTo()) {
					System.out.println("RETLW");
					// TODO RETLW
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.RETURN).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.RETURN).getTo()) {
					System.out.println("RETURN");
					// TODO RETURN
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.SLEEP).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.SLEEP).getTo()) {
					System.out.println("SLEEP");
					// TODO SLEEP
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.SUBLW).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.SUBLW).getTo()) {
					System.out.println("SUBLW");
					// TODO SUBLW
				}
			}

			else if(next >= cmdTable.getAssemblerCommand().get(ECommands.XORLW).getFrom()) {
				if(next <= cmdTable.getAssemblerCommand().get(ECommands.XORLW).getTo()) {
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

		// System.out.println(this.memory.getZelle(0));
		return this.memory.getZelle(pc);
	}

}
