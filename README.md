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

## Test Cases
### User's capabilities
#### 1. All Users
##### 1.1. Login
Expected Behaviour: User should be able to access their dashboard based on their roles

Failure Indicators: User cannot log in or receive incorrect error messages

| Step    | Description           | Input        |
| --------| --------------------- | ------------ |
| 1       | Select "Login" option | 1            |
| 2       | Enter name            | Tan Wei Ling |
| 3       | Enter password        | password     |

Expected Behaviour: The cli should display
```
Logged in as Tan Wei Ling
...
```

##### 1.2. Logout
Expected Behaviour: User that is logged in should be able to log out

Failure Indicators: User cannot log out or recieve incorrect error messages

| Step    | Description           | Input        |
| --------| --------------------- | ------------ |
| 1       | Select "Login" option | 1            |
| 2       | Enter name            | Tan Wei Ling |
| 3       | Enter password        | password     |
| 4       | Log out               | 5            |

Expected Behaviour: The cli should display
```
Logging out...
...
```

##### 1.3. Change password
Expected Behaviour: User that is logged in should be able to change password

Failure Indicators: User cannot change password

| Step    | Description                     | Input        |
| --------| ------------------------------- | ------------ |
| 1       | Select "Login" option           | 1            |
| 2       | Enter name                      | Tan Wei Ling |
| 3       | Enter password                  | password     |
| 4       | Select "change password" option | 4            |
| 5       | Enter new password              | newpassword  |
| 6       | Select "Login" option           | 1            |
| 7       | Enter name                      | Tan Wei Ling |
| 8       | Enter password                  | newpassword  |

Expected Behaviour:

After step 5, the cli should display
```
Your new password has been set.
Please re-login with your new password.
...
```
After step 8, the cli should display
```
Logged in as Tan Wei Ling
...
```

#### 2. Student
##### 2.1. Automatic registration
##### 2.2. View internship opportunities (based on students profile and visibility)
Expected Behaviour: Student is only able to view appropriate internship opportunities

Failure Indicators: Student can view internship opportunities not applicable to themselves

| Step | Description                                                                                       | Input        |
| ---- | ------------------------------------------------------------------------------------------------- | ------------ |
| 1    | Create BASIC Internship Opportunity for Computer Science Majors (refer to 3.2) with title "test"  |              |
| 2    | Approve created Internship Opportunity (refer to 4.3)                                             |              |
| 3    | Select "Login" option                                                                             | 1            |
| 4    | Enter name                                                                                        | Tan Wei Ling |
| 5    | Enter password                                                                                    | password     |
| 6    | Select "Apply for internship"                                                                     | 1            |


Expected Behaviour:

After step 6, the cli should display
```
1) test
...
```
##### 2.3. Apply for internship opportunity
Expected Behaviour: Student is only able to view appropriate internship opportunities

Failure Indicators: Student can view internship opportunities not applicable to themselves

| Step | Description                                                                                      | Input        |
| ---- | ------------------------------------------------------------------------------------------------ | ------------ |
| 1    | Create BASIC Internship Opportunity for Computer Science Majors (refer to 3.2) with title "test" |              |
| 2    | Approve created Internship Opportunity (refer to 4.3)                                            |              |
| 3    | Select "Login" option                                                                            | 1            |
| 4    | Enter name                                                                                       | Tan Wei Ling |
| 5    | Enter password                                                                                   | password     |
| 6    | Select "Apply for internship"                                                                    | 1            |
| 7    | Select "1) test"                                                                                 | 1            |
| 8    | Select "1) Apply for this internship."                                                           | 1            |
| 9    | Exit the menu                                                                                    | -1           |
| 10   | Select "2) View internship applications."                                                        | 2            |

Expected Behaviour:

After step 10, the cli should display
```
1) test
...
```
###### 2.3.1. Maximum 3 applications
| Step | Description                                                                                       | Input        |
| ---- | ------------------------------------------------------------------------------------------------- | ------------ |
| 1    | Create BASIC Internship Opportunity for Computer Science Majors (refer to 3.2) with title "test1" |              |
| 2    | Create BASIC Internship Opportunity for Computer Science Majors (refer to 3.2) with title "test2" |              |
| 3    | Create BASIC Internship Opportunity for Computer Science Majors (refer to 3.2) with title "test3" |              |
| 4    | Create BASIC Internship Opportunity for Computer Science Majors (refer to 3.2) with title "test4" |              |
| 5    | Approve created Internship Opportunities (refer to 4.3)                                           |              |
| 6    | Select "Login" option                                                                             | 1            |
| 7    | Enter name                                                                                        | Tan Wei Ling |
| 8    | Enter password                                                                                    | password     |
| 9    | Apply for "test1", "test2", "test3" and "test4" (repeat steps 6 to 10 of 2.3)                     |              |
| 10   | Apply for "test4" (refer to steps 6 to 10 of 2.3)                                                 |              |

Expected Behaviour:

After step 10, the cli should display
```
You already have 3 applications pending.
...
```
###### 2.3.2. `InternshipLevel` validation based on Student's `yearOfStudy`.
##### 2.4. View internship applications
###### 2.4.1. Default `PENDING` status
##### 2.5. Accept internship placement
###### 2.5.1. Only 1 can be accepted
###### 2.5.2. Other applications will be withdrawn once an internship placement in accepted
##### 2.6. Request internship application withdrawal subject to approval from `CareerCenterStaff`
###### 2.6.1. Before placement confirmation
###### 2.6.2. After placement confirmation

#### 3. Company Representatives
##### 3.1. Registration must include a company
###### 3.1.1. Can only log in once approved by a `CareerCenterStaff`
##### 3.2. Create internship opportunities
###### 3.2.1. Max 5
##### 3.3. After an `InternshipOpportunity` is approved by a `CareerCenterStaff`,
###### 3.3.1. View application details and student details for each of the `InternshipOpportunity`
##### 3.4. Approve or reject intership applications
##### 3.5. Toggle visibility of internship opportunity to `on` or `off`.

#### 4. Career Center Staff
##### 4.1. Automatic registration
##### 4.2. Authorize of reject the account creation of `CompanyRepresentative`
##### 4.3. Approve of reject internship opportunities submitted by `CompanyRepresentative`
###### 3.1. Once approved, the internship opportunity becomes visible to eligible students
##### 4.4. Approve or reject student withdrawal requests
##### 4.5. Generate intership opportunity reports
###### 4.5.1. Filter internship opportunities

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
