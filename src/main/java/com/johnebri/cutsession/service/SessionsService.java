package com.johnebri.cutsession.service;

import com.johnebri.cutsession.dto.sessions.CreateStudioSessionRequest;

import java.text.ParseException;

/**
 * @author John on 12/6/22
 */
public interface SessionsService {
    Object createStudioSession(String merchantId, CreateStudioSessionRequest request) throws Exception;
    Object fetchStudioSessions(String merchantId);
}
