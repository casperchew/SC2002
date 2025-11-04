package model.user;

import model.User;
import model.internship.InternshipApplication;
import java.util.ArrayList; 

public class Student extends User {
    private int YearOfStudy;
    private String Major;
    private ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();
    public static final int MAX_APPLICATIONS = 3;

    public Student(int userID, String name, String passwordHash, int YearOfStudy, String Major) {
        super(userID, name, passwordHash);

        this.YearOfStudy = YearOfStudy;
        this.Major = Major;
    }

    public int getYearOfStudy() {
        return YearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        YearOfStudy = yearOfStudy;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public ArrayList<InternshipApplication> getInternshipApplications() {
        return new ArrayList<>(internshipApplications);
    }

    public int addInternshipApplications(InternshipApplication internshipApplication) {
        if (this.internshipApplications.size() < MAX_APPLICATIONS) {
            this.internshipApplications.add(internshipApplication);
            
            // success
            return 0; 

        } else {

            // FAILURE!!!
            return 1; 
        }
    }

    public int deleteInternshipApplication(InternshipApplication applicationToDelete) {
        if (applicationToDelete == null) {
            // FAIL if the application to delete is null
            return 1; 
        }
        boolean removed = this.internshipApplications.remove(applicationToDelete);

        if (removed) {
            // success
            return 0; 
        } else {
            // FAILURE! 
            return 1; 
        }
    }
}

