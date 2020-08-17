package alg71898603.p5;

import alg71898603.p5.Necklace;
import alg71898603.p5.NecklaceFactory;

public class NeckLaceTiempos {

	public static void main(String[] args) {
		for (int n = 10000; n < Integer.MAX_VALUE; n *= 2) {
			Necklace necklace = NecklaceFactory.createFourPiecesNecklace(n);
			System.out.print(n+"\t");
			long t1 = System.currentTimeMillis();
			necklace.appraisal();
			long t2 = System.currentTimeMillis();
			System.out.println(t2 - t1);
		}
	}
}
