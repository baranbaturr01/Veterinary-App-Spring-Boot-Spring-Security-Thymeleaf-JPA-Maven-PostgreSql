package com.baranbatur.vetApp.service;

public interface ISecurityService {
    boolean isAuthenticated();

    void autologin(String username, String password);
}
