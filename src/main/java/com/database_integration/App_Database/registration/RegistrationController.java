package com.database_integration.App_Database.registration;

import com.database_integration.App_Database.event.RegistrationCompleteEvent;
import com.database_integration.App_Database.registration.token.VerificationToken;
import com.database_integration.App_Database.registration.token.VerificationTokenRepository;
import com.database_integration.App_Database.registration.token.VerificationTokenService;
import com.database_integration.App_Database.user.IUserService;
import com.database_integration.App_Database.user.User;
import com.database_integration.App_Database.utility.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")

public class RegistrationController {
    private final IUserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenService tokenService;


    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationRequest registration, HttpServletRequest request) {
        User user = userService.registerUser(registration);
        // Publish the verification email event here
        publisher.publishEvent(new RegistrationCompleteEvent(user, UrlUtil.getApplicationUrl(request)));
        return "redirect:/registration/registration-form?success";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<VerificationToken> theToken = tokenService.findByToken(token);
        if (theToken.isPresent() && theToken.get().getUser().is_enabled()) {
            return "redirect:/login?verified";
        }

        String verificationResult = tokenService.validateToken(String.valueOf(theToken));
        if (verificationResult.equalsIgnoreCase("Invalid")) {
            return "redirect:/error?invalid";
        } else if (verificationResult.equalsIgnoreCase("Expired")) {
            return "redirect:/login?error/expired";
        } else if (verificationResult.equalsIgnoreCase("Valid")) {
            return "redirect:/login?valid";
        }
        return "redirect:/error?invalid";
    }
}

