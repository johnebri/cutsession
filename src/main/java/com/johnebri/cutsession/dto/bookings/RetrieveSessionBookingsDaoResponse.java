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
public class RetrieveSessionBookingsDaoResponse {
    private List<Booking> filteredResponse;
    private List<Booking> totalResponse;
}
