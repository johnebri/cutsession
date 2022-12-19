package com.johnebri.cutsession.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author John on 11/29/22
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterUserRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name should be between 2 to 255 characters")
    private String name;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Email must be less than or equal 50 characters")
    private String email;

    @NotBlank(message = "City of residence is required")
    @Size(max = 20, message = "City of residence must be less than or equal 20 characters")
    private String cityOfResidence;

    @NotBlank(message = "Username is required")
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

//    @Size(min = 20, message = "Password must have at least 20 characters")
    private String phoneNumber;
}
