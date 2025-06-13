package zahlsystemrechner.ui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import zahlsystemrechner.msgs.settingsmsg;
import zahlsystemrechner.msgs.errors;

public class settingsmenu {
    private static ResourceBundle messages;
    private static final String DATA_FILE_PATH = "zahlsystemrechner/data/data.txt";
    public static int anzahlLetzteZeilen = 5;

    public static void setMessages(ResourceBundle bundle) {
        messages = bundle;
    }

    public static void printMessage(Scanner scanner) {
        settingsmsg.settingsMainMenu(messages);
        String auswahl = scanner.nextLine();
        if (!auswahl.matches("\\d+")) {
            errors.invalidNumberDigitsOnly(messages);
            zahlensystem2.waitForKeypress(scanner);
            return;
        }
        int eingabe = Integer.parseInt(auswahl);
        menus.settingsMain(messages, scanner, eingabe, zahlensystem2.anzahlLetzteZeilen);
    }

    public static void doesfileexist() {
        try {
            File myObj = new File(DATA_FILE_PATH);
            myObj.getParentFile().mkdirs();
            if (myObj.createNewFile()) {
                settingsmsg.doesFileExist(messages, myObj);
            } else {
                settingsmsg.doesFileExist2(messages);
            }
        } catch (IOException e) {
            errors.fileCheckError(messages);
            e.printStackTrace();
        }
    }

    public static String load() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            settingsmsg.noFileCreate(messages);
            return "";
        } catch (IOException e) {
            errors.loadingError(messages);
            e.printStackTrace();
        }
        return content.toString();
    }

    public static List < String > berechnungen = new ArrayList < > ();

    public static void string(String von, String nach, String eingabe, String ausgabe) {
        String eintrag = von + ";" + nach + ";" + eingabe + ";" + ausgabe;
        save(eintrag);
    }

    public static void save(String text) {
        try (FileWriter writer = new FileWriter(DATA_FILE_PATH, true)) {
            writer.write(text + System.lineSeparator());
            settingsmsg.save(messages, text);
        } catch (IOException e) {
            errors.saveError(messages);
            e.printStackTrace();
        }
    }

    public static void saveAllBerechnungen() {
        try (FileWriter writer = new FileWriter(DATA_FILE_PATH, true)) {
            for (String eintrag: berechnungen) {
                writer.write(eintrag + System.lineSeparator());
            }
            settingsmsg.calcSaved(messages);
        } catch (IOException e) {
            errors.saveCalcError(messages);
            e.printStackTrace();
        }
        berechnungen.clear();
    }

    public static void zeigeLetzteZeilen() {
        List < String > zeilen = new ArrayList < > ();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                zeilen.add(line);
            }
        } catch (FileNotFoundException e) {
            settingsmsg.noFileCreate(messages);
            return;
        } catch (IOException e) {
            errors.fileCheckError(messages);
            e.printStackTrace();
            return;
        }

        if (zeilen.isEmpty()) {
            settingsmsg.noEntries(messages);
            return;
        }

        int start = Math.max(zeilen.size() - zahlensystem2.anzahlLetzteZeilen, 0);
        standard(zeilen, start);
    }

    public static void standard(List < String > zeilen, int start) {
        settingsmsg.lastLines(messages, zahlensystem2.anzahlLetzteZeilen);
        for (int i = start; i < zeilen.size(); i++) {
            String[] teile = zeilen.get(i).split(";");
            if (teile.length == 4) {
                String vonLang = kuerzelToLang(teile[0]);
                String nachLang = kuerzelToLang(teile[1]);
                String eingabe = teile[2];
                String ausgabe = teile[3];
                settingsmsg.lastLines1(vonLang, nachLang, eingabe, ausgabe);
            } else {
                errors.invalidEntry(messages, zeilen, i);
            }
        }
    }

    public static String kuerzelToLang(String kuerzel) {
        return switch (kuerzel.toUpperCase()) {
        case "D" -> messages.getString("decimal");
        case "B" -> messages.getString("binary");
        case "H" -> messages.getString("hexadecimal");
        default -> messages.getString("unknown") + "(" + kuerzel + ")";
        };
    }

    public static void clearFile() {
        File file = new File(DATA_FILE_PATH);
        if (!file.exists()) {
            errors.fileNotExists(messages);
            return;
        }
        if (file.length() == 0) {
            settingsmsg.fileAlreadyEmpty(messages);
            return;
        }
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write("");
            settingsmsg.fileCleared(messages);
        } catch (IOException e) {
            errors.fileClearingError(messages);
            e.printStackTrace();
        }
    }

    public static void deleteFile() {
        File file = new File(DATA_FILE_PATH);
        if (file.exists()) {
            if (file.delete()) {
                settingsmsg.fileDelSucc(messages);
            } else {
                errors.fileDelerror(messages);
            }
        } else {
            errors.fileNotExists(messages);
        }
    }
}