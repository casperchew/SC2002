package cli;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.user.CompanyRepresentative;
import model.internship.InternshipOpportunity;
import model.InternshipLevel;

public class RepMenu {
    private static Scanner sc = new Scanner(System.in);

    public static User runMenu(CompanyRepresentative rep) {
        int choice;
        System.out.println();
        System.out.println("=".repeat(20));
        System.out.println();
        System.out.println("1) Log out");
        System.out.println("2) Create internship opportunity");
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
                boolean isVisible;

                internshipTitle = inputString("Set the internship title: ");
                description = inputString("Set the description for the internship: ");
                System.out.println("\nBasic - 1\nIntermediate - 2\nAdvanced");
                levelInt = inputInt("Set the internship level: ");
                level = InternshipLevel.fromValue(levelInt);
                numMajors = inputInt("Enter number of preferred majors you want to set for this internship: ");
                sc.nextLine();

                for (int i = 0; i < numMajors; i++) {
                    preferredMajors.add(inputString("Enter preferred major " + i + ": "));
                }

                

                InternshipOpportunity opportunity = new InternshipOpportunity(
                    internshipTitle, description, level, preferredMajors, openingDate,
                    closingDate, rep.getCompany(), companyRepresenatives, numOfSlots, isVisible
                );
                break;
            default:
				return rep;
        }
    }

    private static String inputString(String text) {
        System.out.println(text);
        String s = sc.nextLine();
        if (s.isEmpty()) {
            System.exit(0);
        }
        return s;
    }

    private static int inputInt(String text) {
        System.out.println(text);
        int n = sc.nextInt();
        sc.nextLine();
        if (n == 0) {
            System.exit(0);
        }
        
        return n;
	}
}
