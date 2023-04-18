package practicaFicherosED;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TratamientoFichero {
	
	static String rutaPaciente = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src"
			+ "\\almacenamiento\\Pacientes.txt";
	static String rutaVisita = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src"
			+ "\\almacenamiento\\Visitas.txt";

	/**
	 * Método de escritura de archivos: Pacientes.txt y Visitas.txt.
	 */
	public static void grabarCliente(Paciente p,boolean esVisita, boolean esPacienteNuevo) {
		String ruta = null;
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		//Comprobamos si registraremos paciente o visita
		if (esVisita != true) {
			ruta = rutaPaciente;
		} 
		
		else {
			ruta = rutaVisita;
		}	
		
		/**
		 * Metodo de editar el archivo, si el fichero existe, se edita, si no,
		 * capturamos la excepcion.
		 */
		try {
			/**
			 * Se añade flag a la edición del archivo para no sobreescribir los datos ya guardados.
			 */
			fichero = new FileWriter(ruta, true);
			pw = new PrintWriter(fichero);
			/**
			 * Se comprueba si el DNI ha sido introducido por teclado o
			 * necesitamos usar el metodo generarDNI() de clase Persona
			 */
			
			//Si no es visita y el dni es recogido por teclado
			if (esVisita != true && p.getDniTeclado() != null) {
				pw.println(p.getDniTeclado()+","+p.getNombre()+","+p.getEdad()+","+p.getCalle()+","
						+ ""+p.getLocalidad()+","+p.getCodPostal());
				System.out.println("++++++++++++++++++++++++++++++++++++++++\nRegistro de nuevo paciente con "
						+ "dni "+p.getDniTeclado()+" ha sido guardado con exito.\n"
								+ "++++++++++++++++++++++++++++++++++++++++");
			
			//Si no es visita y el dni no es recogido por teclado
			} 
			
			else if (esVisita != true && p.getDniTeclado() == null) {
				pw.println(p.getDNI()+","+p.getNombre()+","+p.getEdad()+","+p.getCalle()+","
						+ ""+p.getLocalidad()+","+p.getCodPostal());
				System.out.println("++++++++++++++++++++++++++++++++++++++++\nRegistro de nuevo paciente con "
						+ "dni "+p.getDNI()+" ha sido guardado con exito.\n"
								+ "++++++++++++++++++++++++++++++++++++++++");
			
			//Si es visita
			} 
			
			else {
				pw.println("DNI: "+p.getDniTeclado()+", Fecha: "+p.getFecha()+
					", Hora:"+p.getHora()+", Peso: "+p.getPeso()+"Kgs, Altura: "
							+ ""+p.getAltura()+"m, IMC: "+p.calcularIMC());
				System.out.println("++++++++++++++++++++++++++++++++++++++++\nRegistro de visita de paciente con "
						+ "dni "+p.getDniTeclado()+" ha sido guardado con exito.\n"
								+ "++++++++++++++++++++++++++++++++++++++++");
			}
			
		} 
		
		catch (Exception e) {
			System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
			e.printStackTrace();
			/**
			 * Método para cerrar la edicion del archivo, si el fichero tiene contenido, se cierra, si no,
			 * capturamos la excepción.
			 */
			
		} 
		
		finally {
			try {
				if (null != fichero) {
					fichero.close();
				}
			} 
			
			catch (Exception e2) {
				System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
				e2.printStackTrace();
			}
		} 
		// Si el paciente es continuamos registrando la visita inicial
		if (esPacienteNuevo == true && p.getDniTeclado() != null) {
			System.out.println("Se procede a registrar la visita inicial");
			PersonaApp_Scanner.registroVisita(p.getDniTeclado());
		}
		
		else if (esPacienteNuevo == true && p.getDniTeclado() == null) {
			System.out.println("Se procede a registrar la visita inicial");
			PersonaApp_Scanner.registroVisita(p.getDNI());
			
		}
		
		else {
			PersonaApp_Scanner.menuInicial();
		}

	}//grabarCliente
	
	/**
	 * Método de busqueda de DNI en archivo Pacientes.txt
	 * @param dni de tipo String.
	 * @return true o false si el DNI se encuentra en el archivo Pacientes.txt
	 */
	public static boolean esDniRegistrado (String dni) {
		String ruta = rutaPaciente;
		File f = new File(ruta);
		Scanner s;
			/**
			 * Método para tomar valores de los campos
			 * establecidos por el delimitador ','.
			 */
			try {
				s = new Scanner(f);
				while (s.hasNextLine()) {
					String linea = s.nextLine();
					Scanner sl = new Scanner(linea);
					sl.useDelimiter("\\s*,\\s*");
					//Comprobamos si hay un DNI igual
					if (sl.next().equals(dni)) {
						sl.close();
						return true;
					}
					sl.close();
				}
				s.close();
			} 
			
			catch (FileNotFoundException e) {
				System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
				e.printStackTrace();
		}
			return false;		
	} //esDniRegistrado
	
	/**
	 * Comprobamos si el cliente ha sido registrado en Visitas.txt
	 * y mostramos su evolucion histórica en la clínica.
	 * @param dni
	 */
	@SuppressWarnings("resource")
	public static void esHistorico (String dni) {
		String ruta = rutaVisita;
		File f = new File(ruta);
		Scanner s;
			/**
			 * Método para tomar valores de los campos
			 * establecidos por el delimitador ','.
			 */
			try {
				s = new Scanner(f);
				while (s.hasNextLine()) {
					String linea = s.nextLine();
					Scanner slIf = new Scanner(linea);
					Scanner slWhile = new Scanner(linea);
					slIf.useDelimiter("\\s*,\\s*");
					slWhile.useDelimiter("\\s*,\\s*");
					/**
					 * Mostramos datos de fichero Visitas.txt si se encuentra el
					 * dni introducido por teclado en MAIN.
					 */
					while (slWhile.hasNext() && slWhile.next().equals("DNI: "+dni)) {
						System.out.print(slWhile.next()+", ");
						System.out.print(slWhile.next()+", ");
						System.out.print(slWhile.next()+", ");
						System.out.print(slWhile.next()+", ");
						System.out.print(slWhile.next()+", \n");
						}
					}
				s.close();
			} 
			
			catch (FileNotFoundException e) {
				System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
				e.printStackTrace();
				}
			PersonaApp_Scanner.menuInicial();
	}//esHistorico

	/**
	 * Comprueba si el formato del dni corresponde al estandar
	 * comparando con un patron que exije 8 cifras y una letra.
	 * @return true o false
	 */
	public static boolean esDniValido(String entrada) {
		Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z]");
		Matcher mat = pat.matcher (entrada);
		return (mat.find());
	}//esDniValido
}
