package cli;

import java.util.ArrayList;
import java.util.Scanner;
import controller.*;
import model.*;
import model.internship.InternshipApplication;
import model.user.CareerCenterStaff;
import model.user.CompanyRepresentative;
import model.user.Student;
import java.util.List;

/**
 * The {@code CLI} class provides the main command-line interface for the internship
 * management system. It handles user authentication, account creation, and menu
 * navigation for different user roles, including {@link Student}, {@link CompanyRepresentative},
 * and {@link CareerCenterStaff}.
 * <p>
 * Depending on the type of the logged-in user, it launches the corresponding
 * submenu (e.g., {@code StudentMenu}, {@code RepMenu}, {@code StaffMenu}).
 * </p>
 * 
 * <p><strong>Usage:</strong></p>
 * <pre>
 *     Database db = new Database();
 *     CLI cli = new CLI(db);
 *     cli.main();
 * </pre>
 * 
 * <p>
 * The program continues to run until the user logs out or exits.
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class CLI {

    /** Scanner instance used for reading user input from the console. */
    private Scanner sc = new Scanner(System.in);

    /** Controller for managing user-related operations such as login and registration. */
    private UserController userController;

    /** Controller for handling internship applications. */
    private ApplicationController appController;

    /** Controller for managing internship postings and related data. */
    private InternshipController internshipController;

    /**
     * Constructs a {@code CLI} instance and initializes the system controllers.
     *
     * @param db the shared {@link Database} instance used by all controllers
     */
    public CLI(Database db) {
        this.userController = new UserController(db);
        this.appController = new ApplicationController(db);
        this.internshipController = new InternshipController(db);
    }

	 /**
     * Launches the main CLI loop of the system.
     * <p>
     * This method repeatedly presents menus to the user, allowing login, account creation,
     * and navigation to role-specific menus until the user exits the program.
     * </p>
     * 
     * <ul>
     *     <li>If no user is logged in, options include:
     *         <ul>
     *             <li>Login</li>
     *             <li>Create a new account</li>
     *             <li>Exit the program</li>
     *         </ul>
     *     </li>
     *     <li>Once logged in, users are redirected to their respective menus based on role:</li>
     *         <ul>
     *             <li>{@link Student} → {@code StudentMenu}</li>
     *             <li>{@link CompanyRepresentative} → {@code RepMenu}</li>
     *             <li>{@link CareerCenterStaff} → {@code StaffMenu}</li>
     *         </ul>
     * </ul>
     */
    public void main() {
        int choice;
        int userID;
        String userIDString;
        String name;
        String password;
        User user = null;

        boolean loop = true;
        System.out.println("To exit or logout of the program at any time, press ENTER");
        do {
            if (user == null) {
				System.out.println();
				System.out.println("=".repeat(20));
				System.out.println();
				System.out.println("1) Login");
				System.out.println("2) Create a new account");
                System.out.println("3) Exit");
				choice = inputInt("Select an option: ");
                switch (choice) {
                    // login
                    case 1:
                        System.out.println();
                        System.out.println("=".repeat(20));
                        System.out.println();
                        name = inputString("Enter your name: ");
                        password = inputString("Enter your password: ");

						user = userController.login(name, password);  // Returns null if cannot login

                        if (user == null) {
                            System.out.println("Account not found!");
                        } else {
							System.out.println("Logged in as " + user.getName());
						}

                        break;

                    // create new account
                    case 2:
                        System.out.println();
                        System.out.println("=".repeat(20));
                        System.out.println();
                        System.out.println("Are you a:");
                        System.out.println("1) Student");
                        System.out.println("2) Company Representative");
                        System.out.println("3) Career Center Staff");
                        choice = inputInt("Enter a choice: ");

                        switch (choice) {
                            case 1:
                                System.out.println();
                                System.out.println("=".repeat(20));
                                System.out.println();

                                userIDString = inputString("Enter your student ID: ");
                                userID = Integer.parseInt(userIDString.substring(1, userIDString.length() - 1));
                                name = inputString("Enter your name: ");
                                password = inputString("Enter your password: ");
                                int yearOfStudy = inputInt("Enter your year: ");
                                String major = inputString("Enter your major: ");
                                Student s = new Student(userID, name, password, yearOfStudy, major);
                                userController.createStudent(s);
                                break;
                            case 2:
                                System.out.println("Not implemented");
                                break;
                            case 3:
                                System.out.println("Not implemented");
                                break;
                        }
                        break;
                    
                    case 3:
                        loop = false;
                    default:
                        break;
                }
            } else if (user instanceof Student) {
                // render Student Menu
                Student student = (Student)user;
                StudentMenu studentMenu = new StudentMenu(student, userController, appController, internshipController);
                user = studentMenu.runMenu();
            } else if (user instanceof CompanyRepresentative) {
                CompanyRepresentative rep = (CompanyRepresentative)user;
                switch (rep.getStatus()) {
                    case Status.APPROVED:
                        // render the CompanyRepresentative menu if his application was accepted
                        user = RepMenu.runMenu(rep); // need to be changed to non static
                        break;
                    case Status.PENDING:
                        System.out.println("Your application is pending");
                        break;
                    case Status.REJECTED:
                        System.out.println("Your application has been rejected");
                        break;
                    case Status.FILLED:
                        System.out.println("Your application has been filled");
                        break;
                }

            } else if (user instanceof CareerCenterStaff) {
                // render CareerCenterStaff Menu
                CareerCenterStaff staff = (CareerCenterStaff)user;
                StaffMenu staffMenu = new StaffMenu(staff, userController, appController, internshipController);
                user = staffMenu.runMenu(staff); // need to be changed to non static

            } else {
                System.out.println("Not implemented");
                loop = false;
            }


        } while (loop);
    }


    /**
     * Prompts the user to input a string value with a specified message.
     * <p>
     * If the user presses ENTER without entering text, the program exits.
     * </p>
     *
     * @param text the message to display to the user
     * @return the string input entered by the user
     */
    private String inputString(String text) {
		System.out.println(text);
		String s = sc.nextLine();
		if (s.isEmpty()) {
			System.exit(0);
		}
		return s;
	}

	 /**
     * Prompts the user to input an integer value with a specified message.
     * <p>
     * If the user enters 0, the program terminates.
     * </p>
     *
     * @param text the message to display to the user
     * @return the integer input entered by the user
     */
    private int inputInt(String text) {
        System.out.println(text);
        int n = sc.nextInt();
        sc.nextLine();
        if (n == 0) {
            System.exit(0);
        }
        
        return n;
	}
}
