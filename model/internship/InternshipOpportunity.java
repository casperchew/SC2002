package model.internship;

import java.util.ArrayList;
import java.util.List;     
import java.time.LocalDate;

import model.*;
import model.user.CompanyRepresentative;

public class InternshipOpportunity {
    private String internshipTitle;
    private String description;
    private InternshipLevel internshipLevel;
    private ArrayList<String> preferredMajors;  // TODO change to string (only 1 preferred major is required)
    private LocalDate applicationOpeningDate;
    private LocalDate applicationClosingDate;
    private Status status;  // Career Center Staff needs to approve the internshipOpportunity first before it can become visible
    private String companyName;
    private List<CompanyRepresentative> companyRepresentatives;
    private int numberOfSlots;
    public static final int MAX_NUM_SLOTS = 10;

    public boolean isVisible = false;

    public InternshipOpportunity(
            String internshipTitle,
            String description,
            InternshipLevel internshipLevel,
            ArrayList<String> preferredMajors,
            LocalDate applicationOpeningDate,
            LocalDate applicationClosingDate,
            String companyName,
            List<CompanyRepresentative> companyRepresentatives,
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
        this.preferredMajors = preferredMajors;
        this.applicationOpeningDate = applicationOpeningDate;
        this.applicationClosingDate = applicationClosingDate;
        this.companyName = companyName;
        this.numberOfSlots = numberOfSlots;

        if (companyRepresentatives != null) {
            this.companyRepresentatives = new ArrayList<>(companyRepresentatives);
        } else {
            this.companyRepresentatives = new ArrayList<>();
        }

        // When first created, the status should be PENDING 
        this.status = Status.PENDING; 
    }

    public String getInternshipTitle() {
        return this.internshipTitle;
    }

    public void setInternshipTitle(String internshipTitle) {
        this.internshipTitle = internshipTitle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InternshipLevel getInternshipLevel() {
        return this.internshipLevel;
    }

    public void setInternshipLevel(InternshipLevel internshipLevel) {
        this.internshipLevel = internshipLevel;
    }

    public ArrayList<String> getPreferredMajors() {
        return this.preferredMajors;
    }

    public void setPreferredMajors(ArrayList<String> preferredMajors) {
        this.preferredMajors = preferredMajors;
    }

    public LocalDate getApplicationOpeningDate() {
        return this.applicationOpeningDate;
    }

    public void setApplicationOpeningDate(LocalDate applicationOpeningDate) {
        this.applicationOpeningDate = applicationOpeningDate;
    }

    public LocalDate getApplicationClosingDate() {
        return this.applicationClosingDate;
    }

    public void setApplicationClosingDate(LocalDate applicationClosingDate) {
        this.applicationClosingDate = applicationClosingDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<CompanyRepresentative> getCompanyRepresentatives() { 
        return new ArrayList<>(this.companyRepresentatives); 
    }

    /**
     * @return 0 if successful, 1 if unsuccessful
     */
    public int addCompanyRepresentative(CompanyRepresentative repToAdd) {
        if (repToAdd == null) {
            return 1;
        }

        if (this.companyRepresentatives.contains(repToAdd)) {
            return 1; 
        }

        this.companyRepresentatives.add(repToAdd);
        return 0;
    }
    
    /**
     * @return 0 if successful, 1 if unsuccessful
     */
    public int deleteCompanyRepresentative(CompanyRepresentative repToDelete) {
        if (repToDelete == null) {
            return 1;
        }

        boolean removed = this.companyRepresentatives.remove(repToDelete);
        return removed ? 0 : 1;
    }

    public int getNumberOfSlots() {
        return this.numberOfSlots;
    }

    /**
     * @return 0 if successful, 1 if unsuccessful
     */
    public int setNumberOfSlots(int numberOfSlots) {
        if (numberOfSlots <= 0 || numberOfSlots > MAX_NUM_SLOTS) {
            return 1;
        }

        this.numberOfSlots = numberOfSlots;
        return 0;
    }

    public boolean getVisibility() {
        return this.isVisible;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

}
