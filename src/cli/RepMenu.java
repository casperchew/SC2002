package src.cli;

import java.util.Scanner;

import src.model.User;
import src.model.user.CompanyRepresentative;

public class RepMenu {
    private static Scanner sc = new Scanner(System.in);

    public static User runMenu(CompanyRepresentative rep) {
        int choice;
        System.out.println();
        System.out.println("=".repeat(20));
        System.out.println();
        System.out.println("1) Logout");
        choice = inputInt("Enter an option: ");
        switch (choice) {
            case 1:
                System.out.println("Logging out...");
				return null;
            default:
				return rep;
        }
    }

    private static String inputString(String text) {
        System.out.println(text);
        String s = sc.nextLine();
        if (s.isEmpty()) {
            System.exit(0);
        }
        return s;
    }

    private static int inputInt(String text) {
        System.out.println(text);
        int n = sc.nextInt();
        sc.nextLine();
        if (n == 0) {
            System.exit(0);
        }
        
        return n;
	}
}
