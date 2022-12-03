package com.johnebri.cutsession.service;

import com.johnebri.cutsession.dto.clients.GetClientRequest;
import com.johnebri.cutsession.dto.register.RegisterMerchantRequest;
import com.johnebri.cutsession.dto.register.RegisterMerchantResponse;
import com.johnebri.cutsession.dto.register.RegisterUserRequest;
import com.johnebri.cutsession.dto.register.RegisterUserResponse;
import com.johnebri.cutsession.dto.signin.SigninRequest;
import com.johnebri.cutsession.dto.signin.SigninResponse;

/**
 * @author John on 12/1/22
 */
public interface UserService {

    RegisterUserResponse register(RegisterUserRequest request);
    RegisterMerchantResponse registerMerchant(RegisterMerchantRequest request);
    SigninResponse signin(SigninRequest request) throws Exception;
    Object getClients(GetClientRequest request);



}
