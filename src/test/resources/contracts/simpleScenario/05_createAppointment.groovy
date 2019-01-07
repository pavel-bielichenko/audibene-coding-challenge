package simpleScenario
//noinspection GrPackage
org.springframework.cloud.contract.spec.Contract.make {
    request {
        method "POST"
        url "/api/v1/appointments"
        headers {
            contentType('application/json')
        }
        body([
                clientId: 1,
                doctorId: 1,
                startsAt: "2019-01-01T00:00:00Z",
                endsAt  : "2019-01-01T00:00:00Z"
        ])
    }
    response {
        status 200
        headers {
            header 'Content-Type': 'application/json'
        }
        body([
                id      : 1,
                clientId: 1,
                doctorId: 1
        ])
    }
}