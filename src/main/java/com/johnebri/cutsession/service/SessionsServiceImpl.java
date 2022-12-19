package com.johnebri.cutsession.service;

import com.johnebri.cutsession.dao.StudioSessionDao;
import com.johnebri.cutsession.dto.sessions.CreateStudioSessionRequest;
import com.johnebri.cutsession.dto.sessions.CreateStudioSessionResponse;
import com.johnebri.cutsession.model.StudioSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ChoiceFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.UUID;

/**
 * @author John on 12/6/22
 */

@Service
@Slf4j
public class SessionsServiceImpl implements SessionsService{

    private final StudioSessionDao studioSessionDao;

    public SessionsServiceImpl(StudioSessionDao studioSessionDao) {
        this.studioSessionDao = studioSessionDao;
    }

    @Override
    public Object createStudioSession(String merchantId, CreateStudioSessionRequest request) throws Exception {

        // check if merchant exists

        // check time interval between start and end time, should be 45, 60 or 90 mins
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Instant startsAt = null;
        Instant endsAt = null;
        Instant weekDayStartsAt = null;
        Instant weekDayEndsAt = null;
        Instant weekEndStartsAt = null;
        Instant weekendEndsAt = null;

        try {
            startsAt = sdf.parse(request.getStartsAt()).toInstant();
            endsAt = sdf.parse(request.getEndsAt()).toInstant();
            weekDayStartsAt = sdf.parse("09:00:00Z").toInstant();
            weekDayEndsAt = sdf.parse("20:00:00Z").toInstant();
            weekEndStartsAt = sdf.parse("10:00:00Z").toInstant();
            weekendEndsAt = sdf.parse("22:00:00Z").toInstant();
        } catch (ParseException e) {
            throw new Exception("Incorrect time format. format should be hh:mm:ssz in 24 hours time");
        }

        long minsDiff = ChronoUnit.MINUTES.between(startsAt, endsAt);

        if(minsDiff != 45 && minsDiff != 60 && minsDiff != 90) {
            throw new Exception("Session intervals should be 45, 60 or 90 minutes");
        }

        // check if weekday or weekend session,
        // weekday should be 9am to 8pm, monday - friday
        // weekend should be 10am to 10pm, saturday
        if(request.getType().equalsIgnoreCase("WeekDay")) {
            // weekday session, check that startsAt is equal or greater than 9am and endsAt is less than or equals 8pm
            long diff = ChronoUnit.SECONDS.between(weekDayStartsAt, startsAt);
            if(diff < 0) {
                throw new Exception("Start time must be greater than or equal to 09:00AM on weekdays");
            }

            // weekday endsAt should be before 8pm
            long diff2 = ChronoUnit.SECONDS.between(weekDayEndsAt, endsAt);
            if(diff2 > 0) {
                throw new Exception("End time must be less than or equal to 08:00PM on weekdays");
            }

        } else if(request.getType().equalsIgnoreCase("WeekEnd")) {
            // weekend session, check that startsAt is equal or greater than 10am and endsAt is less than or equals 10pm
            long diff = ChronoUnit.SECONDS.between(weekEndStartsAt, startsAt);
            if(diff < 0) {
                throw new Exception("Start time must be greater than 10:00AM on weekends");
            }

            // weekend ends at should be less than or equals 10PM
            long diff2 = ChronoUnit.SECONDS.between(weekendEndsAt, endsAt);
            if(diff2 > 0) {
                throw new Exception("End time must be less than or equal to 10:00PM on weekends");
            }
        } else {
            // unknown session type
            throw new Exception("Unknown session type");
        }

        // generate user id
        String sessionId = UUID.randomUUID().toString();

        StudioSession studioSession = StudioSession.builder()
                .id(sessionId)
                .merchantId(merchantId)
                .startsAt(request.getStartsAt())
                .endsAt(request.getEndsAt())
                .type(request.getType())
                .build();
        studioSessionDao.create(studioSession);

        CreateStudioSessionResponse response = CreateStudioSessionResponse.builder()
                .sessionId(sessionId)
                .build();
        return response;
    }

    @Override
    public Object fetchStudioSessions(String merchantId) {
        return studioSessionDao.findByMerchantId(merchantId);
    }
}
