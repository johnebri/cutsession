package com.johnebri.cutsession.service;

import com.johnebri.cutsession.dao.BookingDao;
import com.johnebri.cutsession.dao.StudioSessionDao;
import com.johnebri.cutsession.dao.UserDao;
import com.johnebri.cutsession.dto.bookings.*;
import com.johnebri.cutsession.exception.DuplicateResourceException;
import com.johnebri.cutsession.exception.ResourceNotFoundException;
import com.johnebri.cutsession.model.Booking;
import com.johnebri.cutsession.model.StudioSession;
import com.johnebri.cutsession.model.User;
import com.johnebri.cutsession.service.bookingref.BookingRefStrategy;
import com.johnebri.cutsession.service.bookingref.DerivedStrategy;
import org.springframework.beans.factory.annotation.Value;
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
    private final UserDao userDao;

    @Value("${base-url}")
    private String baseUrl;

    public BookingServiceImpl(BookingDao bookingDao, StudioSessionDao studioSessionDao, UserDao userDao) {
        this.bookingDao = bookingDao;
        this.studioSessionDao = studioSessionDao;
        this.userDao = userDao;
    }

    @Override
    public CreateBookingResponse createBooking(CreateBookingRequest request) throws Exception {

        // check if session exists and get the session
        Optional<StudioSession> optionalStudioSession = studioSessionDao.findBySessionId(request.getSessionId());
        if(!optionalStudioSession.isPresent()) {
            throw new ResourceNotFoundException("Studio session not found");
        }

        // check if user id exists
        Optional<User> optionalUser = userDao.findUserByUserId(request.getUserId());
        if(!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User not found : " + request.getUserId());
        }

        // check if session is already booked
        // check if session id and date exists in bookings
        Optional<Booking> optionalBooking = bookingDao.checkForDuplicateBooking(request.getSessionId(), request.getDate());
        if(optionalBooking.isPresent()) {
            throw new DuplicateResourceException("Session is already booked");
        }

        StudioSession studioSesion = optionalStudioSession.get();

        // generate booking id
        String bookingId = UUID.randomUUID().toString();

        // generate booking ref
        String bookingRef = generateBookingRef(new DerivedStrategy(), request);


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

    private String generateBookingRef(BookingRefStrategy strategy, CreateBookingRequest data) {
        return strategy.generateBookingRef(data);
    }

    @Override
    public Object retrieveSessionBookings(RetrieveSessionBookingsRequest request) {

        int limit = request.getLimit();
        int offset = request.getOffset();

        int previousOffset = offset - limit;
        int nextOffset = offset + limit;

        String next = null;
        String previous = null;

        RetrieveSessionBookingsDaoResponse daoResponse = bookingDao.retrieveSessionBookings(request);

        int totalCount = daoResponse.getTotalResponse().size();
        int filteredCount = daoResponse.getFilteredResponse().size();

        boolean prevBool = true;
        boolean nextBool = true;
        if(nextOffset >= totalCount){
            nextBool = false;
        }

        if(previousOffset < 0) {
            prevBool = false;
        }

        // check for params
        String city = request.getCity();

        String merchantParam = "";
        if(request.getMerchant() != null) {
            merchantParam = "&merchant="+request.getMerchant();
        }

        String periodParam = "";
        if(request.getPeriod() != null) {
            periodParam = "&period="+request.getPeriod();
        }

        if(nextBool == true)
            next = baseUrl + "/bookings?city="+city+"&limit="+limit+"&offset="+nextOffset+merchantParam+periodParam;

        if(prevBool == true)
            previous = baseUrl + "/bookings?city="+city+"&limit="+limit+"&offset="+previousOffset+merchantParam+periodParam;

        RetrieveSessionBookingsResponse response = RetrieveSessionBookingsResponse.builder()
                .count(daoResponse.getTotalResponse().size())
                .next(next)
                .previous(previous)
                .data(daoResponse.getFilteredResponse())
                .build();
        return response;
    }
}
