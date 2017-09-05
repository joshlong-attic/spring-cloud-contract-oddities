package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@SpringBootTest(classes = ReservationServiceApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureJson
public class ReservationRestControllerTest {

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllReservations() throws Exception {

        Mockito.when(this.reservationRepository.findAll())
                .thenReturn(Arrays.asList(new Reservation(1L, "Foo"), new Reservation(2L, "Bar")));

        this.mockMvc
                .perform(get("/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("@.[0].id").value(1))
                .andExpect(jsonPath("@.[0].reservationName").value("Foo"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
        ;
    }
}