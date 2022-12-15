package com.johnebri.cutsession.service;

import com.johnebri.cutsession.dao.BookingDao;
import com.johnebri.cutsession.dao.StudioSessionDao;
import com.johnebri.cutsession.dto.bookings.CreateBookingRequest;
import com.johnebri.cutsession.dto.bookings.CreateBookingResponse;
import com.johnebri.cutsession.dto.bookings.RetrieveSessionBookingsRequest;
import com.johnebri.cutsession.model.Booking;
import com.johnebri.cutsession.model.StudioSession;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author John on 12/7/22
 */
@Service
public class BookingServiceImpl implements BookingService{

    private final BookingDao bookingDao;
    private final StudioSessionDao studioSessionDao;

    public BookingServiceImpl(BookingDao bookingDao, StudioSessionDao studioSessionDao) {
        this.bookingDao = bookingDao;
        this.studioSessionDao = studioSessionDao;
    }

    @Override
    public CreateBookingResponse createBooking(CreateBookingRequest request) throws Exception {

        // check if session exists and get the session
        Optional<StudioSession> optionalStudioSession = studioSessionDao.findBySessionId(request.getSessionId());

        System.out.println(optionalStudioSession.get());

        if(!optionalStudioSession.isPresent()) {
            throw new Exception("Studio session not found");
        }

        StudioSession studioSesion = optionalStudioSession.get();

        // generate booking id
        String bookingId = UUID.randomUUID().toString();

        // generate booking ref
        String bookingRef = RandomStringUtils.random(9, true, true);

        Booking booking = Booking.builder()
                .bookingId(bookingId)
                .bookingRef(bookingRef)
                .userId(request.getUserId())
                .sessionId(request.getSessionId())
                .date(request.getDate())
                .startsAt(studioSesion.getStartsAt())
                .endsAt(studioSesion.getEndsAt())
                .notes(request.getNotes())
                .title(request.getTitle())
                .build();
        bookingDao.create(booking);

        return CreateBookingResponse.builder()
                .bookingId(bookingId)
                .bookingRef(bookingRef)
                .build();
    }

    @Override
    public Object retrieveSessionBookings(RetrieveSessionBookingsRequest request) {
        return bookingDao.retrieveSessionBookings(request);
    }
}
