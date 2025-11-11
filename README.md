# SC2002
```javac -d out $(find . -name "*.java")```  
```java -cp out main.MainApp```

# TODO
- Implement CareerCenterStaff
- CompanyRepresentative (YeeTeck)
- Touch up the print statements for the UI (YeeTeck)
- Remove Application superclass

# TEST CASES:
## Career Center Staff
- log in as ccstaff -> approve all internshipOpportunities -> logout -> login as a student -> apply for internship -> send withdrawal request -> logout -> login as ccstaff -> approve/reject withdrawal request -> logout -> login as student -> check if withdrawal request was rejected or approved.