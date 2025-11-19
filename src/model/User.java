package src.model;

public abstract class User {
    private String userID;
    private String name;
    private String passwordHash;

    public User(String userID, String name, String passwordHash) {
        this.userID = userID;
        this.name = name;
        this.passwordHash = passwordHash;
    }

	/**
	 * @return {@code userID}
	 */
    public String getUserID() {
        return userID;
    }

	/**
	 * @param userID the userID to set
	 */
    public void setUserID(String userID) {
        this.userID = userID;
    }

	/**
	 * @return {@code name}
	 */
    public String getName() {
        return name;
    }

	/**
	 * @param name the name to set
	 */
    public void setName(String name) {
        this.name = name;
    }

	/**
	 * @return {@code passwordHash}
	 */
    public String getPasswordHash() {
        return passwordHash;
    }

	/**
	 * @param passwordHash the passwordHash to set
	 */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
