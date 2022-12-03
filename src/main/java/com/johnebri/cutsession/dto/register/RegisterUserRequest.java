package com.johnebri.cutsession.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 11/29/22
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterUserRequest {
    private String name;
    private String dob;
    private String email;
    private String cityOfResidence;
    private String username;
    private String password;
    private String phoneNumber;
}
