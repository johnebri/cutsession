package com.johnebri.cutsession.dto.sessions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/18/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudioSessionResponse {
    private String sessionId;
}
