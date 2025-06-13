package taschenrechner;
import java.util.Scanner;

public class normalmode {
    public static void normalstart() {

        Scanner scanner = new Scanner(System.in);
        int auswahl;
        clear.clearConsole();
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│ Welchen Rechenmodus willst du verwenden? │");
        System.out.println("│ 1. Addition                              │");
        System.out.println("│ 2. Subtraktion                           │");
        System.out.println("│ 3. Multiplikation                        │");
        System.out.println("│ 4. Division                              │");
        System.out.println("├──────────────────────────────────────────┤");

        System.out.print("│ Wähle zwischen 1-4: ");
        auswahl = scanner.nextInt();
        clear.clearConsole();
        if (auswahl >= 1 && auswahl <= 4) {
            String eingabeString = String.valueOf(auswahl);
            int leerzeichen = 21 - eingabeString.length(); // Dynamischer Abstand
            System.out.println("┌──────────────────────────────────────────┐");
            System.out.println("│ Welchen Rechenmodus willst du verwenden? │");
            System.out.println("│ 1. Addition                              │");
            System.out.println("│ 2. Subtraktion                           │");
            System.out.println("│ 3. Multiplikation                        │");
            System.out.println("│ 4. Division                              │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.print("│ Wähle zwischen 1-4: " + eingabeString);
            System.out.println(" ".repeat(leerzeichen) + "│");
            System.out.println("└──────────────────────────────────────────┘");
        } else {
            System.out.println("Ungültige Eingabe!");
        }
        scanner.close();
    }
}