package com.microservices.service;

import com.microservices.entity.User;

public interface UserService {
    User create(User user);
}