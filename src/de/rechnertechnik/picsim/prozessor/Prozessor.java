package de.rechnertechnik.picsim.prozessor;

public class Prozessor implements Runnable {

	private boolean stopProgram = true;
	private boolean breakpoint = false;

	public Prozessor() {

		// Thread runProgram = new Thread(this);
		// runProgram.start();
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

			
			befehlAuslesen();
			
			
			
		}

	}

	private void befehlAuslesen() {
		// TODO Auto-generated method stub
		
	}

}
