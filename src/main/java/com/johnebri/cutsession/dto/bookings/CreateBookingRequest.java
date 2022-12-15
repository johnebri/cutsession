package com.johnebri.cutsession.dto.bookings;

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
public class CreateBookingRequest {
    private String sessionId;
    private String date;
    private String userId;
    private String notes;
    private String title;
}
