package zahlsystemrechner.msgs;
import java.util.ResourceBundle;

import zahlsystemrechner.ui.settingsmenu;
import zahlsystemrechner.ui.zahlensystem2;

import java.util.Locale;

public class message {
    //-----------------------------Basic----------------------------

    public static void pressEnterToContinue(ResourceBundle messages) {
        System.out.println(messages.getString("pressEnterToContinue"));
    }

    private static void clearConsole() {
        zahlensystem2.clearConsole();
    }

    //-------------------------MainMenu--------------------------

    public static void mainMenu(ResourceBundle messages) {
        clearConsole();
        System.out.println(" " + messages.getString("title"));
        System.out.println(messages.getString("option1"));
        System.out.println(messages.getString("option2"));
        System.out.println(messages.getString("option3"));
        System.out.println(messages.getString("option4"));
        System.out.println(messages.getString("option5"));
        System.out.print(messages.getString("prompt"));
    }

    //-------------------------Dezimalmenu--------------------------

    public static void decimalMenu(ResourceBundle messages) {
        clearConsole();
        System.out.println(messages.getString("fromDecimalTo"));
        System.out.println("1. " + messages.getString("binary"));
        System.out.println("2. " + messages.getString("hexadecimal"));
        System.out.println("3. " + messages.getString("back"));
        System.out.print(messages.getString("promptSelection"));
    }

    //-----------------------DezToBin---------------------------

    public static void enterDezimal(ResourceBundle messages) {
        clearConsole();
        System.out.print(messages.getString("enterDecimal") + ": ");
    }

    public static void decimalOutput(ResourceBundle messages, String binaer, int dez, String dezInput) {
        System.out.println(messages.getString("decimal") + ": " + dez);
        System.out.println(messages.getString("binary") + ":   " + binaer);
        settingsmenu.string("D", "B", dezInput, binaer);
    }

    //-----------------------DezToHex---------------------------

    public static void dezToHexOutput(ResourceBundle messages, String hex, int dez, String dezInput) {
        System.out.println(messages.getString("decimal") + ":     " + dez);
        System.out.println(messages.getString("hexadecimal") + ": " + hex);
        settingsmenu.string("D", "H", dezInput, hex);
    }

    //--------------------------BinaerMenu--------------------------

    public static void binMenu(ResourceBundle messages) {
        clearConsole();
        System.out.println("" + messages.getString("fromBinaryTo"));
        System.out.println("1. " + messages.getString("decimal"));
        System.out.println("2. " + messages.getString("hexadecimal"));
        System.out.println("3. " + messages.getString("back"));
        System.out.print(messages.getString("promptSelection"));
    }

    //----------------------BinToDez----------------------------

    public static void enterBinary(ResourceBundle messages) {
        clearConsole();
        System.out.print(messages.getString("enterBinary") + ": ");
    }

    public static void binToDez(ResourceBundle messages, String bin, int dez) {
        System.out.println(messages.getString("binary") + ": " + bin);
        System.out.println(messages.getString("decimal") + ": " + dez);
        settingsmenu.string("B", "D", bin, String.valueOf(dez));
    }

    //----------------------BinToHex----------------------------

    public static void binToHex(ResourceBundle messages, String bin, String hex) {
        System.out.println(messages.getString("binary") + ": " + bin);
        System.out.println(messages.getString("hexadecimal") + ": " + hex);
        settingsmenu.string("B", "H", bin, hex.toString());
    }

    //--------------------------HexMenu-----------------------------

    public static void hexMenu(ResourceBundle messages) {
        clearConsole();
        System.out.println(messages.getString("fromHexadecimalTo"));
        System.out.println("1. " + messages.getString("decimal"));
        System.out.println("2. " + messages.getString("binary"));
        System.out.println("3. " + messages.getString("back"));
        System.out.print(messages.getString("promptSelection"));
    }

    //----------------------HexToDez----------------------------

    public static void hexInput(ResourceBundle messages) {
        clearConsole();
        System.out.print(messages.getString("enterHexadecimal") + ": ");
    }

    public static void hexToDez(ResourceBundle messages, String hex, int dez) {
        System.out.println(messages.getString("hexadecimal") + ": " + hex);
        System.out.println(messages.getString("decimal") + ":     " + dez);
        settingsmenu.string("H", "D", hex, String.valueOf(dez));
    }

    //----------------------HexToBin----------------------------

    public static void hexToBin(ResourceBundle messages, String hex, String bin) {
        System.out.println(messages.getString("hexadecimal") + ": " + hex);
        System.out.println(messages.getString("binary") + ": " + bin);
        settingsmenu.string("H", "B", hex, bin.toString());
    }
    //------------------------LanguageMenu--------------------------

    public static void languageMenu(ResourceBundle messages) {
        clearConsole();
        System.out.println("Choose language:");
        System.out.println("1. Deutsch");
        System.out.println("2. English");
        System.out.println("3. Español");
        System.out.print("Input: ");
    }

    public static void languageChangedTo(ResourceBundle messages, String newLocaleTag) {
        System.out.println(messages.getString("languageChangedTo") + " " + newLocaleTag);
    }

    public static void languageLoad(ResourceBundle messages, Locale currentLocale) {
        System.out.println(messages.getString("languageLoad") + currentLocale);
    }

    public static void settingsNotFound(ResourceBundle messages) {
        System.out.println(messages.getString("settingNotFound"));
    }

    public static void langSaved(ResourceBundle messages, Locale currentLocale) {
        System.out.println(messages.getString("langSaved") + currentLocale.toLanguageTag());
    }
}