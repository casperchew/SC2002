package src.model.user;

import src.enums.Status;
import src.model.User;

public class CompanyRepresentative extends User {
	private String company;
	private Status status;

	public CompanyRepresentative(String userID, String name, String passwordHash, String company) {
		super(userID, name, passwordHash);
		this.company = company;
        this.status = Status.PENDING;
	}

	// Getters and Setters
	// -------------------
	// Company
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	// Status
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	// -------------------
}
