package src.model.internship;

import java.time.LocalDate;

import src.model.*;
import src.model.user.*;

public class InternshipApplication {
    private InternshipOpportunity internshipOpportunity;
    private boolean placementConfirmed; 
    private boolean withdrawalRequested;
    private boolean withdrawalApproved;
    private Student applicant;
    private Status status;
    private LocalDate dateApplied;

    public InternshipApplication(Student applicant, InternshipOpportunity internshipOpportunity) {
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null.");
        }
        this.applicant = applicant;
        this.dateApplied = LocalDate.now(); 
        this.status = Status.PENDING; 
        this.internshipOpportunity = internshipOpportunity;
        this.placementConfirmed = false;
        this.withdrawalRequested = false;
        this.withdrawalApproved = false;
    }

    public Student getApplicant() {
        return this.applicant;
    }

    public void setApplicant(Student applicant) {
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

    public InternshipOpportunity getInternshipOpportunity() {
        return internshipOpportunity;
    }

    public void setInternshipOpportunity(InternshipOpportunity internshipOpportunity) {
        this.internshipOpportunity = internshipOpportunity;
    }

    public boolean getPlacementConfirmed() {
        return this.placementConfirmed;
    }
    
    public void setPlacementConfirmed(boolean placementConfirmed) {
        this.placementConfirmed = placementConfirmed;
    }

    public boolean getWithdrawalRequested() {
        return this.withdrawalRequested;
    }

    public void setWithdrawalRequested(boolean withdrawalRequested) {
        this.withdrawalRequested = withdrawalRequested;
    }

    public boolean getWithdrawalApproved() {
        return this.withdrawalApproved;
    }

    public void setWithdrawalApproved(boolean withdrawalApproved) {
        this.withdrawalApproved = withdrawalApproved;
    }
}
