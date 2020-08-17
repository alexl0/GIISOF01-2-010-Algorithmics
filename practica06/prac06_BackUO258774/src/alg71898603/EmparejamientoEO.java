package alg71898603;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class EmparejamientoEO {

	//Atributos
	private static int numParejas;
	private static int umbralRechazo;
	private static boolean emparejamientoPosible;
	private static int rechazo;
	private static int menorRechazo;
	private static int pareja[];
	private static int mejorPareja[];
	private static boolean emparejado[];
	private static int matHM[][];
	private static int matMH[][];

	public static void main (String umbralDeRechazo [] ){
		if(umbralDeRechazo.length==0) {
			System.out.println("Error, no se ha introducido umbralRechazo como parámetro");
		}
		else { 
			ejecutarBackTracking(umbralDeRechazo[0]); 
		}
	}

	/**
	 * Ejecuta backtracking
	 * @param umbralDeRechazo
	 */
	public static void ejecutarBackTracking(String umbralDeRechazo) {
		umbralRechazo=Integer.parseInt(umbralDeRechazo);
		numParejas=6;
		rechazo=0;
		emparejamientoPosible=false;
		menorRechazo=Integer.MAX_VALUE;
		emparejado=new boolean[numParejas];
		int i=1;
		while(i<numParejas) {
			emparejado[i]=false;
			i++;
		}
		pareja= new int[numParejas];
		mejorPareja= new int[numParejas];
		matHM = new int[numParejas][numParejas] ;
		matMH = new int[numParejas][numParejas] ;
		cargar("src/fichPrueba/rechazo06.txt");
		hombre(0);
		if (!emparejamientoPosible)
			System.out.println ("NO HAY EMPAREJAMIENTO POSIBLE");
		else{
			System.out.println ("EMPAREJAMIENTO ÓPTIMO POSIBLE:"); 
			int j=0;
			while(j<numParejas) {
				System.out.println ("hombre["+j+"] --- mujer["+mejorPareja[j]+"]");
				j++;
			}
			System.out.println ("PUNTUACIÓN TOTAL MÍNIMA="+menorRechazo);
		}
	}

	/**
	 * Ejecuta backtraking con una matriz de una dimensión +1 cada vez
	 * @param umbralDeRechazo
	 */
	public static void medirTiempos(String umbralDeRechazo, int nParejas) {
		umbralRechazo=Integer.parseInt(umbralDeRechazo);
		numParejas=nParejas;
		rechazo=0;
		emparejamientoPosible=false;
		menorRechazo=Integer.MAX_VALUE;
		emparejado=new boolean[numParejas];
		int i=1;
		while(i<numParejas) {
			emparejado[i]=false;
			i++;
		}
		pareja= new int[numParejas];
		mejorPareja= new int[numParejas];
		matHM = new int[numParejas][numParejas] ;
		matMH = new int[numParejas][numParejas] ;
		//cargar("src/fichPrueba/rechazo06.txt");
		
		//Medición de tiempos
		int SumaTotalTiempos=0;
		for(int x=0;x<10;x++) {
			generarMatrizAleatoria(matHM);
			generarMatrizAleatoria(matMH);
			long t1=System.currentTimeMillis();
			hombre(0);
			long t2=System.currentTimeMillis();
			SumaTotalTiempos+=(t2-t1);
		}
		int media=SumaTotalTiempos/10;
		System.out.println("Tamaño de problema (número de parejas que hay que hacer): "+numParejas+"; tiempo medio para 10 matrices distintas: "+(media));
		
		//Mostrar resultado del emparejamiento
		/*
		if (!emparejamientoPosible)
			System.out.println ("NO HAY EMPAREJAMIENTO POSIBLE");
		else{
			System.out.println ("EMPAREJAMIENTO ÓPTIMO POSIBLE:"); 
			int j=0;
			while(j<numParejas) {
				System.out.println ("hombre["+j+"] --- mujer["+mejorPareja[j]+"]");
				j++;
			}
			System.out.println ("PUNTUACIÓN TOTAL MÍNIMA="+menorRechazo);
		}
		*/
	}

	public static void  generarMatrizAleatoria (int[][]matriz){
		int dimension=matriz.length;
		Random numeroAleatorio= new Random();
		int i=0;
		while(i<dimension) {
			int j=0;
			while(j<dimension) {
				matriz[i][j]= numeroAleatorio.nextInt(11);
				j++;
			}
			i++;
		}
	}

	/**
	 * método auxiliar de ejecutarBackTracking()
	 * @param i 
	 */
	public static void hombre (int i){
		if (i==numParejas){ 
			emparejamientoPosible=true;
			if (rechazo<menorRechazo){
				int k=0;
				while(k<numParejas) {
					mejorPareja[k]=pareja[k];
					k++;
				}
				menorRechazo=rechazo;
			} 
		} 
		else {
			int j=0;
			while(j<numParejas) {
				if (!emparejado[j] && rechazo<menorRechazo && matMH[i][j]<=umbralRechazo && matHM[j][i]<=umbralRechazo){
					emparejado[j]=true;
					pareja[i]=j;
					rechazo=rechazo+matMH[i][j]+matHM[j][i];
					hombre(i+1);
					emparejado[j]=false;
					rechazo=rechazo-matMH[i][j]-matHM[j][i];
				} 
				j++;
			}
		}
	}

	/**
	 * Se utiliza para cargar los ficheros contenidos en el paquete fichPrueba en las matrizes hombre y mujer
	 * @param file
	 */
	public static void cargar(String file) {
		String linea;
		String[] datos;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(file));
			int nFilaArriba=0;
			int nFilaAbajo=0;
			linea = fichero.readLine();
			numParejas=Integer.parseInt(linea);
			boolean matrizDeArriba=true;
			matHM = new int[numParejas][numParejas] ;
			matMH = new int[numParejas][numParejas] ;
			while(fichero.ready()) {
				linea = fichero.readLine();
				if(linea.contains("\t")) {
					datos = linea.split("\t");
					for(int i=0;i<numParejas;i++)
						if(matrizDeArriba)
							matMH[nFilaArriba][i]=Integer.parseInt(datos[i]);
						else
							matHM[nFilaAbajo][i]=Integer.parseInt(datos[i]);
					if(matrizDeArriba)
						nFilaArriba++;
					else
						nFilaAbajo++;
				}
				else
					matrizDeArriba=false;
			}
			fichero.close();
			System.out.println("Archivo cargado con éxito.");
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		} 
	}


}
