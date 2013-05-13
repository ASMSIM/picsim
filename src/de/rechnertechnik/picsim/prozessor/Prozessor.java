package de.rechnertechnik.picsim.prozessor;

import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.rechnertechnik.picsim.commands.PIC_Befehle;
import de.rechnertechnik.picsim.commands.BefehlAdressraumZuordnung;
import de.rechnertechnik.picsim.commands.ECommands;
import de.rechnertechnik.picsim.gui.IGUI;
import de.rechnertechnik.picsim.gui.IProzessor;
import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.parser.Parser;
import de.rechnertechnik.picsim.register.SpecialFunctionRegister;
import de.rechnertechnik.picsim.register.Statusregister;

public class Prozessor implements Runnable, IProzessor {

	private boolean stopProgram = false;
	private boolean breakpoint = false;
	private BefehlAdressraumZuordnung cmdTable;
	private Programmspeicher programmSpeicher;
	private Speicher ram;
	private IGUI gui;

	/**
	 * Programmcounter
	 */
	private Speicherzelle pc;
	private Speicherzelle status;
	private SpecialFunctionRegister w = new SpecialFunctionRegister(0);
	private Parser parser;
	private Stack<Integer> stack = new Stack<Integer>();
	
	
	

	public Prozessor(BefehlAdressraumZuordnung cmdTable, Programmspeicher programmSpeicher, Speicher ram, IGUI gui, Parser parser) {
		this.cmdTable = cmdTable;
		this.programmSpeicher = programmSpeicher;
		this.ram = ram;
		this.gui = gui;
		this.parser = parser;

		status = ram.getStatus(true);
		pc = ram.getPCL(true);

		// Init und Fokus auf 1. Zeile
		gui.showSourcecode(parser.getSourceLine(), parser.getCommand_source_line().get(0));

		Thread runProgram = new Thread(this);
		runProgram.start();
	}

	/**
	 * Enthält die Befehlsverarbeitunsschleife
	 */
	@Override
	public void run() {

		// INIT
		while(!stopProgram) {

			while(breakpoint) {
				try {
					Thread.sleep(200);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}

			Integer akt_Befehl = nextCommand();

			PIC_Logger.logger.log(Level.INFO, "Next command: 0x"
					+ Integer.toHexString(akt_Befehl));

			
			//GOTO
			if(isIntegerCommand(ECommands.GOTO, akt_Befehl)) {
				System.out.println("GOTO");
				setPCL(PIC_Befehle.asm_goto(akt_Befehl));
			}

			//ADDWF
			else if(isIntegerCommand(ECommands.ADDWF, akt_Befehl)) {
				System.out.println("ADDWF");
				
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
				PIC_Logger.logger.info("-> CLRW");
				PIC_Befehle.asm_clrw(akt_Befehl, this);
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

			else if(isIntegerCommand(ECommands.INCF, akt_Befehl)) {
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
				PIC_Befehle.asm_movwf(akt_Befehl, this);
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
				PIC_Befehle.asm_bcf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.BSF, akt_Befehl)) {
				System.out.println("BSF");
				PIC_Befehle.asm_bsf(akt_Befehl, this);
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
				PIC_Befehle.asm_call(akt_Befehl, this);
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
				PIC_Befehle.asm_sublw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.XORLW, akt_Befehl)) {
				System.out.println("XORLW");
				PIC_Befehle.asm_xorlw(akt_Befehl, this);
			}

			else {
				System.out.println("Befehl nicht implementiert !");
				this.stopProgram = true;
			}

			breakpoint = true;
		}

	}

	public void incPC() {
		setPCL(pc.getValue() + 1);
	}

	public Speicher getRam() {
		return ram;
	}

	public SpecialFunctionRegister getW() {
		return w;
	}

	public Speicherzelle getStatus() {
		return status;
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

	
	
	public Stack<Integer> getStack() {
		return stack;
	}
	
	
	public Speicherzelle getPc() {
		return pc;
	}
	
	/**
	 * Sets the PCL
	 * 
	 * @param value
	 */
	private void setPCL(Integer value) {

		try {
			pc.setWert(value);
		}
		catch(MemoryOutOfRangeException e) {
			System.err.println("Upps Memory Overflow!");
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


	/**
	 * Holt den naechsten Befehl, auf den der PCL zeigt
	 * @return
	 */
	private Integer nextCommand() {
		Integer next = programmSpeicher.getZelle(pc.getValue()).getValue();

		gui.setFocus(parser.getCommand_source_line().get(pc.getValue()));

		return next;
	}

	@Override
	public void nextStep() {
		breakpoint = false;
	}
}
