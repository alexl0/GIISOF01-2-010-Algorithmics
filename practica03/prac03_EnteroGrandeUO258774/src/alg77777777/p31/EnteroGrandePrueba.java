package alg77777777.p31;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class EnteroGrandePrueba {

	public static void main(String[] args) {
		pruebaSimple();

		//Análisis empírico EnteroGrande
		/*
		for (int c=16;c<Integer.MAX_VALUE;c*=2) {//Hasta que el sistema lance una excepción
			EnteroGrande ent1= EnteroGrande.generar(c);
			EnteroGrande ent2= EnteroGrande.generar(c);
			EnteroGrande x=new EnteroGrande(0);
			long t1,t2;
			int nVeces=1;
			t1 = System.currentTimeMillis();
			for(int i=1;i<=nVeces;i++) {
				//x= ent1.multiplicarClasica(ent2);
				x= ent1.multiplicarDV(ent2);
			}
			t2 = System.currentTimeMillis();
			System.out.println ("Iteración: "+c+" Tiempo: "+(t2-t1)+" Resultado: "+x.toString());
			// Imprimimos el resultado porque a veces, el compilador de java relaiza unas optimizaciones,
			// y si no utilizamos la variable, directamente se ahorra de calcularla. Por eso hay que usarla.
		}
		*/
	cargarFicheroYMultiplicar("src/files/pruebaFichero.txt");

		System.out.println("Fin pruebas");
	}

	public static void pruebaSimple() {
		EnteroGrande ent1= new EnteroGrande("0981");
		EnteroGrande ent2= new EnteroGrande("1234");
		EnteroGrande res= ent1.multiplicarDV(ent2);
		System.out.println("Mult Entero1="+ent1+" * Entero2="+ent2+" = "+res.mostrarVista()+" ********************");
		if (res.compareTo(new EnteroGrande("1210554"))!=0)
			System.err.println("Error en la mult res= "+ res.mostrarVista() 
			+" / correcto= "+ new EnteroGrande("1210554").mostrarVista()+"********************");
	}

	public static void pruebaMultiplicacionClasica(EnteroGrande ent1, EnteroGrande ent2) {
		EnteroGrande res= ent1.multiplicarClasica(ent2);
		BigInteger bi1= new BigInteger(ent1.toString());
		BigInteger bi2= new BigInteger(ent2.toString());
		BigInteger bires= bi1.multiply(bi2);
		if (!bires.toString().equals(res.toString()))
			System.err.println("Error en la mult Entero1="+ent1+" * Entero2="+ent2+ "********************");
		System.out.println("Mult Entero1 * Entero2=\t"+res);
		System.out.println("BigInteger multiply=\t"+bires.toString());
	}

	private static void pruebaMultiplicacionDV(EnteroGrande ent1, EnteroGrande ent2) {
		EnteroGrande res= ent1.multiplicarDV(ent2);
		if (res.compareTo(new EnteroGrande(Long.parseLong(ent1.toString())*Long.parseLong(ent2.toString())))!=0)
			System.err.println("Error en la mult Entero1="+ent1+" * Entero2="+ent2+ "********************");
		System.out.println("Mult Entero1 * Entero2= \t"+res.mostrarVista());

	}

	public static void cargarFicheroYMultiplicar(String nombreFicheroEntrada) {
		String linea;
		String[] datos=null;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			int numMultiplicaciones=Integer.parseInt(fichero.readLine());
			for(int i=0;i<numMultiplicaciones;i++) {
				linea = fichero.readLine();
				datos = linea.split("\\*");//Los números están separados por "*". Se pone \\ porque * es un caracter reservado
				//En datos tenemos los dos números que hay que multiplicar
				EnteroGrande multiplicando=new EnteroGrande(datos[0]);
				EnteroGrande multiplicador=new EnteroGrande(datos[1]);
				System.out.println("\nMultiplicando: "+multiplicando.toString()+"\nMultiplicador: "+multiplicador.toString());
				System.out.println("\tResultado: "+multiplicando.multiplicarDV(multiplicador).toString());
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
}
