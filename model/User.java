package model;

/**
 * Represents a generic user in the internship management system.
 * <p>
 * This abstract class serves as a base for different types of users,
 * such as {@link model.user.Student}, {@link model.user.CompanyRepresentative},
 * and {@link model.user.CareerCenterStaff}. It stores common user information
 * like ID, name, and password hash.
 * </p>
 *
 * <p>
 * Subclasses can extend this class to implement role-specific behavior and
 * attributes, while reusing the core properties and methods defined here.
 * </p>
 *
 * @see model.user.Student
 * @see model.user.CompanyRepresentative
 * @see model.user.CareerCenterStaff
 */
public abstract class User {

    /** Unique identifier for the user. */
    protected int userID;

    /** Name of the user. */
    protected String name;

    /** Password hash used for authentication. */
    protected String passwordHash;

    /**
     * Constructs a new {@code User} with the specified ID, name, and password hash.
     *
     * @param userID unique identifier for the user
     * @param name the user's name
     * @param passwordHash the hashed password for authentication
     */
    public User(int userID, String name, String passwordHash) {
        this.userID = userID;
        this.name = name;
        this.passwordHash = passwordHash;
    }

    /**
     * Returns the unique ID of the user.
     *
     * @return the user ID
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     * Sets the unique ID of the user.
     *
     * @param userID the new user ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Returns the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the password hash of the user.
     *
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the password hash of the user.
     *
     * @param passwordHash the new password hash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
