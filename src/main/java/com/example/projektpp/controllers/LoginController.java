package com.example.projektpp.controllers;

import com.example.projektpp.Repos.User_repo;
import com.example.projektpp.models.User;
import jakarta.mail.SendFailedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private User_repo userrepo;

    private User user;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


}

