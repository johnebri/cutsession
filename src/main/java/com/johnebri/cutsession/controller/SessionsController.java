package com.johnebri.cutsession.controller;

import com.johnebri.cutsession.dto.bookings.CreateBookingRequest;
import com.johnebri.cutsession.dto.sessions.CreateStudioSessionRequest;
import com.johnebri.cutsession.service.SessionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author John on 12/6/22
 */

@RestController
@RequestMapping("api/v1")
public class SessionsController {

    private final SessionsService sessionsService;

    public SessionsController(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    @PostMapping("/studios/{merchantId}")
    public ResponseEntity<Object> createStudioSession(@PathVariable String merchantId, @Valid @RequestBody CreateStudioSessionRequest request) throws Exception {
        return new ResponseEntity<>(sessionsService.createStudioSession(merchantId, request), HttpStatus.OK);
    }

    @GetMapping("/studios/{merchantId}")
    public ResponseEntity<Object> fetchStudioSessions(@PathVariable String merchantId) {
        return new ResponseEntity<>(sessionsService.fetchStudioSessions(merchantId), HttpStatus.OK);
    }
}
