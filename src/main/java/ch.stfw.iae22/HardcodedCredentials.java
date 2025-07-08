package ch.stfw.iae22;

public class HardcodedCredentials {

    public static void main(String[] args) {
        // **Vulnerabler Code: Fest codierte Anmeldeinformationen**
        String username = "admin";
        String password = "SuperSecretPassword123!"; // Das ist die Schwachstelle!

        authenticateUser(username, password);
    }

    public static void authenticateUser(String user, String pass) {
        if (user.equals("admin") && pass.equals("SuperSecretPassword123!")) {
            System.out.println("Benutzer erfolgreich authentifiziert.");
        } else {
            System.out.println("Authentifizierung fehlgeschlagen.");
        }
    }
}
