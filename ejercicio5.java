import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * Escribe un programa capaz de quitar los comentarios de un programa de Java.
 * Se utilizaría de la siguiente manera:
 * quita_commentarios PROGRAMA_ORIGINAL PROGRAMA_LIMPIO
 * Por ejemplo:
 * quita_comentarios hola.java holav2.java
 * crea un fichero con nombre holav2.java que contiene el código de hola.java pero
 * sin los comentarios.
 * @author Carlos Chaves Hernández
 */
public class ejercicio5 {

  public static void main(String[] args) {

    // �N�mero de par�metros correcto?
    if (args.length != 2) {
      System.err.println("Error en el n�mero de par�metros"); 
      System.exit(1);
    }

    // Proceso
    try {
      BufferedReader origen = new BufferedReader(new FileReader(args[0]));  // Abrimos fichero origen
      BufferedWriter destino = new BufferedWriter(new FileWriter(args[1])); // Abrimos fichero destino

      boolean estoyEnUnBloqueDeComentarios = false;     // necesaria para detectar bloques /* ... */
      String lineaLeida; 
      String lineaaEscribir;

      while ((lineaLeida=origen.readLine()) != null) {
        lineaaEscribir = lineaLeida;

        // �hay comentario de bloque multil�nea?
        if (lineaLeida.contains("/*")) {
          estoyEnUnBloqueDeComentarios = true;

          // �hay comentario de l�nea?  
        } else if (lineaLeida.contains("//")) {
          int posComentario = lineaLeida.indexOf("//");
          lineaaEscribir = lineaLeida.substring(0, posComentario);
        }

        // si no estoy en un comentario de bloque escribo
        if (! estoyEnUnBloqueDeComentarios) {
          destino.write(lineaaEscribir);
          destino.newLine();
        }

        // �fin de comentario de bloque multil�nea?
        if (lineaLeida.contains("*/")) {
          estoyEnUnBloqueDeComentarios = false;
        }
      }
      // Finalizo y cierro archivos
      origen.close();
      destino.close();
      System.out.println("Fin del proceso.");

    } catch (FileNotFoundException excp) {
      System.err.println("No se encuentra el archivo.");
      System.exit(1);
    } catch (IOException excp) {
      System.err.println("Error de entrada/salida al manejar el fichero");
      System.exit(2);
    }
  }
}