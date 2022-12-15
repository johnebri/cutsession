package com.johnebri.cutsession.dto.sessions;

import com.johnebri.cutsession.model.enums.StudioSessionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/6/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudioSessionRequest {
    private String startsAt;
    private String endsAt;
    private String type;
}
