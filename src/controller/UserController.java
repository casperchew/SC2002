package src.controller;

import java.util.ArrayList;
import java.util.Objects;

import src.enums.Status;
import src.model.User;
import src.model.user.Student;
import src.model.user.CompanyRepresentative;
import src.model.user.CareerCenterStaff;

public class UserController {
	private Database db;

	public UserController(Database db) {
		this.db = db;
	}

	// CREATE
    public void createStudent(Student student) {
        db.createStudent(student);
    }

    public void createCompanyRep(CompanyRepresentative rep) {
        db.createCompanyRepresentative(rep);
    }

    public void createCareerCenterStaff(CareerCenterStaff staff) {
        db.createCareerCenterStaff(staff);
    }

	// READ
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        users.addAll(db.getStudents());
        users.addAll(db.getCompanyRepresentatives());
        users.addAll(db.getCareerCenterStaffs());

        return users;
    }

    public ArrayList<Student> getStudents() {
        return db.getStudents();
    }

    public ArrayList<CompanyRepresentative> getCompanyRepresentatives() {
        return db.getCompanyRepresentatives();
    }

    public ArrayList<CompanyRepresentative> getCompanyRepresentativesByStatus(Status status) {
		// TODO: use streams and filters instead
        ArrayList<CompanyRepresentative> reps = new ArrayList<CompanyRepresentative>();
        for (CompanyRepresentative rep: db.getCompanyRepresentatives()) {
            if (Objects.equals(rep.getStatus(), status)) {
                reps.add(rep);
            }
        }

        return reps;
    }

	// TODO: unused, could probably delete
    public ArrayList<CareerCenterStaff> getCareerCenterStaffs() {
        return db.getCareerCenterStaffs();
    }

	// OTHER METHODS
    public User login(String name, String passwordHash) {
        for (User user: getUsers()) {
            if (Objects.equals(name, user.getName()) && Objects.equals(passwordHash, user.getPasswordHash())) {
                return user;
            }
        }

        return null;
    }

    public void saveAllUsers() {
        // TODO: write users to csv
		// This method should be moved to Database.java
    }

}
