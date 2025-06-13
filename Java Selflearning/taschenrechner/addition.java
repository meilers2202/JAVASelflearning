package taschenrechner;

import java.util.Scanner;

public class addition {
    public static void normalstart() {
        Scanner scanner = new Scanner(System.in);
        int addition1 = 0;
        int addition2 = 0;
        int additionsumme = 0;
        System.out.print("Gebe die erste Zahl ein: ");
        addition1 = scanner.nextInt();
        System.out.print("Gebe die zweite Zahl ein: ");
        addition2 = scanner.nextInt();
        clear.clearConsole();
        additionsumme = addition1 + addition2;
        System.out.print(addition1 + "+" + addition2 + "=" + additionsumme);
        scanner.close();
    }
}