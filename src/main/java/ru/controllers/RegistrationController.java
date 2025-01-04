package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.service.UserValidationService;

@Controller
public class RegistrationController {

    @Autowired
    UserValidationService userValidationService;

    @GetMapping("register")
    String registration(){
        return "registration/register";
    }

    @PostMapping("register")
    String verification(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            Model model
            ){
        System.out.println(name + password);
        if(userValidationService.isUserValid(name, password)){
            return "forecast";
        }
        model.addAttribute("errorMessage", "Invalid user");


//        if(){
//            //TODO: создать в базе данных
//        } else {
//
//        }
        return "registration/register";
    }
}
