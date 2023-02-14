package com.example.projektpp.controllers;


import com.example.projektpp.Repos.Category_repo;

import com.example.projektpp.Repos.Medicine_repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KategoriaController {
    @Autowired
    private Category_repo categoryRepo;

    @Autowired
    private Medicine_repo medicine_repo;

    @GetMapping({"/listacategory"})
    public ModelAndView listacategory() {
        ModelAndView mav = new ModelAndView("list-kategorie");
        mav.addObject("categories", categoryRepo.getCategory());
        return mav;
    }

    @GetMapping("/akceptujM/{id}")
    public ModelAndView akceptujM(@PathVariable(value = "id") Integer id, Model model) {

        ModelAndView mav = new ModelAndView("znalezioneleki");
        mav.addObject("medicines", medicine_repo.getMedicineByCategory(id));
        return mav;
    }




}
