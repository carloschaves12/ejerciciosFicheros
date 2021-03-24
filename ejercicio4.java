import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

/*
 * Realiza un programa que sea capaz de ordenar alfabéticamente las palabras
 * contenidas en un fichero de texto. El nombre del fichero que contiene las
 * palabras se debe pasar como argumento en la línea de comandos. El nombre
 * del fichero resultado debe ser el mismo que el original añadiendo la coletilla
 * sort, por ejemplo palabras_sort.txt. Suponemos que cada palabra ocupa una
 * línea.
 * @author Carlos Chaves Hernández
 */

public class ejercicio4 {
  public static void main(String[] args)  {

    //Comprobamos si el número de argumentos introducidos es diferente a 1.
    if (args.length != 1) {
      System.err.println("Error en el número de argumentos.");
      System.exit(-1);
    }

    //Leemos el archivo pasado por argumento y metemos el contenido en un Arraylist de Strings.
    List<String> wordsArray;
    try {
      wordsArray = Files.readAllLines(Paths.get(args[0]));

      Collections.sort(wordsArray);

      var fileWr = Files.newBufferedWriter(Paths.get(ficheroDestino(args[0])), StandardOpenOption.CREATE);

      //Recorremos el  array y extraemos las palabras. 
      //Cada palabra debe ocupar una línea.
      //Con cada recorrido escribimos las palabras y un salto de línea en el nuevo fichero.
      for (String r: wordsArray ) {
        fileWr.write(r);
        fileWr.newLine();
      }
      fileWr.close();

    } catch (IOException e) {
      System.err.println("No se ha podido realizar la operación.");
      System.exit(-2);
    }
  }

  private static String ficheroDestino(String name) {
    //Creamos un fichero de escritura con el mismo nombre del original con coletilla *_sort*.
    //Calculamos la longitud del nombre.
    //Le quitamos la longitud de la extensión, la cual conocemos, y lo que nos queda es la longitud del nombre.
    int pointPos = name.lastIndexOf(".");
    String nameFile = name.substring(0, pointPos);
    String extension = name.substring(pointPos);
    return nameFile + "_sort" + extension;
  }
}
