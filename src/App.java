import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static File CargarArchivo() {
        File archivo = new File("KarutaSorter\\database\\cartas.txt");
        return archivo;

    }

    public static void main(String[] args) throws FileNotFoundException {
        File archivo = CargarArchivo();
        Scanner scanner = new Scanner(archivo);
        char emoji = '◾';
        Pattern pattern = Pattern.compile("#(\\d+)");
        ArrayList<String> cartas = new ArrayList<String>();
        ArrayList<String> cartas_low_1_100 = new ArrayList<String>();
        ArrayList<String> cartas_low_101_5000 = new ArrayList<String>();

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            Matcher matcher = pattern.matcher(linea);

            if (linea.charAt(0) == emoji) {
                if (matcher.find()) {
                    String numero = matcher.group(1);
                    String[] partes = linea.split(" ");
                    if (Integer.parseInt(numero) > 50000) {
                        String codigo = partes[1];
                        cartas.add(codigo);
                    } else if (Integer.parseInt(numero) <= 100) {
                        String codigo = partes[1];
                        cartas_low_1_100.add(codigo);
                    } else if (Integer.parseInt(numero) > 100 && Integer.parseInt(numero) <= 5000) {
                        String codigo = partes[1];
                        cartas_low_101_5000.add(codigo);
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Cartas con numero mayor a 50000: ");
        imprimirLista(cartas);

        System.out.println();
        System.out.println("Cartas con numero mayor a 100 y menor o igual a 5000: ");
        imprimirLista(cartas_low_101_5000);

        System.out.println();
        System.out.println("Cartas con numero menor o igual a 100: ");
        imprimirLista(cartas_low_1_100);

        scanner.close();
    }

    public static void imprimirLista(ArrayList<String> lista) {
        StringBuilder sb = new StringBuilder();
        for (String item : lista) {
            sb.append(item).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        System.out.println(sb.toString());
    }
}
