# SC2002

[Javadoc](https://casperchew.github.io/SC2002/)

# TODO

- Implement CareerCenterStaff
- CompanyRepresentative (YeeTeck)
- Touch up the print statements for the UI (YeeTeck)
- Remove Application superclass

# TEST CASES:

## Student

### Applying for internship

- log in as ccstaff -> approve all internshipOpportunities -> logout -> login as a student -> select Apply for internship -> select internship opportunity -> apply for it -> logout -> login as ccstaff -> select View all internship applications -> check if the students application is displayed properly.

### View internship applications

- log in as ccstaff -> approve all internshipOpportunities -> logout -> login as a student -> select Apply for internship -> select a few internship opportunity -> apply for it -> exit that menu -> select View internship applications -> check if the applications are correctly displayed -> select each application -> check if information of each application is correct.

### Filtering internship opportunities

- log in as ccstaff -> approve all internshipOpportunities -> logout -> login as a student -> select Apply for internship -> take note of the displayed internship opportunities -> exit -> select Set filters -> set some filters (e.g. set company name filter to TechCorp) -> exit -> select Apply for internship -> ensure that the displayed internship opportunities are correct based on the filters set.

- login as a student -> select Set filters -> set some filters and take note of them -> exit -> logout -> log back in as the same student -> select Set filters -> check that the filters did not change.

### Changing password

- login as a student -> select Change password -> set password to "newPassword" -> logout -> attempt to login with the old password "password" -> attempt to login with the new password "newPassword" -> ensure that you can successfully login with the new password "newPassword".

## Career Center Staff

### Approving Company rep

- create 2 companyRep accounts -> log in as a ccstaff -> approve one companyRep and reject the other -> logout -> try logging in to both accounts.

### Approving internship opportunity

- log in as ccstaff -> approve 'Software Developer Intern' and reject 'Front-End Web Development Intern' -> log in as Tan Wei Ling -> apply for internship -> ensure that 'Software Developer Intern' is displayed and 'Front-End Web Development Intern' is not displayed.

### Withdrawal request

- log in as ccstaff -> approve all internshipOpportunities -> logout -> login as a student -> apply for internship -> send withdrawal request -> logout -> login as ccstaff -> approve/reject withdrawal request -> logout -> login as student -> check if withdrawal request was rejected or approved.

- log in as ccstaff -> approve all internshipOpportunities -> logout -> login as a student -> apply for internship -> logout -> login as the respective company rep -> approve the student application -> logout -> login as the student -> accept the internship opportunity (i.e. confirm placement) -> send withdrawal request for that very internship opportunity -> logout -> login as ccstaff -> approve/reject withdrawal request -> logout -> login as student -> check if withdrawal request was rejected or approved.

### Filtering internship opportunities

- create a new company representative account (username: d, password: d, company d) -> log in as CCStaff "Mr. Tan Boon Kiat" with password "password" -> approve company representative "d" -> log out -> log in as "d" -> create internship opportunity with preferred major as "Computer Science" and internship level as ADVANCED -> log out -> log in as "Mr. Tan Boon Kiat" -> set company representative filter to "d", set preferred major as "Computer Science" and set internship level filter as ADVANCED (the rest of the fields don't matter) -> generate internship opportunity report -> ensure that only "d"'s opportunity is displayed.

### Generate Report

- log in as ccstaff -> select generate internship opportunity report -> select any internship -> check if report is properly generated.

## Database

The default password to all the users is `password`.

### Students

| StudentID | Name          | Major                           | Year | Email                 |
| --------- | ------------- | ------------------------------- | ---- | --------------------- |
| U2310001A | Tan Wei Ling  | Computer Science                | 2    | tan001@e.ntu.edu.sg   |
| U2310002B | Ng Jia Hao    | Data Science & AI               | 3    | ng002@e.ntu.edu.sg    |
| U2310003C | Lim Yi Xuan   | Computer Engineering            | 4    | lim003@e.ntu.edu.sg   |
| U2310004D | Chong Zhi Hao | Information Engineering & Media | 1    | chong004@e.ntu.edu.sg |
| U2310005E | Wong Shu Hui  | Computer Science                | 3    | wong005@e.ntu.edu.sg  |

### Company Representatives

There are no initial Company Representatives

### Career Center Staffs

| StaffID | Name              | Role                | Department | Email             |
| ------- | ----------------- | ------------------- | ---------- | ----------------- |
| sng001  | Dr. Sng Hui Lin   | Career Center Staff | CCDS       | sng001@ntu.edu.sg |
| tan002  | Mr. Tan Boon Kiat | Career Center Staff | CCDS       | tan002@ntu.edu.sg |
| lee003  | Ms. Lee Mei Ling  | Career Center Staff | CCDS       | lee003@ntu.edu.sg |
