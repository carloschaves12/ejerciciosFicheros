import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*Realiza un programa que lea el fichero creado en el ejercicio anterior y que
 *muestre los números por pantalla.
 *@author Carlos Chaves Hernández
 */

public class ejercicio2 {
  public static void main (String[] args) {

    BufferedReader file;
    try {
      file = Files.newBufferedReader(Paths.get("primos.dat"));

      String linea;
      while ((linea = file.readLine()) !=null) {
        System.out.print(linea);
      }
    }catch (IOException e) {
    }
  }
}
