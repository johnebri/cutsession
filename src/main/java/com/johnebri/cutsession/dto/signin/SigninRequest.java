package com.johnebri.cutsession.dto.signin;

import com.johnebri.cutsession.model.enums.UserTypeEnum;
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
public class SigninRequest {
    private String username;
    private String password;
    private UserTypeEnum accessType;
}
