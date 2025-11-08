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

	/** 
     * A shared {@link Scanner} instance for reading user input from the console. 
     */
    private static Scanner sc = new Scanner(System.in);

	/**
     * Displays the menu options available to a {@link CompanyRepresentative} 
     * and processes the user's selection.
     * <p>
     * Currently, the menu supports one option:
     * <ul>
     *     <li>1) Logout</li>
     * </ul>
     * </p>
     *
     * @param rep the {@link CompanyRepresentative} who is currently logged in
     * @return {@code null} if the user chooses to log out, 
     *         otherwise returns the same {@code CompanyRepresentative} instance
     */
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

	 /**
     * Displays the menu options available to a {@link CompanyRepresentative} 
     * and processes the user's selection.
     * <p>
     * Currently, the menu supports one option:
     * <ul>
     *     <li>1) Logout</li>
     * </ul>
     * </p>
     *
     * @param rep the {@link CompanyRepresentative} who is currently logged in
     * @return {@code null} if the user chooses to log out, 
     *         otherwise returns the same {@code CompanyRepresentative} instance
     */
    private static String inputString(String text) {
        System.out.println(text);
        String s = sc.nextLine();
        if (s.isEmpty()) {
            System.exit(0);
        }
        return s;
    }

	 /**
     * Prompts the user for an integer input with the given message.
     * <p>
     * If the user enters 0, the program terminates.
     * </p>
     *
     * @param text the prompt message to display
     * @return the integer entered by the user
     */
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
