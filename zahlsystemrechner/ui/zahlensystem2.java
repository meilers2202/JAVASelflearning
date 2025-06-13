package zahlsystemrechner.ui;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import zahlsystemrechner.msgs.errors;
import zahlsystemrechner.msgs.message;

public class zahlensystem2 {
    public static int anzahlLetzteZeilen = 10;
    private static final Scanner scanner = new Scanner(System.in);
    public static ResourceBundle messages;
    private static Locale currentLocale;
    private static final String SETTINGS_FILE_PATH = "zahlsystemrechner/data/settings.ini";
    private static final String DEFAULT_LOCALE_TAG = "de";

    public static void main(String[] args) {
        loadSettings();
        initializeLocaleAndMessages();
        mainMenu();
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

    public static void mainMenu() {
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

    public static void dezimalMenu() {
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
        }
    }

    public static void binaerMenu() {
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
        }
    }

    public static void hexMenu() {
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