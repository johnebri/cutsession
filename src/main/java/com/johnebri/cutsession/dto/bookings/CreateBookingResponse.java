package com.johnebri.cutsession.dto.bookings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookingResponse {
    private String bookingId;
    private String bookingRef;
}
