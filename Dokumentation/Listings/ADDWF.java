public static void asm_addwf(Integer akt_Befehl, Prozessor cpu) {

		// Komponenten auslesen
		Integer f = getOpcodeFromToBit(akt_Befehl, 0, 6);
		Integer w = cpu.getW();
		Integer result = cpu.getSpeicherzellenWert(f) + w;

		// Speicherort abfragen
		if(getOpcodeFromToBit(akt_Befehl, 7, 7) == 1) {
			// in f Register speichern
			cpu.setSpeicherzellenWert(f, result, true);
		}
		else {
			// in w Register speichern
			cpu.setW(result, true);
		}

		// PC ++
		cpu.incPC();
		erhoeheLaufzeit(cpu,1);
	}