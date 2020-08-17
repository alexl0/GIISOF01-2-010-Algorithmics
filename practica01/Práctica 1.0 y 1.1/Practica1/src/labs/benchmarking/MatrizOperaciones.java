package labs.benchmarking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class MatrizOperaciones {

	private static int[][]mat;
	private int limInf=4;	//Incluido
	private int limSup=10;	//Incluido

	public static void main (String arg [] ) {
		long t1,t2,t3,t4;
		double suma1, suma2;
		MatrizOperaciones m;
		try {
			m = new MatrizOperaciones(16000);
			t1=System.currentTimeMillis();
			suma1=m.sumarDiagonal1();
			t2=System.currentTimeMillis();
			System.out.println("Tiempo suma mal optimizada: "+(t2-t1));
			
			t3=System.currentTimeMillis();
			suma2=m.sumarDiagonal2();
			t4=System.currentTimeMillis();
			System.out.println("Tiempo suma bien optimizada: "+(t4-t3));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//get & set
	public int getLimInf() {
		return limInf;
	}
	public void setLimInf(int limInf) {
		this.limInf = limInf;
	}
	public int getLimSup() {
		return limSup;
	}
	public void setLimSup(int limSup) {
		this.limSup = limSup;
	}
	public int[][] getMat() {
		return mat;
	}
	public static void setMat(int[][] mat) throws Exception {
		//¿Es cuadrada?
		if(mat[0].length==mat.length) {
			//¿Todos los elementos son positivos?
			for(int i=0;i<mat[0].length;i++)
				for(int j=0;j<mat[0].length;j++)
					if(mat[i][j]<0)
						throw new Exception("La matriz tiene al menos un número <0");
			MatrizOperaciones.mat = mat;
		}
		else
			throw new Exception("La matriz no es cuadrada");
	}
	/**
	 * Devuelve el tamaño de la matriz.
	 */
	public int getTam() {
		return mat[0].length;
	}

	//Constructores
	/**
	 * Crea una matriz de tamaño nxn y la rellena con valores aleatorios
	 * estos valores aleatorios deben de ser parametrizables entre un máximo y un mínimo.
	 * @param n
	 * @throws Exception 
	 */
	public MatrizOperaciones(int n) throws IOException {
		if(n>0) {
			mat=new int[n][n];
			for (int i=0;i<n;i++) {
				for (int j=0;j<n;j++) {
					int numero = ThreadLocalRandom.current().nextInt(limInf, limSup +1);
					mat[i][j]=numero;
				}
			}
		}
		else
			throw new IOException("Error: tamaño menor que 1");
	}
	/**
	 * Crea una matriz a partir de los datos del fichero.
	 * El formato del fichero será: Primera línea, un entero con tamaño de la matriz (n),
	 * resto de las líneas, la fila correspondiente en la que cada valor estará separado por un
	 * tabulador del siguiente.
	 * @param nomFich
	 */
	public MatrizOperaciones(String nomFich) {
		String linea;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nomFich));
			linea = fichero.readLine();
			int tam=Integer.parseInt(linea);
			mat=new int[tam][tam];
			while (fichero.ready()) {
				for(int i=0;i<tam;i++) {
					linea = fichero.readLine();
					for(int j=0;j<tam;j++) {
						mat[i][j] = Integer.parseInt(linea.split("\t")[j]);//Las palabras están separadas por un tabulador
					}
				}
			}
			fichero.close();
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		} 
	}

	/**
	 * Muestra el contenido de la matriz por pantalla.
	 * Fallo: imprime un tabulador de más en cada línea
	 */
	public void escribir() {
		for (int i=0;i<getTam();i++) {
			for (int j=0;j<this.getTam();j++) {
				System.out.print(mat[i][j]+"\t");
			}
			System.out.println();//Salto de línea
		}
	}
	/**
	 * Calcula de forma iterativa la suma de la diagonal.
	 * Forma 1: recorrer toda la matriz, pero sólo sumando los elementos de la diagonal O(n^2)
	 */
	public double sumarDiagonal1() {
		double suma=0;
		for(int i=0;i<getTam();i++) {
			for(int j=0;j<getTam();j++) {
				if(i==j)//El if se hace n^2 veces
					suma+=mat[i][j];
			}
		}
		return suma;
	}
	/**
	 * Calcula de forma iterativa la suma de la diagonal.
	 * Forma 2: recorrer los elementos de la diagonal sumándolos O(n)
	 */
	public double sumarDiagonal2() {
		double suma=0;
		for(int i=0;i<this.getTam();i++) {
			suma+=mat[i][i];
		}
		return suma;
	}
	/**
	 * En una matriz cuyos valores varían entre 1 y 4 vamos a trazar un “camino” 
	 * partienendo de la posición (i,j) que pasamos como parámetro y utilizando 
	 * los valores de la matriz como códigos de dirección: 
	 * 1 - arriba, 2 - derecha, 3 - abajo, 4 – izquierda. 
	 * Vamos a utilizar para marcar el camino el código 
	 * -1. El proceso finalizará cuando el camino salga de los límites de la matriz 
	 * o bien alcance una casilla ya recorrida.
	 * @param i
	 * @param j
	 */
	public void recorrerCamino(int i, int j) {
		//variables auxiliares para almacenar las coordenadas en las que nos encontramos
		int posActualI=i;
		int posActualJ=j;

		//Para guardar las posiciones recorridas crearemos una matriz de igual tamaño con booleanos
		//y la inicializamos a flase
		int longitud=this.getTam();
		boolean visitado[][]=new boolean[longitud][longitud];
		for(int z=0;z<visitado.length;z++)
			for(int y=0;y<visitado.length;y++)
				visitado[z][y]=false;

		while(
				posActualI<this.getTam()
				&& posActualI>=0 		//Mientras no se salga de los límites
				&& posActualJ<this.getTam() 
				&& posActualJ>=0	//tanto positivos como negativos
				&& visitado[posActualI][posActualJ]!=true)			//y no haya sido visitada esa posición de la matriz
		{
			System.out.print("("+this.getMat()[posActualI][posActualJ]+")"+" ");
			visitado[posActualI][posActualJ]=true;
			switch(this.getMat()[posActualI][posActualJ]) {
			case 1:
				posActualI--;
				break;
			case 2:
				posActualJ++;
				break;
			case 3:
				posActualI++;
				break;
			case 4:
				posActualJ--;
				break;

			}
		}
	}



}
