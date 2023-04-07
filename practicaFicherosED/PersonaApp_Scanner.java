package practicaFicherosED;

/**
 * Librerías requeridas
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
				registroPaciente(null, false, true);
				break;
			case 2:
				registroVisita(null);				
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
		scSeleccion.close();
	} while (esMenu == true);
	}//menuInicial
	
	/**
	 * Registro de nuevos pacientes,
	 * campos requeridos por teclado:
	 * DNI, nombre, edad, calle, localidad, cod_postal
	 */
	public static void registroPaciente(String dni, boolean esVisita, boolean esPacienteNuevo) {
		System.out.println("********************************\n"
				+ "Introduce datos de nuevo paciente"
				+ "\n********************************");
		System.out.println("Introduzca el nombre");
		Scanner scNombre = new Scanner(System.in);
		String nombre = scNombre.nextLine();
		
		System.out.println("Introduzca la edad");
		Scanner scEdad = new Scanner(System.in);
		int edad = scEdad.nextInt();
		MuestraMensajeEdad(edad);
		
		System.out.println("Introduzca la calle");
		Scanner scCalle = new Scanner(System.in);
		String calle = scCalle.nextLine();

		System.out.println("Introduzca la localidad");
		Scanner scLocalidad = new Scanner(System.in);
		String localidad = scLocalidad.nextLine();
		
		System.out.println("Introduzca el codigo postal");
		Scanner scCodPostal = new Scanner(System.in);
		String codPostal = scCodPostal.nextLine();

		/**
		 * Objeto paciente con parámetros 
		 * de nuevo paciente.
		 */
		
		if (dni!= null && esVisita == false) {
			
			Paciente pacienteNuevo = new Paciente(nombre, edad, calle, localidad,
					codPostal,dni);
			/**
			 * Llamada a metodo grabarCliente
			 * Guardamos datos de nuevo Paciente en fichero Pacientes.txt
			 */
			TratamientoFichero.grabarCliente(pacienteNuevo,esVisita, esPacienteNuevo);
		} 
		
		else if (dni == null && esVisita == false) {
			
			Paciente pacienteNuevo = new Paciente(nombre, edad, calle, localidad,
					codPostal);
			/**
			 * Llamada a metodo grabarCliente
			 * Guardamos datos de nuevo Paciente en fichero Pacientes.txt
			 */
			TratamientoFichero.grabarCliente(pacienteNuevo,esVisita, esPacienteNuevo);
		}
		
		else if (dni!= null && esVisita == false && esPacienteNuevo == true){
			
			Paciente pacienteNuevo = new Paciente(nombre, edad, calle, localidad,
					codPostal,dni);
			TratamientoFichero.grabarCliente(pacienteNuevo,esVisita, esPacienteNuevo);
		}
		scNombre.close();
		scEdad.close();
		scCalle.close();
		scLocalidad.close();
		scCodPostal.close();

	}//registroPaciente
	
	/**
	 * Registro de nuevas visitas,
	 * campos requeridos:
	 * DNI, fecha de la consulta, hora de la consulta, peso, unidad de
	 * peso, altura, unidad de altura, resultado de calcular el IMC.
	 */
	@SuppressWarnings("resource")
	public static void registroVisita(String dni) {
		boolean esDniValido;
		String dniTeclado = dni;
		
		if (dni == null) {

			System.out.println("********************************\n"
					+ "Registro de nueva visita"
					+ "\n********************************");
		
			do {
				System.out.println("Inserte el dni del paciente: ");
		
				Scanner scDni = new Scanner(System.in);
				dniTeclado = scDni.nextLine().toUpperCase();
		
				esDniValido = TratamientoFichero.esDniValido(dniTeclado);

		
				if (esDniValido == false) {
					System.out.println("El dni introducido no es valido");
				}
			} while (esDniValido == false);
		}

		if (TratamientoFichero.esDniRegistrado(dniTeclado)==true) {
			boolean esVisita = true;
			boolean esPacienteNuevo = false;
			
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

			/**
			 * Declaracion objeto Paciente 
			 * para guardar visitas.
			 */
			Paciente pacienteVisita = new Paciente(dniTeclado, peso, altura, fecha,
					hora, dniTeclado);
			
			//Mostramos el resultado de la visita
			MuestraMensajePeso(pacienteVisita);
			
			//Guardamos la visita en fichero Visitas.txt
			TratamientoFichero.grabarCliente(pacienteVisita,esVisita, esPacienteNuevo);
		} 
		
		else {
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
					Paciente pacienteNuevo = new Paciente();
					pacienteNuevo.setDniTeclado(dniTeclado);
					registroPaciente(dniTeclado, false, true);
					break;
				case 2:
					registroVisita(null);
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
			
			} while (esMenu == true);
		}
	}//registroVisita
	
	/**
	 * Método para mostrar historial de visitas de paciente por consola
	 */
	@SuppressWarnings("resource")
	public static void historialPaciente() {
		boolean esDniValido;
		boolean esDniRegistrado;
		String dni;
		do {
		System.out.println("Inserte el dni del paciente: ");
		
		Scanner scDni = new Scanner(System.in);
		dni = scDni.nextLine().toUpperCase();
		
		esDniValido = TratamientoFichero.esDniValido(dni);
		esDniRegistrado = TratamientoFichero.esDniRegistrado(dni);
		
		if (esDniValido == false) {
			System.out.println("El dni introducido no es valido");
		} 
		
		else {
			
			if (esDniRegistrado == true) {
				System.out.println("Historial de paciente con Dni "+dni+":");
				TratamientoFichero.esHistorico(dni);
			} 
			
			else {
				System.out.println("El dni introducido no se"
						+ "encuentra en el registro de visitas");
			}
		}
		} while (esDniValido == false || esDniRegistrado == false);	
	}//historialPaciente
	
	/**
	 * Este método determina el mensaje a mostrar en pantalla<br>
	 * referente al IMC del objeto persona.
	 * @param p objeto Paciente
	 */
	public static void MuestraMensajePeso(Paciente p) {
		int IMC = p.calcularIMC();
		switch (IMC) {
	 		case Paciente.PESO_IDEAL:
	 			System.out.println("---------------------\nLa persona "
	 					+ "esta en su peso"
	 					+" ideal\n---------------------");
	 			break;
	 		case Paciente.INFRAPESO:
	 			System.out.println("---------------------\nLa persona "
	 					+ "esta por debajo de su"
	 					+" peso ideal\n---------------------");
	 			break;
	 		case Paciente.SOBREPESO:
	 			System.out.println("---------------------\nLa persona "
	 					+ "esta por encima de su"
	 					+" peso ideal\n---------------------");
	 			break;
		}
	}//MuestraMensajePeso
	
	/**
	 * Este método determina el mensaje a mostrar en pantalla
	 * referente a la mayoria de edad de la persona.
	 * @param p objeto Paciente
	 */
	public static void MuestraMensajeEdad(int edad) {
		Paciente p = new Paciente("", edad, "", "",
				"");
		boolean mayoriaEdad = p.esMayorDeEdad();
		
		if (mayoriaEdad == true) {
			System.out.println("--------------------\nLa persona "
					+ "es mayor de edad\n"
					+ "--------------------");
		}
		
		else {
			System.out.println("--------------------\nLa persona "
					+ "es menor de edad\n"
					+ "--------------------");
		}
	}//MuestraMensajeEdad
	
}
