package alg71898603.knapsack;

import alg71898603.p5.DynamicProgrammingCache;

public class KnapsackDemo {
	public static void main(String[] args) {
		
		
		DynamicProgrammingCache simple = new KnapsackCache(
				new String[] { "Objeto1" , "Objeto2", "Objeto3" }, 
				new int[] { 8, 5, 5 }, 
				new int[] { 6, 5, 5 }, 
				10);
		simple.updateTable();
		System.out.println(simple.toString());
		System.out.println(String.format("Max Value: %d\n", simple.getMaxValue()));
		for (String s : simple.getBestElements()) {
			System.out.println(s);
		}
		
		
		String[] labels = new String[] { "Objeto1" , "Objeto2", "Objeto3", "Objeto4", "Objeto5" };
		int[] values = new int[] { 90, 69, 41, 29, 22};
		int[] weights = new int[] { 9, 7, 4, 3, 2 };
		int max = 19;
		
		DynamicProgrammingCache cache = new KnapsackCache(labels, values, weights, max);
		cache.updateTable();
		System.out.println(cache.toString());
		System.out.println(String.format("Max Value: %d\n", cache.getMaxValue()));
		for (String s : cache.getBestElements()) {
			System.out.println(s);
		}
	}
}
