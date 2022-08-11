package com.baranbatur.vetApp.interfaces;

import com.baranbatur.vetApp.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}