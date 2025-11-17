package src.cli;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import src.controller.*;
import src.model.InternshipLevel;
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
				// Utils.clear();
                setFilter();
                return student;

            case 4:
				// Utils.clear();
                // System.out.println("Not implemented");
                changePassword();
				return student;

            case 5:
				Utils.clear();
                System.out.println("Logging out...");
                System.out.println();
				return null;

            default:
				Utils.clear();
				System.out.println("Invalid option!");
				System.out.println();
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
            ArrayList<InternshipOpportunity> filtered = new ArrayList<>();

            // This loop is added for the filtering
            for (InternshipOpportunity opp : opportunities) {

                // Filter by internship level
                if (!student.getInternshipLevelFilter().isEmpty()) {
                    if (!student.getInternshipLevelFilter().contains(opp.getInternshipLevel())) {
                        continue;
                    }
                }

                // Filter by company name
                if (!student.getCompanyNameFilter().isEmpty()) {
                    if (!student.getCompanyNameFilter().contains(opp.getCompanyName())) {
                        continue;
                    }
                }

                // Filter by opening/closing date
                if (opp.getApplicationOpeningDate().isBefore(student.getApplicationOpeningDateFilter())) {
                    continue;
                }
                if (opp.getApplicationClosingDate().isAfter(student.getApplicationClosingDateFilter())) {
                    continue;
                }

                filtered.add(opp);
            }

            if (filtered.isEmpty()) {
                Utils.clear();
                System.out.println("There are currently no available internship opportunities for you.");
                System.out.println("");
                break;
            }

            ArrayList<InternshipOpportunity> approvedList = new ArrayList<>();
            
            System.out.println("Select an internship opportunity to apply for: ");
            for (InternshipOpportunity internshipOpp: filtered) {
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
            System.out.println("Company name: " + chosenInternshipOpp.getCompanyName());
            System.out.println("Internship level: " + chosenInternshipOpp.getInternshipLevel());
            System.out.println("Description: " + chosenInternshipOpp.getDescription());
            System.out.println("Available slots: " + chosenInternshipOpp.getNumberOfSlots());
            System.out.println("Application opening date: " + chosenInternshipOpp.getApplicationOpeningDate());
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

    private void setFilter() {
        boolean loop = true;
        Utils.clear();

        while (loop) {
            System.out.println("Current filters:");
            System.out.print("Internship levels: ");
            if (student.getInternshipLevelFilter().isEmpty()) System.out.println("None");
            else System.out.println(student.getInternshipLevelFilter());

            System.out.print("Company names: ");
            if (student.getCompanyNameFilter().isEmpty()) System.out.println("None");
            else System.out.println(student.getCompanyNameFilter());

            // We should not display the MIN date because it doesnt make any sense
            if (Objects.equals(student.getApplicationOpeningDateFilter(), LocalDate.MIN)) {
                System.out.println("Application opening date (min): None");
            } else {
                System.out.println("Application opening date (min): " + student.getApplicationOpeningDateFilter());
            }
            // We should not display the MAX date because it doesnt make any sense
            if (Objects.equals(student.getApplicationClosingDateFilter(), LocalDate.MAX)) {
                System.out.println("Application closing date (max): None");
            } else {
                System.out.println("Application closing date (max): " + student.getApplicationClosingDateFilter());
            }
            
            System.out.println();

            System.out.println("1) Add internship level filter"); // works
            System.out.println("2) Remove internship level filter"); // works
            System.out.println("3) Add company name filter"); // works
            System.out.println("4) Remove company name filter"); // works
            System.out.println("5) Set application opening date filter");
            System.out.println("6) Set application closing date filter");
            System.out.println("7) Reset all filters");
            System.out.println("8) Exit");
            int choice = Utils.inputInt("Enter an option: ");
            Utils.clear();

            switch (choice) {
                case 1:
                    InternshipLevel[] levels = InternshipLevel.values();
                    System.out.println("Available internship levels:");
                    for (int i = 0; i < levels.length; i++) {
                        System.out.println((i + 1) + ") " + levels[i]);
                    }
                    System.out.println();

                    int lvlChoice = Utils.inputInt("Enter the number of the level to add: ");

                    if (lvlChoice < 1 || lvlChoice > levels.length) {
                        Utils.clear();
                        System.out.println("Invalid option.\n");
                        System.out.println();
                        break;
                    }

                    InternshipLevel levelToAdd = levels[lvlChoice - 1];

                    // Add only if it has not already be added (TODO: this should probably be handled within student.AddInternshipLevelFilter)
                    if (!student.getInternshipLevelFilter().contains(levelToAdd)) {
                        student.AddInternshipLevelFilter(levelToAdd);
                        Utils.clear();
                    } else {
                        Utils.clear();
                    }
                    break;

                case 2:
                    if (student.getInternshipLevelFilter().isEmpty()) {
                        Utils.clear();
                        System.out.println("No internship level filters to remove.\n");
                        System.out.println();
                        break;
                    }

                    System.out.println("Current internship level filters:");
                    for (int i = 0; i < student.getInternshipLevelFilter().size(); i++) {
                        System.out.println((i + 1) + ") " + student.getInternshipLevelFilter().get(i));
                    }
                    System.out.println();

                    int lvlRemoveChoice = Utils.inputInt("Enter the number of the level to remove: ");
                    Utils.clear();
                    
                    // TODO: these checks should probably be handled within the student.removeInternshipFilter()
                    if (lvlRemoveChoice < 1 || lvlRemoveChoice > student.getInternshipLevelFilter().size()) {
                        Utils.clear();
                        System.out.println("Invalid option.\n");
                        System.out.println();
                        break;
                    }

                    InternshipLevel removedLvl = student.RemoveInternshipLevelFilter(lvlRemoveChoice - 1);
                    System.out.println("Removed: " + removedLvl + "\n");
                    break;

                case 3:
                    String companyName = Utils.inputString("Enter company name to add: ");
                    // TODO: Likewise, these checks should probably also be handled within student.AddCompanyNameFilter()
                    if (!student.getCompanyNameFilter().contains(companyName)) {
                        student.AddCompanyNameFilter(companyName);
                        Utils.clear();
                        System.out.println("Company added.\n");
                        System.out.println();
                    } else {
                        Utils.clear();
                        System.out.println("Filter already added.\n");
                        System.out.println();
                    }
                    break;

                case 4:
                    if (student.getCompanyNameFilter().isEmpty()) {
                        Utils.clear();
                        System.out.println("No company name filters to remove.\n");
                        System.out.println();
                        break;
                    }

                    System.out.println("Current company name filters:");
                    for (int i = 0; i < student.getCompanyNameFilter().size(); i++) {
                        System.out.println((i + 1) + ") " + student.getCompanyNameFilter().get(i));
                    }
                    System.out.println();

                    int companyRemoveChoice = Utils.inputInt("Enter number of company to remove: ");
                    Utils.clear();
                    // TODO: Likewise, these checks should probably also be handled within student.AddCompanyNameFilter()
                    if (companyRemoveChoice < 1 || companyRemoveChoice > student.getCompanyNameFilter().size()) {
                        Utils.clear();
                        System.out.println("Invalid option.\n");
                        System.out.println();
                        break;
                    }

                    String removedCompany = student.RemoveCompanyNameFilter(companyRemoveChoice - 1);
                    Utils.clear();
                    System.out.println("Removed: " + removedCompany + "\n");
                    System.out.println();
                    break;

                case 5:
                    // We should not display the MIN date because it doesnt make any sense
                    if (Objects.equals(student.getApplicationOpeningDateFilter(), LocalDate.MIN)) {
                        System.out.println("Application opening date (min): None");
                    } else {
                        System.out.println("Application opening date (min): " + student.getApplicationOpeningDateFilter());
                    }                 
                    try {
                        LocalDate openDate = Utils.inputDate("Enter new opening date (YYYY-MM-DD): ");
                        student.setApplicationOpeningDateFilter(openDate);
                        Utils.clear();
                        System.out.println("Opening date updated.\n");
                        System.out.println();
                    } catch (Exception e) {
                        Utils.clear();
                        System.out.println("Invalid date.\n");
                        System.out.println();
                    }
                    break;

                case 6:
                    // We should not display the MAX date because it doesnt make any sense
                    if (Objects.equals(student.getApplicationClosingDateFilter(), LocalDate.MAX)) {
                        System.out.println("Application closing date (max): None");
                    } else {
                        System.out.println("Application closing date (max): " + student.getApplicationClosingDateFilter());
                    }
                    try {
                        LocalDate closeDate = Utils.inputDate("Enter new closing date (YYYY-MM-DD): ");
                        student.setApplicationClosingDateFilter(closeDate);
                        Utils.clear();
                        System.out.println("Closing date updated.\n");
                        System.out.println();
                    } catch (Exception e) {
                        Utils.clear();
                        System.out.println("Invalid date.\n");
                        System.out.println();
                    }
                    break;

                case 7:
                    student.RemoveInternshipLevelFilter(-1);
                    student.RemoveCompanyNameFilter(-1);
                    student.setApplicationOpeningDateFilter(LocalDate.MIN);
                    student.setApplicationClosingDateFilter(LocalDate.MAX);
                    Utils.clear();
                    System.out.println("All filters reset.\n");
                    System.out.println();
                    break;

                case 8:
                    loop = false;
                    Utils.clear();
                    break;

                default:
                    System.out.println("Invalid option!\n");
                    break;
            }
        }
    }

    private void changePassword() {
        Utils.clear();
        String newPassword = Utils.inputString("Enter your new password: ");
        student.setPasswordHash(newPassword);

        Utils.clear();
        System.out.println("Your new password has been set.");
        System.out.println();
    }

}
