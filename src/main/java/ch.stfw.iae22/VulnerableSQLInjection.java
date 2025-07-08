package ch.stfw.iae22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class VulnerableSQLInjection {

    private static final String DB_URL = "jdbc:h2:mem:testdb"; // In-Memory H2-Datenbank für Demo
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {
        // Initialisiere die Datenbank und füge Testdaten hinzu
        initDatabase();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie den Benutzernamen ein:");
        String username = scanner.nextLine();

        // **Vulnerable Code: Direkte String-Verkettung in der SQL-Abfrage**
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("Benutzer gefunden: " + rs.getString("username") + " (ID: " + rs.getInt("id") + ")");
            } else {
                System.out.println("Benutzer nicht gefunden.");
            }

        } catch (SQLException e) {
            System.err.println("Fehler bei der Datenbankabfrage: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255))");
            stmt.execute("INSERT INTO users (username, password) VALUES ('admin', 'adminpass')");
            stmt.execute("INSERT INTO users (username, password) VALUES ('user', 'userpass')");
            System.out.println("Datenbank initialisiert mit Testdaten.");
        } catch (SQLException e) {
            System.err.println("Fehler bei der Datenbankinitialisierung: " + e.getMessage());
        }
    }
}