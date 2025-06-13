package zahlsystemrechner.ui;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import zahlsystemrechner.msgs.errors;
import zahlsystemrechner.msgs.message;
import zahlsystemrechner.msgs.settingsmsg;

public class menus {
    Scanner scanner = new Scanner(System.in);

// MAINMENU
    //-------------------------Mainmenu-------------------------
    public static void mainMenu(Scanner scanner, int menu, ResourceBundle messages) {
        switch (menu) {
        case 1 -> zahlensystem2.dezimalMenu();
        case 2 -> zahlensystem2.binaerMenu();
        case 3 -> zahlensystem2.hexMenu();
        case 4 -> settingsmenu.printMessage(scanner);
        case 5 -> {
            errors.programExisting(messages);
            scanner.close();
            return;
        }
        default -> {
            errors.invalidSelection(messages);
            zahlensystem2.waitForKeypress(scanner);
        }
        }
    }

    //-----------------------Dezimalmenu------------------------
    public static void dezimalMenu(ResourceBundle messages, Scanner scanner, int choice) {
        while (true) {
            switch (choice) {
            case 1 -> {
                message.enterDezimal(messages);
                String dezInput = scanner.nextLine();
                if (!dezInput.matches("\\d+")) {
                    errors.digitsOnly(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    continue;
                }
                try {
                    int dez = Integer.parseInt(dezInput);
                    String binaer = Integer.toBinaryString(dez);
                    message.decimalOutput(messages, binaer, dez, dezInput);
                    zahlensystem2.waitForKeypress(scanner);
                    return;
                } catch (NumberFormatException e) {
                    errors.invalidNumber(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    continue;
                }
            }
            case 2 -> {
                message.enterDezimal(messages);
                String dezInput = scanner.nextLine();
                if (!dezInput.matches("\\d+")) {
                    errors.digitsOnly(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    continue;
                }
                try {
                    int dez = Integer.parseInt(dezInput);
                    String hex = Integer.toHexString(dez).toUpperCase();
                    message.dezToHexOutput(messages, hex, dez, dezInput);
                    zahlensystem2.waitForKeypress(scanner);
                    return;
                } catch (NumberFormatException e) {
                    errors.invalidNumber(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    continue;
                }
            }
            case 3 -> {
                zahlensystem2.mainMenu();
            }
            default -> {
                errors.invalidNumber(messages);
                zahlensystem2.waitForKeypress(scanner);
                return;
            }
            }
        }
    }

    //----------------------- Binaermenu------------------------
    public static void binaerMenu(ResourceBundle messages, Scanner scanner, int choice, String input) {
        boolean repeat = true;
        while (repeat) {
            switch (choice) {
            case 1 -> {
                message.enterBinary(messages);
                String bin = scanner.nextLine();
                if (!bin.matches("[01]+")) {
                    errors.binaryDigitsOnly(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    zahlensystem2.binaerMenu();
                }
                try {
                    int dez = Integer.parseInt(bin, 2);
                    message.binToDez(messages, bin, dez);
                    zahlensystem2.waitForKeypress(scanner);
                    zahlensystem2.binaerMenu();
                } catch (NumberFormatException e) {
                    errors.invalidBinary(messages);
                    zahlensystem2.waitForKeypress(scanner);
                }
            }
            case 2 -> {
                zahlensystem2.clearConsole();
                message.enterBinary(messages);
                String bin = scanner.nextLine();
                if (!bin.matches("[01]+")) {
                    errors.binaryDigitsOnly(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    zahlensystem2.binaerMenu();
                }

                while (bin.length() % 4 != 0) bin = "0" + bin;

                Map < String,
                Character > binToHex = new HashMap < > ();
                binToHex.put("0000", '0');binToHex.put("0001", '1');
                binToHex.put("0010", '2');binToHex.put("0011", '3');
                binToHex.put("0100", '4');binToHex.put("0101", '5');
                binToHex.put("0110", '6');binToHex.put("0111", '7');
                binToHex.put("1000", '8');binToHex.put("1001", '9');
                binToHex.put("1010", 'A');binToHex.put("1011", 'B');
                binToHex.put("1100", 'C');binToHex.put("1101", 'D');
                binToHex.put("1110", 'E');binToHex.put("1111", 'F');

                StringBuilder hex = new StringBuilder();
                for (int i = 0; i < bin.length(); i += 4) {
                    hex.append(binToHex.get(bin.substring(i, i + 4)));
                }

                message.binToHex(messages, bin, input);
                zahlensystem2.waitForKeypress(scanner);
                zahlensystem2.binaerMenu();
            }
            case 3 -> {
                zahlensystem2.mainMenu();
            }
            default -> {
                errors.invalidNumber(messages);
                zahlensystem2.waitForKeypress(scanner);
                break;
            }
            }
        }
    }

    //------------------------- Hexmenu-------------------------
    public static void hexMenu(ResourceBundle messages, Scanner scanner, int choice, String input) {
        boolean repeat = true;
        while (repeat) {
            switch (choice) {
            case 1 -> {
                message.hexInput(messages);
                String hex = scanner.nextLine().toUpperCase();
                if (!hex.matches("[0-9A-F]+")) {
                    errors.invalidHex(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    zahlensystem2.hexMenu();
                }
                try {
                    int dez = Integer.parseInt(hex, 16);
                    message.hexToDez(messages, hex, dez);
                    zahlensystem2.waitForKeypress(scanner);
                    zahlensystem2.hexMenu();
                } catch (NumberFormatException e) {
                    errors.invalidHexNum(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    zahlensystem2.hexMenu();
                }
            }
            case 2 -> {
                zahlensystem2.clearConsole();
                message.hexInput(messages);
                String hex = scanner.nextLine().toUpperCase();
                if (!hex.matches("[0-9A-F]+")) {
                    errors.invalidHex(messages);
                    zahlensystem2.waitForKeypress(scanner);
                    zahlensystem2.hexMenu();
                }

                Map < Character,
                String > hexToBin = new HashMap < > ();
                hexToBin.put('0', "0000");hexToBin.put('1', "0001");
                hexToBin.put('2', "0010");hexToBin.put('3', "0011");
                hexToBin.put('4', "0100");hexToBin.put('5', "0101");
                hexToBin.put('6', "0110");hexToBin.put('7', "0111");
                hexToBin.put('8', "1000");hexToBin.put('9', "1001");
                hexToBin.put('A', "1010");hexToBin.put('B', "1011");
                hexToBin.put('C', "1100");hexToBin.put('D', "1101");
                hexToBin.put('E', "1110");hexToBin.put('F', "1111");

                StringBuilder bin = new StringBuilder();
                for (char c: hex.toCharArray()) {
                    String binVal = hexToBin.get(c);
                    if (binVal == null) {
                        errors.invalidHexChar(messages, c);
                        bin.setLength(0);
                        break;
                    }
                    bin.append(binVal);
                }

                if (bin.length() > 0) {
                    message.hexToBin(messages, hex, input);
                }
                zahlensystem2.waitForKeypress(scanner);
                zahlensystem2.hexMenu();
            }
            case 3 -> {
                zahlensystem2.mainMenu();
            }
            default -> {
                errors.invalidNumber(messages);
                zahlensystem2.waitForKeypress(scanner);
                zahlensystem2.hexMenu();
            }
            }
        }
    }
// MAINMENU

//SETTINGSMENU
    //-------------------------Mainmenu-------------------------
    public static void settingsMain(ResourceBundle messages, Scanner scanner, int eingabe, int anzahlLetzteZeilen) {
        System.out.println("settingsMain aufgerufen mit eingabe = " + eingabe);
        switch (eingabe) {
        //------------------Show Last Entries-----------------
        case 1:
            zahlensystem2.clearConsole();
            settingsmenu.zeigeLetzteZeilen();
            System.out.print("\n");
            settingsmsg.backToSettings(messages);
            zahlensystem2.waitForKeypress(scanner);
            settingsmenu.printMessage(scanner);
            break;
        //------------------Change Display Count-----------------
        case 2:
            settingsmsg.showLastLines(messages, zahlensystem2.anzahlLetzteZeilen);
            try {
                int neueAnzahl = Integer.parseInt(scanner.nextLine());
                if (neueAnzahl >= 0) {
                    int letzte_zahl = zahlensystem2.anzahlLetzteZeilen;
                    zahlensystem2.anzahlLetzteZeilen = neueAnzahl;
                    zahlensystem2.anzahlLetzteZeilen = neueAnzahl;
                    zahlensystem2.saveSettings();
                    settingsmsg.newCountSaved(messages, letzte_zahl, neueAnzahl);
                } else {
                    errors.enterPositiveNumber(messages);
                }
            } catch (NumberFormatException e) {
                errors.invalidInputInteger(messages);
            }
            System.out.print("\n");
            settingsmsg.backToSettings(messages);
            zahlensystem2.waitForKeypress(scanner);
            settingsmenu.printMessage(scanner);
            break;
        //------------------Check/Create File-----------------
        case 3:
            zahlensystem2.clearConsole();
            settingsmenu.doesfileexist();
            System.out.print("\n");
            settingsmsg.backToSettings(messages);
            zahlensystem2.waitForKeypress(scanner);
            settingsmenu.printMessage(scanner);
            break;
        //------------------Clear Results-----------------
        case 4:
            zahlensystem2.clearConsole();
            settingsmenu.clearFile();
            System.out.print("\n");
            settingsmsg.backToSettings(messages);
            zahlensystem2.waitForKeypress(scanner);
            settingsmenu.printMessage(scanner);
            break;
        //------------------Delete File-----------------
        case 5:
            zahlensystem2.clearConsole();
            settingsmenu.deleteFile();
            System.out.print("\n");
            settingsmsg.backToSettings(messages);
            zahlensystem2.waitForKeypress(scanner);
            settingsmenu.printMessage(scanner);
            break;
        //------------------Change Language-----------------
        case 6:
            zahlensystem2.chooseLanguage(scanner);
            settingsmenu.printMessage(scanner);
            break;
        //------------------Back to Main Menu-----------------
        case 7:
            settingsmsg.backToMainMenu(messages);
            return;
        //------------------Exit Program-----------------
        case 8:
            settingsmsg.programExiting(messages);
            System.exit(0);
        default:
            errors.invalidSelection(messages);
            zahlensystem2.waitForKeypress(scanner);
            settingsmenu.printMessage(scanner);
            break;
        }
    }
//SETTINGSMENU

//CHOOSE-LANGUAGE
    public static String chooseLanguage(ResourceBundle messages, Scanner scanner, String input) {
        try {
            int parsed = Integer.parseInt(input);
            switch (parsed) {
            case 1 -> {
                return "de";
            }
            case 2 -> {
                return "en";
            }
            case 3 -> {
                return "es";
            }
            default -> {
                errors.invalidLanguage(messages);
                zahlensystem2.waitForKeypress(scanner);
                return null;
            }
            }
        } catch (NumberFormatException e) {
            errors.invalidInputInteger(messages);
            zahlensystem2.waitForKeypress(scanner);
            return null;
        }
    }
//CHOOSE-LANGUAGE
}