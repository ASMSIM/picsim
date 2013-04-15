package de.rechnertechnik.picsim.main;


import de.rechnertechnik.picsim.parser.Parser;

public class Main {

	private static Parser parser;

	public static void main(String[] args) {

		System.out.println("#############################");
		System.out.println("######## PICSIMULATOR #######");
		System.out.println("#############################");

		parser = new Parser("files/BA_Test.LST");

	}
}
