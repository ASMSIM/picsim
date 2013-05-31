public static void asm_rrf(Integer befehl, Prozessor cpu) {
		Integer f = getOpcodeFromToBit(befehl, 0, 6);
		Integer d = getOpcodeFromToBit(befehl, 7, 7);
		Integer result = cpu.getSpeicherzellenWert(f);
		
		if(cpu.getStatus(bits.C)){
			result += 0x100;
		}
		
		if((result & 0x01) == 1){
			cpu.setStatus(bits.C);
		}
		else{
			cpu.clearStatus(bits.C);
		}
		
		result = result >> 1;

		if(d == 1) {
			cpu.setSpeicherzellenWert(f, result, true);
		}
		else {
			cpu.setW(result, true);
		}
		cpu.incPC();
		erhoeheLaufzeit(cpu,1);
	}