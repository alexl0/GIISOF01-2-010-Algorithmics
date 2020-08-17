package alg71898603.p5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Necklace {

	//Atributos
	private ArrayList<Integer> valores;
	private ArrayList<String> combinaciones;
	private int[][] precio;
	private String[] cadenas;
	private String collar;

	//Getters & Setters
	public ArrayList<Integer> getValores() {
		return valores;
	}
	public void setValores(ArrayList<Integer> valores) {
		this.valores = valores;
	}
	public ArrayList<String> getCombinaciones() {
		return combinaciones;
	}
	public void setCombinaciones(ArrayList<String> combinaciones) {
		this.combinaciones = combinaciones;
	}
	public int[][] getPrecio() {
		return precio;
	}
	public void setPrecio(int[][] precio) {
		this.precio = precio;
	}
	public String[] getCadenas() {
		return cadenas;
	}
	public void setCadenas(String[] cadenas) {
		this.cadenas = cadenas;
	}
	public String getCollar() {
		return collar;
	}
	public void setCollar(String collar) {
		this.collar = collar;
	}
	public String getMatriz() {
		StringBuffer matriz = new StringBuffer();
		for (int i = 0; i < precio.length; i++) {
			for (int j = 0; j < precio[0].length; j++)
				matriz.append(precio[i][j] + "\t");
			matriz.append("\n");
		}
		return matriz.toString();		
	}
	public String getVector() {
		StringBuffer vector = new StringBuffer();
		for (int i = 0; i < cadenas.length; i++) {
			vector.append(cadenas[i] + "\t");
		}
		return vector.toString();		
	}

	/**
	 * Constructor
	 * @param collar
	 */
	public Necklace(String collar) {
		this.collar=collar;
		valores = new ArrayList<Integer>();
		combinaciones = new ArrayList<String>();
		cargar("src/fichPrueba/case1.txt");
		this.precio = new int[combinaciones.size()][collar.length() + 1];
		this.cadenas = new String[collar.length() + 1];
	}

	/**
	 * Cargar de un fichero
	 * @param file
	 */
	public void cargar(String file) {
		String linea;
		String[] datos;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(file));
			while(fichero.ready()) {
				linea = fichero.readLine();
				datos = linea.split(" ");
				valores.add(Integer.parseInt(datos[1]));
				combinaciones.add(datos[0]);
			}
			fichero.close();
			System.out.println("Archivo cargado con éxito");
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("Archivo no encontrado");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		} 
	}

	/**
	 * Obtiene el valor de trasacción para un NeckLace
	 */
	public void appraisal() {
		for (int i = 0; i < precio.length; i++) {
			precio[i][0] = 0;
		}
		int anterior;
		int maximo;
		int nuevo;
		for (int j = 1; j < precio[0].length; j++) {
			String calcular = collar.substring(0, j);
			for (int i = 0; i < precio.length; i++) {
				String key = combinaciones.get(i);
				int nPrincipio = calcular.length() - key.length() >= 0 ? calcular.length() - key.length() : 0;
				int nFinal = calcular.length();

				String sCalcular = calcular.substring(nPrincipio, nFinal);
				if (sCalcular.equals(key))
					nuevo=valores.get(i);
				else
					nuevo=0;
				if(i==0)
					anterior=precio[precio.length-1][nPrincipio];
				else
					anterior=precio[i-1][j];
				maximo = precio[precio.length - 1][nPrincipio];
				precio[i][j] = Math.max(anterior, nuevo + maximo);
				if (precio[i][j] != anterior) {
					cadenas[j] = sCalcular;
				} else if (i == precio.length-1 && cadenas[j] == null)
					cadenas[j] = cadenas[j-1];
			}
		}
		getBestElements();
	}

	/**
	 * Obtiene una lista de las subdivisiones aplicadas
	 */
	private void getBestElements() {
		ArrayList<String> lista = new ArrayList<String>();
		int i = cadenas.length - 1;
		while (i > 0) {
			String aux = cadenas[i];
			lista.add(aux);
			i -= aux.length();
		}
		String[] cad = new String[lista.size()];
		for (int j = 0; j < cad.length; j++)
			cad[j] = lista.get(lista.size()-1-j);
		cadenas = cad;
	}


}
