package com.johnebri.cutsession.controller;

import com.johnebri.cutsession.dto.clients.GetClientRequest;
import com.johnebri.cutsession.dto.register.RegisterMerchantRequest;
import com.johnebri.cutsession.dto.register.RegisterMerchantResponse;
import com.johnebri.cutsession.dto.register.RegisterUserRequest;
import com.johnebri.cutsession.dto.register.RegisterUserResponse;
import com.johnebri.cutsession.dto.sessions.CreateStudioSessionRequest;
import com.johnebri.cutsession.dto.signin.SigninRequest;
import com.johnebri.cutsession.dto.signin.SigninResponse;
import com.johnebri.cutsession.model.User;
import com.johnebri.cutsession.model.enums.UserTypeEnum;
import com.johnebri.cutsession.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/**
 * @author John on 12/26/22
 */

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void registerUsersTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        RegisterUserRequest registerUserRequest = RegisterUserRequest.builder()
                .name("John Ebri")
                .dob("1991-12-24")
                .email("john.ebri@gmail.com")
                .cityOfResidence("Lagos")
                .username("johnebri")
                .password("12345678")
                .phoneNumber("07030601520")
                .build();
        RegisterUserResponse registerUserResponse = RegisterUserResponse.builder()
                .userId("user-id")
                .build();
        when(userService.register(registerUserRequest)).thenReturn(registerUserResponse);
        ResponseEntity<RegisterUserResponse> responseEntity = userController.registerUsers(registerUserRequest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void registerMerchantsTest () {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        RegisterMerchantRequest registerMerchantRequest = RegisterMerchantRequest.builder()
                .name("John Ebri")
                .email("john.ebri@gmail.com")
                .cityOfOperation("Lagos")
                .username("johnebri")
                .password("12345678")
                .phoneNumber("07030601520")
                .build();
        RegisterMerchantResponse registerMerchantResponse = RegisterMerchantResponse.builder()
                .merchantId("merchant-id")
                .build();
        when(userService.registerMerchant(registerMerchantRequest)).thenReturn(registerMerchantResponse);
        ResponseEntity<RegisterMerchantResponse> responseEntity = userController.registerMerchants(registerMerchantRequest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void signInTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        SigninRequest signinRequest = SigninRequest.builder()
                .username("johnebri")
                .password("11111111")
                .accessType(UserTypeEnum.USER)
                .build();
        SigninResponse signinResponse = SigninResponse.builder()
                .token("token")
                .userId("user-id")
                .merchantId("merchant-id")
                .build();
        when(userService.signin(signinRequest)).thenReturn(signinResponse);
        ResponseEntity<SigninResponse> responseEntity = userController.signIn(signinRequest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void clientTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        GetClientRequest getClientRequest = GetClientRequest.builder()
                .limit(20)
                .offset(0)
                .type(UserTypeEnum.USER)
                .city("lagos")
                .name("wisdom")
                .build();

        int limit = 50;
        int offset = 0;
        UserTypeEnum type = UserTypeEnum.USER;
        String city = "Lagos";
        String name = "John";

        lenient().when(userService.getClients(getClientRequest)).thenReturn(new Object());
        ResponseEntity<Object> responseEntity = userController.clients(limit, offset, type, city, name);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
