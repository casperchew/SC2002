package model.user;

import model.User;
import model.internship.InternshipApplication;
import java.util.ArrayList;

/**
 * Represents a student in the internship management system.
 * <p>
 * A student can apply for internships, track their applications, and
 * manage their personal academic information such as year of study and major.
 * </p>
 *
 * <p>
 * Students are limited to a maximum number of internship applications as defined
 * by {@link #MAX_APPLICATIONS}.
 * </p>
 *
 * @see model.User
 * @see model.internship.InternshipApplication
 */
public class Student extends User {

    /** Current year of study of the student. */
    private int YearOfStudy;

    /** Major of the student. */
    private String Major;

    /** List of internship applications submitted by the student. */
    private ArrayList<InternshipApplication> internshipApplications = new ArrayList<>();

    /** Maximum number of internship applications a student can submit. */
    public static final int MAX_APPLICATIONS = 3;

    /**
     * Constructs a new {@code Student} with the specified ID, name, password hash,
     * year of study, and major.
     *
     * @param userID unique identifier for the student
     * @param name the student's name
     * @param passwordHash hashed password for authentication
     * @param YearOfStudy the student's current year of study
     * @param Major the student's major
     */
    public Student(int userID, String name, String passwordHash, int YearOfStudy, String Major) {
        super(userID, name, passwordHash);
        this.YearOfStudy = YearOfStudy;
        this.Major = Major;
    }

    /**
     * Returns the current year of study of the student.
     *
     * @return the year of study
     */
    public int getYearOfStudy() {
        return YearOfStudy;
    }

    /**
     * Updates the year of study for the student.
     *
     * @param yearOfStudy the new year of study
     */
    public void setYearOfStudy(int yearOfStudy) {
        this.YearOfStudy = yearOfStudy;
    }

    /**
     * Returns the major of the student.
     *
     * @return the student's major
     */
    public String getMajor() {
        return Major;
    }

    /**
     * Updates the major of the student.
     *
     * @param major the new major
     */
    public void setMajor(String major) {
        this.Major = major;
    }

    /**
     * Returns a copy of the list of internship applications submitted by the student.
     *
     * @return a list of {@link InternshipApplication} objects
     */
    public ArrayList<InternshipApplication> getInternshipApplications() {
        return new ArrayList<>(internshipApplications);
    }

     /**
     * Adds a new internship application to the student's list of applications.
     * <p>
     * The student can only have up to {@link #MAX_APPLICATIONS} active applications.
     * If the limit is reached, the method will fail.
     * </p>
     *
     * @param internshipApplication the internship application to add
     * @return {@code 0} if the application was successfully added,
     *         {@code 1} if the student has reached the maximum number of applications
     */
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
    
    /**
     * Deletes a specific internship application from the student's list of applications.
     *
     * @param applicationToDelete the internship application to remove
     * @return {@code 0} if the application was successfully removed,
     *         {@code 1} if the application was null or not found in the list
     */
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
}

