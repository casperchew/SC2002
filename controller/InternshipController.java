package controller;

import model.internship.*;
import model.InternshipLevel;
import model.user.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}
