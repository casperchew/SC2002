package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cli.CLI;
import controller.*;
import model.user.*;
import model.internship.*;

public class MainApp {
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
        File studentsFile = new File("data/sample_student_list.csv");
        try {
            Scanner studentScanner = new Scanner(studentsFile);
            studentScanner.nextLine(); // Skip header row

            while (studentScanner.hasNextLine()) {
                String[] line = studentScanner.nextLine().split(",");

                // Parse the last 7 digits of the StudentID 
                String studentIDString = line[0].substring(1, line[0].length() - 1); 
                int userID = Integer.parseInt(studentIDString); 
                String name = line[1];
                String major = line[2];
                int yearOfStudy = Integer.parseInt(line[3]);
                String passwordHash = "password"; // Default password hash
                InternshipOpportunity internship = null; // Set to null for now cuz the csv doesnt have a internship column 
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
