package com.johnebri.cutsession.controller;

import com.johnebri.cutsession.dto.bookings.CreateBookingRequest;
import com.johnebri.cutsession.dto.bookings.CreateBookingResponse;
import com.johnebri.cutsession.dto.bookings.RetrieveSessionBookingsRequest;
import com.johnebri.cutsession.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author John on 12/7/22
 */
@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponse> bookSession(@Valid @RequestBody CreateBookingRequest request) throws Exception {
        return new ResponseEntity<>(bookingService.createBooking(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> retrieveSessionBookings(
            @RequestParam(required = true) String city,
            @RequestParam(required = true, defaultValue = "20") int limit,
            @RequestParam(required = true) int offset,
            @RequestParam(required = false) String merchant,
            @RequestParam(required = false) String period) {

        RetrieveSessionBookingsRequest request = RetrieveSessionBookingsRequest.builder()
                .city(city)
                .limit(limit)
                .offset(offset)
                .merchant(merchant)
                .period(period)
                .build();

        Object response = bookingService.retrieveSessionBookings(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
