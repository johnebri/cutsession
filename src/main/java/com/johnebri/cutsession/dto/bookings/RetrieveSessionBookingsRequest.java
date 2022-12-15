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
public class RetrieveSessionBookingsRequest {
    private String city;
    private int limit;
    private int offset;
    private String merchant;
    private String period;
}
