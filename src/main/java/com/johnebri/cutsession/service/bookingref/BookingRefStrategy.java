package com.johnebri.cutsession.service.bookingref;

import com.johnebri.cutsession.dto.bookings.CreateBookingRequest;

/**
 * @author John on 12/20/22
 */
public interface BookingRefStrategy {
    public String generateBookingRef(CreateBookingRequest data);
}
