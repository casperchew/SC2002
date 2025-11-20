package src.model;

/**
 * Base abstract class for all users.
 */
public abstract class User {
    private String userID;
    private String name;
    private String passwordHash;

	/**
	 * Constructs a {@link User} from the required attributes.
	 *
	 * @param userID the userID of the {@link User}
	 * @param name the name of the {@link User}
	 * @param passwordHash the passwordHash of the {@link User}
	 */
    public User(String userID, String name, String passwordHash) {
        this.userID = userID;
        this.name = name;
        this.passwordHash = passwordHash;
    }

	/**
	 * Getter for {@code userID}.
	 *
	 * @return {@code userID}.
	 */
    public String getUserID() {
        return userID;
    }

	/**
	 * Setter for {@code userID}.
	 *
	 * @param userID The userID to set.
	 */
    public void setUserID(String userID) {
        this.userID = userID;
    }

	/**
	 * Getter for {@code name}.
	 *
	 * @return {@code name}.
	 */
    public String getName() {
        return name;
    }

	/**
	 * Setter for {@code name}.
	 *
	 * @param name The name to set.
	 */
    public void setName(String name) {
        this.name = name;
    }

	/**
	 * Getter for {@code passwordHash}.
	 *
	 * @return {@code passwordHash}
	 */
    public String getPasswordHash() {
        return passwordHash;
    }

	/**
	 * Setter for {@code passwordHash}.
	 *
	 * @param passwordHash The passwordHash to set.
	 */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
