package com.example.projektpp.controllers;

import com.example.projektpp.Repos.Category_repo;
import com.example.projektpp.models.Category;
import jakarta.mail.SendFailedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DodajKategorieConroller {
    @Autowired
    private Category_repo categoryRepo;
    @GetMapping("/dodaniekategori")
    public String dodaniekategori(Model model) {
        Category category = new Category();
        model.addAttribute("kategoriaadd", category);
        return "dodajkategorie";
    }

    @PostMapping("kategoriaadd")
    public String kategoriaadd(Model model, @Valid @ModelAttribute(value = "kategoriaadd") Category category) throws SendFailedException {
        categoryRepo.save(category);
        return "dodajkategorie";
    }
}
