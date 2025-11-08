package model.user;

import model.User;
import model.Status;

/**
 * Represents a company representative in the internship management system.
 * <p>
 * A company representative can create and manage internship opportunities,
 * view student applications, and interact with career center staff.
 * </p>
 * 
 * <p>
 * Each representative is associated with a company and has a status indicating
 * whether their account or application has been approved, rejected, or is pending.
 * </p>
 *
 * @see model.User
 * @see model.Status
 */
public class CompanyRepresentative extends User {

    /** Name of the company the representative belongs to. */
    private String company;

    /** Status of the representative's application (PENDING, APPROVED, REJECTED, FILLED). */
    private Status status;

    /**
     * Constructs a new {@code CompanyRepresentative} with the specified ID, name,
     * password hash, and company name.
     * <p>
     * The status is initialized to {@link Status#PENDING} by default.
     * </p>
     *
     * @param userID unique identifier for the representative
     * @param name the representative's name
     * @param passwordHash hashed password for authentication
     * @param company the company name associated with the representative
     */
    public CompanyRepresentative(int userID, String name, String passwordHash, String company) {
        super(userID, name, passwordHash);
        this.company = company;
        this.status = Status.PENDING; // the company rep should have a RepresentativeApplication that contains the status, but can change later
	}
    }

    /**
     * Returns the company name associated with this representative.
     *
     * @return the company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Returns the current status of this representative's application.
     * <p>
     * In a future implementation, this status may be linked to a separate
     * {@code RepresentativeApplication} object.
     * </p>
     *
     * @return the status of the representative
     */
    public Status getStatus() {
		// this should get status from this.representativeApplication
        return status;
    }

    /**
     * Updates the status of this representative's application.
     *
     * @param status the new status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
