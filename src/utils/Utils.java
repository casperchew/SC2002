package src.utils;

import java.time.LocalDate;
import java.util.Scanner;

public class Utils {
	public static Scanner scanner = new Scanner(System.in);

    public static String inputString(String text) {
		System.out.print(text);
		String s = scanner.nextLine();
		if (s.isEmpty()) {
			System.exit(0);
		}

		System.out.println();
		return s;
	}

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

    public static LocalDate inputDate(String text) {
        LocalDate date = null;
        while (date == null) {
            String s = Utils.inputString(text);
            date = LocalDate.parse(s);
        }

        return date;
    }

	public static void clear() {
		System.out.print("\033[?1049h");
		System.out.print("\033[2J");
		System.out.print("\033[H");
	}
}
