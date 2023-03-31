package practicaFicherosED;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class TratamientoFichero {

	/**
	 * Método de escritura de archivo de texto en el que guardamos los pacientes.
	 */
	public static void grabarCliente(Paciente p,boolean esVisita) {
		String ruta = null;
		if (esVisita != true) {
			ruta = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Pacientes.txt";
		} else {
			ruta = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Visitas.txt";
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
				System.out.println("Paciente con nombre "+p.getNombre()+" y dni "+p.getDNI()+" ha sido guardado con exito.");
			} else {
				pw.println(p.getDNI()+","+p.getNombre()+","+p.getEdad()+","+p.getCalle()+","+p.getLocalidad()+","+p.getCodPostal());
				System.out.println("Paciente con nombre "+p.getNombre()+" y dni "+p.getDNI()+" ha sido guardado con exito.");
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
}	
