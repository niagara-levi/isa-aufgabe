package ch.stfw.iae22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class VulnerablePathTraversal {

    private static final String BASE_DIRECTORY = "/var/www/app/data/"; // Simulierter Basisordner

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie den Dateinamen ein, den Sie lesen möchten (z.B. 'report.txt' oder '../config/secret.conf'):");
        String filename = scanner.nextLine();

        // **Vulnerabler Code: Unsichere Dateipfadkonstruktion**
        // Hier wird die Benutzereingabe direkt an den Basispfad angehängt.
        String filePath = BASE_DIRECTORY + filename;

        try {
            System.out.println("Versuche, Datei zu lesen: " + filePath);
            readFileContent(filePath);
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void readFileContent(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            System.out.println("\n--- Dateiinhalte ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--- Ende der Datei ---");
        }
    }
}
