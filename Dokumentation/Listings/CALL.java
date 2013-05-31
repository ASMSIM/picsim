public static void asm_call(Integer akt_Befehl, Prozessor cpu) {

		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 10);

		// Push PC + 1 auf Stack
		Integer pc_inc = cpu.getPCValue() + 1;
		cpu.getStack().push(pc_inc);

		// PC auf K Wert setzen
		cpu.setPCL(k);
		erhoeheLaufzeit(cpu,2);

	}