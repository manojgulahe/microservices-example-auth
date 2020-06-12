package com.microservices.accountservice.service.impl;

import org.springframework.stereotype.Service;

import com.microservices.accountservice.entity.UserDto;
import com.microservices.accountservice.entity.UserRegistrationDto;
import com.microservices.accountservice.feignclient.AuthServiceFeignClient;
import com.microservices.accountservice.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AuthServiceFeignClient authServiceFeignClient;

    public AccountServiceImpl(AuthServiceFeignClient authServiceFeignClient) {
        this.authServiceFeignClient = authServiceFeignClient;
    }

    @Override
    public UserDto create(UserRegistrationDto user) {
        return authServiceFeignClient.createUser(user);
    }
}