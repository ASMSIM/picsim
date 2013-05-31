public static void asm_sublw(Integer akt_Befehl, Prozessor cpu) {
		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 7);

		// Get W
		Integer w = cpu.getW();

		// K - W
		Integer result = k - w;

		// Ergebnis in W
		cpu.setW(result, true);

		// PC++
		cpu.incPC();
		erhoeheLaufzeit(cpu,1);
	}
