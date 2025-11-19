package src.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import src.enums.Status;
import src.enums.InternshipLevel;
import src.model.User;

public class CareerCenterStaff extends User {
    private ArrayList<InternshipLevel> internshipLevelFilter;
    private ArrayList<String> preferredMajorsFilter; 
    private LocalDate applicationOpeningDateFilter;
    private LocalDate applicationClosingDateFilter;
	private ArrayList<Status> statusFilter;
    private ArrayList<String> companyNameFilter;
	private ArrayList<CompanyRepresentative> companyRepresentativeFilter;
	
	public CareerCenterStaff(String userID, String name, String passwordHash) {
		super(userID, name, passwordHash);

        this.internshipLevelFilter = new ArrayList<InternshipLevel>();
		this.preferredMajorsFilter = new ArrayList<String>();
        this.applicationOpeningDateFilter = LocalDate.MIN; 
        this.applicationClosingDateFilter = LocalDate.MAX;
		this.statusFilter = new ArrayList<Status>();
        this.companyNameFilter = new ArrayList<String>();
		this.companyRepresentativeFilter = new ArrayList<>();
	}

	// Getters and Setters
	// -------------------
	// internshipLevelFilter
    public ArrayList<InternshipLevel> getInternshipLevelFilter() {
        return internshipLevelFilter;
    }

	public void addInternshipLevelFilter(InternshipLevel internshipLevel) {
        internshipLevelFilter.add(internshipLevel);
    }

    public void removeInternshipLevelFilter(InternshipLevel internshipLevel) {
        internshipLevelFilter.remove(internshipLevel);
    }

    // overload
    public InternshipLevel removeInternshipLevelFilter(int i) {
        if (i == -1) {
            internshipLevelFilter.clear();
            return null;
        }

        return this.internshipLevelFilter.remove(i);
    }   

	// preferredMajorFilter
    public ArrayList<String> getPreferredMajorsFilter() {
        return preferredMajorsFilter;
    }

	public void addPreferredMajorFilter(String major) {
        preferredMajorsFilter.add(major);
    }

    public void removePreferredMajorFilter(String major) {
        preferredMajorsFilter.remove(major);
    }

	/**
	 * Removes the preffered major at the specified position in the preferredMajorFilter list. Shifts any subsequent elements to the left (subtracts one from their indices). If {@code i} is -1, then clears the {@code preferredMajorFilter} list
	 * <br><br>
	 * Overloads the {@code removePreferredMajor} function.
	 */
    public String removePreferredMajorFilter(int i) {
        if (i == -1) {
            preferredMajorsFilter.clear();
            return null;
        }

        return preferredMajorsFilter.remove(i);
    }

	// applicationOpeningDateFilter
    public LocalDate getApplicationOpeningDateFilter() {
        return applicationOpeningDateFilter;
    }

    public void setApplicationOpeningDateFilter(LocalDate applicationOpeningDateFilter) {
        this.applicationOpeningDateFilter = applicationOpeningDateFilter;
    }

	// applicationClosingDateFilter
    public LocalDate getApplicationClosingDateFilter() {
        return applicationClosingDateFilter;
    }

    public void setApplicationClosingDateFilter(LocalDate applicationClosingDateFilter) {
        this.applicationClosingDateFilter = applicationClosingDateFilter;
    }

	// statusFilter
    public ArrayList<Status> getStatusFilter() {
        return statusFilter;
    }

    public void addStatusFilter(Status status) {
        this.statusFilter.add(status);
    }

    public void removeStatusFilter(Status status) {
        this.statusFilter.remove(status);
    }

    // overload
    public Status removeStatusFilter(int i) {
        if (i == -1) {
            this.statusFilter.clear();
            return null;
        }

        return statusFilter.remove(i);
    }

	// companyNameFilter
    public ArrayList<String> getCompanyNameFilter() {
        return companyNameFilter;
    }

    public void addCompanyNameFilter(String companyName) {
        this.companyNameFilter.add(companyName);
    }

    public void removeCompanyNameFilter(String companyName) {
        this.companyNameFilter.remove(companyName);
    }

    // overload
    public String removeCompanyNameFilter(int i) {
        if (i == -1) {
            this.companyNameFilter.clear();
            return null;
        }
        return companyNameFilter.remove(i);
    }   

	// companyRepresentativeFilter
    public ArrayList<CompanyRepresentative> getCompanyRepresentativeFilter() {
        return companyRepresentativeFilter;
    }

    public void addCompanyRepresentativeFilter(CompanyRepresentative rep) {
        this.companyRepresentativeFilter.add(rep);
    }

    public void removeCompanyRepresentativeFilter(CompanyRepresentative rep) {
        this.companyRepresentativeFilter.remove(rep);
    }

    // overload
    public CompanyRepresentative removeCompanyRepresentativeFilter(int i) {
        if (i == -1) {
            this.companyRepresentativeFilter.clear();
            return null;
        }
        return this.companyRepresentativeFilter.remove(i);
    }
}

