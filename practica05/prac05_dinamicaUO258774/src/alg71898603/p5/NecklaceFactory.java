package alg71898603.p5;

import java.util.HashMap;
import java.util.Random;

public class NecklaceFactory {

	private static String randomNecklace(String[] components, int length) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++) {
			int pos = r.nextInt(components.length);
			sb.append(components[pos]);
		}
		return sb.toString();
	}


	public static Necklace createTwoPiecesNecklace(int length){
		//Diamond, Ruby
		return new Necklace(randomNecklace(new String[] { "D", "R" }, length));
	}

	public static Necklace createThreePiecesNecklace(int length){
		//Diamond, Ruby, Emerald
		return new Necklace(randomNecklace(new String[] { "D", "R", "E" }, length));
	}

	public static Necklace createFourPiecesNecklace(int length){
		//Diamond, Ruby, Emerald, Sapphire
		return new Necklace(randomNecklace(new String[] { "D", "R", "E", "S" }, length));
	}

	public static Necklace createFiveNecklace(int length){
		//Diamond, Ruby, Emerald, Sapphire, Topaz
		return new Necklace(randomNecklace(new String[] { "D", "R", "E", "S", "T" }, length));
	}

	public static HashMap<String, Integer> getSimpleValuesTable(){
		HashMap<String, Integer> values = new HashMap<String, Integer>();
		values.put("D", 2);
		values.put("R", 1);
		values.put("RR", 2);
		values.put("DD", 5);
		values.put("DDD", 8);
		values.put("RRR", 7);
		values.put("DDR", 14);


		return values;
	}

	public static HashMap<String, Integer> getDefaultValuesTable(){
		HashMap<String, Integer> values = new HashMap<String, Integer>();
		values.put("D", 2);
		values.put("R", 1);
		values.put("E", 1);
		values.put("S", 1);
		values.put("DD", 50);
		values.put("DDD", 800);
		values.put("RR", 30);
		values.put("RRR", 700);
		values.put("EE", 25);
		values.put("EEE", 200);
		values.put("SS", 4);
		values.put("SSS", 10);
		values.put("DRD", 80);
		values.put("DED", 80);
		values.put("DSD", 80);
		values.put("DDRRDD", 900);
		values.put("DDSSDD", 900);
		values.put("SRSRSRS", 1000);
		values.put("EDEDE", 500);
		values.put("DRESDRES", 8000);
		return values;
	}

	public static HashMap<String, Integer> getValuesTable(String filename){
		//Load from a file
		return null;
	}
}
