package controller;

import java.util.ArrayList;

import model.internship.*;
import model.user.*;

public class ApplicationController {
    private Database db;

    public ApplicationController(Database db) {
        this.db = db;
    }

    public ArrayList<InternshipApplication> getInternshipApplications() {
        return db.getInternshipApplications();
    }

    public void createApplication(InternshipApplication application) {
        db.createInternshipApplication(application);
    }

    public void deleteApplication(InternshipApplication application) {
        db.deleteInternshipApplication(application);
    }

    // process application function should work for both types of applications
    // the cli to use will depend on which user is logged in
}
