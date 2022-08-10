package com.baranbatur.vetApp.interfaces;

public interface ISecurityService {
    boolean isAuthenticated();

    void autologin(String username, String password);
}
