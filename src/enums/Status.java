package src.enums;

/**
 * The {@link Status} enum is used for applications such as the creation of {@link src.model.user.CompanyRepresentative} accounts and {@link src.model.internship.InternshipApplication}
 */
public enum Status {
	/** Pending */
    PENDING,
	/** Approved */
    APPROVED,
	/** Rejected */
    REJECTED,
	/** Filled */
    FILLED
}
