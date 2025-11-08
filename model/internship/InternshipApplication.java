package model.internship;

import model.*;
import model.user.*;

/**
 * Represents a student's application for a specific internship opportunity.
 * <p>
 * This class extends the generic {@link Application} model and adds fields and
 * logic specific to internship-related workflows, such as placement confirmation
 * and withdrawal handling.
 * </p>
 *
 * <p>
 * An {@code InternshipApplication} tracks the status of a student's submission for
 * an internship, including whether the placement has been confirmed by both parties
 * and whether a withdrawal has been requested or approved by the career center.
 * </p>
 *
 * <h3>Lifecycle Overview</h3>
 * <ul>
 *   <li><b>Created:</b> When a student applies for an internship opportunity.</li>
 *   <li><b>Placement confirmed:</b> After company approval and student acceptance.</li>
 *   <li><b>Withdrawal:</b> Student may request withdrawal; requires staff approval.</li>
 * </ul>
 *
 * @see model.Application
 * @see model.user.Student
 * @see model.internship.InternshipOpportunity
 */
public class InternshipApplication extends Application {

    /** The internship opportunity that the student applied for. */
    private InternshipOpportunity internshipOpportunity;

    /**
     * Indicates whether the student has confirmed the internship placement
     * after the company has approved their application.
     */
    private boolean placementConfirmed; // after company approves, the student has to accept. So we use placementConfirmed

    /** Indicates whether the student has requested to withdraw from the internship. */
    private boolean withdrawalRequested;

    /** Indicates whether the withdrawal request has been approved by staff. */
    private boolean withdrawalApproved;

    /**
     * Constructs a new {@code InternshipApplication} with the specified applicant and internship.
     *
     * @param applicant the student applying for the internship
     * @param internshipOpportunity the internship opportunity the student is applying to
     */
    public InternshipApplication(Student applicant, InternshipOpportunity internshipOpportunity) {
        super(applicant);
        this.internshipOpportunity = internshipOpportunity;
        this.placementConfirmed = false;
        this.withdrawalRequested = false;
        this.withdrawalApproved = false;
    }

    /**
     * Returns the internship opportunity associated with this application.
     *
     * @return the internship opportunity
     */
    public InternshipOpportunity getInternshipOpportunity() {
        return internshipOpportunity;
    }

    /**
     * Sets the internship opportunity associated with this application.
     *
     * @param internshipOpportunity the new internship opportunity
     */
    public void setInternshipOpportunity(InternshipOpportunity internshipOpportunity) {
        this.internshipOpportunity = internshipOpportunity;
    }

    /**
     * Returns whether the internship placement has been confirmed by the student.
     *
     * @return {@code true} if placement is confirmed; {@code false} otherwise
     */
    public boolean getPlacementConfirmed() {
        return this.placementConfirmed;
    }

    /**
     * Updates the placement confirmation status of this application.
     *
     * @param placementConfirmed {@code true} to mark as confirmed, {@code false} otherwise
     */
    public void setPlacementConfirmed(boolean placementConfirmed) {
        this.placementConfirmed = placementConfirmed;
    }

    /**
     * Returns whether the student has requested to withdraw from the internship.
     *
     * @return {@code true} if a withdrawal has been requested; {@code false} otherwise
     */
    public boolean getWithdrawalRequested() {
        return this.withdrawalRequested;
    }

    /**
     * Sets whether the student has requested to withdraw from the internship.
     *
     * @param withdrawalRequested {@code true} if a withdrawal request has been made
     */
    public void setWithdrawalRequested(boolean withdrawalRequested) {
        this.withdrawalRequested = withdrawalRequested;
    }

    /**
     * Returns whether the student's withdrawal request has been approved.
     *
     * @return {@code true} if the withdrawal request is approved; {@code false} otherwise
     */
    public boolean getWithdrawalApproved() {
        return this.withdrawalApproved;
    }

    /**
     * Sets whether the student's withdrawal request has been approved by staff.
     *
     * @param withdrawalApproved {@code true} to mark withdrawal as approved
     */
    public void setWithdrawalApproved(boolean withdrawalApproved) {
        this.withdrawalApproved = withdrawalApproved;
    }
}
