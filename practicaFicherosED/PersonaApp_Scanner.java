/*
 * TO-DO V0.06
 * 
 * dni acepta cualquier dni valido pero deberia mostrar un "no se encuentra" cuando no exista en la lista
 * corregir lo del resultado del IMC
 */

package practicaFicherosED;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		//Acceso a menu inicial
		menuInicial();		
	}
	
	/**
	* Menu inicial, estrutura switch en la que
	* dirigimos al usuario entre las diferentes
	* opciones del software. 
	*/
	public static void menuInicial() {
		boolean esMenu = false;
		do {
		System.out.println("**********************\nSelecciona una opcion"
				+ "\n**********************"
				+ "\n1. Registrar nuevo paciente"
				+ "\n2. Registrar nueva visita"
				+ "\n3. Mostrar historial de paciente"
				+ "\n4. Salir.");
		Scanner scSeleccion = new Scanner(System.in);
		int seleccion = scSeleccion.nextInt();
		switch(seleccion){
			case 1:
				registroPaciente();
				break;
			case 2:
				registroVisita();				
				break;
			case 3:
				historialPaciente();
				break;
			case 4:
				System.out.println("Gracias por utilizar nuestro software");
				System.exit(0);
				break;
			default:
				esMenu = true;
				System.out.println("Opcion no valida\n por favor,"
						+ " seleccione una opcion entre las disponibles");
				break;
			}
	}while (esMenu == true);
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
		System.out.println("Introduzca el nombre");
		Scanner scNombre = new Scanner(System.in);
		String nombre = scNombre.nextLine();
		System.out.println("Introduzca la edad");
		Scanner scEdad = new Scanner(System.in);
		int edad = scEdad.nextInt();

		System.out.println("Introduzca la calle");
		Scanner scCalle = new Scanner(System.in);
		String calle = scCalle.nextLine();

		System.out.println("Introduzca la localidad");
		Scanner scLocalidad = new Scanner(System.in);
		String localidad = scLocalidad.nextLine();
		
		System.out.println("Introduzca el codigo postal");
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
		
		System.out.println("Introduzca el dni");
		Scanner scDni = new Scanner(System.in);
		String dni = scDni.nextLine();

		if (TratamientoFichero.esDniRegistrado(dni)==true) {
			boolean esVisita = true;
			
			System.out.println("Introduzca el peso en kilogramos");
			Scanner scPeso = new Scanner(System.in);
			double peso = scPeso.nextDouble();
			
			System.out.println("Inserte la altura en metros");
			Scanner scAltura = new Scanner(System.in);
			double altura = scAltura.nextDouble();
			
			DateFormat dFFecha = new SimpleDateFormat("d MMM yyyy");
			DateFormat dFHora = new SimpleDateFormat("HH:mm:ss");
			String fecha = dFFecha.format(Calendar.getInstance().getTime());
			String hora = dFHora.format(Calendar.getInstance().getTime());
			double imc = 0;

			/**
			 * Declaracion objeto Paciente 
			 * para guardar visitas.
			 */
			Paciente pacienteVisita = new Paciente(dni, peso, altura, fecha, hora, imc);
			pacienteVisita.calcularIMC();
			pacienteVisita.setDNI(dni);
			pacienteVisita.setPeso(peso);
			pacienteVisita.setAltura(altura);
			pacienteVisita.setFecha(fecha);
			pacienteVisita.setHora(hora);
			TratamientoFichero.grabarCliente(pacienteVisita,esVisita);
		} else {
			boolean esMenu = false;
			do {
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
					esMenu = true;
					System.out.println("Opcion no valida\n por favor,"
							+ "seleccione una opcion entre las disponibles");
					break;
				}
			}while (esMenu == false);
		}
	}
	
	/**
	 * Método para mostrar histórial de visitas de paciente por consola
	 */
	public static void historialPaciente() {
		boolean esDniValido;
		String dni;
		do {
		System.out.println("Inserte el dni del paciente: ");
		Scanner scDni = new Scanner(System.in);
		dni = scDni.nextLine();
		esDniValido = TratamientoFichero.esDniValido(dni);
		if (esDniValido == false) {
			System.out.println("El dni introducido no es valido");
		}
		}while (esDniValido == false);
		TratamientoFichero.esHistorico(dni);
	}
	
	/**
	 * Este método determina el mensaje a mostrar en pantalla<br>
	 * referente al IMC del objeto persona.
	 * @param p
	 */
	public static void MuestraMensajePeso(Paciente p) {
		int IMC = p.calcularIMC();
		switch (IMC) {
	 		case Paciente.PESO_IDEAL:
	 			System.out.println("La persona esta en su peso"
	 					+" ideal");
	 			break;
	 		case Paciente.INFRAPESO:
	 			System.out.println("La persona esta por debajo de su"
	 					+" peso ideal");
	 			break;
	 		case Paciente.SOBREPESO:
	 			System.out.println("La persona esta por encima de su"
	 					+" peso ideal");
	 			break;
		}
	}
	
	/**
	 * Este método determina el mensaje a mostrar en pantalla
	 * referente a la mayoria de edad de la persona.
	 * @param p objeto Persona
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
