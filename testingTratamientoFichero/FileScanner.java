package testingTratamientoFichero;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FileScanner {
public static void main(String[] args) {
String ruta = "C:\\Users\\Javier\\eclipse-workspace\\practicaFicherosED\\src\\almacenamiento\\Visitas.txt";
File f = new File(ruta);
System.out.println("La ruta del fichero es: " +
f.getAbsolutePath());
Scanner s;
try {
s = new Scanner(f);
while (s.hasNextLine()) {
String linea = s.nextLine();
Scanner sl = new Scanner(linea);
sl.useDelimiter("\\s*,\\s*");
System.out.println(sl.next());
System.out.println(sl.next());
System.out.println("edad");
System.out.println(sl.next());
System.out.println(sl.next());
System.out.println(sl.next());
sl.close();
}
s.close();
} catch (FileNotFoundException e) {
// PrintWriter pw = null;
e.printStackTrace();
// e.printStackTrace(pw);
}
}
}