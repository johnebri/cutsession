package com.johnebri.cutsession.controller;

import com.johnebri.cutsession.dto.bookings.CreateBookingRequest;
import com.johnebri.cutsession.dto.bookings.CreateBookingResponse;
import com.johnebri.cutsession.dto.bookings.RetrieveSessionBookingsRequest;
import com.johnebri.cutsession.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author John on 12/26/22
 */

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    @Mock
    BookingService bookingService;

    @InjectMocks
    BookingController bookingController;

    @Test
    void bookSessionTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        CreateBookingRequest createBookingRequest = CreateBookingRequest.builder()
                .sessionId("sessionId")
                .date("2022-12-27")
                .userId("user-id")
                .notes("Test notes")
                .title("Test title")
                .build();

        CreateBookingResponse response = CreateBookingResponse.builder()
                .bookingId("booking-id")
                .bookingRef("booking-ref")
                .build();
        when(bookingService.createBooking(createBookingRequest)).thenReturn(response);
        ResponseEntity<CreateBookingResponse> responseEntity = bookingController.bookSession(createBookingRequest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void retrieveSessionBookingsTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        RetrieveSessionBookingsRequest retrieveSessionBookingsRequest = RetrieveSessionBookingsRequest.builder()
                .city("calabar")
                .limit(50)
                .offset(0)
                .merchant("merchant-id")
                .period("2020-12-01:2023-01-01")
                .build();

        String city = "calabar";
        int limit = 50;
        int offset = 0;
        String merchant = "merchant-id";
        String period = "2020-12-01:2023-01-01";

        when(bookingService.retrieveSessionBookings(retrieveSessionBookingsRequest)).thenReturn(new Object());
        ResponseEntity<Object> responseEntity = bookingController.retrieveSessionBookings(city, limit, offset, merchant, period);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
