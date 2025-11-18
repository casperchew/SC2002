package src.controller;

import java.util.ArrayList;

import src.model.internship.InternshipApplication;
import src.model.internship.InternshipOpportunity;
import src.model.user.Student;
import src.model.user.CompanyRepresentative;
import src.model.user.CareerCenterStaff;
import src.test.SampleInternships;

public class Database {
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<CompanyRepresentative> representatives = new ArrayList<CompanyRepresentative>();
	private ArrayList<CareerCenterStaff> staffs = new ArrayList<CareerCenterStaff>();
	private ArrayList<InternshipOpportunity> internshipOpportunities = new ArrayList<InternshipOpportunity>();
	private ArrayList<InternshipApplication> internshipApplications = new ArrayList<InternshipApplication>();

	public Database() {
		// For testing
		internshipOpportunities = SampleInternships.getSampleList();
	}

	// CREATE
	public void createStudent(Student student) {
		students.add(student);
	}

	public void createCompanyRepresentative(CompanyRepresentative rep) {
		representatives.add(rep);
	}

	public void createCareerCenterStaff(CareerCenterStaff staff) {
		staffs.add(staff);
	}

	public void createInternshipOpportunity(InternshipOpportunity opportunity) {
		internshipOpportunities.add(opportunity);
	}

	public void createInternshipApplication(InternshipApplication application) {
		internshipApplications.add(application);
	}

	// READ
	public ArrayList<Student> getStudents() {
		return students;
	}

	public ArrayList<CompanyRepresentative> getCompanyRepresentatives() {
		return representatives;
	}

	public ArrayList<CareerCenterStaff> getCareerCenterStaffs() {
		return staffs;
	}

	public ArrayList<InternshipOpportunity> getInternshipOpportunities() {
		return internshipOpportunities;
	}

	public ArrayList<InternshipApplication> getInternshipApplications() {
		return internshipApplications;
	}

	// UPDATE


	// DELETE
	public void deleteStudent(Student student) {
		students.remove(student);
	}

	public void deleteCompanyRepresentative(CompanyRepresentative rep) {
		representatives.remove(rep);
	}

	public void deleteCareerCenterStaff(CareerCenterStaff staff) {
		staffs.remove(staff);
	}

	public void deleteInternshipOpportunities(InternshipOpportunity opportunity) {
		internshipOpportunities.remove(opportunity);
	}

	public void deleteInternshipApplication(InternshipApplication application) {
		internshipApplications.remove(application);
	}
}
