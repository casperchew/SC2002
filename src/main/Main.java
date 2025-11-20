package src.main;

import src.cli.CLI;
import src.controller.Database;

/**
 * The Main class and the entrypoint of the program
 */
public class Main {
	private static Database db = new Database();
	private static CLI cli = new CLI(db);

	/**
	 * Default constructor
	 */
	public Main() {
		// The default constructor is explicitly defined for javadoc
	}

	/**
	 * Entrypoint of the program
	 *
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		cli.main();
	}
}
