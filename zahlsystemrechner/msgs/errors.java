package zahlsystemrechner.msgs;
import java.util.List;
import java.util.ResourceBundle;

import zahlsystemrechner.ui.zahlensystem2;

public class errors {
    //------------------------------Basic---------------------------------

    private static void clearConsole() {
        zahlensystem2.clearConsole();
    }

    //-----------------------------Errors---------------------------------

    public static void programExisting(ResourceBundle messages) {
        clearConsole();
        System.out.println(messages.getString("programExiting"));
    }

    public static void invalidSelection(ResourceBundle messages) {
        System.out.println(messages.getString("invalidSelection"));
    }

    public static void digitsOnly(ResourceBundle messages) {
        System.out.println(messages.getString("digitsOnlyAllowed"));
    }

    public static void invalidNumber(ResourceBundle messages) {
        System.out.println(messages.getString("invalidNumber"));
    }

    public static void invalidNumberDigitsOnly(ResourceBundle messages) {
        System.out.println(messages.getString("invalidInputDigitsOnly"));
    }

    public static void binaryDigitsOnly(ResourceBundle messages) {
        System.out.println(messages.getString("binaryDigitsOnly"));
    }

    public static void invalidBinary(ResourceBundle messages) {
        System.out.println(messages.getString("invalidBinary"));
    }

    public static void invalidHex(ResourceBundle messages) {
        System.out.println(messages.getString("invalidHexadecimal"));
    }

    public static void invalidHexNum(ResourceBundle messages) {
        System.out.println(messages.getString("invalidHexadecimal"));
    }

    public static void invalidHexChar(ResourceBundle messages, char c) {
        System.out.println(messages.getString("invalidHexadecimalCharacter") + ": " + c);
    }

    public static void invalidClear(ResourceBundle messages) {
        System.out.println(messages.getString("clearConsoleError"));
    }

    public static void invalidLanguage(ResourceBundle messages) {
        System.out.println(messages.getString("invalidLanguageSelection"));
    }

    public static void displayError(ResourceBundle messages, String errorKey, Exception e) {
        System.err.println(messages.getString(errorKey) + ": " + e.getMessage());
    }

    public static void enterPositiveNumber(ResourceBundle messages) {
        System.out.println(messages.getString("enterPositiveNumber"));
    }

    public static void invalidInputInteger(ResourceBundle messages) {
        System.out.println(messages.getString("invalidInputInteger"));
    }

    public static void fileCheckError(ResourceBundle messages) {
        System.out.println(messages.getString("fileCheckError"));
    }

    public static void loadingError(ResourceBundle messages) {
        System.out.println(messages.getString("loadingError"));
    }

    public static void saveError(ResourceBundle messages) {
        System.out.println(messages.getString("savingError"));
    }

    public static void saveCalcError(ResourceBundle messages) {
        System.out.println(messages.getString("savingAllCalculationsError"));
    }

    public static void invalidEntry(ResourceBundle messages, List < String > zeilen, int i) {
        System.out.println(messages.getString("invalidEntry") + " " + zeilen.get(i));
    }

    public static void fileNotExists(ResourceBundle messages) {
        System.out.println(messages.getString("fileNotExists"));
    }

    public static void fileClearingError(ResourceBundle messages) {
        System.out.println(messages.getString("fileClearingError"));
    }

    public static void fileDelerror(ResourceBundle messages) {
        System.out.println(messages.getString("fileDeleteError"));
    }

}