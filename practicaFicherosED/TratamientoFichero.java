package practicaFicherosED;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TratamientoFichero {

	/**
	 * Método de escritura de archivo de texto en el que guardamos los pacientes.
	 */
	public static void grabarCliente(Paciente p,boolean esVisita) {
		String ruta = null;
		FileWriter fichero = null;
		PrintWriter pw = null;	
		String rutaPaciente = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Pacientes.txt";
		String rutaVisita = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Visitas.txt";
		if (esVisita != true) {
			ruta = rutaPaciente;
		} else {
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
			if (esVisita != true) {
				pw.println(p.getDNI()+","+p.getNombre()+","+p.getEdad()+","+p.getCalle()+","+p.getLocalidad()+","+p.getCodPostal());
				System.out.println("Registro de nuevo paciente con dni "+p.DNI+" ha sido guardado con exito.");
			} else {
				pw.println("DNI: "+p.DNI+", Fecha: "+p.getFecha()+
					", Hora:"+p.getHora()+", Peso: "+p.getPeso()+"Kgs, Altura: "+p.getAltura()+"m, IMC: "+p.calcularIMC());
				System.out.println("Registro de visita de paciente con dni "+p.DNI+" ha sido guardado con exito.");
			}
		} catch (Exception e) {
			System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
			e.printStackTrace();
			/**
			 * Método para cerrar la edicion del archivo, si el fichero tiene contenido, se cierra, si no,
			 * capturamos la excepción.
			 */
		} finally {
			try {
				if (null != fichero) {
					fichero.close();
				}
			} catch (Exception e2) {
				System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
				e2.printStackTrace();
			}
		}
		PersonaApp_Scanner.menuInicial();
	}
	
	/**
	 * Método de busqueda de DNI en archivo Pacientes.txt
	 * @param dni de tipo String.
	 * @return true o false si el DNI se encuentra en el archivo Pacientes.txt
	 */
	public static boolean esDniRegistrado (String dni) {
		String ruta = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Pacientes.txt";
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
			} catch (FileNotFoundException e) {
				System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
				e.printStackTrace();
		}
			return false;
			
	} 
	
	/**
	 * Comprobamos si el cliente ha sido registrado en Visitas.txt
	 * y mostramos su evolucion histórica en la clínica.
	 * @param dni
	 */
	public static void esHistorico (String dni) {
		String ruta = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Visitas.txt";
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
			} catch (FileNotFoundException e) {
				System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
				e.printStackTrace();
				}
			PersonaApp_Scanner.menuInicial();
	}

	/**
	 * Método para mostrar el listado de proximas citas pendientes.		
	 * @param archivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void muestraContenido(String archivo) throws
		FileNotFoundException, IOException {
		String cadena;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		System.out.println("********************\nListado de proximas citas:\n"
				+ "********************\n");
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);
			}
			b.close();
			PersonaApp_Scanner.menuInicial();
	}
	
	/**
	 * Comprueba si el formato del dni corresponde al estandar
	 * comparando con un patron que exije 8 cifras y una letra.
	 * @return true o false
	 */
	public static boolean esDniValido(String entrada) {
		Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
		Matcher mat = pat.matcher (entrada);
		if (!mat.find()) {
			return false;
		} else {
			return true;
		}
		
	}
}
