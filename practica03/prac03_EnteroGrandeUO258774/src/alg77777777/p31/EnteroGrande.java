package alg77777777.p31;

import java.util.Random;

public class EnteroGrande implements Comparable
{
	private String valor;	// nos basamos en un String para almacenar las cifras
	// está pensado SOLO para enteros POSITIVOS

	/**
	 * Crea un entero grande a partir de un String
	 * @param entero
	 */
	public EnteroGrande(String entero) {
		this.valor= entero;
	}

	/**
	 * Crea un entero grande a partir de un long
	 * @param entero
	 */
	public EnteroGrande(long entero) {
		this.valor= Long.toString(entero);
	}

	/**
	 * Sustituye el valor del entero grande por uno aleatorio
	 */
	/**
	 * Genera un entero grande aleatorio con el número de cifras indicado
	 * @param numCifras número de cifras que tendrá el entero generado (la primera nunca será 0)
	 * @return EnteroGrande generado
	 */
	public static EnteroGrande generar(int numCifras) {
		StringBuffer numero= new StringBuffer();
		Random rnd= new Random();

		numero.append(rnd.nextInt(9)+1);	// la primera cifra siempre distinta de 0
		for (int i= 1; i<numCifras; i++) {
			numero.append(rnd.nextInt(10));
		}

		return new EnteroGrande(numero.toString());
	}

	/**
	 * Devuelve el número de cifras reales del EnteroGrande
	 * @return int
	 */
	public int getNumCifras() {
		return valor.length();
	}

	/**
	 * Devuelve un valor potencia de 2 en el que caben todas las cifras del EnteroGrande
	 * @return int - número de cifras potencia de 2
	 */
	public int getNumCifrasPot2() {
		// queremos aplicar log en base 2, pero no lo tenemos, 
		// así que hay que hacer un cambio de base
		// además, queremos encontrar el número más cercano por arriba
		return (int)Math.pow(2, Math.ceil(Math.log10(getNumCifras())/Math.log10(2)));

	}

	/**
	 * Devuelve la cifra que ocupa la posición i
	 * @param i
	 * @return
	 */
	private int getCifra(int i) {
		return Integer.parseInt(valor.substring(i,i+1));
	}

	@Override
	public String toString() {
		return valor;
	}

	/**
	 * Devuelve un String con el Entero Grande con los puntos de miles,
	 * Para facilitar la visualización de números grandes
	 */
	public String mostrarVista() {
		StringBuffer numeroPuntos= new StringBuffer();
		for (int i= getNumCifras()-1; i>=0; i--) {
			numeroPuntos.insert(0,getCifra(i));
			int valor= (i-getNumCifras());
			if (valor%3==0 && i!=0)
				numeroPuntos.insert(0, '.');
		}

		return numeroPuntos.toString();
	}

	/**
	 * Realiza una operación suma this + e2
	 * No importa el número de cifras que tengan this y e2, ni que estas sean diferentes
	 * Será siempre una suma de positivos
	 * @param e2 EnteroGrande - sumando2
	 * @return EnteroGrande - resultado de la suma
	 */
	public EnteroGrande sumar(EnteroGrande e2) {
		int n1= getNumCifras();	// cifras de e1
		int n2= e2.getNumCifras();	// cifras de e2
		int maxCifras= n1>n2 ? n1 : n2;

		StringBuffer suma= new StringBuffer();	// vamos guardando resultado final cifra a cifra
		StringBuffer sumaCifra;		// guardamos última cifra calculada
		int llevada= 0;	// Valor que llevamos de una cifra a otra

		int i= n1-1;
		int j= n2-1;
		for (int k= maxCifras-1; k>=0; k--, i--, j--) {  // iteramos mientras que a un sumando le queden cifras
			int c1;	// cifra sumando 1
			if (i>=0) c1 = getCifra(i);
			else c1= 0;	// ya no quedan cifras
			int c2; // cifra sumando 2
			if (j>=0) c2= e2.getCifra(j);
			else c2= 0;
			int res= c1+c2+llevada;
			sumaCifra= new StringBuffer(new Integer(res%10).toString());
			suma= sumaCifra.append(suma);
			if (res>=10) llevada= res/10;
			else llevada= 0;			
		}
		if (llevada>0) {
			sumaCifra= new StringBuffer(new Integer(llevada).toString());
			suma= sumaCifra.append(suma);
		}

		return new EnteroGrande(suma.toString());
	}

	/**
	 * Realiza la operación resta this-e2, sólo para restas ***positivas*** (this>e2)
	 * Elimina 0 por la izquierda antes de devolver el resultado
	 * @param e2 EnteroGrande sustraendo
	 * @return resultado de la resta
	 */
	public EnteroGrande restar(EnteroGrande e2) {
		int n1= getNumCifras();	// cifras de e1
		int n2= e2.getNumCifras();	// cifras de e2
		StringBuffer resta= new StringBuffer();	// vamos guardando resultado final cifra a cifra
		StringBuffer restaCifra;	// guardamos última cifra calculada
		int llevada= 0;	// Valor que llevamos de una cifra a otra

		int j= n2-1;
		for (int i= n1-1; i>=0; i--,j--) {
			int c1= getCifra(i);
			int c2;
			if (j>=0) c2= e2.getCifra(j);
			else c2= 0;
			int res;
			if (c1>=(c2+llevada)) {
				res= c1-(c2+llevada);
				llevada= 0;
			} else {
				res= c1+10-(c2+llevada);
				llevada= 1;
			}
			restaCifra= new StringBuffer(new Integer(res%10).toString());
			resta= restaCifra.append(resta);
		}
		if (llevada>0) {
			// error - no debería entrar por aquí
			System.err.println("Error resta negativa **************** "+this+" - "+e2);
		}
		if (resta.charAt(0)=='0')
			eliminarCerosIzq(resta);

		return new EnteroGrande(resta.toString());
	}


	/**
	 * Elimina caracteres 0 por la izquierda, para dejar la representación habitual del entero
	 * Esta operación es interna y trabaja sobre un StringBuffer, donde hace las modificaciones
	 * @param numero
	 */
	private void eliminarCerosIzq(StringBuffer numero) {
		// elimina ceros por la izquierda
		for (int i= 0; i<numero.length()-1; i++)
			if (numero.charAt(i)=='0') {
				numero.delete(i, i+1);
				i--;
			}
			else
				break;
	}

	/**
	 * Compara this con el argumento y devuelve -1, 1 o 0 según el resultado
	 * Primero compara el número de cifras (supone que no hay 0 por la izquierda)
	 * A igualdad del número de cifras va comparando cifra a cifra
	 * @param e2 EnteroGrande a comparar
	 * @return int 1 <- this>e2, -1 <- this<e2, 0 <- this==e2
	 */
	public int compareTo(EnteroGrande e2) {

		if (this.getNumCifras()>e2.getNumCifras())
			return 1;
		else
			if (this.getNumCifras()<e2.getNumCifras())
				return -1;
			else
				for (int i= 0; i<getNumCifras(); i++)
					if (this.getCifra(i)>e2.getCifra(i))
						return 1;
					else
						if (this.getCifra(i)<e2.getCifra(i))
							return -1;
		return 0;				
	}

	@Override
	public int compareTo(Object obj2) {
		return compareTo((EnteroGrande)obj2);
	}

	@Override
	public boolean equals(Object obj2) {
		return compareTo((EnteroGrande)obj2)==0;
	}

	/**
	 * Desplaza el número hacia la izquierda rellenando con 0
	 * Si el número es 0 no añade ceros innecesarios
	 * @param n número de posiciones que se desplaza
	 * @return EnteroGrande desplazado
	 */
	private EnteroGrande desplazarIzq(int n) {
		if (valor.equals("0"))
			return new EnteroGrande(0);

		StringBuffer relleno= new StringBuffer();
		for (int i= 0; i<n; i++)
			relleno.append("0");
		StringBuffer tmp = new StringBuffer(valor);
		tmp.append(relleno);

		return new EnteroGrande(tmp.toString());
	}

	/**
	 * Crea otro EnteroGrande a partir de la extracción de las cifras que van desde ini (inclusive) hasta fin (exclusive) 
	 * @param ini índice inicial (incluido) para extraer
	 * @param fin índice final (no incluido) para extraer
	 * @return
	 */
	private EnteroGrande extraerCifras(int ini, int fin) {
		return new EnteroGrande(valor.substring(ini,fin));
	}

	/**
	 * Suponiendo que el EnteroGrande tiene n cifras,
	 * extrae las n/2 cifras **iniciales** y lo devuelve como un EnteroGrande
	 * Puede ocurrir que este número concreto no tenga n cifras, entonces extraemos sólo las posibles
	 * y el resto las consideramos 0
	 * @param n número de cifras hipotético sobre el que extraemos la mitad
	 * @return
	 */
	private EnteroGrande extraerCifrasIniciales(int n) {
		int nCifrasIni= getNumCifras()-n/2;
		if (nCifrasIni<=0)
			return new EnteroGrande(0);
		else
			return extraerCifras(0,nCifrasIni);
	}

	/**
	 * Suponiendo que el EnteroGrande tiene n cifras,
	 * extrae las n/2 cifras **finales** y lo devuelve como un EnteroGrande
	 * Puede ocurrir que este número concreto no tenga n cifras, entonces extraemos sólo las posibles
	 * y el resto las consideramos 0
	 * @param n número de cifras hipotético sobre el que extraemos la mitad
	 * @return
	 */
	private EnteroGrande extraerCifrasFinales(int n) {
		int fin= getNumCifras();
		int ini= fin-n/2;
		if (ini<0) ini= 0;
		return extraerCifras(ini,fin);
	}

	/**
	 * Realiza una operación multiplicación entre dos EnterosGrandes this * e2 con el algoritmo clásico
	 * No importa el número de cifras que tengan this y e2, ni que estas sean diferentes
	 * Será siempre un producto de positivos
	 * @param e2 EnteroGrande - segundo factor
	 * @return EnteroGrande - devuelve el producto
	 */
	public EnteroGrande multiplicarClasica(EnteroGrande e2) {
		int numCifras= e2.getNumCifras();
		EnteroGrande[] aux= new EnteroGrande[numCifras];
		EnteroGrande resultado= new EnteroGrande(0);
		
		for (int i= numCifras-1; i>=0; i--) {
			int x= e2.getCifra(i);	// extraemos una cifra del multiplicador
			aux[numCifras-i-1]= multiplicarCifra(x);	// multiplicamos esa cifra por todas las del multiplicando
			aux[numCifras-i-1]= aux[numCifras-i-1].desplazarIzq(numCifras-i-1);
			resultado= resultado.sumar(aux[numCifras-i-1]);
		}
		
		return resultado;
	}
	
	private EnteroGrande multiplicarCifra(int n) {
		int numCifras= this.getNumCifras();
		StringBuffer aux= new StringBuffer();	// vamos guardando resultado final cifra a cifra
		int resto= 0;
		
		for (int i= numCifras-1; i>=0; i--) {
			int cifraI= this.getCifra(i);
			int prodParcial= cifraI * n + resto;
			aux.insert(0, ""+prodParcial%10);
			resto= prodParcial/10;
		}
		if (resto!=0) {
			aux.insert(0, ""+resto);			
		}
			
		
		return new EnteroGrande(aux.toString());
	}
	

	/**
	 * Realiza una operación multiplicación entre dos EnterosGrandes this * e2
	 * No importa el número de cifras que tengan this y e2, ni que estas sean diferentes
	 * Será siempre un producto de positivos
	 * @param e2 EnteroGrande - segundo factor
	 * @return EnteroGrande - devuelve el producto
	 */
	public EnteroGrande multiplicarDV(EnteroGrande e2) {
		return multiplicarDV(this,e2);
	}

	/**
	 * Multiplica 2 enteros grandes aplicando un algoritmo DV: e1 * e2
	 * Utilizando la optimización de Karatsuba
	 * @param e1 primer entero
	 * @param e2 segundo entero
	 * @return
	 */
	private EnteroGrande multiplicarDV(EnteroGrande e1, EnteroGrande e2)
	{
		int n= (int)Math.max(e1.getNumCifrasPot2(),e2.getNumCifrasPot2());
		if (n==1) {
			return e1.casoBase(e2);
		}
		else {
			EnteroGrande w= e1.extraerCifrasIniciales(n);
			EnteroGrande x= e1.extraerCifrasFinales(n);
			EnteroGrande y= e2.extraerCifrasIniciales(n);
			EnteroGrande z= e2.extraerCifrasFinales(n);
			EnteroGrande p= multiplicarDV(w,y);
			EnteroGrande q= multiplicarDV(x,z);
			EnteroGrande r= multiplicarDV(w.sumar(x),y.sumar(z));
			EnteroGrande sumaPyQ= p.sumar(q);
			EnteroGrande restaRySumaPyQ= r.restar(sumaPyQ);
			EnteroGrande pDesplazado= p.desplazarIzq(n);
			EnteroGrande restaRySumaPyQDesplazado= restaRySumaPyQ.desplazarIzq(n/2);
			EnteroGrande resultado= pDesplazado.sumar(q);
			return resultado.sumar(restaRySumaPyQDesplazado);
		}
	}

	private EnteroGrande casoBase(EnteroGrande e2) {
		return new EnteroGrande(Long.parseLong(this.toString())*Long.parseLong(e2.toString()));
	}

}
