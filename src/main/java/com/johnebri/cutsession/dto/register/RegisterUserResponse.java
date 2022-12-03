package com.johnebri.cutsession.dto.register;

import lombok.Builder;
import lombok.Data;

/**
 * @author John on 12/1/22
 */

@Data
@Builder
public class RegisterUserResponse {
    private String userId;
}
