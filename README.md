
*Audibene Coding Challenge implementation*
____________________________________________________________________________________________________________________________
**How to build application?**
```
./gradlew clean build --info
```

For test I am using H2 db

____________________________________________________________________________________________________________________________
**How to run application?**
```
./gradlew bootRun
```

**Please make sure that you define datasource properties befor run app:**
I am using postgresql
you can use your configuration ;) 
```
datasource:
    url: "jdbc:postgresql://localhost:5432/audibene"
    username: "audibene_app"
    password:
```

----------------------------------------------------------------------------------------------------------------------------
**Project setup**

Enable annotation processing -> https://www.jetbrains.com/help/idea/configuring-annotation-processing.html 
Optionaly: install IntelliJ Lombok plugin -> https://plugins.jetbrains.com/plugin/6317-lombok-plugin

_____________________________________________________________________________________________________________________________

Dictionary:
Audiologist - Doctor;
Customer - Client


**Challenge solution description:**

In order to not overcomplicate it base Entities are quite simple. I didn't add entity version, active field to client entity or auditing, cause it will make implementation more complex. But I think it make sense to have them.
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
    
```curl -i -X POST -H "Content-Type:application/json" http://localhost:8411/api/v1/clients -d '{"name":"Bruce"}```

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
``` curl -i -X POST -H "Content-Type:application/json" http://localhost:8411/api/v1/appointments -d '{"clientId": "1","doctorId": "1","startsAt": "$dateTime","endsAt"  : "$dateTime"}```
 
 3) **as an audiologist I want to get a list of all appointments and their ratings:**
 - ```request {
        method "GET"
        url '/api/v1/appointments'
        headers {
            contentType('application/json')
        }
    }
```curl --header "Accept:application/json" http://localhost:8411/api/v1/appointments```

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
    
 ```curl --header "Accept:application/json" http://localhost:8411/api/v1/doctors/1/appointments```
 
 5) **as a customer I want to get the next appointment:**
 - ``` request {
        method "GET"
        url "/api/v1/clients/1/appointments/next"
        headers {
            contentType('application/json')
        }
    }
    
```curl --header "Accept:application/json" http://localhost:8411/api/v1/clients/1/appointments/next```
 
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
```curl -i -X PUT -H "Content-Type:application/json" http://localhost:8888/demo-rest-jersey-spring/podcasts/2 -d '{"rating":"FIVE"}```  
    
    
    **Minuses** application doesn't has any validation
 
