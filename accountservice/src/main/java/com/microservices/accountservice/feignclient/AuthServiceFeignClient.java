package com.microservices.accountservice.feignclient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.accountservice.entity.UserDto;
import com.microservices.accountservice.entity.UserRegistrationDto;

@FeignClient(name = "authservice")
public interface AuthServiceFeignClient {

    @PostMapping(value = "/uaa/user")
    UserDto createUser(@RequestBody UserRegistrationDto user);

}
