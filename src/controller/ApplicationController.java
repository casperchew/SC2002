package src.controller;

import java.util.ArrayList;

import src.model.internship.InternshipApplication;

/**
 * UserController Class that handles CRUD operations for {@link src.model.internship.InternshipApplication}.
 */
public class ApplicationController {
    private Database db;

	/**
	 * Constructs a {@link src.controller.ApplicationController} from the given {@link Database}.
	 *
	 * @param db the {@link src.controller.Database} instance
	 */
    public ApplicationController(Database db) {
        this.db = db;
    }

	// CRUD
	/**
	 * Create a {@link src.model.internship.InternshipApplication}.
	 *
	 * @param application the {@link src.model.internship.InternshipApplication} to create.
	 */
    public void createApplication(InternshipApplication application) {
        db.createInternshipApplication(application);
    }

	/**
	 * Get all {@link src.model.internship.InternshipApplication}s.
	 *
	 * @return An {@code ArrayList} of all {@link src.model.internship.InternshipApplication}s in the database.
	 */
    public ArrayList<InternshipApplication> getInternshipApplications() {
        return db.getInternshipApplications();
	}

	/**
	 * Deletes an internship application.
	 *
	 * @param application The {@link src.model.internship.InternshipApplication} to delete.
	 */
    public void deleteApplication(InternshipApplication application) {
        db.deleteInternshipApplication(application);
    }
}
