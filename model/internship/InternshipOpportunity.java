package model.internship;

import model.*;
import model.user.CompanyRepresentative;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents an internship opportunity offered by a company.
 * <p>
 * This class contains details about the internship, including its title,
 * description, level, preferred majors, application dates, number of slots,
 * visibility status, and the company representatives responsible for the posting.
 * </p>
 *
 * <p>
 * Each internship opportunity must be approved by career center staff before
 * becoming visible to students. The number of available slots is limited and
 * must not exceed {@link #MAX_NUM_SLOTS}.
 * </p>
 *
 * <p><b>Lifecycle Overview:</b></p>
 * <ul>
 *   <li><b>Created:</b> When a company representative submits a new internship opportunity.</li>
 *   <li><b>Status:</b> Initially set to {@link Status#PENDING}; can later become APPROVED or REJECTED.</li>
 *   <li><b>Visibility:</b> Controlled via the {@link #isVisible} field; only approved internships are shown to students.</li>
 * </ul>
 *
 * @see model.internship.Status
 * @see model.user.CompanyRepresentative
 */
public class InternshipOpportunity {

    /** Maximum allowed number of slots for an internship opportunity. */
    public static final int MAX_NUM_SLOTS = 10;

    /** Title of the internship. */
    private String internshipTitle;

    /** Description of the internship. */
    private String description;

    /** Internship level (e.g., BASIC or ADVANCED). */
    private InternshipLevel internshipLevel;

    /** List of preferred student majors for this internship. */
    private ArrayList<String> preferredMajors;

    /** Date when applications open. */
    private LocalDate applicationOpeningDate;

    /** Date when applications close. */
    private LocalDate applicationClosingDate;

    /** Current status of the internship (PENDING, APPROVED, REJECTED). */
    private Status status; // Career Center Staff needs to approve the internshipOpportunity first before it can become visible

    /** Name of the company offering the internship. */
    private String companyName;

    /** List of company representatives associated with this internship. */
    private List<CompanyRepresentative> companyRepresentatives;

    /** Number of available slots for this internship. */
    private int numberOfSlots;

    /** Visibility flag: true if visible to students, false otherwise. */
    public boolean isVisible;

    /**
     * Constructs a new {@code InternshipOpportunity} with the specified properties.
     *
     * @param internshipTitle the title of the internship
     * @param description a brief description of the internship
     * @param internshipLevel the level of the internship (BASIC/ADVANCED)
     * @param preferredMajors list of preferred majors for the internship
     * @param applicationOpeningDate date when applications start
     * @param applicationClosingDate date when applications close; must be on or after the opening date
     * @param companyName the name of the company offering the internship
     * @param companyRepresentatives list of company representatives managing the internship
     * @param numberOfSlots number of available slots (must be between 1 and {@link #MAX_NUM_SLOTS})
     * @param isVisible initial visibility of the internship to students
     *
     * @throws IllegalArgumentException if dates are invalid or number of slots is outside allowed range
     */
    public InternshipOpportunity(String internshipTitle, String description, InternshipLevel internshipLevel, 
                                 ArrayList<String> preferredMajors, LocalDate applicationOpeningDate, LocalDate applicationClosingDate, 
                                 String companyName, List<CompanyRepresentative> companyRepresentatives, int numberOfSlots, boolean isVisible) {
        // input validation
        if (applicationOpeningDate == null || applicationClosingDate == null || applicationClosingDate.isBefore(applicationOpeningDate)) {
            // throw an exception if dates are null or closing date is before opening date
            throw new IllegalArgumentException("Invalid date range: Opening and closing dates must be provided, and closing date must be on or after opening date.");
        }
        if (numberOfSlots <= 0 || numberOfSlots > MAX_NUM_SLOTS) {
            throw new IllegalArgumentException("Number of slots must be between 1 and " + MAX_NUM_SLOTS + ".");
        }

        this.internshipTitle = internshipTitle;
        this.description = description;
        this.internshipLevel = internshipLevel;
        this.preferredMajors = preferredMajors;
        this.applicationOpeningDate = applicationOpeningDate;
        this.applicationClosingDate = applicationClosingDate;
        this.companyName = companyName;
        this.numberOfSlots = numberOfSlots;
        this.isVisible = isVisible;
        
        if (companyRepresentatives != null) {
        this.companyRepresentatives = new ArrayList<>(companyRepresentatives);
        } else {
            this.companyRepresentatives = new ArrayList<>();
        }
        
        // when first created, the status should be PENDING 
        this.status = Status.PENDING; 
    }

    /**
     * Returns the internship title.
     *
     * @return the title
     */
    public String getInternshipTitle() { 
        return this.internshipTitle;
    }
    

    /**
     * Sets the internship title.
     *
     * @param internshipTitle new title
     */
    public void setInternshipTitle(String internshipTitle) {
        this.internshipTitle = internshipTitle;
    }

    /**
     * Returns the internship description.
     *
     * @return description
     */
    public String getDescription() { 
        return this.description;
    }

    /**
     * Sets the internship description.
     *
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the internship level.
     *
     * @return level
     */
    public InternshipLevel getInternshipLevel() {
        return this.internshipLevel;
    }

    /**
     * Sets the internship level.
     *
     * @param internshipLevel new level
     */
    public void setInternshipLevel(InternshipLevel internshipLevel) {
        return this.isVisible;
    }

    /**
     * Returns whether the internship is visible to students.
     *
     * @return {@code true} if the internship is visible; {@code false} otherwise
     */
    public boolean getVisibility() {
        return this.isVisible;
    }
    
    /**
     * Sets the visibility of the internship to students.
     *
     * @param isVisible {@code true} to make the internship visible; {@code false} to hide it
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Updates the level of the internship.
     *
     * @param internshipLevel the new internship level
     */
    public void setInternshipLevel(InternshipLevel internshipLevel) {
        this.internshipLevel = internshipLevel;
    }

    /**
     * Returns the preferred majors for this internship.
     *
     * @return list of preferred majors
     */
    public ArrayList<String> getPreferredMajors() { 
         return this.preferredMajors;
    }

    /**
     * Sets the preferred majors for this internship.
     *
     * @param preferredMajors list of majors
     */
    public void setPreferredMajors(ArrayList<String> preferredMajors) { 
        this.preferredMajors = preferredMajors;
    }

    /**
     * Returns the internship application opening date.
     *
     * @return opening date
     */
    public LocalDate getApplicationOpeningDate() {
        return this.applicationOpeningDate;
    }

    /**
     * Sets the internship application opening date.
     *
     * @param applicationOpeningDate new opening date
     */
    public void setApplicationOpeningDate(LocalDate applicationOpeningDate) {
        this.applicationOpeningDate = applicationOpeningDate;
    }

    /**
     * Returns the internship application closing date.
     *
     * @return closing date
     */
    public LocalDate getApplicationClosingDate() {
        return this.applicationClosingDate;
    }

    /**
     * Sets the internship application closing date.
     *
     * @param applicationClosingDate new closing date
     */
    public void setApplicationClosingDate(LocalDate applicationClosingDate) {
        this.applicationClosingDate = applicationClosingDate;
    }

    /**
     * Returns the current status of the internship.
     *
     * @return status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the internship.
     *
     * @param status new status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Returns the company name offering the internship.
     *
     * @return company name
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * Sets the company name offering the internship.
     *
     * @param companyName new company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Returns a copy of the list of company representatives associated with the internship.
     *
     * @return list of company representatives
     */
    public List<CompanyRepresentative> getCompanyRepresentatives() {
        return new ArrayList<>(this.companyRepresentatives); 
    }

    /**
     * Adds a company representative to the internship.
     *
     * @param repToAdd representative to add
     * @return 0 if successful, 1 if the representative is null or already exists
     */
    public int addCompanyRepresentative(CompanyRepresentative repToAdd) {
        if (repToAdd == null) {
        // FAILURE!!!
        return 1;
    }

    // Check for duplicates using the List's built-in method
    if (this.companyRepresentatives.contains(repToAdd)) {
        // FAILURE!!!
        return 1; 
    }
    this.companyRepresentatives.add(repToAdd);
    
    // success
    return 0;
    }
    }

    /**
     * Removes a company representative from the internship.
     *
     * @param repToDelete representative to remove
     * @return 0 if successful, 1 if representative is null or not found
     */
    // Check for duplicates using the List's built-in method
    public int deleteCompanyRepresentative(CompanyRepresentative repToDelete) { 
        if (repToDelete == null) {
            // FAILURE!!!
            return 1;
        }
        boolean removed = this.companyRepresentatives.remove(repToDelete);

        if (removed) {
            // success
            return 0;
        } else {
            // FAILURE!!! 
            return 1; 
        }
    }
    
    public int getNumberOfSlots() {
        return this.numberOfSlots;
    }

    public int setNumberOfSlots(int numberOfSlots) {
    if (numberOfSlots <= 0 || numberOfSlots > MAX_NUM_SLOTS) {

        // FAILURE!!!
        return 1; // Number of slots must be between 1 and 10.
    }
    this.numberOfSlots = numberOfSlots;
    
    // success
    return 0;
    }
}
