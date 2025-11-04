package cli;

import java.util.Scanner;
import java.util.ArrayList;

import controller.ApplicationController;
import controller.InternshipController;
import controller.UserController;
import model.User;
import model.user.CareerCenterStaff;
import model.user.Student;
import model.internship.*;

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
        System.out.println("1) Approve/reject company representatives");
        System.out.println("2) Approve/reject internship opportunities");
        System.out.println("3) Approve/reject student withdrawal requests");
        System.out.println("4) Generate internship report");
        System.out.println("5) Logout");
        System.out.println("6) View all internship applications"); // for testing
        choice = inputInt("Enter an option: ");

        switch (choice) {
            case 1:
                System.out.println("Not implemented");
				return staff;
            case 2:
                System.out.println("Not implemented");
				return staff;
            case 3:
                System.out.println("Not implemented");
				return staff;
            case 4:
                System.out.println("Not implemented");
				return staff;
            case 5:
                System.out.println("Logging out...");
				return null;
            case 6:
                ArrayList<InternshipApplication> applications = appController.getInternshipApplications();
                System.out.println();
                System.out.println("=".repeat(20));
                System.out.println();
                for (InternshipApplication application: applications) {
                    System.out.println("Pending internship applications: ");
                    System.out.println("Applicant: " + application.getApplicant().getName());
                    System.out.println("Internship Title: " + application.getInternshipOpportunity().getInternshipTitle());
                    System.out.println("Internship Company: " + application.getInternshipOpportunity().getCompanyName());
                    System.out.println();
                }
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
}
