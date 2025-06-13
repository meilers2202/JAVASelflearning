package zahlsystemrechner.msgs;
import java.io.File;
import java.util.ResourceBundle;

import zahlsystemrechner.ui.zahlensystem2;

public class settingsmsg {

    //---------------------------------Basic----------------------------------
    private static void clearConsole() {
        zahlensystem2.clearConsole();
    }

    //----------------------------SettingsMainMenu----------------------------
    public static void settingsMainMenu(ResourceBundle messages) {
        clearConsole();
        System.out.println(messages.getString("settingsTitle"));
        System.out.println(messages.getString("showLastEntries"));
        System.out.println(messages.getString("changeDisplayCount"));
        System.out.println(messages.getString("checkCreateFile"));
        System.out.println(messages.getString("clearResults"));
        System.out.println(messages.getString("deleteFile"));
        System.out.println(messages.getString("changeLanguage"));
        System.out.println(messages.getString("backToMainMenu"));
        System.out.println(messages.getString("exitProgram"));
        System.out.print(messages.getString("promptSelection"));
    }

    public static void backToSettings(ResourceBundle messages) {
        System.out.println(messages.getString("backToSettings"));
    }

    public static void showLastLines(ResourceBundle messages, int anzahlLetzteZeilen) {
        zahlensystem2.clearConsole();
        System.out.println(messages.getString("currentLastLines") + ": " + zahlensystem2.anzahlLetzteZeilen);
        System.out.print(messages.getString("enterNewCount") + ": ");
    }

    public static void newCountSaved(ResourceBundle messages, int letzte_zahl, int neueAnzahl) {
        System.out.println(messages.getString("newCountSaved") + ": " + messages.getString("from") + ": " + letzte_zahl + " ---> " + messages.getString("to") + ": " + neueAnzahl);
    }

    public static void backToMainMenu(ResourceBundle messages) {
        System.out.println(messages.getString("backToMainMenu"));
    }

    public static void programExiting(ResourceBundle messages) {
        System.out.println(messages.getString("programExiting"));
    }

    public static void calcSaved(ResourceBundle messages) {
        System.out.println(messages.getString("allCalculationsSaved"));
    }

    public static void noEntries(ResourceBundle messages) {
        System.out.println(messages.getString("noEntries"));
    }

    public static void lastLines(ResourceBundle messages, int anzahlLetzteZeilen) {
        System.out.println(messages.getString("lastLinesStandardMode") + " " + zahlensystem2.anzahlLetzteZeilen + ":");
    }

    public static void lastLines1(String vonLang, String nachLang, String eingabe, String ausgabe) {
        System.out.println(vonLang + " -> " + nachLang + " = " + eingabe + " -> " + ausgabe);
    }

    public static void fileAlreadyEmpty(ResourceBundle messages) {
        System.out.println(messages.getString("fileAlreadyEmpty"));
    }

    public static void fileCleared(ResourceBundle messages) {
        System.out.println(messages.getString("fileCleared"));
    }

    public static void fileDelSucc(ResourceBundle messages) {
        System.out.println(messages.getString("fileDeletedSuccess"));
    }

    // Doesfileexists
    public static void doesFileExist(ResourceBundle messages, File myObj) {
        System.out.println(messages.getString("fileCreated") + ": " + myObj.getName());
    }

    public static void doesFileExist2(ResourceBundle messages) {
        System.out.println(messages.getString("fileExists"));
    }

    public static void noFileCreate(ResourceBundle messages) {
        System.out.println(messages.getString("fileNotFoundCreate"));
    }
    /////////////////
    // Save

    public static void save(ResourceBundle messages, String text) {
        System.out.println(messages.getString("saved") + ": ''" + text + "''");
    }
    /////////////////
}