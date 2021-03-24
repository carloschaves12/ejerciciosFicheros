
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/*
 * Realiza un programa que diga cu�ntas ocurrencias de una palabra hay en un
 *fichero. Tanto el nombre del fichero como la palabra se deben pasar como
 *argumentos en la l�nea de comandos.
 *@author Carlos Chaves Hernández
 */

public class ejercicio6 {
  public static void main (String[] args) {

    Scanner file;
    Scanner word;

    try {

      // Leo el argumento que se encuentre en la posicion 0 y 1.
      file = new Scanner(new BufferedReader(new FileReader(args[0])));
      word = new Scanner(new BufferedReader(new FileReader(args[1])));

      //Inicializamos las varibles fileWord, le asignamos la palabra que leemos con file.next() y cont, que cuenta el numero de ocurrencias
      int cont = 0;
      String fileWord;

      // Ciclo para ir leyendo palabra por palabra y comprobando si esta se repite.
      // El contador va contabilizando las ocurrencias
      while(file.hasNext()) {
        fileWord = file.next();
        if (fileWord == word.toString()) {
          cont ++;
        }
      }
    } catch (FileNotFoundException e) {
      System.err.print("No se ha encontrado el archivo");
    }
  }
}
