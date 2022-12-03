package com.johnebri.cutsession.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/1/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String dob;
    private String city;
    private String phoneNumber;
    private UserTypeEnum type;
}
