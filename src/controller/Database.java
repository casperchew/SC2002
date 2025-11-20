package src.controller;

import java.util.ArrayList;

import src.model.internship.InternshipApplication;
import src.model.internship.InternshipOpportunity;
import src.model.user.Student;
import src.model.user.CompanyRepresentative;
import src.model.user.CareerCenterStaff;
// import src.test.SampleInternships;

// For testing
import java.time.LocalDate;
import java.util.Arrays;
import src.enums.InternshipLevel;
import src.enums.Status;

/**
 * The {@link Database} class contains the instances of all objects in the program.
 */
public class Database {
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<CompanyRepresentative> representatives = new ArrayList<CompanyRepresentative>();
	private ArrayList<CareerCenterStaff> staffs = new ArrayList<CareerCenterStaff>();
	private ArrayList<InternshipOpportunity> internshipOpportunities = new ArrayList<InternshipOpportunity>();
	private ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();

	/**
	 * Constructs a {@link Database} instance.
	 */
	public Database() {
		// For testing
		// internshipOpportunities = SampleInternships.getSampleList();

		CompanyRepresentative rep1 = new CompanyRepresentative("a", "a", "password", "a");
		rep1.setStatus(Status.APPROVED);
		representatives.add(rep1);

		InternshipOpportunity opp = new InternshipOpportunity(
			"Title",
			"Description",
			InternshipLevel.BASIC,
			"Computer Science",
			LocalDate.parse("2020-01-01"),
			LocalDate.parse("2030-01-01"),
			"a",
			new ArrayList<CompanyRepresentative>(representatives),
			1
		);
		opp.setStatus(Status.APPROVED);
		opp.setVisibility(true);
		internshipOpportunities.add(opp);
	}

	// CREATE
	/**
	 * Creates a {@link src.model.user.Student} in the database
	 *
	 * @param student the {@link src.model.user.Student} to be created
	 */
	public void createStudent(Student student) {
		students.add(student);
	}

	/**
	 * Creates a {@link src.model.user.CompanyRepresentative} in the database
	 *
	 * @param rep the {@link src.model.user.CompanyRepresentative} to be created
	 */
	public void createCompanyRepresentative(CompanyRepresentative rep) {
		representatives.add(rep);
	}

	/**
	 * Creates a {@link src.model.user.CareerCenterStaff} in the database
	 *
	 * @param staff the {@link src.model.user.CareerCenterStaff} to be created
	 */
	public void createCareerCenterStaff(CareerCenterStaff staff) {
		staffs.add(staff);
	}

	/**
	 * Creates a {@link src.model.internship.InternshipOpportunity} in the database
	 *
	 * @param opportunity the {@link src.model.internship.InternshipOpportunity} to be created
	 */
	public void createInternshipOpportunity(InternshipOpportunity opportunity) {
		internshipOpportunities.add(opportunity);
	}

	/**
	 * Creates a {@link src.model.internship.InternshipApplication} in the database
	 *
	 * @param application the {@link src.model.internship.InternshipApplication} to be created
	 */
	public void createInternshipApplication(InternshipApplication application) {
		internshipApplications.add(application);
	}

	// READ
	/**
	 * Gets all the {@link src.model.user.Student}s in the database.
	 *
	 * @return The {@link src.model.user.Student}s in the database.
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}

	/**
	 * Gets all the {@link src.model.user.CompanyRepresentative}s in the database.
	 *
	 * @return The {@link src.model.user.CompanyRepresentative}s in the database.
	 */
	public ArrayList<CompanyRepresentative> getCompanyRepresentatives() {
		return representatives;
	}

	/**
	 * Gets all the {@link src.model.user.CareerCenterStaff}s in the database.
	 *
	 * @return The {@link src.model.user.CareerCenterStaff}s in the database.
	 */
	public ArrayList<CareerCenterStaff> getCareerCenterStaffs() {
		return staffs;
	}

	/**
	 * Gets all the {@link src.model.internship.InternshipOpportunity}s in the database.
	 *
	 * @return The {@link src.model.internship.InternshipOpportunity}s in the database.
	 */
	public ArrayList<InternshipOpportunity> getInternshipOpportunities() {
		return internshipOpportunities;
	}

	/**
	 * Gets all the {@link src.model.internship.InternshipApplication}s in the database.
	 *
	 * @return The {@link src.model.internship.InternshipApplication}s in the database.
	 */
	public ArrayList<InternshipApplication> getInternshipApplications() {
		return internshipApplications;
	}

	// UPDATE

	// DELETE
	/**
	 * Deletes a {@link src.model.user.Student} from the database.
	 *
	 * @param student The {@link src.model.user.Student} to delete.
	 */
	public void deleteStudent(Student student) {
		students.remove(student);
	}

	/**
	 * Deletes a {@link src.model.user.CompanyRepresentative} from the database.
	 *
	 * @param rep The {@link src.model.user.CompanyRepresentative} to delete.
	 */
	public void deleteCompanyRepresentative(CompanyRepresentative rep) {
		representatives.remove(rep);
	}

	/**
	 * Deletes a {@link src.model.user.CareerCenterStaff} from the database.
	 *
	 * @param staff The {@link src.model.user.CareerCenterStaff} to delete.
	 */
	public void deleteCareerCenterStaff(CareerCenterStaff staff) {
		staffs.remove(staff);
	}

	/**
	 * Deletes a {@link src.model.internship.InternshipOpportunity} from the database.
	 *
	 * @param opportunity The {@link src.model.internship.InternshipOpportunity} to delete.
	 */
	public void deleteInternshipOpportunities(InternshipOpportunity opportunity) {
		internshipOpportunities.remove(opportunity);
	}

	/**
	 * Deletes a {@link src.model.internship.InternshipApplication} from the database.
	 *
	 * @param application The {@link src.model.internship.InternshipApplication} to delete.
	 */
	public void deleteInternshipApplication(InternshipApplication application) {
		internshipApplications.remove(application);
	}
}
