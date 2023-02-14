package com.example.projektpp.controllers;

import com.example.projektpp.Repos.User_repo;
import com.example.projektpp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmationController {

    @Autowired
    private User_repo user_repo;

    @GetMapping("/confirm")
    public String greeting(@RequestParam(value="id", required=true) String confirmationId, Model model) {

        User user = user_repo.getUserByConfirmationId(confirmationId);
        String message = "Invalid confirmation id. Contact us or try again.";
        if(user!=null){
            if(!user.isActive()){
                user.setActive(true);
                user.setConfirmationId(null);
                user_repo.save(user);
            }
            message = user.getUsername() + ", your account has been verified. You may now log in. ";
        }

        model.addAttribute("message", message);
        return "message";
    }
}
