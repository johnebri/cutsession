package com.johnebri.cutsession.dto.bookings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author John on 12/7/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookingRequest {
    @NotBlank(message = "Session ID is required")
    private String sessionId;

    @NotBlank(message = "Date is required")
    private String date;

    @NotBlank(message = "User ID is required")
    private String userId;

    private String notes;
    private String title;
}
