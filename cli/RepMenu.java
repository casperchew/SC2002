package cli;

import java.util.Scanner;

import controller.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.user.CompanyRepresentative;
import model.internship.InternshipOpportunity;
import model.InternshipLevel;

public class RepMenu {
    private Scanner sc = new Scanner(System.in);
    private CompanyRepresentative rep;
    private UserController userController;
    private ApplicationController appController;
    private InternshipController internshipController;

    public RepMenu(
        CompanyRepresentative rep,
        UserController userController,
        ApplicationController appController,
        InternshipController internshipController) {
            this.rep = rep;
            this.userController = userController;
            this.appController = appController;
            this.internshipController = internshipController;
    }

    public User runMenu(CompanyRepresentative rep) {
        int choice;
        System.out.println();
        System.out.println("=".repeat(20));
        System.out.println();
        System.out.println("1) Log out");
        System.out.println("2) Create internship opportunity");
        System.out.println("3) View created internship opportunities");
        choice = inputInt("Enter an option: ");
        switch (choice) {
            case 1:
                // Log out
                System.out.println("Logging out...");
				return null;
            case 2:
                // Create internship
                String internshipTitle, description;
                InternshipLevel level;
                ArrayList<String> preferredMajors = new ArrayList<String>();
                LocalDate openingDate, closingDate;
                List<CompanyRepresentative> companyRepresenatives;
                int numOfSlots, levelInt, numMajors;

                internshipTitle = inputString("Set the internship title: ");
                description = inputString("Set the description for the internship: ");
                System.out.println("\n1) Basic\n2) Intermediate\n3) Advanced");
                levelInt = inputInt("Set the internship level: ");
                level = InternshipLevel.fromValue(levelInt);
                numMajors = inputInt("Enter number of preferred majors you want to set for this internship: ");

                for (int i = 0; i < numMajors; i++) {
                    preferredMajors.add(inputString("Enter preferred major " + (i + 1) + ": "));
                }

                // Get dates
                openingDate = inputDate("Enter opening date (YYYY-MM-DD): ");
                closingDate = inputDate("Enter closing date (YYYY-MM-DD): ");

                // Validate date logic
                while (closingDate.isBefore(openingDate)) {
                    System.out.println("Error: Closing date cannot be before opening date.");
                    closingDate = inputDate("Enter closing date (YYYY-MM-DD): ");
                }

                // Get number of slots
                numOfSlots = inputInt("Enter number of available slots: ");
                while (numOfSlots <= 0) {
                    System.out.println("Error: Number of slots must be positive.");
                    numOfSlots = inputInt("Enter number of available slots: ");
                }

                // Initialize the list of representatives and add the creator
                companyRepresenatives = new ArrayList<>();
                companyRepresenatives.add(rep);

                InternshipOpportunity opportunity = new InternshipOpportunity(
                        internshipTitle, description, level, preferredMajors, openingDate,
                        closingDate, rep.getCompany(), companyRepresenatives, numOfSlots
                );

                // Submit internship opportunity to career staff for approval
                internshipController.createInternshipOpportunity(opportunity);

                // Provide feedback to the user
                System.out.println("\nInternship opportunity '" + internshipTitle + "' created successfully!");
                break;

            case 3:
                // View internship opportunity status
                ArrayList<InternshipOpportunity> opportunities = internshipController.getInternshipOpportunities(rep);
                
                for (InternshipOpportunity opp: opportunities) {
                    opp.printOpportunityDetails();
                }
                break;

            default:
				System.out.println("Invalid option, please try again.");
                break;
        }
        return rep;
    }

    private LocalDate inputDate(String text) {
        LocalDate date = null;
        while (date == null) {
            String dateString = inputString(text);
            try {
                date = LocalDate.parse(dateString); // Uses default YYYY-MM-DD format
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
        return date;
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
