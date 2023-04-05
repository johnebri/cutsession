package com.johnebri.cutsession.service.bookingref;

import com.johnebri.cutsession.dto.bookings.CreateBookingRequest;

import java.util.Random;

/**
 * @author John on 12/20/22
 */
public class DerivedStrategy implements BookingRefStrategy {
    @Override
    public String generateBookingRef(CreateBookingRequest data) {

        String SALTCHARS = data.getDate() + data.getSessionId();
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
