package alg71898603.p4;

public class Nodo {
	
	//Atributos
	Nodo izquierda;
	Nodo derecha;
	String cadena;
	int frecuencia;
	
	//Getters y setters
	public Nodo getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Nodo izquierda) {
		this.izquierda = izquierda;
	}

	public Nodo getDerecha() {
		return derecha;
	}

	public void setDerecha(Nodo derecha) {
		this.derecha = derecha;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	//Constructores
	public Nodo(String cadena, int frecuencia) {
		this.cadena=cadena;
		this.frecuencia=frecuencia;
		this.izquierda=null;
		this.derecha=null;
	}
	public Nodo() {
		this.cadena=null;
		this.frecuencia=-1;
		this.izquierda=null;
		this.derecha=null;
	}
	public Nodo(Nodo izquierda, Nodo derecha, String cadena, int frecuencia) {
		super();
		this.izquierda = izquierda;
		this.derecha = derecha;
		this.cadena = cadena;
		this.frecuencia = frecuencia;
	}
	
}
