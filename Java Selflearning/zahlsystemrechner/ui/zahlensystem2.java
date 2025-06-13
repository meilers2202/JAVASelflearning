package zahlsystemrechner.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//Datenbank
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import zahlsystemrechner.msgs.errors;
import zahlsystemrechner.msgs.message;

public class zahlensystem2 {
    public static int anzahlLetzteZeilen = 10;
    private static final Scanner scanner = new Scanner(System.in);
    public static ResourceBundle messages;
    private static Locale currentLocale;
    private static final String SETTINGS_FILE_PATH = "zahlsystemrechner/data/settings.ini";
    private static final String DEFAULT_LOCALE_TAG = "de";

    //Datenbank
    private static final String DB_URL = "jdbc:mysql://localhost:3306/zahlensystem2";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try {
            loadSettings();
            initializeLocaleAndMessages();
            datenbank(scanner);
            waitForKeypress(scanner);
            mainMenu(scanner);
        } finally {
            if (scanner != null) {
                scanner.close();
                System.out.println("Scanner wurde geschlossen.");
            }
        }
    }

    public static void datenbank(Scanner scanner) {
        System.out.println("--- Benutzererstellung ---");
        System.out.print("Gib deinen Namen ein: ");
        String name = scanner.nextLine();

        Connection connection = null;
        PreparedStatement checkStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC-Treiber geladen.");

            System.out.println("Verbindung zu '" + DB_URL + "' herstellen...");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Verbindung erfolgreich hergestellt.");

            String checkSQL = "SELECT COUNT(*) FROM USER WHERE Name = ?;";
            checkStmt = connection.prepareStatement(checkSQL);
            checkStmt.setString(1, name);
            rs = checkStmt.executeQuery();

            int userCount = 0;
            if (rs.next()) {
                userCount = rs.getInt(1);
            }

            if (userCount > 0) {
                System.out.println("Benutzer '" + name + "' existiert bereits in der Datenbank. Kein neuer Eintrag erstellt.");
            } else {
                String insertSQL = "INSERT INTO USER (Name, language, lastLinesCount) VALUES (?, ?, ?);";
                insertStmt = connection.prepareStatement(insertSQL);
                insertStmt.setString(1, name);
                insertStmt.setString(2, "de");
                insertStmt.setInt(3, 5);

                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Benutzer '" + name + "' erfolgreich in der Datenbank erstellt.");
                } else {
                    System.out.println("Fehler beim Erstellen des Benutzers '" + name + "'. Keine Zeile hinzugefügt.");
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL-Fehler aufgetreten: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC-Treiber nicht gefunden: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (checkStmt != null) checkStmt.close();
                if (insertStmt != null) insertStmt.close();
                if (connection != null) connection.close();
                System.out.println("Datenbankverbindung geschlossen.");
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Ressourcen: " + e.getMessage());
            }
        }
    }
    
    private static void initializeLocaleAndMessages() {
        File dataDir = new File("zahlsystemrechner/data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        messages = ResourceBundle.getBundle("zahlsystemrechner.locales.loc", currentLocale);
        settingsmenu.setMessages(messages);
    }

    public static void waitForKeypress(Scanner scanner) {
        message.pressEnterToContinue(messages);
        scanner.nextLine();
    }

    private static void loadSettings() {
        Properties settings = new Properties();
        try (FileInputStream in = new FileInputStream(SETTINGS_FILE_PATH)) {
            settings.load(in);
            String savedLocaleTag = settings.getProperty("language", DEFAULT_LOCALE_TAG);
            currentLocale = Locale.of(savedLocaleTag);
            String zeilenStr = settings.getProperty("lastLinesCount", "10");
            try {
                anzahlLetzteZeilen = Integer.parseInt(zeilenStr);
            } catch (NumberFormatException e) {
                anzahlLetzteZeilen = 10;
            }
            messages = ResourceBundle.getBundle("zahlsystemrechner.locales.loc", currentLocale);
            message.languageLoad(messages, currentLocale);
        } catch (IOException e) {
            currentLocale = Locale.of(DEFAULT_LOCALE_TAG);
            messages = ResourceBundle.getBundle("zahlsystemrechner.locales.loc", currentLocale);
            message.settingsNotFound(messages);
            saveSettings();
        }
    }

    public static void saveSettings() {
        try {
            File settingsFile = new File(SETTINGS_FILE_PATH);
            settingsFile.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(settingsFile)) {
                writer.write("language=" + currentLocale.toLanguageTag() + System.lineSeparator());
                writer.write("lastLinesCount=" + anzahlLetzteZeilen + System.lineSeparator());
            }
            message.langSaved(messages, currentLocale);
        } catch (IOException e) {
            errors.displayError(messages, "saveSettingsError", e);
            e.printStackTrace();
        }
    }

    public static void mainMenu(Scanner scanner) {
        while (true) {
            message.mainMenu(messages);
            String auswahl = scanner.nextLine();
            if (!auswahl.matches("\\d+")) {
                errors.invalidNumberDigitsOnly(messages);
                waitForKeypress(scanner);
                continue;
            }
            int menu = Integer.parseInt(auswahl);
            menus.mainMenu(scanner, menu, messages);
        }
    }

    public static void dezimalMenu(Scanner scanner) {
        while (true) {
            message.decimalMenu(messages);
            String eingabe = scanner.nextLine();
            if (!eingabe.matches("\\d+")) {
                errors.invalidSelection(messages);
                waitForKeypress(scanner);
                continue;
            }
            int choice = Integer.parseInt(eingabe);
            menus.dezimalMenu(messages, scanner, choice);
            if (choice == 3) {
                 mainMenu(scanner);
                 return;
            }
        }
    }

    public static void binaerMenu(Scanner scanner) {
        while (true) {
            message.binMenu(messages);
            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                errors.invalidNumberDigitsOnly(messages);
                waitForKeypress(scanner);
                continue;
            }
            int choice = Integer.parseInt(input);
            menus.binaerMenu(messages, scanner, choice, input);
            if (choice == 3) {
                mainMenu(scanner);
                return;
            }
        }
    }

    public static void hexMenu(Scanner scanner) {
        while (true) {
            message.hexMenu(messages);
            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                errors.invalidSelection(messages);
                waitForKeypress(scanner);
                continue;
            }
            int choice = Integer.parseInt(input);
            menus.hexMenu(messages, scanner, choice, input);
            if (choice == 3) {
                mainMenu(scanner);
                return;
            }
        }
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            errors.invalidClear(messages);
        }
    }

    public static void chooseLanguage(Scanner scanner) {
        while (true) {
            message.languageMenu(messages);
            String input = scanner.nextLine();
            String newLocaleTag = menus.chooseLanguage(messages, scanner, input);
            if (newLocaleTag != null) {
                currentLocale = Locale.of(newLocaleTag);
                saveSettings();
                initializeLocaleAndMessages();
                message.languageChangedTo(messages, newLocaleTag);
                waitForKeypress(scanner);
                return;
            }
        }
    }
}