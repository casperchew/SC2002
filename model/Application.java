package model;

import java.time.LocalDate;

public abstract class Application {

    protected User applicant;
    protected Status status;
    protected LocalDate dateApplied;

    public Application(User applicant) {
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null.");
        }
        
        this.applicant = applicant;
        this.dateApplied = LocalDate.now(); 
        this.status = Status.PENDING; 
    }

    public User getApplicant() {
        return this.applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateApplied() {
        return this.dateApplied;
    }
}

