package com.johnebri.cutsession.service;

import com.johnebri.cutsession.dao.StudioSessionDao;
import com.johnebri.cutsession.dto.sessions.CreateStudioSessionRequest;
import com.johnebri.cutsession.model.StudioSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
    public Object createStudioSession(String merchantId, CreateStudioSessionRequest request) {

        // check if merchant exists


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
        return sessionId;
    }

    @Override
    public Object fetchStudioSessions(String merchantId) {
        return studioSessionDao.findByMerchantId(merchantId);
    }
}
