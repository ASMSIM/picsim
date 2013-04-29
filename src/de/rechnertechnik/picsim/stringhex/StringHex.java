package de.rechnertechnik.picsim.stringhex;

public class StringHex {

	public static String intToHex(Integer value) {
		String intString = Integer.toHexString(value);
		System.out.println(intString.length());
		System.out.println(intString);

		Integer anzNullen = 4 - intString.length();

		for(int i = 0; i < anzNullen; i++) {
			intString = "0" + intString;
		}

		return intString;
	}

}
