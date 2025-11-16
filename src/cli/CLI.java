package src.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import src.controller.*;
import src.model.*;
import src.model.internship.InternshipApplication;
import src.model.user.CareerCenterStaff;
import src.model.user.CompanyRepresentative;
import src.model.user.Student;
import src.utils.Utils;

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

		Utils.clear();
        boolean loop = true;
        do {
            if (user == null) {
				System.out.println("Internship Placement Management System");
				System.out.println("--------------------------------------");
				System.out.println("\t1) Login");
				System.out.println("\t2) Create a new account");
                System.out.println("\t3) Exit");
                System.out.println("");
				choice = Utils.inputInt("Select an option: ");
                switch (choice) {
                    // login
                    case 1:
                        name = Utils.inputString("Enter your name: ");
                        password = Utils.inputString("Enter your password: ");

						user = userController.login(name, password);  // Returns null if cannot login

                        if (user == null) {
							Utils.clear();
                            System.out.println("Invalid username or password.");
							System.out.println();
                        } else {
							Utils.clear();
							System.out.println("Logged in as " + user.getName());
							System.out.println();
						}

                        break;

                    // create new account
                    case 2:
						Utils.clear();
                        System.out.println("Are you a:");
                        System.out.println("\t1) Student");
                        System.out.println("\t2) Company Representative");
                        System.out.println("\t3) Career Center Staff");
                        choice = Utils.inputInt("Enter a choice: ");

                        switch (choice) {
                            case 1:
                                userIDString = Utils.inputString("Enter your student ID: ");
                                userID = Integer.parseInt(userIDString.substring(1, userIDString.length() - 1));
                                name = Utils.inputString("Enter your name: ");
                                password = Utils.inputString("Enter your password: ");
                                int yearOfStudy = Utils.inputInt("Enter your year: ");
                                String major = Utils.inputString("Enter your major: ");
                                Student s = new Student(userID, name, password, yearOfStudy, major, null);
                                userController.createStudent(s);
								user = userController.login(name, password);
								Utils.clear();
								System.out.println("Logged in as " + user.getName());
								System.out.println();
                                break;
                            case 2:
                                Random rand = new Random();
                                int repID = rand.nextInt(9000000) + 1000000;
                                name = Utils.inputString("Enter your name: ");
                                String companyName = Utils.inputString("Company name: ");
                                password = Utils.inputString("Enter your password: ");
                                CompanyRepresentative rep = new CompanyRepresentative(repID, name, password, companyName);
                                userController.createCompanyRep(rep);
								Utils.clear();
								System.out.println("Your account is pending approval.");
                                System.out.println();
                                break;
                            case 3:
                                System.out.println("Not implemented");
                                break;
                        }
                        break;
                    
                    case 3:
                        loop = false;
						break;

                    default:
						Utils.clear();
						System.out.println("Invalid option!");
						System.out.println("");
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
                        RepMenu repMenu = new RepMenu(rep, userController, appController, internshipController);
                        user = repMenu.runMenu(rep); // need to be changed to non static
                        break;
                    case Status.PENDING:
                        System.out.println("Your account is pending approval.");
                        System.out.println();
                        user = null;
                        break;
                    case Status.REJECTED:
                        System.out.println("Your account has been rejected.");
                        System.out.println();
                        user = null;
                        break;
                    case Status.FILLED:
                        System.out.println("Your application has been filled.");
                        System.out.println();
                        user = null;
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
}
