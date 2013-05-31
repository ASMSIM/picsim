public static void asm_btfss(Integer akt_Befehl, Prozessor cpu) {

		Integer f = getOpcodeFromToBit(akt_Befehl, 0, 6);
		Integer bitNr = getOpcodeFromToBit(akt_Befehl, 7, 9);

		Integer k = cpu.getSpeicherzellenWert(f);
		boolean bitIsSet = getBit(k, bitNr);

		// Bit is set
		if(bitIsSet) {
			asm_nop(akt_Befehl, cpu);
		}
		// Bit not set
		else {
			// Nothing
		}

		// PC++
		cpu.incPC();
		erhoeheLaufzeit(cpu,1);

	}