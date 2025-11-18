package src.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import src.enums.InternshipLevel;
import src.model.User;
import src.model.internship.InternshipApplication;
import src.model.internship.InternshipOpportunity; 

public class Student extends User {
    public static final int MAX_APPLICATIONS = 3;

    private int YearOfStudy;
    private String Major;
    InternshipOpportunity internship;
    private ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();

    // Filters
    ArrayList<InternshipLevel> internshipLevelFilter;
    ArrayList<String> companyNameFilter;
    private LocalDate applicationOpeningDateFilter;
    private LocalDate applicationClosingDateFilter;

    public Student(String userID, String name, String passwordHash, int YearOfStudy, String Major, InternshipOpportunity internship) {
        super(userID, name, passwordHash);

        this.YearOfStudy = YearOfStudy;
        this.Major = Major;
        this.internship = internship;

        this.internshipLevelFilter = new ArrayList<InternshipLevel>();
        this.companyNameFilter = new ArrayList<String>();
        this.applicationOpeningDateFilter = LocalDate.MIN; 
        this.applicationClosingDateFilter = LocalDate.MAX;
    }

	// Getters and Setters
	// -------------------
	// YearOfStudy
    public int getYearOfStudy() {
        return YearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        YearOfStudy = yearOfStudy;
    }

	// Major
    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

	// Internship
    public InternshipOpportunity getInternship() {
        return this.internship;
    }

    public void setInternship(InternshipOpportunity internship) {
        this.internship = internship;
    }

	// InternshipApplications
    public ArrayList<InternshipApplication> getInternshipApplications() {
        return new ArrayList<>(internshipApplications);
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

    public void AddInternshipLevelFilter(InternshipLevel internshipLevel) {
        this.internshipLevelFilter.add(internshipLevel);
    }

    public void RemoveInternshipLevelFilter(InternshipLevel internshipLevel) {
        this.internshipLevelFilter.remove(internshipLevel);
    }

    public InternshipLevel RemoveInternshipLevelFilter(int i) {
        if (i == -1) {
            this.internshipLevelFilter.clear();
            return null;
        }
        return this.internshipLevelFilter.remove(i);
    }   

	// CompanyNameFilter
    public ArrayList<String> getCompanyNameFilter() {
        return companyNameFilter;
    }

    public void AddCompanyNameFilter(String companyName) {
        this.companyNameFilter.add(companyName);
    }

    public void RemoveCompanyNameFilter(String companyName) {
        this.companyNameFilter.remove(companyName);
    }

    public String RemoveCompanyNameFilter(int i) {
        if (i == -1) {
            this.companyNameFilter.clear();
            return null;
        }

        return this.companyNameFilter.remove(i);
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
