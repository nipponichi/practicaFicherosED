package practicaFicherosED;

import java.io.FileWriter;
import java.io.PrintWriter;

public class TratamientoFichero {

	/**
	 * Método de escritura de archivo de texto en el que guardamos los pacientes.
	 */
	public static void grabarCliente(Paciente p) {
		String ruta = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Pacientes.txt";
		FileWriter fichero = null;
		PrintWriter pw = null;		
		/**
		 * Metodo de editar el archivo, si el fichero existe, se edita, si no,
		 * capturamos la excepcion.
		 */
		try {
			/**
			 * Se añade flag a la edicion del archivo para no sobreescribir los datos ya guardados.
			 */
			fichero = new FileWriter(ruta, true);
			pw = new PrintWriter(fichero);
			pw.println(p.getDNI()+","+p.getNombre()+","+p.getEdad()+","+p.getCalle()+","+p.getLocalidad()+","+p.getCodPostal());
			System.out.println("Paciente con nombre "+p.getNombre()+" y dni "+p.getDNI()+" ha sido guardado con exito.");

		} catch (Exception e) {
			System.err.println("El fichero 'Pacientes.txt' no existe para la ruta expecificada");
			e.printStackTrace();
			/**
			 * Metodo de cerrar la edicion del archivo, si el fichero existe, se cierra, si no,
			 * capturamos la excepcion.
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
}
