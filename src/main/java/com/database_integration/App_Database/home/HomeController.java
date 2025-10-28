package com.database_integration.App_Database.home;

import com.database_integration.App_Database.user.IUserService;
import com.database_integration.App_Database.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")

public class HomeController {
    @GetMapping
    public String homePage(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/error")
    public String error(){
        return "error";
    }

}
