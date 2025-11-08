package controller;

import model.internship.*;
import model.user.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ApplicationController} class serves as the intermediary layer between the CLI
 * and the underlying database for managing internship applications. 
 * <p>
 * It provides methods to retrieve and create internship applications, acting as part of
 * the Controller layer in the MVC (Model-View-Controller) architecture.
 * </p>
 * 
 * <p>
 * Typical usage example:
 * </p>
 * <pre>
 *     Database db = new Database();
 *     ApplicationController appController = new ApplicationController(db);
 *     ArrayList&lt;InternshipApplication&gt; applications = appController.getInternshipApplications();
 * </pre>
 * 
 * @author 
 * @version 1.0
 */
public class ApplicationController {
    /** Reference to the central {@link Database} instance used for data access. */
    private Database db;

    /**
     * Constructs an {@code ApplicationController} with a reference to the given database.
     *
     * @param db the {@link Database} instance used to store and retrieve application data
     */
    public ApplicationController(Database db) {
        this.db = db;
    }

    /**
     * Retrieves all internship applications currently stored in the database.
     *
     * @return an {@link ArrayList} of {@link InternshipApplication} objects representing 
     *         all existing internship applications
     */
    public ArrayList<InternshipApplication> getInternshipApplications() {
        return db.getInternshipApplications();
    }

    /**
     * Creates and stores a new internship application in the database.
     *
     * @param application the {@link InternshipApplication} object to be added
     */
    public void createApplication(InternshipApplication application) {
        db.createInternshipApplication(application);
    }

    /**
     * Future extension: Processes applications for approval or rejection.
     * <p>
     * This function will be implemented to handle both student internship applications
     * and company representative approval requests, depending on the logged-in user type.
     * </p>
     */
    // public void processApplication(...) { ... }
}
