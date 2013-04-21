package de.rechnertechnik.picsim.main;


import de.rechnertechnik.picsim.parser.Parser;
import de.rechnertechnik.picsim.prozessor.Prozessor;

public class Main {

	private static Parser parser;
	private static Prozessor prozessor;

	public static void main(String[] args) {

		System.out.println("#############################");
		System.out.println("######## PICSIMULATOR #######");
		System.out.println("#############################");

		parser = new Parser("res/BA_Test.LST");
		prozessor = new Prozessor();
		
		
		

	}
}
