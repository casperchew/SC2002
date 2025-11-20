package src.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import src.enums.Status;
import src.enums.InternshipLevel;
import src.model.User;

/**
 * CareerCenterStaff Class
 */
public class CareerCenterStaff extends User {
    private ArrayList<InternshipLevel> internshipLevelFilter;
    private ArrayList<String> preferredMajorsFilter; 
    private LocalDate applicationOpeningDateFilter;
    private LocalDate applicationClosingDateFilter;
	private ArrayList<Status> statusFilter;
    private ArrayList<String> companyNameFilter;
	private ArrayList<CompanyRepresentative> companyRepresentativeFilter;
	
	/**
	 * Constructs a {@code CareerCenterStaff} with the required attributes
	 *
	 * @param userID the NTU account of the career center staff
	 * @param name the {@code name} of the career center staff
	 * @param passwordHash the {@code passwordHash} of the career center staff
	 */
	public CareerCenterStaff(String userID, String name, String passwordHash) {
		super(userID, name, passwordHash);

		// TODO: move default attributes outside constructor
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
	/**
	 * Getter for {@code internshipLevelFilter}
	 *
	 * @return {@code internshipLevelFilter}
	 */
    public ArrayList<InternshipLevel> getInternshipLevelFilter() {
        return internshipLevelFilter;
    }

	/**
	 * Adds {@code internshipLevel} to {@code internshipLevelFilter}
	 *
	 * @param internshipLevel the {@code internshipLevel} to add
	 */
	public void addInternshipLevelFilter(InternshipLevel internshipLevel) {
        internshipLevelFilter.add(internshipLevel);
    }

	/**
	 * Removes {@code internshipLevel} from {@code internshipLevelFilter}
	 *
	 * @param internshipLevel the {@code internshipLevel} to remove
	 */
    public void removeInternshipLevelFilter(InternshipLevel internshipLevel) {
        internshipLevelFilter.remove(internshipLevel);
    }

	/**
	 * Removes {@code internshipLevel} from {@code internshipLevelFilter}
	 *
	 * @param i the index of the {@code internshipLevel} to remove
	 * @return TODO
	 */
    public InternshipLevel removeInternshipLevelFilter(int i) {
        if (i == -1) {
            internshipLevelFilter.clear();
            return null;
        }

        return internshipLevelFilter.remove(i);
    }   

	// preferredMajorFilter
	/**
	 * Getter for {@code preferredMajorsFilter}
	 *
	 * @return {@code preferredMajorsFilter}
	 */
    public ArrayList<String> getPreferredMajorsFilter() {
        return preferredMajorsFilter;
    }

	/**
	 * Adds {@code major} to {@code preferredMajorsFilter}
	 *
	 * @param major the {@code major} to add
	 */
	public void addPreferredMajorFilter(String major) {
        preferredMajorsFilter.add(major);
    }

	/**
	 * Adds {@code major} to {@code preferredMajorsFilter}
	 *
	 * @param major the {@code major} to remove
	 */
    public void removePreferredMajorFilter(String major) {
        preferredMajorsFilter.remove(major);
    }

	/**
	 * Removes the preferred major at the specified position in the preferredMajorFilter list. Shifts any subsequent elements to the left (subtracts one from their indices).
	 * <br><br>
	 * Overloads the {@code removePreferredMajor} function.
	 *
	 * @param i the index of the {@code major} to remove. If {@code i} is -1, the {@code preferredMajorFilter} list is cleared
	 * @return TODO
	 */
    public String removePreferredMajorFilter(int i) {
        if (i == -1) {
            preferredMajorsFilter.clear();
            return null;
        }

        return preferredMajorsFilter.remove(i);
    }

	// applicationOpeningDateFilter
	/**
	 * Getter for {@code applicationOpeningDateFilter}
	 *
	 * @return {@code applicationOpeningDateFilter}
	 */
    public LocalDate getApplicationOpeningDateFilter() {
        return applicationOpeningDateFilter;
    }

	/**
	 * Setter for {@code applicationOpeningDateFilter}
	 *
	 * @param applicationOpeningDateFilter the {@code applicationOpeningDateFilter} to set
	 */
    public void setApplicationOpeningDateFilter(LocalDate applicationOpeningDateFilter) {
        this.applicationOpeningDateFilter = applicationOpeningDateFilter;
    }

	// applicationClosingDateFilter
	/**
	 * Getter for {@code applicationClosingDateFilter}
	 *
	 * @return {@code applicationClosingDateFilter}
	 */
    public LocalDate getApplicationClosingDateFilter() {
        return applicationClosingDateFilter;
    }

	/**
	 * Setter for {@code applicationClosingDateFilter}
	 *
	 * @param applicationClosingDateFilter the {@code applicationClosingDateFilter} to set
	 */
    public void setApplicationClosingDateFilter(LocalDate applicationClosingDateFilter) {
        this.applicationClosingDateFilter = applicationClosingDateFilter;
    }

	// statusFilter
	/**
	 * Getter for {@code statusFilter}
	 *
	 * @return {@code statusFilter}
	 */
    public ArrayList<Status> getStatusFilter() {
        return statusFilter;
    }

	/**
	 * Adds {@code status} to {@code statusFilter}
	 *
	 * @param status the {@code status} to add
	 */
    public void addStatusFilter(Status status) {
        this.statusFilter.add(status);
    }

	/**
	 * Removes {@code status} from {@code statusFilter}
	 *
	 * @param status the {@code status} to remove
	 */
    public void removeStatusFilter(Status status) {
        this.statusFilter.remove(status);
    }

	/**
	 * Removes {@code status} from {@code statusFilter}
	 *
	 * @param i the index of the {@code status} to remove
	 * @return TODO
	 */
    public Status removeStatusFilter(int i) {
        if (i == -1) {
            this.statusFilter.clear();
            return null;
        }

        return statusFilter.remove(i);
    }

	// companyNameFilter
	/**
	 * Getter for {@code companyNameFilter}
	 *
	 * @return {@code companyNameFilter}
	 */
    public ArrayList<String> getCompanyNameFilter() {
        return companyNameFilter;
    }

	/**
	 * Adds {@code companyName} to {@code companyNameFilter}
	 *
	 * @param companyName the {@code companyName} to add
	 */
    public void addCompanyNameFilter(String companyName) {
        companyNameFilter.add(companyName);
    }

	/**
	 * Removes {@code companyName} from {@code companyNameFilter}
	 *
	 * @param companyName the {@code companyName} to remove
	 */
    public void removeCompanyNameFilter(String companyName) {
        companyNameFilter.remove(companyName);
    }

	/**
	 * Removes {@code companyName} from {@code companyNameFilter}
	 *
	 * @param i the index of the {@code companyName} to remove
	 * @return TODO
	 */
    public String removeCompanyNameFilter(int i) {
        if (i == -1) {
            companyNameFilter.clear();
            return null;
        }

        return companyNameFilter.remove(i);
    }   

	// companyRepresentativeFilter
	/**
	 * Getter for companyRepresentativeFilter
	 *
	 * @return {@code companyRepresentativeFilter}
	 */
    public ArrayList<CompanyRepresentative> getCompanyRepresentativeFilter() {
        return companyRepresentativeFilter;
    }

	/**
	 * Adds {@code rep} to {@code companyRepresentativeFilter}
	 *
	 * @param rep the {@code rep} to add
	 */
    public void addCompanyRepresentativeFilter(CompanyRepresentative rep) {
        companyRepresentativeFilter.add(rep);
    }

	/**
	 * Removes {@code rep} from {@code companyRepresentativeFilter}
	 *
	 * @param rep the {@code rep} to remove
	 */
    public void removeCompanyRepresentativeFilter(CompanyRepresentative rep) {
        companyRepresentativeFilter.remove(rep);
    }

	/**
	 * Removes {@code rep} from {@code companyRepresentativeFilter}
	 *
	 * @param i the index of the {@code rep} to remove
	 * @return TODO
	 */
    public CompanyRepresentative removeCompanyRepresentativeFilter(int i) {
        if (i == -1) {
            companyRepresentativeFilter.clear();
            return null;
        }

        return companyRepresentativeFilter.remove(i);
    }
}
