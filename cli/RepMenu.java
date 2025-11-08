package cli;

import java.util.Scanner;

import model.User;
import model.user.CompanyRepresentative;

/**
 * The {@code RepMenu} class provides a simple command-line interface (CLI) 
 * menu for a {@link CompanyRepresentative} user.
 * <p>
 * This class allows the representative to log out or remain logged in. 
 * It handles user input via a {@link Scanner} and provides helper methods 
 * for safely reading string and integer input.
 * </p>
 * 
 * <p><strong>Usage:</strong></p>
 * <pre>
 *     CompanyRepresentative rep = new CompanyRepresentative(...);
 *     User user = RepMenu.runMenu(rep);
 * </pre>
 * 
 * <p>If the user selects "Logout", the method returns {@code null}. 
 * Otherwise, it returns the same {@code CompanyRepresentative} instance.</p>
 * 
 * @author 
 * @version 1.0
 */
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
