//Check Interrupt in Prozessor Klasse
interruptHandler.checkInterrupt(this);

//in der Interrupt Klasse
	public void checkInterrupt(Prozessor cpu) {

		Integer INTCON = cpu.get_RAM_Value(0x0b);
		boolean GIE = ((INTCON & 0x80) == 0x80) ? true : false;
		boolean INTE = ((INTCON & 0x10) == 0x10) ? true : false;
		boolean T0IE = ((INTCON & 0x20) == 0x20) ? true : false;

		System.out.println(Integer.toHexString(INTCON));
		
		// Global Interrupt enabled
		if(GIE) {

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