/*
	JAVA es sensible a min�sculas y mayusculas (min�scula != may�scula)
	Es norma que una clase comience por letra may�scula.
	Los m�todos y todo tipo de variables comienzan por min�scula.

	Las clases JAVA se guardan en ficheros con el mismo nombre
	al que se a�ade la extensi�n Nombre.java.

	Los paquetes deben estar en directorio del mismo nombre, esto es,
	el paquete alg77777777.p1 debe estar en el directorio
	alg77777777\p1. 
 */

package p1 ;

import java.util.Random ; //es la clase que genera n�meros aleatorios

/** Esta clase permite trabajar con vectores */
public class Vector2
{
	static int []v;

	public static void main (String arg [] )
	{
		int n= Integer.parseInt(arg[0]);  //tama�o del problema leido de l�nea de comandos

		v = new int [n] ;
		
		rellena (v);
		escribe (v);

		long t1=System.currentTimeMillis();//La toma de tiempos sp en una variable tipo long
		int s=suma (v);
		long t2=System.currentTimeMillis();
		long t=t2-t1;
		System.out.println ("Suma de los elementos del vector = "+ s+"\nTiempo suma = "+t);

		int [] m = new int [2];
		maximo (v,m);
		System.out.println ("Valor del m�ximo del vector = "+ m[1]);
		System.out.println ("Posici�n del m�ximo del vector = "+ m[0]);

	} // fin de main


	/** Este m�todo da valores aleatorios a un vector de enteros, 
		utiliza para ello la clase Random del paquete java.util  
	 **/
	public static void rellena (int[]a)
	{
		Random r= new Random ();
		int n= a.length;
		for(int i=0;i<n;i++)
			a[i]=r.nextInt (199)-99;//valores entre -99 y 99

	}  // fin de rellena   


	/** Este m�todo saca el contenido por pantalla
	 **/
	public static void escribe (int[]a)
	{
		int n= a.length;
		for (int i=0; i<n; i++ )
			System.out.println ("Elemento "+i+" = "+a[i]);
		System.out.println();

	}  // fin de escribe   


	/** Este m�todo suma los elementos de un vector y devuelve el resultado
	 */
	public static int suma (int[]a)
	{
		int s=0;
		int n= a.length;
		for (int i=0;i<n;i++) s=s+a[i];
		return s;

	}  // fin de suma


	/**
	 * Este m�todo calcula el m�ximo y su posici�n y devuelve los dos valores.
	 */
	public static void maximo (int[]a, int[]m)
	{
		int n= a.length;
		m[0]=0; 	// posici�n inicial
		m[1]= a[0];	// primer elemento como referencia
		for (int i=1;i<n;i++)
			if (a[i]>m[1]) 
			{
				m[0]=i;
				m[1]=a[i];
			}

	}  // fin de maximo

}  // fin de clase