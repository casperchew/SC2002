package src.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import src.model.*;
import src.model.internship.*;
import src.model.user.*;

public class InternshipController {
    private Database db;

    public InternshipController(Database db) {
        this.db = db;
    }

    public ArrayList<InternshipOpportunity> getInternshipOpportunities(Student student) {
        InternshipLevel studentLevel;
        ArrayList<InternshipOpportunity> opportunities = new ArrayList<InternshipOpportunity>();
        if (student.getYearOfStudy() <= 2) {
            studentLevel = InternshipLevel.BASIC;
        } else {
            studentLevel = InternshipLevel.ADVANCED;
        }

        for (InternshipOpportunity internshipOpp: db.getInternshipOpportunities()) {
            if (
                (internshipOpp.getPreferredMajors().contains(student.getMajor())) 
                && (studentLevel.greaterThanOrEqualTo(internshipOpp.getInternshipLevel()))
                && (internshipOpp.isVisible)
            ) {
                opportunities.add(internshipOpp);
            }
        }
        return opportunities;
    }

    // Overloading
    public ArrayList<InternshipOpportunity> getInternshipOpportunities() {
        return db.getInternshipOpportunities();
    }

    // Overloading
    public ArrayList<InternshipOpportunity> getInternshipOpportunities(Status status) {
        ArrayList<InternshipOpportunity> opportunities = new ArrayList<InternshipOpportunity>();
        for (InternshipOpportunity internshipOpp: db.getInternshipOpportunities()) {
            if (Objects.equals(status, internshipOpp.getStatus())) {
                opportunities.add(internshipOpp);
            }
        }
        return opportunities;
    }
}
