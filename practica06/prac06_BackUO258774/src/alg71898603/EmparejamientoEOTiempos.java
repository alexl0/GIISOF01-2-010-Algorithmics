package alg71898603;

public class EmparejamientoEOTiempos {

	/**
	 * 	//Crear matrices aleatorias sucesivamente de manera que la siguiente sea de dimensión igual a la dimensión
		//de la anterior más 1
	 * 	@param umbralDeRechazo
	 */
	public static void main(String[] umbralRechazo) {
		for(int i=0;i<Integer.MAX_VALUE;i++) {
			EmparejamientoEO.medirTiempos(umbralRechazo[0], i);
		}
	}

}
