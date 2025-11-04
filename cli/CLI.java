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
        int choice;
        int userID;
        String userIDString;
        String name;
        String password;
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
                    // login
                    case 1:
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

                    // create new account
                    case 2:
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
                                break;
                            case 2:
                                System.out.println("Not implemented");
                                break;
                            case 3:
                                System.out.println("Not implemented");
                                break;
                        }
                        break;
                    
                    case 3:
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
                    case Status.APPROVED:
                        // render the CompanyRepresentative menu if his application was accepted
                        user = RepMenu.runMenu(rep); // need to be changed to non static
                        break;
                    case Status.PENDING:
                        System.out.println("Your application is pending");
                        break;
                    case Status.REJECTED:
                        System.out.println("Your application has been rejected");
                        break;
                    case Status.FILLED:
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
