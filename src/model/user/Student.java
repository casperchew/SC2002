package src.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import src.model.InternshipLevel;
import src.model.User;
import src.model.internship.InternshipApplication;
import src.model.internship.InternshipOpportunity; 

public class Student extends User {
    private int YearOfStudy;
    private String Major;
    InternshipOpportunity internship;
    private ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();
    public static final int MAX_APPLICATIONS = 3;

    // filters (can add more filters as necessary)
    ArrayList<InternshipLevel> internshipLevelFilter;
    ArrayList<String> companyNameFilter;
    private LocalDate applicationOpeningDateFilter;
    private LocalDate applicationClosingDateFilter;

    public Student(int userID, String name, String passwordHash, int YearOfStudy, String Major, InternshipOpportunity internship) {
        super(userID, name, passwordHash);

        this.YearOfStudy = YearOfStudy;
        this.Major = Major;
        this.internship = internship;

        this.internshipLevelFilter = new ArrayList<InternshipLevel>();
        this.companyNameFilter = new ArrayList<String>();
        // At initialization, we set these such that all dates fall within the range of the date filters
        this.applicationOpeningDateFilter = LocalDate.MIN; 
        this.applicationClosingDateFilter = LocalDate.MAX;
    }

    public int getYearOfStudy() {
        return YearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        YearOfStudy = yearOfStudy;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public InternshipOpportunity getInternship() {
        return this.internship;
    }

    public void setInternship(InternshipOpportunity internship) {
        this.internship = internship;
    }

    public ArrayList<InternshipApplication> getInternshipApplications() {
        return new ArrayList<>(internshipApplications);
    }

    public int addInternshipApplications(InternshipApplication internshipApplication) {
        if (this.internshipApplications.size() < MAX_APPLICATIONS) {
            this.internshipApplications.add(internshipApplication);
            
            // success
            return 0; 

        } else {

            // FAILURE!!!
            return 1; 
        }
    }

    public int deleteInternshipApplication(InternshipApplication applicationToDelete) {
        if (applicationToDelete == null) {
            // FAIL if the application to delete is null
            return 1; 
        }
        boolean removed = this.internshipApplications.remove(applicationToDelete);

        if (removed) {
            // success
            return 0; 
        } else {
            // FAILURE! 
            return 1; 
        }
    }

    public void AddInternshipLevelFilter(InternshipLevel internshipLevel) {
        this.internshipLevelFilter.add(internshipLevel);
    }
    public void RemoveInternshipLevelFilter(InternshipLevel internshipLevel) {
        this.internshipLevelFilter.remove(internshipLevel);
    }
    // overload
    public InternshipLevel RemoveInternshipLevelFilter(int i) {
        if (i == -1) {
            this.internshipLevelFilter.clear();
            return null;
        }
        return this.internshipLevelFilter.remove(i);
    }   
    public ArrayList<InternshipLevel> getInternshipLevelFilter() {
        return internshipLevelFilter;
    }

    public void AddCompanyNameFilter(String companyName) {
        this.companyNameFilter.add(companyName);
    }
    public void RemoveCompanyNameFilter(String companyName) {
        this.companyNameFilter.remove(companyName);
    }
    // overload
    public String RemoveCompanyNameFilter(int i) {
        if (i == -1) {
            this.companyNameFilter.clear();
            return null;
        }
        return this.companyNameFilter.remove(i);
    }   
    public ArrayList<String> getCompanyNameFilter() {
        return companyNameFilter;
    }

    public void setApplicationOpeningDateFilter(LocalDate applicationOpeningDateFilter) {
        this.applicationOpeningDateFilter = applicationOpeningDateFilter;
    }
    public LocalDate getApplicationOpeningDateFilter() {
        return applicationOpeningDateFilter;
    }

    public void setApplicationClosingDateFilter(LocalDate applicationClosingDateFilter) {
        this.applicationClosingDateFilter = applicationClosingDateFilter;
    }
    public LocalDate getApplicationClosingDateFilter() {
        return applicationClosingDateFilter;
    }
}

