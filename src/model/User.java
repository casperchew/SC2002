package src.model;

public abstract class User {
    protected String userID;
    protected String name;
    protected String passwordHash;

    public User(String userID, String name, String passwordHash) {
        this.userID = userID;
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
