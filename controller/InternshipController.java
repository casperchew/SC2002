package controller;

import model.internship.*;
import model.InternshipLevel;
import model.user.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The {@code InternshipController} class manages the logic related to internship opportunities.
 * <p>
 * It serves as an intermediary between the CLI and the {@link Database}, providing methods
 * to retrieve and filter internship opportunities based on the attributes of a given student.
 * </p>
 *
 * <p>
 * Specifically, this controller determines which internships a student is eligible to apply for,
 * by checking their major, year of study (translated into internship level), and the visibility
 * status of each internship.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 *     Database db = new Database();
 *     InternshipController internshipController = new InternshipController(db);
 *     Student s = new Student(1001, "Alice", "password", 2, "Computer Science");
 *     ArrayList&lt;InternshipOpportunity&gt; available = internshipController.getInternshipOpportunities(s);
 * </pre>
 *
 * @author  
 * @version 1.0
 */
public class InternshipController {

    /** Reference to the application's in-memory database. */
    private Database db;

    /**
     * Constructs an {@code InternshipController} instance with the given database.
     *
     * @param db the {@link Database} instance that stores all internship data
     */
    public InternshipController(Database db) {
        this.db = db;
    }

    /**
     * Retrieves all internship opportunities that are available to the specified student.
     * <p>
     * This method filters internships according to:
     * <ul>
     *   <li>The student's major (must match one of the internship's preferred majors)</li>
     *   <li>The student's academic level — converted to an {@link InternshipLevel} based on their year of study</li>
     *   <li>The internship's visibility status (only those with {@code isVisible = true} are shown)</li>
     * </ul>
     * </p>
     *
     * <p>
     * Students in their first or second year are classified as {@link InternshipLevel#BASIC},
     * while students in their third year or above are classified as {@link InternshipLevel#ADVANCED}.
     * </p>
     *
     * @param student the {@link Student} object requesting available internships
     * @return an {@link ArrayList} of {@link InternshipOpportunity} objects that the student can apply for
     */
    public ArrayList<InternshipOpportunity> getInternshipOpportunities(Student student) {
        InternshipLevel studentLevel;
        ArrayList<InternshipOpportunity> opportunities = new ArrayList<>();

        // Determine student's internship level based on year of study
        if (student.getYearOfStudy() <= 2) {
            studentLevel = InternshipLevel.BASIC;
        } else {
            studentLevel = InternshipLevel.ADVANCED;
        }

        // Filter internship opportunities based on eligibility
        for (InternshipOpportunity internshipOpp : db.getInternshipOpportunities()) {
            if (
                internshipOpp.getPreferredMajors().contains(student.getMajor())
                && studentLevel.greaterThanOrEqualTo(internshipOpp.getInternshipLevel())
                && internshipOpp.isVisible
            ) {
                opportunities.add(internshipOpp);
            }
        }

        return opportunities;
    }
}
