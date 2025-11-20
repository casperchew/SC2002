package src.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import src.controller.ApplicationController;
import src.controller.InternshipOpportunityController;
import src.controller.UserController;
import src.enums.Status;
import src.enums.InternshipLevel;
import src.model.User;
import src.model.internship.InternshipApplication;
import src.model.internship.InternshipOpportunity;
import src.model.user.CareerCenterStaff;
import src.model.user.CompanyRepresentative;
import src.model.user.Student;
import src.utils.Utils;

public class StaffMenu {
    private CareerCenterStaff staff;
    private UserController userController;
    private ApplicationController appController;
    private InternshipOpportunityController internshipOpportunityController;

    public StaffMenu(
        CareerCenterStaff staff, 
        UserController userController,
        ApplicationController appController,
        InternshipOpportunityController internshipOpportunityController
    ) {
        this.staff = staff;
        this.userController = userController;
        this.appController = appController;
        this.internshipOpportunityController = internshipOpportunityController;
    }

    public User runMenu(CareerCenterStaff staff) {
        int choice;
        System.out.println("1) Approve/reject company representatives.");
        System.out.println("2) Approve/reject internship opportunities.");
        System.out.println("3) Approve/reject student withdrawal requests.");
        System.out.println("4) Generate internship opportunity report.");
        System.out.println("5) Set internship opportunity report filters.");  // TODO
        System.out.println("6) View all internship applications.");  // For testing
        System.out.println("7) Change password.");
        System.out.println("8) Logout.");
		System.out.println();
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
                setFilters();
				return staff;
            case 6:
                printAllApplications();
                return staff;
            case 7:
                changePassword();  
                return null;
            case 8:
                Utils.clear();
				System.out.println("Logging out...");
				System.out.println();
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
                System.out.println("There are no pending Company Representative accounts.");
                System.out.println();
                
                break;
            }
            
            System.out.println("Company Representative Accounts pending approval: ");
            
            for (int i = 0; i < pendingReps.size(); i++) {
                CompanyRepresentative rep = pendingReps.get(i);
                System.out.println((i + 1) + ") " + rep.getName() + " (" + rep.getCompany() + ")");
            }
            
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
            
			CompanyRepresentative chosenRep = null;
			try {
				chosenRep = pendingReps.get(choice - 1);
			} catch (IndexOutOfBoundsException e) {
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
                    Utils.clear();
                    break;
            }
        }
    }

    private void approveInternshipOpportunities() {
        boolean loop = true;

        while (loop) {
            Utils.clear();
            ArrayList<InternshipOpportunity> pendingOpportunities = internshipOpportunityController.getInternshipOpportunitiesByStatus(Status.PENDING);

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
            int choice = Utils.inputInt("Enter the number of an internship opportunity (or -1 to exit or -2 to approve all pending opportunities): ");
            if (choice == -1) {
                Utils.clear();
                break;
            }

            if (choice == -2) {
                for (InternshipOpportunity opp: pendingOpportunities) {
                    opp.setStatus(Status.APPROVED);
                }

                Utils.clear();
                System.out.println("All pending internship opportunities have been approved.");
                System.out.println();
                break;
            }

            InternshipOpportunity chosenOpportunity = null;
			try {
				chosenOpportunity = pendingOpportunities.get(choice - 1);
			} catch (IndexOutOfBoundsException e) {
				Utils.clear();
                System.out.println("Invalid selection. Please enter a valid number from the list (1 to " + pendingOpportunities.size() + ").");
			}

            ArrayList<String> repNames = new ArrayList<>();
            for (CompanyRepresentative rep : chosenOpportunity.getCompanyRepresentatives()) {
                repNames.add(rep.getName());
            }
            String companyReps = String.join(", ", repNames);

            System.out.println("Company name: " + chosenOpportunity.getCompanyName());
            System.out.println("Internship title: " + chosenOpportunity.getInternshipTitle());
            System.out.println("Description: " + chosenOpportunity.getDescription());
            System.out.println("Preferred Majors: " + chosenOpportunity.getPreferredMajor().toString());
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
            for (InternshipApplication application: appController.getInternshipApplications()) {
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
                System.out.println((i + 1) + ") " + app.getApplicant().getName() + " (" + app.getInternshipOpportunity().getInternshipTitle() + ")");
            }

            System.out.println();
            int choice = Utils.inputInt("Enter the number of a withdrawal request (or -1 to exit or -2 to approve all requests): ");
            if (choice == -1) {
                Utils.clear();
                break;
            }

            if (choice == -2) {
                for (InternshipApplication application: applications) {
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
                    Utils.clear();
                    break;
            }
        }
    }

    private void generateOpportunityReport() {
        boolean loop = true;

        while (loop) {
            Utils.clear();

            ArrayList<InternshipOpportunity> opportunities = internshipOpportunityController.getInternshipOpportunities();
            ArrayList<InternshipOpportunity> filtered = new ArrayList<>();
            for (InternshipOpportunity opp : opportunities) {

                // Filter by internship level
                if (!staff.getInternshipLevelFilter().isEmpty()) {
                    if (!staff.getInternshipLevelFilter().contains(opp.getInternshipLevel())) {
                        continue;
                    }
                }

                // Filter by company name
                if (!staff.getCompanyNameFilter().isEmpty()) {
                    if (!staff.getCompanyNameFilter().contains(opp.getCompanyName())) {
                        continue;
                    }
                }

                // Filter by preferred majors
                if (!staff.getPreferredMajorsFilter().isEmpty()) {
                    boolean majorMatch = false;
                    for (String major : staff.getPreferredMajorsFilter()) {
                        if (opp.getPreferredMajor().contains(major)) {
                            majorMatch = true;
                            break;
                        }
                    }
                    if (!majorMatch) {
                        continue;
                    }
                }

                // Filter by opening/closing date
                if (opp.getApplicationOpeningDate().isBefore(staff.getApplicationOpeningDateFilter())) {
                    continue;
                }
                if (opp.getApplicationClosingDate().isAfter(staff.getApplicationClosingDateFilter())) {
                    continue;
                }

                // Filter by status
                if (!staff.getStatusFilter().isEmpty()) {
                    if (!staff.getStatusFilter().contains(opp.getStatus())) {
                        continue;
                    }
                }

                // Filter by company representative
                if (!staff.getCompanyRepresentativeFilter().isEmpty()) {
                    boolean repMatch = false;
                    for (CompanyRepresentative rep : staff.getCompanyRepresentativeFilter()) {
                        if (opp.getCompanyRepresentatives().contains(rep)) {
                            repMatch = true;
                            break;
                        }
                    }
                    if (!repMatch) {
                        continue;
                    }
                }

                filtered.add(opp);
            }


			// TODO: use streams instead
			// List<InternshipOpportunity> filtered = internshipOpportunityController.getInternshipOpportunities().stream()
			// 	.filter()
			// 	.collect(Collectors.toList());
            if (filtered.isEmpty()) {
                System.out.println("There are no internship opportunities yet.");
                System.out.println();
                break;
            }

            System.out.println("Internship Opportunities:");
            for (int i = 0; i < filtered.size(); i++) {
                System.out.println((i + 1) + ") " + filtered.get(i).getInternshipTitle());
            }

            System.out.println();
            int choice = Utils.inputInt("Enter the number of an internship opportunity to generate the report (or -1 to exit): ");

            if (choice == -1) {
                Utils.clear();
                break;
            }

            int index = choice - 1;
            InternshipOpportunity chosenOpportunity = null;

            if (index >= 0 && index < filtered.size()) {
                chosenOpportunity = filtered.get(index);
            }

            if (chosenOpportunity == null) {
                Utils.clear();
                System.out.println("Invalid selection. Please enter a valid number.");
                System.out.println();
                continue;
            }

            Utils.clear();

            String preferredMajor = chosenOpportunity.getPreferredMajor();
            ArrayList<String> arrReps = new ArrayList<>();
            for (CompanyRepresentative rep : chosenOpportunity.getCompanyRepresentatives()) {
                arrReps.add(rep.getName());
            }
            String companyReps = String.join(", ", arrReps);
            System.out.println("Company name: " + chosenOpportunity.getCompanyName());
            System.out.println("Internship title: " + chosenOpportunity.getInternshipTitle());
            System.out.println("Internship Level: " + chosenOpportunity.getInternshipLevel());
            System.out.println("Description: " + chosenOpportunity.getDescription());
            System.out.println("Preferred Major: " + preferredMajor);
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

    private void setFilters() {
        boolean loop = true;
        
        while (loop) {
            Utils.clear();
            
            System.out.println("Current Filters:");
            System.out.println("Internship Level Filter: " + 
                (staff.getInternshipLevelFilter().isEmpty() ? "None" : staff.getInternshipLevelFilter()));
            System.out.println("Company Name Filter: " + 
                (staff.getCompanyNameFilter().isEmpty() ? "None" : staff.getCompanyNameFilter()));
            System.out.println("Preferred Majors Filter: " + 
                (staff.getPreferredMajorsFilter().isEmpty() ? "None" : staff.getPreferredMajorsFilter()));
            System.out.println("Application Opening Date Filter: " + 
                (staff.getApplicationOpeningDateFilter().equals(java.time.LocalDate.MIN) ? "None" : staff.getApplicationOpeningDateFilter()));
            System.out.println("Application Closing Date Filter: " + 
                (staff.getApplicationClosingDateFilter().equals(java.time.LocalDate.MAX) ? "None" : staff.getApplicationClosingDateFilter()));
            System.out.println("Status Filter: " + 
                (staff.getStatusFilter().isEmpty() ? "None" : staff.getStatusFilter()));
            System.out.println("Company Representative Filter: " + 
                (staff.getCompanyRepresentativeFilter().isEmpty() ? "None" : staff.getCompanyRepresentativeFilter()));
            System.out.println();
            
            System.out.println("1) Manage Internship Level Filter.");
            System.out.println("2) Manage Company Name Filter.");
            System.out.println("3) Manage Preferred Major Filter.");
            System.out.println("4) Manage Application Opening Date Filter.");
            System.out.println("5) Manage Application Closing Date Filter.");
            System.out.println("6) Manage Status Filter.");
            System.out.println("7) Manage Company Representative Filter.");
            System.out.println("8) Clear all filters.");
            System.out.println("9) Exit.");
            
            int choice = Utils.inputInt("Enter an option: ");
            
            switch (choice) {
                case 1:
                    manageInternshipLevelFilter();
                    break;
                case 2:
                    manageCompanyNameFilter();
                    break;
                case 3:
                    managePreferredMajorFilter();
                    break;
                case 4:
                    manageApplicationOpeningDateFilter();
                    break;
                case 5:
                    manageApplicationClosingDateFilter();
                    break;
                case 6:
                    manageStatusFilter();
                    break;
                case 7:
                    manageCompanyRepresentativeFilter();
                    break;
                case 8:
                    Utils.clear();
                    staff.removeInternshipLevelFilter(-1);
                    staff.removeCompanyNameFilter(-1);
                    staff.removePreferredMajorFilter(-1);
                    staff.setApplicationOpeningDateFilter(java.time.LocalDate.MIN);
                    staff.setApplicationClosingDateFilter(java.time.LocalDate.MAX);
                    staff.removeStatusFilter(-1);
                    staff.removeCompanyRepresentativeFilter(-1);
                    System.out.println("All filters cleared.");
                    System.out.println();
                    break;
                case 9:
                    Utils.clear();
                    loop = false;
                    break;
                default:
                    Utils.clear();
                    break;
            }
        }
    }

    private void manageInternshipLevelFilter() {
        boolean loop = true;
        
        while (loop) {
            Utils.clear();
            
            System.out.println("Current Internship Level Filters: " + 
                (staff.getInternshipLevelFilter().isEmpty() ? "None" : staff.getInternshipLevelFilter()));
            System.out.println();
            
            System.out.println("1) Add Internship Level Filter.");
            System.out.println("2) Remove Internship Level Filter.");
            System.out.println("3) Exit.");
            
            int choice = Utils.inputInt("Enter an option: ");
            
            switch (choice) {
                case 1:
                    Utils.clear();
                    System.out.println("Available Internship Levels:");
                    System.out.println("1) BASIC");
                    System.out.println("2) INTERMEDIATE");
                    System.out.println("3) ADVANCED");
                    System.out.println();
                    
                    int levelChoice = Utils.inputInt("Enter an option: ");
                    
                    switch (levelChoice) {
                        case 1:
                            staff.addInternshipLevelFilter(InternshipLevel.BASIC);
                            Utils.clear();
                            System.out.println("BASIC internship level filter added.");
                            System.out.println();
                            break;
                        
                        case 2: 
                            staff.addInternshipLevelFilter(InternshipLevel.INTERMEDIATE);
                            Utils.clear();
                            System.out.println("INTERMEDIATE internship level filter added.");
                            System.out.println();
                            break;

                        case 3: 
                            staff.addInternshipLevelFilter(InternshipLevel.ADVANCED);
                            Utils.clear();
                            System.out.println("ADVANCED internship level filter added.");
                            System.out.println();
                            break;

                        default:
                            Utils.clear();
                            System.out.println("Invalid selection.");
                            System.out.println();
                            break;
                    }
                    break;
                    
                case 2:
                    Utils.clear();
                    if (staff.getInternshipLevelFilter().isEmpty()) {
                        System.out.println("No internship level filters to remove.");
                        System.out.println();
                        break;
                    }
                    
                    System.out.println("Current Internship Level Filters:");
                    for (int i = 0; i < staff.getInternshipLevelFilter().size(); i++) {
                        System.out.println((i + 1) + ") " + staff.getInternshipLevelFilter().get(i));
                    }
                    System.out.println();
                    
                    int removeChoice = Utils.inputInt("Enter the number to remove (or -1 to clear all): ");
                    
                    if (removeChoice == -1) {
                        staff.removeInternshipLevelFilter(-1);
                        Utils.clear();
                        System.out.println("All internship level filters cleared.");
                        System.out.println();
                    } else if (removeChoice > 0 && removeChoice <= staff.getInternshipLevelFilter().size()) {
                        staff.removeInternshipLevelFilter(removeChoice - 1);
                        Utils.clear();
                        System.out.println("Internship level filter removed.");
                        System.out.println();
                    } else {
                        Utils.clear();
                        System.out.println("Invalid selection.");
                        System.out.println();
                    }
                    break;
                    
                case 3:
                    Utils.clear();
                    loop = false;
                    break;
                    
                default:
                    Utils.clear();
                    break;
            }
        }
    }

    private void manageCompanyNameFilter() {
        boolean loop = true;
        
        while (loop) {
            Utils.clear();
            
            System.out.println("Current Company Name Filters: " + 
                (staff.getCompanyNameFilter().isEmpty() ? "None" : staff.getCompanyNameFilter()));
            System.out.println();
            
            System.out.println("1) Add Company Name Filter.");
            System.out.println("2) Remove Company Name Filter.");
            System.out.println("3) Exit.");
            
            int choice = Utils.inputInt("Enter an option: ");
            
            switch (choice) {
                case 1:
                    Utils.clear();
                    String companyName = Utils.inputString("Enter company name to add: ");
                    staff.addCompanyNameFilter(companyName);
                    Utils.clear();
                    System.out.println("Company name filter added.");
                    System.out.println();
                    break;
                    
                case 2:
                    Utils.clear();
                    if (staff.getCompanyNameFilter().isEmpty()) {
                        System.out.println("No company name filters to remove.");
                        System.out.println();
                        break;
                    }
                    
                    System.out.println("Current Company Name Filters:");
                    for (int i = 0; i < staff.getCompanyNameFilter().size(); i++) {
                        System.out.println((i + 1) + ") " + staff.getCompanyNameFilter().get(i));
                    }
                    System.out.println();
                    
                    int removeChoice = Utils.inputInt("Enter the number to remove (or -1 to clear all): ");
                    
                    if (removeChoice == -1) {
                        staff.removeCompanyNameFilter(-1);
                        Utils.clear();
                        System.out.println("All company name filters cleared.");
                        System.out.println();
                    } else if (removeChoice > 0 && removeChoice <= staff.getCompanyNameFilter().size()) {
                        staff.removeCompanyNameFilter(removeChoice - 1);
                        Utils.clear();
                        System.out.println("Company name filter removed.");
                        System.out.println();
                    } else {
                        Utils.clear();
                        System.out.println("Invalid selection.");
                        System.out.println();
                    }
                    break;
                    
                case 3:
                    Utils.clear();
                    loop = false;
                    break;
                    
                default:
                    Utils.clear();
                    break;
            }
        }
    }

    private void managePreferredMajorFilter() {
        boolean loop = true;
        
        while (loop) {
            Utils.clear();
            
            System.out.println("Current Preferred Major Filters: " + 
                (staff.getPreferredMajorsFilter().isEmpty() ? "None" : staff.getPreferredMajorsFilter()));
            System.out.println();
            
            System.out.println("1) Add Preferred Major Filter.");
            System.out.println("2) Remove Preferred Major Filter.");
            System.out.println("3) Exit.");
            
            int choice = Utils.inputInt("Enter an option: ");
            
            switch (choice) {
                case 1:
                    Utils.clear();
                    String major = Utils.inputString("Enter preferred major to add: ");
                    staff.addPreferredMajorFilter(major);
                    Utils.clear();
                    System.out.println("Preferred major filter added.");
                    System.out.println();
                    break;
                    
                case 2:
                    Utils.clear();
                    if (staff.getPreferredMajorsFilter().isEmpty()) {
                        System.out.println("No preferred major filters to remove.");
                        System.out.println();
                        break;
                    }
                    
                    System.out.println("Current Preferred Major Filters:");
                    for (int i = 0; i < staff.getPreferredMajorsFilter().size(); i++) {
                        System.out.println((i + 1) + ") " + staff.getPreferredMajorsFilter().get(i));
                    }
                    System.out.println();
                    
                    int removeChoice = Utils.inputInt("Enter the number to remove (or -1 to clear all): ");
                    
                    if (removeChoice == -1) {
                        staff.removePreferredMajorFilter(-1);
                        Utils.clear();
                        System.out.println("All preferred major filters cleared.");
                        System.out.println();
                    } else if (removeChoice > 0 && removeChoice <= staff.getPreferredMajorsFilter().size()) {
                        staff.removePreferredMajorFilter(removeChoice - 1);
                        Utils.clear();
                        System.out.println("Preferred major filter removed.");
                        System.out.println();
                    } else {
                        Utils.clear();
                        System.out.println("Invalid selection.");
                        System.out.println();
                    }
                    break;
                    
                case 3:
                    Utils.clear();
                    loop = false;
                    break;
                    
                default:
                    Utils.clear();
                    break;
            }
        }
    }

    private void manageApplicationOpeningDateFilter() {
        boolean loop = true;
        
        while (loop) {
            Utils.clear();
            
            System.out.println("Current Application Opening Date Filter: " + 
                (staff.getApplicationOpeningDateFilter().equals(java.time.LocalDate.MIN) ? "None" : staff.getApplicationOpeningDateFilter()));
            System.out.println();
            
            System.out.println("1) Set Application Opening Date Filter.");
            System.out.println("2) Reset Application Opening Date Filter.");
            System.out.println("3) Exit.");
            
            int choice = Utils.inputInt("Enter an option: ");
            
            switch (choice) {
                case 1:
                    Utils.clear();
                    String openingDate = Utils.inputString("Enter application opening date (YYYY-MM-DD): ");
                    try {
                        java.time.LocalDate date = java.time.LocalDate.parse(openingDate);
                        staff.setApplicationOpeningDateFilter(date);
                        Utils.clear();
                        System.out.println("Application opening date filter set.");
                        System.out.println();
                    } catch (Exception e) {
                        Utils.clear();
                        System.out.println("Invalid date format.");
                        System.out.println();
                    }
                    break;
                    
                case 2:
                    Utils.clear();
                    staff.setApplicationOpeningDateFilter(java.time.LocalDate.MIN);
                    System.out.println("Application opening date filter reset.");
                    System.out.println();
                    break;
                    
                case 3:
                    Utils.clear();
                    loop = false;
                    break;
                    
                default:
                    Utils.clear();
                    break;
            }
        }
    }

    private void manageApplicationClosingDateFilter() {
        boolean loop = true;
        
        while (loop) {
            Utils.clear();
            
            System.out.println("Current Application Closing Date Filter: " + 
                (staff.getApplicationClosingDateFilter().equals(java.time.LocalDate.MAX) ? "None" : staff.getApplicationClosingDateFilter()));
            System.out.println();
            
            System.out.println("1) Set Application Closing Date Filter.");
            System.out.println("2) Reset Application Closing Date Filter.");
            System.out.println("3) Exit.");
            
            int choice = Utils.inputInt("Enter an option: ");
            
            switch (choice) {
                case 1:
                    Utils.clear();
                    String closingDate = Utils.inputString("Enter application closing date (YYYY-MM-DD): ");
                    try {
                        java.time.LocalDate date = java.time.LocalDate.parse(closingDate);
                        staff.setApplicationClosingDateFilter(date);
                        Utils.clear();
                        System.out.println("Application closing date filter set.");
                        System.out.println();
                    } catch (Exception e) {
                        Utils.clear();
                        System.out.println("Invalid date format.");
                        System.out.println();
                    }
                    break;
                    
                case 2:
                    Utils.clear();
                    staff.setApplicationClosingDateFilter(java.time.LocalDate.MAX);
                    System.out.println("Application closing date filter reset.");
                    System.out.println();
                    break;
                    
                case 3:
                    Utils.clear();
                    loop = false;
                    break;
                    
                default:
                    Utils.clear();
                    break;
            }
        }
    }

    private void manageStatusFilter() {
        boolean loop = true;
        
        while (loop) {
            Utils.clear();
            
            System.out.println("Current Status Filters: " + 
                (staff.getStatusFilter().isEmpty() ? "None" : staff.getStatusFilter()));
            System.out.println();
            
            System.out.println("1) Add Status Filter.");
            System.out.println("2) Remove Status Filter.");
            System.out.println("3) Exit.");
            
            int choice = Utils.inputInt("Enter an option: ");
            
            switch (choice) {
                case 1:
                    Utils.clear();
                    System.out.println("Available Status Values:");
                    System.out.println("1) PENDING");
                    System.out.println("2) APPROVED");
                    System.out.println("3) REJECTED");
                    System.out.println();
                    
                    int statusChoice = Utils.inputInt("Enter an option: ");
                    
                    if (statusChoice == 1) {
                        staff.addStatusFilter(Status.PENDING);
                        Utils.clear();
                        System.out.println("PENDING status filter added.");
                        System.out.println();
                    } else if (statusChoice == 2) {
                        staff.addStatusFilter(Status.APPROVED);
                        Utils.clear();
                        System.out.println("APPROVED status filter added.");
                        System.out.println();
                    } else if (statusChoice == 3) {
                        staff.addStatusFilter(Status.REJECTED);
                        Utils.clear();
                        System.out.println("REJECTED status filter added.");
                        System.out.println();
                    } else {
                        Utils.clear();
                        System.out.println("Invalid selection.");
                        System.out.println();
                    }
                    break;
                    
                case 2:
                    Utils.clear();
                    if (staff.getStatusFilter().isEmpty()) {
                        System.out.println("No status filters to remove.");
                        System.out.println();
                        break;
                    }
                    
                    System.out.println("Current Status Filters:");
                    for (int i = 0; i < staff.getStatusFilter().size(); i++) {
                        System.out.println((i + 1) + ") " + staff.getStatusFilter().get(i));
                    }
                    System.out.println();
                    
                    int removeChoice = Utils.inputInt("Enter the number to remove (or -1 to clear all): ");
                    
                    if (removeChoice == -1) {
                        staff.removeStatusFilter(-1);
                        Utils.clear();
                        System.out.println("All status filters cleared.");
                        System.out.println();
                    } else if (removeChoice > 0 && removeChoice <= staff.getStatusFilter().size()) {
                        staff.removeStatusFilter(removeChoice - 1);
                        Utils.clear();
                        System.out.println("Status filter removed.");
                        System.out.println();
                    } else {
                        Utils.clear();
                        System.out.println("Invalid selection.");
                        System.out.println();
                    }
                    break;
                    
                case 3:
                    Utils.clear();
                    loop = false;
                    break;
                    
                default:
                    Utils.clear();
                    break;
            }
        }
    }

    private void manageCompanyRepresentativeFilter() {
        boolean loop = true;
        
        while (loop) {
            Utils.clear();
            
            ArrayList<CompanyRepresentative> currentFilters = staff.getCompanyRepresentativeFilter();
            System.out.print("Current Company Representative Filters: ");
            if (currentFilters.isEmpty()) {
                System.out.println("None");
            } else {
                ArrayList<String> names = new ArrayList<>();
                for (CompanyRepresentative rep : currentFilters) {
                    names.add(rep.getName());
                }
                System.out.println(String.join(", ", names));
            }
            System.out.println();
            
            System.out.println("1) Add Company Representative Filter.");
            System.out.println("2) Remove Company Representative Filter.");
            System.out.println("3) Exit.");
            
            int choice = Utils.inputInt("Enter an option: ");
            
            switch (choice) {
                case 1:
                    Utils.clear();
                    ArrayList<CompanyRepresentative> allReps = userController.getCompanyRepresentatives();
                    
                    if (allReps.isEmpty()) {
                        System.out.println("No company representatives available.");
                        System.out.println();
                        break;
                    }
                    
                    System.out.println("Available Company Representatives:");
                    for (int i = 0; i < allReps.size(); i++) {
                        CompanyRepresentative rep = allReps.get(i);
                        System.out.println((i + 1) + ") " + rep.getName() + " (" + rep.getCompany() + ")");
                    }
                    System.out.println();
                    
                    int repChoice = Utils.inputInt("Enter the number of the representative to add (or -1 to cancel): ");
                    
                    if (repChoice == -1) {
                        Utils.clear();
                        break;
                    }
                    
                    if (repChoice > 0 && repChoice <= allReps.size()) {
                        CompanyRepresentative selectedRep = allReps.get(repChoice - 1);
                        staff.addCompanyRepresentativeFilter(selectedRep);
                        Utils.clear();
                        System.out.println("Company representative filter added.");
                        System.out.println();
                    } else {
                        Utils.clear();
                        System.out.println("Invalid selection.");
                        System.out.println();
                    }
                    break;
                    
                case 2:
                    Utils.clear();
                    if (staff.getCompanyRepresentativeFilter().isEmpty()) {
                        System.out.println("No company representative filters to remove.");
                        System.out.println();
                        break;
                    }
                    
                    System.out.println("Current Company Representative Filters:");
                    for (int i = 0; i < staff.getCompanyRepresentativeFilter().size(); i++) {
                        CompanyRepresentative rep = staff.getCompanyRepresentativeFilter().get(i);
                        System.out.println((i + 1) + ") " + rep.getName() + " (" + rep.getCompany() + ")");
                    }
                    System.out.println();
                    
                    int removeChoice = Utils.inputInt("Enter the number to remove (or -1 to clear all): ");
                    
                    if (removeChoice == -1) {
                        staff.removeCompanyRepresentativeFilter(-1);
                        Utils.clear();
                        System.out.println("All company representative filters cleared.");
                        System.out.println();
                    } else if (removeChoice > 0 && removeChoice <= staff.getCompanyRepresentativeFilter().size()) {
                        staff.removeCompanyRepresentativeFilter(removeChoice - 1);
                        Utils.clear();
                        System.out.println("Company representative filter removed.");
                        System.out.println();
                    } else {
                        Utils.clear();
                        System.out.println("Invalid selection.");
                        System.out.println();
                    }
                    break;
                    
                case 3:
                    Utils.clear();
                    loop = false;
                    break;
                    
                default:
                    Utils.clear();
                    break;
            }
        }
    }
    private void printAllApplications() {
        boolean loop = true;

        while (loop) {
            Utils.clear();
            ArrayList<InternshipApplication> applications = appController.getInternshipApplications();

            if (applications.isEmpty()) {
                System.out.println("There are no internship applications yet.");
                System.out.println();
                break;
            }

            System.out.println("Internship Applications:");
            for (int i = 0; i < applications.size(); i++) {
                InternshipApplication app = applications.get(i);
                System.out.println((i + 1) + ") " + app.getApplicant().getName() + " (" + app.getInternshipOpportunity().getInternshipTitle() + ")");
            }

            System.out.println();
            int choice = Utils.inputInt("Enter the number of an application to view details (or -1 to exit): ");

            if (choice == -1) {
                Utils.clear();
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

            System.out.println("Applicant: " + chosenApplication.getApplicant().getName());
            System.out.println("Internship title: " + chosenApplication.getInternshipOpportunity().getInternshipTitle());
            System.out.println("Internship company: " + chosenApplication.getInternshipOpportunity().getCompanyName());
            System.out.println("Date applied: " + chosenApplication.getDateApplied());
            System.out.println("Status: " + chosenApplication.getStatus());
            System.out.println("Placement confirmed: " + chosenApplication.getPlacementConfirmed());
            System.out.println("Withdrawal requested: " + chosenApplication.getWithdrawalRequested());
            System.out.println("Withdrawal approved: " + chosenApplication.getWithdrawalApproved());
            System.out.println();

            System.out.println("1) Select another application.");
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

    private void changePassword() {
        Utils.clear();
        String newPassword = Utils.inputString("Enter your new password: ");
        staff.setPasswordHash(newPassword);

        Utils.clear();
        System.out.println("Your new password has been set.");
        System.out.println("Please re-login with your new password.");
        System.out.println();
    }
}
