private void extractAssemblercode() {
		
		for(int i = 0; i < sourceLine.size(); i++) {

			if(!(sourceLine.get(i).charAt(0) == ' ')) {

				String dst = sourceLine.get(i).substring(5, 9);
				// System.out.println(dst);
				Integer befehl = (int) Integer.parseInt(dst, 16);
				// System.out.println(befehl);
				asmProg.add(befehl);

				command_source_line.put(asmProg.size() - 1, i);
			}

		}
	}