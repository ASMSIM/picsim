public static void asm_decfsz(Integer akt_Befehl, Prozessor cpu) {

		Integer f = getOpcodeFromToBit(akt_Befehl, 0, 6); // zum speichern
		Integer result = cpu.getSpeicherzellenWert(f) - 1;
		// Speicherort abfragen
		if(getOpcodeFromToBit(akt_Befehl, 7, 7) == 1) {

			// in f Register speichern
			cpu.setSpeicherzellenWert(f, result, false);
		}
		else {
			// in w Register speichern
			cpu.setW(result, false);
		}

		// Result neu einlesen (evtl overflow)
		result = cpu.getSpeicherzellenWert(f);

		if(result >= 1) {
			cpu.incPC();
			erhoeheLaufzeit(cpu,1);
		}
		else if(result == 0) {
			cpu.incPC();
			cpu.incPC();
			erhoeheLaufzeit(cpu,2);
		}
	}