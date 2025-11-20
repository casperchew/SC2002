package src.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import src.enums.InternshipLevel;
import src.model.User;
import src.model.internship.InternshipApplication;
import src.model.internship.InternshipOpportunity; 

/**
 * Student class
 */
public class Student extends User {
	/** The maximum number of internship opportunities a {@code Student} can apply for at once. */
    public static final int MAX_APPLICATIONS = 3;

    private int yearOfStudy;
    private String major;
    private InternshipOpportunity internship;
    private ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();

    private ArrayList<InternshipLevel> internshipLevelFilter = new ArrayList<InternshipLevel>();
    private ArrayList<String> companyNameFilter = new ArrayList<String>();
    private LocalDate applicationOpeningDateFilter = LocalDate.MIN;
    private LocalDate applicationClosingDateFilter = LocalDate.MAX;

	/**
	 * Constructs a Student constructor with required attributes
	 *
	 * @param userID the matriculation number of the Student
	 * @param name the {@code name} of the {@code Student}
	 * @param passwordHash the {@code passwordHash} of the {@code Student}
	 * @param yearOfStudy the {@code yearOfStudy} of the {@code Student}
	 * @param major the {@code major} of the {@code Student}
	 * @param internship the {@code internship} of the {@code Student}
	 */
    public Student(String userID, String name, String passwordHash, int yearOfStudy, String major, InternshipOpportunity internship) {
        super(userID, name, passwordHash);

        this.yearOfStudy = yearOfStudy;
        this.major = major;
        this.internship = internship;
    }

	// Getters and Setters
	// -------------------
	// yearOfStudy
	/**
	 * Getter for {@code yearOfStudy}
	 *
	 * @return yearOfStudy
	 */
    public int getYearOfStudy() {
        return yearOfStudy;
    }

	/**
	 * Setter for {@code yearOfStudy}
	 *
	 * @param yearOfStudy the {@code yearOfStudy} to set
	 */
    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

	// major
	/**
	 * Getter for {@code major}
	 *
	 * @return major
	 */
    public String getMajor() {
        return major;
    }

	/**
	 * Setter for {@code major}
	 *
	 * @param major the {@code major} to set
	 */
    public void setMajor(String major) {
        this.major = major;
    }

	// internship
	/**
	 * Getter for {@code internship}
	 *
	 * @return internship
	 */
    public InternshipOpportunity getInternship() {
        return internship;
    }

	/**
	 * Setter for {@code internship}
	 *
	 * @param internship the {@code internship} to set
	 */
    public void setInternship(InternshipOpportunity internship) {
        this.internship = internship;
    }

	// internshipApplications
	/**
	 * Getter for {@code internshipApplications}
	 *
	 * @return internshipApplications
	 */
    public ArrayList<InternshipApplication> getInternshipApplications() {
        return internshipApplications;
    }

	/** 
	 * Adds {@code internshipApplication} to {@code Student}'s {@code internshipApplications}
	 *
	 * @param internshipApplication the {@code internshipApplication} to delete
	 * @return 0 if success, 1 if error
	 */
    public int addInternshipApplication(InternshipApplication internshipApplication) {
        if (internshipApplications.size() < MAX_APPLICATIONS) {
            internshipApplications.add(internshipApplication);
            return 0; 
        } else {
            return 1; 
        }
    }

	/**
	 * Deletes {@code internshipApplication} from {@code Student}'s {@code internshipApplications}
	 *
	 * @param internshipApplication the {@code internshipApplication} to delete
	 * @return 0 if success, 1 if error
	 */
    public int deleteInternshipApplication(InternshipApplication internshipApplication) {
        if (internshipApplication == null) {
            return 1; 
        }

        boolean removed = this.internshipApplications.remove(internshipApplication);
		return removed ? 0 : 1;
    }

	// InternshipLevelFilter
	/**
	 * Getter for {@code internshipLevelFilter}
	 *
	 * @return internshipLevelFilter
	 */
    public ArrayList<InternshipLevel> getInternshipLevelFilter() {
        return internshipLevelFilter;
    }

	/**
	 * Adds {@code internshipLevel} to {@code Student}'s {@code internshipLevelFilter}
	 *
	 * @param internshipLevel the {@code internshipLevel} to add
	 */
    public void addInternshipLevelFilter(InternshipLevel internshipLevel) {
        internshipLevelFilter.add(internshipLevel);
    }

	/**
	 * Removes {@code internshipLevel} from {@code Student}'s {@code internshipLevelFilter}
	 *
	 * @param internshipLevel the {@code internshipLevel} to remove
	 */
    public void removeInternshipLevelFilter(InternshipLevel internshipLevel) {
        internshipLevelFilter.remove(internshipLevel);
    }

	/**
	 * Removes {@code internshipLevel} from {@code Student}'s {@code internshipLevelFilter}.
	 *
	 * @param i the index of the {@code internshipLevel} to remove.
	 * @return The {@link src.enums.InternshipLevel} that was from removed from the list, or {@code null} if the filter was cleared.
	 */
    public InternshipLevel removeInternshipLevelFilter(int i) {
        if (i == -1) {
            internshipLevelFilter.clear();
            return null;
        }

        return internshipLevelFilter.remove(i);
    }   

	// CompanyNameFilter
	/**
	 * Getter for companyNameFilter
	 *
	 * @return companyNameFilter
	 */
    public ArrayList<String> getCompanyNameFilter() {
        return companyNameFilter;
    }

	/**
	 * Adds {@code companyName} to {@code Student}'s {@code companyNameFilter}
	 *
	 * @param companyName the {@code companyName} to add
	 */
    public void addCompanyNameFilter(String companyName) {
        companyNameFilter.add(companyName);
    }

	/**
	 * Removes {@code companyName} from {@code Student}'s {@code companyNameFilter}
	 *
	 * @param companyName the {@code companyName} to remove
	 */
    public void removeCompanyNameFilter(String companyName) {
        companyNameFilter.remove(companyName);
    }

	/**
	 * Removes {@code companyName} from {@code Student}'s {@code companyNameFilter}
	 *
	 * @param i the index of the {@code companyName} to remove
	 * @return The {@code companyName} that was removed from the list, or {@code null} if the list was cleared.
	 */
    public String removeCompanyNameFilter(int i) {
        if (i == -1) {
            companyNameFilter.clear();
            return null;
        }

        return companyNameFilter.remove(i);
    }   

	// OpeningDateFilter
	/**
	 * Getter for {@code applicationOpeningDateFilter}
	 *
	 * @return applicationOpeningDateFilter
	 */
    public LocalDate getApplicationOpeningDateFilter() {
        return applicationOpeningDateFilter;
    }

	/**
	 * Setter for {@code applicationOpeningDateFilter}
	 *
	 * @param applicationOpeningDateFilter the {@code applicationOpeningDateFitlter} to set
	 */
    public void setApplicationOpeningDateFilter(LocalDate applicationOpeningDateFilter) {
        this.applicationOpeningDateFilter = applicationOpeningDateFilter;
    }

	// ClosingDateFilter
	/**
	 * Getter for {@code applicationClosingDateFilter}
	 *
	 * @return applicationClosingDate
	 */
    public LocalDate getApplicationClosingDateFilter() {
        return applicationClosingDateFilter;
    }

	/**
	 * Getter for {@code applicationClosingDateFilter}
	 *
	 * @param applicationClosingDateFilter the {@code applicationClosingDateFitlter} to set
	 */
    public void setApplicationClosingDateFilter(LocalDate applicationClosingDateFilter) {
        this.applicationClosingDateFilter = applicationClosingDateFilter;
    }
	// -------------------
}
