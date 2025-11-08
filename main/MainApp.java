package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cli.*;
import controller.*;
import model.user.*;
import model.internship.*;

/**
 * The {@code MainApp} class serves as the entry point for the internship management system.
 * <p>
 * This class initializes the system by loading sample data from CSV files into the
 * in-memory {@link Database}, creating corresponding {@link User} objects,
 * and starting the command-line interface (CLI) for user interaction.
 * </p>
 *
 * <p>
 * The {@code MainApp} currently loads:
 * <ul>
 *   <li>Student data from {@code data/sample_student_list.csv}</li>
 *   <li>Career center staff data from {@code data/sample_staff_list.csv}</li>
 * </ul>
 * Future enhancements will include loading:
 * <ul>
 *   <li>Company representatives</li>
 *   <li>Internship opportunities</li>
 *   <li>Internship applications</li>
 * </ul>
 * </p>
 *
 * <p>
 * The application follows a layered architecture:
 * <ul>
 *   <li><b>Model</b> – Data structures such as {@link Student}, {@link CareerCenterStaff}, etc.</li>
 *   <li><b>Controller</b> – Logic components such as {@link UserController}</li>
 *   <li><b>CLI</b> – Text-based user interface to interact with the system</li>
 * </ul>
 * </p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 *     // Entry point
 *     public static void main(String[] args) {
 *         MainApp.main(args);
 *     }
 * </pre>
 *
 * @author  
 * @version 1.0
 */
public class MainApp {

    /** Shared in-memory database instance used by all controllers. */
	private static Database db = new Database();
	
    /** Controller responsible for managing user-related operations. */
	private static UserController userController = new UserController(db);
    // private static ApplicationController appController = new ApplicationController(db);
    // private static InternshipController = new internshipController(db);
	
    /** Command-line interface (CLI) instance used for user interactions. */
	private static CLI cli = new CLI(db);

    /**
     * The main entry point for launching the application.
     * <p>
     * This method initializes system data by loading CSV files and then
     * starts the CLI to allow users to log in and perform operations.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
	public static void main(String[] args) {
        // System.out.println("testing");
		init();
		cli.main();
	}

    /**
     * Initializes the application by loading default user data from CSV files.
     * <p>
     * This includes reading student and staff lists, creating corresponding
     * {@link User} objects, and registering them with the {@link UserController}.
     * </p>
     *
     * <p>
     * <b>CSV Format Example:</b><br>
     * For students – {@code "StudentID,Name,Major,YearOfStudy"}<br>
     * For staff – {@code "StaffID,Name"}
     * </p>
     *
     * <p>
     * Each user is assigned a default password hash ("password") for demonstration purposes.
     * Future versions should implement secure password hashing and file validation.
     * </p>
     */
	private static void init() {
		// TODO fix by adding to UserController directly
        // ArrayList<Student> students = new ArrayList<Student>();
        // ArrayList<CareerCenterStaff> careerCenterStaffs = new ArrayList<CareerCenterStaff>();
        // ArrayList<CompanyRepresentative> companyRepresentatives = new ArrayList<CompanyRepresentative>();
        // ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();
        // ArrayList<RepresentativeApplication> representativeApplications = new ArrayList<RepresentativeApplication>();
        // ArrayList<InternshipOpportunity> internshipOpportunities = new ArrayList<InternshipOpportunity>();

        // Load Students
        File studentsFile = new File("data/sample_student_list.csv");
        try {
            Scanner studentScanner = new Scanner(studentsFile);
            studentScanner.nextLine(); // Skip header row

            while (studentScanner.hasNextLine()) {
                String[] line = studentScanner.nextLine().split(",");

                // parse the last 7 digits of the StudentID 
                String studentIDString = line[0].substring(1, line[0].length() - 1); 
                int userID = Integer.parseInt(studentIDString); 
                String name = line[1];
                String major = line[2];
                int yearOfStudy = Integer.parseInt(line[3]);
                String passwordHash = "password"; // Default password hash
                Student student = new Student(
                    userID, 
                    name, 
                    passwordHash, 
                    yearOfStudy, 
                    major
                );
				userController.createStudent(student);
            } 
			studentScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Load CareerCenterStaffs
        File staffFile = new File("data/sample_staff_list.csv");
        try {
            Scanner staffScanner = new Scanner(staffFile);
            staffScanner.nextLine(); // Skip header row

            while (staffScanner.hasNextLine()) {
                String[] line = staffScanner.nextLine().split(",");

                String staffIDString = line[0].replaceAll("\\D", ""); // remove non-digit characters
                int userID = Integer.parseInt(staffIDString);
                String name = line[1];
                String passwordHash = "password"; 
                CareerCenterStaff staff = new CareerCenterStaff(userID, name, passwordHash);
                userController.createCareerCenterStaff(staff);
            }
            staffScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }


        // Load the other users here
        // ...

        // Load the pending applications here
        // ...

        // Load created internship opportunities here
        // ...
	}
}
