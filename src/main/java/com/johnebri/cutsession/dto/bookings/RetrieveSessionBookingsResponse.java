package com.johnebri.cutsession.dto.bookings;

import com.johnebri.cutsession.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author John on 12/26/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveSessionBookingsResponse {
    private int count;
    private String next;
    private String previous;
    private List<Booking> data;
}
