package src.cli;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import src.controller.ApplicationController;
import src.controller.InternshipController;
import src.controller.UserController;
import src.model.InternshipLevel;
import src.model.internship.InternshipOpportunity;
import src.model.user.CompanyRepresentative;
import src.model.*;
import src.utils.Utils;

public class RepMenu {
    private CompanyRepresentative rep;
    private UserController userController;
    private ApplicationController appController;
    private InternshipController internshipController;

    public RepMenu(
            CompanyRepresentative rep,
            UserController userController,
            ApplicationController appController,
            InternshipController internshipController
    ) {
        this.rep = rep;
        this.userController = userController;
        this.appController = appController;
        this.internshipController = internshipController;
    }

    public User runMenu(CompanyRepresentative rep) {
        boolean loop = true;

        while (loop) {
            // Utils.clear();
            System.out.println();
            System.out.println("1) Create internship opportunity.");
            System.out.println("2) View created internship opportunities.");
            System.out.println("3) View student applications."); // TODO (allow the rep to approve/reject applications)
            System.out.println("4) Log out.");

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
                    System.out.println("Not implemented");
                    return rep;
                case 4:
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

    private void createInternshipOpportunity() {
        Utils.clear();
        String title = Utils.inputString("Set the internship title: ");
        String description = Utils.inputString("Set the description for the internship: ");

        System.out.println("\n1) Basic\n2) Intermediate\n3) Advanced");
        int levelChoice = Utils.inputInt("Set the internship level: ");
        InternshipLevel level = switch (levelChoice) {
            case 1 -> InternshipLevel.BASIC;
            case 2 -> InternshipLevel.INTERMEDIATE;
            case 3 -> InternshipLevel.ADVANCED;
            default -> InternshipLevel.BASIC;
        };

        int numMajors = Utils.inputInt("Enter number of preferred majors: ");
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
        while (numSlots <= 0) {
            System.out.println("Error: Number of slots must be positive.");
            numSlots = Utils.inputInt("Enter number of available slots: ");
        }

        ArrayList<CompanyRepresentative> reps = new ArrayList<>();
        reps.add(rep);

        InternshipOpportunity opportunity = new InternshipOpportunity(
                title, description, level, preferredMajors, openingDate, closingDate,
                rep.getCompany(), reps, numSlots
        );

        internshipController.createInternshipOpportunity(opportunity);
        System.out.println("\nInternship opportunity '" + title + "' created successfully!");
    }

    private void viewCreatedInternships() {
        Utils.clear();
        ArrayList<InternshipOpportunity> opportunities = internshipController.getInternshipOpportunities(rep);
        
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
            System.out.println("2) Exit.");
            int subChoice = Utils.inputInt("Enter an option: ");
            switch (subChoice) {
                case 1 -> Utils.clear();
                case 2 -> {
                    loop = false;
                    Utils.clear();
                }
            }
        }
    }

    private void viewStudentApplications() {}
}
