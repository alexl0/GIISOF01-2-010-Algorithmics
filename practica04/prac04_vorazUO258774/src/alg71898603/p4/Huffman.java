package alg71898603.p4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
	
	//Atributos
	private List<String> cadenas;
	private List<Integer> frecuencias;
	private Map<String, String>codes;
	private Nodo raiz;
	
	//Cargar de fichero
	public Huffman(String file) {
		cargar(file);
	}
	public void cargar(String file) {
		String linea;
		String[] datos;
		cadenas=new ArrayList<String>();
		frecuencias=new ArrayList<Integer>();
		codes=new HashMap<String,String>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(file));
			while(fichero.ready()) {
				linea = fichero.readLine();
				datos = linea.split(" ");
				cadenas.add(datos[0]);
				frecuencias.add(Integer.parseInt(datos[1]));
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
	
	public Map<String, String> getCodes(){
		return getCodes(raiz,"");
	}
	public Map<String, String> getCodes(Nodo n, String cadena){
		if(n.cadena!=null) {
			codes.put(n.cadena, cadena);
		}
		else {
			getCodes(n.izquierda,cadena+"0");
			getCodes(n.derecha,cadena+"1");
		}
		return codes;
	}

	public void run() {
		//Creamos una cola de prioridades ordenando elementos de mayor a menor frecuencia
		PriorityQueue<Nodo> colaPrioridad=new PriorityQueue<Nodo>(new Comparador());
		
		//Añadimos los primeros nodos
		for (int i=0;i<cadenas.size();i++) {
			Nodo nodo=new Nodo(cadenas.get(i),frecuencias.get(i));
			colaPrioridad.add(nodo);
		}
		while(colaPrioridad.size()>1) {
			Nodo n1=colaPrioridad.poll();
			Nodo n2=colaPrioridad.poll();
			
			raiz=new Nodo(n1,n2,null,n1.frecuencia+n2.frecuencia);
			colaPrioridad.add(raiz);
		}
		
	}

	
}
