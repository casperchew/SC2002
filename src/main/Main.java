package src.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.cli.CLI;
import src.controller.UserController;
import src.controller.Database;
import src.model.internship.InternshipOpportunity;
import src.model.user.Student;
import src.model.user.CareerCenterStaff;

public class Main {
	private static Database db = new Database();
	private static UserController userController = new UserController(db);
	private static CLI cli = new CLI(db);

	public static void main(String[] args) {
		init();
		cli.main();
	}

	private static void init() {
		// TODO fix by adding to UserController directly
        // ArrayList<Student> students = new ArrayList<Student>();
        // ArrayList<CareerCenterStaff> careerCenterStaffs = new ArrayList<CareerCenterStaff>();
        // ArrayList<CompanyRepresentative> companyRepresentatives = new ArrayList<CompanyRepresentative>();
        // ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();
        // ArrayList<RepresentativeApplication> representativeApplications = new ArrayList<RepresentativeApplication>();
        // ArrayList<InternshipOpportunity> internshipOpportunities = new ArrayList<InternshipOpportunity>();

        // Load Students
        File studentsFile = new File("src/data/sample_student_list.csv");
        try {
            Scanner studentScanner = new Scanner(studentsFile);
            studentScanner.nextLine(); // Skip header row

            while (studentScanner.hasNextLine()) {
                String[] line = studentScanner.nextLine().split(",");

                String userID = line[0];
                String name = line[1];
                String major = line[2];
                int yearOfStudy = Integer.parseInt(line[3]); // TODO: error handling
                String passwordHash = "password";  // Default password hash
                InternshipOpportunity internship = null; // TODO: change Student contructor to allow an optional parameter for internship
                Student student = new Student(
                    userID, 
                    name, 
                    passwordHash, 
                    yearOfStudy, 
                    major,
                    internship
                );

				userController.createStudent(student);
            } 

			studentScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Load CareerCenterStaffs
        File staffFile = new File("src/data/sample_staff_list.csv");
        try {
            Scanner staffScanner = new Scanner(staffFile);
            staffScanner.nextLine(); // Skip header row

            while (staffScanner.hasNextLine()) {
                String[] line = staffScanner.nextLine().split(",");

                String userID = line[0];
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
