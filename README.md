# BiddingTool | mbr targeting
 
**Prerequisites**
* Java 1.8 or higher
---
**Assumptions:**
* There must be atleast one match rule for the campign to be eligible for the bid.
* A rule is considered if and only if both key and value match
---
**Usage**

**Start Application**

**Gradle**
* Run command "gradlew.bat bootRun" if Windows OS
* Run command "./gradlew bootRun" if Linux or Mac OS

**IDE**
1. Open as Gradle Java project in IntelliJ IDE.
2. Create Run configuration for a Java Application.
3. Use spo.workforcemanager.WorkforceManagerApplication as the Main Class
4. Use spo.Workforce_Manager as classpath of module.
5. Select JRE 1.8 or higher
6. Run the created Run configuration.
8. SpringBoot REST application will be started in an embedded tomcat server with port 8090
---
* The default port number is port:**8090**
---
**REST Endpoints**

* POST request to http://localhost:8090 with the input json.
* GET request to http://localhost:8090 with an arbitrary number of query parameters (rules to match).
---
**Tools**
* Use a REST Client like Postman
* Use Java 11's new HTTP Client
* Use any thirdparty HTTP Client
---
**Stop Application**
* Stop tomcat service to stop the application
---
