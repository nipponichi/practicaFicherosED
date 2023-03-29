package practicaFicherosED;

/**
 * Librería Scanner, para la introducción de<br>
 * datos por teclado.
 */
import java.util.Scanner;

public class PersonaApp_Scanner {
	
	/**
	 * Método main
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/**
		 * Introducción de datos por consola<br>
		 * para atributos nombre, edad, sexo y peso.
		 */
		System.out.println("Introduce el nombre");
		String nombre = sc.next();
		System.out.println("Introduce la edad");
		int edad = sc.nextInt();
		System.out.println("Introduce el sexo");
		char sexo = sc.next().charAt(0);
		System.out.println("Introduce el peso");
		double peso = sc.nextDouble();
		System.out.println("Introduce la altura");
		double altura = sc.nextDouble();
		
		/**
		 * Declaración de objeto persona en clase main
		 */
		Persona persona1 = new Persona();
		Persona persona2 = new Persona(nombre, edad, sexo);
		Persona persona3 = new Persona(nombre, edad, sexo, peso, altura);
		
		/**
		 * Parametros objeto persona1
		 */
		persona1.setNombre("Laura");
		persona1.setEdad(30);
		persona1.setSexo('M');
		persona1.setPeso(60);
		persona1.setAltura(1.60);
		
		/**
		 * Parámetros objeto persona2
		 */
		persona2.setPeso(90.5);
		persona2.setAltura(1.80);
		
		/**
		 * Imprime por consola<br>
		 * parametros y resultados<br> 
		 * para objeto persona1
		 */
		System.out.println("Persona1");
		MuestraMensajePeso(persona1);
		MuestraMensajeEdad(persona1);
		System.out.println(persona1.toString());
		
		/**
		 * Imprime por consola<br>
		 * parametros y resultados<br> 
		 * para objeto persona2
		 */
		System.out.println("Persona2");
		MuestraMensajePeso(persona2);
		MuestraMensajeEdad(persona2);
		System.out.println(persona2.toString());
 
		/**
		 * Imprime por consola<br>
		 * parametros y resultados<br> 
		 * para objeto persona3
		 */
		System.out.println("Persona3");
		MuestraMensajePeso(persona3);
		MuestraMensajeEdad(persona3);
		System.out.println(persona3.toString());
		
		sc.close();
	}
 
	/**
	 * Este método determina el mensaje a mostrar en pantalla<br>
	 * referente al IMC del objeto persona.
	 * @param p
	 */
	public static void MuestraMensajePeso(Persona p) {
		int IMC = p.calcularIMC();
		switch (IMC) {
	 		case Persona.PESO_IDEAL:
	 			System.out.println("La persona esta en su peso"
	 					+" ideal");
	 			break;
	 		case Persona.INFRAPESO:
	 			System.out.println("La persona esta por debajo de su"
	 					+" peso ideal");
	 			break;
	 		case Persona.SOBREPESO:
	 			System.out.println("La persona esta por encima de su"
	 					+" peso ideal");
	 			break;
		}
	}
	/**
	 * Este método determina el mensaje a mostrar en pantalla
	 * referente a la mayoria de edad de la persona.
	 * @param p2
	 */

	public static void MuestraMensajeEdad(Persona p2) {
		boolean mayoriaEdad = p2.esMayorDeEdad();
		if (mayoriaEdad == true) {
			System.out.println("La persona es mayor de edad");
		}
		else {
			System.out.println("La persona es menor de edad");
		}
	}
}
