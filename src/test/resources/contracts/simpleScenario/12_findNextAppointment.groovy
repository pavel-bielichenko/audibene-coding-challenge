package simpleScenario
//noinspection GrPackage
org.springframework.cloud.contract.spec.Contract.make {
    request {
        method "GET"
        url "/api/v1/clients/1/appointments/next"
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
                id      : 2,
                clientId: 1,
                doctorId: 1
        ])
    }
}