package com.baranbatur.vetApp.controller;

import com.baranbatur.vetApp.helper.UserValidator;
import com.baranbatur.vetApp.model.Animal;
import com.baranbatur.vetApp.model.Role;
import com.baranbatur.vetApp.model.User;
import com.baranbatur.vetApp.interfaces.ISecurityService;
import com.baranbatur.vetApp.interfaces.UserService;
import com.baranbatur.vetApp.repository.IRoleRepository;
import com.baranbatur.vetApp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private ISecurityService isecurityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {

        if (isecurityService.isAuthenticated()) {
            return "redirect:/welcome";
        }

        List<Role> roles = roleRepository.findAll();
        model.addAttribute("role2s", roles);
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        System.out.println(userForm.getRoles());
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        userForm.setRoles(userForm.getRoles());
        userService.save(user);
        isecurityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (isecurityService.isAuthenticated()) {
            return "redirect:/welcome";
        }

        if (error != null) model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null) model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }


    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "redirect:/add-animal";
    }
}
