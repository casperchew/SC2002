package src.utils;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * A Utils Class for utility functions
 */
public class Utils {
	private static Scanner scanner = new Scanner(System.in);

	private Utils() {}

	/**
	 * Reads a String from {@code stdin}
	 *
	 * @param text The text displayed before reading input
	 * @return The String from {@code stdin}
	 */
    public static String inputString(String text) {
		System.out.print(text);
		String s = scanner.nextLine();
		if (s.isEmpty()) {
			System.exit(0);
		}

		System.out.println();
		return s;
	}

	/**
	 * Reads an int from {@code stdin}
	 *
	 * @param text The text displayed before reading input
	 * @return The int from {@code stdin}
	 */
    public static int inputInt(String text) {
        System.out.print(text);
        int n = scanner.nextInt();
        if (n == 0) {
            System.exit(0);
        }

        scanner.nextLine();
		System.out.println();
        return n;
	}

	/**
	 * Reads a date from {@code stdin}
	 *
	 * @param text The text displayed before reading input
	 * @return The date from {@code stdin}
	 */
    public static LocalDate inputDate(String text) {
        LocalDate date = null;
        while (date == null) {
            String s = Utils.inputString(text);
            date = LocalDate.parse(s);
        }

        return date;
    }

	/**
	 * Clears the screen
	 */
	public static void clear() {
		System.out.print("\033[?1049h");
		System.out.print("\033[2J");
		System.out.print("\033[H");
	}
}
