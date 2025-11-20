package src.controller;

import java.util.ArrayList;
import java.util.Objects;

import src.enums.InternshipLevel;
import src.enums.Status;
import src.model.User;
import src.model.internship.InternshipOpportunity;
import src.model.internship.InternshipApplication;
import src.model.user.Student;
import src.model.user.CompanyRepresentative;

public class InternshipOpportunityController {
    private Database db;

    public InternshipOpportunityController(Database db) {
        this.db = db;
    }

	// CRUD
	// Create
    public void createInternshipOpportunity(InternshipOpportunity opportunity) {
        db.createInternshipOpportunity(opportunity);
    }

	// Read
    public ArrayList<InternshipOpportunity> getInternshipOpportunities() {
        return db.getInternshipOpportunities();
    }

    public ArrayList<InternshipOpportunity> getInternshipOpportunitiesByStatus(Status status) {
        ArrayList<InternshipOpportunity> opportunities = new ArrayList<InternshipOpportunity>();
        for (InternshipOpportunity internshipOpp: db.getInternshipOpportunities()) {
            if (Objects.equals(status, internshipOpp.getStatus())) {
                opportunities.add(internshipOpp);
            }
        }
        return opportunities;
    }

    public ArrayList<InternshipOpportunity> getInternshipOpportunitiesByStudent(Student student) {
		// TODO: use streams instead
        InternshipLevel studentLevel;
        ArrayList<InternshipOpportunity> opportunities = new ArrayList<InternshipOpportunity>();
        if (student.getYearOfStudy() <= 2) {
            studentLevel = InternshipLevel.BASIC;
        } else {
            studentLevel = InternshipLevel.ADVANCED;
        }

		// TODO: use streams instead
        for (InternshipOpportunity internshipOpp: db.getInternshipOpportunities()) {
            if (
                (internshipOpp.getPreferredMajor().contains(student.getMajor())) 
                && (studentLevel.greaterThanOrEqualTo(internshipOpp.getInternshipLevel()))
                && (internshipOpp.getVisibility())
            ) {
                opportunities.add(internshipOpp);
            }
        }

        return opportunities;
    }

    public ArrayList<InternshipOpportunity> getInternshipOpportunitiesByCompanyRepresentative(CompanyRepresentative companyRepresentative) {
        ArrayList<InternshipOpportunity> opportunities = new ArrayList<InternshipOpportunity>();
        for (InternshipOpportunity internshipOpp: db.getInternshipOpportunities()) {
            for (CompanyRepresentative oppCompanyRepresentative: internshipOpp.getCompanyRepresentatives()) {
                if (Objects.equals(companyRepresentative.getUserID(), oppCompanyRepresentative.getUserID())) {
                    opportunities.add(internshipOpp);
                    break;
                }
            }
        }

        return opportunities;
    }
}
