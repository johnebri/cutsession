package com.johnebri.cutsession.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/7/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    private String bookingId;
    private String bookingRef;
    private String userId;
    private String sessionId;
    private String date;
    private String startsAt;
    private String endsAt;
    private String notes;
    private String title;
}
