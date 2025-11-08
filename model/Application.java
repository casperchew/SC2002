package model;

import java.time.LocalDate;

/**
 * Represents a general application submitted by a {@link User} in the system.
 * <p>
 * This abstract class serves as a base for different types of applications,
 * such as {@link model.internship.InternshipApplication}. It stores common
 * attributes like the applicant, application status, and the date of submission.
 * </p>
 *
 * <p>
 * The status of an application is represented by the {@link Status} enum and
 * is initialized to {@link Status#PENDING} by default. The date applied is
 * automatically set to the current date at the time of creation.
 * </p>
 *
 * @see model.User
 * @see model.Status
 */
public abstract class Application {

    /** The user who submitted the application. */
    protected User applicant;

    /** Current status of the application. */
    protected Status status;

    /** The date when the application was submitted. */
    protected LocalDate dateApplied;

    /**
     * Constructs a new {@code Application} with the specified applicant.
     *
     * @param applicant the user submitting the application; must not be null
     * @throws IllegalArgumentException if {@code applicant} is null
     */
    public Application(User applicant) {
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null.");
        }
        this.applicant = applicant;
        this.dateApplied = LocalDate.now(); 
        this.status = Status.PENDING; 
    }

    /**
     * Returns the applicant who submitted this application.
     *
     * @return the applicant
     */
    public User getApplicant() {
        return this.applicant;
    }

    /**
     * Updates the applicant for this application.
     *
     * @param applicant the new applicant
     */
    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    /**
     * Returns the current status of this application.
     *
     * @return the status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Updates the status of this application.
     * <p>
     * This method is {@code protected} because only subclasses or
     * controlling classes should modify the application status.
     * </p>
     *
     * @param status the new status
     */
    protected void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Returns the date when this application was submitted.
     *
     * @return the date of application
     */
    public LocalDate getDateApplied() {
        return this.dateApplied;
    }
}
