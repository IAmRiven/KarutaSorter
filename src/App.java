import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static File CargarArchivo() {
        File archivo = new File("database\\cartas.txt");
        return archivo;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File archivo = CargarArchivo();
        Scanner scanner = new Scanner(archivo);
        char emoji = '◾';
        int contador_cartas = 0;
        Pattern pattern = Pattern.compile("#(\\d+)");
        ArrayList<String> cartas_1_9 = new ArrayList<String>();
        ArrayList<String> cartas_10_99 = new ArrayList<String>();
        ArrayList<String> cartas_100_999 = new ArrayList<String>();
        ArrayList<String> cartas_1000_9999 = new ArrayList<String>();
        ArrayList<String> cartas_10000_above = new ArrayList<String>();

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            Matcher matcher = pattern.matcher(linea);

            if (linea.charAt(0) == emoji) {
                if (matcher.find()) {
                    String numero = matcher.group(1);
                    String[] partes = linea.split(" ");
                    if (Integer.parseInt(numero) <= 9) {
                        String codigo = partes[1];
                        cartas_1_9.add(codigo);
                        contador_cartas++;
                    } else if (Integer.parseInt(numero) >= 10 && Integer.parseInt(numero) <= 99) {
                        String codigo = partes[1];
                        cartas_10_99.add(codigo);
                        contador_cartas++;
                    } else if (Integer.parseInt(numero) >= 100 && Integer.parseInt(numero) <= 999) {
                        String codigo = partes[1];
                        cartas_100_999.add(codigo);
                        contador_cartas++;
                    } else if (Integer.parseInt(numero) >= 1000 && Integer.parseInt(numero) <= 9999) {
                        String codigo = partes[1];
                        cartas_1000_9999.add(codigo);
                        contador_cartas++;
                    } else if (Integer.parseInt(numero) >= 10000) {
                        String codigo = partes[1];
                        cartas_10000_above.add(codigo);
                        contador_cartas++;
                    }
                }
            }
        }
        scanner.close();

        try {
            FileWriter writer = new FileWriter("database\\results.txt");

            writer.write("Cartas 1-9:\n");
            escribirLista(writer, cartas_1_9);
            writer.write("Total de cartas: " + cartas_1_9.size() + "\n\n");

            writer.write("Cartas 10-99:\n");
            escribirLista(writer, cartas_10_99);
            writer.write("Total de cartas: " + cartas_10_99.size() + "\n\n");

            writer.write("Cartas 100-999:\n");
            escribirLista(writer, cartas_100_999);
            writer.write("Total de cartas: " + cartas_100_999.size() + "\n\n");

            writer.write("Cartas 1000-9999:\n");
            escribirLista(writer, cartas_1000_9999);
            writer.write("Total de cartas: " + cartas_1000_9999.size() + "\n\n");

            writer.write("Cartas 10000 y más:\n");
            escribirLista(writer, cartas_10000_above);
            writer.write("Total de cartas: " + cartas_10000_above.size() + "\n\n");

            writer.write("Total de cartas: " + contador_cartas + "\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirLista(FileWriter writer, ArrayList<String> lista) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String item : lista) {
            sb.append(item).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        writer.write(sb.toString() + "\n");
    }
}