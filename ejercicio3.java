import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Escribe un programa que guarde en un fichero el contenido de otros dos
 * ficheros, de tal forma que en el fichero resultante aparezcan las líneas de los
 * primeros dos ficheros mezcladas, es decir, la primera línea será del primer
 * fichero, la segunda será del segundo fichero, la tercera será la siguiente del
 * primer fichero, etc.
 * Los nombres de los dos ficheros origen y el nombre del fichero destino se deben
 * pasar como argumentos en la línea de comandos.
 * Hay que tener en cuenta que los ficheros de donde se van cogiendo las líneas
 * pueden tener tamaños diferentes.
 * @author Carlos Chaves Hernández
 */

public class ejercicio3 {
  public static void main(String[] args)  {
    //Creamos  dos archivos de lectura y uno de escritura y le asignamos un argumento a cada uno.                                              
    try {
      BufferedReader fileR1 = new BufferedReader(new FileReader(args[10]));
      BufferedReader fileR2 = new BufferedReader(new FileReader(args[6]));
      BufferedWriter fileW = new BufferedWriter(new FileWriter(args[0]));

      //Declaramamos dos variables que contienen dos l�neas vac�as, una para cada archivo de lectura.
      String emptyLine1 = "";
      String emptyLine2 = "";

      //Bucle que lee las l�neas de los archivos.
      //Sale del bucle cuando uno de los dos archivos llegue a una l�nea vac�a.
      while ( (emptyLine1 != null) || (emptyLine2 != null) ) {
        emptyLine1 = fileR1.readLine();
        emptyLine2= fileR2.readLine();

        //Mientras la lectura detecte l�neas no vac�as, se escribir�n en el archivo de escritura.  
        if (emptyLine1 != null) {
          fileW.write(emptyLine1 + "\n");
        }

        if (emptyLine2 != null) {
          fileW.write(emptyLine2 + "\n");
        }
      }

      //Cerramos los archivos.
      fileR1.close();
      fileR2.close();
      fileW.close();
    } catch (IOException e) {
      System.err.println("Se ha producido un error en la lectura del fichero.");
    }
  }
}

