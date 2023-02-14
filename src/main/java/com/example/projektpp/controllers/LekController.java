package com.example.projektpp.controllers;


import com.example.projektpp.Repos.Medicine_repo;
import com.example.projektpp.Repos.User_repo;
import com.example.projektpp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LekController {
    @Autowired
    private Medicine_repo medicine_repo;


    @GetMapping({"/listamedicines"})
    public ModelAndView listamedicines() {
        ModelAndView mav = new ModelAndView("list-leki");
        mav.addObject("medicines", medicine_repo.findAll());
        return mav;
    }




}