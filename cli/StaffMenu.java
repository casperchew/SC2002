package cli;

import java.util.Scanner;
import java.util.ArrayList;

import controller.ApplicationController;
import controller.InternshipController;
import controller.UserController;
import model.User;
import model.user.CareerCenterStaff;
import model.user.Student;
import model.internship.*;

/**
 * The {@code StaffMenu} class provides the command-line interface (CLI) menu
 * for {@link CareerCenterStaff} users in the internship management system.
 * <p>
 * It allows staff members to perform administrative functions such as approving
 * or rejecting company representatives, internship opportunities, and student withdrawal requests.
 * Additionally, staff can generate internship reports and view all internship applications.
 * </p>
 * 
 * <p><strong>Usage:</strong></p>
 * <pre>
 *     StaffMenu staffMenu = new StaffMenu(staff, userController, appController, internshipController);
 *     staffMenu.runMenu(staff);
 * </pre>
 * 
 * <p>
 * The menu remains active until the user selects the "Logout" option, 
 * at which point the method returns {@code null}.
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class StaffMenu {

    /** Shared scanner instance for reading user input from the console. */
    private Scanner sc = new Scanner(System.in);

    /** The logged-in {@link CareerCenterStaff} instance currently using the menu. */
    private CareerCenterStaff staff;

    /** Controller for managing user-related operations. */
    private UserController userController;

    /** Controller for handling internship applications. */
    private ApplicationController appController;

    /** Controller for managing internship postings and details. */
    private InternshipController internshipController;

    /**
     * Constructs a {@code StaffMenu} with the specified staff user and controllers.
     *
     * @param staff the {@link CareerCenterStaff} currently logged in
     * @param userController the {@link UserController} used for user-related operations
     * @param appController the {@link ApplicationController} used for internship applications
     * @param internshipController the {@link InternshipController} used for managing internships
     */
    public StaffMenu(
        CareerCenterStaff staff, 
        UserController userController,
        ApplicationController appController,
        InternshipController internshipController
    ) {
        this.staff = staff;
        this.userController = userController;
        this.appController = appController;
        this.internshipController = internshipController;
    }

    /**
     * Displays the menu options available to a {@link CareerCenterStaff} user and 
     * processes their selection.
     * <p>
     * The menu options include:
     * </p>
     * <ul>
     *     <li>1) Approve/reject company representatives</li>
     *     <li>2) Approve/reject internship opportunities</li>
     *     <li>3) Approve/reject student withdrawal requests</li>
     *     <li>4) Generate internship report</li>
     *     <li>5) Logout</li>
     *     <li>6) View all internship applications (for testing)</li>
     * </ul>
     * 
     * <p>
     * If the staff selects "Logout", the method returns {@code null}.
     * Otherwise, it returns the same {@link CareerCenterStaff} instance.
     * </p>
     * 
     * @param staff the {@link CareerCenterStaff} currently logged in
     * @return {@code null} if the user logs out; otherwise returns the current staff instance
     */
    public User runMenu(CareerCenterStaff staff) {
        int choice;
        System.out.println();
        System.out.println("=".repeat(20));
        System.out.println();
        System.out.println("1) Approve/reject company representatives");
        System.out.println("2) Approve/reject internship opportunities");
        System.out.println("3) Approve/reject student withdrawal requests");
        System.out.println("4) Generate internship report");
        System.out.println("5) Logout");
        System.out.println("6) View all internship applications"); // For testing

        choice = inputInt("Enter an option: ");

        switch (choice) {
            case 1:
                System.out.println("Not implemented");
                return staff;
            case 2:
                System.out.println("Not implemented");
                return staff;
            case 3:
                System.out.println("Not implemented");
                return staff;
            case 4:
                System.out.println("Not implemented");
                return staff;
            case 5:
                System.out.println("Logging out...");
                return null;
            case 6:
                ArrayList<InternshipApplication> applications = appController.getInternshipApplications();
                System.out.println();
                System.out.println("=".repeat(20));
                System.out.println();
                for (InternshipApplication application : applications) {
                    System.out.println("Pending internship applications:");
                    System.out.println("Applicant: " + application.getApplicant().getName());
                    System.out.println("Internship Title: " + application.getInternshipOpportunity().getInternshipTitle());
                    System.out.println("Internship Company: " + application.getInternshipOpportunity().getCompanyName());
                    System.out.println();
                }
            default:
                return staff;
        }
    }

    /**
     * Prompts the user for a string input with the given message.
     * <p>
     * If the user enters an empty string, the program terminates.
     * </p>
     *
     * @param text the prompt message to display
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
     * Prompts the user for an integer input with the given message.
     * <p>
     * If the user enters 0, the program terminates.
     * </p>
     *
     * @param text the prompt message to display
     * @return the integer entered by the user
     */
    private int inputInt(String text) {
        System.out.println(text);
        int n = sc.nextInt();
        sc.nextLine(); // Consume leftover newline
        if (n == 0) {
            System.exit(0);
        }
        return n;
    }
}
