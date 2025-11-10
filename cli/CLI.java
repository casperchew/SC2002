package cli;

import java.util.ArrayList;
import java.util.Scanner;
import controller.*;
import model.*;
import model.internship.InternshipApplication;
import model.user.CareerCenterStaff;
import model.user.CompanyRepresentative;
import model.user.Student;
import java.util.List;

public class CLI {
    private Scanner sc = new Scanner(System.in);
	private UserController userController;
    private ApplicationController appController;
    private InternshipController internshipController;

    public CLI(Database db) {
        this.userController = new UserController(db);
        this.appController = new ApplicationController(db);
        this.internshipController = new InternshipController(db);
    }

    public void main() {
        int choice, userID;
        String userIDString, name, password, company;
        User user = null;

        boolean loop = true;
        System.out.println("To exit or logout of the program at any time, press ENTER");
        do {
            if (user == null) {
				System.out.println();
				System.out.println("=".repeat(20));
				System.out.println();
				System.out.println("1) Login");
				System.out.println("2) Create a new account");
                System.out.println("3) Exit");
				choice = inputInt("Select an option: ");
                switch (choice) {
                    case 1:
                        // login
                        System.out.println();
                        System.out.println("=".repeat(20));
                        System.out.println();
                        name = inputString("Enter your name: ");
                        password = inputString("Enter your password: ");

						user = userController.login(name, password);  // Returns null if cannot login

                        if (user == null) {
                            System.out.println("Account not found!");
                        } else {
							System.out.println("Logged in as " + user.getName());
						}

                        break;

                    
                    case 2:
                        // create new account
                        System.out.println();
                        System.out.println("=".repeat(20));
                        System.out.println();
                        System.out.println("Are you a:");
                        System.out.println("1) Student");
                        System.out.println("2) Company Representative");
                        System.out.println("3) Career Center Staff");
                        choice = inputInt("Enter a choice: ");

                        switch (choice) {
                            case 1:
                                // Handles student cases
                                System.out.println();
                                System.out.println("=".repeat(20));
                                System.out.println();

                                userIDString = inputString("Enter your student ID: ");
                                userID = Integer.parseInt(userIDString.substring(1, userIDString.length() - 1));
                                name = inputString("Enter your name: ");
                                password = inputString("Enter your password: ");
                                int yearOfStudy = inputInt("Enter your year: ");
                                String major = inputString("Enter your major: ");
                                Student s = new Student(userID, name, password, yearOfStudy, major);
                                userController.createStudent(s);
                                System.out.println("Student account created successfully!");
                                break;
                            case 2:
                                // Handles company rep cases
                                userIDString = inputString("Enter your student ID: ");
                                userID = Integer.parseInt(userIDString.substring(1, userIDString.length() - 1));
                                name = inputString("Enter your name: ");
                                password = inputString("Enter your password: ");
                                company = inputString("Enter your company name: ");
                                CompanyRepresentative r = new CompanyRepresentative(userID, name, password, company);
                                userController.createCompanyRep(r);
                                System.out.println("Company representative account created successfully!");
                                break;
                            case 3:
                                // Handles career centre staff cases
                                System.out.println("Not implemented");
                                break;
                        }
                        break;
                    
                    case 3:
                        // Exit
                        loop = false;
                    default:
                        break;
                }
            } else if (user instanceof Student) {
                // render Student Menu
                Student student = (Student)user;
                StudentMenu studentMenu = new StudentMenu(student, userController, appController, internshipController);
                user = studentMenu.runMenu();
            } else if (user instanceof CompanyRepresentative) {
                CompanyRepresentative rep = (CompanyRepresentative)user;
                switch (rep.getStatus()) {
                    case APPROVED:
                        // render the CompanyRepresentative menu if his application was accepted
                        user = RepMenu.runMenu(rep); // need to be changed to non static
                        break;
                    case PENDING:
                        System.out.println("Your application is pending");
                        break;
                    case REJECTED:
                        System.out.println("Your application has been rejected");
                        break;
                    case FILLED:
                        System.out.println("Your application has been filled");
                        break;
                }

            } else if (user instanceof CareerCenterStaff) {
                // render CareerCenterStaff Menu
                CareerCenterStaff staff = (CareerCenterStaff)user;
                StaffMenu staffMenu = new StaffMenu(staff, userController, appController, internshipController);
                user = staffMenu.runMenu(staff); // need to be changed to non static

            } else {
                System.out.println("Not implemented");
                loop = false;
            }


        } while (loop);
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
