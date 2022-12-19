package com.johnebri.cutsession.dto.signin;

import com.johnebri.cutsession.model.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author John on 12/2/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SigninRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Access type is required")
    private UserTypeEnum accessType;
}
