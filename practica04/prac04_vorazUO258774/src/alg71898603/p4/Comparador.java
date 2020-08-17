package alg71898603.p4;

import java.util.Comparator;

public class Comparador implements Comparator<Nodo>{
	@Override
	public int compare(Nodo o1, Nodo o2) {
		return o1.frecuencia-o2.frecuencia;
	}
}
