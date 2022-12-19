package com.johnebri.cutsession.dao;

import com.johnebri.cutsession.dto.bookings.RetrieveSessionBookingsRequest;
import com.johnebri.cutsession.model.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author John on 12/7/22
 */
@Slf4j
@Component
public class BookingDao implements DAO<Booking>{

    private JdbcTemplate jdbcTemplate;

    public BookingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Booking> rowMapper = (rs, rowNum) -> {
        Booking booking = new Booking();
        booking.setBookingId(rs.getString("booking_id"));
        booking.setBookingRef(rs.getString("booking_ref"));
        booking.setUserId(rs.getString("user_id"));
        booking.setSessionId(rs.getString("session_id"));
        booking.setDate(rs.getString("date"));
        booking.setStartsAt(rs.getString("starts_at"));
        booking.setEndsAt(rs.getString("ends_at"));
        booking.setNotes(rs.getString("notes"));
        booking.setTitle(rs.getString("title"));
        return booking;
    };


    @Override
    public List<Booking> list() {
        return null;
    }

    @Override
    public void create(Booking b) {
        String sql = "INSERT INTO bookings (booking_id, booking_ref, user_id, session_id, date, starts_at, ends_at, notes, title) VALUES (?,?,?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql, b.getBookingId(), b.getBookingRef(), b.getUserId(), b.getSessionId(), b.getDate(), b.getStartsAt(), b.getEndsAt(), b.getNotes(), b.getTitle());
        if(insert == 1) {
            log.info("Booking created successfully");
        }
    }

    @Override
    public Optional<Booking> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Booking booking, int id) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Booking> retrieveSessionBookings(RetrieveSessionBookingsRequest request) {

        StringBuilder sb = new StringBuilder();
        List<Object> paramsList = new ArrayList<>();

        sb.append("SELECT b.booking_id, b.booking_ref, b.user_id, b.session_id, b.date, b.starts_at, b.ends_at, b.notes, b.title, b.notes " +
                        "FROM bookings b " +
                        "INNER JOIN studio_sessions ss " +
                        "ON b.session_id = ss.id " +
                        "INNER JOIN users u " +
                        "ON ss.merchant_id = u.id");

        sb.append("  WHERE u.city = ?");
        paramsList.add(request.getCity());

        if(request.getMerchant() != null) {
            sb.append(" AND ss.merchant_id = ?");
            paramsList.add(request.getMerchant());
        }

        if(request.getPeriod() != null) {
            // split period into start and end dates
            String[] dates = request.getPeriod().split(":");
            String startDate = dates[0];
            String endDate = dates[1];

            sb.append(" AND b.date >= ? AND b.date <= ?");
            paramsList.add(startDate);
            paramsList.add(endDate);
        }

        sb.append(" LIMIT ?");
        paramsList.add(request.getLimit());

        String sql = sb.toString();

        Object[] params = new Object[paramsList.size()];
        for(int x = 0; x<paramsList.size(); x++) {
            params[x] = paramsList.get(x);
        }

        List<Booking> bookings = jdbcTemplate.query(sql, params, rowMapper);
        return bookings;
    }

    public Optional<Booking> checkForDuplicateBooking(String sessionId, String date) {
        String sql = "SELECT * FROM bookings WHERE session_id = ? AND date = ?";
        Booking booking = null;
        try {
            booking = jdbcTemplate.queryForObject(sql, new Object[]{sessionId, date}, rowMapper);
        } catch (DataAccessException ex) {
            log.error("an error occurred: " + ex.getMessage());
        }
        return Optional.ofNullable(booking);
    }
}
