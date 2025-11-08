package controller;

import model.*;
import model.user.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The {@code UserController} class manages all user-related operations within the system.
 * <p>
 * It serves as an intermediary between the user interface (CLI) and the underlying
 * {@link Database}, handling account creation, authentication (login), and retrieval
 * of user information. 
 * </p>
 *
 * <p>
 * The system supports three types of users:
 * <ul>
 *   <li>{@link Student}</li>
 *   <li>{@link CompanyRepresentative}</li>
 *   <li>{@link CareerCenterStaff}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Future implementations may include password hashing, persistent storage,
 * and comprehensive validation checks for account creation.
 * </p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 *     Database db = new Database();
 *     UserController userController = new UserController(db);
 *     Student student = new Student(101, "Alice", "password", 2, "Computer Science");
 *     userController.createStudent(student);
 *     User loggedIn = userController.login("Alice", "password");
 * </pre>
 *
 * @author  
 * @version 1.0
 */
public class UserController {

    /** Reference to the in-memory database instance. */
    private Database db;

    /**
     * Constructs a {@code UserController} instance with a reference to the system database.
     *
     * @param db the {@link Database} used to store and manage user data
     */
    public UserController(Database db) {
        this.db = db;
    }

    /**
     * Retrieves all users currently stored in the database, including students,
     * company representatives, and career center staff.
     *
     * @return an {@link ArrayList} containing all {@link User} objects in the system
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(db.getStudents());
        allUsers.addAll(db.getCompanyRepresentatives());
        allUsers.addAll(db.getCareerCenterStaffs());
        return allUsers;
    }

    /**
     * Creates a new student account in the database.
     * <p>
     * Note: This implementation does not check for duplicate usernames or IDs.
     * Validation should be added in future revisions.
     * </p>
     *
     * @param student the {@link Student} object to be added to the system
     */
    public void createStudent(Student student) {
        db.createStudent(student);
    }

    /**
     * Creates a new company representative account in the database.
     * <p>
     * Note: This implementation does not perform validation or approval checks.
     * </p>
     *
     * @param rep the {@link CompanyRepresentative} object to be added to the system
     */
    public void createCompanyRep(CompanyRepresentative rep) {
        db.createCompanyRepresentative(rep);
    }

    /**
     * Creates a new career center staff account in the database.
     *
     * @param staff the {@link CareerCenterStaff} object to be added to the system
     */
    public void createCareerCenterStaff(CareerCenterStaff staff) {
        db.createCareerCenterStaff(staff);
    }

    /**
     * Authenticates a user attempting to log in to the system.
     * <p>
     * This method searches through all user types and returns the user whose
     * {@code name} and {@code passwordHash} match the provided credentials.
     * </p>
     *
     * <p>
     * Currently, passwords are stored and compared as plain strings.
     * In future versions, this should be replaced with proper password hashing.
     * </p>
     *
     * @param name the username entered by the user
     * @param passwordHash the password (or hashed password) entered by the user
     * @return the matching {@link User} object if authentication succeeds, or {@code null} if login fails
     */
    public User login(String name, String passwordHash) {
        for (User user : getAllUsers()) {
            if (Objects.equals(name, user.getName()) && Objects.equals(passwordHash, user.getPasswordHash())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Saves all user information to persistent storage.
     * <p>
     * Currently a placeholder — this function is intended to export all
     * user data to an external file (e.g., CSV or JSON) in future versions.
     * </p>
     */
    public void saveAllUsers() {
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
