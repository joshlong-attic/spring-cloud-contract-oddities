import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description "should return all Reservations"
    request {
        method GET()
        url "/reservations"
    }
    response {
        status 200
        body([[id: 1, reservationName: "Foo"], [id: 2, reservationName: "Bar"]])
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
    }
}