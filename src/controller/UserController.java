package src.controller;

import java.util.ArrayList;
import java.util.Objects;

import src.enums.Status;
import src.model.User;
import src.model.user.Student;
import src.model.user.CompanyRepresentative;
import src.model.user.CareerCenterStaff;

public class UserController {
	private Database db;

	public UserController(Database db) {
		this.db = db;
	}

	// CREATE
    public void createStudent(Student student) {
        // technically we should check if the account already exists
        db.createStudent(student);
    }

    public void createCompanyRep(CompanyRepresentative rep) {
        // technically we should check if the account already exists
        db.createCompanyRepresentative(rep);
    }

    public void createCareerCenterStaff(CareerCenterStaff staff) {
        // technically we should check if the account already exists
        db.createCareerCenterStaff(staff);
    }

	// READ
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        users.addAll(db.getStudents());
        users.addAll(db.getCompanyRepresentatives());
        users.addAll(db.getCareerCenterStaffs());

        return users;
    }

    public ArrayList<Student> getStudents() {
        return db.getStudents();
    }

    public ArrayList<CompanyRepresentative> getCompanyRepresentatives() {
        return db.getCompanyRepresentatives();
    }

    public ArrayList<CompanyRepresentative> getCompanyRepresentativesByStatus(Status status) {
		// TODO: use streams and filters instead
        ArrayList<CompanyRepresentative> reps = new ArrayList<CompanyRepresentative>();
        for (CompanyRepresentative rep: db.getCompanyRepresentatives()) {
            if (Objects.equals(rep.getStatus(), status)) {
                reps.add(rep);
            }
        }

        return reps;
    }

    public ArrayList<CareerCenterStaff> getCareerCenterStaff() {
        return db.getCareerCenterStaffs();
    }

	// OTHER METHODS
    public User login(String name, String passwordHash) {
        for (User user: getUsers()) {
            if (Objects.equals(name, user.getName()) && Objects.equals(passwordHash, user.getPasswordHash())) {
                return user;
            }
        }

        return null;
    }

    public void saveAllUsers() {
        // TODO: write users to csv
		// This method should be moved to Database.java
    }

}

// AuthManager.java
// package controller;
// import model.*;
// import java.util.List;
// import java.util.Objects;
//
// public class AuthManager {
//     private UserController userController;
//     private User currentUser;
//
//     public AuthManager(UserController userController) {
//         this.userController = userController;
//         this.currentUser = null; // Initialize currentUser to null as no one is logged in initially
//     }
//
//     public static String hash(String s) {
//         // hashing implement later
//         return s;
//     }
//
//     public User login(String name, String password) {
//         // hash password here (unhashed for now cuz lazy)
//         String passwordHash = hash(password);
//
//         List<User> allUsers = userController.getAllUsers();
//         for (User user : allUsers) {
//             if (Objects.equals(name, user.getName()) && Objects.equals(passwordHash, user.getPasswordHash())) {
//                 this.currentUser = user; 
//                 return user;
//             }
//         }
//         // return null if no match is found
//         return null;
//     }
//
//     public User getCurrentUser() {
//         return this.currentUser;
//     }
//
//     public void changePassword(String newPassword) {
//         currentUser.setPasswordHash(newPassword);
//     }
//
//     public void logout() {
//         // save all user array lists to a file
//         userController.saveAllUsers();
//
//         // clear session
//         this.currentUser = null;
//     }
// }
