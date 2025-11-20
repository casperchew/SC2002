package src.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import src.enums.InternshipLevel;
import src.enums.Status;
import src.model.User;
import src.model.internship.InternshipOpportunity;
import src.model.internship.InternshipApplication;
import src.model.user.Student;
import src.model.user.CompanyRepresentative;

/**
 * InternshipOpportunity Class that handles CRUD operations for {@link src.model.internship.InternshipOpportunity}.
 */
public class InternshipOpportunityController {
    private Database db;

	/**
	 * Constructs a {@link src.controller.InternshipOpportunityController} from the given {@link Database}.
	 *
	 * @param db the {@link src.controller.Database} instance
	 */
    public InternshipOpportunityController(Database db) {
        this.db = db;
    }

	// CRUD
	// Create
	/**
	 * Create a {@link src.model.internship.InternshipOpportunity}
	 *
	 * @param opportunity the {@link src.model.internship.InternshipOpportunity} to create
	 */
    public void createInternshipOpportunity(InternshipOpportunity opportunity) {
        db.createInternshipOpportunity(opportunity);
    }

	// Read
	/**
	 * Get all {@link src.model.internship.InternshipOpportunity}s in the database.
	 *
	 * @return An {@code ArrayList} with all {@link src.model.internship.InternshipOpportunity}s in the database.
	 */
    public ArrayList<InternshipOpportunity> getInternshipOpportunities() {
        return db.getInternshipOpportunities();
    }

	/**
	 * Get all {@link src.model.internship.InternshipOpportunity}s in the database that has a specified {@code status}.
	 *
	 * @param status The status of the {@link src.model.internship.InternshipOpportunity}s.
	 * @return An {@code ArrayList} with all {@link src.model.internship.InternshipOpportunity}s in the database that satisfy the criteria.
	 */
    public List<InternshipOpportunity> getInternshipOpportunitiesByStatus(Status status) {
		return db.getInternshipOpportunities().stream()
			.filter(opp -> opp.getStatus().equals(status))
			.collect(Collectors.toList());
    }

	/**
	 * Get all {@link src.model.internship.InternshipOpportunity}s in the database that {@code student} is eligible for.
	 *
	 * @param student The {@link src.model.user.Student} to check against.
	 * @return An {@code ArrayList} with all {@link src.model.internship.InternshipOpportunity}s in the database that satisfy the criteria.
	 */
    public List<InternshipOpportunity> getInternshipOpportunitiesByStudent(Student student) {
		// TODO: this violates one of the SOLID principles, but dont know which one, maybe OCP? To fix it we need to add a getInternshipLevel method to Student.java
		InternshipLevel studentLevel = (student.getYearOfStudy() <= 2) ? InternshipLevel.BASIC : InternshipLevel.ADVANCED;

		return db.getInternshipOpportunities().stream()
			.filter(opp -> opp.getPreferredMajor().equals(student.getMajor()))
			.filter(opp -> studentLevel.greaterThanOrEqualTo(opp.getInternshipLevel()))
			.filter(opp -> opp.getVisibility())
			.collect(Collectors.toList());
    }

	/**
	 * Get all {@link src.model.internship.InternshipOpportunity}s in the database that {@code companyRepresentative} is involved in.
	 *
	 * @param companyRepresentative The selected {@link src.model.user.CompanyRepresentative}.
	 * @return An {@code ArrayList} with all {@link src.model.internship.InternshipOpportunity}s in the database that satisfy the criteria.
	 */
    public List<InternshipOpportunity> getInternshipOpportunitiesByCompanyRepresentative(CompanyRepresentative companyRepresentative) {
		return db.getInternshipOpportunities().stream()
			.filter(opp -> opp.getCompanyRepresentatives().contains(companyRepresentative))
			.collect(Collectors.toList());
    }
}
