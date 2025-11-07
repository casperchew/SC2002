package model.internship;

import model.*;
import model.user.*;

public class InternshipApplication extends Application {
    private InternshipOpportunity internshipOpportunity;
    private boolean placementConfirmed; 
    private boolean withdrawalRequested;
    private boolean withdrawalApproved;
    public InternshipApplication(Student applicant, InternshipOpportunity internshipOpportunity) {
        super(applicant);
        this.internshipOpportunity = internshipOpportunity;
        this.placementConfirmed = false;
        this.withdrawalRequested = false;
        this.withdrawalApproved = false;
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
