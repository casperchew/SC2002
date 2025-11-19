package src.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import src.enums.InternshipLevel;
import src.model.User;
import src.model.internship.InternshipApplication;
import src.model.internship.InternshipOpportunity; 

public class Student extends User {
    public static final int MAX_APPLICATIONS = 3;

    private int yearOfStudy;
    private String major;
    private InternshipOpportunity internship;
    private ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();

    // Filters
    private ArrayList<InternshipLevel> internshipLevelFilter;
    private ArrayList<String> companyNameFilter;
    private LocalDate applicationOpeningDateFilter;
    private LocalDate applicationClosingDateFilter;

    public Student(String userID, String name, String passwordHash, int yearOfStudy, String major, InternshipOpportunity internship) {
        super(userID, name, passwordHash);

        this.yearOfStudy = yearOfStudy;
        this.major = major;
        this.internship = internship;

        internshipLevelFilter = new ArrayList<InternshipLevel>();
        companyNameFilter = new ArrayList<String>();
        applicationOpeningDateFilter = LocalDate.MIN; 
        applicationClosingDateFilter = LocalDate.MAX;
    }

	// Getters and Setters
	// -------------------
	// yearOfStudy
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

	// major
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

	// internship
    public InternshipOpportunity getInternship() {
        return internship;
    }

    public void setInternship(InternshipOpportunity internship) {
        this.internship = internship;
    }

	// internshipApplications
    public ArrayList<InternshipApplication> getInternshipApplications() {
        return internshipApplications;
    }

	/** 
	 * @return 0 if success, 1 if error
	 */
    public int addInternshipApplications(InternshipApplication internshipApplication) {
        if (internshipApplications.size() < MAX_APPLICATIONS) {
            internshipApplications.add(internshipApplication);
            return 0; 
        } else {
            return 1; 
        }
    }

	/**
	 * @return 0 if success, 1 if error
	 */
    public int deleteInternshipApplication(InternshipApplication application) {
        if (application == null) {
            return 1; 
        }

        boolean removed = this.internshipApplications.remove(application);
		return removed ? 0 : 1;
    }

	// InternshipLevelFilter
    public ArrayList<InternshipLevel> getInternshipLevelFilter() {
        return internshipLevelFilter;
    }

    public void addInternshipLevelFilter(InternshipLevel internshipLevel) {
        internshipLevelFilter.add(internshipLevel);
    }

    public void removeInternshipLevelFilter(InternshipLevel internshipLevel) {
        internshipLevelFilter.remove(internshipLevel);
    }

    public InternshipLevel removeInternshipLevelFilter(int i) {
        if (i == -1) {
            internshipLevelFilter.clear();
            return null;
        }

        return internshipLevelFilter.remove(i);
    }   

	// CompanyNameFilter
    public ArrayList<String> getCompanyNameFilter() {
        return companyNameFilter;
    }

    public void addCompanyNameFilter(String companyName) {
        companyNameFilter.add(companyName);
    }

    public void removeCompanyNameFilter(String companyName) {
        companyNameFilter.remove(companyName);
    }

    public String removeCompanyNameFilter(int i) {
        if (i == -1) {
            companyNameFilter.clear();
            return null;
        }

        return companyNameFilter.remove(i);
    }   

	// OpeningDateFilter
    public LocalDate getApplicationOpeningDateFilter() {
        return applicationOpeningDateFilter;
    }

    public void setApplicationOpeningDateFilter(LocalDate applicationOpeningDateFilter) {
        this.applicationOpeningDateFilter = applicationOpeningDateFilter;
    }

	// ClosingDateFilter
    public LocalDate getApplicationClosingDateFilter() {
        return applicationClosingDateFilter;
    }

    public void setApplicationClosingDateFilter(LocalDate applicationClosingDateFilter) {
        this.applicationClosingDateFilter = applicationClosingDateFilter;
    }
	// -------------------
}
