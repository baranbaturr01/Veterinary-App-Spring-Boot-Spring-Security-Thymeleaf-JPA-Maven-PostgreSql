package com.baranbatur.vetApp.interfaces;

import com.baranbatur.vetApp.model.Role;

public interface ISecurityService {
    boolean isAuthenticated();

    void autologin(String username, String password);
}
