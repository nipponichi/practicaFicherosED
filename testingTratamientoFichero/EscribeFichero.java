package testingTratamientoFichero;

import java.io.FileWriter;
import java.io.PrintWriter;
/*
* Aquí contemplamos una de las opciones a la hora de capturar
excepciones
* En esta parte lo hacemos con la estructura try + catch y añadimos la
opción de ejecución
* obligatoria con la sentencia finally.
*/
public class EscribeFichero {
public static void main(String[] args) {
String ruta = "c:/Users/Pedro/Desktop/escritura.txt";
FileWriter fichero = null;
PrintWriter pw = null;
try {
// Añadir flag a true para no machacar contenido del
//fichero de escritura
fichero = new FileWriter(ruta, true);
pw = new PrintWriter(fichero);
for (int i = 0; i < 10; i++) {
pw.println("Coche " + i);
}
} catch (Exception e) {
e.printStackTrace();
} finally {
try {
// Nuevamente aprovechamos el finally para
// asegurarnos que se cierra el fichero.
if (null != fichero) {
fichero.close();
}
} catch (Exception e2) {
e2.printStackTrace();
}
}
}
}
