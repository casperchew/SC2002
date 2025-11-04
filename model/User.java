package model;

public abstract class User {
    protected int userID;
    protected String name;
    protected String passwordHash;

    public User(int userID, String name, String passwordHash) {
        this.userID = userID;
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
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
