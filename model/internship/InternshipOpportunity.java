package model.internship;

import model.*;
import model.user.CompanyRepresentative;
import java.time.LocalDate;
import java.util.List;     
import java.util.ArrayList;

public class InternshipOpportunity {
    private String internshipTitle;
    private String description;
    private InternshipLevel internshipLevel;
    private ArrayList<String> preferredMajors;
    private LocalDate applicationOpeningDate;
    private LocalDate applicationClosingDate;
    private Status status; // Career Center Staff needs to approve the internshipOpportunity first before it can become visible
    private String companyName;
    private List<CompanyRepresentative> companyRepresentatives;    
    private int numberOfSlots;
    public boolean isVisible;
    public static final int MAX_NUM_SLOTS = 10;

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

    public boolean getVisibility() {
        return this.isVisible;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
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
