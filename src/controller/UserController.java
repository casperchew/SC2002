package src.controller;

import java.util.ArrayList;
import java.util.Objects;

import src.enums.Status;
import src.model.User;
import src.model.user.Student;
import src.model.user.CompanyRepresentative;
import src.model.user.CareerCenterStaff;

/**
 * UserController Class that handles CRUD operations for {@link src.model.User}.
 */
public class UserController {
	private Database db;

	/**
	 * Constructs a {@link UserController} from the given {@link Database}
	 *
	 * @param db The {@link Database} used.
	 */
	public UserController(Database db) {
		this.db = db;
	}

	// CREATE
	/**
	 * Create a {@link src.model.user.Student}
	 *
	 * @param student the {@link src.model.user.Student} to create
	 */
    public void createStudent(Student student) {
        db.createStudent(student);
    }

	/**
	 * Create a {@link src.model.user.CompanyRepresentative}
	 *
	 * @param rep the {@link src.model.user.CompanyRepresentative} to create
	 */
    public void createCompanyRep(CompanyRepresentative rep) {
        db.createCompanyRepresentative(rep);
    }

	/**
	 * Create a {@link src.model.user.CareerCenterStaff}
	 *
	 * @param staff the {@link src.model.user.CareerCenterStaff} to create
	 */
    public void createCareerCenterStaff(CareerCenterStaff staff) {
        db.createCareerCenterStaff(staff);
    }

	// READ
	/**
	 * Get all {@link src.model.User}s in the database.
	 *
	 * @return An {@code ArrayList} with all {@link src.model.User}s in the database.
	 */
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        users.addAll(db.getStudents());
        users.addAll(db.getCompanyRepresentatives());
        users.addAll(db.getCareerCenterStaffs());

        return users;
    }

	/**
	 * Get all {@link src.model.user.Student}s in the database.
	 *
	 * @return An {@code ArrayList} with all {@link src.model.user.Student}s in the database.
	 */
    public ArrayList<Student> getStudents() {
        return db.getStudents();
    }

	/**
	 * Get all {@link src.model.user.CompanyRepresentative}s in the database.
	 *
	 * @return An {@code ArrayList} with all {@link src.model.user.CompanyRepresentative}s in the database.
	 */
    public ArrayList<CompanyRepresentative> getCompanyRepresentatives() {
        return db.getCompanyRepresentatives();
    }

	/**
	 * Get all {@link src.model.user.CompanyRepresentative}s in the database that has a specified {@code status}.
	 *
	 * @param status The status of the {@link src.model.user.CompanyRepresentative}s.
	 * @return An {@code ArrayList} with all {@link src.model.user.CompanyRepresentative}s in the database that satisfy the criteria.
	 */
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

	// OTHER METHODS
	/**
	 * Acts as a login function
	 *
	 * @param name The name of the {@link src.model.User}.
	 * @param passwordHash The {@code passwordHash} of the {@link src.model.User}.
	 * @return The {@link src.model.User} instance if successful, null if unsuccessful.
	 */
    public User login(String name, String passwordHash) {
        for (User user: getUsers()) {
            if (Objects.equals(name, user.getName()) && Objects.equals(passwordHash, user.getPasswordHash())) {
                return user;
            }
        }

        return null;
    }
}
