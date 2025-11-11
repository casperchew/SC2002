package src.model.user;

import src.model.Status;
import src.model.User;

public class CompanyRepresentative extends User {
	private String company;
	private Status status;

	public CompanyRepresentative(int userID, String name, String passwordHash, String company) {
		super(userID, name, passwordHash);
		this.company = company;
        this.status = Status.PENDING; // the company rep should have a RepresentativeApplication that contains the status, but can change later
	}

	public String getCompany() {
		return company;
	}

	public Status getStatus() {
		// this should get status from this.representativeApplication
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
