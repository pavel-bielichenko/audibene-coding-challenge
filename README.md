
*Audibene Coding Challenge implementation*
____________________________________________________________________________________________________________________________
**How to build application?**
```
./gradlew clean build --info
```
-----------------------------------------------------------------------------------------------------------------------------
**Project setup**

Enable annotation processing -> https://www.jetbrains.com/help/idea/configuring-annotation-processing.html 
Optionaly: install IntelliJ Lombok plugin -> https://plugins.jetbrains.com/plugin/6317-lombok-plugin

_____________________________________________________________________________________________________________________________

Dictionary:
Audiologist - Doctor;
Customer - Client


**Challenge solution description:**

In order to not overcomplicate it base Entities are quite simple. I didn't add entity version, active field to client entity, auditing to my solution cause it will make implementation more complex. But I think it make sense to have them.
Doctor has: id and name.
Client has: id and name.
Appointment has: id, clientId, doctorId, startsAt, endsAt, rating.

**Important!**
For resources tests I decided to use Spring Cloud contract tests -> https://codearte.github.io/accurest/
after you build the projects you can find auto genereted tests under -> */build/generated-test-sources/contracts/accurest/* folder.

Contracts can be found under -> https://github.com/pavel-bielichenko/audibene-coding-challenge/tree/master/src/test/resources/contracts/simpleScenario

I like Spring Cloud contract tests, cause they salfe explained, kind of documentation :) and can be used in microservice integration testing.

 1) **as an audiologist I want to create a new customer entry:**
  - ```request {
        method "POST"
        url "/api/v1/clients"
        headers {
            contentType('application/json')
        
        body([
                name: "Bruce Wayne"
        ])
    }
     ---------------------------------------------------------
 2) **as an audiologist I want to create appointments with a customer:**
  - ```request {
        method "POST"
        url "/api/v1/appointments"
        headers {
            contentType('application/json')
        }
        body([
                clientId: 1,
                doctorId: 1,
                startsAt: "$dateTime",
                endsAt  : "$dateTime"
        ])
    }
 
 3) **as an audiologist I want to get a list of all appointments and their ratings:**
 - ```request {
        method "GET"
        url '/api/v1/appointments'
        headers {
            contentType('application/json')
        }
    }
 
 4) **as an audiologist I want to get a list of the next week’s appointments:**
 - ```request {
        method "GET"
        urlPath("/api/v1/doctors/1/appointments") {
            queryParameters {
                parameter from: "$dateTime"
                parameter to: "$dateTime"
            }
        }
        headers {
            contentType('application/json')
        }
    }
 
 5) **as a customer I want to get the next appointment:**
 - ``` request {
        method "GET"
        url "/api/v1/clients/1/appointments/next"
        headers {
            contentType('application/json')
        }
    }
 
 6) **as a customer I want to rate the last appointment:**
 - ```   request {
        method "PUT"
        url "/api/v1/clients/1/appointments/1/rate"
        headers {
            contentType('application/json')
        }
        body([
                rating: "FIVE"
        ])
    }
    
    
    
    **Minuses** application doesn't has any validation
 
