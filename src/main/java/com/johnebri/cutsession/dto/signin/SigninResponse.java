package com.johnebri.cutsession.dto.signin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/2/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SigninResponse {
    private String token;
}
