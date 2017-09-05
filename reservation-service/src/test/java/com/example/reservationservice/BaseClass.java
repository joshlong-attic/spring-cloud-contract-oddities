package com.example.reservationservice;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */

@SpringBootTest(classes = ReservationServiceApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureJson
public class BaseClass {

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationRestController controller;

    @Before
    public void before() {

        RestAssuredMockMvc.standaloneSetup(this.controller);

        Mockito.when(this.reservationRepository.findAll())
                .thenReturn(Arrays.asList(new Reservation(1L, "Foo"),
                        new Reservation(2L, "Bar")));


    }
}
