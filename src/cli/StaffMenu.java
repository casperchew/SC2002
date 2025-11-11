package src.cli;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import src.controller.ApplicationController;
import src.controller.InternshipController;
import src.controller.UserController;
import src.model.Status;
import src.model.User;
import src.model.internship.*;
import src.model.user.CareerCenterStaff;
import src.model.user.CompanyRepresentative;
import src.model.user.Student;
import src.utils.Utils;

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
        // System.out.println("=".repeat(20));
        // System.out.println();
        System.out.println("1) Approve/reject company representatives.");
        System.out.println("2) Approve/reject internship opportunities."); // done
        System.out.println("3) Approve/reject student withdrawal requests.");
        System.out.println("4) Generate internship opportunity report.");
        System.out.println("5) Set internship opportunity report filters.");
        System.out.println("6) View all internship applications."); // done (for testing)
        System.out.println("7) Logout."); // done
        choice = inputInt("Enter an option: ");

        switch (choice) {
            case 1:
                Utils.clear();
                System.out.println("Not implemented");
                approveCompanyReps();
				return staff;
            case 2:
                Utils.clear();
                approveInternshipOpportunities();
				return staff;
            case 3:
                Utils.clear();
                approveWithdrawalRequests();
				return staff;
            case 4:
                Utils.clear();
                System.out.println("Not implemented");
                generateOpportunityReport();
				return staff;
            case 5:
                Utils.clear();
                System.out.println("Not implemented");
				return staff;
            case 6:
                Utils.clear();
                printAllApplications();
                return staff;
            case 7:
                Utils.clear();
                System.out.println("Logging out...");
				return null;
            default:
                Utils.clear();
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
        Utils.clear();
        // System.out.println("=".repeat(20));
        System.out.println();
        
        if (internshipController.getInternshipOpportunities(Status.PENDING).isEmpty()) {
            System.out.println("There are no pending internship opportunities.");
            System.out.println();
            break;
        }

        System.out.println("Internship opportunities pending approval: ");
        for (InternshipOpportunity opportunity: internshipController.getInternshipOpportunities(Status.PENDING)) {
            System.out.println("- " + opportunity.getInternshipTitle());
        }
        
        InternshipOpportunity chosenOpportunity = null;
        String chosenOpportunityTitle = inputString("Enter an internship opportunity name for more options (or type \"exit\" to leave this menu or type \"approve all\" to approve all pending opportunities): ");
        
        if (Objects.equals(chosenOpportunityTitle, "exit")) { 
            break; 
        }
        
        if (Objects.equals(chosenOpportunityTitle, "approve all")) {
            for (InternshipOpportunity opportunity: internshipController.getInternshipOpportunities(Status.PENDING)) {
                opportunity.setStatus(Status.APPROVED);
                opportunity.setVisibility(true);
            }
            Utils.clear(); 
            System.out.println("All pending internship opportunities have been approved.");
            break; 
        }
        
        for (InternshipOpportunity opportunity: internshipController.getInternshipOpportunities(Status.PENDING)) {
            if (Objects.equals(opportunity.getInternshipTitle(), chosenOpportunityTitle)) {
                chosenOpportunity = opportunity;
            }
        }
        
        if (chosenOpportunity == null) {
            Utils.clear(); 
            System.out.println("Please select a valid internship opportunity.");
            continue;
        }

        Utils.clear(); 

        String preferredMajors = String.join(", ", chosenOpportunity.getPreferredMajors());
        ArrayList<String> arrReps = new ArrayList<String>();
        for (CompanyRepresentative rep: chosenOpportunity.getCompanyRepresentatives()) { arrReps.add(rep.getName()); }
        String companyReps = String.join(",", arrReps);
        
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
                Utils.clear(); 
                System.out.println(chosenOpportunity.getInternshipTitle() + " has been approved.");
                break;
            case 2:
                chosenOpportunity.setStatus(Status.REJECTED);
                chosenOpportunity.setVisibility(false);
                Utils.clear();
                System.out.println(chosenOpportunity.getInternshipTitle() + " has been rejected.");
                break;
            case 3:
                continue; // Loop will restart and clear the screen
            case 4:
                loop = false;
                break;
            }
        }
    }

    private void approveWithdrawalRequests() {
        boolean loop = true;
        while (loop) {
            Utils.clear(); 

            ArrayList<InternshipApplication> applications = new ArrayList<InternshipApplication>();
            for (InternshipApplication application: appController.getInternshipApplications()) {
                if (application.getWithdrawalRequested() && !(application.getPlacementConfirmed())) {
                    applications.add(application);
                }
            }
            
            System.out.println();
            
            if (applications.isEmpty()) {
                System.out.println("There are no pending withdrawal requests.");
                System.out.println();
                break;
            }
            
            System.out.println("Applicants requesting withdrawal: ");
            for (InternshipApplication application: applications) {
                System.out.println("- " + application.getApplicant().getName() + ": " + application.getInternshipOpportunity().getInternshipTitle());
            }
            
            InternshipApplication chosenApplication = null;
            String chosenApplicantAndTitle = inputString("Enter the \"{applicant name}: {internship title}\" for more options (or type \"exit\" to leave this menu or type \"approve all\" to approve all withdrawal requests): ");
            
            if (Objects.equals(chosenApplicantAndTitle, "exit")) { 
                Utils.clear();
                break; 
            }
            
            if (Objects.equals(chosenApplicantAndTitle, "approve all")) {
                for (InternshipApplication application: applications) {
                    application.setWithdrawalApproved(true);
                    application.setWithdrawalRequested(false);
                    Student student = application.getApplicant();
                    student.deleteInternshipApplication(application); 
                }
                Utils.clear(); 
                System.out.println("All pending withdrawals have been approved.");
                break; 
            }
            String chosenApplicant = null;
            String internshipTitle = null;
            int delimiterIndex = chosenApplicantAndTitle.indexOf(":");
            
            // Here we need to split the user input into applicant name and the internship title
            if (delimiterIndex > 0) {
                chosenApplicant = chosenApplicantAndTitle.substring(0, delimiterIndex).trim();
                internshipTitle = chosenApplicantAndTitle.substring(delimiterIndex + 1).trim();
            } else {
                // Invalid format (not "exit", not "approve all", and no colon)
                Utils.clear();
                continue; 
            }

            for (InternshipApplication application: applications) {
                if (Objects.equals(chosenApplicant, application.getApplicant().getName()) 
                    && Objects.equals(internshipTitle, application.getInternshipOpportunity().getInternshipTitle())) {
                    chosenApplication = application;
                }
            }
            
            if (chosenApplication == null) {
                Utils.clear(); 
                System.out.println("Please select a valid applicant and internship title.");
                continue;
            }
            
            Utils.clear(); 

            System.out.println("Applicant name: " + chosenApplication.getApplicant().getName());
            
            System.out.println("Internship Opportunity Title: " + chosenApplication.getInternshipOpportunity().getInternshipTitle());
            System.out.println("Internship Company Name: " + chosenApplication.getInternshipOpportunity().getCompanyName());
            System.out.println("Date applied: " + chosenApplication.getDateApplied());
            System.out.println("Status: " + chosenApplication.getStatus());
            System.out.println();
            System.out.println("1) Approve this withdrawal request.");
            System.out.println("2) Reject this withdrawal request.");
            System.out.println("3) Select another withdrawal request.");
            System.out.println("4) Exit.");
            int choice = inputInt("Enter an option: ");      
            
            switch (choice) {
            case 1: 
                chosenApplication.setWithdrawalApproved(true);
                chosenApplication.setWithdrawalRequested(false);
                Student student = chosenApplication.getApplicant();
                student.deleteInternshipApplication(chosenApplication);
                Utils.clear(); 
                System.out.println(chosenApplication.getApplicant().getName() + "'s withdrawal request has been approved.");
                break;
            case 2:
                chosenApplication.setWithdrawalApproved(false);
                chosenApplication.setWithdrawalRequested(false);
                Utils.clear(); 
                System.out.println(chosenApplication.getApplicant().getName() + "'s withdrawal request has been rejected.");
                break;
            case 3:
                continue;
            case 4:
                loop = false;
                Utils.clear();
                break;
            }
        }
    }

    private void generateOpportunityReport() {}

    private void printAllApplications() {
        Utils.clear();
        ArrayList<InternshipApplication> applications = appController.getInternshipApplications();
        System.out.println();
        // System.out.println("=".repeat(20));
        // System.out.println();
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
