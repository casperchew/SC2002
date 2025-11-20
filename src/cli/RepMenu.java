package src.cli;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

/**
 * CLI for {@link src.model.user.CompanyRepresentative}
 */
public class RepMenu {
    private CompanyRepresentative rep;
    private ApplicationController applicationController;
    private InternshipOpportunityController internshipOpportunityController;

	/**
	 * Constructs a {@link RepMenu} for a {@link src.model.user.CompanyRepresentative} from the required controllers
	 *
	 * @param rep the {@link src.model.user.CompanyRepresentative} that the CLI is for
	 * @param userController the {@link src.controller.UserController} used
	 * @param applicationController the {@link src.controller.ApplicationController} used
	 * @param internshipOpportunityController the {@link src.controller.InternshipOpportunityController} used
	 */
    public RepMenu(
            CompanyRepresentative rep,
            UserController userController,
            ApplicationController applicationController,
            InternshipOpportunityController internshipOpportunityController
    ) {
        this.rep = rep;
        this.applicationController = applicationController;
        this.internshipOpportunityController = internshipOpportunityController;
    }

	/**
	 * Displays the CLI menu for {@link src.model.user.CompanyRepresentative}.
	 *
	 * @return the {@link src.model.user.CompanyRepresentative} instance after the {@code rep} interacts with the menu.
	 */
    public User runMenu() {
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
				changePassword();
				return null;
			case 5:
				Utils.clear();
				System.out.println("Logging out...");
				return null;
			default:
				Utils.clear();
				System.out.println("Invalid option, please try again.");
				return rep;
		} 
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
                // existingOpp.getCompanyRepresentatives().add(rep);
                existingOpp.addCompanyRepresentative(rep);
                System.out.println("You have been added as a representative for the internship opportunity successfully!");
                return;
            }
        }
        
        String description = Utils.inputString("Set the description for the internship: ");

		System.out.println();
        System.out.println("1) Basic");
        System.out.println("2) Intermediate");
        System.out.println("3) Advanced");
		System.out.println();
        int levelChoice = Utils.inputInt("Set the internship level: ");
        InternshipLevel level = switch (levelChoice) {
            case 1 -> InternshipLevel.BASIC;
            case 2 -> InternshipLevel.INTERMEDIATE;
            case 3 -> InternshipLevel.ADVANCED;
            default -> InternshipLevel.BASIC;
        };

		String preferredMajor = Utils.inputString("Enter preferred major: ");
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
				title,
				description,
				level,
				preferredMajor,
				openingDate,
				closingDate,
                rep.getCompany(),
				reps,
				numSlots
        );

        internshipOpportunityController.createInternshipOpportunity(opportunity);
        Utils.clear();   
        System.out.println("Internship opportunity '" + title + "' created successfully!");   
		System.out.println();
    }

    private void viewCreatedInternships() {
        Utils.clear();

        boolean loop = true;
        while (loop) {
            List<InternshipOpportunity> opportunities = internshipOpportunityController.getInternshipOpportunitiesByCompanyRepresentative(rep);
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
            System.out.println("Preferred Major: " + opp.getPreferredMajor());
            System.out.println("Application Open: " + opp.getApplicationOpeningDate());
            System.out.println("Application Close: " + opp.getApplicationClosingDate());
            System.out.println("Slots: " + opp.getNumberOfSlots());
            System.out.println("Status: " + opp.getStatus());
            System.out.println("Visibility: " + opp.getVisibility());
			System.out.println();
            System.out.println("1) Select another internship opportunity.");
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
        boolean loop = true;

        while (loop) {

            // Get only opportunities that belong to this representative
            List<InternshipOpportunity> repOpps =
                internshipOpportunityController.getInternshipOpportunitiesByCompanyRepresentative(rep);

            // Build pending applications list ONLY by scanning opportunities (NOT manually scanning all apps)
            ArrayList<InternshipApplication> pendingApps = new ArrayList<>();

            for (InternshipOpportunity opp : repOpps) {
                if (opp.getSlotsLeft() == 0) {
                    continue;
                }

                ArrayList<InternshipApplication> apps = applicationController.getInternshipApplications();
                for (InternshipApplication app : apps) {
                    if ((Objects.equals(app.getStatus(), Status.PENDING)) 
                    && (app.getInternshipOpportunity().getCompanyRepresentatives().contains(rep))) {
                        pendingApps.add(app);
                    }
                }
            }

            if (pendingApps.isEmpty()) {
                Utils.clear();
                System.out.println("There are no pending student internship applications.");
                System.out.println();
                break;
            }

            System.out.println("Pending Internship Applications:");
            for (int i = 0; i < pendingApps.size(); i++) {
                InternshipApplication app = pendingApps.get(i);
                InternshipOpportunity opp = app.getInternshipOpportunity();
                Student student = app.getApplicant();

                System.out.println((i + 1) + ") " + student.getName() +
                                " (" + opp.getInternshipTitle() + ")");
            }
            System.out.println();

            int choice = Utils.inputInt("Enter the number of an application to view options (or -1 to exit): ");
            if (choice == -1) {
                break;
            }

            InternshipApplication chosenApp = null;
            try {
                chosenApp = pendingApps.get(choice - 1);
            } catch (IndexOutOfBoundsException e) {
                Utils.clear();
                System.out.println("Invalid selection. Please enter a valid number (1 to " + pendingApps.size() + ").");
                System.out.println();
                continue;
            }

            Utils.clear();

            InternshipOpportunity opp = chosenApp.getInternshipOpportunity();
            Student applicant = chosenApp.getApplicant();

            System.out.println("Application " + choice);
            System.out.println("Title: " + opp.getInternshipTitle());
            System.out.println("Student name: " + applicant.getName());
            System.out.println("Major: " + applicant.getMajor());
            System.out.println("Year of study: " + applicant.getYearOfStudy());
            System.out.println();

            System.out.println("1) Approve this application.");
            System.out.println("2) Reject this application.");
            System.out.println("3) Select another application.");
            System.out.println("4) Exit.");

            int subChoice = Utils.inputInt("Enter an option: ");

            switch (subChoice) {
                case 1:
                    // Approved 
                    chosenApp.setStatus(Status.SUCCESSFUL);
                    opp.decrementSlotsLeft();
                    if (opp.getSlotsLeft() == 0) {
                        opp.setStatus(Status.FILLED);
                    }
                    Utils.clear();
                    System.out.println("The application has been approved.");
                    System.out.println();
                    break;

                case 2:
                    // Rejected (original logic)
                    chosenApp.setStatus(Status.REJECTED);
                    Utils.clear();
                    System.out.println("The application has been rejected.");
                    System.out.println();
                    break;

                case 3:
                    Utils.clear();
                    continue;

                case 4:
                    Utils.clear();
                    loop = false;
                    break;
            }
        }
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

}
