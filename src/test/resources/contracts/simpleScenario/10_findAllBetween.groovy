package simpleScenario
//noinspection GrPackage
org.springframework.cloud.contract.spec.Contract.make {
    request {
        method "GET"
        urlPath("/api/v1/doctors/1/appointments") {
            queryParameters {
                parameter from: "2019-06-01T00:00:00Z"
                parameter to: "2019-11-01T00:00:00Z"
            }
        }
        headers {
            contentType('application/json')
        }
    }
    response {
        status 200
        headers {
            header 'Content-Type': 'application/json'
        }
        body([
                ["id"      : 2,
                 "clientId": 1,
                 "doctorId": 1,
                 "startsAt": "2019-10-01T02:00:00+02:00",
                 "endsAt"  : "2019-10-01T02:00:00+02:00",
                 "rating"  : null]
        ])
    }
}