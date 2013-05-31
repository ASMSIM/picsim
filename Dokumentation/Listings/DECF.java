public static void asm_decf(Integer akt_Befehl, Prozessor cpu) {
		Integer f = getOpcodeFromToBit(akt_Befehl, 0, 6);
		Integer result = cpu.getSpeicherzellenWert(f) - 1;

		// Speicherort abfragen
		if(getOpcodeFromToBit(akt_Befehl, 7, 7) == 1) {

			// in f Register speichern
			cpu.setSpeicherzellenWert(f, result, true);

		}
		else {
			// in w Register speichern
			cpu.setW(result, true);
		}

		cpu.incPC();
		erhoeheLaufzeit(cpu,1);
	}