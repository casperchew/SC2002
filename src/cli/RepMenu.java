package src.cli;

import java.time.LocalDate;
import java.util.ArrayList;

import src.controller.UserController;
import src.controller.ApplicationController;
import src.controller.InternshipOpportunityController;
import src.enums.InternshipLevel;
import src.model.internship.InternshipApplication;
import src.model.internship.InternshipOpportunity;
import src.model.user.CompanyRepresentative;
import src.model.user.Student;
import src.model.User;
import src.utils.Utils;
import src.enums.Status;

public class RepMenu {
    private CompanyRepresentative rep;
    private ApplicationController appController;
    private InternshipOpportunityController internshipOpportunityController;

    public RepMenu(
            CompanyRepresentative rep,
            UserController userController,
            ApplicationController appController,
            InternshipOpportunityController internshipOpportunityController
    ) {
        this.rep = rep;
        this.appController = appController;
        this.internshipOpportunityController = internshipOpportunityController;
    }

    public User runMenu(CompanyRepresentative rep) {
        boolean loop = true;

        while (loop) {
            // Utils.clear();
            System.out.println();
            System.out.println("1) Create internship opportunity.");
            System.out.println("2) View created internship opportunities.");
            System.out.println("3) View student applications.");
            System.out.println("4) Change password");
            System.out.println("5) Log out.");

            int choice = Utils.inputInt("Enter an option: ");

            switch (choice) {
                case 1:
                    createInternshipOpportunity();
                    return rep;
                case 2:
                    viewCreatedInternships();
                    return rep;
                case 3:
                    viewStudentApplications();
                    return rep;
                case 4:
                    // Changing passwords require re-logins
                    changePassword();
                    return null;
                case 5:
                    Utils.clear();
                    System.out.println("Logging out...");
                    loop = false;
                    return null;
                default:
                    Utils.clear();
                    System.out.println("Invalid option, please try again.");
                    return rep;
            } 
        } return rep;
    }

    private void changePassword() {
        Utils.clear();
        String newPassword = Utils.inputString("Enter your new password: ");
        rep.setPasswordHash(newPassword);
        Utils.clear();
        System.out.println("Your new password has been set.");
        System.out.println("Please re-login with your new password.");
        System.out.println();
    }

    private void createInternshipOpportunity() {
        Utils.clear();

        ArrayList<InternshipOpportunity> opps = internshipOpportunityController.getInternshipOpportunities();

        int count = 0;

        for (InternshipOpportunity opp: opps) {
            if (opp.getCompanyRepresentatives().contains(rep)) {
                count ++;
            }
        }

        if (count >= 5) {
            System.out.println("Error: each representative can only create up to 5 intrnships!");
            return;
        }

        String title = Utils.inputString("Set the internship title: ");

        // Checks whether internship opportunity exists or not
        ArrayList<InternshipOpportunity> existingOpps = internshipOpportunityController.getInternshipOpportunities();
        InternshipOpportunity opportunity;
        for (InternshipOpportunity existingOpp: existingOpps) {
            if (existingOpp.getInternshipTitle().equals(title) && existingOpp.getCompanyName().equals(rep.getCompany())) {
                existingOpp.getCompanyRepresentatives().add(rep);
                System.out.println("You have been added as a representative for the internship opportunity successfully!");
                return;
            }
        }
        
        String description = Utils.inputString("Set the description for the internship: ");

        System.out.println("\n1) Basic\n2) Intermediate\n3) Advanced");
        int levelChoice = Utils.inputInt("Set the internship level: ");
        InternshipLevel level = switch (levelChoice) {
            case 1 -> InternshipLevel.BASIC;
            case 2 -> InternshipLevel.INTERMEDIATE;
            case 3 -> InternshipLevel.ADVANCED;
            default -> InternshipLevel.BASIC;
        };

        int numMajors = 1; // Assume only 1 preferred major per opportunity
        ArrayList<String> preferredMajors = new ArrayList<>();
        for (int i = 0; i < numMajors; i++) {
            preferredMajors.add(Utils.inputString("Enter preferred major " + (i + 1) + ": "));
        }

        LocalDate openingDate = Utils.inputDate("Enter opening date (YYYY-MM-DD): ");
        LocalDate closingDate = Utils.inputDate("Enter closing date (YYYY-MM-DD): ");
        while (closingDate.isBefore(openingDate)) {
            System.out.println("Error: Closing date cannot be before opening date.");
            closingDate = Utils.inputDate("Enter closing date (YYYY-MM-DD): ");
        }

        int numSlots = Utils.inputInt("Enter number of available slots: ");
        while (numSlots < 1 || numSlots > 10) {
            System.out.println("Error: Number of slots must be between 1 & 10 inclusive.");
            numSlots = Utils.inputInt("Enter number of available slots: ");
        }

        ArrayList<CompanyRepresentative> reps = new ArrayList<>();
        reps.add(rep);

        opportunity = new InternshipOpportunity(
                title, description, level, preferredMajors, openingDate, closingDate,
                rep.getCompany(), reps, numSlots
        );

        internshipOpportunityController.createInternshipOpportunity(opportunity);
        System.out.println("\nInternship opportunity '" + title + "' created successfully!");        
    }

    private void viewCreatedInternships() {
        Utils.clear();
        ArrayList<InternshipOpportunity> opportunities = internshipOpportunityController.getInternshipOpportunities(rep);
        
        boolean loop = true;
        while (loop) {
            if (opportunities.isEmpty()) {
                Utils.clear();
                System.out.println("You have not created any internship opportunities yet.");
                System.out.println();
                break;
            }
            System.out.println("Created Internship Opportunities:");
            for (int i = 0; i < opportunities.size(); i++) {
                System.out.println((i + 1) + ") " + opportunities.get(i).getInternshipTitle());
            }

            int choice = Utils.inputInt("Enter the number of an internship to view details (or -1 to exit): ");
            if (choice == -1) {
                Utils.clear();
                break;
            }

            int index = choice - 1;
            if (index < 0 || index >= opportunities.size()) {
                Utils.clear();
                System.out.println("Invalid selection.");
                continue;
            }

            Utils.clear();
            InternshipOpportunity opp = opportunities.get(index);
            System.out.println("Company: " + opp.getCompanyName());
            System.out.println("Title: " + opp.getInternshipTitle());
            System.out.println("Level: " + opp.getInternshipLevel());
            System.out.println("Description: " + opp.getDescription());
            System.out.println("Preferred Majors: " + String.join(", ", opp.getPreferredMajors()));
            System.out.println("Application Open: " + opp.getApplicationOpeningDate());
            System.out.println("Application Close: " + opp.getApplicationClosingDate());
            System.out.println("Slots: " + opp.getNumberOfSlots());
            System.out.println("Status: " + opp.getStatus());
            System.out.println("Visibility: " + opp.getVisibility());

            System.out.println("\n1) Select another internship opportunity.");
            System.out.println("2) Toggle internship visibility.");
            System.out.println("3) Exit.");
            int subChoice = Utils.inputInt("Enter an option: ");
            switch (subChoice) {
                case 1:
                    Utils.clear();
                    break;
                case 2:
                    Utils.clear();
                    boolean visibility = !opp.getVisibility();
                    opp.setVisibility(visibility);
                    System.out.println("Visibility toggled " + visibility + " successfully!");
                    break;
                case 3:
                    loop = false;
                    Utils.clear();
                    break;
            }
        }
    }

    private void viewStudentApplications() {
        Utils.clear();
        ArrayList<InternshipApplication> allApplications;
        allApplications = appController.getInternshipApplications();

        int count = 0;

        // Filters through internship applications for which the representative is in charge of
        for (InternshipApplication application: allApplications) {
            InternshipOpportunity opp = application.getInternshipOpportunity();
            int numOfSlots = opp.getNumberOfSlots();
            if (opp.getCompanyRepresentatives().contains(rep)
                && numOfSlots > 0
                && application.getStatus() == Status.PENDING) {
                count ++;

                System.out.println("Application " + count);
                System.out.println("Title: " + opp.getInternshipTitle());

                Student applicant = application.getApplicant();
                System.out.println("Student name: " + applicant.getName());
                System.out.println("Major: " + applicant.getMajor());
                System.out.println("Year of study: " + applicant.getYearOfStudy());

                int choice  = Utils.inputInt("Approve (1) or reject (0): ");
                
                switch (choice) {
                    case 0:
                        // Rejected
                        application.setStatus(Status.REJECTED);
                        break;
                    case 1:
                        // Approved
                        application.setStatus(Status.SUCCESSFUL);
                        opp.setNumberOfSlots(numOfSlots - 1);
                        if (opp.getNumberOfSlots() == 0) {
                            opp.setStatus(Status.FILLED);
                        }
                        break;
                }
            }
        }
    }
}
