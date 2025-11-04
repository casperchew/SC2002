package cli;

import java.util.Scanner;

import model.User;
import model.internship.*;
import model.user.Student;
import controller.*;
import java.util.ArrayList;
import java.util.Objects;

public class StudentMenu {
    private static Scanner sc = new Scanner(System.in);
    private Student student;
    private UserController userController;
    private ApplicationController appController;
    private InternshipController internshipController;
    // We need to implement filtering too and the settings need to be saved
    // private ... filter

    public StudentMenu(
        Student student, 
        UserController userController,
        ApplicationController appController,
        InternshipController internshipController
    ) {
        this.student = student;
        this.userController = userController;
        this.appController = appController;
        this.internshipController = internshipController;
    }
   
    public User runMenu() {
        int choice;
        System.out.println();
        System.out.println("=".repeat(20));
        System.out.println();
        // add more options later
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
                // authManager.changePassword(newPassword);
				return student;
            case 3:
                System.out.println("Logging out...");
				return null;
            default:
				return student;
		}
    }

    private void ApplyForOpportunities() {
        boolean loop = true;
        while (loop) {
            // Print the opportunities nicely
            System.out.println();
            System.out.println("=".repeat(20));
            System.out.println();
            ArrayList<InternshipOpportunity> opportunities = internshipController.getInternshipOpportunities(student);
            if (opportunities.isEmpty()) {
                System.out.println("There are currently no available internship opportunities for you.");
                break;
            }
            System.out.println("Select an internship opportunity to apply for: ");
            for (InternshipOpportunity internshipOpp: opportunities) {
                // Need to apply filtering here
                System.out.println("- " + internshipOpp.getInternshipTitle());
            }
            String internshipTitle = inputString("Enter an internship title for more options (or type \"exit\" to leave this menu): ");
            if (Objects.equals(internshipTitle, "exit")) {break;}
            // Find the selected internship opportunity
            InternshipOpportunity chosenInternshipOpp = null;
            for (InternshipOpportunity internshipOpp: opportunities) {
                if (Objects.equals(internshipTitle, internshipOpp.getInternshipTitle())) {
                    chosenInternshipOpp = internshipOpp;
                    break;
                };
            }
            if (Objects.equals(chosenInternshipOpp, null)) {
                System.out.println("Please select a valid internship opportunity.");
                continue;
            }
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
                    // Apply
                    InternshipApplication application = new InternshipApplication(
                        student,
                        chosenInternshipOpp
                    );
                    appController.createApplication(application);
                    break;
                case 2:
                    // Select another internship
                    break;
                case 3:
                    // Exit internship application menu
                    loop = false;
            }
        }
    }

    private String inputString(String text) {
        System.out.println(text);
        String s = sc.nextLine();
        if (s.isEmpty()) {
            System.exit(0);
        }
        return s;
    }

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
