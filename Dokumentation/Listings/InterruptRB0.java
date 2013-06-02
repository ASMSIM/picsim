private void checkExternalInterrupt(Prozessor cpu, Integer INTCON) {

		Integer PortB = cpu.get_RAM_Value(0x06);
		boolean RB0 = ((PortB & 0x01) == 0x01) ? true : false;

		Integer OPTION_REG = cpu.get_RAM_Value(0x81);
		boolean INTEDG = ((OPTION_REG & 0x40) == 0x40) ? true : false; // true =																		// rising
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