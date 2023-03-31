package practicaFicherosED;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;


public class TratamientoFichero {

	/**
	 * Método de escritura de archivo de texto en el que guardamos los pacientes.
	 */
	public static void grabarCliente(Paciente p,boolean esVisita) {
		String ruta = null;
		String rutaPaciente = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Pacientes.txt";
		String rutaVisita = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Visitas.txt";
		Date d = new Date();
		if (esVisita != true) {
			ruta = rutaPaciente;
		} else {
			ruta = rutaVisita;
		}
		FileWriter fichero = null;
		PrintWriter pw = null;		
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
				System.out.println("Registro de paciente con dni "+p.getDNI()+" ha sido guardado con exito.");
			} else {
				/*
				 * p.getDNI()+","+p.getNombre()+","+p.getEdad()+","+p.getCalle()+","+p.getLocalidad()+","+p.getCodPostal()
				 * DNI, fecha de la consulta, hora de la consulta, peso, unidad de 
		peso, altura, unidad de altura, resultado de calcular el IMC.
				 */
				pw.println(p.getDNI()+","+d.getDay()+","+d.getTime()+","+p.getPeso()+"Kg,"+p.getAltura()+"m,");//+muestraMensajePeso();
				System.out.println("Registro de visita para paciente con dni "+p.getDNI()+" ha sido guardado con exito.");
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
	}

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
					//DNI
					if (sl.next().equals(dni)) {
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
	
	public static void FileScanner () {
		String ruta = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Pacientes.txt";
		File f = new File(ruta);
		Paciente p = new Paciente();
		System.out.println("La ruta del fichero es: " +
		f.getAbsolutePath());
		Scanner s;
		try {
		s = new Scanner(f);
		while (s.hasNextLine()) {
		String linea = s.nextLine();
		Scanner sl = new Scanner(linea);
		sl.useDelimiter("\\s*,\\s*");
		/*
		 * p.getDNI()+","+p.getNombre()+","+p.getEdad()+","+p.getCalle()+","+p.getLocalidad()+","+p.getCodPostal()
		 * DNI, fecha de la consulta, hora de la consulta, peso, unidad de 
peso, altura, unidad de altura, resultado de calcular el IMC.
		 */
		sl.next();
		sl.next();
		sl.next();
		int edad = sl.nextInt();
		p.setEdad(edad);
		
		System.out.println(sl.next());
		
		System.out.println(sl.next());
		System.out.println(sl.next());
		System.out.println(sl.next());
		System.out.println(sl.next());
		}
		s.close();
		} catch (FileNotFoundException e) {
		// PrintWriter pw = null;
		e.printStackTrace();
		// e.printStackTrace(pw);
		}
		}
	
}	
