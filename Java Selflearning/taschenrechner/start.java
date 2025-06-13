package taschenrechner;

import java.util.Scanner;

public class start {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hauptAuswahl;
        int unterAuswahl;

        showHauptmenue();

        System.out.print("│ Wähle zwischen 1-3: ");
        hauptAuswahl = scanner.nextInt();
        if (hauptAuswahl < 1 || hauptAuswahl > 3) {
            System.out.println("Ungültige Auswahl!");
        }

        clearConsole();

        // Hauptmenü
        showHauptmenue();
        String hauptEingabe = String.valueOf(hauptAuswahl);
        System.out.print("│ Wähle zwischen 1-3: " + hauptEingabe);
        System.out.println(" ".repeat(21 - hauptEingabe.length()) + "│");
        System.out.println("├──────────────────────────────────────────┤");

        // Rechenarten-Menü anzeigen
        showRechenartMenue(hauptAuswahl);
        System.out.print("│ Wähle eine Rechenart: ");
        unterAuswahl = scanner.nextInt();

        int maxOption = (hauptAuswahl == 2) ? 7 : 4;
        if (unterAuswahl < 1 || unterAuswahl > maxOption) {
            System.out.println("Ungültige Auswahl!");
        }

        String unterEingabe = String.valueOf(unterAuswahl);

        // Erste Zahl
        clearConsole();
        showMenus(hauptEingabe, unterEingabe, hauptAuswahl);
        System.out.print("│ Gebe die erste Zahl ein: ");
        int zahl1 = scanner.nextInt();

        // Zweite Zahl
        clearConsole();
        showMenus(hauptEingabe, unterEingabe, hauptAuswahl);
        System.out.print("│ Gebe die erste Zahl ein: " + zahl1);
        System.out.println(" ".repeat(16 - unterEingabe.length()) + "│");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.print("│ Gebe die zweite Zahl ein: ");
        int zahl2 = scanner.nextInt();

        // Berechnung
        double ergebnis = 0;
        boolean gueltig = true;
        String operator = "";

        switch (unterAuswahl) {
            case 1:
                ergebnis = zahl1 + zahl2;
                operator = "+";
                break;
            case 2:
                ergebnis = zahl1 - zahl2;
                operator = "-";
                break;
            case 3:
                ergebnis = zahl1 * zahl2;
                operator = "*";
                break;
            case 4:
                if (zahl2 == 0) {
                    System.out.println("│ Fehler: Division durch 0 ist nicht erlaubt! │");
                    gueltig = false;
                } else {
                    ergebnis = (double) zahl1 / zahl2;
                    operator = "/";
                }
                break;
            case 5:
                ergebnis = Math.pow(zahl1, zahl2);
                operator = "^";
                break;
            case 6:
                if (zahl2 == 0) {
                    System.out.println("│ Fehler: Modulo durch 0 ist nicht erlaubt! │");
                    gueltig = false;
                } else {
                    ergebnis = zahl1 % zahl2;
                    operator = "%";
                }
                break;
            case 7:
                ergebnis = (zahl1 + zahl2) / 2.0;
                operator = "Durchschnitt";
                break;
        }

        if (gueltig) {
            clearConsole();
            showMenus(hauptEingabe, unterEingabe, hauptAuswahl);
            if (unterAuswahl == 7) {
                System.out.print("│ (" + zahl1 + " + " + zahl2 + ") / 2 = " + ergebnis);
            } else {
                System.out.print("│ " + zahl1 + " " + operator + " " + zahl2 + " = " + ergebnis);
            }
            System.out.println(" ".repeat(33 - String.valueOf(ergebnis).length()) + "│");
            System.out.println("└──────────────────────────────────────────┘");
        }

        scanner.close();
    }

    static void showHauptmenue() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│ Welchen Rechenmodus willst du verwenden? │");
        System.out.println("│ 1. Normal     (Standard)                 │");
        System.out.println("│ 2. Erweitert  (Besondere)                │");
        System.out.println("│ 3. Premium    (All-Inclusive)            │");
        System.out.println("├──────────────────────────────────────────┤");
    }

    static void showRechenartMenue(int hauptAuswahl) {
        if (hauptAuswahl == 1) {
            System.out.println("│ Welche Rechenart möchtest du nutzen?     │");
            System.out.println("│ 1. Addition                              │");
            System.out.println("│ 2. Subtraktion                           │");
            System.out.println("│ 3. Multiplikation                        │");
            System.out.println("│ 4. Division                              │");
        } else if (hauptAuswahl == 2) {
            System.out.println("│ Welche Rechenart möchtest du nutzen?     │");
            System.out.println("│ 5. Potenz                                │");
            System.out.println("│ 6. Modulus                               │");
            System.out.println("│ 7. Durchschnitt                          │");
        } else if (hauptAuswahl == 3) {
            System.out.println("│ Welche Rechenart möchtest du nutzen?     │");
            System.out.println("│ 1. Addition                              │");
            System.out.println("│ 2. Subtraktion                           │");
            System.out.println("│ 3. Multiplikation                        │");
            System.out.println("│ 4. Division                              │");
            System.out.println("│ 5. Potenz                                │");
            System.out.println("│ 6. Modulus                               │");
            System.out.println("│ 7. Durchschnitt                          │");
        }
        System.out.println("├──────────────────────────────────────────┤");
    }

    static void showMenus(String hauptEingabe, String unterEingabe, int hauptAuswahl) {
        showHauptmenue();
        System.out.print("│ Wähle zwischen 1-3: " + hauptEingabe);
        System.out.println(" ".repeat(21 - hauptEingabe.length()) + "│");
        System.out.println("├──────────────────────────────────────────┤");
        showRechenartMenue(hauptAuswahl);
        System.out.print("│ Wähle eine Rechenart: " + unterEingabe);
        System.out.println(" ".repeat(19 - unterEingabe.length()) + "│");
        System.out.println("├──────────────────────────────────────────┤");
    }

    static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Bildschirm konnte nicht gelöscht werden.");
        }
    }
}
