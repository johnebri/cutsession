package com.johnebri.cutsession.service;

import com.johnebri.cutsession.dto.bookings.CreateBookingRequest;
import com.johnebri.cutsession.dto.bookings.CreateBookingResponse;
import com.johnebri.cutsession.dto.bookings.RetrieveSessionBookingsRequest;

/**
 * @author John on 12/7/22
 */
public interface BookingService {
    CreateBookingResponse createBooking(CreateBookingRequest request) throws Exception;
    Object retrieveSessionBookings(RetrieveSessionBookingsRequest request);
}
