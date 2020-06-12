package com.microservices.accountservice.service;

import com.microservices.accountservice.entity.UserDto;
import com.microservices.accountservice.entity.UserRegistrationDto;

public interface AccountService {

    /**
     * Invokes Auth Service user creation
     *
     * @param user
     * @return created account
     */
    UserDto create(UserRegistrationDto user);
}