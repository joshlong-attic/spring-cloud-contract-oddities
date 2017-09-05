package com.example.reservationclient;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReservationClientConfiguration.class)
@AutoConfigureStubRunner(workOffline = true, ids = "com.example:reservation-service:+:8081")
@AutoConfigureMockMvc
@AutoConfigureJson
//@AutoConfigureWireMock(port = 8081)
public class ReservationClientApplicationTests {

    @Autowired
    private ReservationClient client;

    @Test
    public void testClientShouldReturnAllReservations() throws Exception {
        Collection<Reservation> collection = this.client.read();
        BDDAssertions.then(collection).hasSize(2);
        Reservation next = collection.iterator().next();
        BDDAssertions.then(next.getReservationName()).isEqualToIgnoringCase("foo");
        BDDAssertions.then(next.getId()).isEqualTo(1L);
    }
/*

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testClientShouldReturnAllReservations() throws Exception {

        String json = this.objectMapper.writeValueAsString(Arrays.asList(new Reservation(1L, "Foo"),
                new Reservation(2L, "Bar")));

        WireMock.stubFor(
                WireMock.get(WireMock.urlMatching("/reservations"))
                        .willReturn(WireMock.aResponse()
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .withStatus(200)
                                .withBody(json)));

        Collection<Reservation> collection = this.client.read();
        BDDAssertions.then(collection).hasSize(2);
        Reservation next = collection.iterator().next();
        BDDAssertions.then(next.getReservationName()).isEqualToIgnoringCase("foo");
        BDDAssertions.then(next.getId()).isEqualTo(1L);

    }
*/

}
