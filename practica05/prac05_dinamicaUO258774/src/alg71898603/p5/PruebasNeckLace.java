package alg71898603.p5;

import org.junit.jupiter.api.Test;
import alg71898603.p5.Necklace;
import alg71898603.p5.NecklaceFactory;

class PruebasNeckLace {

	@Test
	void pruebaNeckLace() {
		Necklace n1 = NecklaceFactory.createFourPiecesNecklace(20);
		n1.appraisal();
		System.out.println(n1.getCollar().toString()+"\n\n");
		System.out.println(n1.getMatriz()+"\n");
		System.out.println(n1.getVector());
	}
}
