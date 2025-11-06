package controller;

import model.*;
import model.user.*;
import java.util.ArrayList;
import java.util.Objects;

public class UserController {
	private Database db;

	public UserController(Database db) {
		this.db = db;
	}

    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        
        allUsers.addAll(db.getStudents());
        allUsers.addAll(db.getCompanyRepresentatives());
        allUsers.addAll(db.getCareerCenterStaffs());
        return allUsers; // Return the combined list
    }

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

	public User login(String name, String passwordHash) {
		// System.out.println("=".repeat(20));
		// System.out.println(name);
		// System.out.println(passwordHash);
		for (User user: getAllUsers()) {
			// System.out.println(user.getName());
			// System.out.println(user.getPasswordHash());
            if (Objects.equals(name, user.getName()) && Objects.equals(passwordHash, user.getPasswordHash())) {
                return user;
            }
        }
		System.out.println("=".repeat(20));

        // Return null if no match is found
        return null;
	}

    public void saveAllUsers() {
        // save all user information to a csv file
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
