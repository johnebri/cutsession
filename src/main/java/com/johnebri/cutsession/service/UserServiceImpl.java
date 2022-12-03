package com.johnebri.cutsession.service;

import com.johnebri.cutsession.dao.UserDao;
import com.johnebri.cutsession.dto.clients.*;
import com.johnebri.cutsession.dto.register.RegisterMerchantRequest;
import com.johnebri.cutsession.dto.register.RegisterMerchantResponse;
import com.johnebri.cutsession.dto.register.RegisterUserRequest;
import com.johnebri.cutsession.dto.register.RegisterUserResponse;
import com.johnebri.cutsession.dto.signin.SigninRequest;
import com.johnebri.cutsession.dto.signin.SigninResponse;
import com.johnebri.cutsession.model.User;
import com.johnebri.cutsession.model.UserTypeEnum;
import com.johnebri.cutsession.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author John on 12/1/22
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserDao userDao, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {

        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());

        // generate user id
        String userId = UUID.randomUUID().toString();

        // validations
        User user = User.builder()
                .id(userId)
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .dob(request.getDob())
                .city(request.getCityOfResidence())
                .phoneNumber(request.getPhoneNumber())
                .type(UserTypeEnum.USER)
                .build();
        userDao.create(user);

        RegisterUserResponse registerResponse = RegisterUserResponse.builder()
                .userId(userId)
                .build();
        return registerResponse;
    }

    @Override
    public RegisterMerchantResponse registerMerchant(RegisterMerchantRequest request) {

        // generate merchant id
        String merchantId = UUID.randomUUID().toString();

        // validations
        User user = User.builder()
                .id(merchantId)
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .city(request.getCityOfOperation())
                .phoneNumber(request.getPhoneNumber())
                .type(UserTypeEnum.MERCHANT)
                .build();
        userDao.create(user);

        RegisterMerchantResponse response = RegisterMerchantResponse.builder()
                .merchantId(merchantId)
                .build();
        return response;
    }

    @Override
    public SigninResponse signin(SigninRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        } catch (Exception ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        SigninResponse response = SigninResponse.builder()
                .token(jwt)
                .build();
        return response;


    }

    @Override
    public Object getClients(GetClientRequest request) {

        List<User> users = userDao.getClients(request);

        if(request.getType().equals(UserTypeEnum.USER.toString())) {
            // users
            List<UserResponse> userResponseList = new ArrayList<>();
            for(User u : users) {
                UserResponse userResponse = UserResponse.builder()
                        .userId(u.getId())
                        .name(u.getName())
                        .email(u.getEmail())
                        .cityOfResidence(u.getCity())
                        .phoneNumber(u.getPhoneNumber())
                        .build();
                userResponseList.add(userResponse);
            }
            return UserListResponse.builder()
                    .data(userResponseList)
                    .build();
        } else {
            // merchant
            List<MerchantResponse> merchantResponseList = new ArrayList<>();
            for(User u : users) {
                MerchantResponse userResponse = MerchantResponse.builder()
                        .merchantId(u.getId())
                        .name(u.getName())
                        .email(u.getEmail())
                        .cityOfOperation(u.getCity())
                        .phoneNumber(u.getPhoneNumber())
                        .build();
                merchantResponseList.add(userResponse);
            }
            return MerchantListResponse.builder()
                    .data(merchantResponseList)
                    .build();
        }
    }

}
