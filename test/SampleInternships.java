package test;

import java.time.LocalDate;
import java.util.ArrayList;

import model.internship.InternshipOpportunity;
import model.user.CompanyRepresentative;
import model.InternshipLevel;

public class SampleInternships {

    public static ArrayList<InternshipOpportunity> getSampleList() {

        // Company representatives
        CompanyRepresentative rep1 = new CompanyRepresentative(1, "Alice Tan", "hashedPW1", "TechCorp");
        CompanyRepresentative rep2 = new CompanyRepresentative(2, "Ben Wong", "hashedPW2", "FinTech Innovations");
        CompanyRepresentative rep3 = new CompanyRepresentative(3, "Chloe Lee", "hashedPW3", "HealthPlus");
        CompanyRepresentative rep4 = new CompanyRepresentative(4, "Derek Lim", "hashedPW4", "AeroSys");
        CompanyRepresentative rep5 = new CompanyRepresentative(5, "Eva Koh", "hashedPW5", "MedSignals");

        // Lists of representatives
        ArrayList<CompanyRepresentative> reps1 = new ArrayList<>();
        reps1.add(rep1);

        ArrayList<CompanyRepresentative> reps2 = new ArrayList<>();
        reps2.add(rep2);

        ArrayList<CompanyRepresentative> reps3 = new ArrayList<>();
        reps3.add(rep3);

        ArrayList<CompanyRepresentative> reps4 = new ArrayList<>();
        reps4.add(rep4);

        ArrayList<CompanyRepresentative> reps5 = new ArrayList<>();
        reps5.add(rep5);

        // Preferred majors
        ArrayList<String> majorsCS = new ArrayList<>();
        majorsCS.add("Computer Science");
        
        ArrayList<String> majorsDSAI = new ArrayList<>();
        majorsDSAI.add("Data Science & AI");
        
        ArrayList<String> majorsCE = new ArrayList<>();
        majorsCE.add("Computer Engineering");
        
        ArrayList<String> majorsIEM = new ArrayList<>();
        majorsIEM.add("Information Engineering & Media");
        
        ArrayList<String> majorsBIO_CS = new ArrayList<>();
        majorsBIO_CS.add("BIO");
        majorsBIO_CS.add("Computer Science");

        // List of internships
        ArrayList<InternshipOpportunity> internships = new ArrayList<>();

        internships.add(new InternshipOpportunity(
                "Software Developer Intern",
                "Work on backend Java applications and APIs",
                InternshipLevel.BASIC,
                majorsCS,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 31),
                "TechCorp",
                reps1,
                5,
                true
        ));

        internships.add(new InternshipOpportunity(
                "Data Science Intern",
                "Assist with machine learning model training and data preparation",
                InternshipLevel.INTERMEDIATE,
                majorsDSAI,
                LocalDate.of(2025, 2, 1),
                LocalDate.of(2025, 2, 28),
                "FinTech Innovations",
                reps2,
                3,
                true
        ));

        internships.add(new InternshipOpportunity(
                "Cybersecurity Analyst Intern",
                "Participate in penetration testing and SOC monitoring",
                InternshipLevel.BASIC,
                majorsCE,
                LocalDate.of(2025, 1, 15),
                LocalDate.of(2025, 2, 15),
                "HealthPlus",
                reps3,
                4,
                true
        ));

        internships.add(new InternshipOpportunity(
                "Aerospace Software Intern",
                "Develop flight-control simulation software",
                InternshipLevel.ADVANCED,
                majorsIEM,
                LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 3, 30),
                "AeroSys",
                reps4,
                2,
                true
        ));

        internships.add(new InternshipOpportunity(
                "Bio-Tech R&D Intern",
                "Assist with data analysis and lab automation tools",
                InternshipLevel.ADVANCED,
                majorsBIO_CS,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 20),
                "MedSignals",
                reps5,
                6,
                true
        ));

        return internships;
    }
}
