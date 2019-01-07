package simpleScenario
//noinspection GrPackage
org.springframework.cloud.contract.spec.Contract.make {
    request {
        method "POST"
        url "/api/v1/doctors"
        headers {
            contentType('application/json')
        }
        body([
                name: "Joker"
        ])
    }
    response {
        status 200
        headers {
            header 'Content-Type': 'application/json'
        }
        body([
                id  : 1,
                name: "Joker"
        ])
    }
}