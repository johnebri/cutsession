package com.johnebri.cutsession.controller;

import com.johnebri.cutsession.dto.sessions.CreateStudioSessionRequest;
import com.johnebri.cutsession.service.SessionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author John on 12/26/22
 */

@ExtendWith(MockitoExtension.class)
public class SessionControllerTest {

    @Mock
    SessionsService sessionsService;

    @InjectMocks
    SessionsController sessionsController;

    @Test
    void createStudioSessionTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String merchantId = "merchant-id";
        CreateStudioSessionRequest createStudioSessionRequest = CreateStudioSessionRequest.builder()
                .startsAt("2022-12-27")
                .endsAt("2022-12-27")
                .type("weekDay")
                .build();
        when(sessionsService.createStudioSession(merchantId, createStudioSessionRequest)).thenReturn(new Object());
        ResponseEntity<Object> responseEntity = sessionsController.createStudioSession(merchantId, createStudioSessionRequest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void fetchStudioSessionTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String merchantId = "merchant-id";
        when(sessionsService.fetchStudioSessions(merchantId)).thenReturn(new Object());
        ResponseEntity<Object> responseEntity = sessionsController.fetchStudioSessions(merchantId);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
