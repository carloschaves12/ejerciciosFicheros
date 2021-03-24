import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * Escribe un programa que guarde en un fichero con nombre primos.dat los
 * números primos que hay entre 1 y 500.
 * @author Carlos Chaves Hernández
 */

public class ejercicio1 {
  public static void main(String[] args) throws FileNotFoundException {

    PrintWriter file = new PrintWriter("primos.dat");

    for (int i=1; i<=501; i++) {
      if (esPrimo(i)) {
        file.println(i + ", ");
      }
    }

    file.close();

  }

  public static boolean esPrimo(int primo){
    for (int i = 2; i < primo; i++) {
      if ((primo % i) == 0) {
        return false;
      }
    }
    return true;
  }
}
