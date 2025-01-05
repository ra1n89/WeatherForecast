package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.service.UserValidationService;

@Controller
public class LoginController {

    @Autowired
    UserValidationService userValidationService;

    @GetMapping("login")
    String registration(){
        return "login/login";
    }

    @PostMapping("login")
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
        return "login/login";
    }
}
