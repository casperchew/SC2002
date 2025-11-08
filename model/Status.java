package model;

/**
 * Represents the status of an application or process within the internship management system.
 * <p>
 * Status values are typically used to track the progress of applications,
 * company representative approvals, or internship opportunities.
 * </p>
 *
 * <ul>
 *   <li>{@link #PENDING} – The application or request is awaiting review or action.</li>
 *   <li>{@link #APPROVED} – The application or request has been accepted.</li>
 *   <li>{@link #REJECTED} – The application or request has been declined.</li>
 *   <li>{@link #FILLED} – The position or opportunity has been filled and is no longer available.</li>
 * </ul>
 *
 * @see model.Application
 * @see model.internship.InternshipOpportunity
 * @see model.user.CompanyRepresentative
 */
public enum Status {
    /** Pending review or action. */
    PENDING,

    /** Approved or accepted. */
    APPROVED,

    /** Rejected or declined. */
    REJECTED,

    /** Filled or no longer available. */
    FILLED
}
