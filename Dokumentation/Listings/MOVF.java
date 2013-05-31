public static void asm_movf(Integer befehl, Prozessor cpu) {
		Integer w = cpu.getW();
		Integer f = getOpcodeFromToBit(befehl, 0, 6);
		Integer result = cpu.getSpeicherzellenWert(f);

		if(getOpcodeFromToBit(befehl, 7, 7) == 1) {
			cpu.setSpeicherzellenWert(f, result, true);
		}
		else {
			cpu.setW(result, true);
		}
		cpu.incPC();
		erhoeheLaufzeit(cpu,1);
	}