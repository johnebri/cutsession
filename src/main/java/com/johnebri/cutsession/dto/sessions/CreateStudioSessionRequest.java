package com.johnebri.cutsession.dto.sessions;

import com.johnebri.cutsession.model.enums.StudioSessionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author John on 12/6/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudioSessionRequest {
    @NotBlank(message = "Start time is required")
    private String startsAt;

    @NotBlank(message = "End time is required")
    private String endsAt;

    @NotBlank(message = "Type is required")
    private String type;
}
