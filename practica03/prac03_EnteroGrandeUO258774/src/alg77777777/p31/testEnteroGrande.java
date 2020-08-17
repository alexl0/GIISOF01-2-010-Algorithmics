package alg77777777.p31;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class testEnteroGrande {

	@Test
	void testMultiplicacionClasica() {
		//Prueba simple
		System.out.print("\n\n\n--------------------PRUEBA testMultiplicacionClasica()---------------------");
		EnteroGrande entero1= new EnteroGrande("0981");
		EnteroGrande entero2= new EnteroGrande("1234");
		EnteroGrande resultado= entero1.multiplicarDV(entero2);
		System.out.println("\nMult Entero1="+entero1+" * Entero2="+entero2+" = "+resultado.mostrarVista()+" ********************\n");
		Assert.assertEquals(0, resultado.compareTo(new EnteroGrande("1210554")));
		
		//Prueba compleja
		for (int i= 0; i<10; i++) {
			System.out.println("Prueba "+i);
			int c= 1000; // num cifras
			EnteroGrande ent1= EnteroGrande.generar(c);
			EnteroGrande ent2= EnteroGrande.generar(c);
			
			EnteroGrande res= ent1.multiplicarClasica(ent2);
			BigInteger bi1= new BigInteger(ent1.toString());
			BigInteger bi2= new BigInteger(ent2.toString());
			BigInteger bires= bi1.multiply(bi2);
			Assert.assertTrue(bires.toString().equals(res.toString()));
		}
		System.out.println("Fin prueba multiplicación clásica");
	}
	@Test
	void testMultiplicacionDV() {
		System.out.print("\n\n\n--------------------PRUEBA testMultiplicacionDV()---------------------");
		EnteroGrande ent1= new EnteroGrande(3333);
		EnteroGrande ent2= new EnteroGrande(4444);
		EnteroGrande res= ent1.multiplicarDV(ent2);
		Assert.assertEquals(0, res.compareTo(new EnteroGrande(Long.parseLong(ent1.toString())*Long.parseLong(ent2.toString()))));
		System.out.println("Fin prueba multiplicación DV. Sin errores.");
	}
	@Test
	void testCargarFicheroYMultiplicar() {
		System.out.print("\n\n\n--------------------PRUEBA testCargarFicheroYMultiplicar()---------------------");
		EnteroGrandePrueba.cargarFicheroYMultiplicar("src/files/pruebaFichero.txt");
		System.out.println("\nFin prueba testCargarFicheroYMultiplicar");
	}
}
