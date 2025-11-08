package controller;

import java.util.ArrayList;
import model.user.*;
import model.internship.*;
import test.*;

/**
 * The {@code Database} class functions as an in-memory data storage component for the system.
 * <p>
 * It holds collections of all key entities — including {@link Student}, 
 * {@link CompanyRepresentative}, {@link CareerCenterStaff}, {@link InternshipOpportunity},
 * and {@link InternshipApplication}. 
 * </p>
 *
 * <p>
 * This class provides basic CRUD (Create, Read, Update, Delete) operations for each type
 * of entity. It is designed primarily for testing and prototyping purposes, as data is 
 * stored only in memory and will not persist between program executions.
 * </p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 *     Database db = new Database();
 *     Student s = new Student(1, "Alice", "password", 2, "Computer Science");
 *     db.createStudent(s);
 *     ArrayList&lt;Student&gt; students = db.getStudents();
 * </pre>
 *
 * @author  
 * @version 1.0
 */
public class Database {

    /** List of all registered students. */
    private ArrayList<Student> students = new ArrayList<>();

    /** List of all company representatives. */
    private ArrayList<CompanyRepresentative> representatives = new ArrayList<>();

    /** List of all career center staff members. */
    private ArrayList<CareerCenterStaff> staffs = new ArrayList<>();

    /** List of all internship opportunities available. */
    private ArrayList<InternshipOpportunity> internshipOpportunities = new ArrayList<>();

    /** List of all internship applications submitted. */
    private ArrayList<InternshipApplication> internshipApplications = new ArrayList<>();

    /**
     * Constructs a new {@code Database} instance and initializes it with 
     * sample internship opportunities for testing purposes.
     */
    public Database() {
        // For testing
        internshipOpportunities = SampleInternships.getSampleList();
    }

    /**
     * Adds a new student record to the database.
     *
     * @param student the {@link Student} object to be added
     */
    //create
    public void createStudent(Student student) {
        students.add(student);
    }

    /**
     * Adds a new company representative record to the database.
     *
     * @param rep the {@link CompanyRepresentative} object to be added
     */
    public void createCompanyRepresentative(CompanyRepresentative rep) {
        representatives.add(rep);
    }

    /**
     * Adds a new career center staff record to the database.
     *
     * @param staff the {@link CareerCenterStaff} object to be added
     */
    public void createCareerCenterStaff(CareerCenterStaff staff) {
        staffs.add(staff);
    }

    /**
     * Adds a new internship opportunity record to the database.
     *
     * @param opportunity the {@link InternshipOpportunity} object to be added
     */
    public void createInternshipOpportunity(InternshipOpportunity opportunity) {
        internshipOpportunities.add(opportunity);
    }

    /**
     * Adds a new internship application record to the database.
     *
     * @param application the {@link InternshipApplication} object to be added
     */
    public void createInternshipApplication(InternshipApplication application) {
        internshipApplications.add(application);
    }

    /**
     * Retrieves the list of all registered students.
     *
     * @return an {@link ArrayList} of {@link Student} objects
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Retrieves the list of all registered company representatives.
     *
     * @return an {@link ArrayList} of {@link CompanyRepresentative} objects
     */
    public ArrayList<CompanyRepresentative> getCompanyRepresentatives() {
        return representatives;
    }

    /**
     * Retrieves the list of all career center staff members.
     *
     * @return an {@link ArrayList} of {@link CareerCenterStaff} objects
     */
    public ArrayList<CareerCenterStaff> getCareerCenterStaffs() {
        return staffs;
    }

    /**
     * Retrieves the list of all available internship opportunities.
     *
     * @return an {@link ArrayList} of {@link InternshipOpportunity} objects
     */
    public ArrayList<InternshipOpportunity> getInternshipOpportunities() {
        return internshipOpportunities;
    }

    /**
     * Retrieves the list of all internship applications.
     *
     * @return an {@link ArrayList} of {@link InternshipApplication} objects
     */
    public ArrayList<InternshipApplication> getInternshipApplications() {
        return internshipApplications;
    }

    /**
     * Removes a student record from the database.
     *
     * @param student the {@link Student} object to be removed
     */
    public void deleteStudent(Student student) {
        students.remove(student);
    }

    /**
     * Removes a company representative record from the database.
     *
     * @param rep the {@link CompanyRepresentative} object to be removed
     */
    public void deleteCompanyRepresentative(CompanyRepresentative rep) {
        representatives.remove(rep);
    }

    /**
     * Removes a career center staff record from the database.
     *
     * @param staff the {@link CareerCenterStaff} object to be removed
     */
    public void deleteCareerCenterStaff(CareerCenterStaff staff) {
        staffs.remove(staff);
    }

    /**
     * Removes an internship opportunity record from the database.
     *
     * @param opportunity the {@link InternshipOpportunity} object to be removed
     */
    public void deleteInternshipOpportunities(InternshipOpportunity opportunity) {
        internshipOpportunities.remove(opportunity);
    }

    /**
     * Removes an internship application record from the database.
     *
     * @param application the {@link InternshipApplication} object to be removed
     */
    public void deleteInternshipApplication(InternshipApplication application) {
        internshipApplications.remove(application);
    }

    /**
     * TODO: Implement full CRUD operations (including update and search filters)
     * for all model classes to enable complete database functionality.
     */
    // TODO make CRUD for all models
}
