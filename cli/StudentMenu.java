package cli;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import controller.*;
import model.User;
import model.Status;
import model.internship.*;
import model.user.Student;
import utils.Utils;

public class StudentMenu {
    private static Scanner sc = new Scanner(System.in);
    private Student student;
    private UserController userController;
    private ApplicationController appController;
    private InternshipController internshipController;

	// TODO filters

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

        System.out.println("\t1) Apply for internship.");
        System.out.println("\t2) View internship applications.");
        System.out.println("\t3) Set filters.");
        System.out.println("\t4) Change Password.");
        System.out.println("\t5) Logout.");
        System.out.println("");
        choice = Utils.inputInt("Enter an option: ");
        switch (choice) {
            case 1:
                applyForInternshipOpportunities();
				return student;

            case 2:
                viewInternshipApplications();
                return student;

            case 3:
				Utils.clear();
                System.out.println("Not implemented");
				System.out.println();
                return student;

            case 4:
				Utils.clear();
                System.out.println("Not implemented");
				System.out.println();
				return student;

            case 5:
				return null;

            default:
				return student;
		}
    }

    private void applyForInternshipOpportunities() {
        if (student.getInternship() != null) {
            System.out.println();
            System.out.println("You have already accepted an internship opportunity.");
            return;
        }

        boolean loop = true;
        while (loop) {
            ArrayList<InternshipOpportunity> opportunities = internshipController.getInternshipOpportunities(student);
            if (opportunities.isEmpty()) {
				Utils.clear();
                System.out.println("There are currently no available internship opportunities for you.");
				System.out.println("");
                break;
            }

            System.out.println("Select an internship opportunity to apply for: ");
            for (InternshipOpportunity internshipOpp: opportunities) {
                // Need to apply filtering here
                if (Objects.equals(internshipOpp.getStatus(), Status.APPROVED)) {
                    System.out.println("- " + internshipOpp.getInternshipTitle());
                } 
            }
            String internshipTitle = Utils.inputString("Enter an internship title for more options (or type \"exit\" to leave this menu): ");
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

            System.out.println("Title: " + chosenInternshipOpp.getInternshipTitle());
            System.out.println("Description: " + chosenInternshipOpp.getDescription());
            System.out.println("Available slots: " + chosenInternshipOpp.getNumberOfSlots());
            System.out.println("Application closing date: " + chosenInternshipOpp.getApplicationClosingDate());

            System.out.println();
            System.out.println("1) Apply for this internship.");
            System.out.println("2) Select another internship.");
            System.out.println("3) Exit.");
            int choice = Utils.inputInt("Enter an option: ");
            switch (choice) {
                case 1:
                    InternshipApplication application = new InternshipApplication(
                        student,
                        chosenInternshipOpp
                    );

                    if (Student.MAX_APPLICATIONS > student.getInternshipApplications().size()) {
                        appController.createApplication(application);
                        student.addInternshipApplications(application);
                    } else {
                        System.out.println();
                        System.out.println("You already have 3 applications pending.");
                    }
                    break;

                case 2:
                    // Select another internship
                    break;

                case 3:
                    loop = false;
                    break;
            }
        }
    }

    private void viewInternshipApplications() {
        boolean loop = true;
        while (loop) {
            if (student.getInternship() != null) {
                System.out.println("You have already accepted an internship opportunity.");
            }

            if (student.getInternshipApplications().size() == 0) {
				Utils.clear();
                System.out.println("You have no pending applications.");
				System.out.println();
                break; 
            }

			Utils.clear();
            System.out.println("Internship applications: ");
            System.out.println();
            for (InternshipApplication application: student.getInternshipApplications()) {
                System.out.println("- " + application.getInternshipOpportunity().getInternshipTitle());
            }
            InternshipApplication chosenInternshipApplication = null;
            System.out.println();
            String chosenInternshipTitle = Utils.inputString("Enter an internship application name for more options (or type \"exit\" to leave this menu): ");
            if (Objects.equals(chosenInternshipTitle, "exit")) {loop = false;}
            for (InternshipApplication application: student.getInternshipApplications()) {
                if (Objects.equals(
                    chosenInternshipTitle, 
                    application.getInternshipOpportunity().getInternshipTitle()
                )) {
                    chosenInternshipApplication = application;
                }
            }
            if (chosenInternshipApplication == null) { 
                System.out.println("Please select a valid application.");
                continue;
            }
            System.out.println();
            System.out.println("=".repeat(20));
            System.out.println();
            System.out.println("Internship title: " + chosenInternshipApplication.getInternshipOpportunity().getInternshipTitle());
            System.out.println("Internship description: " + chosenInternshipApplication.getInternshipOpportunity().getDescription());
            System.out.println("Status: " + chosenInternshipApplication.getStatus());
            System.out.println("Withdrawal requested: " + chosenInternshipApplication.getWithdrawalRequested());
            
            System.out.println("1) Accept placement.");
            System.out.println("2) Request withdrawal.");
            System.out.println("3) Exit.");
            int choice = Utils.inputInt("Enter an option: ");
            switch (choice) {
                case 1:
                    // chosenInternshipApplication.setStatus(Status.APPROVED); // Just for testing
                    if (Objects.equals(chosenInternshipApplication.getStatus(), Status.APPROVED)) {
                        // chosenInternshipApplication.setPlacementConfirmed(true);
                        student.setInternshipOpportunity(chosenInternshipApplication.getInternshipOpportunity());
                        // Delete all his other applications
                        for (InternshipApplication application: student.getInternshipApplications()) {
                            student.deleteInternshipApplication(application);
                            // appController.deleteApplication(application);
                            application.setPlacementConfirmed(true);
                            // System.out.println("Deleting: " + application.getInternshipOpportunity().getInternshipTitle());
                        };
                        System.out.println();
                        System.out.println("Congratulations! You have been hired.");
                        loop = false;
                        // we probably need to keep track of the number of students accepted since InternshipOpportunity has MAX_NUM_SLOTS
                    } else {
                        System.out.println();
                        System.out.println("Your application has not yet been approved");
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Sending withdrawal request...");
                    chosenInternshipApplication.setWithdrawalRequested(true);
                    break;
                case 3:
                    loop = false;
            }
        }
    }
}
