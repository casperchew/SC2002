package src.controller;

import java.util.ArrayList;

import src.model.internship.InternshipApplication;

public class ApplicationController {
    private Database db;

    public ApplicationController(Database db) {
        this.db = db;
    }

	// CRUD
    public void createApplication(InternshipApplication application) {
        db.createInternshipApplication(application);
    }

    public ArrayList<InternshipApplication> getInternshipApplications() {
        return db.getInternshipApplications();
	}

    public void deleteApplication(InternshipApplication application) {
        db.deleteInternshipApplication(application);
    }
}
