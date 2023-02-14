package com.example.projektpp.controllers;


import com.example.projektpp.Repos.Role_repo;
import com.example.projektpp.Repos.User_repo;
import com.example.projektpp.Serwisy.EmailService;
import com.example.projektpp.models.User;
import jakarta.mail.SendFailedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class RegistrationController {

    @Autowired
    private User_repo userrepo;

    @Autowired
    private Role_repo rolerepo;
    private User user;
    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationController() {
    }

    @GetMapping("/message")
    public String message()
    {
        return "message";
    }

    @GetMapping("/register")
    public String Register(Model model)
    {
        User user = new User();
        model.addAttribute("registeradd", user);
        return "register";
    }

    @PostMapping("registeradd")
    public String registeradd(Model model, @Valid @ModelAttribute(value = "registeradd") User user, BindingResult result) throws SendFailedException {
        if (result.hasErrors()) {
            System.out.println(result.getFieldErrors("password"));
            System.out.println(result.getFieldErrors("username"));
            System.out.println(result.getFieldErrors("email"));
            model.addAttribute("registeradd", user);
            return "register";
        }

        if (userrepo.findByUsername(user.getUsername()) == null && userrepo.findByEmail(user.getEmail()) == null) {
            System.out.println(userrepo.findByUsername(user.getUsername()));
            user.setConfirmationId(createConfirmationID());
            emailService.send(user.getEmail(), "Link aktywacyjny do konta na mojej stronie", "http://localhost:8080/confirm?id=" + user.getConfirmationId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(new HashSet<>(Arrays.asList(rolerepo.findById(2L).get())));
            userrepo.save(user);
        } else if (userrepo.findByUsername(user.getUsername()) != null) {
            model.addAttribute("ex", "nazwa uzytkownika jest juz zajeta");
            return "register";
        } else if (userrepo.findByEmail(user.getEmail()) != null) {
            model.addAttribute("ex", "email jest juz zajety");
            return "register";
        }
        return "/login";
    }

    private String createConfirmationID() {
        return java.util.UUID.randomUUID().toString();
    }
}
