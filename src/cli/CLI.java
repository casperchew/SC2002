package src.cli;

import src.controller.UserController;
import src.controller.ApplicationController;
import src.controller.InternshipOpportunityController;
import src.controller.Database;
import src.model.User;
import src.model.user.Student;
import src.model.user.CompanyRepresentative;
import src.model.user.CareerCenterStaff;
import src.utils.Utils;

public class CLI {
	private UserController userController;
    private ApplicationController appController;
    private InternshipOpportunityController internshipController;

    public CLI(Database db) {
        this.userController = new UserController(db);
        this.appController = new ApplicationController(db);
        this.internshipController = new InternshipOpportunityController(db);
    }

    public void main() {
        int choice;
        String userID;
        String name;
        String password;

        User user = null;

		Utils.clear();
        boolean loop = true;
        do {
            if (user == null) {
				System.out.println("Internship Placement Management System");
				System.out.println("--------------------------------------");
				System.out.println("1) Login");
				System.out.println("2) Create a new account");
                System.out.println("3) Exit");
                System.out.println("");
				choice = Utils.inputInt("Select an option: ");
                switch (choice) {
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

                    
                    case 2:
						Utils.clear();
                        System.out.println("Are you a:");
                        System.out.println("1) Student");
                        System.out.println("2) Company Representative");
                        System.out.println("3) Career Center Staff");
                        choice = Utils.inputInt("Enter a choice: ");

                        switch (choice) {
                            case 1:
                                userID = Utils.inputString("Enter your student ID: ");
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
                                userID = "1"; // Placeholder value
                                name = Utils.inputString("Enter your name: ");
                                String companyName = Utils.inputString("Company name: ");
                                password = Utils.inputString("Enter your password: ");
                                CompanyRepresentative rep = new CompanyRepresentative(userID, name, password, companyName);
                                userController.createCompanyRep(rep);
								Utils.clear();
								System.out.println("Your account is pending approval.");
                                System.out.println();
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
                    case APPROVED:
                        // render the CompanyRepresentative menu if his application was accepted
                        RepMenu repMenu = new RepMenu(rep, userController, appController, internshipController);
                        user = repMenu.runMenu(rep); // need to be changed to non static
                        break;
                    case PENDING:
                        System.out.println("Your account is pending approval.");
                        System.out.println();
                        user = null;
                        break;
                    case REJECTED:
                        System.out.println("Your account has been rejected.");
                        System.out.println();
                        user = null;
                        break;
                    case FILLED:
                        System.out.println("Your application has been filled.");
                        System.out.println();
                        user = null;
                        break;
                }
                user = null;

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
