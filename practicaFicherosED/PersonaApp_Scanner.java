/*
 * TO-DO V0.05 
 * Implementar método para recoger parametros desde fichero Pacientes.txt 
 * del cliente seleccionado mediante DNI.
 */

package practicaFicherosED;

/**
 * Librería Scanner, para la introducción de<br>
 * datos por teclado.
 */
import java.util.Scanner;
import java.util.Date;

public class PersonaApp_Scanner {
	
	/**
	 * Método main
	 * @param args
	 */
	public static void main(String[] args) {
		//Acceso a menu inicial
		menuInicial();		
	}	
		/**
		 * Menu inicial, estrutura switch en la que
		 * dirigimos al usuario entre las diferentes
		 * opciones del software. 
		 */
	public static void menuInicial() {
		Scanner scOpcion = new Scanner(System.in);
		
		System.out.println("**********************\nSelecciona una opcion"
				+ "\n**********************"
				+ "\n1. Registrar nuevo paciente"
				+ "\n2. Registrar nueva visita"
				+ "\n3. Salir.");
		
		int seleccion = scOpcion.nextInt();
		
		switch(seleccion){
			case 1:
				registroPaciente();
			case 2:
				registroVisita();				
				break;
			case 3:
				System.out.println("Gracias por utilizar nuestro software");
				System.exit(0);
				break;
			default:
				System.out.println("Opcion no valida\n por favor,"
						+ " seleccione una opcion entre las disponibles");
				break;
			}
	}
	
	/**
	 * Registro de nuevos pacientes,
	 * campos requeridos por teclado:
	 * DNI, nombre, edad, calle, localidad, cod_postal
	 */
	public static void registroPaciente() {

		System.out.println("********************************\n"
				+ "Introduce datos de nuevo paciente"
				+ "\n********************************");
		System.out.println("Introduce el nombre");
		Scanner scNombre = new Scanner(System.in);
		String nombre = scNombre.nextLine();
		System.out.println("Introduce la edad");
		Scanner scEdad = new Scanner(System.in);
		int edad = scEdad.nextInt();
		System.out.println("Introduce el genero"
				+ "\nM. Masculino"
				+ "\nF. Femenino");
		Scanner scSexo = new Scanner(System.in);
		char sexo = scSexo.nextLine().charAt(0);
		System.out.println("Introduce la calle");
		Scanner scCalle = new Scanner(System.in);
		String calle = scCalle.nextLine();
		System.out.println("Introduce la localidad");
		Scanner scLocalidad = new Scanner(System.in);
		String localidad = scLocalidad.nextLine();
		System.out.println("Introduce el codigo postal");
		Scanner scCodPostal = new Scanner(System.in);
		String codPostal = scCodPostal.nextLine();
		boolean esVisita = false;
		/**
		 * Objeto paciente con parámetros 
		 * de nuevo paciente.
		 */
		Paciente pacienteNuevo = new Paciente(nombre, edad, calle, localidad,
				codPostal);
		/**
		 * Llamada a metodo grabarCliente
		 * Guardamos datos de nuevo Paciente en fichero Pacientes.txt
		 */
		TratamientoFichero.grabarCliente(pacienteNuevo,esVisita);
	}
	
	/**
	 * Registro de nuevas visitas,
	 * campos requeridos:
	 * DNI, fecha de la consulta, hora de la consulta, peso, unidad de
	 * peso, altura, unidad de altura, resultado de calcular el IMC.
	 */
	public static void registroVisita() {

		System.out.println("********************************\n"
				+ "Registro de nueva visita"
				+ "\n********************************");
		
		System.out.println("se muestra hora y fecha");
		System.out.println("Se toma el dni");
		Scanner scDni = new Scanner(System.in);
		String dni = scDni.nextLine();				
		if (TratamientoFichero.esDniRegistrado(dni)==true) {
			System.out.println("Introduce el peso");
			Scanner scPeso = new Scanner(System.in);
			double peso = scPeso.nextDouble();
			System.out.println("Introduce la altura");
			Scanner scAltura = new Scanner(System.in);
			double altura = scAltura.nextDouble();
			Date d = new Date();
		} else {
			System.out.println("El DNI introducido no se encuentra\n"
					+ "entre nuestros registros.\n"
					+ "Desea registrar a un nuevo paciente?"
					+ "\n1. Si"
					+ "\n2. No, volver a introducir DNI"
					+ "\n3. Volver a menu inicial");
			
			Scanner opcionRegistro = new Scanner(System.in);
			int seleccionRegistro = opcionRegistro.nextInt();
			switch (seleccionRegistro) {
				case 1:
					registroPaciente();
					break;
				case 2:
					registroVisita();
					break;
				case 3:
					menuInicial();
					break;
				default:
					System.out.println("Opcion no valida\n por favor,"
							+ "seleccione una opcion entre las disponibles");
					break;
				
				}
		}
			
		/*Paciente pacienteVisita = new Paciente(nombre,edad, sexo, peso,
				 altura, calle, localidad, codPostal)
		MuestraMensajePeso(pacienteVisita);*/
	}
	
	/**
	 * Este método determina el mensaje a mostrar en pantalla<br>
	 * referente al IMC del objeto persona.
	 * @param p
	 */
	public static void MuestraMensajePeso(Paciente p) {
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
