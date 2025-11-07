package cli;

import java.util.Scanner;
import java.util.ArrayList;

import controller.ApplicationController;
import controller.InternshipController;
import controller.UserController;
import model.Status;
import model.User;
import model.user.CareerCenterStaff;
import model.user.CompanyRepresentative;
import model.user.Student;
import model.internship.*;
import java.util.Objects;

public class StaffMenu {
    private Scanner sc = new Scanner(System.in);
    private CareerCenterStaff staff;
    private UserController userController;
    private ApplicationController appController;
    private InternshipController internshipController;

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

    public User runMenu(CareerCenterStaff staff) {
        int choice;
        System.out.println();
        System.out.println("=".repeat(20));
        System.out.println();
        System.out.println("1) Approve/reject company representatives.");
        System.out.println("2) Approve/reject internship opportunities.");
        System.out.println("3) Approve/reject student withdrawal requests.");
        System.out.println("4) Generate internship opportunity report.");
        System.out.println("5) Set internship opportunity report filters.");
        System.out.println("6) View all internship applications."); // for testing
        System.out.println("7) Logout.");
        choice = inputInt("Enter an option: ");

        switch (choice) {
            case 1:
                System.out.println("Not implemented");
                approveCompanyReps();
				return staff;
            case 2:
                approveInternshipOpportunities();
				return staff;
            case 3:
                System.out.println("Not implemented");
                approveWithdrawalRequests();
				return staff;
            case 4:
                System.out.println("Not implemented");
                generateOpportunityReport();
				return staff;
            case 5:
                System.out.println("Not implemented");
				return staff;
            case 6:
                printAllApplications();
                return staff;
            case 7:
                System.out.println("Logging out...");
				return null;
            default:
				return staff;
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

    private void approveCompanyReps() {}

    private void approveInternshipOpportunities() {
        boolean loop = true;
        while (loop) {
            System.out.println();
            System.out.println("=".repeat(20));
            System.out.println();
            if (internshipController.getInternshipOpportunities(Status.PENDING).isEmpty()) {
                System.out.println("There are no pending internship opportunities.");
                System.out.println();
                break;
            }

            for (InternshipOpportunity opportunity: internshipController.getInternshipOpportunities(Status.PENDING)) {
                System.out.println("- " + opportunity.getInternshipTitle());
            }
            // set visible and set status as approved
            InternshipOpportunity chosenOpportunity = null;
            String chosenOpportunityTitle = inputString("Enter an internship opportunity name for more options (or type \"exit\" to leave this menu or type \"approve all\" to approve all pending opportunities): ");
            if (Objects.equals(chosenOpportunityTitle, "exit")) { break; }
            if (Objects.equals(chosenOpportunityTitle, "approve all")) {
                for (InternshipOpportunity opportunity: internshipController.getInternshipOpportunities(Status.PENDING)) {
                    opportunity.setStatus(Status.APPROVED);
                    opportunity.setVisibility(true);
                }
            }
            for (InternshipOpportunity opportunity: internshipController.getInternshipOpportunities(Status.PENDING)) {
                if (Objects.equals(opportunity.getInternshipTitle(), chosenOpportunityTitle)) {
                    chosenOpportunity = opportunity;
                }
            }
            if (chosenOpportunity == null) {
                System.out.println();
                System.out.println("Please select a valid internship opportunity.");
                continue;
            }

            String preferredMajors = String.join(", ", chosenOpportunity.getPreferredMajors());
            ArrayList<String> arrReps = new ArrayList<String>();
            for (CompanyRepresentative rep: chosenOpportunity.getCompanyRepresentatives()) { arrReps.add(rep.getName()); }
            String companyReps = String.join(",", arrReps);
            System.out.println();
            System.out.println("Company name: " + chosenOpportunity.getCompanyName());
            System.out.println("Internship title: " + chosenOpportunity.getInternshipTitle());
            System.out.println("Description: " + chosenOpportunity.getDescription());
            System.out.println("Preferred Majors: " + preferredMajors);
            System.out.println("Company representatives: " + companyReps);
            System.out.println("Application opening date: " + chosenOpportunity.getApplicationOpeningDate());
            System.out.println("Application closing date: " + chosenOpportunity.getApplicationClosingDate());
            System.out.println("Number of slots: " + chosenOpportunity.getNumberOfSlots());
            System.out.println();
            System.out.println("1) Approve this internship opportunity.");
            System.out.println("2) Reject this internship opportunity.");
            System.out.println("3) Select another internship opportunity.");
            System.out.println("4) Exit.");
            int choice = inputInt("Enter an option: ");

            switch (choice) {
                case 1: 
                    chosenOpportunity.setStatus(Status.APPROVED);
                    chosenOpportunity.setVisibility(true);
                    break;
                case 2:
                    chosenOpportunity.setStatus(Status.REJECTED);
                    chosenOpportunity.setVisibility(false);
                    break;
                case 3:
                    continue;
                case 4:
                    loop = false;
                    break;
            }
        }
    }

    private void approveWithdrawalRequests() {
        // Remember to delete the application from Student.internshipApplications and set the withdrawalApproved to true 
    }
    private void generateOpportunityReport() {}

    private void printAllApplications() {
        ArrayList<InternshipApplication> applications = appController.getInternshipApplications();
        System.out.println();
        System.out.println("=".repeat(20));
        System.out.println();
        for (InternshipApplication application: applications) {
            System.out.println("Pending internship applications: ");
            System.out.println("Applicant: " + application.getApplicant().getName());
            System.out.println("Internship title: " + application.getInternshipOpportunity().getInternshipTitle());
            System.out.println("Internship company: " + application.getInternshipOpportunity().getCompanyName());
            System.out.println("Placement confirmed: "+ application.getPlacementConfirmed());
            System.out.println("Withdrawal requested: " + application.getWithdrawalRequested());
            System.out.println();
        }
    }
}
