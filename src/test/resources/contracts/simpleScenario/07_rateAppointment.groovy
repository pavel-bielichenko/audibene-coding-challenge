package simpleScenario
//noinspection GrPackage
org.springframework.cloud.contract.spec.Contract.make {
    request {
        method "PUT"
        url "/api/v1/clients/1/appointments/1/rate"
        headers {
            contentType('application/json')
        }
        body([
                rating: "FIVE"
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
                doctorId: 1,
                rating  : "FIVE"
        ])
    }
}