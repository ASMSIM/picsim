private void interruptHasOccured(Prozessor cpu){

		Integer INTCON = cpu.get_RAM_Value(0x0b);
		
		cpu.setSpeicherzellenWert(0x0b, (INTCON & 0x7F), false); // Disable GIE

		Integer pcl = cpu.getPCValue(); // Programmcounter holen
		cpu.getStack().push(pcl); // PCL auf Stack

		cpu.setPCL(0x04); // Springe zum Interruptvektor	
	}