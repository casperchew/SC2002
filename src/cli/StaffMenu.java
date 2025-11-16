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
        System.out.println("1) Approve/reject company representatives.");
        System.out.println("2) Approve/reject internship opportunities.");
        System.out.println("3) Approve/reject student withdrawal requests.");
        System.out.println("4) Generate internship opportunity report.");
        System.out.println("5) Set internship opportunity report filters.");  // TODO
        System.out.println("6) View all internship applications.");  // For testing
        System.out.println("7) Logout.");
        choice = Utils.inputInt("Enter an option: ");

        switch (choice) {
            case 1:
                approveCompanyReps();
				return staff;
            case 2:
                approveInternshipOpportunities();
				return staff;
            case 3:
                approveWithdrawalRequests();
				return staff;
            case 4:
                generateOpportunityReport();
				return staff;
            case 5:
                Utils.clear();
                System.out.println("Not implemented");
				return staff;
            case 6:
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

    private void approveCompanyReps() {
        Utils.clear();
        boolean loop = true;
        while (loop) {
            ArrayList<CompanyRepresentative> pendingReps = userController.getCompanyRepresentativesByStatus(Status.PENDING);
            if (pendingReps.isEmpty()) {
                Utils.clear();
                System.out.println("There are no pending company representative accounts.");
                System.out.println();
                
                break;
            }
            
            System.out.println("Company Representative Accounts pending approval: ");
            
            for (int i = 0; i < pendingReps.size(); i++) {
                CompanyRepresentative rep = pendingReps.get(i);
                System.out.println((i + 1) + ") " + rep.getName() + " (" + rep.getCompany() + ")");
            }
            
            CompanyRepresentative chosenRep = null;
            
            System.out.println();
            int choice = Utils.inputInt("Enter the number of the representative to view options (or -1 to exit or -2 to approve all pending accounts): ");
            if (choice == -1) { 
                break; 
            }
            
            if (choice == -2) {
                for (CompanyRepresentative rep: pendingReps) {
                    rep.setStatus(Status.APPROVED);
                }
                Utils.clear();
                System.out.println("All pending company representative accounts have been approved.");
                System.out.println();
                break; 
            }
            
            int index = choice - 1; 
            if (index >= 0 && index < pendingReps.size()) {
                chosenRep = pendingReps.get(index);
            }
            if (chosenRep == null) {
                Utils.clear();
                System.out.println("Invalid selection. Please enter a valid number from the list (1 to " + pendingReps.size() + ").");
                System.out.println();
                continue;
            }

            Utils.clear(); 
            System.out.println("Name: " + chosenRep.getName());
            System.out.println("Company Name: " + chosenRep.getCompany());
            System.out.println("Status: " + chosenRep.getStatus());
            System.out.println();
            
            System.out.println("1) Approve this account.");
            System.out.println("2) Reject this account.");
            System.out.println("3) Select another account.");
            System.out.println("4) Exit.");
            
            int subChoice = Utils.inputInt("Enter an option: ");

            switch (subChoice) {
                case 1: 
                    chosenRep.setStatus(Status.APPROVED);
                    Utils.clear();
                    System.out.println(chosenRep.getName() + "'s account has been approved.");
                    System.out.println();
                    break;
                case 2:
                    chosenRep.setStatus(Status.REJECTED);
                    Utils.clear();
                    System.out.println(chosenRep.getName() + "'s account has been rejected.");
                    System.out.println();
                    break;
                case 3:
                    Utils.clear();
                    continue; 
                case 4:
                    loop = false;
                    Utils.clear();
                    break;
            }
        }
    }

    private void approveInternshipOpportunities() {
        boolean loop = true;

        while (loop) {
            Utils.clear();
            ArrayList<InternshipOpportunity> pendingOpportunities =
                    (ArrayList<InternshipOpportunity>) internshipController.getInternshipOpportunities(Status.PENDING);

            if (pendingOpportunities.isEmpty()) {
                System.out.println("There are no pending internship opportunities.");
                System.out.println();
                break;
            }

            System.out.println("Internship opportunities pending approval: ");
            for (int i = 0; i < pendingOpportunities.size(); i++) {
                System.out.println((i + 1) + ") " + pendingOpportunities.get(i).getInternshipTitle());
            }

            System.out.println();
            int choice = Utils.inputInt(
                    "Enter the number of an internship opportunity (or -1 to exit or -2 to approve all pending opportunities): "
            );

            if (choice == -1) {
                Utils.clear();
                break;
            }

            if (choice == -2) {
                for (InternshipOpportunity opp : pendingOpportunities) {
                    opp.setStatus(Status.APPROVED);
                    opp.setVisibility(true);
                }
                Utils.clear();
                System.out.println("All pending internship opportunities have been approved.");
                System.out.println();
                break;
            }

            int index = choice - 1;
            InternshipOpportunity chosenOpportunity = null;

            if (index >= 0 && index < pendingOpportunities.size()) {
                chosenOpportunity = pendingOpportunities.get(index);
            }

            if (chosenOpportunity == null) {
                Utils.clear();
                System.out.println("Invalid selection. Please enter a valid number from the list (1 to " + pendingOpportunities.size() + ").");
                System.out.println();
                continue;
            }

            Utils.clear();

            String preferredMajors = String.join(", ", chosenOpportunity.getPreferredMajors());
            ArrayList<String> repNames = new ArrayList<>();
            for (CompanyRepresentative rep : chosenOpportunity.getCompanyRepresentatives()) {
                repNames.add(rep.getName());
            }
            String companyReps = String.join(", ", repNames);

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

            int subChoice = Utils.inputInt("Enter an option: ");

            switch (subChoice) {
                case 1:
                    chosenOpportunity.setStatus(Status.APPROVED);
                    chosenOpportunity.setVisibility(true);
                    Utils.clear();
                    System.out.println(chosenOpportunity.getInternshipTitle() + " has been approved.");
                    System.out.println();
                    break;

                case 2:
                    chosenOpportunity.setStatus(Status.REJECTED);
                    chosenOpportunity.setVisibility(false);
                    Utils.clear();
                    System.out.println(chosenOpportunity.getInternshipTitle() + " has been rejected.");
                    System.out.println();
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
        boolean loop = true;
        Utils.clear();
        while (loop) {
            ArrayList<InternshipApplication> applications = new ArrayList<>();
            for (InternshipApplication application : appController.getInternshipApplications()) {
                if (application.getWithdrawalRequested()) {
                    applications.add(application);
                }
            }

            System.out.println();

            if (applications.isEmpty()) {
                Utils.clear();
                System.out.println("There are no pending withdrawal requests.");
                System.out.println();
                break;
            }

            System.out.println("Applicants requesting withdrawal:");
            for (int i = 0; i < applications.size(); i++) {
                InternshipApplication app = applications.get(i);
                System.out.println((i + 1) + ") " + app.getApplicant().getName()
                                + " (" + app.getInternshipOpportunity().getInternshipTitle() + ")");
            }

            System.out.println();
            int choice = Utils.inputInt(
                    "Enter the number of a withdrawal request (or -1 to exit or -2 to approve all requests): "
            );

            if (choice == -1) {
                Utils.clear();
                break;
            }

            if (choice == -2) {
                for (InternshipApplication application : applications) {
                    application.setWithdrawalApproved(true);
                    application.setWithdrawalRequested(false);
                    Student student = application.getApplicant();
                    student.deleteInternshipApplication(application);
                }
                Utils.clear();
                System.out.println("All pending withdrawals have been approved.");
                System.out.println();
                break;
            }

            int index = choice - 1;
            InternshipApplication chosenApplication = null;

            if (index >= 0 && index < applications.size()) {
                chosenApplication = applications.get(index);
            }

            if (chosenApplication == null) {
                Utils.clear();
                System.out.println("Invalid selection. Please enter a valid number.");
                System.out.println();
                continue;
            }

            Utils.clear();

            System.out.println("Applicant name: " + chosenApplication.getApplicant().getName());
            System.out.println("Internship Opportunity Title: " + chosenApplication.getInternshipOpportunity().getInternshipTitle());
            System.out.println("Internship Company Name: " + chosenApplication.getInternshipOpportunity().getCompanyName());
            System.out.println("Date applied: " + chosenApplication.getDateApplied());
            System.out.println("Status: " + chosenApplication.getStatus());
            System.out.println("Placement confirmed: " + chosenApplication.getPlacementConfirmed());
            System.out.println();

            System.out.println("1) Approve this withdrawal request.");
            System.out.println("2) Reject this withdrawal request.");
            System.out.println("3) Select another withdrawal request.");
            System.out.println("4) Exit.");

            int subChoice = Utils.inputInt("Enter an option: ");

            switch (subChoice) {
                case 1:
                    chosenApplication.setWithdrawalApproved(true);
                    chosenApplication.setWithdrawalRequested(false);
                    Student student = chosenApplication.getApplicant();
                    student.deleteInternshipApplication(chosenApplication);
                    student.setInternship(null);
                    Utils.clear();
                    System.out.println(chosenApplication.getApplicant().getName() + "'s withdrawal request has been approved.");
                    System.out.println();
                    break;

                case 2:
                    chosenApplication.setWithdrawalApproved(false);
                    chosenApplication.setWithdrawalRequested(false);
                    Utils.clear();
                    System.out.println(chosenApplication.getApplicant().getName() + "'s withdrawal request has been rejected.");
                    System.out.println();
                    break;

                case 3:
                    Utils.clear();
                    continue;

                case 4:
                    loop = false;
                    Utils.clear();
                    break;
            }
        }
    }

    private void generateOpportunityReport() {
        boolean loop = true;

        while (loop) {
            // Utils.clear();

            ArrayList<InternshipOpportunity> opportunities = internshipController.getInternshipOpportunities();

            if (opportunities.isEmpty()) {
                System.out.println("There are no internship opportunities yet.");
                System.out.println();
                break;
            }

            System.out.println("Internship Opportunities:");
            for (int i = 0; i < opportunities.size(); i++) {
                System.out.println((i + 1) + ") " + opportunities.get(i).getInternshipTitle());
            }

            System.out.println();
            int choice = Utils.inputInt("Enter the number of an internship opportunity to generate the report (or -1 to exit): ");

            if (choice == -1) {
                Utils.clear();
                break;
            }

            int index = choice - 1;
            InternshipOpportunity chosenOpportunity = null;

            if (index >= 0 && index < opportunities.size()) {
                chosenOpportunity = opportunities.get(index);
            }

            if (chosenOpportunity == null) {
                Utils.clear();
                System.out.println("Invalid selection. Please enter a valid number.");
                System.out.println();
                continue;
            }

            Utils.clear();

            String preferredMajors = String.join(", ", chosenOpportunity.getPreferredMajors());
            ArrayList<String> arrReps = new ArrayList<>();
            for (CompanyRepresentative rep : chosenOpportunity.getCompanyRepresentatives()) {
                arrReps.add(rep.getName());
            }
            String companyReps = String.join(", ", arrReps);
            System.out.println("Company name: " + chosenOpportunity.getCompanyName());
            System.out.println("Internship title: " + chosenOpportunity.getInternshipTitle());
            System.out.println("Internship Level: " + chosenOpportunity.getInternshipLevel());
            System.out.println("Description: " + chosenOpportunity.getDescription());
            System.out.println("Preferred Majors: " + preferredMajors);
            System.out.println("Company representatives: " + companyReps);
            System.out.println("Application opening date: " + chosenOpportunity.getApplicationOpeningDate());
            System.out.println("Application closing date: " + chosenOpportunity.getApplicationClosingDate());
            System.out.println("Number of slots: " + chosenOpportunity.getNumberOfSlots());
            System.out.println("Status: " + chosenOpportunity.getStatus());
            System.out.println("Visibility: " + chosenOpportunity.getVisibility());
            System.out.println();

            System.out.println("1) Select another internship opportunity.");
            System.out.println("2) Exit.");

            int subChoice = Utils.inputInt("Enter an option: ");

            switch (subChoice) {
                case 1:
                    Utils.clear();
                    continue;
                case 2:
                    Utils.clear();
                    loop = false;
                    break;
            }
        }
    }


    private void printAllApplications() {
        // Utils.clear();
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
