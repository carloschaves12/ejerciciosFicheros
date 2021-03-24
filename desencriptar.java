import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/*
 * Haz un programa que reciba como parámetro un fichero encriptado con el método César, lo desencripte y almacene el resultado en otro archivo, que también pasamos como parámetro, de manera que:
 * Si el programa no recibe el número de parámetros adecuado termina con un error 1.
 * Si el programa recibe un solo parámetro guardará la información encriptada en el mismo archivo del que lee, pero antes advertirá al usuario de que machacará el archivo origen, dando opción a que la operación no se haga.
 * Si el fichero origen no existe (da error al abrirlo como lectura) el programa termina con un mensaje de error y código 2.
 * Si en el fichero destino no se puede escribir (da error al abrirlo como escritura) el programa termina con un mensaje de error y código 3.
 * Para desencriptar necesitas una clave que debes pedir al usuario.
 * @author Carlos Chaves Hernández
 * */
public class desencriptar {

  private static final String LETRAS = "ABCDEFGHIJKLMN�OPQRSTUVWXYZ������";
  private static final int ERROR_ARGS = 1;
  private static final int ERROR_LECTURA = 2;
  private static final int ERROR_ESCRITURA = 3;

  public static void main(String[] args) {
    validarArgumentos(args);
    
    String origen = args[0];
    List<String> lineas = getLineasFichero(origen);
    
    String destino = getFicheroDestino(args);
    int clave = getClave();

    crearFicheroDesencriptado(destino, lineas, clave);
  }

  private static void validarArgumentos(String[] args) {
    if (args.length < 1 || args.length > 2) {
      finalizarConError("N�mero de argumentos err�neo.", ERROR_ARGS);
    }
  }
  
  private static List<String> getLineasFichero(String fichero) {
    List<String> lineas = null;
    try {
      lineas = Files.readAllLines(Paths.get(fichero));
    } catch (IOException e) {
      finalizarConError("No puedo abrir " + fichero, ERROR_LECTURA);
    }
    return lineas;
  }

  private static String getFicheroDestino(String[] args) {
    if (args.length == 2) {
      return args[1];
    }
    validarSobreescritura(args[0]);
    return args[0];
  }

  private static void validarSobreescritura(String fichero) {
    System.out.println("Tenga en cuenta que este proceso sobreescribir� " + fichero 
        + " y perder� la informaci�n contenida en el mismo.\n");

    Scanner s = new Scanner(System.in);
    String respuesta;
    do {
      System.out.print("�Est� seguro/a? (S/N) ");
      respuesta = s.nextLine().toUpperCase();
    } while (!respuesta.equals("S") && !respuesta.equals("N"));

    if (respuesta.equals("N")) {
      System.out.println("Proceso cancelado.");
      System.exit(0);
    }
  }

  private static int getClave() {
    Scanner s = new Scanner(System.in);
    int clave;

    do {
      System.out.print("Desplazamiento (positivo) para la encriptaci�n usando C�sar: ");
      clave = s.nextInt();
    } while (clave <= 0); 
    
    return clave;
  }

  private static void crearFicheroDesencriptado(String destino, List<String> lineas, int clave) {
    try (var ficheroDesencriptado = new PrintWriter(destino)) {
      for (String linea: lineas) {
        String cadenaDesencriptada = getCadenaDesencriptada(linea, clave);
        ficheroDesencriptado.println(cadenaDesencriptada);
      }
      System.out.println("Creado fichero desencriptado: " + destino);

    } catch (IOException e) {
      finalizarConError("Error al escribir en " + destino, ERROR_ESCRITURA);
    }
  }

  private static String getCadenaDesencriptada(String cadena, int desplazamiento) {
    String cadenaDesencriptada = "";
    for (char ch: cadena.toCharArray()) {
      cadenaDesencriptada += getCaracterDesencriptado(ch, desplazamiento);
    }
    return cadenaDesencriptada;
  }

  private static char getCaracterDesencriptado(char ch, int desplazamiento) {
    return (Character.isLetter(ch)) ? getCaracterDesplazado(ch, desplazamiento) : ch;
  }
  
  private static char getCaracterDesplazado(char ch, int desplazamiento) {
    String letras = (Character.isUpperCase(ch)) ? LETRAS : LETRAS.toLowerCase();
    int posicionCaracter = letras.indexOf(ch);
    int posicionCaracterDesplazado = (posicionCaracter - desplazamiento) % letras.length();
    if (posicionCaracterDesplazado < 0) {
      posicionCaracterDesplazado = letras.length() - posicionCaracterDesplazado;
    }
    return letras.charAt(posicionCaracterDesplazado);
  }

  private static void finalizarConError(String mensajeError, int codigoError) {
    System.err.println(mensajeError);
    System.exit(codigoError);
  }

}