public static void asm_andlw(Integer akt_Befehl, Prozessor cpu) {

		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 7);

		// Hole W
		Integer w = cpu.getW();

		// AND
		cpu.setW((w & k), true);

		// PC++
		cpu.incPC();
		erhoeheLaufzeit(cpu,1);
	}