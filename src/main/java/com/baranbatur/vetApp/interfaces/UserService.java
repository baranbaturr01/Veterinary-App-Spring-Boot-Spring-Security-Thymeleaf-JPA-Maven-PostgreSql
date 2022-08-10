package com.baranbatur.vetApp.interfaces;

import com.baranbatur.vetApp.model.User;
import com.baranbatur.vetApp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}