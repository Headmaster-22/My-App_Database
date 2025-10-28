package com.database_integration.App_Database.event.listener;

import com.database_integration.App_Database.event.RegistrationCompleteEvent;
import com.database_integration.App_Database.registration.token.VerificationTokenService;
import com.database_integration.App_Database.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Properties;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener  implements  ApplicationListener<RegistrationCompleteEvent>{
    private final VerificationTokenService tokenService;
    private final JavaMailSender mailSender;
    private User user;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event){
        // Get the user
        user = event.getUser();
        // Generate a token
        String vToken = UUID.randomUUID().toString();
        // Save the token from the user
        tokenService.saveVerificationTokenForUser(user, vToken);
        // Build the verification website
        String url = event.getConfirmationUrl() + "/registration/verifyEmail?token" + vToken;
        // Send the email to the user
        try{
            sendVerificationEmail(url);
        }catch ( MessagingException | UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException{
        String subject = "Email Verification";
        String senderName = "Users verification Service";
        String mailContent = "<p> Hi, " + user.getFirst_name()+ ",<p>" +
                "<p> Thank you for registering with us" + " " + "Please follow the link below to complete your registration.<p>"+
                "<a href = \"" + url + "\"> Verify your email to activate your account" + "</a>" +
                "<p> Thank you <br> User Registration Portal Service";
        emailMessage(subject, senderName, mailContent, mailSender, user);
    }

    private static void emailMessage(String subject,  String senderName, String mailContent, JavaMailSender mailSender, User userName)
            throws MessagingException, UnsupportedEncodingException{
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message, true, "UTF_8");
        messageHelper.setFrom("jimohabdulquyum@gmail.com", senderName);
        messageHelper.setTo(userName.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}


