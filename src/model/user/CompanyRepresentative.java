package src.model.user;

import java.util.Objects;

import src.enums.Status;
import src.model.User;

/**
 * CompanyRepresentative Class
 */
public class CompanyRepresentative extends User {
	private String company;
	private Status status;

	/**
	 * Constructs a CompanyRepresentative with the required attributes
	 *
	 * @param userID the company email address of the company representative
	 * @param name the {@code name} of the company representative
	 * @param passwordHash the {@code passwordHash} of the company representative
	 * @param company the {@code company} of the company representative
	 */
	public CompanyRepresentative(String userID, String name, String passwordHash, String company) {
		super(userID, name, passwordHash);
		this.company = company;
        this.status = Status.PENDING;
	}

	// Getters and Setters
	// -------------------
	// Company
	/**
	 * Getter for {@code company}
	 *
	 * @return {@code company}
	 */
	public String getCompany() {
		return company;
	}

	/** Setter for {@code company}
	 *
	 * @param company the {@code company} to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	// Status
	/**
	 * Getter for {@code status}
	 *
	 * @return {@code status}
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Setter for {@code status}
	 *
	 * @param status the {@code status} to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	// -------------------

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		CompanyRepresentative other = (CompanyRepresentative) obj;
		return Objects.equals(this.getUserID(), other.getUserID());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserID());
	}

}
