package src.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import src.enums.Status;
import src.enums.InternshipLevel;
import src.model.User;

public class CareerCenterStaff extends User {

	// filters (can add more filters as necessary)
    ArrayList<InternshipLevel> internshipLevelFilter;
    ArrayList<String> companyNameFilter;
    private ArrayList<String> preferredMajorsFilter; 
    private LocalDate applicationOpeningDateFilter;
    private LocalDate applicationClosingDateFilter;
	private ArrayList<Status> statusFilter;
	private ArrayList<CompanyRepresentative> companyRepresentativeFilter;

	
	public CareerCenterStaff(String userID, String name, String passwordHash) {
		super(userID, name, passwordHash);

        this.internshipLevelFilter = new ArrayList<InternshipLevel>();
        this.companyNameFilter = new ArrayList<String>();
		this.preferredMajorsFilter = new ArrayList<String>();
		this.statusFilter = new ArrayList<Status>();
		this.companyRepresentativeFilter = new ArrayList<>();
        // At initialization, we set these such that all dates fall within the range of the date filters
        this.applicationOpeningDateFilter = LocalDate.MIN; 
        this.applicationClosingDateFilter = LocalDate.MAX;
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

	public void AddPreferredMajorFilter(String major) {
        this.preferredMajorsFilter.add(major);
    }
    public void RemovePreferredMajorFilter(String major) {
        this.preferredMajorsFilter.remove(major);
    }
    // overload
    public String RemovePreferredMajorFilter(int i) {
        if (i == -1) {
            this.preferredMajorsFilter.clear();
            return null;
        }
        return this.preferredMajorsFilter.remove(i);
    }
    public ArrayList<String> getPreferredMajorsFilter() {
        return preferredMajorsFilter;
    }

    public void AddStatusFilter(Status status) {
        this.statusFilter.add(status);
    }
    public void RemoveStatusFilter(Status status) {
        this.statusFilter.remove(status);
    }
    // overload
    public Status RemoveStatusFilter(int i) {
        if (i == -1) {
            this.statusFilter.clear();
            return null;
        }
        return this.statusFilter.remove(i);
    }
    public ArrayList<Status> getStatusFilter() {
        return statusFilter;
    }


    public void AddCompanyRepresentativeFilter(CompanyRepresentative rep) {
        this.companyRepresentativeFilter.add(rep);
    }
    public void RemoveCompanyRepresentativeFilter(CompanyRepresentative rep) {
        this.companyRepresentativeFilter.remove(rep);
    }
    // overload
    public CompanyRepresentative RemoveCompanyRepresentativeFilter(int i) {
        if (i == -1) {
            this.companyRepresentativeFilter.clear();
            return null;
        }
        return this.companyRepresentativeFilter.remove(i);
    }

    public ArrayList<CompanyRepresentative> getCompanyRepresentativeFilter() {
        return companyRepresentativeFilter;
    }

}

