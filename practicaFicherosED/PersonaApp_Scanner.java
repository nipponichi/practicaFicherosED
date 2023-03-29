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
		Scanner scOpcion = new Scanner(System.in);
		boolean menuInicial = true;
		Paciente p = new Paciente();
		/**
		 * Introducción de datos por consola<br>
		 * para atributos nombre, edad, sexo y peso.
		 */
		
		do {
		System.out.println("**********************\nSelecciona una opcion"
				+ "\n**********************"
				+ "\n1. Registrar nuevo paciente"
				+ "\n2. Registrar nueva visita"
				+ "\n3. Salir.");
		
		int seleccion = scOpcion.nextInt();
		
		switch(seleccion){
			case 1:
				/**
				 * Registro de nuevos pacientes,
				 * campos requeridos por teclado:
				 * DNI, nombre, edad, calle, localidad, cod_postal
				 */
				System.out.println("Introducimos datos de nuevo paciente:");
				System.out.println("Introduce el nombre");
				Scanner scNombre = new Scanner(System.in);
				String nombre = scNombre.next();
				System.out.println("Introduce la edad");
				Scanner scEdad = new Scanner(System.in);
				int edad = scEdad.nextInt();
				System.out.println("Introduce la calle");
				Scanner scCalle = new Scanner(System.in);
				String calle = scCalle.nextLine();
				System.out.println("Introduce la localidad");
				Scanner scLocalidad = new Scanner(System.in);
				String localidad = scLocalidad.nextLine();
				System.out.println("Introduce el codigo postal");
				Scanner scCodPostal = new Scanner(System.in);
				String codPostal = scCodPostal.nextLine();
				/**
				 * Objeto paciente con parámetros 
				 * de nuevo paciente.
				 */
				Paciente paciente = new Paciente(nombre, edad, calle, localidad,
						codPostal);
				/**
				 * Llamada a metodo grabarCliente
				 * Guardamos datos de nuevo Paciente en fichero Pacientes.txt
				 */
				TratamientoFichero.grabarCliente(paciente);
				break;
			case 2:
				//Registro visitas
				break;
			case 3:
				System.out.println("Gracias por utilizar nuestro software");
				System.exit(0);
				break;
			default:
				System.out.println("Opcion no valida\n por favor,"
						+ "seleccione una opcion entre las disponibles");
				break;
			}
		
		} while (menuInicial = true);
		

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
	 * @param p
	 */

	public static void MuestraMensajeEdad(Persona p) {
		boolean mayoriaEdad = p.esMayorDeEdad();
		if (mayoriaEdad == true) {
			System.out.println("La persona es mayor de edad");
		}
		else {
			System.out.println("La persona es menor de edad");
		}
	}
}
