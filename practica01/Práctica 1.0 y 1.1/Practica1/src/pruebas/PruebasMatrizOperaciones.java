package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import labs.benchmarking.MatrizOperaciones;

class PruebasMatrizOperaciones {

	/**
	 * Prueba el constructor con parámetro int
	 */
	@Test
	void testMatrizOperacionesInt() {
		int tam=4;
		try {
			MatrizOperaciones m1 = new MatrizOperaciones(tam);
			for(int i=0;i<tam;i++) {
				for(int j=0;j<tam;j++) {
					Assertions.assertTrue(m1.getMat()[i][j]>=m1.getLimInf());
					Assertions.assertTrue(m1.getMat()[i][j]<=m1.getLimSup());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prueba el constructor con parámetro String
	 */
	@Test
	void testMatrizOperacionesString() {
		MatrizOperaciones m2 = new MatrizOperaciones("src/files/datos.txt");
		for(int i=0;i<m2.getTam();i++) {
			for(int j=0;j<m2.getTam();j++) {
				Assertions.assertEquals(m2.getMat()[i][j],4);
			}
		}
	}

	/*
	 * Prueba el método escribir:
	 * Crea una matriz de 4x4 con números aleatorios y la imprime por pantalla
	 */
	@Test
	void testEscribir() {
		int tam=4;
		try {
			MatrizOperaciones m1 = new MatrizOperaciones(tam);
			System.out.print("\ttestEscribir()\n");
			m1.escribir();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prueba el método recorrerCamino
	 */
	@Test
	void testRecorrerCamino() {
		MatrizOperaciones m1 = new MatrizOperaciones("src/files/datos2.txt");
		System.out.print("\tMatriz de la cual haremos el testRecorrerCamino\n");
		m1.escribir();
		System.out.print("\ttestRecorrerCamino()\n");
		m1.recorrerCamino(4, 0);
	}
	
}
