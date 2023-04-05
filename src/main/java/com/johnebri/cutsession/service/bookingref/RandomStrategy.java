package com.johnebri.cutsession.service.bookingref;

import com.johnebri.cutsession.dto.bookings.CreateBookingRequest;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author John on 12/20/22
 */
public class RandomStrategy implements BookingRefStrategy{
    @Override
    public String generateBookingRef(CreateBookingRequest data) {
        return RandomStringUtils.random(9, true, true);
    }
}
