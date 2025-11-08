package cli;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Objects;

import model.User;
import model.internship.*;
import model.user.Student;
import controller.*;

/**
 * The {@code StudentMenu} class provides the command-line interface (CLI)
 * menu for {@link Student} users in the internship management system.
 * <p>
 * It allows students to browse available internship opportunities, apply for positions,
 * and update their account settings such as changing passwords.
 * </p>
 *
 * <p><strong>Usage:</strong></p>
 * <pre>
 *     StudentMenu studentMenu = new StudentMenu(student, userController, appController, internshipController);
 *     studentMenu.runMenu();
 * </pre>
 *
 * <p>
 * The menu continues to display options until the student logs out, at which point
 * {@link #runMenu()} returns {@code null}.
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class StudentMenu {

    /** Shared scanner instance for reading user input from the console. */
    private static Scanner sc = new Scanner(System.in);

    /** The logged-in {@link Student} currently using the menu. */
    private Student student;

    /** Controller for managing user-related operations such as authentication and password changes. */
    private UserController userController;

    /** Controller for managing internship applications. */
    private ApplicationController appController;

    /** Controller for retrieving and managing internship opportunities. */
    private InternshipController internshipController;

    /**
     * Constructs a {@code StudentMenu} with the specified student and controllers.
     *
     * @param student the {@link Student} currently logged in
     * @param userController the {@link UserController} managing user-related actions
     * @param appController the {@link ApplicationController} handling internship applications
     * @param internshipController the {@link InternshipController} managing internship opportunities
     */
    public StudentMenu(
        Student student, 
        UserController userController,
        ApplicationController appController,
        InternshipController internshipController
		// We need to implement filtering too and the settings need to be saved 
		// private ... filter
    ) {
        this.student = student;
        this.userController = userController;
        this.appController = appController;
        this.internshipController = internshipController;
    }

    /**
     * Displays the main menu for a {@link Student} and processes user selections.
     * <p>
     * Menu options include:
     * </p>
     * <ul>
     *     <li>1) Apply for internship</li>
     *     <li>2) Change Password</li>
     *     <li>3) Logout</li>
     * </ul>
     *
     * <p>
     * Selecting "Logout" ends the session and returns {@code null}, 
     * while other options perform actions and return the same {@code Student} instance.
     * </p>
     *
     * @return {@code null} if the user logs out; otherwise the current {@link Student} instance
     */
    public User runMenu() {
        int choice;
        System.out.println();
        System.out.println("=".repeat(20));
        System.out.println();
        System.out.println("1) Apply for internship");
        System.out.println("2) Change Password");
        System.out.println("3) Logout");
        choice = inputInt("Enter an option: ");

        switch (choice) {
            case 1:
                ApplyForOpportunities();
                return student;
            case 2:
                String newPassword = inputString("Enter new password: ");
                // Future: userController.changePassword(student, newPassword);
                return student;
            case 3:
                System.out.println("Logging out...");
                return null;
            default:
                return student;
        }
    }

    /**
     * Displays a list of available internship opportunities and allows the student to apply.
     * <p>
     * This method retrieves opportunities via the {@link InternshipController}, 
     * displays their details, and enables the student to submit an application 
     * through the {@link ApplicationController}.
     * </p>
     * 
     * <p>
     * Students can type {@code "exit"} at any time to leave the internship selection menu.
     * </p>
     */
    private void ApplyForOpportunities() {
        boolean loop = true;
        while (loop) {
            System.out.println();
            System.out.println("=".repeat(20));
            System.out.println();

            ArrayList<InternshipOpportunity> opportunities = internshipController.getInternshipOpportunities(student);

            if (opportunities.isEmpty()) {
                System.out.println("There are currently no available internship opportunities for you.");
                break;
            }

            System.out.println("Select an internship opportunity to apply for:");
            for (InternshipOpportunity internshipOpp : opportunities) {
                System.out.println("- " + internshipOpp.getInternshipTitle());
            }

            String internshipTitle = inputString("Enter an internship title for more options (or type \"exit\" to leave this menu): ");
            if (Objects.equals(internshipTitle, "exit")) {
                break;
            }

            // Find the selected internship opportunity
            InternshipOpportunity chosenInternshipOpp = null;
            for (InternshipOpportunity internshipOpp : opportunities) {
                if (Objects.equals(internshipTitle, internshipOpp.getInternshipTitle())) {
                    chosenInternshipOpp = internshipOpp;
                    break;
                }
            }

            if (chosenInternshipOpp == null) {
                System.out.println("Please select a valid internship opportunity.");
                continue;
            }

            // Display details of the selected opportunity
            System.out.println();
            System.out.println("=".repeat(20));
            System.out.println();
            System.out.println("Title: " + chosenInternshipOpp.getInternshipTitle());
            System.out.println("Description: " + chosenInternshipOpp.getDescription());
            System.out.println("Available slots: " + chosenInternshipOpp.getNumberOfSlots());
            System.out.println("Application closing date: " + chosenInternshipOpp.getApplicationClosingDate());

            System.out.println();
            System.out.println("1) Apply for this internship");
            System.out.println("2) Select another internship");
            System.out.println("3) Exit");
            int choice = inputInt("Enter an option: ");

            switch (choice) {
                case 1:
                    InternshipApplication application = new InternshipApplication(student, chosenInternshipOpp);
                    appController.createApplication(application);
                    System.out.println("Application submitted successfully.");
                    break;
                case 2:
                    break; // Loop again to select another internship
                case 3:
                    loop = false;
                    break;
            }
        }
    }

    /**
     * Prompts the user for a string input with a given message.
     * <p>
     * If the input is empty, the program terminates immediately.
     * </p>
     *
     * @param text the message to display to the user
     * @return the string entered by the user
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
     * Prompts the user for an integer input with a given message.
     * <p>
     * If the user enters 0, the program terminates immediately.
     * </p>
     *
     * @param text the message to display to the user
     * @return the integer entered by the user
     */
    private int inputInt(String text) {
        System.out.println(text);
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline character
        if (n == 0) {
            System.exit(0);
        }
        return n;
    }
}
