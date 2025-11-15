package src.cli;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import src.controller.*;
import src.model.Status;
import src.model.User;
import src.model.internship.*;
import src.model.user.Student;
import src.utils.Utils;

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
        System.out.println("\t3) Set filters."); // TODO
        System.out.println("\t4) Change Password."); // TODO
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
				// Utils.clear();
                System.out.println("Not implemented");
				System.out.println();
                return student;

            case 4:
				// Utils.clear();
                System.out.println("Not implemented");
				System.out.println();
				return student;

            case 5:
				Utils.clear();
				return null;

            default:
				// Utils.clear();
				System.out.println("Invalid option!");
				System.out.println("");
				return student;
		}
    }

    private void applyForInternshipOpportunities() {
        Utils.clear();
        if (student.getInternship() != null) {
            System.out.println("You have already accepted an internship opportunity.");
            System.out.println();
            return;
        }

        boolean loop = true;
        while (loop) {
            // Utils.clear();
            ArrayList<InternshipOpportunity> opportunities = internshipController.getInternshipOpportunities(student);
            if (opportunities.isEmpty()) {
                Utils.clear();
                System.out.println("There are currently no available internship opportunities for you.");
                System.out.println("");
                break;
            }

            ArrayList<InternshipOpportunity> approvedList = new ArrayList<>();
            System.out.println("Select an internship opportunity to apply for: ");
            for (InternshipOpportunity internshipOpp: opportunities) {
                if (Objects.equals(internshipOpp.getStatus(), Status.APPROVED)) {
                    approvedList.add(internshipOpp);
                }
            }

            for (int i = 0; i < approvedList.size(); i++) {
                System.out.println((i + 1) + ") " + approvedList.get(i).getInternshipTitle());
            }

            System.out.println();
            int choice = Utils.inputInt("Enter the number of the the internship opportunity to view options (or -1 to exit): ");
            Utils.clear();

            if (choice == -1) {
                break;
            }

            if (choice < 1 || choice > approvedList.size()) {
                System.out.println("Please select a valid internship opportunity.");
                System.out.println();
                continue;
            }

            InternshipOpportunity chosenInternshipOpp = approvedList.get(choice - 1);

            System.out.println("Title: " + chosenInternshipOpp.getInternshipTitle());
            System.out.println("Description: " + chosenInternshipOpp.getDescription());
            System.out.println("Available slots: " + chosenInternshipOpp.getNumberOfSlots());
            System.out.println("Application closing date: " + chosenInternshipOpp.getApplicationClosingDate());

            System.out.println();
            System.out.println("1) Apply for this internship.");
            System.out.println("2) Select another internship.");
            System.out.println("3) Exit.");
            int subChoice = Utils.inputInt("Enter an option: ");

            switch (subChoice) {
                case 1:
                    Utils.clear();
                    InternshipApplication application = new InternshipApplication(
                        student,
                        chosenInternshipOpp
                    );

                    if (Student.MAX_APPLICATIONS > student.getInternshipApplications().size()) {
                        appController.createApplication(application);
                        student.addInternshipApplications(application);
                    } else {
                        System.out.println("You already have 3 applications pending.");
                        System.out.println();
                    }
                    break;

                case 2:
                    Utils.clear();
                    break;

                case 3:
                    Utils.clear();
                    loop = false;
                    break;
            }
        }
    }

    private void viewInternshipApplications() {
        boolean loop = true;
        Utils.clear();
        while (loop) {
            // if (student.getInternship() != null) {
            //     System.out.println("You have already accepted an internship opportunity.");
            //     System.out.println();
            //     break;
            // }

            if (student.getInternshipApplications().size() == 0) {
                Utils.clear();
                System.out.println("You have no pending applications.");
                System.out.println();
                break;
            }

            // Utils.clear();
            ArrayList<InternshipApplication> apps = student.getInternshipApplications();

            System.out.println("Internship applications: ");
            System.out.println();
            for (int i = 0; i < apps.size(); i++) {
                System.out.println((i + 1) + ") " + apps.get(i).getInternshipOpportunity().getInternshipTitle());
            }

            System.out.println();
            int choice = Utils.inputInt("Enter the number of the the internship application to view options (or -1 to exit): ");

            if (choice == -1) {
                loop = false;
                Utils.clear();
                continue;
            }

            if (choice < 1 || choice > apps.size()) {
                Utils.clear();
                System.out.println("Please select a valid application.");
                System.out.println();
                continue;
            }

            InternshipApplication chosenInternshipApplication = apps.get(choice - 1);

            Utils.clear();
            System.out.println("Internship title: " + chosenInternshipApplication.getInternshipOpportunity().getInternshipTitle());
            System.out.println("Internship description: " + chosenInternshipApplication.getInternshipOpportunity().getDescription());
            System.out.println("Status: " + chosenInternshipApplication.getStatus());
            System.out.println("Placement confimed: " + chosenInternshipApplication.getPlacementConfirmed());
            System.out.println("Withdrawal requested: " + chosenInternshipApplication.getWithdrawalRequested());

            System.out.println("1) Accept placement.");
            System.out.println("2) Request withdrawal.");
            System.out.println("3) Exit.");
            int subChoice = Utils.inputInt("Enter an option: ");

            switch (subChoice) {
                case 1:
                    // If a student chooses an internship opportunity, we set the other applications to rejected and delete them from student.internshipOpportunities
                    // The selected internship opportunity must not be deleted yet because he can still request withdrawal
                    // chosenInternshipApplication.setStatus(Status.APPROVED);
                    if (Objects.equals(chosenInternshipApplication.getStatus(), Status.APPROVED)) {
                        student.setInternship(chosenInternshipApplication.getInternshipOpportunity());
                        for (InternshipApplication application: student.getInternshipApplications()) {
                            if (!(Objects.equals(application, chosenInternshipApplication))) {
                                student.deleteInternshipApplication(application);
                                application.setStatus(Status.REJECTED);
                            } else {
                                application.setPlacementConfirmed(true);
                            }
                        }
                        Utils.clear();
                        System.out.println("Congratulations! You have been hired.");
                        System.out.println();
                        loop = false;
                    } else {
                        Utils.clear();
                        System.out.println("Your application has not yet been approved");
                        System.out.println();
                    }
                    break;

                case 2:
                    Utils.clear();
                    System.out.println("Sending withdrawal request...");
                    System.out.println();
                    chosenInternshipApplication.setWithdrawalRequested(true);
                    break;

                case 3:
                    Utils.clear();
                    loop = false;
                    break;
            }
        }
    }
}
