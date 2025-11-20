package src.model.internship;

import java.time.LocalDate;
import java.util.ArrayList;

import src.enums.InternshipLevel;
import src.enums.Status;
import src.model.user.CompanyRepresentative;

/** InternshipOpportunity Class */
public class InternshipOpportunity {

	/** The maximum number of slots that an internship opportunity is allowed to open. */
    public static final int MAX_NUM_SLOTS = 10;

    private String internshipTitle;
    private String description;
    private InternshipLevel internshipLevel;
    private String preferredMajor;  
    private LocalDate applicationOpeningDate;
    private LocalDate applicationClosingDate;
    private Status status;
    private String companyName;
    private ArrayList<CompanyRepresentative> companyRepresentatives;
    private int numberOfSlots;

    private boolean visible = false;
	/**
	 * Constructs a {@code InternshipOpportunity} instance with all required fields.
	 *
	 * @param internshipTitle The {@code title} of the internship opportunity.
	 * @param description The {@code description} of the internship opportunity.
	 * @param internshipLevel The internship level of the internship opportunity.
	 * @param preferredMajor The preferred major of the internship opportunity.
	 * @param applicationOpeningDate The application opening date of the internship opportunity. Must not be null.
	 * @param applicationClosingDate The application closing date of the internship opportunity. Must not be null and must be on or after the opening date.
	 * @param companyName The name of the company offering the internship opportunity.
	 * @param companyRepresentatives The list of company representatives associated with the internship opportunity. Can be null.
	 * @param numberOfSlots The maximum number of slots available for this internship opportunity. Must be greater than 0 and less than or equal to {@code MAX_NUM_SLOTS}.
	 * @throws IllegalArgumentException if the application dates are invalid (null or closing date is before opening date).
	 * @throws IllegalArgumentException if the number of slots is outside the valid range (1 to {@code MAX_NUM_SLOTS}).
	 */
    public InternshipOpportunity(
            String internshipTitle,
            String description,
            InternshipLevel internshipLevel,
            String preferredMajor,
            LocalDate applicationOpeningDate,
            LocalDate applicationClosingDate,
            String companyName,
            ArrayList<CompanyRepresentative> companyRepresentatives,
            int numberOfSlots
    ) {
        if (applicationOpeningDate == null || applicationClosingDate == null || applicationClosingDate.isBefore(applicationOpeningDate)) {
            throw new IllegalArgumentException("Invalid date range: Opening and closing dates must be provided, and closing date must be on or after opening date.");
        }

        if (numberOfSlots <= 0 || numberOfSlots > MAX_NUM_SLOTS) {
            throw new IllegalArgumentException("Number of slots must be between 1 and " + MAX_NUM_SLOTS + ".");
        }

        this.internshipTitle = internshipTitle;
        this.description = description;
        this.internshipLevel = internshipLevel;
        this.preferredMajor = preferredMajor;
        this.applicationOpeningDate = applicationOpeningDate;
        this.applicationClosingDate = applicationClosingDate;
        this.companyName = companyName;
        this.numberOfSlots = numberOfSlots;

        if (companyRepresentatives != null) {
            this.companyRepresentatives = new ArrayList<CompanyRepresentative>(companyRepresentatives);
        } else {
            this.companyRepresentatives = new ArrayList<CompanyRepresentative>();
        }

        this.status = Status.PENDING; 
    }

	/**
	 * Getter for {@code internshipTitle}
	 *
	 * @return {@code internshipTitle}
	 */
    public String getInternshipTitle() {
        return internshipTitle;
    }

	/**
	 * Setter for {@code internshipTitle}
	 *
	 * @param internshipTitle the {@code internshipTitle} to set
	 */
    public void setInternshipTitle(String internshipTitle) {
        this.internshipTitle = internshipTitle;
    }

	/**
	 * Getter for {@code description}
	 *
	 * @return {@code description}
	 */
    public String getDescription() {
        return description;
    }

	/**
	 * Setter for {@code description}
	 *
	 * @param description the {@code description} to set
	 */
    public void setDescription(String description) {
        this.description = description;
    }

	/**
	 * Getter for {@code internshipLevel}
	 *
	 * @return {@code internshipLevel}
	 */
    public InternshipLevel getInternshipLevel() {
        return internshipLevel;
    }

	/**
	 * Setter for {@code internshipLevel}
	 *
	 * @param internshipLevel the {@code internshipLevel} to set
	 */
    public void setInternshipLevel(InternshipLevel internshipLevel) {
        this.internshipLevel = internshipLevel;
    }

	/**
	 * Getter for {@code preferredMajor}
	 *
	 * @return {@code preferredMajor}
	 */
    public String getPreferredMajor() {
        return preferredMajor;
    }

	/** Setter for {@code preferredMajor}
	 *
	 * @param preferredMajor the {@code preferredMajor} to set
	 */
    public void setPreferredMajor(String preferredMajor) {
        this.preferredMajor = preferredMajor;
    }

	/**
	 * Getter for {@code applicationOpeningDate}
	 *
	 * @return {@code applicationOpeningDate}
	 */
    public LocalDate getApplicationOpeningDate() {
        return applicationOpeningDate;
    }

	/**
	 * Setter for {@code applicationOpeningDate}
	 *
	 * @param applicationOpeningDate the {@code applicationOpeningDate} to set
	 */
    public void setApplicationOpeningDate(LocalDate applicationOpeningDate) {
        this.applicationOpeningDate = applicationOpeningDate;
    }

	/**
	 * Getter for {@code applicationClosingDate}
	 *
	 * @return {@code applicationClosingDate}
	 */
    public LocalDate getApplicationClosingDate() {
        return applicationClosingDate;
    }

	/**
	 * Setter for {@code applicationClosingDate}
	 *
	 * @param applicationClosingDate the {@code applicationClosingDate} to set
	 */
    public void setApplicationClosingDate(LocalDate applicationClosingDate) {
        this.applicationClosingDate = applicationClosingDate;
    }

	/**
	 * Getter for {@code status}
	 * 
	 * @return {@code status}
	 */
    public Status getStatus() {
        return status;
    }

	/** Setter for {@code status}
	 *
	 * @param status the {@code status} to set
	 */
    public void setStatus(Status status) {
        this.status = status;
    }

	/**
	 * Getter for {@code companyName}
	 *
	 * @return {@code companyName}
	 */
    public String getCompanyName() {
        return companyName;
    }

	/**
	 * Setter for {@code companyName}
	 *
	 * @param companyName the {@code companyName} to set
	 */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

	/**
	 * Getter for {@code companyRepresentatives}
	 *
	 * @return {@code companyRepresentatives}
	 */
    public ArrayList<CompanyRepresentative> getCompanyRepresentatives() { 
		if (companyRepresentatives == null) {
			return new ArrayList<>(); 
		}

		// Return shallow copy
        return new ArrayList<CompanyRepresentative>(companyRepresentatives); 
    }

    /**
	 * Adds {@code repToAdd} to {@code companyRepresentatives}
	 *
	 * @param repToAdd the {@link CompanyRepresentative} to add
     * @return 0 if successful, 1 if unsuccessful
     */
    public int addCompanyRepresentative(CompanyRepresentative repToAdd) {
        if (repToAdd == null) {
            return 1;
        }

        if (companyRepresentatives.contains(repToAdd)) {
            return 1; 
        }

        companyRepresentatives.add(repToAdd);
        return 0;
    }
    
    /**
	 * Deletes {@code repToDelete} from {@code companyRepresentatives}
	 *
	 * @param repToDelete the {@link CompanyRepresentative} to delete
     * @return 0 if successful, 1 if unsuccessful
     */
    public int deleteCompanyRepresentative(CompanyRepresentative repToDelete) {
        if (repToDelete == null) {
            return 1;
        }

        boolean removed = companyRepresentatives.remove(repToDelete);
        return removed ? 0 : 1;
    }

	/**
	 * Getter for {@code numberOfSlots}
	 *
	 * @return {@code numberOfSlots}
	 */
    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    /**
	 * Setter for {@code numberOfSlots}
	 *
	 * @param numberOfSlots the {@code numberOfSlots} to set. Must be between 1 and {@link MAX_NUM_SLOTS} inclusive.
     * @return 0 if successful, 1 if unsuccessful.
     */
    public int setNumberOfSlots(int numberOfSlots) {
        if (numberOfSlots <= 0 || numberOfSlots > MAX_NUM_SLOTS) {
            return 1;
        }

        this.numberOfSlots = numberOfSlots;
        return 0;
    }

	/**
	 * Getter for {@code visible}
	 *
	 * @return {@code visible}
	 */
    public boolean getVisibility() {
        return visible;
    }

	/**
	 * Setter for {@code visible}
	 *
	 * @param visible the {@code visible} to set
	 */
    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

}
