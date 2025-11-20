package src.model.internship;

import java.time.LocalDate;

import src.enums.Status;
import src.model.User;
import src.model.user.Student;

/**
 * InternshipApplication Class
 */
public class InternshipApplication {
    private Student applicant;
    private InternshipOpportunity internshipOpportunity;

    private LocalDate dateApplied;
    private Status status = Status.PENDING;
    private boolean placementConfirmed = false;
    private boolean withdrawalRequested = false;
    private boolean withdrawalApproved = false;

	/**
	 * Constructs a {@code InternshipApplication} with the required attributes
	 *
	 * @param applicant The {@link Student} who is applying for the internship. Must not be null.
	 * @param internshipOpportunity The {@link InternshipOpportunity} the student is applying for. Must not be null.
	 * @throws IllegalArgumentException if the {@code applicant} is null.
	 * @throws IllegalArgumentException if the {@code internshipOpportunity} is null.
	 */
    public InternshipApplication(Student applicant, InternshipOpportunity internshipOpportunity) {
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null.");
        } else if (internshipOpportunity == null) {
			throw new IllegalArgumentException("Internship Opportunity cannot be null.");
		}

        this.applicant = applicant;
        this.internshipOpportunity = internshipOpportunity;
        this.dateApplied = LocalDate.now(); 
    }

	/**
	 * Getter for {@code applicant}
	 *
	 * @return applicant
	 */
    public Student getApplicant() {
        return applicant;
    }

	/**
	 * Setter for {@code applicant}
	 *
	 * @param applicant the {@code applicant} to set
	 */
    public void setApplicant(Student applicant) {
        this.applicant = applicant;
    }

	/**
	 * Getter for {@code internshipOpportunity}
	 *
	 * @return {@code internshipOpportunity}
	 */
    public InternshipOpportunity getInternshipOpportunity() {
        return internshipOpportunity;
    }

	/**
	 * Getter for {@code dateApplied}
	 *
	 * @return {@code dateApplied}
	 */
    public LocalDate getDateApplied() {
        return dateApplied;
    }

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

	/**
	 * Getter for {@code placementConfirmed}
	 *
	 * @return {@code placementConfirmed}
	 */
    public boolean getPlacementConfirmed() {
        return placementConfirmed;
    }

	/**
	 * Setter for {@code placementConfirmed}
	 *
	 * @param placementConfirmed the {@code placementConfirmed} to set
	 */
    public void setPlacementConfirmed(boolean placementConfirmed) {
        this.placementConfirmed = placementConfirmed;
    }

	/**
	 * Getter for {@code withdrawalRequested}
	 *
	 * @return {@code withdrawalRequested}
	 */
    public boolean getWithdrawalRequested() {
        return withdrawalRequested;
    }

	/**
	 * Setter for {@code withdrawalRequested}
	 *
	 * @param withdrawalRequested the {@code withdrawalRequested} to set
	 */
    public void setWithdrawalRequested(boolean withdrawalRequested) {
        this.withdrawalRequested = withdrawalRequested;
    }

	/**
	 * Getter for {@code withdrawalApproved}
	 *
	 * @return {@code withdrawalApproved}
	 */
    public boolean getWithdrawalApproved() {
        return withdrawalApproved;
    }

	/**
	 * Setter for {@code withdrawalApproved}
	 *
	 * @param withdrawalApproved the {@code withdrawalApproved} to set
	 */
    public void setWithdrawalApproved(boolean withdrawalApproved) {
        this.withdrawalApproved = withdrawalApproved;
    }
}
