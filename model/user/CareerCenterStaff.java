package model.user;

import model.User;

/**
 * Represents a career center staff member in the internship management system.
 * <p>
 * This class extends {@link User} and can be used to manage internship
 * opportunities, approve or reject applications, and oversee students and
 * company representatives.
 * </p>
 *
 * <p>
 * Currently, this class only stores basic user information inherited from
 * {@link User} such as ID, name, and password hash. Additional staff-specific
 * functionality is handled elsewhere in the system (e.g., via {@link controller.StaffMenu}).
 * </p>
 *
 * @see model.User
 */
public class CareerCenterStaff extends User {

    /**
     * Constructs a new {@code CareerCenterStaff} with the specified ID, name,
     * and password hash.
     *
     * @param userID unique identifier for the staff member
     * @param name the staff member's name
     * @param passwordHash hashed password for authentication
     */
    public CareerCenterStaff(int userID, String name, String passwordHash) {
        super(userID, name, passwordHash);
    }
}
