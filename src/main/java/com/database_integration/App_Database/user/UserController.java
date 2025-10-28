package com.database_integration.App_Database.user;

//Import ch.qos.logback.core.model.Model
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public String getUser(Model model) {
        model.addAttribute("User", userService.getAllUsers());
        return "users";
    }
}
